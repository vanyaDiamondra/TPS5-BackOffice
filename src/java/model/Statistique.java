/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.StatistiqueDAO;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Statistique {
     StatistiqueDAO sDAO;
    
    private StatistiqueDAO getStatistiqueDAO(){
        if (sDAO == null){
            sDAO = new StatistiqueDAO();
        }
        return sDAO;
    }
    
    
    public ArrayList<Utilisateurs> getMaxVenteUtilisateur() throws Exception{
        sDAO = getStatistiqueDAO();
        return sDAO.getMaxVenteUtilisateur();
    }
    
    public ArrayList<Categorie> getMaxVenteCategorie() throws Exception{
        sDAO = getStatistiqueDAO();
        return sDAO.getMaxVenteCategorie();
    }
    
    public ArrayList<Commission> getCommissionparMois() throws Exception{
        sDAO = getStatistiqueDAO();
        return sDAO.getCommissionparmois();
    }
    
    public int getNombreUtilisateur() throws Exception{
        sDAO = getStatistiqueDAO();
        return sDAO.getNombreUtilisateur();
    }
    
    public double getSoldeCollecteActuel() throws Exception{
        sDAO = getStatistiqueDAO();
        return sDAO.getSoldeCollecteActuel();
    }
    
    public double getPourcentageCommissionActuel() throws Exception{
        sDAO = getStatistiqueDAO();
        return sDAO.getPourcentageCommissionActuel();
    }
    
    public double getPourcentageEnchereVendu() throws Exception{
        sDAO = getStatistiqueDAO();
        int nbtotalenchere = sDAO.getNombreTotalEnchere();
        int nbtotalencherevendu = sDAO.getNombreTotalEnchereVendu();
        System.out.println(nbtotalenchere);
        double pourcentage = ( (double)nbtotalencherevendu/(double)nbtotalenchere)*100;
        System.out.println(pourcentage);
        return pourcentage;
    }
    
    public double getPourcentageEnchereNonVendu() throws Exception{
        sDAO = getStatistiqueDAO();
        int nbtotalenchere = sDAO.getNombreTotalEnchere();
        int nbtotalencherenonvendu = sDAO.getNombreTotalEnchereNonVendu();
        System.out.println(nbtotalencherenonvendu);
        double pourcentage = ((double)nbtotalencherenonvendu/(double)nbtotalenchere)*100;
        System.out.println(pourcentage);
        return pourcentage;
    }
}
