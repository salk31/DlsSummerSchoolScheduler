/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.in;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import uk.ac.diamond.ss.domain.Facility;
import uk.ac.diamond.ss.domain.Person;
import uk.ac.diamond.ss.domain.in.PersonReader;

public class PersonReaderTest {

    @Test
    public void testReadPeople() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/minimalProblemAndSolution.xlsx");
        Workbook wb = WorkbookFactory.create(inputStream);
        PersonReader pr = new PersonReader(wb.getSheet("candidate_preferences"));
        List<Person> got = pr.read();

        assertEquals("[Bugs Bunny, Tom, Ginger]", "" + got);

        assertEquals(got.get(0).getID(), 1);

        assertTrue(got.get(0).getPreferences().containsKey(Facility.getOrCreate("I07",0)));

        assertTrue(got.get(0).getPreferences().containsValue(3));
    }

}
