/*
 * Diamond User Administration System
 * Copyright � 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.out;

import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.score.constraint.ConstraintMatch;
import org.optaplanner.core.api.score.constraint.ConstraintMatchTotal;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 *
 *
 * @author yjs77802
 */
public class ScorePrinter {
    private final Solver solver;

    public ScorePrinter(Solver solver) {
        this.solver = solver;
    }

    public void print(Solution solution) {
        System.out.println("......................................................");
        System.out.println("Constraints");
        System.out.println("......................................................");

        ScoreDirector sd = solver.getScoreDirectorFactory().buildScoreDirector();
        sd.setWorkingSolution(solution);

        for (ConstraintMatchTotal constraintMatchTotal : sd.getConstraintMatchTotals()) {
            for (ConstraintMatch constraintMatch : constraintMatchTotal.getConstraintMatchSet()) {
                System.out.print(constraintMatch.getConstraintName());
                for (Object o : constraintMatch.getJustificationList()) {
                    System.out.print(", ");
                    System.out.print("" + o);
                }
                System.out.println(".");
            }
        }

        System.out.println("Score = " + sd.calculateScore());
    }
}
