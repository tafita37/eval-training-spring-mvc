<%@ include file="../static/cssPart.jsp" %>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
<%@page import="java.util.*" %>
<%@page import="eval.tsakitsaky.cinepax.model.produit.pack.*" %>
<%@ page import="java.text.*" %>
<% List<Pack> listePack = (List<Pack>) request.getAttribute("listePack");
        Locale locale = new Locale("fr", "FR");
        NumberFormat formatteur = NumberFormat.getInstance(locale);
        %>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Liste de pack</h4>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Nom</th>
                                                <th>Photo</th>
                                                <th>Cout de revient</th>
                                                <th>Prix de vente</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for(int i=0; i<listePack.size(); i++) { %>
                                                <tr>
                                                    <td>
                                                        <% out.println(listePack.get(i).getNomPack());
                                                            %>
                                                    </td>
                                                    <td>
                                                        <img src="<% out.println(
                                                            "../../../assets/images/pack/"+listePack.get(i).getPhotoPack()
                                                        );%>"
                                                        alt=""
                                                        srcset=""
                                                        />
                                                    </td>
                                                    <td>
                                                        <% out.println(
                                                            formatteur.format(listePack.get(i).getCoutRevientPack())+ " ar"
                                                            ); %>
                                                    </td>
                                                    <td>
                                                        <% out.println(
                                                            formatteur.format(listePack.get(i).getPrixVentePack())+ " ar"
                                                            ); %>
                                                    </td>
                                                    <td>
                                                        <i data-toggle="modal"
                                                            data-target="#detailPack<%= listePack.get(i).getIdPack() %>"
                                                            class="fas fa-edit modif"></i>
                                                    </td>
                                                    <td>
                                                        <a href="<% out.println("/ventePack/pack/deletePackById/"+listePack.get(i).getIdPack()); %>">
                                                            <i class="fas fa-trash corbeille"></i>
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <a href="<% out.println("/ventePack/formulePack/"+listePack.get(i).getIdPack()); %>">
                                                            Formule
                                                        </a>
                                                    </td>
                                                </tr>
                                                <div class="modal fade"
                                                    id="detailPack<%= listePack.get(i).getIdPack() %>"
                                                    tabindex="-1" role="dialog"
                                                    aria-labelledby="exampleModalLabel"
                                                    aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title"
                                                                    id="exampleModalLabel">
                                                                    Modification du pack <%
                                                                        out.println(listePack.get(i).getNomPack());
                                                                        %>
                                                                </h5>
                                                                <button type="button"
                                                                    class="close"
                                                                    data-dismiss="modal"
                                                                    aria-label="Close">
                                                                    <span
                                                                        aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <form class="forms-sample"
                                                                method="post"
                                                                action="/ventePack/pack/updatePack"
                                                                enctype="multipart/form-data">
                                                                <div class="modal-body">
                                                                    <input 
                                                                        type="hidden" 
                                                                        name="idPack" 
                                                                        value=<% out.println(listePack.get(i).getIdPack()); %>
                                                                    />
                                                                    <div class="form-group">
                                                                        <label
                                                                            for="inputNomPack">Nom</label>
                                                                        <input type="text"
                                                                            name="nomPack"
                                                                            id="inputNomPack"
                                                                            class="form-control"
                                                                            placeholder="Nom"
                                                                            value="<% out.println(listePack.get(i).getNomPack()); %>" />
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label
                                                                            for="inputPrixVente">Prix
                                                                            de vente</label>
                                                                        <input type="number"
                                                                            name="prixVente"
                                                                            id="inputPrixVente"
                                                                            class="form-control"
                                                                            placeholder="Prix de vente"
                                                                            value=<%
                                                                            out.println(
                                                                            listePack.get(i).getPrixVentePack()
                                                                            ); %>
                                                                        />
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>Photo : </label>
                                                                        <input type="file"
                                                                            name="photoPack"
                                                                            class="file-upload-default" />
                                                                        <div
                                                                            class="input-group col-xs-12">
                                                                            <input type="text"
                                                                                class="form-control file-upload-info"
                                                                                disabled
                                                                                placeholder="Photo de pack"
                                                                                value="<% out.println(
                                                                                            listePack.get(i).getPhotoPack()
                                                                                        ); %>" 
                                                                            />
                                                                            <span
                                                                                class="input-group-append">
                                                                                <button
                                                                                    class="file-upload-browse btn btn-primary"
                                                                                    type="button">
                                                                                    <i class="mdi mdi-paperclip"
                                                                                        style="font-size: 0.9rem;"></i>
                                                                                </button>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button"
                                                                        class="btn btn-secondary"
                                                                        data-dismiss="modal">
                                                                        Fermer
                                                                    </button>
                                                                    <button type="submit"
                                                                        class="btn btn-primary">
                                                                        Sauvegarder les changements
                                                                    </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                                <% } %>
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
        <%@ include file="../static/jsPart.jsp" %>
            </body>

            </html>