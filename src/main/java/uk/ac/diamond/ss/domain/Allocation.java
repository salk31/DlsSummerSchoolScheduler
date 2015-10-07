/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import uk.ac.diamond.ss.DodgyDifficultyWeightFactory;

@PlanningEntity(difficultyWeightFactoryClass = DodgyDifficultyWeightFactory.class)
public class Allocation {

    private Person p;
    private Shift sh;
    private int ID;

    public int getID(){
        return ID;
    }

    public void setID(int i){
        ID = i;
    }

    //@PlanningVariable(valueRangeProviderRefs = {"people"})
    public Person getPerson(){
        return p;
    }

    public void setPerson( Person aP){
        p = aP;
    }

    public void setShift( Shift aSh){
        sh = aSh;
    }

    @PlanningVariable(valueRangeProviderRefs = {"shifts"})
    public Shift getShift(){
        return sh;
    }

}
