/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MGestionUtilisateur;

import java.util.Date;

/**
 *
 * @author mahjoub
 */
public class User {
    private int id ;
    private String username ;
    private String username_canonical ;
    private String email ;
    private String email_canonical ;
    private int enabled ;
    private String password ;
    private Date last_login ;
    private String roles ;
    private String about ;
    private int phone_number ;
    private String location ;
    private String address ;
    private String job ;  
    private int nb_ban ;

    public User(int id, String username, String username_canonical, String email, String email_canonical, int enabled, String password, Date last_login, String roles, String about, int phone_number, String location, String address, String job, int nb_ban) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.password = password;
        this.last_login = last_login;
        this.roles = roles;
        this.about = about;
        this.phone_number = phone_number;
        this.location = location;
        this.address = address;
        this.job = job;
        this.nb_ban = nb_ban;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
public User(int id, String username, String email,  String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = role;
    }
public User(int id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = role;
    }
public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getNb_ban() {
        return nb_ban;
    }

    public void setNb_ban(int nb_ban) {
        this.nb_ban = nb_ban;
    }
    
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role=" + roles + ", enabled=" + enabled + ", last_login=" + last_login + ", roles=" + roles + ", about=" + about + ", phone_number=" + phone_number + ", location=" + location + ", address=" + address + ", job=" + job + ", nb_ban=" + nb_ban + '}';
    }


}
