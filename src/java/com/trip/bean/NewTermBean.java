/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.bean;

import com.trip.facade.AddressFacadeLocal;
import com.trip.facade.PlaceFacadeLocal;
import com.trip.facade.TermFacadeLocal;
import com.trip.facade.TypeFacadeLocal;
import com.trip.model.Address;
import com.trip.model.Person;
import com.trip.model.Place;
import com.trip.model.Term;
import com.trip.model.Type;
import com.trip.utility.StaticVariables;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dominik
 */
@ManagedBean(name = "newTerm")
@ViewScoped
public class NewTermBean implements Serializable {

    private String name, street, placeName, city, postcode, house;
    private Type selectedCategory;
    private Date date;

    @EJB
    TypeFacadeLocal typeDao;
    @EJB
    TermFacadeLocal termDao;
    @EJB
    AddressFacadeLocal addressDao;
    @EJB
    PlaceFacadeLocal placeDao;

    public String createTerm() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Term term = new Term();
        term.setName(name);

        if (selectedCategory == null) {
            facesContext.addMessage(null, new FacesMessage("Zvolte kategorii"));
            return null;
        }

        Address ax = addressDao.findByUnique(street, house);
        if (ax == null) {
            ax = new Address();
            ax.setCity(city);
            ax.setHouseNumber(house);
            ax.setPostcode(postcode);
            ax.setStreet(street);
            addressDao.create(ax);
        }

        Place p = placeDao.findByName(name);
        if (p == null) {
            p = new Place();
            p.setName(placeName);
            p.setAddressid(ax);
            placeDao.create(p);
        }

        term.setPlaceid(p);
        term.setTypeid(selectedCategory);
        term.setCoordinatorId((Person) facesContext.getExternalContext().getSessionMap().get(StaticVariables.USER));
        term.setDate(date);
        termDao.create(term);
        selectedCategory.getTermList().add(term);
        return "/restricted/userhome?faces-redirect=true";
    }

    public Type getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Type selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
