/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.mbeans;

import com.monash.entity.User;
import com.monash.service.IUserServiceRemote;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author bob
 */
@ManagedBean
@RequestScoped
public class UserManagedBean implements Serializable{
    @EJB
    private IUserServiceRemote userServiceRemote;
    private String email;
    private String password;
    private String userType;
    private String message;   // prompt message
    private HttpSession session;
    private User user;


    public UserManagedBean() {
        this.user = new User();
    }



    /**
     * user login method
     * @return
     * @throws Exception 
     */
    public String login() throws Exception {
        if(userType.equals("Librarian")){
            User user = userServiceRemote.findByEmailPasswordAndType(email, DigestUtils.md5Hex(password),"Librarian");
            if(user != null){
                // save user information in session
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ExternalContext extContext =facesContext.getExternalContext();
                this.session = (HttpSession) extContext.getSession(true);
                this.session.setAttribute("user", user);
                return "main";
            }
        }
        else if(userType.equals("Member")){
            User user = userServiceRemote.findByEmailPasswordAndType(email, DigestUtils.md5Hex(password),"Member");
            if(user != null){
                this.session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                this.session.setAttribute("user", user);
                return "main";
            }
        }
        setMessage("Email or Password error!");
        return "login";
    }
    /**
     * user register method
     * @return
     * @throws Exception 
     */
    public String register() throws Exception{
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));  // md5 encode
        String result = userServiceRemote.addUser(user);
        if (result.equals("success")){
            return "login";
        }
        return "register";
    }
    /**
     * user logout method
     * @return 
     */
    public String logout(){
        //clear session, then logout
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext =facesContext.getExternalContext();
        this.session = (HttpSession) extContext.getSession(true);
        this.session.removeAttribute("user");
        return "login";
    }
    /**
     * view personal information method
     * @return 
     */
    public String viewPersionalInfo(){
        return "user_info";
    }
    

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IUserServiceRemote getUserServiceRemote() {
        return userServiceRemote;
    }
        public String getMessage() {
        return message;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserServiceRemote(IUserServiceRemote userServiceRemote) {
        this.userServiceRemote = userServiceRemote;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }


    

   
}
