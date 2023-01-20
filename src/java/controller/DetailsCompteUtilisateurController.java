/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ConnectionPostgresSQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CompteUtilisateurs;
import model.Utilisateurs;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DetailsCompteUtilisateurController", urlPatterns = {"/detailsCompte"})
public class DetailsCompteUtilisateurController extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String compteID = request.getParameter("compteID");
        System.out.println(compteID);
        try {
            Connection conn = ConnectionPostgresSQL.getconnect();
            CompteUtilisateurs compte = new CompteUtilisateurs();
            compte.setUtilisateursid(Integer.parseInt(compteID));
            
            compte = compte.find(conn);
            Utilisateurs utilisateur = (Utilisateurs)new Utilisateurs(compte.getUtilisateursid()).find(conn);
 
            request.setAttribute("utilisateur", utilisateur);
            request.setAttribute("compte", compte);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/detailCompteUtilisateur.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
