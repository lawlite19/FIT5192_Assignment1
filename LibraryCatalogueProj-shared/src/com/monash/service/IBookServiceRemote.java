/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.service;

import com.monash.entity.Book;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author bob
 */
@Remote
public interface IBookServiceRemote {
    //search all books
    public List<Book> findAll() throws Exception;
    //search by id
    public Book findById(Integer id) throws Exception;
    //search by title,author,callNumber,isbn and type 
    public List<Book> findByTitleAuthorCallNumberAndType(String title,String author,String callNumber,String isbn,String type) throws Exception;
    // like
    public List<Book> findByLike(String title,String author,String callNumber,String isbn,String type) throws Exception;
    //save a book
    public String saveBook(Book book) throws Exception;
    // delete by id
    public String deleteById(Integer id);
    // update book
    public String updateBook(Book book);
    // find by callNumber
    public boolean findByCallNumber(String callNumber) throws Exception;
}
