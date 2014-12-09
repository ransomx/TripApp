/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.bean;

import com.trip.facade.TypeFacadeLocal;
import com.trip.model.Type;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "newCategory")
@ViewScoped
public class NewCategoryBean implements Serializable{
    private String name, desc, duration;
    private List<Type> categoryList;
    //private Person current;

    @EJB
    TypeFacadeLocal typeDao;
    
    @PostConstruct
    public void init() {
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        categoryList = typeDao.findAll();
    }
    
        public List<Type> getCategoryList() {
        return categoryList;
    }
    
    public void setCategoryList(List<Type> categoryList) {
        this.categoryList = categoryList;
    }
    
    public void createCategory(){
        Type c = new Type();
        c.setName(name);
        c.setDuration(duration);
        c.setDescription(desc);
        
        typeDao.create(c);
        categoryList.add(c);
        name="";
        desc="";
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
