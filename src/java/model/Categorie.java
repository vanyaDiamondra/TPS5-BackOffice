/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annotation.FieldDisable;
import db.ConnectionPostgresSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import outils.ModelView;
import outils.Url;

/**
 *
 * @author Princy
 */
public class Categorie {
    int id;
    String nom;
    
    @FieldDisable
    double totalprixvente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTotalprixvente() {
        return totalprixvente;
    }

    public void setTotalprixvente(double totalprixvente) {
        this.totalprixvente = totalprixvente;
    }
    
    
    //Utils
    private ModelView modelView = new ModelView();
    private PreparedStatement stmt = null;
    ArrayList<Categorie> liste = new ArrayList<>();
    ResultSet res = null;
    HashMap<String, Object> hMap = new HashMap<String, Object>();
    
    @Url(nameFunction = "index")
    public ModelView LayoutRedirect() throws Exception {
        System.out.println("LayoutRedirect passes ");
        DemandeRechargement[] demandes = new DemandeRechargement().getAll();
        hMap.put("demandes", demandes.length);
        hMap.put("liste-categorie", this.getAll());
        modelView.sethMapDAO(hMap);
        modelView.setTarget("categorie.jsp");
        return modelView;
    }
    
    //create
    @Url(nameFunction = "categorie-save")
    public ModelView save() throws Exception {
        Connection con = ConnectionPostgresSQL.getconnect();
        try {
            String query = "insert into categorie values (default,?)";
            stmt = con.prepareStatement(query);
            con.setAutoCommit(false);
            stmt.setString(1, this.getNom());
            stmt.execute();
            System.out.println(" categorie is SAVE!!");
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            con.commit();
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();
        }
        return LayoutRedirect();
    }
    
    //getall
    public ArrayList<Categorie> getAll() throws Exception {
        Connection con = ConnectionPostgresSQL.getconnect();
        try {
            String query = " SELECT * from categorie";
            System.out.println(query);
            res = con.createStatement().executeQuery(query);
            while (res.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(res.getInt(1));
                categorie.setNom(res.getString(2));
                liste.add(categorie);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (res != null)
                res.close();
            if (con != null)
                con.close();
        }
        return liste;
    }
    
    //getById
    @Url(nameFunction = "categorie-byId")
    public ModelView getById() throws Exception {
        Connection con = ConnectionPostgresSQL.getconnect();
        HashMap<String, Object> hMap = new HashMap<String, Object>();
        try {
            String query = " SELECT * from categorie where id= "+ this.getId();
            System.out.println(query);
            res = con.createStatement().executeQuery(query);
            while (res.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(res.getInt(1));
                categorie.setNom(res.getString(2));
                liste.add(categorie);
            }
            DemandeRechargement[] demandes = new DemandeRechargement().getAll();
            hMap.put("demandes", demandes.length);
            hMap.put("liste-byId", liste);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            modelView.sethMapDAO(hMap);
            modelView.setTarget("updateCategorie.jsp");
            if (res != null)
                res.close();
            if (con != null)
                con.close();
        }
        return modelView;
    }

    //update
    @Url(nameFunction = "categorie-update")
    public ModelView update() throws Exception {
        Connection con = ConnectionPostgresSQL.getconnect();
        int val = 0;
        try {
            String query = "UPDATE categorie SET nom ='" + this.getNom()+ "' where id = "+ this.getId();
            con.setAutoCommit(false);
            val = con.createStatement().executeUpdate(query);
            System.out.println(query +" " + val);
            con.commit();
            System.out.println(" categorie is UPDATE!!");
        } catch (Exception e) {
            // TODO: handle exception
            con.rollback();
            e.printStackTrace();
        } finally {
            if (res != null)
                res.close();
            if (con != null)
                con.close();
        }
        return LayoutRedirect();
    }
}
