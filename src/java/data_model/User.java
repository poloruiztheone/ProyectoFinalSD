/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brb25
 */
@XmlRootElement 
public class User {
    
    public String name;
    public String pass;
    public int id;
    
}
