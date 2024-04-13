import ListeLaptop from '@/components/principal/ListeLaptop.vue';
import { createRouter, createWebHistory } from 'vue-router';
import Login from '../components/principal/Login.vue';
import LoginUser from '../components/principal/LoginUser.vue';
import NewMarque from '../components/principal/NewMarque.vue';
import ListeMarque from '../components/principal/ListeMarque.vue';
import ModifMarque from '../components/principal/ModifMarque.vue';
import ListeProcesseur from '@/components/principal/ListeProcesseur.vue';
import NewProcesseur from '@/components/principal/NewProcesseur.vue';
import ModifProcesseur from '@/components/principal/ModifProcesseur.vue';
import ListeTypeRam from '@/components/principal/ListeTypeRam.vue';
import NewTypeRam from '@/components/principal/NewTypeRam.vue';
import ModifTypeRam from '@/components/principal/ModifTypeRam.vue';
import ListeRam from '@/components/principal/ListeRam.vue';
import NewRam from '@/components/principal/NewRam.vue';
import ModifRam from '@/components/principal/ModifRam.vue';
import ListeTypeDisque from '@/components/principal/ListeTypeDisque.vue';
import NewTypeDisque from '@/components/principal/NewTypeDisque.vue';
import ModifTypeDisque from '@/components/principal/ModifTypeDisque.vue';
import ListeDisque from '@/components/principal/ListeDisque.vue';
import NewDisque from '@/components/principal/NewDisque.vue';
import ModifDisque from '@/components/principal/ModifDisque.vue';
import NewLaptop from '@/components/principal/NewLaptop.vue';
import ModifLaptop from '@/components/principal/ModifLaptop.vue';
import NewUser from '@/components/principal/NewUser.vue';
import NewPdv from '@/components/principal/NewPdv.vue';
import AffectUserToPdv from '@/components/principal/AffectUserToPdv.vue';
import AjoutStockMagasin from '@/components/principal/AjoutStockMagasin.vue';
import TransfertLaptop from '@/components/principal/TransfertLaptop.vue';
import ReceptionLaptop from '@/components/principal/ReceptionLaptop.vue';
import RenvoieLaptop from '@/components/principal/RenvoieLaptop.vue';
import ReceptionRenvoieLaptop from '@/components/principal/ReceptionRenvoieLaptop.vue';
import VenteLaptop from '@/components/principal/VenteLaptop.vue';
import StatVenteMois from '@/components/principal/StatVenteMois.vue';
import StatBeneficeMois from '@/components/principal/StatBeneficeMois.vue';
import StatVenteLaptopParPdv from '@/components/principal/StatVenteLaptopParPdv.vue';
import ListeVenteLaptop from '@/components/principal/ListeVenteLaptop.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Login
    },
    {
      path: '/loginMagasin',
      name: 'loginMagasin',
      component: Login
    },
    {
      path: '/loginUser',
      name: 'loginMagasin',
      component: LoginUser
    },
    {
      path: '/laptop',
      children : [
        {
          path : 'listeLaptop/:numPage',
          name: 'listeLaptop',
          component : ListeLaptop
        },
        {
          path : 'newLaptop',
          name: 'newLaptop',
          component : NewLaptop
        },
        {
          path : 'modifLaptop/:idLaptop',
          name: 'modifLaptop',
          component : ModifLaptop
        },
        {
          path : 'ajoutStockCentral',
          name: 'ajoutStockCentral',
          component : AjoutStockMagasin
        },
        {
          path : 'statVenteMois',
          name : 'statVenteMois',
          component : StatVenteMois
        },
        {
          path : 'statVenteMoisPdv',
          name : 'statVenteMoisPdv',
          component : StatVenteLaptopParPdv
        },
        {
          path : 'statBeneficeMois',
          name : 'statBeneficeMois',
          component : StatBeneficeMois
        }
      ]
    },
    {
      path: '/marque',
      children : [
        {
          path : 'newMarque',
          name: 'newMarque',
          component : NewMarque
        },
        {
          path : 'listeMarque/:numPage',
          name: 'listeMarque',
          component : ListeMarque
        },
        {
          path : 'modifMarque/:idMarque',
          name: 'modifMarque',
          component : ModifMarque
        }
      ]
    }, 
    {
      path : '/processeur',
      children : [
        {
          path : 'listeProcesseur/:numPage',
          name : 'listeProcesseur',
          component : ListeProcesseur
        },
        {
          path : 'insertProcesseur',
          name : 'insertProcesseur',
          component : NewProcesseur
        },
        {
          path : 'modifProcesseur/:idProcesseur',
          name : 'modifProcesseur',
          component : ModifProcesseur
        }
      ]
    },
    {
      path : "/ram",
      children : [
        {
          path : 'typeRam',
          children : [
            {
              path : "listeTypeRam/:numPage",
              name : "listeTypeRam",
              component : ListeTypeRam
            },
            {
              path : "newTypeRam",
              name : "newTypeRam",
              component : NewTypeRam
            },
            {
              path : "modifTypeRam/:idTypeRam",
              name : "modifTypeRam",
              component : ModifTypeRam
            }
          ]
        },
        {
          path : 'listeRam/:numPage',
          name : 'listeRam',
          component : ListeRam
        },
        {
          path : 'insertRam',
          name : 'insertRam',
          component : NewRam
        },
        {
          path : 'modifRam/:idRam',
          name : 'modifRam',
          component : ModifRam
        }
      ]
    },
    {
      path : "/disque",
      children : [
        {
          path : 'typeDisque',
          children : [
            {
              path : "listeTypeDisque/:numPage",
              name : "listeTypeDisque",
              component : ListeTypeDisque
            },
            {
              path : "newTypeDisque",
              name : "newTypeDisque",
              component : NewTypeDisque
            },
            {
              path : "modifTypeDisque/:idTypeDisque",
              name : "modifTypeDisque",
              component : ModifTypeDisque
            }
          ]
        },
        {
          path : 'listeDisque/:numPage',
          name : 'listeDisque',
          component : ListeDisque
        },
        {
          path : 'insertDisque',
          name : 'insertDisque',
          component : NewDisque
        },
        {
          path : 'modifDisque/:idDisque',
          name : 'modifDisque',
          component : ModifDisque
        }
      ]
    },
    {
      path : "/pdv",
      children : [
        {
          path : 'user',
          children : [
            {
              path : 'newUser',
              name : 'newUser',
              component : NewUser
            }
          ]
        },
        {
          path : 'newPdv',
          name : 'newPdv',
          component : NewPdv
        },
        {
          path : 'affectUserToPdv',
          name : 'affectUserToPdv',
          component : AffectUserToPdv
        },
        {
          path : 'laptop',
          children : [
            {
              path : 'transfertLaptop',
              name : 'transfertLaptop',
              component : TransfertLaptop
            },
            {
              path : 'receptionLaptop',
              name : 'receptionLaptop',
              component : ReceptionLaptop
            },
            {
              path : 'renvoieLaptop',
              name : 'renvoieLaptop',
              component : RenvoieLaptop
            },
            {
              path : 'receptionRenvoieLaptop',
              name : 'receptionRenvoieLaptop',
              component : ReceptionRenvoieLaptop
            },
            {
              path : 'venteLaptop',
              name : 'venteLaptop',
              component : VenteLaptop
            },
            {
              path : 'listeVenteLaptop/:numPage',
              name : 'listeVenteLaptop',
              component : ListeVenteLaptop
            }
          ]
        }
      ]
    }
  ]
})

export default router
