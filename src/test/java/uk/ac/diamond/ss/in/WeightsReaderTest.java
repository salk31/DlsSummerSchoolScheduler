/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.in;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import uk.ac.diamond.ss.domain.in.WeightsReader;

public class WeightsReaderTest {

    @Test
    public void testReadWeights() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/minimalProblemAndSolution.xlsx");
        Workbook wb = WorkbookFactory.create(inputStream);
        new WeightsReader(wb.getSheet("weights")).read();

        assertEquals(WeightsReader.LONG_EXPERIMANTS ,6);
        assertEquals(WeightsReader.GROUP_SIZE_HARD, 1);
        assertEquals(WeightsReader.CORRELATION ,1);
    }

}
