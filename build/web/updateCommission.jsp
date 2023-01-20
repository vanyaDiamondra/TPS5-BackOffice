<%-- 
    Document   : updateCommission
    Created on : 13 janv. 2023, 02:24:55
    Author     : Princy
--%>

<%@page import="model.Commission"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<h2>Update commission</h2>
        <%
            HashMap<String,Object> hMap = (HashMap<String,Object>) request.getAttribute("hMapDAO"); 
            ArrayList<Commission> liste = (ArrayList<Commission>) hMap.get("val-byId");
        %>
    <form action="commission-update.do" method="post">
        <% 
            for(Commission c : liste ){
        %>
        <input type="number" name="pourcentage" id="" value="<%= c.getPourcentage()%>"> 
        <input name="id" type="text" value="<%= c.getId() %>" hidden>
        <%
            }
        %>
        <input type="submit" value="Update">
    </form>
<jsp:include page="footer.jsp"/>