package model;
import annotation.FieldDisable;
import general.ObjetBDD;

public class Utilisateurs extends ObjetBDD {
    int id;
    String nom;
    String prenom;
    String dateNaissance;
    String adresse;
    String email;
    String password;

    @FieldDisable
    double totalprixvente;

    public double getTotalprixvente() {
        return totalprixvente;
    }

    public void setTotalprixvente(double totalprixvente) {
        this.totalprixvente = totalprixvente;
    }
    
    public Utilisateurs() {
    }
    
    public Utilisateurs(int id) {
        this.id = id;
    }

    public Utilisateurs(int id, String nom, String prenom, String dateNaissance, String adresse, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}