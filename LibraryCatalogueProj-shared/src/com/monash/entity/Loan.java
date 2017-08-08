package com.monash.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "t_loan")
@NamedQueries({@NamedQuery(name = Loan.FIND_ALL, query = "SELECT l from Loan l")})
public class Loan implements Serializable {
    public static final String FIND_ALL = "Loan.find_all";

    private Integer id;
    private User memberId;
    private Timestamp startDate;
    private Timestamp dueDate;

//    private Set<Book> bookIds;
    private Book bookId;

    public Loan(User memberId, Timestamp startDate, Timestamp dueDate, Book bookId) {
        this.memberId = memberId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.bookId = bookId;
    }

    public Loan() {
    }
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Loan_Seq")
    @SequenceGenerator(name="Loan_Seq",sequenceName="Loan_Seq",allocationSize=1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @NotNull
    public User getMemberId() {
        return memberId;
    }

    public void setMemberId(User memberId) {
        this.memberId = memberId;
    }

    @NotNull
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="t_loan_book",    
//        joinColumns={    
//            @JoinColumn(name="l_id",referencedColumnName="id")    
//            },    
//            inverseJoinColumns={    
//            @JoinColumn(name="b_id",referencedColumnName="id")    
//       }    
//    ) 
//    public Set<Book> getBookIds() {
//        return bookIds;
//    }
//
//    public void setBookIds(Set<Book> bookIds) {
//        this.bookIds = bookIds;
//    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @NotNull
    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Loan{" + "id=" + id + ", memberId=" + memberId + ", startDate=" + startDate + ", dueDate=" + dueDate + ", bookId=" + bookId + '}';
    }
    
    
}
