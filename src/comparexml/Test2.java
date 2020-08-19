
package comparexml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.Difference;
import org.xml.sax.SAXException;

public class Test2 {
    
    public static void main(String args[]) throws FileNotFoundException, SAXException, IOException {
        
        // reading two xml file to compare in Java program
        FileInputStream fis1 = new FileInputStream("1.xml");
        FileInputStream fis2 = new FileInputStream("2.xml");

        // using BufferedReader for improved performance
        BufferedReader  source = new BufferedReader(new InputStreamReader(fis1));
        BufferedReader  target = new BufferedReader(new InputStreamReader(fis2));

        //configuring XMLUnit to ignore white spaces
        XMLUnit.setIgnoreWhitespace(true);

        
        
        try{
            //comparing two XML using XMLUnit in Java
            //List differences = compareXML(source, target);
            List differences = compareXML(source, target);
            //create a txt file that name is output
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

            //showing differences found in two xml files
            //printDifferences(differences);
            StringBuilder diffLines = new StringBuilder();
            int totalDifferences = differences.size(); //total differences count(all: tag and text)
            out.write("Total differences : " + totalDifferences + "\r\r");
            
            for(Object object : differences){
                int i;
                Difference difference = (Difference) object;
                diffLines.append(difference.toString() + '\n');
                
            }out.write(diffLines.toString() + "\r"); //write differences to output.txt

            //close the output.txt
            out.close();
            
        }catch(IOException e){

            System.out.println("there is an error");
            
        }
        
    }
        
        public static List compareXML(Reader source, Reader target) throws SAXException, IOException{

            //creating Diff instance to compare two XML files
            Diff xmlDiff = new Diff(source, target);

            //for getting detailed differences between two xml files
            DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);

            return detailXmlDiff.getAllDifferences();
        }

}

    

