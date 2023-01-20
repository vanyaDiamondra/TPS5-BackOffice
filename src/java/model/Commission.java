/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.ConnectionPostgresSQL;
import java.sql.Connection;
import java.sql.Date;
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
public class Commission {
    private int id;
    private double pourcentage;
    private String dateCommission;
    
    int mois;
    int annee;
    double moyennecommissionparmois;

    public double getPourcentage() {
        /*if(pourcentage misy virj){
            pourcentage * 100 
        }*/
        return pourcentage;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPourcentage(double pourcentage) {
        //verifier rah misy virgule na tsis
        /*if(pourcentage tsis misy virj){
            this.pourcentage = (pourcentage/100);
        }else{
            this.pourcentage = pourcentage;
        }*/

        this.pourcentage = pourcentage;
    }

    public String getDateCommission() {
        return dateCommission;
    }

    public void setDateCommission(String dateCommission) {
        this.dateCommission = dateCommission;
    }
    
    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getMoyennecommissionparmois() {
        return moyennecommissionparmois;
    }

    public void setMoyennecommissionparmois(double moyennecommissionparmois) {
        this.moyennecommissionparmois = moyennecommissionparmois;
    }

    public Commission() {
    }

    public Commission(double pourcentage, String dateCommission) {
        this.pourcentage = pourcentage;
        this.dateCommission = dateCommission;
    }

    //Utils
    private ModelView modelView = new ModelView();
    private PreparedStatement stmt = null;
    ArrayList<Commission> liste = new ArrayList<>();
    ResultSet res = null;
    HashMap<String, Object> hMap = new HashMap<String, Object>();
    
    @Url(nameFunction = "commission")
    public ModelView LayoutRedirect() throws Exception {
        System.out.println("LayoutRedirect passes ");
        hMap.put("liste-commission", this.getAll());
        DemandeRechargement[] demandes = new DemandeRechargement().getAll();
        hMap.put("demandes", demandes.length);
        modelView.sethMapDAO(hMap);
        modelView.setTarget("commission.jsp");
        return modelView;
    }
    
    //create
    @Url(nameFunction = "commission-save")
    public ModelView save() throws Exception {
        Connection con = ConnectionPostgresSQL.getconnect();
        try {
            String query = "insert into commissions values (default,?,current_date)";
            stmt = con.prepareStatement(query);
            con.setAutoCommit(false);
            System.out.println("save :" + this.getPourcentage());
            stmt.setDouble(1, this.getPourcentage());
            stmt.execute();
            System.out.println(query);
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
    
    public ArrayList<Commission> getAll() throws Exception {
        Connection con = ConnectionPostgresSQL.getconnect();
        try {
            String query = " SELECT * from commissions order by dates DESC";
            System.out.println(query);
            res = con.createStatement().executeQuery(query);
            while (res.next()) {
                Commission commission = new Commission();
                commission.setId(res.getInt(1));
                commission.setPourcentage(res.getDouble(2));
                commission.setDateCommission(String.valueOf(res.getDate(3)));
                liste.add(commission);
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
     @Url(nameFunction = "commission-byId")
     public ModelView getById() throws Exception {
         Connection con = ConnectionPostgresSQL.getconnect();
         HashMap<String, Object> hMap = new HashMap<String, Object>();
         try {
             String query = " SELECT * from commissions where id= " + this.getId();
             System.out.println(query);
             res = con.createStatement().executeQuery(query);
             while (res.next()) {
                 Commission commission = new Commission();
                 commission.setId(res.getInt(1));
                 commission.setPourcentage(res.getDouble(2));
                 commission.setDateCommission(String.valueOf(res.getDate(3)));
                 liste.add(commission);
             }
             hMap.put("val-byId", liste);
             DemandeRechargement[] demandes = new DemandeRechargement().getAll();
            hMap.put("demandes", demandes.length);
         } catch (Exception e) {
             // TODO: handle exception
             e.printStackTrace();
         } finally {
             modelView.sethMapDAO(hMap);
             modelView.setTarget("updateCommission.jsp");
             if (res != null)
                 res.close();
             if (con != null)
                 con.close();
         }
         return modelView;
     }
 
     //update
     @Url(nameFunction = "commission-update")
     public ModelView update() throws Exception {
         Connection con = ConnectionPostgresSQL.getconnect();
         try {
             String query = "UPDATE commissions SET pourcentage = "+ this.getPourcentage() +", dates= current_date where id= " + this.getId();
             con.setAutoCommit(false);
             System.out.println(query);
             con.createStatement().executeUpdate(query);
             con.commit();
         } catch (Exception e) {
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
