/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.bean;

import com.trip.facade.NoteFacadeLocal;
import com.trip.facade.TermFacadeLocal;
import com.trip.model.Note;
import com.trip.model.Person;
import com.trip.model.Term;
import com.trip.utility.StaticVariables;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dominik
 */
@ManagedBean(name = "term")
@ViewScoped
public class TermBean implements Serializable {

    private Term selectedTerm;
    private String noteText;
            
    @EJB
    TermFacadeLocal termDao;

    public Term getSelectedTerm() {
        return selectedTerm;
    }

    public void setSelectedTerm(Term selectedTerm) {
        this.selectedTerm = selectedTerm;
    }

    public void signUp() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Person current = (Person) facesContext.getExternalContext().getSessionMap().get(StaticVariables.USER);
        
        for (Person p : selectedTerm.getPersonList()) {
            if(p.getEmail().equals(current.getEmail())){
                return;
            }
        }
        
        selectedTerm.getPersonList().add(current);
        termDao.edit(selectedTerm);
    }
    
    public void signOut() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        selectedTerm.getPersonList().remove(
                (Person) facesContext.getExternalContext().getSessionMap().get(StaticVariables.USER));
        termDao.edit(selectedTerm);
    }
    
    public boolean isSelected(){
        return selectedTerm!=null;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
    
    @EJB
    NoteFacadeLocal noteDao;
    public void addNote(){
        Note note = new Note();
        note.setTermid(selectedTerm);
        note.setContent(noteText);
        noteDao.create(note);
        selectedTerm.getNoteList().add(note);
        noteText="";
    }
    
    
}
