<%@page import="eval.cinepax.cinepax.model.film.*" %>
<%@page import="eval.cinepax.cinepax.model.film.produit.*" %>
<%@ page import="java.text.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Locale" %>
<%@page import="java.sql.*" %>
<% 
    List<VChiffreAffaireBFilmJ> listeCAFilm = (List<VChiffreAffaireBFilmJ>) request.getAttribute("listeCAFilm");
    List<VChiffreAffaireBProduitJ> listeCAProduit = (List<VChiffreAffaireBProduitJ>) request.getAttribute("listeCAProduit");
    List<VNbVueBFilmJ> listeVueFilm = (List<VNbVueBFilmJ>) request.getAttribute("listeVueFilm");
    Locale locale = new Locale("fr", "FR");
    NumberFormat formatteur = NumberFormat.getInstance(locale);
    double caFilm=(double) request.getAttribute("ca_film");
    double caProduit=(double) request.getAttribute("ca_produit");
    double nbVueTotal=(double) request.getAttribute("nb_vue_total");
    Date date=(Date) request.getAttribute("date");
%>
<%@ include file="../static/header.jsp" %>
<%@ include file="../static/menuDeroulant.jsp" %>
                <div class="main-panel">
                    <input type="hidden" id="date" value="<% out.println(date); %>"/>
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-md-12 grid-margin">
                                <div class="row">
                                    <div class="col-12 col-xl-4">
                                        <div class="justify-content-start d-flex">
                                            <div>
                                                <form action="/affaire/chiffreAffaire"
                                                    method="get" class="d-flex">
                                                    <div class="form-group mx-1">
                                                        <input type="date" name="date"
                                                            id="" class="form-control">
                                                    </div>
                                                    <div class="form-group mx-1">
                                                        <button type="submit"
                                                            class="btn btn-primary mr-2">
                                                            Rechercher
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 grid-margin stretch-card">
                                <div class="card position-relative">
                                    <div class="card-body">
                                        <div class="row mb-3 border-bottom pb-4 pt-4">
                                            <div
                                                class="col-md-12 col-xl-3 d-flex flex-column justify-content-start"
                                            >
                                                <div class="ml-xl-4 mt-3">
                                                    <p class="card-title">
                                                        Chiffre d'affaire par film du <%
                                                            out.println(date); %>
                                                    </p>
                                                    <h1 class="text-primary">
                                                        <% out.println(formatteur.format(caFilm)+" ar"); %>
                                                    </h1>
                                                </div>
                                            </div>
                                            <div class="col-md-12 col-xl-9">
                                                <div class="row">
                                                    <div class="col-md-6 border-right">
                                                        <div
                                                            class="table-responsive mb-3 mb-md-0 mt-3">
                                                            <table
                                                                class="table table-borderless report-table">
                                                                <% for(int i=0;
                                                                    i<listeCAFilm.size();
                                                                    i++) { %>
                                                                    <tr>
                                                                        <td
                                                                            class="text-muted">
                                                                            <% out.println(
                                                                                listeCAFilm.get(i).
                                                                                getFilm().
                                                                                getNomFilm()
                                                                                ); %>
                                                                        </td>
                                                                        <td
                                                                            class="w-100 px-0">
                                                                            <div
                                                                                class="progress progress-md mx-4">
                                                                                <div class="progress-bar bg-primary"
                                                                                    role="progressbar"
                                                                                    style="width: <% out.println(listeCAFilm.get(i).getPourcentage()+"%");%>"
                                                                                    aria-valuenow="
                                                                                    <% out.println(listeCAFilm.get(i).getPourcentage());
                                                                                        %>
                                                                                        "
                                                                                        aria-valuemin="0"
                                                                                        aria-valuemax="100"
                                                                                        >
                                                                                </div>
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <h5
                                                                                class="font-weight-bold mb-0">
                                                                                <% out.println(listeCAFilm.get(i).getPourcentage()+"%");
                                                                                    %>
                                                                            </h5>
                                                                        </td>
                                                                    </tr>
                                                                    <% } %>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 mt-3">
                                                        <canvas
                                                            id="north-america-chart"></canvas>
                                                        <div id="north-america-legend">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row mb-3 border-bottom pb-4 pt-4">
                                            <div
                                                class="col-md-12 col-xl-3 d-flex flex-column justify-content-start">
                                                <div class="ml-xl-4 mt-3">
                                                    <p class="card-title">Chiffre
                                                        d'affaire par produit du <%
                                                            out.println(date); %>
                                                    </p>
                                                    <h1 class="text-primary">
                                                        <% out.println(formatteur.format(caProduit)+" ar"); %>
                                                    </h1>
                                                </div>
                                            </div>
                                            <div class="col-md-12 col-xl-9">
                                                <div class="row">
                                                    <div class="col-md-6 border-right">
                                                        <div
                                                            class="table-responsive mb-3 mb-md-0 mt-3">
                                                            <table
                                                                class="table table-borderless report-table">
                                                                <% for(int i=0;
                                                                    i<listeCAProduit.size();
                                                                    i++) { %>
                                                                    <tr>
                                                                        <td
                                                                            class="text-muted">
                                                                            <% out.println(
                                                                                listeCAProduit.get(i).
                                                                                getProduit().
                                                                                getNomProduit()
                                                                                ); %>
                                                                        </td>
                                                                        <td
                                                                            class="w-100 px-0">
                                                                            <div
                                                                                class="progress progress-md mx-4">
                                                                                <div class="progress-bar bg-primary"
                                                                                    role="progressbar"
                                                                                    style="width: <% out.println(listeCAProduit.get(i).getPourcentage()+"%");
                                                                                    %>"
                                                                                    aria-valuenow="
                                                                                    <% out.println(listeCAProduit.get(i).getPourcentage());
                                                                                        %>
                                                                                        "
                                                                                        aria-valuemin="0"
                                                                                        aria-valuemax="100"
                                                                                        >
                                                                                </div>
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <h5
                                                                                class="font-weight-bold mb-0">
                                                                                <% out.println(listeCAProduit.get(i).getPourcentage()+"%");
                                                                                    %>
                                                                            </h5>
                                                                        </td>
                                                                    </tr>
                                                                    <% } %>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 mt-3">
                                                        <canvas
                                                            id="south-america-chart"></canvas>
                                                        <div id="south-america-legend">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row pb-4 pt-4">
                                            <div
                                                class="col-md-12 col-xl-3 d-flex flex-column justify-content-start">
                                                <div class="ml-xl-4 mt-3">
                                                    <p class="card-title">
                                                        Nombre de vues le  <% out.println(date); %>
                                                    </p>
                                                    <h1 class="text-primary">
                                                        <% out.println(formatteur.format(nbVueTotal)); %>
                                                    </h1>
                                                </div>
                                            </div>
                                            <div class="col-md-12 col-xl-9">
                                                <div class="row">
                                                    <div class="col-md-6 border-right">
                                                        <div class="table-responsive mb-3 mb-md-0 mt-3">
                                                            <table class="table table-borderless report-table">
                                                                <% for(int i=0; i<listeVueFilm.size(); i++) { %>
                                                                    <tr>
                                                                        <td
                                                                            class="text-muted">
                                                                            <% out.println(
                                                                                listeVueFilm.get(i).
                                                                                getFilm().
                                                                                getNomFilm()
                                                                                ); %>
                                                                        </td>
                                                                        <td
                                                                            class="w-100 px-0">
                                                                            <div
                                                                                class="progress progress-md mx-4">
                                                                                <div class="progress-bar bg-primary"
                                                                                    role="progressbar"
                                                                                    style="width: <% out.println(listeVueFilm.get(i).getPourcentage()+"%");%>"
                                                                                    aria-valuenow="
                                                                                    <% out.println(listeVueFilm.get(i).getPourcentage());
                                                                                        %>
                                                                                        "
                                                                                        aria-valuemin="0"
                                                                                        aria-valuemax="100"
                                                                                        >
                                                                                </div>
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <h5
                                                                                class="font-weight-bold mb-0">
                                                                                <% out.println(listeVueFilm.get(i).getPourcentage()+"%");
                                                                                    %>
                                                                            </h5>
                                                                        </td>
                                                                    </tr>
                                                                    <% } %>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 mt-3">
                                                        <canvas id="vue-film-chart"></canvas>
                                                        <div id="vue-film-legend">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <a class="carousel-control-prev"
                                            href="#detailedReports" role="button"
                                            data-slide="prev">
                                            <span class="carousel-control-prev-icon"
                                                aria-hidden="true"></span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                        <a class="carousel-control-next"
                                            href="#detailedReports" role="button"
                                            data-slide="next">
                                            <span class="carousel-control-next-icon"
                                                aria-hidden="true"></span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                        <!-- </div> -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- content-wrapper ends -->
                    <!-- partial:partials/_footer.html -->
                    <%@ include file="../static/footer.jsp" %>
                        <!-- partial -->
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
        <script src="/vendors/chart.js/Chart.min.js"></script>
        <script src="/vendors/datatables.net/jquery.dataTables.js"></script>
        <script
            src="/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
        <script src="/js/dataTables.select.min.js"></script>

        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="/js/off-canvas.js"></script>
        <script src="/js/hoverable-collapse.js"></script>
        <script src="/js/template.js"></script>
        <script src="/js/settings.js"></script>
        <script src="/js/todolist.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page-->
        <script src="/js/dashboard.js"></script>
        <script src="/js/Chart.roundedBarCharts.js"></script>
        <!-- End custom js for this page-->
    </body>
</html>