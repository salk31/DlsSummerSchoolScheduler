/*
 * Diamond User Administration System
 * Copyright � 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author yjs77802
 */
public class Facility {

    private static final Map<String, Facility> facilityByName = new HashMap<String, Facility>();

    public static Facility getOrCreate(String name,int aID) {
        Facility fac = facilityByName.get(name);
        if (fac == null) {
            fac = new Facility(name);
            fac.setID(aID);
            facilityByName.put(name, fac);
        }
        return fac;
    }

    private final String name;
    private int id;

    public Facility(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o){
        Facility f = (Facility) o;
        return f.getName().equals(name);
    }

    @Override
    public int hashCode() {
        return id;
    }

    private void setID(int aID) {
        id = aID;
    }

}
