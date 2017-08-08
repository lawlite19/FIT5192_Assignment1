/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.mbeans;

import com.monash.entity.Loan;
import com.monash.entity.User;
import com.monash.service.ILoanServiceRemote;
import com.monash.service.IUserServiceRemote;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author bob
 */
@ManagedBean
@SessionScoped
public class MemberManagedBean implements Serializable{
    @EJB
    private IUserServiceRemote userServiceRemote;
    @EJB
    private ILoanServiceRemote loanServiceRemote;
    private List<User> users;
    private List<Loan> loans;
    private User member;
    private User user;
    private Integer id;
    private String lastName;
    private String firstName;
    private String email;
    private String message;
    /**
     * Creates a new instance of MemberManagedBean
     */
    public MemberManagedBean() {
        member = new User();
    }
    /**
     * view all member user
     * @return
     * @throws Exception 
     */
    public String list_member() throws Exception{
        users = userServiceRemote.findByType("Member");
        return "member_list";   
    }
    /**
     * view member user details
     * @return
     * @throws Exception 
     */
    public String detailUI() throws Exception{
        // personal info
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user_id");
        user = userServiceRemote.findById(Integer.parseInt(id));
        // loan info
        loans = loanServiceRemote.findByUserId(Integer.parseInt(id));  
        return "member_detailUI";
    }
    /**
     * search by the combination of id, firstName, lastName and email
     * @return
     * @throws Exception 
     */
    public String searchByMultiCondition() throws Exception{
        users = userServiceRemote.findByIdLastFirstNameAndEmail(id,firstName,lastName,email);
        return "member_list";
    }
    /**
     * jump to add UI method
     * @return 
     */
    public String addUI(){
        return "member_saveUI";
    }
    /**
     * add a user method
     * @return
     * @throws Exception 
     */
    public String add() throws Exception{
        member.setPassword(DigestUtils.md5Hex(member.getPassword()));  // md5 encryption
        String result = userServiceRemote.addUser(member);
        if("success".equals(result)){
            return list_member();
        }
        message = "add user failed!";
        return "failed";
    }
    /**
     * jump to edit UI method
     * @return
     * @throws Exception 
     */
    public String editUI() throws Exception{
        String user_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user_id");
        member = userServiceRemote.findById(Integer.parseInt(user_id));
        id = member.getId();
        return "member_saveUI";
    }
    /**
     * update a user
     * @return
     * @throws Exception 
     */
    public String edit() throws Exception{
        member.setId(id);
        member.setPassword(DigestUtils.md5Hex(member.getPassword()));  // md5 encryption
        String result = userServiceRemote.updateUser(member);
        if("success".equals(result)){
            return list_member();
        }
        message = "update user failed!";
        return "failed";
    }
    /**
     * delete a user method
     * @return
     * @throws Exception 
     */
    public String delete() throws Exception{
        String user_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user_id");
        String result = userServiceRemote.deleteById(Integer.parseInt(user_id));
        if("success".equals(result)){
            return list_member();
        }
        message = "delete user failed!";
        return "failed";
    }
    public IUserServiceRemote getUserServiceRemote() {
        return userServiceRemote;
    }

    public void setUserServiceRemote(IUserServiceRemote userServiceRemote) {
        this.userServiceRemote = userServiceRemote;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public ILoanServiceRemote getLoanServiceRemote() {
        return loanServiceRemote;
    }

    public void setLoanServiceRemote(ILoanServiceRemote loanServiceRemote) {
        this.loanServiceRemote = loanServiceRemote;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
