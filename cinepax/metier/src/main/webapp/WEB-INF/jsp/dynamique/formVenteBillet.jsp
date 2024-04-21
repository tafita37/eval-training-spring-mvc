<%@page import="java.util.List"%>
<%@page import="eval.cinepax.cinepax.model.film.*"%>
<%@page import="eval.cinepax.cinepax.model.place.*"%>
<%
    List<Billet> listeBillet = (List<Billet>) request.getAttribute("listeBillet");
    List<Place> listePlace = (List<Place>) request.getAttribute("listePlace");
%>
        <%@ include file="../static/cssPart.jsp" %>
        <%@ include file="../static/header.jsp" %>
        <%@ include file="../static/menuDeroulant.jsp" %>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row d-flex justify-content-center">
                    <div class="col-md-6 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Vendre un billet</h4>
                                <p class="card-description">
                                    Vente
                                </p>
                                <form class="forms-sample" method="post" action="/billet/vendreBillet">
                                    <div class="form-group">
                                        <label for="inputBillet">Billet</label>
                                        <select name="id_billet" id="inputBillet" class="form-control">
                                            <option value="">
                                                Choisir un billet
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
                                    <div class="form-group">
                                        <label for="">Choisir les places enfants</label>
                                        <div class="place-list">
                                            <%
                                                for(int i=0; i<listePlace.size(); i++) {
                                                    %>
                                                    <div class="form-check">
                                                        <label class="form-check-label">
                                                            <input 
                                                                type="checkbox" 
                                                                class="form-check-input" 
                                                                name="id_place_enfant" 
                                                                value=<% out.println(listePlace.get(i).getIdPlace()); %>
                                                            />
                                                            <%
                                                                out.println(listePlace.get(i).getPlaceName());
                                                            %>
                                                        </label>
                                                    </div>
                                                <% }
                                            %>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">Choisir les places adultes</label>
                                        <div class="place-list">
                                            <%
                                                for(int i=0; i<listePlace.size(); i++) {
                                                    %>
                                                    <div class="form-check">
                                                        <label class="form-check-label">
                                                            <input 
                                                                type="checkbox" 
                                                                class="form-check-input" 
                                                                name="id_place_adulte" 
                                                                value=<% out.println(listePlace.get(i).getIdPlace()); %>
                                                            />
                                                            <%
                                                                out.println(listePlace.get(i).getPlaceName());
                                                            %>
                                                        </label>
                                                    </div>
                                                <% }
                                            %>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputDateVente">Date de vente</label>
                                        <input 
                                            type="date" 
                                            name="date_vente_billet" 
                                            class="form-control" 
                                            id="inputDateVente" 
                                            placeholder="Date de vente"
                                        >
                                    </div>
                                    <button type="submit" class="btn btn-primary mr-2">
                                        Vendre
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="../static/footer.jsp" %>
        </div>
        <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="/vendors/typeahead.js/typeahead.bundle.min.js"></script>
  <script src="/vendors/select2/select2.min.js"></script>
  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="/js/off-canvas.js"></script>
  <script src="/js/hoverable-collapse.js"></script>
  <script src="/js/template.js"></script>
  <script src="/js/settings.js"></script>
  <script src="/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="/js/file-upload.js"></script>
  <script src="/js/typeahead.js"></script>
  <script src="/js/select2.js"></script>
  <!-- End custom js for this page-->
</body>

</html>