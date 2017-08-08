/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.monash.gui;


import com.monash.entity.Book;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

public class TableGUIImpl extends JFrame implements LibraryCatalogueGUI {
    
    private static final String[] TABLE_COLUMNS = {"Book ID", "ISBN", "Call Number", "Title", "Author(s)", "Publisher", "Type" ,"Thumbnail","Description", "Preview URL"};
    
    private final JButton closeButton;
    private final JButton addButton;
    private final JButton viewButton;
    private final JButton searchButton;
    private final JButton updateButton;
    private final JButton deleteButton;
    
    private final JPanel inputPanel;
    private final JPanel buttonPanel;

    private final JLabel bookIdLabel;
    private final JLabel isbnLabel;
    private final JLabel callNumberLabel;
    private final JLabel titleLabel;
    private final JLabel authorsLabel;
    private final JLabel publisherLabel;
    private final JLabel typeLabel;
    private final JLabel thumbnailLabel;
    private final JLabel descriptionLabel;
    private final JLabel previewURLLabel;

    private final JTextField bookIdTextField;
    private final JTextField isbnTextField;
    private final JTextField callNumberTextField;
    private final JTextField titleTextField;
    private final JTextField authorsTextField;
    private final JTextField publisherTextField;
    private final JTextField typeTextField;
    private final JTextField thumbnailTextField;
    private final JTextField descriptionTextField;
    private final JTextField previewURLField;
    
    private final JTable bookTable;
    
    private final JComboBox typeComboBox;

    Book property;

    public TableGUIImpl(ActionListener actionListener, ListSelectionListener listSelectionListener) {
        super("Monash Library Catalogue");

        // create buttons
        this.closeButton = new JButton("Close");
        this.viewButton = new JButton("View");
        this.searchButton = new JButton("Search");
        this.addButton = new JButton("Add");
        this.updateButton = new JButton("Update");
        this.deleteButton = new JButton("Delete");

        // create container
        Container container = this.getContentPane();

        // create labels
        this.bookIdLabel = new JLabel("Book ID:");
        this.isbnLabel = new JLabel("ISBN Number:");
        this.callNumberLabel = new JLabel("Call Number:");
        this.titleLabel = new JLabel("Title:");
        this.authorsLabel = new JLabel("Author(s):");
        this.publisherLabel = new JLabel("Publisher:");       
        this.typeLabel = new JLabel("Type:");
        this.thumbnailLabel = new JLabel("Thumbnail:");
        this.descriptionLabel = new JLabel("Description:");
        this.previewURLLabel = new JLabel("Preview URL:");

        // create text fields
        this.bookIdTextField = new JTextField();
        this.isbnTextField = new JTextField();
        this.callNumberTextField = new JTextField();
        this.titleTextField = new JTextField();
        this.authorsTextField = new JTextField();
        this.publisherTextField = new JTextField();
        this.typeTextField = new JTextField();
        this.thumbnailTextField = new JTextField();
        this.descriptionTextField = new JTextField();
        this.previewURLField = new JTextField();
        
        // create table
        this.bookTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.bookTable.getSelectionModel().addListSelectionListener(listSelectionListener);       
        this.bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        TableColumnModel bookTableColumnModel = this.bookTable.getColumnModel();
        bookTableColumnModel.getColumn(0).setPreferredWidth(50);
        bookTableColumnModel.getColumn(1).setPreferredWidth(300);
        bookTableColumnModel.getColumn(2).setPreferredWidth(100);
        bookTableColumnModel.getColumn(3).setPreferredWidth(100);
        bookTableColumnModel.getColumn(4).setPreferredWidth(100);
        bookTableColumnModel.getColumn(5).setPreferredWidth(100);
        
        //create combobox
        this.typeComboBox = new JComboBox();
        
        // create panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();

        // set layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(11,2));
        this.buttonPanel.setLayout(new GridLayout(1,4));

        // add action listeners
        this.closeButton.addActionListener(actionListener);
        this.addButton.addActionListener(actionListener);
        this.viewButton.addActionListener(actionListener);
        this.searchButton.addActionListener(actionListener);
        this.updateButton.addActionListener(actionListener);
        this.deleteButton.addActionListener(actionListener);

        // add components
        this.inputPanel.add(this.bookIdLabel);
        this.inputPanel.add(this.bookIdTextField);

        this.inputPanel.add(this.isbnLabel);
        this.inputPanel.add(this.isbnTextField);
        
        this.inputPanel.add(this.callNumberLabel);
        this.inputPanel.add(this.callNumberTextField);
        
        this.inputPanel.add(this.titleLabel);
        this.inputPanel.add(this.titleTextField);
        
        this.inputPanel.add(this.authorsLabel);
        this.inputPanel.add(this.authorsTextField);
        
        this.inputPanel.add(this.publisherLabel);
        this.inputPanel.add(this.publisherTextField);
        
        this.inputPanel.add(this.typeLabel);
        this.inputPanel.add(this.typeTextField);
        
        this.inputPanel.add(this.thumbnailLabel);
        this.inputPanel.add(this.thumbnailTextField);
        
        this.inputPanel.add(this.descriptionLabel);
        this.inputPanel.add(this.descriptionTextField);
        
        this.inputPanel.add(this.previewURLLabel);
        this.inputPanel.add(this.previewURLField);
        
    

        // add buttons to panel
        this.buttonPanel.add(this.addButton);
        this.buttonPanel.add(this.updateButton);
        this.buttonPanel.add(this.deleteButton);
        this.buttonPanel.add(this.searchButton);
        this.buttonPanel.add(this.viewButton);
        this.buttonPanel.add(this.closeButton);
        
        // add panels to content pane
        container.add(inputPanel,BorderLayout.NORTH);
        container.add(new JScrollPane(this.bookTable), BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);
       
        // change the default behaviour of the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(950, 570);       
        this.setVisible(true);
    }
    
