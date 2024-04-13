import { createRouter, createWebHistory } from 'vue-router';
import LoginAdmin from '../components/principal/LoginAdmin.vue';
import LoginStation from '../components/principal/LoginStation.vue';
import AjoutStockProduit from '../components/principal/AjoutStockProduit.vue';
import VenteProduit from '@/components/principal/VenteProduit.vue';
import NewProduit from '@/components/principal/NewProduit.vue';
import ListeProduit from '@/components/principal/ListeProduit.vue';
import ListeEntreeStock from '@/components/principal/ListeEntreeStock.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: LoginAdmin
    },
    {
      path: '/admin',
      name: 'admin',
      children : 
      [
        {
          path : "loginAdmin",
          name: 'loginAdmin',
          component: LoginAdmin
        }
      ]
    },
    {
      path: '/station',
      name: 'station',
      children : 
      [
        {
          path : "loginStation",
          name: 'loginStation',
          component: LoginStation
        }
      ]
    },
    {
      path : '/stock',
      name : 'stock',
      children : 
      [
        {
          path : 'ajoutStock',
          name : 'ajoutStock',
          component : AjoutStockProduit
        },
        {
          path : 'venteProduit',
          name : 'venteProduit',
          component : VenteProduit
        },
        {
          path : 'listeEntreeStock/:numPage',
          name : 'listeEntreeStock',
          component : ListeEntreeStock
        }
      ]
    },
    {
      path : '/produit',
      name : 'produit',
      children :
      [
        {
          path : 'newProduit',
          name : 'newProduit',
          component : NewProduit
        },
        {
          path : 'listeProduit/:numPage',
          name : 'listeProduit',
          component : ListeProduit
        }
      ]
    }
  ]
})

export default router
