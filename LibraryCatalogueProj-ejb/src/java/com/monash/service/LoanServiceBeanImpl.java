/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.service;

import com.monash.entity.Book;
import com.monash.entity.Loan;
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
public class LoanServiceBeanImpl implements ILoanServiceRemote{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Loan> findByUserId(Integer userId) throws Exception {
        Query query = em.createQuery("select l from Loan l where l.memberId.id = :userId ORDER BY l.startDate DESC", Loan.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Loan> findAll() throws Exception {
        return em.createNamedQuery(Loan.FIND_ALL, Loan.class).getResultList();
    }

    @Override
    public String saveLoan(Loan loan) throws Exception {
        try {
            em.persist(loan);
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }

    @Override
    public List<Loan> findMyLoans(Integer id) throws Exception {
        Query query = em.createQuery("select l from Loan l where l.memberId.id = :userId ORDER BY l.startDate DESC", Loan.class);
        query.setParameter("userId", id);
        return query.getResultList();
    }

    @Override
    public List<Loan> findByLoanIdFirstLastNameEmailPhoneIsbnCallNumberTitle(Integer loanId, String userFirstName, String userLastName, String userEmail, String userPhone, String bookIsbn, String bookCallNumber, String bookTitle) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Loan.class);
        Root<Loan> b = query.from(Loan.class);
        List<Predicate> predicatesList = new ArrayList<Predicate>();
        if((loanId != null) && !(loanId.equals(""))){
            predicatesList.add(builder.equal(b.get("id"), loanId));
        } 
        if(userFirstName != null && !userFirstName.trim().equals("")){
            predicatesList.add(builder.equal(b.get("memberId").get("firstName"), userFirstName.trim()));
        }
        if(userLastName != null && !userLastName.trim().equals("")){
            predicatesList.add(builder.equal(b.get("memberId").get("lastName"), userLastName.trim()));
        }
        if(userEmail != null && !userEmail.trim().equals("")){
            predicatesList.add(builder.equal(b.get("memberId").get("email"), userEmail.trim()));
        }
        if(userPhone != null && !userPhone.trim().equals("")){
            predicatesList.add(builder.equal(b.get("memberId").get("phoneNumber"), userPhone.trim()));
        }
        if(bookIsbn != null && !bookIsbn.trim().equals("")){
            predicatesList.add(builder.equal(b.get("bookId").get("isbn"), bookIsbn.trim()));
        }
        if(bookCallNumber != null && !bookCallNumber.trim().equals("")){
            predicatesList.add(builder.equal(b.get("bookId").get("callNumber"), bookCallNumber.trim()));
        }
        if(bookTitle != null && !bookTitle.trim().equals("")){
            predicatesList.add(builder.equal(b.get("bookId").get("title"), bookTitle.trim()));
        }
        query.select(b).where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        return em.createQuery(query).getResultList();
    }

    @Override
    public Loan findByUserFristLoanAndNoDueDate(Integer id) {
        Query query = em.createQuery("SELECT l from Loan l where l.memberId.id = :userId and l.dueDate IS NULL", Loan.class);
        query.setParameter("userId", id);
        List<Loan> loans = query.getResultList();
        if (loans.size()>0){
            return loans.get(0);
        }
        return null;
    }
}
