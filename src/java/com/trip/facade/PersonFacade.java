/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.facade;

import com.trip.model.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dominik
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> implements PersonFacadeLocal {
    @PersistenceContext(unitName = "TripCore2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }
    
    @Override
    public Person findByEmail(String email) {
        Person company;
        try{
        company = (Person)em.createNamedQuery("Person.findByEmail").setParameter("email", email).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
        return company;
    }
    
}
