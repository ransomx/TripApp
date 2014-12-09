/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.bean;

import com.trip.facade.PersonFacadeLocal;
import com.trip.model.Person;
import com.trip.utility.StaticVariables;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable{
    public static final String AUTH_KEY = "app.user.name";

    private String email;
    private String password;
    private Person current;

    public Person getCurrent() {
        return current;
    }

    @EJB
    private PersonFacadeLocal userDao;
    
    public String login() {
        current = userDao.findByEmail(email);
        if (current != null && !current.getPassword().equals(password)) {
            current = null;
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (current == null) {
            facesContext.addMessage(null, new FacesMessage("Tento uživatel neexistuje"));
            return (email = password = null);
        } else {
            facesContext.getExternalContext().getSessionMap().put(
                    StaticVariables.USER, current);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
        AUTH_KEY, current.getName());
            return "/restricted/userhome?faces-redirect=true";
        }
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(StaticVariables.USER);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
        .remove(AUTH_KEY);
        return "/login?faces-redirect=true";
    }
    
    public void checkPermissions(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)(fc.getExternalContext().getSession(false)); 
        String cid = (String) httpSession.getAttribute(AUTH_KEY);
        if( cid == null){
            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();
            handler.performNavigation("/login?faces-redirect=true");
        }
    }

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext()
        .getSessionMap().get(AUTH_KEY) != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
