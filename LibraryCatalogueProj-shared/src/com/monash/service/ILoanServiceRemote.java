/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.service;

import com.monash.entity.Loan;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author bob
 */
@Remote
public interface ILoanServiceRemote {
    // find by user id
    public List<Loan> findByUserId(Integer userId) throws Exception;
    // find all loans
    public List<Loan> findAll() throws Exception;
    // save a loan record
    public String saveLoan(Loan loan) throws Exception;
    // find loans of a particular user
    public List<Loan> findMyLoans(Integer id) throws Exception;
    // find loans according to multi-condition
    public List<Loan> findByLoanIdFirstLastNameEmailPhoneIsbnCallNumberTitle(Integer loanId, String userFirstName, String userLastName, String userEmail, String userPhone, String bookIsbn, String bookCallNumber, String bookTitle);
    // find the first record of a user and duedate is null
    public Loan findByUserFristLoanAndNoDueDate(Integer id);
    
    
}
