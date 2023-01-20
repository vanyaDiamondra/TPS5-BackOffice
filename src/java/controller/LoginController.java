/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.Categorie;

/**
 *
 * @author DELL
 */
@WebServlet(name = "LoginController", urlPatterns = {"/loginservlet"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        
        try {
            Admin result = new Admin().login(login, mdp);
            Categorie categorie = new Categorie();
            
            if(result != null){
                HttpSession session = request.getSession();
                session.setAttribute("session-admin", result.getId());
                request.setAttribute("liste_categorie", categorie.getAll());
                
                response.sendRedirect("/Enchere/stat");
            }else{
                RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
                rd.forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
