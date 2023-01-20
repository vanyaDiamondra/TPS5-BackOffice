<%@page import="java.util.ArrayList,model.Utilisateurs,model.Categorie,model.Commission" %>

<jsp:include page="header.jsp"/>
<script type="text/javascript">
    google.charts.load('current', {packages: ['corechart']});
    google.charts.setOnLoadCallback(drawCharts);
    
    function drawCharts(){
        drawChartCommission();
        drawChartCategorieplusvendu();
    }
    
    function drawChartCommission(){
        <% ArrayList<Commission> commissionparmois = (ArrayList<Commission>) request.getAttribute("commissionparmois"); %>
        let data = google.visualization.arrayToDataTable([
            ['Date','Pourcentage'],
            <% for (Commission c : commissionparmois) { %>
                [<%= c.getAnnee() %>+"-"+<%= c.getMois() %>,<%= c.getMoyennecommissionparmois()%>],
            <% } %>
        ]);
        
        let options = {
            curveType: 'function',
            legend: {position: "bottom"}
        };
        
        let chart = new google.visualization.LineChart(document.getElementById("commissionparmois"));
        
        chart.draw(data,options);
    }

    function drawChartCategorieplusvendu(){
        <% ArrayList<Categorie> maxventecategorie = (ArrayList<Categorie>) request.getAttribute("maxventecategorie"); %>
        let data = google.visualization.arrayToDataTable([
            ['Nom','Prix De Vente'],
            <% for (Categorie c : maxventecategorie) { %>
                ['<%= c.getNom() %>',<%= c.getTotalprixvente() %>],
            <% } %>
        ]);
        
        let options = {
            curveType: 'function',
            legend: {position: "top"}
        };
        
        let chart = new google.visualization.PieChart(document.getElementById("categorieplusvendu"));
        
        chart.draw(data,options);
    }
</script>
                        <!-- start page title -->
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="page-title-box">
                                    <h4>Tableau de Bord</h4>
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Enchere</a></li>
                                        </ol>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="state-information d-none d-sm-block">
                                    <div class="state-graph">
                                        <div id="header-chart-1" data-colors='["--bs-primary"]'></div>
                                        <div class="info">Commission <%= request.getAttribute("pourcentagecommissionactuel") %>%</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->

                        <div class="row">
                            <div class="col-xl-3 col-sm-6">
                                <div class="card mini-stat bg-primary">
                                    <div class="card-body mini-stat-img">
                                        <div class="mini-stat-icon">
                                            <i class="mdi mdi-cube-outline float-end"></i>
                                        </div>
                                        <div class="text-white">
                                            <h6 class="text-uppercase mb-3 font-size-16 text-white">Nombre D'utilisateurs</h6>
                                            <h2 class="mb-4 text-white"><%= request.getAttribute("nbutilisateurs") %></h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6">
                                <div class="card mini-stat bg-primary">
                                    <div class="card-body mini-stat-img">
                                        <div class="mini-stat-icon">
                                            <i class="mdi mdi-buffer float-end"></i>
                                        </div>
                                        <div class="text-white">
                                            <h6 class="text-uppercase mb-3 font-size-16 text-white">Total Soldes Collectés</h6>
                                            <h2 class="mb-4 text-white"><%= request.getAttribute("soldecollecteactuel") %></h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6">
                                <div class="card mini-stat bg-primary">
                                    <div class="card-body mini-stat-img">
                                        <div class="mini-stat-icon">
                                            <i class="mdi mdi-tag-text-outline float-end"></i>
                                        </div>
                                        <div class="text-white">
                                            <h6 class="text-uppercase mb-3 font-size-16 text-white">Encheres Total Vendu</h6>
                                            <h2 class="mb-4 text-white"><%= request.getAttribute("pourcentageencherevendu") %>%</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6">
                                <div class="card mini-stat bg-primary">
                                    <div class="card-body mini-stat-img">
                                        <div class="mini-stat-icon">
                                            <i class="mdi mdi-briefcase-check float-end"></i>
                                        </div>
                                        <div class="text-white">
                                            <h6 class="text-uppercase mb-3 font-size-16 text-white">Encheres Non-Vendu</h6>
                                            <h2 class="mb-4 text-white"><%= request.getAttribute("pourcentageencherenonvendu") %>%</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end row -->

                        <div class="row">

                            <div class="col-xl-6">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title mb-4">Commission par mois</h4>

                                        <div id="commissionparmois" style="margin: auto" data-colors='["#f0f1f4","--bs-primary","--bs-info"]' class="morris-charts morris-charts-height" dir="ltr">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xl-6">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title mb-4">Categories les plus vendus</h4>

                                        <%-- Insert Text Categorie --%>

                                        <div id="categorieplusvendu" style="margin: auto" data-colors='["#f0f1f4","--bs-primary","--bs-info"]' class="morris-charts morris-charts-height" dir="ltr"></div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- end row -->
                        <jsp:include page="footer.jsp"/>  

        </div>
        <!-- END layout-wrapper -->

        <!-- Right Sidebar -->
        <div class="right-bar">
            <div data-simplebar class="h-100">

                <div class="rightbar-title d-flex align-items-center px-3 py-4">
            
                    <h5 class="m-0 me-2">Settings</h5>

                    <a href="javascript:void(0);" class="right-bar-toggle ms-auto">
                        <i class="mdi mdi-close noti-icon"></i>
                    </a>
                </div>


            </div> <!-- end slimscroll-menu-->
        </div>
        <!-- /Right-bar -->

        <!-- Right bar overlay-->
        <div class="rightbar-overlay"></div>

        <!-- JAVASCRIPT -->
        <script src="assets/libs/jquery/jquery.min.js"></script>
        <script src="assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="assets/libs/simplebar/simplebar.min.js"></script>
        <script src="assets/libs/node-waves/waves.min.js"></script>
        <script src="assets/libs/jquery-sparkline/jquery.sparkline.min.js"></script>

        <!--Morris Chart-->
        <script src="assets/libs/morris.js/morris.min.js"></script>
        <script src="assets/libs/raphael/raphael.min.js"></script>

        <script src="assets/js/pages/dashboard.init.js"></script>

        <script src="assets/js/app.js"></script>

    </body>

</html>