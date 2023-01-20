/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categorie;
import model.Commission;
import model.DemandeRechargement;
import model.Statistique;
import model.Utilisateurs;

/**
 *
 * @author DELL
 */
@WebServlet(name = "StatistiqueController", urlPatterns = {"/stat"})
public class StatistiqueController extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ArrayList<Utilisateurs> listemaxventeutilisateur = null;
            ArrayList<Categorie> listemaxventecategorie = null;
            ArrayList<Commission> commissionparmois = null;
            int nbutilisateur = 0;
            double soldecollecteactuel = 0;
            double pourcentagecommissionactuel = 0;
            double pourcentageencherevendu = 0;
            double pourcentageencherenonvendu = 0;
            
            try {
                Statistique ss = new Statistique();
                listemaxventeutilisateur = ss.getMaxVenteUtilisateur();
                listemaxventecategorie = ss.getMaxVenteCategorie();
                commissionparmois = ss.getCommissionparMois();
                
                nbutilisateur = ss.getNombreUtilisateur();
                soldecollecteactuel = ss.getSoldeCollecteActuel();
                pourcentageencherevendu = ss.getPourcentageEnchereVendu();
                pourcentageencherenonvendu = ss.getPourcentageEnchereNonVendu();
                pourcentagecommissionactuel = ss.getPourcentageCommissionActuel();
           
                DemandeRechargement[] demandes = new DemandeRechargement().getAll();
                HashMap<String, Object> hMap = new HashMap<String, Object>();
                hMap.put("demandes", demandes.length);
                request.setAttribute("hMapDAO", hMap);;
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            
            request.setAttribute("maxventeutilisateur", listemaxventeutilisateur);
            request.setAttribute("maxventecategorie", listemaxventecategorie);
            request.setAttribute("commissionparmois", commissionparmois);
            
            request.setAttribute("nbutilisateurs", nbutilisateur);
            request.setAttribute("soldecollecteactuel", soldecollecteactuel);
            request.setAttribute("pourcentageencherevendu", (int)pourcentageencherevendu);
            request.setAttribute("pourcentageencherenonvendu", (int)pourcentageencherenonvendu);
            request.setAttribute("pourcentagecommissionactuel", pourcentagecommissionactuel);
            
            RequestDispatcher rqd = request.getRequestDispatcher("statistique.jsp");
            rqd.forward(request, response);
    }
}
