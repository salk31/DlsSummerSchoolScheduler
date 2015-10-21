/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss;




import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import uk.ac.diamond.ss.domain.Correlation;
import uk.ac.diamond.ss.domain.Facility;

/**
 *
 *
 */

public class CorrelationManagerTest {

    @Test
    public void testAssign(){
   Facility tr1 =  Facility.getOrCreate("tr1", 1111);
   Facility tr2 =  Facility.getOrCreate("tr2", 1112);
   Facility tr3 =  Facility.getOrCreate("tr3", 1113);

   Set<Facility> s1 = new HashSet<Facility>();
   s1.add(tr1);
   s1.add(tr2);

   Set<Facility> s2 = new HashSet<Facility>();
   s2.add(tr1);
   s2.add(tr3);

   List<Correlation> corr =  new ArrayList<Correlation>();
   corr.add(new Correlation(s1,3));
   corr.add(new Correlation(s2,3));

   new CorrelationManager().assign(corr);

   assertTrue(tr1.getCorrelations().contains(corr.get(0)));
   assertTrue(tr1.getCorrelations().contains(corr.get(1)));
   assertTrue(tr2.getCorrelations().contains(corr.get(0)));
   assertFalse(tr2.getCorrelations().contains(corr.get(1)));
   assertTrue(tr3.getCorrelations().contains(corr.get(1)));
   assertFalse(tr3.getCorrelations().contains(corr.get(0)));
    }
}
