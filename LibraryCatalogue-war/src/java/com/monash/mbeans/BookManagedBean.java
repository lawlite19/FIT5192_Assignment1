/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.mbeans;

import com.monash.api.service.IBookApiServiceRemote;
import com.monash.entity.Book;
import com.monash.service.IBookServiceRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bob
 */
@ManagedBean
@SessionScoped
public class BookManagedBean {

    @EJB
    private IBookServiceRemote bookServiceRemote;
    
    @EJB
    private IBookApiServiceRemote bookApiServiceRemote;
    
    private List<Book> books;
    private List<Book> books_find;  // use api to search books
    private Book book;
    private String title;
    private String author;
    private String callNumber;
    private String isbn;
    private String type;
    private String message;
    private int id;
    private String searchCondition;
    


    /**
     * Creates a new instance of BookManagedBean
     */
    public BookManagedBean() {
        book = new Book();
    }
    /**
     * list all books
     * @return
     * @throws Exception 
     */
    public String list() throws Exception{
        books = bookServiceRemote.findAll();
        return "book_list";
    }
    /**
     * jump to add book UI
     * @return
     * @throws Exception 
     */
    public String addUI() throws Exception{
        return "book_addUI";
    }
    /**
     * jump to detail UI
     * @return
     * @throws Exception 
     */
    public String detailUI() throws Exception{
//        Integer id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
//        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        Map varMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        Integer id = (Integer) request.getAttribute("id"); 
        // get the parameter
        String book_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("book_id");
        book = bookServiceRemote.findById(Integer.parseInt(book_id));
        return "book_detailUI";
    }
    /**
     * search by title,author,callNumber and type
     * @return 
     * @throws java.lang.Exception 
     */
    public String searchByMultiCondition() throws Exception{
        books = bookServiceRemote.findByTitleAuthorCallNumberAndType(title,author,callNumber,isbn,type);
        return "book_list";
    }
    /**
     * save a book method
     * @return
     * @throws Exception 
     */
    public String add() throws Exception{
        String result = bookServiceRemote.saveBook(book);
        if("success".equals(result)){
            return list();
        }
        message = "save exception!";
        return "failed";
    }
    /**
     * delete a book method
     * @return
     * @throws Exception 
     */
    public String deleteBook() throws Exception{
        String book_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("book_id");
        String result = bookServiceRemote.deleteById(Integer.parseInt(book_id));
        if("success".equals(result)){
            return list();
        }
        
        message = "delete exception!";
        return "failed";
    }
    /**
     * jump to edit UI
     * @return
     * @throws Exception 
     */
    public String editUI() throws Exception{
        // get the original object
        String book_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("book_id");
        book = bookServiceRemote.findById(Integer.parseInt(book_id));
        id = book.getId();
        return "book_editUI";
    }
    /**
     * update a book method
     * @return
     * @throws Exception 
     */
    public String edit() throws Exception{
        book.setId(id);
        String result = bookServiceRemote.updateBook(book);
        if("success".equals(result)){
            return list();
        }
        message = "update failed!";
        return "failed";
    }
    /**
     * search books according to API
     * @return
     * @throws Exception 
     */
    public String apiSearch() throws Exception{
        try {
                books_find = bookApiServiceRemote.findBooks(searchCondition);
            } catch (Exception ex) {
                Logger.getLogger(BookManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                message = "search failed according to book API";
        }
        
        return "book_addUI";
    }
    /**
     * save a book according to API
     * @param book
     * @return
     * @throws Exception 
     */
    public String aipAddBook(Book book) throws Exception{
//        String book_model = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("book_model");
        // judge if the book exists
        boolean exist = bookServiceRemote.findByCallNumber(book.getCallNumber());
        if (exist){
            message = "Already add this book!";
            return "failed";
        }
        String result = bookServiceRemote.saveBook(book);
        if("success".equals(result)){
            return list();
        }
        message = "save exception!";
        return "failed";
    }
    
    public IBookServiceRemote getBookServiceRemote() {
        return bookServiceRemote;
    }

    public void setBookServiceRemote(IBookServiceRemote bookServiceRemote) {
        this.bookServiceRemote = bookServiceRemote;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
        public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public IBookApiServiceRemote getBookApiServiceRemote() {
        return bookApiServiceRemote;
    }

    public void setBookApiServiceRemote(IBookApiServiceRemote bookApiServiceRemote) {
        this.bookApiServiceRemote = bookApiServiceRemote;
    }

    public List<Book> getBooks_find() {
        return books_find;
    }

    public void setBooks_find(List<Book> books_find) {
        this.books_find = books_find;
    }
    
}
