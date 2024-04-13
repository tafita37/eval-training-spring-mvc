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

    const allRam=reactive({});
    const nbPageRam=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();

    const getAllRam = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/ram/allRam/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allRam, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const countNbPagesRam = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/ram/nbPagesRam';
            listeNumPages.splice(0, listeNumPages.length);
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageRam, response.data);
            for(var i=1; i<=nbPageRam.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const loadFormModif = (idRam) => {
        router.push({
            name : "modifRam",
            params : {
                idRam : idRam
            }
        })
    }

    const deleteRamById = (idRam) => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/ram/deleteRamById?idRam='+idRam;
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                console.log(reponse);
                alert(reponse.data.message);
                if(reponse.data.status==200) {
                    getAllRam();
                    countNbPagesRam();
                }
            }).catch(error => {
                console.log(error);
                alert(error.response.data.message);
            })
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    }

    onMounted(() => {
        getAllRam();
        countNbPagesRam();
    }); 

    watch(() => route.params.numPage, () => {
        getAllRam();
    });

    const getStringFrequence = (frequence) => {
        return frequence+" Hz";
    };

    const getStringCapacite = (capacite) => {
        return capacite+" go";
    };
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
                                <H4CardTitle card-title="Ram"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead :title-header="['Numero', 'Capacite', 'Frequence', 'Type de ram']"/>
                                        <tbody>
                                            <TableContain v-for="ram in allRam.data" :liste-elements="[ram.idRam, getStringCapacite(ram.capacite), getStringFrequence(ram.frequence), ram.typeRam.nomTypeRam]" :modif-function="() => loadFormModif(ram.idRam)" :delete-function="() => deleteRamById(ram.idRam)"/>
                                        </tbody>
                                    </table>
                                    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" url="listeRam"/>
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