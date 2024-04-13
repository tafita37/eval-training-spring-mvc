<%@page import="eval.cinepax.cinepax.model.film.produit.*"%>
<%@page import="java.util.*"%>
<%
    List<Produit> listeProduit = (List<Produit>) request.getAttribute("listeProduit");
%>
<%@ include file="../static/header.jsp" %>
    <%@ include file="../static/menuDeroulant.jsp" %>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row d-flex justify-content-center">
                    <div class="col-md-6 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Vendre un produit</h4>
                                <p class="card-description">
                                    Vente
                                </p>
                                <form class="forms-sample" method="post" action="/produit/venteProduit">
                                    <div class="form-group">
                                        <label for="inputProduit">Produit</label>
                                        <select name="id_produit" id="inputProduit" class="form-control">
                                            <option value="">
                                                Choisir un produit
                                            </option>
                                            <%
                                                for(int i=0; i<listeProduit.size(); i++) {
                                                    %>
                                                    <option value=<% out.println(listeProduit.get(i).getIdProduit()); %>>
                                                        <% 
                                                            out.println(listeProduit.get(i).getNomProduit()); 
                                                        %>
                                                    </option>
                                                <% }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputDateVente">Date de vente</label>
                                        <input 
                                            type="date" 
                                            name="date_vente_produit" 
                                            class="form-control" 
                                            id="inputDateVente" 
                                            placeholder="Date de vente"
                                        />
                                    </div>
                                    <div class="form-group">
                                        <label for="inputQuantiteVente">Quantite : </label>
                                        <input 
                                            type="number" 
                                            name="quantite_vente_produit" 
                                            id="inputQuantiteVente" 
                                            class="form-control"
                                            placeholder="Quantite"
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