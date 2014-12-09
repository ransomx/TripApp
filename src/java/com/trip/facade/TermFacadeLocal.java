/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.facade;

import com.trip.model.Term;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dominik
 */
@Local
public interface TermFacadeLocal {

    void create(Term term);

    void edit(Term term);

    void remove(Term term);

    Term find(Object id);

    List<Term> findAll();

    List<Term> findRange(int[] range);

    int count();
    
}
