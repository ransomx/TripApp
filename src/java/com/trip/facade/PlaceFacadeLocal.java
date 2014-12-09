/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.facade;

import com.trip.model.Place;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dominik
 */
@Local
public interface PlaceFacadeLocal {

    void create(Place place);

    void edit(Place place);

    void remove(Place place);

    Place find(Object id);

    List<Place> findAll();

    List<Place> findRange(int[] range);

    int count();
    
    Place findByName(String name);
    
}
