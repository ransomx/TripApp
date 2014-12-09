/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.bean;

import com.trip.facade.AddressFacadeLocal;
import com.trip.facade.CompanyFacadeLocal;
import com.trip.facade.PersonFacadeLocal;
import com.trip.model.Address;
import com.trip.model.Company;
import com.trip.model.Person;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "register")
@ViewScoped
public class RegisterBean implements Serializable {

    private String emailA;
    private String emailB;
    private String passwordA;
    private String passwordB;
    private String name;
    private String surname;
    private String selectedRole;
    private static final String COORD = "Koordinátor";

    @EJB
    private PersonFacadeLocal personDao;

    public RegisterBean() {

    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }
    
    public boolean isCoordinator(){
        if(selectedRole!=null)
        return selectedRole.equals(COORD);
        else return false;
    }
    

    public String register() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (!emailA.equals(emailB)) {
            fc.addMessage(null, new FacesMessage("Emaily si neodpovídají"));
            return (emailA = emailB = null);
        } else if (!passwordA.equals(passwordB)) {
            fc.addMessage(null, new FacesMessage("Hesla si neodpovídají"));
            return (passwordA = passwordB = null);
        }

        Person u = personDao.findByEmail(emailA);
        
        if (u != null) {
            fc.addMessage(null, new FacesMessage("Uživatel s tímto emailem již existuje"));
            return (emailA = emailB = null);
        }
        
        if(isCoordinator()){
        personDao.create(prepAdd(makeCompany(),makeAddress()));
        }
        else
        personDao.create(prepAdd());
        
        return "/registerSuccess?faces-redirect=true";
    }

    private Person prepAdd() {
        Person toAdd = new Person();
        toAdd.setName(name);
        toAdd.setSurname(surname);
        toAdd.setRole(selectedRole);
        toAdd.setEmail(emailA);
        toAdd.setPassword(passwordA);
        return toAdd;
    }
    
    @EJB
    CompanyFacadeLocal companyDao;
    @EJB
    AddressFacadeLocal addressDao;
    
    
    // If coordinator role is selected
    private Person prepAdd(Company c, Address a) {
        
        Company cx = companyDao.findByEmail(c.getEmail());
        if(cx == null){
            Address ax = addressDao.findByUnique(a.getStreet(),a.getHouseNumber());
            if(ax == null){
                ax = a;
                addressDao.create(ax);
            }
            cx = c;
            cx.setAddressid(a);
            companyDao.create(cx);
        }
        
        Person toAdd = new Person();
        toAdd.setCompanyid(cx);
        toAdd.setName(name);
        toAdd.setSurname(surname);
        toAdd.setRole(selectedRole);
        toAdd.setEmail(emailA);
        toAdd.setPassword(passwordA);
        
        return toAdd;
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

    public String getPasswordA() {
        return passwordA;
    }

    public void setPasswordA(String passwordA) {
        this.passwordA = passwordA;
    }

    public String getPasswordB() {
        return passwordB;
    }

    public void setPasswordB(String passwordB) {
        this.passwordB = passwordB;
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
    
    private String street,postcode,houseNumber,city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public Address makeAddress(){
        Address a = new Address();
        a.setCity(city);
        a.setHouseNumber(houseNumber);
        a.setPostcode(postcode);
        a.setStreet(street);
        return a;
    }
    
    private String cname,cemail;

    public String getCName() {
        return cname;
    }

    public void setCName(String name) {
        this.cname = name;
    }

    public String getCEmail() {
        return cemail;
    }

    public void setCEmail(String email) {
        this.cemail = email;
    }
    
    public Company makeCompany(){
        Company c = new Company();
        c.setName(cname);
        c.setEmail(cemail);
        return c;
    }
}
