import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;  

public class XMLParser {
	public PriorityQueue<Process> processen;
	public PriorityQueue<Process> outputProcessen;

	public PriorityQueue<Process> getProcessen() {
		return processen;
	}

	public void setProcessen(PriorityQueue<Process> processen) {
		this.processen = processen;
	}
	public PriorityQueue<Process> getOutputProcessen() {
		return outputProcessen;
	}

	public void setOutputProcessen(PriorityQueue<Process> outputProcessen) {
		this.outputProcessen = outputProcessen;
	}

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
	public void schrijfProcessen(String bestandsnaam) {

		String xmlFilePath = bestandsnaam;
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("processlist");
			document.appendChild(root);

			for(Process p: outputProcessen) {
				// process element
				Element process = document.createElement("process");
				root.appendChild(process);
				// id element
				Element pid = document.createElement("pid");
				pid.appendChild(document.createTextNode(String.valueOf(p.getId())));
				process.appendChild(pid);
				// bedieningstijd element
				Element serviceTime = document.createElement("serviceTime");
				serviceTime.appendChild(document.createTextNode(String.valueOf(p.getServiceTime())));
				process.appendChild(serviceTime);
				// wachttijd element
				Element waitTime = document.createElement("waitTime");
				String str=String.valueOf(p.getWachtTijd());
				String str2 = str.replaceAll("\\.",",");
				waitTime.appendChild(document.createTextNode(str2));
				process.appendChild(waitTime);
				// genormaliseerde omlooptijd element
				Element normTAT = document.createElement("normTAT");
				str=String.valueOf(p.getNormOmloopTijd());
				str2 = str.replaceAll("\\.",",");
				normTAT.appendChild(document.createTextNode(str2));
				process.appendChild(normTAT);
			}
			// create the xml file
			//transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			
			Path fileToDeletePath = Paths.get(xmlFilePath);
			Files.deleteIfExists(fileToDeletePath);
			File output=new File(xmlFilePath);
			
			StreamResult streamResult = new StreamResult(output);

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging 

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File"+xmlFilePath);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}  