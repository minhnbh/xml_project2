/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import sample.page.Page;
import sample.utils.XMLUtilities;

/**
 *
 * @author MinhNBHSE61805
 */
@WebServlet(name = "DeleteStudentServlet", urlPatterns = {"/DeleteStudentServlet"})
public class DeleteStudentServlet extends HttpServlet {
    
    public String xmlFile = "WEB-INF/studentAccounts.xml";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String urlRewriting = Page.loginPage;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = request.getParameter("id");
            String searchValue = request.getParameter("lastSearchValue");
            
            ServletContext context = this.getServletContext();
            Document doc = (Document) context.getAttribute("DOMTREE");
            String realPath = this.getServletContext().getRealPath("/");
            String xmlFilePath = realPath + xmlFile;

            if (doc == null) {
                doc = XMLUtilities.parseFileToDOM(xmlFilePath);
                context.setAttribute("DOMTREE", doc);
            }
            
            if (doc != null) {
                XPath xpath = XMLUtilities.getXPath();
                String exp = "//student[@id='"
                            + id
                            + "']";
                
                Node student = (Node)xpath.evaluate(exp, doc, XPathConstants.NODE);
                
                if (student != null) {
                    Node parent = student.getParentNode();
                    parent.removeChild(student);
                    XMLUtilities.transformDOMtoFile(doc, xmlFile);
                    urlRewriting = "ProcessServlet"
                                        + "?btAction=Search"
                                        + "?txtSearchValue="
                                        + searchValue;
                }//end if student
            }//end if doc
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DeleteStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DeleteStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DeleteStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DeleteStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
