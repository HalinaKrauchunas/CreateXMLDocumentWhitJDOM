import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.output.Format;

import java.io.*;
import java.text.*;
import java.util.List;

public class CreateXMLWithJDOM {

    public static void main(String[] args) throws ParseException, IOException {

        List<Customer> data = DataProvider.getData(DataProvider.SMALL);

        JDOMCreator jdomCreator = new JDOMCreator();
        Document document = jdomCreator.createXMLDocument(data);
        FileWriter fileWriter = new FileWriter(new File("./output/customers.xml"));

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fileWriter);

        String xmlString = xmlOutputter.outputString(document);
        System.out.println(xmlString);
    }
}
