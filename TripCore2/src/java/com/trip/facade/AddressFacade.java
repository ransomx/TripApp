/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.facade;

import com.trip.model.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dominik
 */
@Stateless
public class AddressFacade extends AbstractFacade<Address> implements AddressFacadeLocal {
    @PersistenceContext(unitName = "TripCore2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }
    
    @Override
    public Address findByUnique(String street, String houseNumber){
        Address address;
        try{
        address = (Address)em.createNamedQuery("Address.findByStreetHouse").setParameter("street", street).setParameter("houseNumber", houseNumber).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
        return address;
    }
    
}
