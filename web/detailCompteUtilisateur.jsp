<%@page import="modele.*"%>
<%
    CompteUtilisateurs compte = (CompteUtilisateurs)request.getAttribute("compte");
    Utilisateurs utilisateur = (Utilisateurs)request.getAttribute("utilisateur");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vente aux enchères</title>
    </head>
    <body>
        <h3>Details</h3>

        <div>
           <p> Utilisateur : <%= utilisateur.getNom() %> <%= utilisateur.getPrenom()%></p>
           <p>Solde actuel : <%= compte.getSolde()%> </p>
        </div>
    </body>
</html>
