<%@ include file="../static/cssPart.jsp" %>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
<%@page import="java.util.*" %>
<%@page import="eval.tsakitsaky.cinepax.model.produit.pack.*" %>
<%@ page import="java.text.*" %>
<%@ page import="eval.tsakitsaky.cinepax.model.produit.pack.*" %>
<%@ page import="eval.tsakitsaky.cinepax.model.produit.*"%>
<% 
    Locale locale = new Locale("fr", "FR");
    NumberFormat formatteur = NumberFormat.getInstance(locale);
    List<PackFormule> listePackFormule = (List<PackFormule>) request.getAttribute("listeFormule");
    List<Produit> listeProduit = (List<Produit>) request.getAttribute("listeProduit");
    int idPack=(int) request.getAttribute("idPack");
%>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Formule pour le pack</h4>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>
                                                    Produit
                                                </th>
                                                <th>
                                                    Quantite                                                    
                                                </th>
                                                <th>
                                                    Cout de revient
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for(int i=0; i<listePackFormule.size(); i++) {
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <%
                                                                out.println(listePackFormule.get(i).getProduit().getNomProduit());
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(
                                                                    formatteur.format(
                                                                        listePackFormule.get(i).getQuantiteProduit()
                                                                    )
                                                                );
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(
                                                                    formatteur.format(
                                                                        listePackFormule.get(i).getCoutRevientFormule()
                                                                    )
                                                                );
                                                            %>
                                                        </td>
                                                        <td>
                                                            <i 
                                                                data-toggle="modal" 
                                                                class="fas fa-edit modif" 
                                                                data-target="#detailPack<%= listePackFormule.get(i).getIdPackFormule() %>"
                                                            ></i>
                                                        </td>
                                                        <td>
                                                            <a href="<% out.println("/ventePack/pack/deletePackFormuleById/"+listePackFormule.get(i).getIdPackFormule()); %>">
                                                                <i class="fas fa-trash corbeille"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                    <div 
                                                        class="modal fade"
                                                        id="detailPack<%= listePackFormule.get(i).getIdPackFormule() %>"
                                                        tabindex="-1" 
                                                        role="dialog"
                                                        aria-labelledby="exampleModalLabel"
                                                        aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title"
                                                                        id="exampleModalLabel">
                                                                        Modification 
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
                                                                    action="/ventePack/pack/updatePackFormule"
                                                                    enctype="multipart/form-data">
                                                                    <div class="modal-body">
                                                                        <input 
                                                                            type="hidden" 
                                                                            name="idPackFormule" 
                                                                            value=<% out.println(listePackFormule.get(i).getIdPackFormule()); %>
                                                                        />
                                                                        <div class="form-group">
                                                                            <label
                                                                                for="inputQuantiteProduit">Quantite : </label>
                                                                            <input 
                                                                                type="number"
                                                                                name="quantiteProduit"
                                                                                id="inputQuantiteProduit"
                                                                                class="form-control"
                                                                                placeholder="Quantite"
                                                                                value=<% out.println(listePackFormule.get(i).getQuantiteProduit()); %>
                                                                            />
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
                                                <% }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button data-toggle="modal" data-target="#newPackFormule" type="submit" class="btn btn-primary mr-2">
                    Rajouter
                </button>
                <div class="modal fade" id="newPackFormule">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title"
                                    id="exampleModalLabel">
                                    Rajouter une formule 
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
                                action="/ventePack/pack/insertPackFormule"
                                enctype="multipart/form-data">
                                <div class="modal-body">
                                    <input 
                                        type="hidden" 
                                        name="idPack"
                                        value=<% out.println(idPack); %> 
                                    />
                                    <div class="form-group">
                                        <select name="idProduit" id="" class="form-control">
                                            <option value="">
                                                Choisissez un produit
                                            </option>
                                            <%
                                                for(int i=0; i<listeProduit.size(); i++) {
                                                    %>
                                                    <option value=<% out.println(listeProduit.get(i).getIdProduit()); %>>
                                                        <% out.println(listeProduit.get(i).getNomProduit()); %>
                                                    </option>
                                                <% }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label
                                            for="inputQuantiteProduit">Quantite : </label>
                                        <input 
                                            type="number"
                                            name="quantiteProduit"
                                            id="inputQuantiteProduit"
                                            class="form-control"
                                            placeholder="Quantite"
                                        />
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