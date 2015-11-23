/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.in;


import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uk.ac.diamond.ss.domain.Facility;
import uk.ac.diamond.ss.domain.Shift;

public class ShiftReader{

    private Sheet sheet;

    public ShiftReader(Sheet sheet) {
        this.sheet = sheet;
    }
    public List<Shift> read() {
        List<Shift> result = new ArrayList<Shift>();
        Row firstRow = sheet.getRow(0);
        Row secondRow = sheet.getRow(1);
        Row thirdRow = sheet.getRow(2);
        int count = 0;
        for (short c = 1; c < firstRow.getLastCellNum(); c++) {
            //facility name
            Cell cellFirst = firstRow.getCell(c);
            Facility faclitity = Facility.getOrCreate(cellFirst.getStringCellValue(),c-1);
            //Period length
            Cell cellSecond = secondRow.getCell(c);
            int type = (int) cellSecond.getNumericCellValue();
            //Starting periods
            Cell cellThird = thirdRow.getCell(c);
            String s = cellThird.getStringCellValue();
            //Create shifts accordingly
            for (String retval: s.split(",")){
                Shift sf = new Shift(faclitity,count);
                sf.setStartTime((Integer.parseInt(retval)-1)*KeyValuesReader.SHIFTS_LENGHT);
                sf.setEndTime((Integer.parseInt(retval))*KeyValuesReader.SHIFTS_LENGHT);
                result.add(sf);
                count++;
                if(type==2){
                    Shift sf1 = new Shift(faclitity,count);
                    sf.setPair(sf1);
                    sf1.setPair(sf);
                    sf1.setStartTime((Integer.parseInt(retval))*KeyValuesReader.SHIFTS_LENGHT);
                    sf1.setEndTime((Integer.parseInt(retval)+1)*KeyValuesReader.SHIFTS_LENGHT);
                    result.add(sf1);
                    count++;
                }
             }
        }
        return result;
    }
}
