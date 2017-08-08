/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.service;

import com.monash.entity.Book;
import com.monash.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author bob
 */
@Stateless
public class UserServiceBeanImpl implements IUserServiceRemote{
    @PersistenceContext
    private EntityManager em;
    @Override
    public User findByEmailPasswordAndType(String email, String password, String userType) throws Exception {
        Query query = em.createQuery("SELECT u from User u where u.email = :email and u.password = :password and u.type = :userType", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        query.setParameter("userType", userType);
        List<User> users = query.getResultList();
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public String addUser(User user) throws Exception {
        try {
            em.persist(user);
            return "success";
        } catch (Exception e) {
            return "failed";
        }
   }

    @Override
    public List<User> findByType(String type) throws Exception {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(User.class);
        Root<User> u = query.from(User.class);
        query.select(u).where(builder.equal(u.get("type"), type));
        return em.createQuery(query).getResultList();
    }

    @Override
    public User findById(Integer id) throws Exception {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(User.class);
        Root<User> u = query.from(User.class);
        query.select(u).where(builder.equal(u.get("id").as(Integer.class), id));
        return (User) em.createQuery(query).getSingleResult();
    }

    @Override
    public List<User> findByIdLastFirstNameAndEmail(Integer id, String firstName, String lastName, String email) throws Exception {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(User.class);
        Root<User> b = query.from(User.class);
        List<Predicate> predicatesList = new ArrayList<Predicate>();
        
        // judge the condition
        if((id != null) && !(id.equals(""))){
            predicatesList.add(builder.equal(b.get("id").as(Integer.class), id));
        } 
        if(firstName != null && !firstName.trim().equals("")){
            predicatesList.add(builder.equal(b.get("firstName"), firstName.trim()));
        }
        if(lastName != null && !lastName.trim().equals("")){
            predicatesList.add(builder.equal(b.get("lastName"), lastName.trim()));
        }
        if(email != null && !email.trim().equals("")){
            predicatesList.add(builder.equal(b.get("email"), email.trim()));
        }    
        query.select(b).where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        return em.createQuery(query).getResultList(); 
    }

    @Override
    public String updateUser(User member) {
        try {
            em.merge(member);
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }

    @Override
    public String deleteById(Integer id) {
        try {
            em.remove(em.find(User.class, id));
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }
    
}
