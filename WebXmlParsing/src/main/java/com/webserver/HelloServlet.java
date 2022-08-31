package com.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;




import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.mustafa.sessions.BeforeRun;
import com.mustafa.trial.bags.BagKey;
import com.mustafa.trial.bags.XBag;



/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
          
        out.print("<html><body>");
        out.print("<h2>Welcome to My new Home Page</h2>");
        out.print("</body></html>");
        
       
		
        
        
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
            BufferedReader b = new BufferedReader(req.getReader());  
            StringBuffer xmlBuffer = new StringBuffer();    
            String xmlString = "";          
            while((xmlString = b.readLine()) != null) {  
                   xmlBuffer.append(xmlString);  
            }  
            xmlString = xmlBuffer.toString();  
            XBag bag = new XBag();
            if (xmlString.length() > 0) {  
              System.out.println("Got XML: " + xmlString);
              
              HashMap<String, String> values = new HashMap<String, String>();
              Document xml = convertStringToDocument(xmlString);
              if (xml.hasChildNodes()) {
                  printNote(xml.getChildNodes(),bag);
              }
              
              resp.getWriter().print(xmlString);
              
              System.out.println(bag.getValue(BagKey.CUSTOMER_NAME));
              System.out.println(bag.getValue(BagKey.CUSTOMER_SURNAME));
              System.out.println(bag.getValue(BagKey.CUSTOMER_EMAIL));
              
              BeforeRun run = new BeforeRun();
              

              
      		  run.execute(operation,bag);

      			
              
            }      
            else {  
                 System.out.println("No XML document received");  
            }  
		   
	}
	
	
	private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(
                    xmlStr)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	static String operation = "";
	
	private static void printNote(NodeList nodeList,XBag bag) {
	      for (int count = 0; count < nodeList.getLength(); count++) {

	          Node tempNode = nodeList.item(count);
	          
	          // make sure it's element node.
	          if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

	              // get node name and value
	        	  // This code taken from https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	              System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
	             
	              System.out.println("Node Value =" + tempNode.getTextContent());
	              if(tempNode.getNodeName().equals("commandName")) {
	            	  operation = tempNode.getTextContent();
	              }

	              if (tempNode.hasAttributes()) {
	            	  
	                  // get attributes names and values
	                  NamedNodeMap nodeMap = tempNode.getAttributes();
	                  for (int i = 0; i < nodeMap.getLength(); i++) {
	                      Node node = nodeMap.item(i);
	                      System.out.println("attr name : " + node.getNodeName());
	                      bag.put(node.getNodeValue(),tempNode.getTextContent());
	                      System.out.println("attr value : " + node.getNodeValue());
	                      
	                  }

	              }
	              
	              
	              if (tempNode.hasChildNodes()) {
	                  // loop again if has child nodes
	            	  
	                  printNote(tempNode.getChildNodes(),bag);
	              }

	              System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

	          }

	      }
	

	}
	
	
}	
