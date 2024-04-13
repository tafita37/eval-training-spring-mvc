<%@ include file="../static/cssPart.jsp" %>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
<%@page import="java.util.*"%>
<%@page import="eval.tsakitsaky.cinepax.model.stat.*"%>
<%
    List<VStatVenteEtudiant> listeStatVente = (List<VStatVenteEtudiant>) request.getAttribute("listeStatVente");
%>
<div class="main-panel">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Billets vendus</h4>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Quantite</th>
                                        <th>Type de billet</th>
                                        <th>Etudiant</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for(int i=0; i<listeStatVente.size(); i++) {
                                            %>
                                            <tr>
                                                <td>
                                                    <%
                                                        out.println(listeStatVente.get(i).getQuantiteVendu());
                                                    %>
                                                </td>
                                                <td>
                                                    <%
                                                        out.println(listeStatVente.get(i).getPack().getNomPack());
                                                    %>
                                                </td>
                                                <td>
                                                    <%
                                                        out.println(listeStatVente.get(i).getEtudiant().getPrenomEtudiant());
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