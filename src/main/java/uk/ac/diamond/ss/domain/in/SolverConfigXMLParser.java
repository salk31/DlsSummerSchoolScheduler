/*
 * Diamond User Administration System
 * Copyright © 2016 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain.in;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * XML reader. Used to modify configuration field.
 *
 */
public class SolverConfigXMLParser {



    /**
     * Constructor
     */
    public SolverConfigXMLParser() {

    }

    /**
     * Reader
     * @return
     * @throws XMLStreamException
     * @throws Throwable
     */
    public String getRandomSeedValue(String file_in) throws XMLStreamException, Throwable {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader( new FileInputStream(file_in));
        boolean flag = false;
        while (reader.hasNext()) {
            int Event = reader.next();
            switch (Event) {
            case XMLStreamConstants.START_ELEMENT: {
                if ("randomSeed".equals(reader.getLocalName())) {
                    flag = true;
                }
                break;
            }

            case XMLStreamConstants.CHARACTERS: {
                if (flag) {
                   flag = false;
                   return reader.getText();
                }
                break;
            }

            }
        }
        return null;
    }


    public void setRandomSeedValue(int value,String file_in, String file_out) throws XMLStreamException, Throwable {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader( new FileInputStream(file_in));
        XMLOutputFactory factory1 = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = factory1.createXMLStreamWriter(new FileWriter(file_out));


        boolean flag = false;

        while (reader.hasNext()) {
            int Event = reader.next();

            switch (Event) {
            case XMLStreamConstants.START_ELEMENT: {
                if ("randomSeed".equals(reader.getLocalName())) {
                    flag = true;
                }
                writer.writeStartElement(reader.getLocalName());
                break;
            }

            case XMLStreamConstants.CHARACTERS: {
                if (flag) {
                    writer.writeCharacters(Integer.toString(value));
                    flag = false;
                }
                else{
                    writer.writeCharacters(reader.getText());
                }

                break;
            }
            case XMLStreamConstants.END_ELEMENT: {
                writer.writeEndElement();
            }
            }
        }
        reader.close();
        writer.flush();
        writer.close();
 }



}
