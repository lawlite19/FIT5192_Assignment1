/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.service;

import com.monash.entity.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author bob
 */
@Remote
public interface IUserServiceRemote {
    // user login method
    public User findByEmailPasswordAndType(String email, String password, String userType) throws Exception;
    // user register method
    public String addUser(User user) throws Exception;
    // find all member
    public List<User> findByType(String type) throws Exception;
    // find by userID
    public User findById(Integer id) throws Exception;
    // find by id, first name, last name and email
    public List<User> findByIdLastFirstNameAndEmail(Integer id,String firstName, String lastName, String email) throws Exception;
    // update an user
    public String updateUser(User member);
    // delete by user id
    public String deleteById(Integer id);
}
