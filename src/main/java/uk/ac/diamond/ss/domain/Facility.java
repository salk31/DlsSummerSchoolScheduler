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

    public static Facility getOrCreate(String name) {
        Facility fac = facilityByName.get(name);
        if (fac == null) {
            fac = new Facility(name);
            facilityByName.put(name, fac);
        }
        return fac;
    }

    private final String name;

    public Facility(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
