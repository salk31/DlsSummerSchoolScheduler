/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.readers;


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

   /* public List<Shift> load() {
        List<Shift> result = new ArrayList<Shift>();
        List<Shift> similar = new ArrayList<Shift>();
        List<Shift> longEx = new ArrayList<Shift>();
        Shift one = new Shift();
        one.setID(10);
        one.setStartTime(0);
        one.setEndTime(5);

        Shift two = new Shift();
        two.setID(20);
        two.setStartTime(6);
        two.setEndTime(10);
        one.addSimilar(two);
        two.addSimilar(one);

        Shift three = new Shift();
        three.setID(3);
        three.setStartTime(11);
        three.setEndTime(15);

        Shift four = new Shift();
        four.setID(4);
        four.setStartTime(16);
        four.setEndTime(18);

        three.addLongExperiment(four);
        four.addLongExperiment(three);

        result.add(one);
        result.add(two);
        result.add(three);
        result.add(four);
        return result;
    }*/



}
