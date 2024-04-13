<script setup>
    import { onMounted, reactive, ref } from 'vue';
    import axios from 'axios';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import TitleListe from '../reuse/TitleListe.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';
    import { useRoute } from 'vue-router';
    import ButtonListe from '../reuse/ButtonListe.vue';

    const route=useRoute();
    const idPointVente=route.query.idPointVente;
    const allUserOfPdv=reactive({});
    const pointVente=reactive({});
    const titleListe=ref("");

    const getAllUserOfPdv = () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/pdv/user/allUserOfPointVente?idPointVente='+idPointVente;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = axios.get(urlApiSpringBoot, {headers});
            response.then(reponse => {
                Object.assign(allUserOfPdv, reponse.data);
            }).catch(error => {
                if(error.response.status==403) {
                    console.log(headers);
                    // router.push("/");
                } else {
                    console.error(error);
                }
            })
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            } else {
                console.error(error);
            }
        }
    };

    const getPdvById = () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/pdv/findPointVenteById?idPointVente='+idPointVente;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                Object.assign(pointVente, reponse.data);
                titleListe.value=pointVente.data.nomPointVente;
            }).catch(error => {
                if(error.response.status==403) {
                    router.push("/");
                } else {
                    console.error(error);
                }
            })
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            } else {
                console.error(error);
            }
        }
    };

    const getTitleListe = () => {
        return "Liste des utilisateurs dans "+titleListe.value;
    }

    const loadFormModif = (idUser) => {
        router.push({path : "/modifUser", query: {idUser : idUser}});
    }

    const loadChangePdv = (idUser, idPointVente) => {
        router.push({path : "/changePdvOfUser", query: {idUser : idUser, idPointVente : idPointVente}});
    }

    const deleteUserById = (idUser) => {
        const responseDelete={};
        try {
            const urlApiSpringBoot = 'http://localhost:8080/pdv/user/deleteUserById?idUser='+idUser;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                Object.assign(responseDelete, reponse.data);
                console.log(responseDelete);
            }).catch(error => {
                if(error.response.status==403) {
                    router.push("/");
                } else {
                    console.error(error);
                }
            })
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            } else {
                console.error(error);
            }
        }
    }

    const deleteUserOfPdvById = (idUser) => {
        const responseDelete={};
        try {
            const urlApiSpringBoot = 'http://localhost:8080/pdv/user/deleteUserOfPdv?idUser='+idUser;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                Object.assign(responseDelete, reponse.data);
                console.log(responseDelete);
            }).catch(error => {
                if(error.response.status==403) {
                    router.push("/");
                } else {
                    console.error(error);
                }
            })
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            } else {
                console.error(error);
            }
        }
    }

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {    
            getPdvById();
            getAllUserOfPdv();
        }
    });
</script>
<template>
    <TitleListe :title-liste="getTitleListe()"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(user)  in allUserOfPdv.data" :key="user.idUser">
            <H5TitleListe :title-h5="user.idUser" labels="Numero"/>
            <SpanListe :title-span="user.nomUser" labels="Nom"/>
            <SpanListe :title-span="user.prenomUser" labels="Prenom"/>
            <SpanListe :title-span="user.emailUser" labels="Email"/>
            <ModifSupprButton :funct-modif="() => loadFormModif(user.idUser)" :funct-delete="() => deleteUserById(user.idUser)"/>
            <ButtonListe contain-button="Changer de point de vente" bg="blue" :event-click="() => loadChangePdv(user.idUser, idPointVente)"/>
            <ButtonListe contain-button="Retirer du point de vente" bg="red" :event-click="() => deleteUserOfPdvById(user.idUser) "/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>