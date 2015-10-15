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

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Person) {
            Person that = (Person) other;
            result = (this.getID() == that.getID());
        }
        return result;
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
        if (preferences.containsKey(k)){
            int pref = preferences.get(k);
            if (pref != 0){
                return 6 - pref;//numbers should be reverted!!
            }
        }
        return 0;
    }

    public boolean isPreference(Shift s){
        return preferences.get(s.getFacility()) > 0;
    }

    private boolean shifFacility(Facility f, Shift s){
        if (s.getFacility().getName().equals(f)){
            return true;
        }
        return false;
    }

    public int getSumPreference(){
        int sum = 0;
        for(int i : preferences.values()){
            sum = sum+i;
        }
        return sum;

    }

    public boolean preferencesInclude(int num) {
        for (int i : preferences.values()){
            if (i == 6-num){
                return true;
            }
        }
        return false;
    }

}
