<%@ include file="../static/cssPart.jsp" %>
    <body>
        <div class="container-scroller">
            <div class="container-fluid page-body-wrapper full-page-wrapper">
                <div class="content-wrapper d-flex align-items-center auth px-0">
                    <div class="row w-100 mx-0">
                        <div class="col-lg-4 mx-auto">
                            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                                <div class="brand-logo">
                                    <img src="../../images/logo.svg" alt="logo">
                                </div>
                                <h4>Se connecter en tant que etudiant</h4>
                                <form class="pt-3" method="post" action="/loginInscription/loginEtudiant">
                                    <div class="form-group">
                                        <input 
                                            type="email" 
                                            class="form-control form-control-lg" 
                                            id="exampleInputEmail1"
                                            placeholder="Email"
                                            name="emailEtudiant"
                                            value="taf.rand37@gmail.com"
                                        />
                                    </div>
                                    <div class="form-group">
                                        <input 
                                            type="password" 
                                            class="form-control form-control-lg"
                                            id="exampleInputPassword1" 
                                            placeholder="Password"
                                            name="mdpEtudiant"
                                            value="0000"
                                        />
                                    </div>
                                    <div class="mt-3">
                                        <button 
                                            type="submit" 
                                            class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
                                        >
                                            SIGN IN
                                        </button>
                                    </div>
                                    <div class="text-center mt-4 font-weight-light">
                                        Don't have an account? <a href="register.html" class="text-primary">Create</a>
                                    </div>
                                    <div class="text-center mt-4 font-weight-light">
                                        Login admin 
                                        <a href="/loginInscription/formLoginAdminUser" class="text-primary">
                                            Se connecter
                                        </a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- content-wrapper ends -->
            </div>
            <!-- page-body-wrapper ends -->
        </div>
        <%@ include file="../static/jsPart.jsp" %>
    </body>

    </html>