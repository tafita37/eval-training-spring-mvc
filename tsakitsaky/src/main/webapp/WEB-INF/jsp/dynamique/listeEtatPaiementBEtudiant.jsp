<%@ include file="../static/cssPart.jsp" %>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
<%@page import="java.util.*" %>
<%@page import="eval.tsakitsaky.cinepax.model.paiement.*" %>
<%@ page import="java.text.*" %>
<% 
    List<VEtatPaiementBEtudiant> listeEtatPaiement=(List<VEtatPaiementBEtudiant>) request.getAttribute("listeEtatPaiement");
    Locale locale = new Locale("fr", "FR");
    NumberFormat formatteur = NumberFormat.getInstance(locale);
%>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Etat de paiement/Etudiant</h4>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Etudiant</th>
                                                <th>Montant Recu</th>
                                                <th>Reste a payer</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for(int i=0; i<listeEtatPaiement.size(); i++) {
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <%
                                                                out.println(
                                                                    listeEtatPaiement.get(i).getEtudiant().getPrenomEtudiant()
                                                                );
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(
                                                                    formatteur.format(
                                                                        listeEtatPaiement.get(i).getMontantPayer()
                                                                    )+" ar"
                                                                );
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(
                                                                    formatteur.format(
                                                                        listeEtatPaiement.get(i).getRestePayer()
                                                                    )+" ar"
                                                                );
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