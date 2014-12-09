/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.facade;

import com.trip.model.Note;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dominik
 */
@Local
public interface NoteFacadeLocal {

    void create(Note note);

    void edit(Note note);

    void remove(Note note);

    Note find(Object id);

    List<Note> findAll();

    List<Note> findRange(int[] range);

    int count();
    
}
