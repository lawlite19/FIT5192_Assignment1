/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.mbeans;

import com.monash.entity.Book;
import com.monash.entity.Loan;
import com.monash.entity.User;
import com.monash.service.IBookServiceRemote;
import com.monash.service.ILoanServiceRemote;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bob
 */
@ManagedBean
@SessionScoped
public class LoanManagedBean implements Serializable{
    @EJB
    private ILoanServiceRemote loanServiceRemote;
    @EJB
    private IBookServiceRemote bookServiceRemote;
    
    private List<Loan> loans;
    private String message;
    private Integer loanId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPhone;
    private String bookIsbn;
    private String bookCallNumber;
    private String bookTitle;
    /**
     * Creates a new instance of LoanManagedBean
     */
    public LoanManagedBean() {
    }
    /**
     * view all loans' information method
     * @return
     * @throws Exception 
     */
    public String list_myLoans() throws Exception{
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User user = (User) session.getAttribute("user");
        loans = loanServiceRemote.findMyLoans(user.getId());
        return "loan_list";
    }
    /** 
     * member borrow a book 
     * @return
     * @throws Exception 
     */
    public String borrowBook() throws Exception{
        // get user object
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User user = (User) session.getAttribute("user");
        // judge if user can borrow
        Loan loanFind = loanServiceRemote.findByUserFristLoanAndNoDueDate(user.getId());
        if (loanFind != null){
            long now = new Date().getTime();
            int days = (int) ((now - loanFind.getStartDate().getTime())/1000/3600/24);
            if(days>28){
                message = "You have a loan that is overdue, you can't borrow a new book!";
                return "failed";
            }
        }
        // search book object
        String book_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("book_id");
        Book bookFind = bookServiceRemote.findById(Integer.parseInt(book_id));

        // update the type of book
        bookFind.setType("Reserve");
        String result_book = bookServiceRemote.updateBook(bookFind);
        if("success".equals(result_book)){
            // save loan
            Loan model = new Loan();
            model.setBookId(bookFind);
            model.setStartDate(new Timestamp(new Date().getTime()));
            model.setMemberId(user);
            String result = loanServiceRemote.saveLoan(model);
            if("success".equals(result)){
                return list_myLoans();
            }
            message = "borrow book failed";
            return "failed";
        }
        else{
            message = "Update the type of book failed";
            return "failed";
        }

    }
    /**
     * view all loans
     * @return
     * @throws Exception 
     */
    public String list_loan() throws Exception{
        loans = loanServiceRemote.findAll();
        return "loan_list";
    }
    public String searchByMultiCondition() throws Exception{
        loans = loanServiceRemote.findByLoanIdFirstLastNameEmailPhoneIsbnCallNumberTitle(loanId,userFirstName,userLastName,userEmail,userPhone,bookIsbn,bookCallNumber,bookTitle);
        return "loan_list";
    }
    public ILoanServiceRemote getLoanServiceRemote() {
        return loanServiceRemote;
    }

    public void setLoanServiceRemote(ILoanServiceRemote loanServiceRemote) {
        this.loanServiceRemote = loanServiceRemote;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public IBookServiceRemote getBookServiceRemote() {
        return bookServiceRemote;
    }

    public void setBookServiceRemote(IBookServiceRemote bookServiceRemote) {
        this.bookServiceRemote = bookServiceRemote;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookCallNumber() {
        return bookCallNumber;
    }

    public void setBookCallNumber(String bookCallNumber) {
        this.bookCallNumber = bookCallNumber;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

   
    
}
