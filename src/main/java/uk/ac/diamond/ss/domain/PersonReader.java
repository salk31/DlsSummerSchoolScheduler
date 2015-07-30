/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


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
        List<Facility> facs = new ArrayList<Facility>();

        for (short c = 1; c < firstRow.getLastCellNum(); c++) {
            Cell cell = firstRow.getCell(c);
            facs.add(Facility.getOrCreate(cell.getStringCellValue()));
        }
        // read data rows
        for (short r = 1; r <=sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            String name = row.getCell(0).getStringCellValue();
            Person person = new Person(name);
            result.add(person);

            for (short c = 1; c <= row.getLastCellNum(); c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    // TODO __ add preference
                }
            }
        }

        return result;
    }

}
