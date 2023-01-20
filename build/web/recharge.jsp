<%@page import="model.*"%>
<%
    DemandeRechargement[] demandes = (DemandeRechargement[])request.getAttribute("demandes");
    String message = null;
    
    if( request.getAttribute("message") != null ){
        message = (String)request.getAttribute("message");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>

<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-body">

                <h4 class="card-title">Demande de rechargement</h4>
                <form action="erecharge" method="post">
                <p><input type="submit" class="btn btn-primary btn-lg waves-effect waves-light" value="Valider"></p>
                <% if( message != null ){ %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">
                        </button>
                    <strong>Succès! </strong><%= message %>
                </div>
                <% } %>
                
                <table id="datatable" class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Utilisateurs ID</th>
                                <th>Date demande</th>
                                <th>Montant</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            <% for( DemandeRechargement demande: demandes ){ %>
                                <tr>
                                    <td><input type="checkbox" name="demandeID" value="<%= demande.getId() %>"></td>
                                    <td><%= demande.getId() %></td>
                                    <td><%= demande.getDateDemande() %></td>
                                    <td><%= demande.getMontant()%></td>
                                    <td>
                                        <!-- <form action="detailsCompte" method="post">
                                            <input type="hidden" name="compteID" value="<%= demande.getCompteUtilisateursid() %>">
                                            <input type="submit" value="Voir détails">
                                        </form> -->
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <!-- end col -->
</div>


<jsp:include page="footer.jsp"/>