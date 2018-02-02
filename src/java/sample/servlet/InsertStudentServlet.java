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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import sample.page.Page;
import sample.utils.XMLUtilities;

/**
 *
 * @author MinhNBHSE61805
 */
public class InsertStudentServlet extends HttpServlet {

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
        String urlRewriting = Page.searchPage;
        try (PrintWriter out = response.getWriter()) {
            ServletContext context = this.getServletContext();
            Document doc = (Document) context.getAttribute("DOMTREE");
            String realPath = this.getServletContext().getRealPath("/");
            String xmlFilePath = realPath + xmlFile;

            if (doc == null) {
                doc = XMLUtilities.parseFileToDOM(xmlFilePath);
                context.setAttribute("DOMTREE", doc);
            }
            
            String lastname = request.getParameter("lastname");
            String middlename = request.getParameter("middlename");
            String firstname = request.getParameter("firstname");
            String password = request.getParameter("password");
            String sex = request.getParameter("sex");
            String address = request.getParameter("address");
            String status = request.getParameter("status");
            
            if (doc != null) {
                Element root = doc.getDocumentElement();
                Element newStudent = doc.createElement("student");
                Element newLastName = doc.createElement("lastname");
                Text sNewLastName = doc.createTextNode(lastname);
                Element newMiddleName = doc.createElement("middlename");
                Text sNewMiddleName = doc.createTextNode(middlename);
                Element newFirstName = doc.createElement("firstname");
                Text sNewFirstName = doc.createTextNode(firstname);
                Element newSex = doc.createElement("sex");
                Text sNewSex = doc.createTextNode(sex);
                Element newPassword = doc.createElement("password");
                Text sNewPassword = doc.createTextNode(password);
                Element newAddress = doc.createElement("address");
                Text sNewAddress = doc.createTextNode(address);
                Element newStatus = doc.createElement("status");
                Text sNewStatus = doc.createTextNode(status);
                
                newStudent.appendChild(newLastName);
                newLastName.appendChild(sNewLastName);
                
                newStudent.appendChild(newMiddleName);
                newMiddleName.appendChild(sNewMiddleName);
                
                newStudent.appendChild(newFirstName);
                newLastName.appendChild(sNewFirstName);
                
                newStudent.appendChild(newSex);
                newSex.appendChild(sNewSex);
                
                newStudent.appendChild(newPassword);
                newPassword.appendChild(sNewPassword);
                
                newStudent.appendChild(newAddress);
                newAddress.appendChild(sNewAddress);
                
                newStudent.appendChild(newStatus);
                newStatus.appendChild(sNewStatus);
                
                root.appendChild(newStudent);
                
                XMLUtilities.transformDOMtoFile(doc, xmlFile);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(InsertStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(InsertStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(InsertStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
