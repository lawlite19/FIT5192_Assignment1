/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash;

import com.monash.entity.Book;
import com.monash.gui.LibraryCatalogueGUI;
import com.monash.gui.TableGUIImpl;
import com.monash.service.IBookServiceRemote;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author bob
 */
public class LibraryCatalogue implements ActionListener, ListSelectionListener {
    
    @EJB
    private static IBookServiceRemote bookServiceRemote;     // interface 

    private String name;
    private LibraryCatalogueGUI gui;

    public LibraryCatalogue(String name) throws Exception {
        this.name = name;
    }

    /**
     * display UI and all information
     */
    public void initView() {
        this.gui = new TableGUIImpl(this, this);
        this.displayAllBooks();
    }

    /**
     * button click listener
     * @param event 
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == gui.getViewButton()) {
            this.displayAllBooks();
        } else if (event.getSource() == gui.getAddButton()) {
            this.addBook();
            this.displayAllBooks();
        } else if (event.getSource() == gui.getSearchButton()) {
            this.searchBook();
        } else if (event.getSource() == gui.getUpdateButton()) {
            this.updateBook();
        } else if (event.getSource() == gui.getDeleteButton()) {
            this.deleteBook();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if ((event.getSource() == this.gui.getBookTable().getSelectionModel()) && (! event.getValueIsAdjusting()))
        {
            try
            {
                if (this.gui.isBookSelected()) {
                    int bookId = this.gui.getSelectedBookId();
                    Book book = this.bookServiceRemote.findById(bookId);
                    this.gui.displaySelectedBookDetails(book);
                }               
            }
            catch (Exception e)
            {
                gui.displayMessageInDialog(e.getMessage());
            }
        }
    }
    
    /**
     * update information of a book
     */
    public void updateBook() {
        try {
            Book book = this.gui.getBookDetails();
            bookServiceRemote.updateBook(book);
            this.displayAllBooks();
            this.gui.clearInput();
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to update property: " + ex.getMessage());
        }
    }

    /**
     * delete a book by book id
     */
    public void deleteBook() {
        try {
            int bookId = this.gui.getBookId();
            bookServiceRemote.deleteById(bookId);
            this.displayAllBooks();
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to update book: " + ex.getMessage());
        }  finally {
            this.gui.clearInput();
        }
    }
    /**
     * search book by title, author, callNumber, type
     */
    public void searchBook() {
        String title = this.gui.getBookTitle();
        String author = this.gui.getBookAuthor();
        String callNumber = this.gui.getBookCallNumber();
        String isbn = this.gui.getBookIsbn();
        String type = this.gui.getBookType();
        this.searchBookByMultiCondition(title, author, callNumber, isbn, type);
    }
    /**
     * search book by multi-conditions
     * @param title
     * @param author
     * @param callNumber
     * @param isbn
     * @param type 
     */
    public void searchBookByMultiCondition(String title, String author, String callNumber, String isbn, String type) {
        try {
                List<Book> books = bookServiceRemote.findByTitleAuthorCallNumberAndType(title,author,callNumber,isbn,type);
                if (books != null && !books.isEmpty()) {
                    this.gui.displayBookDetails(books);
                } else {
                    this.gui.displayMessageInDialog("No matched properties found");
                    this.gui.clearPropertyTable();
                }
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by contact person: " + ex.getMessage());
            this.gui.clearPropertyTable();
        } finally {
            this.gui.clearInput();
        }
    }


    public void searchBookById(int id) {
        try {
            
            Book book = bookServiceRemote.findById(id);
            
            if (book != null) {
                this.gui.displayBookDetails(book);
            } else {
                this.gui.displayMessageInDialog("No matched books found");
                this.gui.clearPropertyTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search book by ID: " + ex.getMessage());
            this.gui.clearPropertyTable();
        } finally {
            this.gui.clearInput();
        }
    }

    private void displayAllBooks() {
        try {
            List<Book> books = bookServiceRemote.findAll();
            
            if (books != null) {
                this.gui.displayBookDetails(books);
            }
            
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to retrieve books: " + ex.getMessage());
        }
    }

    private void addBook() {
        Book book = gui.getBookDetails();
        try {
            bookServiceRemote.saveBook(book);
            this.displayAllBooks();
            this.gui.clearInput();
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to add book: " + ex.getMessage());
        } finally {
            this.gui.clearInput();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            final LibraryCatalogue agency = new LibraryCatalogue("Monash Library Catalogue");
            //JDK 1.7
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    agency.initView();
//                }
//            });
            agency.initView();
            
//            //JDK 1.8
//            SwingUtilities.invokeLater(()-> {
//                agency.initView();
//            });
        } catch (Exception ex) {
            System.out.println("Failed to run application: " + ex.getMessage());
        }
    }

}