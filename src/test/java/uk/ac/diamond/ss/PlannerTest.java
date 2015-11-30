/*
 * Diamond User Administration System
 * Copyright © 2012 Diamond Light Source Ltd
 */
package uk.ac.diamond.ss;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.optaplanner.core.api.score.constraint.ConstraintMatchTotal;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import uk.ac.diamond.ss.domain.Allocation;
import uk.ac.diamond.ss.domain.Correlation;
import uk.ac.diamond.ss.domain.Facility;
import uk.ac.diamond.ss.domain.Person;
import uk.ac.diamond.ss.domain.Shift;
import uk.ac.diamond.ss.domain.in.KeyValuesReader;
import uk.ac.diamond.ss.domain.in.WeightsReader;

public class PlannerTest {

	@Test
	public void testSchedule() {
		PlannerEngine pe = new PlannerEngine();
		Solver solver = pe.getSolver();
		// 4 out of 10 beamlines implemented
		Facility frac1 = Facility.getOrCreate("I11", 101);
		frac1.setLong();
		Facility frac2 = Facility.getOrCreate("I06", 102);
		Facility frac3 = Facility.getOrCreate("I07", 103);
		Facility frac4 = Facility.getOrCreate("I15", 104);
		Map<Facility, Integer> preferences = new HashMap<Facility, Integer>();
		preferences.put(frac1, 2);// preference towards I11
		preferences.put(frac2, 1);// preference towards I06
		PlannerSolution prob = new PlannerSolution();

		String[] names = new String[] { "Ginger", "Blue" };
		int id = 1;
		for (String name : names) {
			Person p = new Person(name);
			p.setName(name);
			p.setID(id);
			if (name.equals("Ginger")) {
				// Ginger is expected to get I11 and I06
				p.setPereferences(preferences);// only Ginger gets preferences
			}
			id++;
			prob.getPeople().add(p);
		}

		Set<Facility> sf = new HashSet<Facility>();
		sf.add(frac3);
		sf.add(frac4);
		int rate = 1;
		// to check correlation constraint: 0 expected in the final soft score
		Correlation c1 = new Correlation(sf, rate);
		frac4.addCorrelation(c1);// frac4 correlated with all
		frac3.addCorrelation(c1);// frac4 correlated with all
		List<Shift> as = new ArrayList<Shift>();
		Shift sh1 = new Shift(frac1, 0, 0, 1);
		Shift sh2 = new Shift(frac1, 1, 1, 2);
		Shift sh3 = new Shift(frac1, 2, 2, 3);
		Shift sh4 = new Shift(frac1, 3, 3, 4);
		// to check long experiment constraint I11 in pairs expected in the
		// solution
		sh1.setPair(sh2);
		sh2.setPair(sh1);
		sh3.setPair(sh4);
		sh4.setPair(sh3);
		as.add(sh1);
		as.add(sh2);
		as.add(sh3);
		as.add(sh4);
		as.add(new Shift(frac2, 4, 0, 1));
		as.add(new Shift(frac2, 5, 1, 2));
		as.add(new Shift(frac2, 6, 2, 3));
		as.add(new Shift(frac2, 7, 3, 4));
		as.add(new Shift(frac3, 8, 0, 1));
		as.add(new Shift(frac3, 9, 1, 2));
		as.add(new Shift(frac3, 10, 2, 3));
		as.add(new Shift(frac3, 11, 3, 4));
		as.add(new Shift(frac4, 12, 0, 1));
		as.add(new Shift(frac4, 13, 1, 2));

		prob.setShifts(as);

		prob.setAllocations();

		ScoreDirector guiScoreDirector = solver.getScoreDirectorFactory()
				.buildScoreDirector();
		guiScoreDirector.setWorkingSolution(prob);

		// initial weights
		for (ConstraintMatchTotal constraintMatchTotal : guiScoreDirector
				.getConstraintMatchTotals()) {
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard1")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard2")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard3")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard4")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard5")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"preferencesSoft")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -5*WeightsReader.PREFERENCES);// the total preference is sum of all
											// preferences given (long experiments counted twice
			}
			if (constraintMatchTotal.getConstraintName()
					.equals("groupSizeSoft")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
			}
			if (constraintMatchTotal.getConstraintName()
					.equals("groupSizeHard")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"noOverlappingAllocationsHard")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1*WeightsReader.NO_OVERLAPPING);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"noSimilarSesssionsHard")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1*WeightsReader.NO_SIMILOAR);
				assertEquals(constraintMatchTotal.getScoreLevel(), 0);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"correlationSoft")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
				assertEquals(constraintMatchTotal.getScoreLevel(), 1);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"longExperimentsPairsHard")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1*WeightsReader.LONG_EXPERIMANTS);
				assertEquals(constraintMatchTotal.getScoreLevel(), 0);
			}
		}

		solver.solve(prob);

		//solution time test
		long timeMinutes = solver.getTimeMillisSpent()/60000;	
		if(timeMinutes < KeyValuesReader.TERMINATION_TOTAL_TIME){
			assertTrue(timeMinutes >= KeyValuesReader.TERMINATION_TIME_UNIMPROVED);
		}
		else{
			assertTrue(timeMinutes == KeyValuesReader.TERMINATION_TOTAL_TIME);
		}
		
		PlannerSolution ps = (PlannerSolution) solver.getBestSolution();
		
		// final score - works only for the default of weights in WeightsReader!!!!!
		// -6 as there are 4 out 10 beamlines implemented for the tests
		assertEquals("-6hard/0soft", "" + ps.getScore());

		// final weights
		guiScoreDirector.setWorkingSolution(ps);
		for (ConstraintMatchTotal constraintMatchTotal : guiScoreDirector
				.getConstraintMatchTotals()) {
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard1")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard2")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard3")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard4")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard5")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"groupSizeHard6")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), -1);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"preferencesSoft")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);// meet preferences - long experiment
											// so can get zero 0
			}
			if (constraintMatchTotal.getConstraintName()
					.equals("groupSizeSoft")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
			}
			if (constraintMatchTotal.getConstraintName()
					.equals("groupSizeHard")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"noOverlappingAllocationsHard")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"noSimilarSesssionsHard")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
				assertEquals(constraintMatchTotal.getScoreLevel(), 0);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"correlationSoft")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);
				assertEquals(constraintMatchTotal.getScoreLevel(), 1);
			}
			if (constraintMatchTotal.getConstraintName().equals(
					"longExperimentsPairsHard")) {
				assertEquals(constraintMatchTotal.getWeightTotalAsNumber()
						.intValue(), 0);// pairs kept
				assertEquals(constraintMatchTotal.getScoreLevel(), 0);
			}
		}
		// check the solution
		int long_eperiments = 0;
		int pref_beamline = 0;
		for (Allocation a : ps.getAllocations()) {
			// long experiments
			if (a.getPerson().getName().equals("Ginger")
					&& a.getShift().getFacility().getName().equals("I11")) {
				long_eperiments++;
			}

			// preferences
			if (a.getPerson().getName().equals("Ginger")
					&& (a.getShift().getFacility().getName().equals("I11") || a
							.getShift().getFacility().getName().equals("I06"))) {
				pref_beamline++;
			}
			System.out.println(a.getPerson().getName() + " : "
					+ a.getShift().getFacility().getName());
		}

		assertEquals(long_eperiments, 2);

		assertEquals(pref_beamline, 3);
	}

}
