<nav class="sidebar sidebar-offcanvas" id="sidebar">
  <ul class="nav">
    <li class="nav-item">
      <a class="nav-link" href="../../index.html">
        <i class="icon-grid menu-icon"></i>
        <span class="menu-title">Dashboard</span>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="collapse" href="#vente" aria-expanded="false" aria-controls="vente">
        <i class="icon-layout menu-icon"></i>
        <span class="menu-title">Vente de billets</span>
        <i class="menu-arrow"></i>
      </a>
      <div class="collapse" id="vente">
        <ul class="nav flex-column sub-menu">
          <li class="nav-item"> 
            <a class="nav-link" href="/">
              Nouvelle vente
            </a>
          </li>
          <li class="nav-item"> 
            <a class="nav-link" href="/billet/listeVenteBillet">
              Liste des ventes
            </a>
          </li>
          <li class="nav-item"> 
            <a class="nav-link" href="/billet/getStatistiqueVenteBFilm">
              Statistique
            </a>
          </li>
        </ul>
      </div>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="collapse" href="#venteProduits" aria-expanded="false" aria-controls="venteProduits">
        <i class="icon-layout menu-icon"></i>
        <span class="menu-title">Vente de produits</span>
        <i class="menu-arrow"></i>
      </a>
      <div class="collapse" id="venteProduits">
        <ul class="nav flex-column sub-menu">
          <li class="nav-item"> 
            <a class="nav-link" href="/produit/formVenteProduit">
              Nouvelle vente
            </a>
          </li>
        </ul>
      </div>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="collapse" href="#place" aria-expanded="false" aria-controls="place">
        <i class="icon-layout menu-icon"></i>
        <span class="menu-title">Seances</span>
        <i class="menu-arrow"></i>
      </a>
      <div class="collapse" id="place">
        <ul class="nav flex-column sub-menu">
          <li class="nav-item"> 
            <a class="nav-link" href="/billet/formNewBillet">
              Nouvelle seance
            </a>
          </li>
          <li class="nav-item"> 
            <a class="nav-link" href="/billet/place/listePlaceDispoBySeance">
              Liste des places
            </a>
          </li>
        </ul>
      </div>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="collapse" href="#ca" aria-expanded="false" aria-controls="ca">
        <i class="icon-layout menu-icon"></i>
        <span class="menu-title">Chiffre d'affaire</span>
        <i class="menu-arrow"></i>
      </a>
      <div class="collapse" id="ca">
        <ul class="nav flex-column sub-menu">
          <li class="nav-item"> 
            <a class="nav-link" href="/affaire/chiffreAffaire">
              Statistique
            </a>
          </li>
        </ul>
      </div>
    </li>
  </ul>
</nav>