    @Override
    public void displayBookType(List<String> types) {
//        this.contactPersonComboBox.removeAllItems();
//        
//        this.contactPersonComboBox.addItem("");
//        
//        for (String type : types) {
//            this.contactPersonComboBox.addItem(type);
//        }
    }
    
    @Override
    public String getSelectedBookType() {
//        if (this.contactPersonComboBox.getItemCount() > 0 && this.contactPersonComboBox.getSelectedIndex() > 0) {
//            return (String)this.contactPersonComboBox.getSelectedItem();
//        } else {
//            return null;
//        }
        return null;
    }
    
    @Override
    public int getBookId() {
        String id = this.bookIdTextField.getText();
        return id == null || id.isEmpty() ? 0 : Integer.parseInt(id);
    }
    
    @Override
    public double getBudget() {
//        String price = this.priceTextField.getText();
//        return price == null || price.isEmpty() ? 0 : Double.parseDouble(price);
        return 1.0;
    }
    
    @Override
    public boolean isBookSelected() {
        return (this.bookTable.getSelectedRow() >= 0);
    }
    
    @Override
    public int getSelectedBookId() {
        int selectedRowIndex = this.bookTable.getSelectedRow();
        
        String propertyId = this.bookTable.getValueAt(selectedRowIndex, 0).toString();
        return Integer.parseInt(propertyId); 
    }

    @Override
    public Book getBookDetails()
    {
        return new Book(Integer.parseInt(bookIdTextField.getText()), 
                        isbnTextField.getText(), 
                        callNumberTextField.getText(), 
                        titleTextField.getText(), 
                        authorsTextField.getText(),
                        publisherTextField.getText(), 
                        typeTextField.getText(), 
                        thumbnailTextField.getText(),
                        descriptionTextField.getText(),
                        previewURLField.getText()
                        );		
    }
    
    @Override
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void displayBookDetails(Book book) {  
        this.clearPropertyTable();
        this.clearInput();
        ((DefaultTableModel)this.bookTable.getModel()).addRow(new Object[]{book.getId(),
                                                                           book.getIsbn(), 
                                                                           book.getCallNumber(),
                                                                           book.getTitle(), 
                                                                           book.getAuthor(),
                                                                           book.getPublisher(),
                                                                           book.getType(),
                                                                           book.getThumbnail(),
                                                                           book.getDescription(),
                                                                           book.getPreviewUrl()
                                                                           });
    }
    
