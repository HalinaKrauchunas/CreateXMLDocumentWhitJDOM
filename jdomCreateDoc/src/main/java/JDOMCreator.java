import org.jdom2.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.w3c.dom.*;

import java.text.*;
import java.util.*;

public class JDOMCreator {

    @SuppressWarnings("unused")
    private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public JDOMCreator() {

    }

    public Document createXMLDocument(List<Customer> data) {

        Document document = new Document();
        Element root = new Element("customers");
        document.addContent(root);

        for (Customer customer : data) {
            Element customerElement = new Element("customer");
            root.addContent(customerElement);

            customerElement.setAttribute(Customer.ID, Integer.toString(customer.getId()));

            addChildElement(customerElement, Customer.NAME, customer.getName());
            addChildElement(customerElement, Customer.PHONE, customer.getPhone());
            addChildElement(customerElement, Customer.AGE, Integer.toString(customer.getAge()));

            Element about = addChildElement(customerElement, Customer.ABOUT, "");
            CDATA cdata = new CDATA(customer.getAbout());
            about.addContent(cdata);

            addChildElement(customerElement, Customer.ACTIVE, Boolean.toString(customer.isActive()));
            addChildElement(customerElement, Customer.BALANCE, customer.getBalance());

            DateFormat dateFormat = new SimpleDateFormat(XMLDATEFORMAT);
            addChildElement(customerElement, Customer.JOINED, dateFormat.format(customer.getJoined()));
        }
        return document;
    }

    private Element addChildElement(Element parent, String elementName, String textValue) {

        Element child = new Element(elementName);
        child.setText(textValue);
        parent.addContent(child);
        return child;
    }
}
