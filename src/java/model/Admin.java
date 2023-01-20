/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.ConnectionPostgresSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import outils.ModelView;
import outils.Url;

/**
 *
 * @author 26132
 */
public class Admin {
    private int id;
    private String login;
    private String mdp;

    public Admin(int id, String login, String mdp) {
        this.id = id;
        this.login = login;
        this.mdp = mdp;
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
   
    public Admin login(String user,String password) throws Exception {
        Admin response = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionPostgresSQL.getconnect();
            String sql = "Select * from admin where login='"+user+"'"+" AND password='"+password+"'";
            System.out.println(sql);
            stmt = con.prepareStatement(sql);  
            ResultSet set = stmt.executeQuery();
            while(set.next()){
                System.out.println("misy hono");
               response = new Admin(set.getInt("id"),set.getString("login"),set.getString("password"));
            }
        } 
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            if(stmt!=null) stmt.close();
            if(con !=null) con.close();
        }
        System.out.println("model.Admin.login() "+response);
        return response;
    }
}
