<%-- 
    Document   : index
    Created on : 12 janv. 2023, 21:28:51
    Author     : Princy
--%>

<%@page import="model.Categorie"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
        <%
            HashMap<String,Object> hMap = (HashMap<String,Object>) request.getAttribute("hMapDAO"); 
            ArrayList<Categorie> liste = (ArrayList<Categorie>) hMap.get("liste-categorie");
            int nbDemande = (int)hMap.get("demandes");
        %>
    <h4>Catégorie</h4>
    <div class="col-lg-6 ">
        <form method="post" action="categorie-save.do">
        <div class="card card-body text-center">
            <h4 class="card-title">Ajout catégorie</h4>
            <div class="mb-3">
                <input type="text" class="form-control" id="validationCustom01" placeholder="Nom" name="nom" required=""> 
                
            </div>
            <button class="btn btn-primary w-md waves-effect waves-light" type="submit"><i class="mdi mdi-plus"></i></button>
        </div>
        </form>    
    </div>
    <hr>
    <br>
    
    <div class="row" style="width: 500px;" >
        <div class="col-lg-12">
            <div class="card" >
                <div class="card-body">
                    <h4 class="card-title">Listes des Catégories</h4>
                    <div class="table-responsive">
                        <table class="table table-editable table-nowrap align-middle table-edits">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Catégorie</th>
                                
                                </tr>
                            </thead>
                            <% for(Categorie c : liste ){  %>
                                <tbody>
                                    <tr data-id="5">
                                        <td data-field="id"><%= c.getId() %></td>
                                        <td data-field="name"><%= c.getNom()%></td>
                                        <td>
                                            <a class="btn btn-outline-secondary btn-sm edit" href="categorie-byId.do?id=<%= c.getId() %>" title="Edit">
                                                <i class="fas fa-pencil-alt"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                          <% }  %>
                        </table>
                    </div>

                </div>
            </div>
        </div> <!-- end col -->
    </div> <!-- end row -->
                        
                  
<jsp:include page="footer.jsp"/>
