/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DemandeRechargement;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DemandeRechargemntController", urlPatterns = {"/erecharge"})
public class DemandeRechargementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DemandeRechargement[] demandes = new DemandeRechargement().getAll();
            request.setAttribute("demandes", demandes);
            
            HashMap<String, Object> hMap = new HashMap<String, Object>();
            hMap.put("demandes", demandes.length);
            request.setAttribute("hMapDAO", hMap);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/recharge.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String[] demandes = request.getParameterValues("demandeID");
        DemandeRechargement d = new DemandeRechargement();
        try {
            for(String s: demandes){
                d.setId(Integer.parseInt(s));
                d.validRecharging();
            }
            
            request.setAttribute("message", "Demande(s) validé(s)");
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
