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
    private Map<Shift,Integer> preferences = new HashMap<Shift,Integer>();

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

    public Map<Shift,Integer> getPreferences(){
        return preferences;
    }

    public int checkPreference(Shift s){
    /*    if(preferences.containsKey(s)){
            return preferences.get(s);
        }*/
        if(s.getID()==4 && name.equals("Ginger")){
            return 5;
        }
        if(s.getID()==3 && name.equals("Tom")){
            return 4;
        }
        return 0;
    }

    public boolean isPreference(Shift s){
        if(s.getID()== 3 && name.equals("Tom")){
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
        return false;
       // return preferences.containsKey(s);
    }



    // TODO __ read in people/preferences
    // TODO __ read in timetable
    // TODO __ write out timetable
    // TODO __ preferences Map or Join?
}
