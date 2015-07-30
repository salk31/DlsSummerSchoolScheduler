/*
 * Diamond User Administration System
 * Copyright © 2014 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss;

import java.util.Collection;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.constraint.ConstraintMatchTotal;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 * Wrapper around optiplanner.
 */

public class PlannerEngine {

    private final Solver solver;

    public PlannerEngine() {
        //System.setProperty("drools.dialect.java.compiler", "JANINO");

        SolverFactory solverFactory = SolverFactory.createFromXmlResource("solverConfig.xml");


        solver = solverFactory.buildSolver();
    }

    public Solver getSolver() {
        return solver;
    }

    public Collection<ConstraintMatchTotal> getConstraintMatchTotals() {
        PlannerSolution prob = new PlannerSolution();
        prob.start();


        ScoreDirector guiScoreDirector = solver.getScoreDirectorFactory().buildScoreDirector();
        guiScoreDirector.setWorkingSolution(prob);

        Score score = guiScoreDirector.calculateScore();
        return guiScoreDirector.getConstraintMatchTotals();
    }
}
