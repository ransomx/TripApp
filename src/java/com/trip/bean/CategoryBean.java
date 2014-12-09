/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.bean;

import com.trip.model.Term;
import com.trip.model.Type;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Dominik
 */
@ManagedBean(name = "category")
@ViewScoped
public class CategoryBean implements Serializable{
    private Type selectedType;
    private List<Term> termList;

    public Type getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(Type selectedType) {
        this.selectedType = selectedType;
        if(selectedType!=null)
        termList = selectedType.getTermList();
    }

    public List<Term> getTermList() {
        return termList;
    }

    public void setTermList(List<Term> termList) {
        this.termList = termList;
    }
    
    
}
