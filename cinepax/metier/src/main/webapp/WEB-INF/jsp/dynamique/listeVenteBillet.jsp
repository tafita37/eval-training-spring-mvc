<%@page import="eval.cinepax.cinepax.model.film.*"%>
<%@page import="eval.cinepax.cinepax.model.vente.*"%>
<%@page import="java.util.*"%>
<%
    List<Billet> listeBillet = (List<Billet>) request.getAttribute("listeBillet");
    List<VenteBillet> listeVenteBillet = (List<VenteBillet>) request.getAttribute("listeVenteBillet");
%>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
<div class="main-panel">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-md-12 grid-margin">
                <div class="row">
                    <div class="col-12 col-xl-4">
                        <div class="justify-content-start d-flex">
                            <div>
                                <form action=""
                                    method="get" class="d-flex">
                                    <div class="form-group mx-1">
                                        <select name="id_billet" id="" class="form-control">
                                            <option value="">
                                                Choisissez un billet
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
                                    </div>
                                    <div class="form-group mx-1">
                                        <button type="submit"
                                            class="btn btn-primary mr-2">
                                            Rechercher
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Billets vendus</h4>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Film</th>
                                        <th>Salle</th>
                                        <th>Date et heure de seance</th>
                                        <th>Date de vente</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for(int i=0; i<listeVenteBillet.size(); i++) {
                                            %>
                                            <tr>
                                                <td>
                                                    <% 
                                                        out.println(listeVenteBillet.get(i).getBillet().getFilm().getNomFilm()); 
                                                    %>
                                                </td>
                                                <td>
                                                    <% 
                                                        out.println(listeVenteBillet.get(i).getBillet().getSalle().getNomSalle()); 
                                                    %>
                                                </td>
                                                <td>
                                                    <% 
                                                        out.println(listeVenteBillet.get(i).getBillet().getDateHeureBillet()); 
                                                    %>
                                                </td>
                                                <td>
                                                    <% 
                                                        out.println(listeVenteBillet.get(i).getDateVenteBillet()); 
                                                    %>
                                                </td>
                                                <td>
                                                    <a href="<% out.println("/billet/listeTicketOfVente/"+listeVenteBillet.get(i).getIdVenteBillet()); %>">
                                                        Tickets
                                                    </a>
                                                </td>
                                            </tr>
                                        <% }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../static/footer.jsp" %>
    <!-- content-wrapper ends -->
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