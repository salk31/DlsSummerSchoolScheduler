/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import uk.ac.diamond.ss.DodgyDifficultyWeightFactory;

@PlanningEntity(difficultyWeightFactoryClass = DodgyDifficultyWeightFactory.class)
public class Shift {

    private int ID;
public Shift(){
}

    public Shift(int ID) {
        ID =ID;
    }

   @PlanningVariable(valueRangeProviderRefs = "shiftIDs")
    public int getID() {
        return ID;
    }

    public void setID (int anID) {
        this.ID = anID;
    }

    //TODO
    //Day, type, beamline


}
