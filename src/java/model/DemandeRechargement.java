/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import db.ConnectMongoDB;
import db.ConnectionPostgresSQL;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author DELL
 */
public class DemandeRechargement {
    Object _id;
    int id;
    int CompteUtilisateursid;
    CompteUtilisateurs compteUtilisateurs;
    double montant;
    String dateDemande;
    int statut;

    public DemandeRechargement() {
    }

    public DemandeRechargement(int id, int CompteUtilisateursid, double montant, String dateDemande, int statut) throws Exception {
        this.id = id;
        this.CompteUtilisateursid = CompteUtilisateursid;
        setMontant(montant);
        this.dateDemande = dateDemande;
        this.statut = statut;
    }

    public int getCompteUtilisateursid() {
        return CompteUtilisateursid;
    }

    public void setCompteUtilisateursid(int CompteUtilisateursid) {
        this.CompteUtilisateursid = CompteUtilisateursid;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) throws Exception {
        if( montant < 0 ){
            throw new Exception("Solde montant invalide");
        }
        this.montant = montant;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

    public int getStatut() {
        return statut;
    }

    public CompteUtilisateurs getCompteUtilisateurs() {
        return compteUtilisateurs;
    }

    public void setCompteUtilisateurs(CompteUtilisateurs compteUtilisateurs) {
        this.compteUtilisateurs = compteUtilisateurs;
    }
    
    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public DemandeRechargement[] getAll() throws Exception {
        MongoDatabase db = ConnectMongoDB.getConnect();
        MongoCollection<Document> coll = db.getCollection("DemandeRechargement");
        ArrayList<DemandeRechargement> demandes = new ArrayList<>();
        
        Bson filter = Filters.eq("statut", 1);
        FindIterable<Document> documents = coll.find(filter);   
        MongoCursor cursor = documents.iterator();
        Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
        while (cursor.hasNext()) {
            String json = new Gson().toJson(cursor.next());
            DemandeRechargement row = gson.fromJson(json, DemandeRechargement.class);
            demandes.add(row);
        }
        DemandeRechargement[] result = new DemandeRechargement[demandes.size()];
        return demandes.toArray(result);
    }
    
    public DemandeRechargement find() throws Exception{
        MongoDatabase db = ConnectMongoDB.getConnect();
        MongoCollection<Document> coll = db.getCollection("DemandeRechargement");
       DemandeRechargement demande = null;
        
        Bson filter = Filters.eq("id", this.getId());
        System.out.println("modele.DemandeRechargement.find() "+this.getId());
        FindIterable<Document> documents = coll.find(filter);   
        MongoCursor cursor = documents.iterator();
        
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        while (cursor.hasNext()) {
            String json = gson.toJson(cursor.next());
            demande = gson.fromJson(json, DemandeRechargement.class);
        }
        return demande;
    }
    
    public void validRecharging() throws Exception{
        // update statut du demande id to mongodb
        MongoDatabase db = ConnectMongoDB.getConnect();
        UpdateResult result = db.getCollection("DemandeRechargement").updateOne(
                new BasicDBObject("id", this.getId()),
                new BasicDBObject("$set", new BasicDBObject("statut", 0))
        ) ;
      
        // UPDATE solde compte utilisateurs from postgres
        DemandeRechargement demande = this.find();
        Connection conn = ConnectionPostgresSQL.getconnect();
        CompteUtilisateurs compte = new CompteUtilisateurs();
        compte.setUtilisateursid(demande.getCompteUtilisateursid());
        
        compte = compte.find(conn);
        compte.setSolde(compte.getSolde()+demande.getMontant());
        compte.update(conn);       
        conn.close();
    }
    
    public void getDetailsCompte(int compteUtilisateursID) throws Exception { // postgres
        Connection conn = ConnectionPostgresSQL.getconnect();
        CompteUtilisateurs compte = new CompteUtilisateurs(this.getCompteUtilisateursid()).find(conn);
        
        Utilisateurs utilisateurs = new Utilisateurs(compte.getUtilisateursid());
        conn.close();
        // get the solde final
        // informations about the count owner
    }
}
