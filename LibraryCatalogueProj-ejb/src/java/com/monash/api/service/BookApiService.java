/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.api.service;

import com.google.gson.Gson;
import com.monash.api.entity.Books;
import com.monash.entity.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bob
 */
@Stateless
public class BookApiService implements IBookApiServiceRemote{
    @PersistenceContext
    private EntityManager em;
    private static final String Base_Url = "https://api.douban.com/v2/book/search?fields=id,title,author,isbn10,image,publisher,summary,url,id&q=";
    private static final String Base_Url_Id = "https://api.douban.com/v2/book/";
    @Override
    public List<Book> findBooks(String searchCondition) throws Exception {
        final String methodPath = Base_Url+searchCondition;
        URL url = null;
        HttpURLConnection conn = null;
        String result = "";
        try{
            url = new URL(methodPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setRequestMethod("GET");

            InputStream inStream = null;
            int code = conn.getResponseCode();
            System.err.println(code);
            if (code==200){
                if (conn != null) {
                    inStream = conn.getInputStream();
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));  
                StringBuffer buffer = new StringBuffer();  
                String line = "";  
                while ((line = in.readLine()) != null){  
                  buffer.append(line);  
                }
                result = buffer.toString();  
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }
        if(!"".equals(result)){
            Gson gson = new Gson();
            Books booksInfo = gson.fromJson(result, Books.class);
            List<Books.BooksBean> bookbBeans =  booksInfo.getBooks();
            List<Book> books = new ArrayList<>();
            for(Books.BooksBean bean: bookbBeans){
                books.add(new Book(bean.getIsbn10(),bean.getIsbn10()+"-"+bean.getId(),bean.getTitle(),bean.getAuthor().toString(),bean.getPublisher(),"General",bean.getImage(),bean.getSummary(),bean.getUrl()));
            }
            return books;
        }
        return null;
    }

    @Override
    public String saveBookById(String book_id) throws Exception {
        final String methodPath = Base_Url_Id+book_id;
        URL url = null;
        HttpURLConnection conn = null;
        String result = "";
        try{
            url = new URL(methodPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setRequestMethod("GET");

            InputStream inStream = null;
            int code = conn.getResponseCode();
            System.err.println(code);
            if (code==200){
                if (conn != null) {
                    inStream = conn.getInputStream();
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));  
                StringBuffer buffer = new StringBuffer();  
                String line = "";  
                while ((line = in.readLine()) != null){  
                  buffer.append(line);  
                }
                result = buffer.toString();  
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }
        if(!"".equals(result)){
            Gson gson = new Gson();
            Books booksInfo = gson.fromJson(result, Books.class);
            List<Books.BooksBean> bookbBeans =  booksInfo.getBooks();
            if (bookbBeans.size()>0){
                Books.BooksBean bean = bookbBeans.get(0);
                Book book_new = new Book(bean.getIsbn10(),bean.getIsbn10()+"-"+bean.getId(),bean.getTitle(),bean.getAuthor().toString(),bean.getPublisher(),"General",bean.getImage(),bean.getSummary(),bean.getUrl());
                try {
                    em.persist(book_new);
                    return "success";
                } catch (Exception e) {
                    return "failed";
                }
                
            } 
        }
        return "failed";
    }
    
}
