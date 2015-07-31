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

import uk.ac.diamond.ss.domain.Person;

/**
 * A candidate solution to the scheduling problem.
 */
@PlanningSolution
public class PlannerSolution implements org.optaplanner.core.api.domain.solution.Solution<HardSoftLongScore> {


    private List<Person> people = new ArrayList<Person>();

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

    @PlanningEntityCollectionProperty
    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }


    @ValueRangeProvider(id = "names")
    public List<String> getNames() {
        List<String> names = new ArrayList<String>();
        names.add("Fred");
        names.add("Ginger");

        return names;
    }
}
