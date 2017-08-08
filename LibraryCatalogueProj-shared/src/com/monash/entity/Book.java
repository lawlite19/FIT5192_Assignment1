package com.monash.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "t_book")
@NamedQueries({@NamedQuery(name = Book.FIND_ALL, query = "SELECT b FROM Book b")})
public class Book implements Serializable {
    public static final String FIND_ALL = "Book.find_all";
    private Integer id;
    private String isbn;
    private String callNumber;
    private String title;
    private String author;
    private String publisher;
    private String type;
    private String thumbnail;
    private String description;
    private String previewUrl;
    private Set<Loan> loans;

    public Book(String isbn, String callNumber, String title, String author, String publisher, String type, String thumbnail, String description, String previewUrl) {
        this.isbn = isbn;
        this.callNumber = callNumber;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.type = type;
        this.thumbnail = thumbnail;
        this.description = description;
        this.previewUrl = previewUrl;
    }

    public Book(Integer id, String isbn, String callNumber, String title, String author, String publisher, String type, String thumbnail, String description, String previewUrl) {
        this.id = id;
        this.isbn = isbn;
        this.callNumber = callNumber;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.type = type;
        this.thumbnail = thumbnail;
        this.description = description;
        this.previewUrl = previewUrl;
    }
    

    public Book() {
    }


    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Book_Seq")
    @SequenceGenerator(name="Book_Seq",sequenceName="Book_Seq",allocationSize=1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Column(length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
    
    @OneToMany(mappedBy = "bookId", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", isbn=" + isbn + ", callNumber=" + callNumber + ", title=" + title + ", author=" + author + ", publisher=" + publisher + ", type=" + type + ", thumbnail=" + thumbnail + ", description=" + description + ", previewUrl=" + previewUrl + ", loans=" + loans + '}';
    }

    
}
