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
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;

import uk.ac.diamond.ss.domain.Allocation;
import uk.ac.diamond.ss.domain.Person;
import uk.ac.diamond.ss.domain.Shift;

/**
 * A candidate solution to the scheduling problem.
 */
@PlanningSolution

public class PlannerSolution implements org.optaplanner.core.api.domain.solution.Solution<HardSoftLongScore> {

    // Problem facts
    private List<Person> people = new ArrayList<Person>();

    private List<Shift> shifts = new ArrayList<Shift>();

    // Problem entities
    private List<Allocation> allocations = new ArrayList<Allocation>();

    private List facts = new ArrayList();

    private HardSoftLongScore score;

    public PlannerSolution() {
    }

    @Override
    public Collection<? extends Object> getProblemFacts() {
        facts.addAll(people);
        facts.addAll(shifts);
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

    @ValueRangeProvider(id = "shifts")
    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> sh) {
        this.shifts = sh;
    }

    @PlanningEntityCollectionProperty
    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> list) {
        this.allocations = list;
    }

    public void setAllocations() {
        this.allocations = createAllocationList();
    }

    private List<Allocation> createAllocationList() {
        int id = 0;
        List<Allocation> alloList = new ArrayList<Allocation>();
        for (int i = 0; i < Parameters.SHIFTS_PER_STUDENT; i++) {
            for (Person p : getPeople()) {
                Allocation allocation = new Allocation();
                allocation.setPerson(p);
                allocation.setID(id);
                id++;
                alloList.add(allocation);
            }
        }
        return alloList;
    }

}
