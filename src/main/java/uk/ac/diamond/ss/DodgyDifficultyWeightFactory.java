package uk.ac.diamond.ss;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory;

import uk.ac.diamond.ss.domain.Allocation;

public class DodgyDifficultyWeightFactory implements SelectionSorterWeightFactory<PlannerSolution, Allocation> {

// TODO __ seems like we might want to use this but current implementation is nonsense.

@Override
public Comparable createSorterWeight(PlannerSolution solution, Allocation person) {

      return new Comparable<Object>() {
        @Override
        public int compareTo(Object o) {
          return -1;
        }
      };
  }


}
