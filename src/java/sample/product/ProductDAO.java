/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import sample.utilities.DBUtils;

/**
 *
 * @author MinhNBHSE61805
 */
public class ProductDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("XMLProjectPU");
    EntityManager em = emf.createEntityManager();

    public void persist(Object object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void getLatestProduct() {
        String jpql = "SELECT p FROM Product p";
        Query query = em.createQuery(jpql);
        try {
            productList = new ArrayList<>();
            productList = query.setMaxResults(5).getResultList();
        } catch (Exception e) {

        }
    }

    public void getAllProduct() {
        Query query = em.createNamedQuery("Product.findAll");
        try {
            productList = new ArrayList<>();
            productList = query.getResultList();
        } catch (Exception e) {

        }
    }

    public void getProductByCategory(String categoryId) {
        Query query = em.createNamedQuery("Product.findByCategoryId");
        query.setParameter("categoryId", categoryId);
        try {
            productList = new ArrayList<>();
            productList = query.getResultList();
        } catch (Exception e) {

        }
    }

    public void getHotProduct() {
        Query query = em.createQuery("SELECT p FROM Product p ORDER BY p.soldProduct");
        try {
            productList = new ArrayList<>();
            productList = query.setMaxResults(5).getResultList();
        } catch (Exception e) {

        }
    }
    
    public void getSaleProduct() {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.salePrice > 0 ORDER BY p.salePrice");
        try {
            productList = new ArrayList<>();
            productList = query.setMaxResults(5).getResultList();
        } catch (Exception e) {
            
        }
    }
    
    public String formatPrice(int firstPrice) {
        if (firstPrice < 1000) {
            return String.valueOf(firstPrice);
        }
        NumberFormat formatter = new DecimalFormat("###,###");
        String resp = formatter.format(firstPrice);
        resp = resp.replaceAll(",", ".");
        return resp + " VNĐ";
    }
}
