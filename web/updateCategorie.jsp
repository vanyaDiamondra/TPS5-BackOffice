<%-- 
    Document   : updateCategorie
    Created on : 13 janv. 2023, 00:53:56
    Author     : Princy
--%>

<%@page import="model.Categorie"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
        <%
            HashMap<String,Object> hMap = (HashMap<String,Object>) request.getAttribute("hMapDAO"); 
            ArrayList<Categorie> liste = (ArrayList<Categorie>) hMap.get("liste-byId");
        %>
    
    <h4>Modifier Catésgorie</h4>
    <div class="col-lg-4 ">
        <form method="get" action="categorie-update.do">
        <div class="card card-body text-center">
            <h4 class="card-title">Ajout catégorie</h4>
              <% 
            for(Categorie c : liste ){
        %>
            <div class="mb-3">
                <input type="text" class="form-control" id="validationCustom01" placeholder="nom categorie" value="<%= c.getNom()%>" name="nom" required="">
            </div>
            <input name="id" type="text" value="<%= c.getId() %>" hidden>
           <%
            }
        %>
            <button class="btn btn-primary w-md waves-effect waves-light" type="submit">Modifier</button>
        </div>
        </form>    
    </div>
    
    
<jsp:include page="footer.jsp"/>
