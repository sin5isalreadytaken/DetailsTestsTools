package gdt_meta_server;

import jdk.internal.org.xml.sax.ErrorHandler;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.SAXParseException;
import jdk.nashorn.internal.runtime.Source;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.Console;
import java.io.File;
import java.io.IOException;

/**
 * Created by wenxiangzhou214164 on 2017/7/7.
 */
public class XmlXsd {


    public static void main(String[] args) {
        System.out.println(validateXMLSchema("D:\\workspace\\sohu\\MediaMarketPromotion\\ds.xsd", "D:\\workspace\\sohu\\MediaMarketPromotion\\dg.xml"));
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath){

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
