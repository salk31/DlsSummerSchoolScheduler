/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;


/**
 * Read in people and
 */
public class PersonReader {
    private Workbook workbook;

    PersonReader(Workbook workbook) {
        this.workbook = workbook;
    }

    Collection<Person> read() {
        return null;
    }

}
