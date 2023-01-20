<%-- 
    Document   : listesVentes
    Created on : 13 janv. 2023, 03:13:56
    Author     : Princy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"/>

    <h3>Listes produits vendus</h3>

    <div class="mb-3" data-select2-id="7"  style="width: 200px;">
        <label class="form-label">Catégorie</label>
        <select class="form-control select2 select2-hidden-accessible" data-select2-id="1" tabindex="-1" aria-hidden="true">
            <option >Select</option>
            <option >Select</option>
        </select>
    </div>
    
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
                                                        <th>Produits</th>
                                                        <th>Prix vendus</th>
                                                        <th>Commission</th>
                                                   </tr>
                                                </thead>
      
                                                <tbody>
                                                    <tr data-id="5">
                                                        <td data-field="id"></td>
                                                        <td data-field="name"></td>
                                                        <td data-field="name"></td>
                                                    </tr>
                                                </tbody>
       
                                            </table>
                                        </div>
        
                                    </div>
                                </div>
                            </div> <!-- end col -->
                        </div> <!-- end row -->:
                        
                        
                        

    <p class="text-primary" style="font-size: 17px;">Total commission :?? MGA</p>
    
<jsp:include page="footer.jsp"/>