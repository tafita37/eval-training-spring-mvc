<%@ include file="../static/cssPart.jsp" %>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
<%@page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="eval.tsakitsaky.cinepax.model.paiement.livraison.*" %>
<% 
    Locale locale = new Locale("fr", "FR");
    NumberFormat formatteur = NumberFormat.getInstance(locale);
    List<VEtatLivraison> listeEtat = (List<VEtatLivraison>) request.getAttribute("listeEtat");
%>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Etat de livraison</h4>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Axe</th>
                                                <th>Client</th>
                                                <th>Contact</th>
                                                <th>Vendeur</th>
                                                <th>Nombre de pack</th>
                                                <th>Montant total</th>
                                                <th>Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for(int i=0; i<listeEtat.size(); i++) {
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <%
                                                                out.println(listeEtat.get(i).getAxe().getNomAfficher());
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(listeEtat.get(i).getClient().getNomClient());
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(listeEtat.get(i).getClient().getContactClient());
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(listeEtat.get(i).getEtudiant().getPrenomEtudiant());
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(formatteur.format(listeEtat.get(i).getNbPack()));
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(
                                                                    formatteur.format(listeEtat.get(i).getMontantTotal())+
                                                                    " ar"
                                                                );
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(listeEtat.get(i).getDateVenteBillet());
                                                            %>
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
                    <div class="card-body" style="display: flex;"> 
                        <a href="/livraison/exportPdf" class="btn btn-danger btn-icon-text">
                            <i class="ti-download btn-icon-prepend"></i>                                                     
                            Export pdf
                        </a>
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