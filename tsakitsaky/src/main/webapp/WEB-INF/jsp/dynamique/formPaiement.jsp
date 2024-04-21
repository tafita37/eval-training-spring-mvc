<%@page import="java.util.*"%>
<%@page import="eval.tsakitsaky.cinepax.model.vente.*"%>
<%@ include file="../static/cssPart.jsp" %>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
<%
    List<VenteBillet> listeVenteBillet = (List<VenteBillet>) request.getAttribute("listeVenteBillet");
%>
                    <div class="main-panel">
                        <div class="content-wrapper">
                            <div class="row d-flex justify-content-center">
                                <div class="col-md-6 grid-margin stretch-card">
                                    <div class="card">
                                        <div class="card-body">
                                            <h4 class="card-title">Effectuer un paiement</h4>
                                            <p class="card-description">
                                                Paiement
                                            </p>
                                            <form class="forms-sample" method="post" action="/paiement/payerVente">
                                                <div class="form-group">
                                                    <label for="inputPack">Vente</label>
                                                    <select name="idVenteBillet" id="inputPack" class="form-control">
                                                        <option value="">
                                                            Choisir le numero de vente
                                                        </option>
                                                        <%
                                                            for(int i=0; i<listeVenteBillet.size(); i++) {
                                                                %>
                                                                <option value=<% out.println(listeVenteBillet.get(i).getIdVenteBillet()); %>>
                                                                    <%
                                                                        out.println(listeVenteBillet.get(i).getVenteBilletInputForm());
                                                                    %>
                                                                </option>
                                                            <% }
                                                        %>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label for="inputQuantite">Montant</label>
                                                    <input 
                                                        type="number" 
                                                        name="montantPayer" 
                                                        id="inputQuantite" 
                                                        class="form-control"
                                                        placeholder="Quantite"
                                                    />
                                                </div>
                                                <div class="form-group">
                                                    <label for="inputDateVente">Date de paiement</label>
                                                    <input 
                                                        type="date" 
                                                        name="datePaiement" 
                                                        class="form-control" 
                                                        id="inputDateVente" 
                                                        placeholder="Date et heure de debut"
                                                    />
                                                </div>
                                                <button type="submit" class="btn btn-primary mr-2">
                                                    Payer
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