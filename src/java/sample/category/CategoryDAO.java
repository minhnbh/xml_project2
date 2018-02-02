/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class CategoryDAO {

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

    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void getParentCategory() throws SQLException, NamingException {
        String jpql = "SELECT c FROM Category c WHERE c.parentCategory = 0";
        Query query = em.createQuery(jpql);
        try {
            categoryList = new ArrayList<>();
            categoryList = query.getResultList();
        } catch (Exception e) {

        }
    }

    public void getChildCategory(int parentId) throws NamingException, SQLException {
        String jpql = "SELECT c FROM Category c WHERE c.parentCategory = :parent_category";
        Query query = em.createQuery(jpql);
        query.setParameter("parent_category", parentId);
        try {
            categoryList = new ArrayList<>();
            categoryList = query.getResultList();
        } catch (Exception e) {

        }
    }
}
