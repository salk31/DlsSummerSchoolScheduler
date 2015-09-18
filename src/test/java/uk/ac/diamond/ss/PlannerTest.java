/*
 * Diamond User Administration System
 * Copyright © 2012 Diamond Light Source Ltd
 */
package uk.ac.diamond.ss;





import org.junit.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import uk.ac.diamond.ss.domain.Person;


/**
 *
 */
public class PlannerTest  {

    @Test
    public void testSchedule() {
        PlannerEngine pe = new PlannerEngine();
        Solver solver = pe.getSolver();


        PlannerSolution prob = new PlannerSolution();

        String[] names = new String[]{"Ginger", "Ginger"};
        for (String name : names) {
            Person p = new Person(name);
            p.setName(name);
            prob.getPeople().add(p);
        }

        ScoreDirector guiScoreDirector = solver.getScoreDirectorFactory().buildScoreDirector();
        guiScoreDirector.setWorkingSolution(prob);
       // Object x = guiScoreDirector.getConstraintMatchTotals();
        //assertEquals("[defaultpkg/sameName/level0=-4]", "" + guiScoreDirector.getConstraintMatchTotals());
        //Logger root11 = (Logger) LoggerFactory.getLogger("org.optaplanner.core");
        //root11.setLevel(Level.DEBUG);

        solver.solve(prob);

      //  assertEquals("0hard/0soft", "" + solver.getBestSolution().getScore());

//        for (ConstraintMatchTotal constraintMatchTotal : pe.getConstraintMatchTotals()) {
//
//            String constraintName = constraintMatchTotal.getConstraintName();
//
//            Number weightTotal = constraintMatchTotal.getWeightTotalAsNumber();
//
//            for (ConstraintMatch constraintMatch : constraintMatchTotal.getConstraintMatchSet()) {
//                Number weight = constraintMatch.getWeightAsNumber();
//
//                System.out.println("type=" + constraintMatch.getConstraintName());
//                for (Object o : constraintMatch.getJustificationList()) {
//                    System.out.println(" x=" + o);
//                }
//                System.out.println("------------------------------------------");
//
//            }
//
//        }


        PlannerSolution ps = (PlannerSolution) solver.getBestSolution();
        for (Person p : ps.getPeople()) {
            System.out.println("Xtest=" + p);
        }
    }
}

