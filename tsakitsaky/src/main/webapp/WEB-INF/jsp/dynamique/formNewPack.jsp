<%@ include file="../static/cssPart.jsp" %>
    <%@ include file="../static/header.jsp" %>
        <%@ include file="../static/menuDeroulant.jsp" %>
            <%@page import="java.util.*" %>
                <%@page import="eval.tsakitsaky.cinepax.model.produit.*" %>
                    <% List<Produit> listeProduit = (List<Produit>) request.getAttribute("listeProduit");
                            %>
                            <div class="main-panel">
                                <div class="content-wrapper">
                                    <div class="row d-flex justify-content-center">
                                        <div class="col-md-6 grid-margin stretch-card">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h4 class="card-title">Creer un nouveau pack</h4>
                                                    <p class="card-description">
                                                        Insertion
                                                    </p>
                                                    <form 
                                                        class="forms-sample" 
                                                        method="post"
                                                        action="/ventePack/pack/creerPack" 
                                                        enctype="multipart/form-data"
                                                    >
                                                        <div class="form-group">
                                                            <label for="inputNomPack">Nom</label>
                                                            <input type="text" name="nomPack" id="inputNomPack"
                                                                class="form-control" placeholder="Nom" />
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="inputPrixVente">Prix de vente</label>
                                                            <input type="number" name="prixVente" id="inputPrixVente"
                                                                class="form-control" placeholder="Prix de vente" />
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Photo : </label>
                                                            <input type="file" name="photoPack" class="file-upload-default">
                                                            <div class="input-group col-xs-12">
                                                                <input type="text" class="form-control file-upload-info"
                                                                    disabled placeholder="Photo de pack" />
                                                                <span class="input-group-append">
                                                                    <button class="file-upload-browse btn btn-primary"
                                                                        type="button">
                                                                        <i class="mdi mdi-paperclip" style="font-size: 0.9rem;"></i>
                                                                    </button>
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <div id="produit">
                                                            <label for="selectProduit">Formule : </label>
                                                            <div class="form-group">
                                                                <div class="input-group">
                                                                    <select name="idProduit[]" id="selectProduit"
                                                                        class="form-control">
                                                                        <option value="">
                                                                            Choisissez un produit
                                                                        </option>
                                                                        <% for(int i=0; i<listeProduit.size(); i++) { %>
                                                                            <option value=<%
                                                                                out.println(listeProduit.get(i).getIdProduit());
                                                                                %>
                                                                                >
                                                                                <% out.println(listeProduit.get(i).getNomProduit());
                                                                                    %>
                                                                            </option>
                                                                            <% } %>
                                                                    </select>
                                                                    <input type="number" name="quantiteProduit[]"
                                                                        placeholder="Quantite"
                                                                        class="form-control ml-1" />
                                                                    <span class="input-group-append">
                                                                        <button class="btn btn-primary"
                                                                            onclick="ajouterPack(event)">
                                                                            Rajouter
                                                                        </button>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <button type="submit" class="btn btn-primary mr-2">
                                                            Inserer
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
                            <%@ include file="../static/jsPart.jsp" %>
                                </body>

                                </html>