/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.category.CategoryDAO;
import sample.page.Page;
import sample.product.Product;
import sample.product.ProductDAO;

/**
 *
 * @author MinhNBHSE61805
 */
public class DashboardServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String sCategoryId = request.getParameter("categoryId");

            HttpSession session = request.getSession();
            ProductDAO productDAO = new ProductDAO();

            // Get products
            productDAO.getAllProduct();

            List<Product> productList = productDAO.getProductList();
            session.setAttribute("PRODUCT_LIST", productList);
            
            productDAO.getLatestProduct();
            List<Product> latestProduct = productDAO.getProductList();
            session.setAttribute("LATEST_PRODUCT", latestProduct);
            
            productDAO.getHotProduct();
            List<Product> hotProduct = productDAO.getProductList();
            session.setAttribute("HOT_PRODUCT", hotProduct);
            
            productDAO.getSaleProduct();
            List<Product> saleProduct = productDAO.getProductList();
            session.setAttribute("SALE_PRODUCT", saleProduct);
            
//            session.setAttribute("CONTENT_VIEW", "views/product/ShowProduct.jsp");
            String url = Page.layoutServlet;
            
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