    @Override
    public void displayBookDetails(List<Book> books) {    
        this.clearPropertyTable();
        this.clearInput();
        
        for (Book book : books) {
            ((DefaultTableModel)this.bookTable.getModel()).addRow(new Object[]{book.getId(), 
                                                                               book.getIsbn(), 
                                                                               book.getCallNumber(), 
                                                                               book.getTitle(), 
                                                                               book.getAuthor(),
                                                                               book.getPublisher(),
                                                                               book.getType(),
                                                                               book.getThumbnail(),
                                                                               book.getDescription(),
                                                                               book.getPreviewUrl()
                                                                               });
        }
    }
    
    @Override
    public void displayBookDetails(Set<Book> books) {    
        this.clearPropertyTable();
        this.clearInput();
        
        for (Book book : books) {
            ((DefaultTableModel)this.bookTable.getModel()).addRow(new Object[]{book.getId(), 
                                                                               book.getIsbn(), 
                                                                               book.getCallNumber(), 
                                                                               book.getTitle(), 
                                                                               book.getAuthor(),
                                                                               book.getPublisher(),
                                                                               book.getType(),
                                                                               book.getThumbnail(),
                                                                               book.getDescription(),
                                                                               book.getPreviewUrl()
                                                                               });
        }
    }

    @Override
    public void displaySelectedBookDetails(Book book) {
        this.bookIdTextField.setText(String.valueOf(book.getId()));           
        this.isbnTextField.setText(book.getIsbn());
        this.callNumberTextField.setText(book.getCallNumber());
        this.titleTextField.setText(book.getTitle());
        this.authorsTextField.setText(book.getAuthor());
        this.publisherTextField.setText(book.getPublisher());               
        this.typeTextField.setText(book.getType());
        this.thumbnailTextField.setText(book.getThumbnail()); 
        this.descriptionTextField.setText(book.getDescription());
        this.previewURLField.setText(book.getPreviewUrl());
        
    }
    
    
    @Override
    public void clearPropertyTable() {     
        int numberOfRow = this.bookTable.getModel().getRowCount();
        
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.bookTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }            
        }
    }
    
    @Override
    public void clearInput() {
        this.clearTextFields();
        this.clearComboBoxes();
    }

    @Override
    public void clearTextFields() {
        this.bookIdLabel.setText("");
        this.isbnTextField.setText("");
        this.callNumberTextField.setText("");
        this.titleTextField.setText("");
        this.authorsTextField.setText("");
        this.publisherTextField.setText(""); 
        this.typeTextField.setText("");
        this.thumbnailTextField.setText("");
        this.descriptionTextField.setText("");
        this.previewURLField.setText("");  
    }
    
    @Override
    public void clearComboBoxes() {
        if (this.typeComboBox.getItemCount() > 0) {
            this.typeComboBox.setSelectedIndex(0);
        } 
    }

    @Override
    public JButton getUpdateButton() {
        return updateButton;
    }

    @Override
    public JButton getDeleteButton() {
        return deleteButton;
    }

    @Override
    public JTable getBookTable() {
        return bookTable;
    }

    @Override
    public JButton getViewButton() {
        return viewButton;
    }

    @Override
    public JButton getAddButton() {
        return addButton;
    }
    
    @Override
    public JButton getSearchButton() {
        return searchButton;
    }

    @Override
    public JButton getCloseButton() {
        return closeButton;
    }

    @Override
    public String getBookTitle() {
        String title = this.titleTextField.getText();
        return title == null || title.isEmpty() ? "" : title;
    }

    @Override
    public String getBookAuthor() {
        String author = this.authorsTextField.getText();
        return author == null || author.isEmpty() ? "" : author;
    }

    @Override
    public String getBookCallNumber() {
        String callNumber = this.callNumberTextField.getText();
        return callNumber == null || callNumber.isEmpty() ? "" : callNumber;
    }

    @Override
    public String getBookType() {
        String type = this.typeTextField.getText();
        return type == null || type.isEmpty() ? "" : type;
    }
    @Override
    public String getBookIsbn() {
        String isbn = this.isbnTextField.getText();
        return isbn == null || isbn.isEmpty() ? "" : isbn;
    }

}

