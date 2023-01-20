<%-- 
    Document   : DureeEnchere
    Created on : 13 janv. 2023, 03:05:25
    Author     : Princy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.DureeEnchere"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
     <%
            HashMap<String,Object> hMap = (HashMap<String,Object>) request.getAttribute("hMapDAO"); 
            ArrayList<DureeEnchere> liste = (ArrayList<DureeEnchere>) hMap.get("liste-dureeenchere");
        %>
    
    
    <h4>Commission</h4>
    <div class="col-lg-4 " style="width: 500px;">
        <form method="post" action="dureeenchere-save.do" class="row gx-3 gy-2 align-items-center">
        <div class="card card-body text-center">
            <h4 class="card-title">Durée enchère</h4>
            <br>
                <div class="mb-3 row">
                    <div class="col-sm-3">
                        <label for="example-datetime-local-input" >Durée minimum</label>
                        <input class="form-control"  type="time" name="dureeMin"   id="example-date-input" required>
                    </div>
                    <div class="col-sm-3">
                        <label for="example-datetime-local-input" >Durée maximum</label>
                        <input class="form-control"  type="time" name="dureeMax"   id="example-date-input" required>
                    </div>
                </div>
            <br>
            <div class="mb-3 row">
                <button class="btn btn-primary w-md waves-effect waves-light" type="submit">Ajouter</button>
            </div>
            <br>
        </div>
        </form>    
    </div>
    
    
    
    
    
    <hr>
    <br>

    
    
    
    
    <div class="row" style="width: 500px;" >
                            <div class="col-12">
                                <div class="card" >
                                    <div class="card-body">
        
                                        <h4 class="card-title">Listes Durée</h4>
                            
        
                                        <div class="table-responsive">
                                            <table class="table table-editable table-nowrap align-middle table-edits">
                                                <thead>
                                                    <tr>
                                                        <th>Durée Minimum</th>
                                                        <th>Durée Maximum</th>
                                                        <th>Date</th>
                                                   </tr>
                                                </thead>
        <% 
            for(DureeEnchere c : liste ){
        %>
                                                <tbody>
                                                    <tr data-id="5">
                                                        <td data-field="id"><%= c.getDureeMin() %></td>
                                                        <td data-field="name"><%= c.getDureeMax() %></td>
                                                        <td data-field="name"><%= c.getDates() %></td>
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
