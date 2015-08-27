/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import java.util.ArrayList;
import java.util.List;

public class Shift {

    private int ID  = 0;
    private int sTime;
    private List<Shift> similarShifts = new ArrayList<Shift>();
    private List<Shift> longExperiment = new ArrayList<Shift>();
    private int eTime;
    private Facility facility ;
    private boolean isLong = false;

    public Shift(){
    }

    public int getID() {
        return ID;
    }

    public void setID (int anID) {
        ID = anID;
    }

    public int getStartTime() {
        return sTime;
    }

    public void setStartTime (int startTime) {
        sTime = startTime;
    }

    public void addSimilar(Shift s){
        if(!similarShifts.contains(s)){
            similarShifts.add(s);
        }
    }

    public boolean checkIfSimilar(Shift s){
        if(similarShifts.contains(s)){
            return true;
        }
        return false;
    }

    public void addLongExperiment(Shift s){
        setLong();
        //s.setLong();
        if(!longExperiment.contains(s)){
            longExperiment.add(s);
        }
    }

    public boolean checkIfLong(Shift s){
        if(longExperiment.contains(s)){
            return true;
        }
        return false;
    }

    public boolean isLong(){
        return isLong;
    }

    public void setLong(){
        isLong=true;
    }

    public boolean checkOverlap(Shift s){
        if((s.getStartTime() <= eTime && s.getStartTime() >= sTime ) || (s.getEndTime() >= sTime && s.getEndTime() <= eTime)){
            return true;
        }
        return false;
    }

    public int getEndTime() {
        return eTime;
    }

    public void setEndTime (int endTime) {
        eTime = endTime;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility machine) {
        facility  = machine;
    }

}
