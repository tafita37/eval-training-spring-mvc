<%@page import="java.util.List"%>
<%@page import="eval.cinepax.cinepax.view.stat.StatVenteBFilm"%>
<%
    List<StatVenteBFilm> listeStatVente = (List<StatVenteBFilm>) request.getAttribute("listeStatVente");
    double totalPourcentageVente=listeStatVente.stream().mapToDouble(StatVenteBFilm::getPourcentageVente).sum();
    int totalNbVente=listeStatVente.stream().mapToInt(StatVenteBFilm::getNbVente).sum();
%>
<%@ include file="../static/header.jsp" %>
    <%@ include file="../static/menuDeroulant.jsp" %>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Statistique de vente de bille par film</h4>
                                <div class="table-responsive pt-3">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>
                                                    #
                                                </th>
                                                <th>
                                                    Film
                                                </th>
                                                <th>
                                                    Pourcentage
                                                </th>
                                                <th>
                                                    Nombre de vente
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for(int i=0; i<listeStatVente.size(); i++) {
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <% 
                                                                out.println(listeStatVente.get(i).getId());
                                                            %>
                                                        </td>
                                                        <td>
                                                            <% 
                                                                out.println(listeStatVente.get(i).getFilm().getNomFilm());
                                                            %>
                                                        </td>
                                                        <td>
                                                            <div class="progress">
                                                                <div 
                                                                    class="progress-bar bg-info" 
                                                                    role="progressbar" 
                                                                    style="<% out.println("width:"+listeStatVente.get(i).getPourcentageVente()+"%"); %>"
                                                                    aria-valuenow="<% out.println("width:"+listeStatVente.get(i).getPourcentageVente()); %>" 
                                                                    aria-valuemin="0" 
                                                                    aria-valuemax="100"
                                                                >
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <% 
                                                                out.println(listeStatVente.get(i).getNbVente());
                                                            %>
                                                        </td>
                                                    </tr>
                                                <% }
                                            %>
                                            <tr>
                                                <td>
                                                    
                                                </td>
                                                <td>

                                                </td>
                                                <td>
                                                    <%
                                                        out.println(totalPourcentageVente+" %");
                                                    %>
                                                </td>
                                                <td>
                                                    <%
                                                        out.println(totalNbVente);
                                                    %>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <%@ include file="../static/footer.jsp" %>
                <!-- partial -->
        </div>
        <!-- main-panel ends -->
        </div>
        <!-- page-body-wrapper ends -->
        </div>
        <!-- container-scroller -->
        <!-- plugins:js -->
        <script src="../../vendors/js/vendor.bundle.base.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="../../js/off-canvas.js"></script>
        <script src="../../js/hoverable-collapse.js"></script>
        <script src="../../js/template.js"></script>
        <script src="../../js/settings.js"></script>
        <script src="../../js/todolist.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page-->
        <!-- End custom js for this page-->
        </body>

        </html>