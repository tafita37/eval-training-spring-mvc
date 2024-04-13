<%@page import="eval.cinepax.cinepax.model.vente.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%
    List<PlaceVenteBillet> listeTicket=(List<PlaceVenteBillet>) request.getAttribute("listeTicket");
    Locale locale = new Locale("fr", "FR");
    NumberFormat formatteur = NumberFormat.getInstance(locale);
%>
<%@ include file="../static/header.jsp" %>
    <%@ include file="../static/menuDeroulant.jsp" %>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <%
                        for(int i=0; i<listeTicket.size(); i++) {
                            %>
                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Cinepax</h4>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <address class="text-primary">
                                                    <p class="font-weight-bold">Film : </p>
                                                    <p>
                                                        <%
                                                            out.println(listeTicket.get(i).getVenteBillet().getBillet().getFilm().getNomFilm());
                                                        %>
                                                    </p>
                                                </address>
                                            </div>
                                            <div class="col-md-6">
                                                <address class="text-primary">
                                                    <p class="font-weight-bold">
                                                        Salle : 
                                                    </p>
                                                    <p class="mb-2">
                                                        <%
                                                            out.println(listeTicket.get(i).getVenteBillet().getBillet().getSalle().getNomSalle());
                                                        %>
                                                    </p>
                                                </address>
                                            </div>
                                            <div class="col-md-3">
                                                <address class="text-primary">
                                                    <p class="font-weight-bold">
                                                        Row : 
                                                    </p>
                                                    <p class="mb-2">
                                                        <%
                                                            out.println(listeTicket.get(i).getPlace().getRangee().getNomRangee());
                                                        %>
                                                    </p>
                                                </address>
                                            </div>
                                            <div class="col-md-3">
                                                <address class="text-primary">
                                                    <p class="font-weight-bold">
                                                        Seat : 
                                                    </p>
                                                    <p class="mb-2">
                                                        <%
                                                            out.println(listeTicket.get(i).getPlace().getNumeroPlace());
                                                        %>
                                                    </p>
                                                </address>
                                            </div>
                                            <div class="col-md-3">
                                                <address class="text-primary">
                                                    <p class="font-weight-bold">
                                                        Time : 
                                                    </p>
                                                    <p class="mb-2">
                                                        <%
                                                            out.println(listeTicket.get(i).getVenteBillet().getBillet().getHoursBillet());
                                                        %>
                                                    </p>
                                                </address>
                                            </div>
                                            <div class="col-md-3">
                                                <address class="text-primary">
                                                    <p class="font-weight-bold">
                                                        Date : 
                                                    </p>
                                                    <p class="mb-2">
                                                        <%
                                                            out.println(listeTicket.get(i).getVenteBillet().getBillet().getDateBillet());
                                                        %>
                                                    </p>
                                                </address>
                                            </div>
                                            <div class="col-md-6">
                                                <address class="text-primary">
                                                    <p class="font-weight-bold">
                                                        Prix : 
                                                    </p>
                                                    <p class="mb-2">
                                                        <% 
                                                            out.println(formatteur.format(listeTicket.get(i).getTarif().getPrixTarif())+" ar"); 
                                                        %>
                                                    </p>
                                                </address>
                                            </div>
                                            <div class="col-md-6">
                                                <address class="text-primary">
                                                    <p class="font-weight-bold">
                                                        Siege : 
                                                    </p>
                                                    <p class="mb-2">
                                                        <% 
                                                            out.println(listeTicket.get(i).getTarif().getCategorieName()); 
                                                        %>
                                                    </p>
                                                </address>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body" style="display: flex; flex-direction: row-reverse;"> 
                                        <a href="<% out.println("/billet/exportPdf/"+listeTicket.get(i).getIdPlaceVenteBillet()); %>" class="btn btn-danger btn-icon-text">
                                            <i class="ti-download btn-icon-prepend"></i>                                                     
                                            Export pdf
                                        </a>
                                    </div>
                                </div>
                            </div>
                        <% }
                    %>
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