/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.facade;

import com.trip.model.Place;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dominik
 */
@Stateless
public class PlaceFacade extends AbstractFacade<Place> implements PlaceFacadeLocal {
    @PersistenceContext(unitName = "TripCore2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaceFacade() {
        super(Place.class);
    }
    
    @Override
    public Place findByName(String name) {
        Place place;
        try{
        place = (Place)em.createNamedQuery("Place.findByName").setParameter("name", name).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
        return place;
    }
    
}
