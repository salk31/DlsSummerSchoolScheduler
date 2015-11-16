/*
 * Diamond User Administration System
 * Copyright � 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import java.util.HashMap;
import java.util.Map;

import uk.ac.diamond.ss.domain.readers.KeyValuesReader;
import uk.ac.diamond.ss.domain.readers.WeightsReader;

/**
 *
 *
 * @author yjs77802
 */

public class Person {

    private String name;
    private int ID;
    private Map<Facility,Integer> preferences = new HashMap<Facility,Integer>();//mapped preferences are kept in the map

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
            return pref;
        }
        return 0;
    }

    public boolean isPreference(Shift s){
        return preferences.get(s.getFacility()) > 0;
    }

    public boolean isFirstPreference(Shift s){
        return preferences.get(s.getFacility()) == KeyValuesReader.MAX_PREFERENCES;
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
        return 2*sum;//blee needs to be better
    }

    public boolean preferencesInclude(int num) {
        for (int i : preferences.values()){
            if (i == num){
                return true;
            }
        }
        return false;
    }

    public static int mapPreference( int pref){
        //invert
        int mappedPreference = 0;
        if (pref != 0){
            mappedPreference = 1+ KeyValuesReader.MAX_PREFERENCES - pref;
        }
        //scale
       if( mappedPreference == 5){
           mappedPreference = WeightsReader.PREFERENCE1 * mappedPreference;
       }
       if( mappedPreference == 4){
           mappedPreference = WeightsReader.PREFERENCE2 * mappedPreference;
       }
       if( mappedPreference == 3){
           mappedPreference = WeightsReader.PREFERENCE3 * mappedPreference;
       }
       if( mappedPreference == 2){
           mappedPreference = WeightsReader.PREFERENCE4 * mappedPreference;
       }
       if( mappedPreference == 1){
           mappedPreference = WeightsReader.PREFERENCE5 * mappedPreference;
       }
       return mappedPreference;
    }

}
