<%@page import="java.util.*"%>
<%@page import="eval.cinepax.cinepax.model.film.*"%>
<%@page import="eval.cinepax.cinepax.exception.*"%>
<%
    List<Film> listeFilm=(List<Film>) request.getAttribute("listeFilm");
    List<Salle> listeSalle=(List<Salle>) request.getAttribute("listeSalle");
    ExcelException excelException = (ExcelException) request.getAttribute("excelError");
%>
        <%@ include file="../static/cssPart.jsp" %>
        <%@ include file="../static/header.jsp" %>
        <%@ include file="../static/menuDeroulant.jsp" %>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row d-flex justify-content-center">
                    <div class="col-md-6 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Creer une nouvelle seance</h4>
                                <p class="card-description">
                                    Insertion
                                </p>
                                <form class="forms-sample" method="post" action="/billet/creerBillet">
                                    <div class="form-group">
                                        <label for="inputFilm">Film</label>
                                        <select name="id_film" id="inputFilm" class="form-control">
                                            <option value="">
                                                Choisir un film
                                            </option>
                                            <%
                                                for(int i=0; i<listeFilm.size(); i++) {
                                                    %>
                                                    <option value=<% out.println(listeFilm.get(i).getIdFilm()); %>>
                                                        <% out.println(listeFilm.get(i).getNomFilm()); %>
                                                    </option>
                                                <% }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputSalle">Salle</label>
                                        <select name="id_salle" id="inputSalle" class="form-control">
                                            <option value="">
                                                Choisir une salle
                                            </option>
                                            <%
                                                for(int i=0; i<listeSalle.size(); i++) {
                                                    %>
                                                    <option value=<% out.println(listeSalle.get(i).getIdSalle()); %>>
                                                        <% out.println(listeSalle.get(i).getNomSalle()); %>
                                                    </option>
                                                <% }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputDateVente">Date et heure de debut</label>
                                        <input 
                                            type="datetime-local" 
                                            name="date_heure_billet" 
                                            class="form-control" 
                                            id="inputDateVente" 
                                            placeholder="Date et heure de debut"
                                        >
                                    </div>
                                    <button type="submit" class="btn btn-primary mr-2">
                                        Creer
                                    </button>
                                    <button type="button" class="btn btn-primary mr-2" data-target="#detailPack" data-toggle="modal">
                                        Importer excel
                                    </button>
                                </form>
                                <%
                                    if(excelException!=null) {
                                        %>                                     
                                        <div class="container mt-5">
                                            <div class="alert alert-danger" role="alert">
                                                <%
                                                    for(int i=0; i<excelException.getListeLineException().size(); i++) {
                                                        out.println(excelException.getListeLineException().get(i).getMessage()+"<br/>");
                                                    }
                                                %>
                                            </div>
                                        </div>  
                                    <% }
                                %>
                                <div 
                                    class="modal fade"
                                    id="detailPack"
                                    tabindex="-1" 
                                    role="dialog"
                                    aria-labelledby="exampleModalLabel"
                                    aria-hidden="true"
                                >
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">         
                                            <div class="card">
                                                <div class="card-body"> 
                                                    <h4 class="card-title">Excel</h4>
                                                    <p class="card-description">
                                                        Importation
                                                    </p>                                  
                                                    <form action="/billet/importSeanceExcel" method="post" class="col-md-6 mt-3 forms-sample" enctype="multipart/form-data">
                                                        <div class="form-group">
                                                            <input type="file" name="file" class="file-upload-default">
                                                            <div class="input-group col-xs-12">
                                                                <input type="text" class="form-control file-upload-info"
                                                                    disabled placeholder="Excel" />
                                                                <span class="input-group-append">
                                                                    <button class="file-upload-browse btn btn-primary"
                                                                        type="button">
                                                                        <i class="mdi mdi-paperclip" style="font-size: 0.9rem;"></i>
                                                                    </button>
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-primary mr-2">
                                                                Uploader
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                
                                </div>
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