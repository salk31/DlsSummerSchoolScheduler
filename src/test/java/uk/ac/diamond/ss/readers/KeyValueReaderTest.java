/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.readers;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import uk.ac.diamond.ss.domain.readers.KeyValuesReader;



/**
 *
 *
 */
public class KeyValueReaderTest {

    @Test
    public void testReadKeyValues() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/minimalProblemAndSolution.xlsx");
        Workbook wb = WorkbookFactory.create(inputStream);
        new KeyValuesReader(wb.getSheet("key_values")).read();


        assertEquals(KeyValuesReader.SHIFTS_PER_STUDENT ,1);
        assertEquals(KeyValuesReader.STUDENTS_PER_SHIFT ,5);
        assertEquals(KeyValuesReader.MAX_PREFERENCES ,5);//default if null
    }

}
