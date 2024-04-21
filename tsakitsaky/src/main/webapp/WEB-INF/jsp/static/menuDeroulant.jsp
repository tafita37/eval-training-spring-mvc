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
            <a class="nav-link" href="/ventePack/formVentePack">
              Nouvelle vente
            </a>
          </li>
          <li class="nav-item"> 
            <a class="nav-link" href="/ventePack/statVenteByEtudiant">
              Stat de vente
            </a>
          </li>
          <li class="nav-item"> 
            <a class="nav-link" href="/ventePack/listePack">
              Liste des pack
            </a>
          </li>
          <li class="nav-item"> 
            <a class="nav-link" href="/ventePack/formInsertPack">
              Nouveau pack
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/ventePack/listeNbVenteMontant">
              Stat Vente/pack
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/ventePack/getStatVenteCamembertPPack">
              Camembert
            </a>
          </li>
        </ul>
      </div>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="collapse" href="#paiement" aria-expanded="false" aria-controls="paiement">
        <i class="icon-layout menu-icon"></i>
        <span class="menu-title">Paiement</span>
        <i class="menu-arrow"></i>
      </a>
      <div class="collapse" id="paiement">
        <ul class="nav flex-column sub-menu">
          <li class="nav-item"> 
            <a class="nav-link" href="/paiement/formPaiementVente">
              Faire un paiement
            </a>
            <a class="nav-link" href="/paiement/etatPaiementBEtudiant">
              Etat de paiement
            </a>
          </li>
        </ul>
      </div>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="collapse" href="#livraison" aria-expanded="false" aria-controls="livraison">
        <i class="icon-layout menu-icon"></i>
        <span class="menu-title">Livraison</span>
        <i class="menu-arrow"></i>
      </a>
      <div class="collapse" id="livraison">
        <ul class="nav flex-column sub-menu">
          <li class="nav-item"> 
            <a class="nav-link" href="/livraison/etatLivraison">
              Etat de livraison
            </a>
        </ul>
      </div>
    </li>
  </ul>
</nav>