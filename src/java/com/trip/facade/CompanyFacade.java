/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.facade;

import com.trip.model.Company;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dominik
 */
@Stateless
public class CompanyFacade extends AbstractFacade<Company> implements CompanyFacadeLocal {
    @PersistenceContext(unitName = "TripCore2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyFacade() {
        super(Company.class);
    }
    
    @Override
    public Company findByEmail(String email){
        Company company;
        try{
        company = (Company)em.createNamedQuery("Company.findByEmail").setParameter("email", email).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
        return company;
    }
    
}
