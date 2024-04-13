<script setup>
  import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <ul class="header_info">
    <li>
      <span class="fa fa-user"></span>
      <RouterLink to="/">Magasin</RouterLink>
    </li>
    <li>
      <span class="fa fa-user"></span>
      <RouterLink to="/loginPdv">Point de vente</RouterLink>
    </li>
  </ul>
  <div class="sup">
    <ul class="header">
      <li>
        <RouterLink to="/allMarque">MARQUES</RouterLink>
      </li>
      <li>
        <RouterLink to="/allProcesseur">PROCESSEUR</RouterLink>
      </li>
      <li>
        <RouterLink to="/allRam">RAM</RouterLink>
      </li>
      <li>
        <RouterLink to="/allDisque">DISQUE DUR</RouterLink>
      </li>
      <li>
        <RouterLink to="/listeLaptop">LAPTOPS</RouterLink>
      </li>
      <li>
        <RouterLink to="/listePointVente">POINTS DE VENTES</RouterLink>
      </li>
      <li>
        <RouterLink to="/listeResteMagasin">RESTE STOCK</RouterLink>
      </li>
    </ul>
  </div>
  <RouterView />
</template>

<style scoped>
  .header_info {
    background-color: #120851;
    width: 100%;
    margin: 0;
    color: white;
    line-height: 2.8;
    display: flex;
    align-items: center;
  }

  .header_info li {
    margin-left: 30px;
  }

  .sup {
    background-color: #120851;
  }

  .header {
    color: white;
    margin: 1px;
    width: 60%;
    top: 40px;
    display: flex;
    line-height: 3.8;
    align-items: center;
    justify-content: space-between;
  }

  ul {
    width: 100%;
  }

  li {
    list-style: none;
  }
</style>
