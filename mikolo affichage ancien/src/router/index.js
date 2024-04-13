import AboutViewVue from '@/views/AboutView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import NewMarque from '../views/NewMarque.vue'
import NewProcesseur from '../views/NewProcesseur.vue'
import NewTypeRam from '../views/NewTypeRam.vue'
import NewTypeDisque from '../views/NewTypeDisque.vue'
import NewDisque from '../views/NewDisque.vue'
import NewRam from '../views/NewRam.vue'
import NewLaptop from '../views/NewLaptop.vue'
import ListeLaptop from '../views/ListeLaptop.vue'
import ListeMarque from '../views/ListeMarque.vue'
import ListeProcesseur from '../views/ListeProcesseur.vue'
import ListeRam from '../views/ListeRam.vue'
import ListeDisque from '../views/ListeDisque.vue'
import Login from '../views/Login.vue';
import LoginPointVente from '../views/LoginPointVente.vue';
import ModifDisque from '../views/ModifDisque.vue';
import ModifMarque from '../views/ModifMarque.vue';
import ModifProcesseur from '../views/ModifProcesseur.vue';
import ModifRam from '../views/ModifRam.vue';
import ModifLaptop from '../views/ModifLaptop.vue';
import NewUtilisateur from '../views/NewUtilisateur.vue';
import NewPdv from '../views/NewPdv.vue';
import NewPdvUser from '../views/NewPdvUser.vue';
import TransfertLaptop from '../views/TransfertLaptop.vue';
import AjoutStock from '@/views/AjoutStock.vue'
import ReceptionLaptop from '@/views/ReceptionLaptop.vue'
import VenteLaptop from '@/views/VenteLaptop.vue'
import ListePointVente from '@/views/ListePointVente.vue'
import ModifPointVente from '@/views/ModifPointVente.vue'
import ListeUserPdv from '@/views/ListeUserPdv.vue'
import ModifUser from '@/views/ModifUser.vue'
import ModifUserFromPointVente from '@/views/ModifUserFromPointVente.vue'
import ListeResteStockMagasin from '@/views/ListeResteStockMagasin.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login_register',
      component: Login
    },
    {
      path: '/loginRegister',
      name: 'register_login',
      component: Login
    },
    {
      path: '/listeLaptop',
      name: 'listeLaps',
      component: ListeLaptop
    },
    {
      path: '/about',
      name: 'about',
      component: AboutViewVue
    },
    {
      path: '/newMarque',
      name: 'newMarque',
      component: NewMarque
    },
    {
      path: '/newProcesseur',
      name: 'newProcesseur',
      component: NewProcesseur
    },
    {
      path: '/newTypeRam',
      name: 'newTypeRam',
      component: NewTypeRam
    },
    {
      path: '/newRam',
      name: 'newRam',
      component: NewRam
    },
    {
      path: '/newTypeDisque',
      name: 'newTypeDisque',
      component: NewTypeDisque
    },
    {
      path: '/newDisque',
      name: 'newDisque',
      component: NewDisque
    },
    {
      path: '/newLaptop',
      name: 'newLaptop',
      component: NewLaptop
    },
    {
      path: '/allMarque',
      name: 'allMarque',
      component: ListeMarque
    },
    {
      path: '/allProcesseur',
      name: 'allProcesseur',
      component: ListeProcesseur
    },
    {
      path: '/allRam',
      name: 'allRam',
      component: ListeRam
    },
    {
      path: '/allDisque',
      name: 'allDisque',
      component: ListeDisque
    },
    {
      path: '/modifDisque',
      name: 'modifDisque',
      component: ModifDisque
    },
    {
      path: '/modifMarque',
      name: 'modifMarque',
      component: ModifMarque
    },
    {
      path: '/modifProcesseur',
      name: 'modifProcesseur',
      component: ModifProcesseur
    },
    {
      path: '/modifRam',
      name: 'modifRam',
      component: ModifRam
    },
    {
      path: '/modifLaptop',
      name: 'modifLaptop',
      component: ModifLaptop
    },
    {
      path: '/newUser',
      name: 'newUser',
      component: NewUtilisateur
    },
    {
      path: '/newPDV',
      name: 'newPDV',
      component: NewPdv
    },
    {
      path: '/newPDVUser',
      name: 'newPDVUser',
      component: NewPdvUser
    },
    {
      path: '/ajoutStock',
      name: 'ajoutStock',
      component: AjoutStock
    },
    {
      path: '/transfertLaptop',
      name: 'transfertLaptop',
      component: TransfertLaptop
    },
    {
      path: '/loginPdv',
      name: 'loginPdv',
      component: LoginPointVente
    },
    {
      path: '/receptionLaptop',
      name: 'receptionLaptop',
      component: ReceptionLaptop
    },
    {
      path: '/venteLaptop',
      name: 'venteLaptop',
      component: VenteLaptop
    },
    {
      path: '/listePointVente',
      name: 'listePointVente',
      component: ListePointVente
    },
    {
      path: '/modifPointvente',
      name: 'modifPointvente',
      component: ModifPointVente
    },
    {
      path: '/listeUserOfPdv',
      name: 'listeUserOfPdv',
      component: ListeUserPdv
    },
    {
      path: '/modifUser',
      name: 'modifUser',
      component: ModifUser
    },
    {
      path: '/changePdvOfUser',
      name: 'changePdvOfUser',
      component: ModifUserFromPointVente
    },
    {
      path: '/listeResteMagasin',
      name: 'listeResteMagasin',
      component: ListeResteStockMagasin
    }
  ]
})

export default router
