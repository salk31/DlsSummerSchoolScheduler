/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.in;

import org.apache.poi.ss.usermodel.Sheet;

public class KeyValuesReader {

    private Sheet sheet;
    public static int SHIFTS_PER_STUDENT = 4;// default 4
    public static int STUDENTS_PER_SHIFT = 5; // default 5
    public static int MAX_PREFERENCES = 5; // default 5

    public static int SHIFTS_LENGHT = 6;// Length of a shift in hours
    
    public static long TERMINATION_TOTAL_TIME = 3;//minutes
    public static long TERMINATION_TIME_UNIMPROVED = 1;

    public KeyValuesReader(Sheet sheet) {
        this.sheet = sheet;
    }

    public void read() {
        if (sheet.getRow(0) != null && sheet.getRow(0).getCell(1) != null) {
            SHIFTS_PER_STUDENT = (int) sheet.getRow(0).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(1) != null && sheet.getRow(1).getCell(1) != null) {
            STUDENTS_PER_SHIFT = (int) sheet.getRow(1).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(2) != null && sheet.getRow(2).getCell(1) != null) {
            MAX_PREFERENCES = (int) sheet.getRow(2).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(3) != null && sheet.getRow(3).getCell(1) != null) {
        	TERMINATION_TOTAL_TIME  = (long) sheet.getRow(3).getCell(1).getNumericCellValue();
        }
        if (sheet.getRow(4) != null && sheet.getRow(4).getCell(1) != null) {
        	TERMINATION_TIME_UNIMPROVED = (long) sheet.getRow(4).getCell(1).getNumericCellValue();
        }
        
    }

}
