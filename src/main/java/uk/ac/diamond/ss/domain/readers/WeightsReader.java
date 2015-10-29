/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.readers;

import org.apache.poi.ss.usermodel.Sheet;

public class WeightsReader {

    private Sheet sheet;
    public static int GROUP_SIZE_SOFT;
    public static int GROUP_SIZE_HARD;
    public static int NO_OVERLAPPING;
    public static int NO_SIMILOAR;
    public static int CORRELATION;
    public static int LONG_EXPERIMANTS;
    public static int PREFERENCES;
    public static int PREFERENCE1;
    public static int PREFERENCE2;
    public static int PREFERENCE3;
    public static int PREFERENCE4;
    public static int PREFERENCE5;

    public WeightsReader(Sheet sheet) {
        this.sheet = sheet;
    }

    public void read() {

        if (sheet.getRow(0).getCell(1) != null) {
            GROUP_SIZE_SOFT = (int) sheet.getRow(0).getCell(1).getNumericCellValue();
        } else {
            GROUP_SIZE_SOFT = 1;
        }
        if (sheet.getRow(1).getCell(1) != null) {
            GROUP_SIZE_HARD = (int) sheet.getRow(1).getCell(1).getNumericCellValue();
        } else {
            GROUP_SIZE_HARD = 1;
        }
        if (sheet.getRow(2).getCell(1) != null) {
            NO_OVERLAPPING = (int) sheet.getRow(2).getCell(1).getNumericCellValue();
        } else {
            NO_OVERLAPPING = 1;
        }
        if (sheet.getRow(3).getCell(1) != null) {
            NO_SIMILOAR = (int) sheet.getRow(3).getCell(1).getNumericCellValue();
        } else {
            NO_SIMILOAR = 1;
        }
        if (sheet.getRow(4).getCell(1) != null) {
            CORRELATION = (int) sheet.getRow(4).getCell(1).getNumericCellValue();
        } else {
            CORRELATION = 1;
        }
        if (sheet.getRow(5).getCell(1) != null) {
            LONG_EXPERIMANTS = (int) sheet.getRow(5).getCell(1).getNumericCellValue();
        } else {
            LONG_EXPERIMANTS = 1;
        }
        if (sheet.getRow(6).getCell(1) != null) {
            PREFERENCES = (int) sheet.getRow(6).getCell(1).getNumericCellValue();
        } else {
            PREFERENCES = 1;
        }
        if (sheet.getRow(7).getCell(1) != null) {
            PREFERENCE1 = (int) sheet.getRow(7).getCell(1).getNumericCellValue();
        } else {
            PREFERENCE1 = 1;
        }
        if (sheet.getRow(8).getCell(1) != null) {
            PREFERENCE2 = (int) sheet.getRow(8).getCell(1).getNumericCellValue();
        } else {
            PREFERENCE2 = 1;
        }
        if (sheet.getRow(9).getCell(1) != null) {
            PREFERENCE3 = (int) sheet.getRow(9).getCell(1).getNumericCellValue();
        } else {
            PREFERENCE3 = 1;
        }
        if (sheet.getRow(10).getCell(1) != null) {
            PREFERENCE4 = (int) sheet.getRow(10).getCell(1).getNumericCellValue();
        } else {
            PREFERENCE4 = 1;
        }
        if (sheet.getRow(11).getCell(1) != null) {
            PREFERENCE5 = (int) sheet.getRow(11).getCell(1).getNumericCellValue();
        } else {
            PREFERENCE5 = 1;
        }
    }

}
