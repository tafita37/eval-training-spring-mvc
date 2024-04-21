<%@ include file="../static/cssPart.jsp" %>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
<%@page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="eval.tsakitsaky.cinepax.model.vente.*" %>
<% 
    Locale locale = new Locale("fr", "FR");
    NumberFormat formatteur = NumberFormat.getInstance(locale);
    List<VNombreMontantVentePPack> listeNbVenteMontant = (List<VNombreMontantVentePPack>) request.getAttribute("listeNbVenteMontant");
%>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Nombre de vente et montant</h4>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Pack</th>
                                                <th>Quantite</th>
                                                <th>Prix de vente</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for(int i=0; i<listeNbVenteMontant.size(); i++) {
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <%
                                                                out.println(listeNbVenteMontant.get(i).getPack().getNomPack());
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(
                                                                    formatteur.format(
                                                                        listeNbVenteMontant.get(i).getQuantiteVenteBillet()
                                                                    )
                                                                );
                                                            %>
                                                        </td>
                                                        <td>
                                                            <%
                                                                out.println(
                                                                    formatteur.format(
                                                                        listeNbVenteMontant.get(i).getPrixVente()
                                                                    )+
                                                                    " ar"
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
                    <div class="card-body" style="display: flex;"> 
                        <a href="/ventePack/exportNbVenteMontantToExcel" class="btn btn-success btn-icon-text">
                            <i class="ti-download btn-icon-prepend"></i>                                                     
                            Export excel
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