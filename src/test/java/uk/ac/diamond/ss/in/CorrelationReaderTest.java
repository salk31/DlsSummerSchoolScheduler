/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.in;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import uk.ac.diamond.ss.domain.Correlation;
import uk.ac.diamond.ss.domain.Facility;
import uk.ac.diamond.ss.domain.in.CorrelationReader;



/**
 *
 *
 */
public class CorrelationReaderTest {

    @Test
    public void testReadCorrelation() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/minimalProblemAndSolution.xlsx");
        Workbook wb = WorkbookFactory.create(inputStream);
        CorrelationReader pr = new CorrelationReader(wb.getSheet("correlation"));

        List<Correlation> crt = pr.read();

        int r = 0;
        Set<Facility> sf = new HashSet<Facility>();

        r = crt.get(0).getRate();
        sf = crt.get(0).getSetFacility();

        assertEquals(crt.size(),1);//Uncompleted rows are skipped
        assertEquals(r,2);
        assertEquals(sf.size(),2);
        assertTrue(sf.contains(Facility.getOrCreate("Test1",10000)));
        assertTrue(sf.contains(Facility.getOrCreate("Test2",10001)));
    }

}
