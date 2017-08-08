/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.mbeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author bob
 */
@ManagedBean
@RequestScoped
public class HomeManagedBean implements Serializable{

    public String home_index(){
        return "index";
    }
    public String home_top(){
        return "top.xhtml";
    }
    public String home_left(){
        return "left.xhtml";
    }
    public String home_right(){
        return "right.xhtml";
    }
    
}
