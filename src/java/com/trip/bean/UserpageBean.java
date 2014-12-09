/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.bean;

import com.trip.facade.PersonFacadeLocal;
import com.trip.facade.TypeFacadeLocal;
import com.trip.model.Person;
import com.trip.model.Type;
import com.trip.utility.StaticVariables;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dominik
 */
@ManagedBean(name = "userpage")
@ViewScoped
public class UserpageBean implements Serializable{
    
    @EJB
    PersonFacadeLocal userDao;
    @EJB
    TypeFacadeLocal typeDao;
    
    private Person current;
    private final FacesContext facesContext = FacesContext.getCurrentInstance();
    private List<Type> categoryList;
    private Type selectedCategory;

    @PostConstruct
    public void init() {
        current = (Person) facesContext.getExternalContext().getSessionMap().get(StaticVariables.USER);
        categoryList =  typeDao.findAll();
    }

    public Type getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Type selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public List<Type> getTermList() {
        return categoryList;
    }

    public void setTermList(List<Type> termList) {
        this.categoryList = termList;
    }

    public Person getCurrent() {
        return current;
    }
    
    public boolean isCoordinator(){
        if(current!=null)
            return current.getRole().equals(StaticVariables.COORD);
        else return false;
    }
    
    
}
