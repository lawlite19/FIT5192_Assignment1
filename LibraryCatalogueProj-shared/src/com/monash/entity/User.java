package com.monash.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "t_user")
@NamedQueries({@NamedQuery(name = User.FIND_ALL, query = "SELECT u from User u")})
public class User implements Serializable {
    public static final String FIND_ALL = "User.find_all";

    private Integer id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String type;
    private String address;
    private String phoneNumber;

    public User(String lastName, String firstName, String email, String password, String type, String address, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="User_Seq")
    @SequenceGenerator(name="User_Seq",sequenceName="User_Seq",allocationSize=1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Pattern(regexp = "[^\\d]+", message = "lastName can't contail digits")
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Pattern(regexp = "[^\\d]+", message = "firstName can't contail digits")
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "email format error")
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotNull
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Pattern(regexp = "^[9][0-9]{7}$|^[0][0-9]{9}$", message = "phone nuber format error")
    @NotNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", password=" + password + ", type=" + type + ", address=" + address + ", phoneNumber=" + phoneNumber + '}';
    }
    
}
