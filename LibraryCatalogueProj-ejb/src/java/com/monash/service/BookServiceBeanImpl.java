/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.service;

import com.monash.entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author bob
 */
@Stateless
public class BookServiceBeanImpl implements IBookServiceRemote{
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Book> findAll() throws Exception {
        return em.createNamedQuery(Book.FIND_ALL).getResultList();
    }

    @Override
    public Book findById(Integer id) throws Exception {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Book.class);
        Root<Book> b = query.from(Book.class);
        query.select(b).where(builder.equal(b.get("id").as(Integer.class), id));
        return (Book) em.createQuery(query).getSingleResult();
    }

    @Override
    public List<Book> findByTitleAuthorCallNumberAndType(String title, String author, String callNumber, String isbn, String type) throws Exception {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Book.class);
        Root<Book> b = query.from(Book.class);
        List<Predicate> predicatesList = new ArrayList<Predicate>();
        if((title != null) && !(title.trim().equals(""))){
            predicatesList.add(builder.equal(b.get("title"), title.trim()));
        } 
        if(author != null && !author.trim().equals("")){
            predicatesList.add(builder.equal(b.get("author"), author.trim()));
        }    
        if(callNumber != null && !callNumber.trim().equals("")){
            predicatesList.add(builder.equal(b.get("callNumber"), callNumber.trim()));
        }
        if(isbn != null && !isbn.trim().equals("")){
            predicatesList.add(builder.equal(b.get("isbn"), isbn.trim()));
        }
        if(type != null && !type.trim().equals("")){
            predicatesList.add(builder.equal(b.get("type"), type.trim()));
        }       
        query.select(b).where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        return em.createQuery(query).getResultList();
    }

    @Override
    public String saveBook(Book book) throws Exception {
        try {
            em.persist(book);
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }

    @Override
    public String deleteById(Integer id) {
        try {
            em.remove(em.find(Book.class, id));
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }

    @Override
    public String updateBook(Book book) {
        try {
            em.merge(book);
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }

    @Override
    public boolean findByCallNumber(String callNumber) throws Exception {
        Query query = em.createQuery("select b from Book b where b.callNumber = :callNumber", Book.class);
        query.setParameter("callNumber", callNumber);
        List<Book> books = query.getResultList();
        if(books.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Book> findByLike(String title, String author, String callNumber, String isbn, String type) throws Exception {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Book.class);
        Root<Book> b = query.from(Book.class);
        List<Predicate> predicatesList = new ArrayList<Predicate>();
        if((title != null) && !(title.trim().equals(""))){
            predicatesList.add(builder.like(b.<String>get("title"), "%"+title.trim()+"%"));
        } 
        if(author != null && !author.trim().equals("")){
            predicatesList.add(builder.equal(b.<String>get("author"), "%"+author.trim()+"%"));
        }    
        if(callNumber != null && !callNumber.trim().equals("")){
            predicatesList.add(builder.equal(b.<String>get("callNumber"), "%"+callNumber.trim()+"%"));
        }
        if(isbn != null && !isbn.trim().equals("")){
            predicatesList.add(builder.equal(b.<String>get("isbn"), "%"+isbn.trim()+"%"));
        }
        if(type != null && !type.trim().equals("")){
            predicatesList.add(builder.equal(b.<String>get("type"), "%"+type.trim()+"%"));
        }       
        query.select(b).where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        return em.createQuery(query).getResultList();
    }
}
