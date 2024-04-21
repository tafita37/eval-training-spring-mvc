<%@page import="java.util.*"%>
<%@page import="eval.tsakitsaky.cinepax.model.produit.pack.*"%>
<%@page import="eval.tsakitsaky.cinepax.model.axe.*"%>
<%@ include file="../static/cssPart.jsp" %>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
<% 
    List<Pack> listePack=(List<Pack>) request.getAttribute("listePack");
    List<Client> listeClient = (List<Client>) request.getAttribute("listeClient");
%>
                    <div class="main-panel">
                        <div class="content-wrapper">
                            <div class="row d-flex justify-content-center">
                                <div class="col-md-6 grid-margin stretch-card">
                                    <div class="card">
                                        <div class="card-body">
                                            <h4 class="card-title">Vendre un pack</h4>
                                            <p class="card-description">
                                                Vente
                                            </p>
                                            <form class="forms-sample" method="post" action="/ventePack/vendrePack">
                                                <div class="form-group">
                                                    <label for="inputPack">Pack</label>
                                                    <select name="idPack" id="inputPack" class="form-control">
                                                        <option value="">
                                                            Choisir un pack
                                                        </option>
                                                        <%
                                                            for(int i=0; i<listePack.size(); i++) {
                                                                %>
                                                                <option 
                                                                    value=<% out.println(listePack.get(i).getIdPack()); %>
                                                                >
                                                                    <% out.println(listePack.get(i).getNomPack()); %>
                                                                </option>
                                                            <% }
                                                        %>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label for="inputClient">Client</label>
                                                    <select name="idClient" id="inputClient" class="form-control">
                                                        <option value="">
                                                            Choisir un client
                                                        </option>
                                                        <%
                                                            for(int i=0; i<listeClient.size(); i++) {
                                                                %>
                                                                <option 
                                                                    value=<% out.println(listeClient.get(i).getIdClient()); %>
                                                                >
                                                                    <% out.println(listeClient.get(i).getNomAfficher()); %>
                                                                </option>
                                                            <% }
                                                        %>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label for="inputQuantite">Quantite</label>
                                                    <input 
                                                        type="number" 
                                                        name="quantiteVenteBillet" 
                                                        id="inputQuantite" 
                                                        class="form-control"
                                                        placeholder="Quantite"
                                                    />
                                                </div>
                                                <div class="form-group">
                                                    <label for="inputDateVente">Date de vente</label>
                                                    <input 
                                                        type="date" 
                                                        name="dateVenteBillet" 
                                                        class="form-control" 
                                                        id="inputDateVente" 
                                                        placeholder="Date et heure de debut"
                                                    />
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
                    <%@ include file="../static/jsPart.jsp" %>
                        </body>

                        </html>