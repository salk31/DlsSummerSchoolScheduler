/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.readers;

import org.apache.poi.ss.usermodel.Sheet;

public class KeyValuesReader {

    private Sheet sheet;
    public static int SHIFTS_PER_STUDENT;//default 4
    public static int STUDENTS_PER_SHIFT; //default 5
    public static int MAX_PREFERENCES; // default 5

    public static  int SHIFTS_LENGHT = 6;//Length of a shift in hours
    public static  int SHIFTS_OFFSET = 12;//no experiments at night

    public KeyValuesReader(Sheet sheet) {
        this.sheet = sheet;
    }

    public void read(){
        if(sheet.getRow(0).getCell(1)!=null){
            SHIFTS_PER_STUDENT = (int) sheet.getRow(0).getCell(1).getNumericCellValue();
        } else{
            SHIFTS_PER_STUDENT = 4;
        }
        if(sheet.getRow(1).getCell(1)!=null){
            STUDENTS_PER_SHIFT = (int) sheet.getRow(1).getCell(1).getNumericCellValue();
        }
        else{
            STUDENTS_PER_SHIFT = 5;
        }
        if(sheet.getRow(2).getCell(1) !=null){
            MAX_PREFERENCES = (int) sheet.getRow(2).getCell(1).getNumericCellValue();
        }
        else{
            MAX_PREFERENCES = 5;
        }
    }

}
