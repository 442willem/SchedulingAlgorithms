import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import java.io.File;
import java.util.PriorityQueue;  

public class XMLParser {
	public PriorityQueue<Process> processen;

	public XMLParser() {
		processen=new PriorityQueue<Process>();
	}

	public void leesProcessen(int aantalProcessen) {
		try   {  
			File file;
			if (aantalProcessen==10000)file = new File("processen10000.xml");  
			else if (aantalProcessen==20000)file = new File("processen20000.xml");
			else file = new File("processen50000.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(file);  
			doc.getDocumentElement().normalize();   
			NodeList nodeList = doc.getElementsByTagName("process");  
			for (int itr = 0; itr < nodeList.getLength(); itr++) {  
				Node node = nodeList.item(itr);  
				System.out.println("\nNode Name :" + node.getNodeName());  
				if (node.getNodeType() == Node.ELEMENT_NODE)   {  
					Element eElement = (Element) node;  
					int id=Integer.parseInt(eElement.getElementsByTagName("pid").item(0).getTextContent());	
					int at=Integer.parseInt(eElement.getElementsByTagName("arrivaltime").item(0).getTextContent());
					int st=Integer.parseInt(eElement.getElementsByTagName("servicetime").item(0).getTextContent());
					processen.add(new Process(id,at,st));
				}  
			}  
		}   
		catch (Exception e)   {  
			e.printStackTrace();  
		}   
	}
}  