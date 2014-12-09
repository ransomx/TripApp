/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.bean;

import com.trip.facade.PersonFacadeLocal;
import com.trip.model.Person;
import com.trip.utility.StaticVariables;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Dominik
 */
@ManagedBean(name = "editUser")
@ViewScoped
public class EditUserBean {
    
    private Person user;
    private String emailA,emailB,passA,passB,name,surname;
    
    public EditUserBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        user = (Person)facesContext.getExternalContext().getSessionMap().get(StaticVariables.USER);
        name = user.getName();
        surname = user.getSurname();
        emailB = emailA = user.getEmail();
        passA = user.getPassword();
    }

    public String getEmailA() {
        return emailA;
    }

    public void setEmailA(String emailA) {
        this.emailA = emailA;
    }

    public String getEmailB() {
        return emailB;
    }

    public void setEmailB(String emailB) {
        this.emailB = emailB;
    }

    public String getPassA() {
        return passA;
    }

    public void setPassA(String passA) {
        this.passA = passA;
    }

    public String getPassB() {
        return passB;
    }

    public void setPassB(String passB) {
        this.passB = passB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    @EJB
    PersonFacadeLocal userDao;
    
    public String editUser(){
        user.setEmail(emailA);
        user.setName(name);
        user.setPassword(passA);
        user.setSurname(surname);
        
        userDao.edit(user);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        user = (Person)facesContext.getExternalContext().getSessionMap().put(StaticVariables.USER,user);
        return "/restricted/userhome?faces-redirect=true";
    }
}
