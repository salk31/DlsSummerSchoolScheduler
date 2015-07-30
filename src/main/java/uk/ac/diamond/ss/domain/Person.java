/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import uk.ac.diamond.ss.DodgyDifficultyWeightFactory;

/**
 *
 *
 * @author yjs77802
 */
@PlanningEntity(difficultyWeightFactoryClass = DodgyDifficultyWeightFactory.class)
public class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @PlanningVariable(valueRangeProviderRefs = "names")
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

    // TODO __ read in people/preferences
    // TODO __ read in timetable
    // TODO __ write out timetable
    // TODO __ preferences Map or Join?
}
