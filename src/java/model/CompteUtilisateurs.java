/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annotation.FieldDisable;
import general.ObjetBDD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class CompteUtilisateurs extends ObjetBDD {
    int id;
    double solde;
    int Utilisateursid;
    @FieldDisable
    Utilisateurs utilisateurs;
    
    public CompteUtilisateurs() {
    }

    public CompteUtilisateurs(int id, double solde, int Utilisateursid) throws Exception {
        this.id = id;
        setSolde(solde);
        this.Utilisateursid = Utilisateursid;
    }
    
    public CompteUtilisateurs(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) throws Exception {
        if( solde < 0 ){
            throw new Exception("Solde montant invalide");
        }
        this.solde = solde;
    }

    public int getUtilisateursid() {
        return Utilisateursid;
    }

    public void setUtilisateursid(int Utilisateursid) {
        this.Utilisateursid = Utilisateursid;
    }

    public Utilisateurs getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Utilisateurs utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
    
    
    
    @Override
    public CompteUtilisateurs find(Connection conn) throws Exception{
        CompteUtilisateurs result = null;
        String sql = "select * from  compteutilisateurs where utilisateursid = "+this.getUtilisateursid();
        System.out.println(sql);
        try{
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            
            while( res.next() ){
                result = new CompteUtilisateurs(res.getInt("id"), res.getDouble("solde"), res.getInt("Utilisateursid"));
            } 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    public CompteUtilisateurs findByID(Connection conn) throws Exception{
        CompteUtilisateurs result = null;
        String sql = "select * from  compteutilisateurs where id = "+this.getId();
        System.out.println(sql);
        try{
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            
            while( res.next() ){
                result = new CompteUtilisateurs(res.getInt("id"), res.getDouble("solde"), res.getInt("Utilisateursid"));
            } 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
