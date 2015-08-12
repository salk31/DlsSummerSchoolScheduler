/*
 * Diamond User Administration System
 * Copyright Â© 2013 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.CountableValueRange;
import org.optaplanner.core.api.domain.valuerange.ValueRangeFactory;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;

import uk.ac.diamond.ss.domain.Person;
import uk.ac.diamond.ss.domain.Shift;

/**
 * A candidate solution to the scheduling problem.
 */
@PlanningSolution

public class PlannerSolution implements org.optaplanner.core.api.domain.solution.Solution<HardSoftLongScore> {


    private List<Person> people = new ArrayList<Person>();

    private List<Shift> shifts = new ArrayList<Shift>();

    private List facts  = new ArrayList();

    private HardSoftLongScore score;

    public PlannerSolution() {
    }

    @Override
    public Collection<? extends Object> getProblemFacts() {
        return facts;
    }

    @Override
    public HardSoftLongScore getScore() {
        return score;
    }

    @Override
    public void setScore(HardSoftLongScore p) {
        this.score = p;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

  @PlanningEntityCollectionProperty
    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> sh) {
        this.shifts = sh;
    }

    @ValueRangeProvider(id = "shiftIDs")
    //Instead of a Collection, you can also return a ValueRange or CountableValueRange, build by the ValueRangeFactory:
    public CountableValueRange<Integer> getShiftIDs() {
        return ValueRangeFactory.createIntValueRange(1, shifts.size());//2*15 number of bemalines
    }

}
