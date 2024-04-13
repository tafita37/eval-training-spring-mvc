<%@page import="java.util.List"%>
<%@page import="eval.cinepax.cinepax.model.place.*"%>
<%@page import="eval.cinepax.cinepax.model.film.*"%>
<%
    List<Billet> listeBillet = (List<Billet>) request.getAttribute("listeBillet");
    List<VCountPlaceRangee> listeCountRangee=(List<VCountPlaceRangee>) request.getAttribute("listeCountRangee");
%>
<%@ include file="../static/header.jsp" %>
    <%@ include file="../static/menuDeroulant.jsp" %>
        <div class="main-panel">
            <div class="content-wrapper">
                <h4 class="card-title">Liste des places</h4>
                <div class="row d-flex justify-content-center">
                    <form action="/billet/place/listePlaceDispoBySeance" method="get" class="d-flex">
                        <select name="id_billet" id="" class="form-control mx-2">
                            <option value="">
                                Choisir une seance
                            </option>
                            <%
                                for(int i=0; i<listeBillet.size(); i++) {
                                    %>
                                    <option value=<% out.println(listeBillet.get(i).getIdBillet()); %>>
                                        <%
                                            out.println(listeBillet.get(i).getNameBillet());
                                        %>
                                    </option>
                                <% }
                            %>
                        </select>
                        <button type="submit" class="btn btn-primary mr-2">
                            Rechercher
                        </button>
                    </form>
                    <div class="listePlace" style="width: 100%;">
                        <%
                            for(int i=0; i<listeCountRangee.size(); i++) {
                                %>
                                <div 
                                    style="
                                        display : grid; 
                                        grid-template-columns : repeat(<% out.println(listeCountRangee.get(i).getNbPlace()); %>, 1fr); 
                                        margin-block-start:10px;
                                        gap:10px;
                                    "
                                >
                                    <%
                                    for(int j=0; j<listeCountRangee.get(i).getListeVPlaceEtat().size(); j++) {
                                        %>
                                        <button type="button" class="<% out.println(listeCountRangee.get(i).getListeVPlaceEtat().get(j).getButtonClass()); %>">
                                            <%
                                                out.println(listeCountRangee.get(i).getListeVPlaceEtat().get(j).getPlaceName());
                                            %>
                                        </button>
                                    <% } %>
                                </div>
                            <% }
                        %>
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