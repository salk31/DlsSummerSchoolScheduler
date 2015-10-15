/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import uk.ac.diamond.ss.Parameters;

public class Shift {

    private final int ID;
    private int sTime;
    private int eTime;
    private final Facility facility ;
    private Shift pair;

    public Shift(Facility fa, int count){
        this.facility = fa;
        this.ID = count;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Shift) {
            Shift that = (Shift) other;
            result = (this.getID() == that.getID());
        }
        return result;
    }

    public int getStudentsPerShift() {
        return Parameters.STUDENTS_PER_SHIFT;
    }

    public int getID() {
        return ID;
    }

    public int getStartTime() {
        return sTime;
    }

    public void setStartTime (int startTime) {
        sTime = startTime;
    }

    public boolean checkIfSimilar(Shift s){
        if(s.getFacility().getName() == facility.getName()){
            if(pair!=null && s.getPair().equals(pair)){
                return false;
            }
            return true;
        }
        return false;
    }

    public Shift getPair(){
        return pair;
    }

    public void setPair(Shift sh) {
        pair = sh;
    }

    public boolean checkOverlap(Shift s){
        if ((s.getEndTime() <= sTime && s.getEndTime() < eTime && s.getStartTime() < sTime && s.getStartTime() < eTime)
                || (s.getStartTime() >= eTime && s.getStartTime() > sTime && s.getEndTime() > eTime && s.getEndTime() > sTime)){
            return false;//true
        }
        return true;//False
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

    @Override
    public String toString() {
        return facility.getName() + " " + sTime;
    }
}
