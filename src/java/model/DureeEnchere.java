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
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import outils.ModelView;
import outils.Url;

/**
 *
 * @author Princy
 */
public class DureeEnchere {
    private String dureeMin;
    private String dureeMax;
    private String dates;

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public DureeEnchere() {
    }

    public String getDureeMin() {
        return dureeMin;
    }

    public void setDureeMin(String dureeMin) {
        this.dureeMin = dureeMin;
    }

    public String getDureeMax() {
        return dureeMax;
    }

    public void setDureeMax(String dureeMax) {
        this.dureeMax = dureeMax;
    }

    // Utils
    private ModelView modelView = new ModelView();
    private PreparedStatement stmt = null;
    ArrayList<DureeEnchere> liste = new ArrayList<>();
    ResultSet res = null;
    HashMap<String, Object> hMap = new HashMap<String, Object>();

    public static String changeToTime(String time) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss");
        java.util.Date parsedDate = inputFormat.parse(time);

        String formattedDate = outputFormat.format(parsedDate);
        return formattedDate;
    }

    @Url(nameFunction = "dureeenchere")
    public ModelView LayoutRedirectDureeEnchere() throws Exception {
        System.out.println("LayoutRedirect passes ");
        hMap.put("liste-dureeenchere", this.getAll());
        DemandeRechargement[] demandes = new DemandeRechargement().getAll();
        hMap.put("demandes", demandes.length);
        modelView.sethMapDAO(hMap);
        modelView.setTarget("DureeEnchere.jsp");
        return modelView;
    }

    // create
    @Url(nameFunction = "dureeenchere-save")
    public ModelView save() throws Exception {
        Connection con = ConnectionPostgresSQL.getconnect();
        try {
            String query = "insert into ContrainteDuree values (?,?,current_date)";
            stmt = con.prepareStatement(query);
            con.setAutoCommit(false);
            
            stmt.setTime(1, Time.valueOf(changeToTime(this.getDureeMin())));
            stmt.setTime(2, Time.valueOf(changeToTime(this.getDureeMax())));
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
        return LayoutRedirectDureeEnchere();
    }

    // getall
    public ArrayList<DureeEnchere> getAll() throws Exception {
        Connection con = ConnectionPostgresSQL.getconnect();
        try {
            String query = " SELECT * from ContrainteDuree order by dates desc";
            System.out.println(query);
            res = con.createStatement().executeQuery(query);
            while (res.next()) {
                DureeEnchere dureeEnchere = new DureeEnchere();
                dureeEnchere.setDureeMin(String.valueOf(res.getTime(1)));
                dureeEnchere.setDureeMax(String.valueOf(res.getTime(2)));
                dureeEnchere.setDates(String.valueOf(res.getDate(3)));
                liste.add(dureeEnchere);
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

    // getById
    /*
     * @Url(nameFunction = "dureeenchere-byId")
     * public ModelView getById() throws Exception {
     * Connection con = new ConnectDB().getConnectDB();
     * HashMap<String, Object> hMap = new HashMap<String, Object>();
     * try {
     * String query = " SELECT * from categorie where id= " + this.getId();
     * System.out.println(query);
     * res = con.createStatement().executeQuery(query);
     * while (res.next()) {
     * Categorie categorie = new Categorie();
     * categorie.setId(res.getInt(1));
     * categorie.setNomCategorie(res.getString(2));
     * liste.add(categorie);
     * }
     * hMap.put("result-byId", liste);
     * } catch (Exception e) {
     * // TODO: handle exception
     * e.printStackTrace();
     * } finally {
     * modelView.sethMapDAO(hMap);
     * modelView.setTarget("updateDureeEnchere.jsp");
     * if (res != null)
     * res.close();
     * if (con != null)
     * con.close();
     * }
     * return modelView;
     * }
     * 
     * // update
     * 
     * @Url(nameFunction = "dureeenchere-update")
     * public ModelView update() throws Exception {
     * Connection con = new ConnectDB().getConnectDB();
     * try {
     * String query = "UPDATE categorie SET nom = ? ";
     * stmt = con.prepareStatement(query);
     * con.setAutoCommit(false);
     * stmt.setString(1, this.getNomCategorie());
     * 
     * stmt.execute();
     * System.out.println(" dureeenchere is UPDATE!!");
     * } catch (Exception e) {
     * // TODO: handle exception
     * e.printStackTrace();
     * } finally {
     * if (res != null)
     * res.close();
     * if (con != null)
     * con.close();
     * }
     * return LayoutRedirectDureeEnchere();
     * }
     */

}
