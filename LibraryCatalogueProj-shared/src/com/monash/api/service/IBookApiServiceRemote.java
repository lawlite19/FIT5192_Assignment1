/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.api.service;



import com.monash.entity.Book;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author bob
 */
@Remote
public interface IBookApiServiceRemote {
    // find books according to api service
    public List<Book> findBooks(String searchCondition) throws Exception;
    // add book according to bookId
    public String saveBookById(String book_id) throws Exception;
}
