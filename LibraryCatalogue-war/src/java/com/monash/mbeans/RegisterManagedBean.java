/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author bob
 */
@ManagedBean
@RequestScoped
public class RegisterManagedBean {

    public String registerUI(){
        return "register";
    }
}
