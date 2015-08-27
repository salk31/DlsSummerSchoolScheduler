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

public class Person {

    private String name;
    private int ID;
    private Map<Facility,Integer> preferences = new HashMap<Facility,Integer>();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID (int anID) {
        this.ID = anID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Map<Facility,Integer> getPreferences(){
        return preferences;
    }

    public void setPereferences(Map<Facility, Integer> pref) {
        preferences = pref;
    }

    public int checkPreference(Shift s){
        Facility k = s.getFacility();
        if(preferences.containsKey(k)){
            return preferences.get(k);
        }
        return 0;
    }

    public boolean isPreference(Shift s){
       return preferences.containsKey(s.getFacility());
    }

    private boolean shifFacility(Facility f, Shift s){
        if(s.getFacility().getName().equals(f)){
            return true;
        }
       return false;
    }

    /* if(s.getID()==4 && name.equals("Ginger")){
    return 5;
}
if(s.getID()==3 && name.equals("Tom")){
    return 4;
}*/

    /*   if(s.getID()== 3 && name.equals("Tom")){
    return true;
}
if(s.getID()== 4 && name.equals("Tom")){
    return true;
}
if(s.getID()==4 && name.equals("Ginger")){
    return true;
}
if(s.getID()==20 && name.equals("Bugs Bunny")){
    return true;
}
return false;*/
    // TODO __ read in timetable
    // TODO __ write out timetable
    // TODO __ preferences Map or Join?
}
