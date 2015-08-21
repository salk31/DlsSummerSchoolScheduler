/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

public class Shift {

    private int ID;

public Shift(){
}

    public Shift(int ID) {
        ID =ID;
    }

    public int getID() {
        return ID;
    }

    public void setID (int anID) {
        this.ID = anID;
    }

    //TODO
    //Day, time, beamline


}
