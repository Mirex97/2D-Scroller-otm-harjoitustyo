package VR.util;

import VR.Main;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tiled.io.xml.XMLWriter;

public class XmlWriterUtil {

    private boolean windows;
    private XMLWriter xmlWriter;
    private Writer writer;
    private boolean allFixed;

    public XmlWriterUtil() {
        allFixed = false;
        windows = true;
        String osName = System.getProperties().getProperty("os.name");
        if (osName.contains("Linux")) {
            windows = false;
        }

    }
    
    public void setFixed() {
        allFixed = true;
    }
    
    public boolean getFixed() {
        return allFixed;
    }
    
    public boolean getSystem() {
        return windows;
    }

    //Cant use libtiled Xmlwriter! Doesn't support modifying existing xml file! (Creates new!).
    public void windowsFix(String path) {
        try {
            File file = new File(path);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            Node map = doc.getFirstChild();
            Node tileset = doc.getElementsByTagName("image").item(0);
            NamedNodeMap attr = tileset.getAttributes();
            Node nodeAttr = attr.getNamedItem("source");
            nodeAttr.setTextContent("./levels/" + nodeAttr.getNodeValue());

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println("ERROR WHILE MODIFYING XML");
            Main.login.error();
        }
    }
    
    
    
    public void fixTileset(String path) {
        if (windows) {
            windowsFix(path);
        }
    }

}
