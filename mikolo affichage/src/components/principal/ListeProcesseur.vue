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
    import { onMounted, reactive, watch } from 'vue';
    import { useRoute } from 'vue-router';
    import { baseUrl } from '@/static/Constantes';
    import router from '@/router';
    import Pagination from '../reeuse/Pagination.vue';
    import SearchHeader from '../reeuse/SearchHeader.vue';

    const allProcesseur=reactive({});
    const nbPageProcesseur=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();
    const searchProcesseur=reactive({});

    const getAllProcesseur = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/processeur/allProcesseur/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allProcesseur, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const countNbPagesProcesseur = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/processeur/nbPagesProcesseur';
            listeNumPages.splice(0, listeNumPages.length);
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageProcesseur, response.data);
            for(var i=1; i<=nbPageProcesseur.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const loadFormModif = (idProcesseur) => {
        router.push({
            name : "modifProcesseur",
            params : {
                idProcesseur : idProcesseur
            }
        })
    }

    const deleteProcesseurById = (idProcesseur) => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/processeur/deleteProcesseurById?idProcesseur='+idProcesseur;
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                alert(reponse.data.message);
                if(reponse.data.status==200) {
                    getAllProcesseur();
                    countNbPagesProcesseur();
                }
            })
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    }

    onMounted(() => {
        getAllProcesseur();
        countNbPagesProcesseur();
    }); 

    watch(() => route.params.numPage, () => {
        getAllProcesseur();
    });

    const getStringFrequence = (frequence) => {
        return frequence+" Hz";
    }
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
                                <H4CardTitle card-title="Processeur"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead :title-header="['Numero', 'Nom', 'Frequence', 'Nombre de coeur']"/>
                                        <tbody>
                                            <TableContain v-for="processeur in allProcesseur.data" :liste-elements="[processeur.idProcesseur, processeur.nomProcesseur, getStringFrequence(processeur.frequence), processeur.nbCoeur]" :modif-function="() => loadFormModif(processeur.idProcesseur)" :delete-function="() => deleteProcesseurById(processeur.idProcesseur)"/>
                                        </tbody>
                                    </table>
                                    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" url="listeProcesseur"/>
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