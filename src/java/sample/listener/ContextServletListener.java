/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.listener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import sample.utils.XMLUtilities;

/**
 * Web application lifecycle listener.
 *
 * @author MinhNBHSE61805
 */
public class ContextServletListener implements ServletContextListener {

    public String xmlFile = "WEB-INF/studentAccounts.xml";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Deploying...");
            
            ServletContext context = sce.getServletContext();
            
            String realPath = context.getRealPath("/");
            String xmlFilePath = realPath + xmlFile;
            Document doc = XMLUtilities.parseFileToDOM(xmlFilePath);
            if (doc != null) {
                context.setAttribute("DOMTREE", doc);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
