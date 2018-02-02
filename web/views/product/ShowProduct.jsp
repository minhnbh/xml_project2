<%-- 
    Document   : ShowProduct
    Created on : Jan 18, 2018, 7:43:54 AM
    Author     : MinhNBHSE61805
--%>

<%@page import="sample.product.ProductDAO"%>
<%@page import="sample.product.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="assets/product/ProductStyle.css">
<%
    List<Product> latestProduct = (List<Product>) session.getAttribute("LATEST_PRODUCT");
    List<Product> hotProduct = (List<Product>) session.getAttribute("HOT_PRODUCT");
    List<Product> saleProduct = (List<Product>) session.getAttribute("SALE_PRODUCT");
    ProductDAO productDAO = new ProductDAO();
%>

<% if (latestProduct != null && latestProduct.size() > 0) { %>
<section>
    <div class="row product_row_heading">
        <div style="width: 100%">
            <h3>Sản phẩm mới</h3>
        </div>
    </div>
    <div class="row product_row">
        <%
            int count = 0;
            if (latestProduct != null && latestProduct.size() > 0) {
                for (Product product : latestProduct) {
        %>
        <div class="product">
            <div class="product_image"><img src="assets/images/pg27vq.png" /></div>
            <h3 class="product_title"><%= product.getProductName()%></h3>
            <h4 class="product_price"><%= productDAO.formatPrice(Integer.parseInt(product.getPrice())) %></h4>
        </div>
        <%
            count++;
            if (count % 5 == 0) {
        %>
    </div>
    <div class="row product_row">
        <%
                    }
                }
                while (count % 5 != 0) {
                    out.println("<div class='product'></div>");
                    count++;
                }
            }
        %>
    </div>
</section>
<%
    }
    if (hotProduct != null && hotProduct.size() > 0) {
%>
<section>
    <div class="row product_row_heading">
        <div style="width: 100%">
            <h3>Sản phẩm bán chạy</h3>
        </div>
    </div>
    <div class="row product_row">
        <%
            int count = 0;
            if (latestProduct != null && latestProduct.size() > 0) {
                for (Product product : hotProduct) {
        %>
        <div class="product">
            <div class="product_image"><img src="assets/images/pg27vq.png" /></div>
            <h3 class="product_title"><%= product.getProductName() %></h3>
            <h4 class="product_price"><%= productDAO.formatPrice(Integer.parseInt(product.getPrice())) %></h4>
        </div>
        <%
            count++;
            if (count % 5 == 0) {
        %>
    </div>
    <div class="row product_row">
        <%
                    }
                }
                while (count % 5 != 0) {
                    out.println("<div class='product'></div>");
                    count++;
                }
            }
        %>
    </div>
</section>
<%
    }
    if (saleProduct != null && saleProduct.size() > 0) {
%>
<section>
    <div class="row product_row_heading">
        <div style="width: 100%">
            <h3>Sản phẩm khuyến mãi</h3>
        </div>
    </div>
    <div class="row product_row">
        <%
            int count = 0;
            if (latestProduct != null && latestProduct.size() > 0) {
                for (Product product : saleProduct) {
                    
        %>
        <div class="product">
            <div class="product_image"><img src="assets/images/pg27vq.png" /></div>
            <h3 class="product_title"><%= product.getProductName() %></h3>
            <h4 class="product_price"><%= productDAO.formatPrice(Integer.parseInt(product.getPrice())) %></h4>
        </div>
        <%
            count++;
            if (count % 5 == 0) {
        %>
    </div>
    <div class="row product_row">
        <%
                    }
                }
                while (count % 5 != 0) {
                    out.println("<div class='product'></div>");
                    count++;
                }
            }
        %>
    </div>
</section>
<%
    }
%>