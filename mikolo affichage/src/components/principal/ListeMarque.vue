<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FooterOne from '../reeuse/FooterOne.vue';
    import H4CardTitle from '../reeuse/H4CardTitle.vue';
    import CardDescription from '../reeuse/CardDescription.vue';
    import TableHead from '../reeuse/TableHead.vue';
    import TableContain from '../reeuse/TableContain.vue';
    import axios from 'axios';
    import { onMounted, reactive, ref, watch } from 'vue';
    import { useRoute } from 'vue-router';
    import { baseUrl } from '@/static/Constantes';
    import router from '@/router';
    import Pagination from '../reeuse/Pagination.vue';
    import SearchHeader from '../reeuse/SearchHeader.vue';
import NormalSearch from '../reeuse/NormalSearch.vue';

    const allMarque=reactive({});
    const nbPageMarque=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();
    const searchMarque=reactive({});

    const getAllMarque = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/marque/allMarque/'+numPage;
            // console.log(urlApiSpringBoot);
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allMarque, response.data);
            // console.log(allMarque);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const countNbPagesMarque = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/marque/nbPagesMarque';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageMarque, response.data);
            listeNumPages.splice(0, listeNumPages.length);
            for(var i=1; i<=nbPageMarque.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            console.log(error);
        }
    };

    const loadFormModif = (idMarque) => {
        router.push({
            name : "modifMarque",
            params : {
                idMarque : idMarque
            }
        })
    }

    const rechercheMarqueByName = () => {
        try {
            const numPage=route.params.numPage;
            const token = localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl + '/laptop/marque/findMarqueByName';
            const params=new URLSearchParams();
            params.append("nomMarque", searchMarque.nomMarque);
            params.append("numPage", numPage);
            const response = axios.post(urlApiSpringBoot, null, { params, headers });
            response.then(reponse => {
                Object.assign(allMarque, reponse.data);
            }).catch(error => {
                console.log(error);
            })
        } catch (error) {
            console.log(error);
        }
    }

    const countNbPagesMarqueRecherche = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/marque/nbPagesMarqueRecherche';
            const params=new URLSearchParams();
            params.append("nomMarque", searchMarque.nomMarque);
            const response = await axios.get(urlApiSpringBoot, { params, headers });
            Object.assign(nbPageMarque, response.data);
            listeNumPages.splice(0, listeNumPages.length);
            console.log(nbPageMarque);
            for(var i=1; i<=nbPageMarque.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            console.log(error);
        }
    };

    const deleteMarqueById = (idMarque) => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/marque/deleteMarqueById?idMarque='+idMarque;
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                alert(reponse.data.message);
                if(!searchMarque.nomMarque||searchMarque.nomMarque=='') {
                    getAllMarque();
                    countNbPagesMarque();
                } else {
                    rechercheMarqueByName();
                    countNbPagesMarqueRecherche();
                }
            }).catch(error => {
                console.log(error);
            })
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    }


    onMounted(() => {
        getAllMarque();
        countNbPagesMarque();
    }); 

    watch(() => route.params.numPage, () => {
        if(!searchMarque.nomMarque||searchMarque.nomMarque=='') {
            getAllMarque();
            countNbPagesMarque();
        } else {
            rechercheMarqueByName();
            countNbPagesMarqueRecherche();
        }
    });

    watch(() => searchMarque.nomMarque, () => {
        rechercheMarqueByName();
        countNbPagesMarqueRecherche();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne />
        <SousGeneralContain>
            <MenuDeroulantOne />
            <PrincipalContain>
                <SousPrincipal>
                    <div class="col-lg-6 grid-margin stretch-card mx-auto">
                        <div class="card">
                            <div class="card-body">
                                <!-- <NormalSearch/> -->
                                <NormalSearch place-holder-search="Rechercher une marque" 
                                :search-function="rechercheMarqueByName" 
                                :reactive-object="searchMarque" reactive-reference="nomMarque"/>
                                <H4CardTitle card-title="Marques"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead :title-header="['Numero', 'Nom']"/>
                                        <tbody>
                                            <TableContain v-for="marque in allMarque.data" :liste-elements="[marque.idMarque, marque.nomMarque]" :modif-function="() => loadFormModif(marque.idMarque)" :delete-function="() => deleteMarqueById(marque.idMarque)"/>
                                        </tbody>
                                    </table>
                                    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" url="listeMarque"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </SousPrincipal>
                <FooterOne />
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
    @import "@/assets/css/reeuse/liste.css";
</style>