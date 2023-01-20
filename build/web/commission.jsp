<%-- 
    Document   : Commission
    Created on : 13 janv. 2023, 01:20:38
    Author     : Princy
--%>

<%@page import="model.Commission"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
   
    <h4>Commission</h4>
    <div class="col-lg-6 ">
        <form method="post" action="commission-save.do">
        <div class="card card-body text-center">
            <h4 class="card-title">Ajout commission</h4>
            <div class="mb-3 row">
                <input type="text" class="form-control" id="validationCustom01" placeholder="pourcentage en %" name="pourcentage" required>
            </div>
            <br>
            <div class="mb-3 row">
                <button class="btn btn-primary w-md waves-effect waves-light" type="submit"><i class="mdi mdi-plus"></i></button>
            </div>
        </div>
        </form>    
    </div>
    
    
    
    
    
    
    
    <br>
    <hr>
    
    
        <%
            HashMap<String,Object> hMap = (HashMap<String,Object>) request.getAttribute("hMapDAO"); 
            ArrayList<Commission> liste = (ArrayList<Commission>) hMap.get("liste-commission");
        %>
    
        
        
        <div class="row" style="width: 500px;" >
                            <div class="col-12">
                                <div class="card" >
                                    <div class="card-body">
        
                                        <h4 class="card-title">Listes des Commissions</h4>
                                                
                                        <div class="table-responsive">
                                            <table class="table table-editable table-nowrap align-middle table-edits">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Pourcentage</th>
                                                        <th>Date d'ajout</th>
                                                   </tr>
                                                </thead>
         <% 
            for(Commission c : liste ){
        %>
                                                <tbody>
                                                    <tr data-id="5">
                                                        <td data-field="id"><%= c.getId() %></td>
                                                        <td data-field="name"><%= c.getPourcentage() %> %</td>
                                                        <td data-field="name"><%= c.getDateCommission() %></td>
                                                        <td>
                                                            <a class="btn btn-outline-secondary btn-sm edit" href="commission-byId.do?id=<%= c.getId() %>" title="Edit">
                                                                <i class="fas fa-pencil-alt"></i>
                                                            </a>
                                                        </td>
                                                        <td>
                                                            <a class="btn btn-outline-secondary btn-sm edit" href="commission-byId.do?id=<%= c.getId() %>" title="Delete">
                                                                <i class="fas fa-trash"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </tbody>
             <%
            }
        %>
                                            </table>
                                        </div>
        
                                    </div>
                                </div>
                            </div> <!-- end col -->
                        </div> <!-- end row -->
                        
                        
                        
<jsp:include page="footer.jsp"/>