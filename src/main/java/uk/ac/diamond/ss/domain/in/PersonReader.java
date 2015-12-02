/*
 * Diamond User Administration System
 * Copyright � 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.in;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uk.ac.diamond.ss.domain.Facility;
import uk.ac.diamond.ss.domain.Person;


/**
 * Read in people and
 */
public class PersonReader {

    private Sheet sheet;

    public PersonReader(Sheet sheet) {
        this.sheet = sheet;
    }

    public List<Person> read() {
        List<Person> result = new ArrayList<Person>();

        // read first row for preference names
        Row firstRow = sheet.getRow(0);
        if(firstRow == null){
        	return result;
        }
        List<Facility> facs = new ArrayList<Facility>();
        for (short c = 1; c < firstRow.getLastCellNum(); c++) {      	
            Cell cell = firstRow.getCell(c);
            facs.add(Facility.getOrCreate(cell.getStringCellValue(),c-1));
        }
        // read data rows
        for (short r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if(row == null){
            	continue;
            }
            
            Cell cell0 = row.getCell(0);
            if(cell0 == null){
            	continue;
            }
            String name = cell0.getStringCellValue();
            Person person = new Person(name);
            person.setID(r);

            Map<Facility,Integer> preferences = new HashMap<Facility, Integer>();
            for (short c = 1; c < row.getLastCellNum(); c++) {
                Cell cell = row.getCell(c);
                int preference = 0;
                if (cell != null) {
                    preference = Person.mapPreference((int) cell.getNumericCellValue());
                    preferences.put(facs.get(c-1),preference);
                }
            }
            person.setPereferences(preferences);
            result.add(person);
        }
        return result;
    }

}
