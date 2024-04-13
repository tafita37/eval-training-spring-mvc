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
    import NormalSearch from '../reeuse/NormalSearch.vue';

    const allTypeRam=reactive({});
    const nbPageTypeRam=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();
    const searchTypeRam=reactive({});

    const getAllTypeRam = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/ram/allTypeRam/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allTypeRam, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const countNbPagesTypeRam = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/ram/nbPagesTypeRam';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageTypeRam, response.data);
            listeNumPages.splice(0, listeNumPages.length);
            for(var i=1; i<=nbPageTypeRam.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            console.log(error);
        }
    };

    const loadFormModif = (idTypeRam) => {
        router.push({
            name : "modifTypeRam",
            params : {
                idTypeRam : idTypeRam
            }
        })
    }

    const rechercheTypeRamByName = () => {
        try {
            const numPage=route.params.numPage;
            const token = localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl + '/laptop/ram/findTypeRamByName';
            const params=new URLSearchParams();
            params.append("nomTypeRam", searchTypeRam.nomTypeRam);
            params.append("numPage", numPage);
            const response = axios.post(urlApiSpringBoot, null, { params, headers });
            response.then(reponse => {
                Object.assign(allTypeRam, reponse.data);
            }).catch(error => {
                console.log(error);
            })
        } catch (error) {
            console.log(error);
        }
    }

    const countNbPagesTypeRamRecherche = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/ram/nbPagesTypeRamRecherche';
            const params=new URLSearchParams();
            params.append("nomTypeRam", searchTypeRam.nomTypeRam);
            const response = await axios.get(urlApiSpringBoot, { params, headers });
            Object.assign(nbPageTypeRam, response.data);
            listeNumPages.splice(0, listeNumPages.length);
            console.log(nbPageTypeRam);
            for(var i=1; i<=nbPageTypeRam.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            console.log(error);
        }
    };

    const deleteTypeRamById = (idTypeRam) => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/ram/deleteTypeRamById?idTypeRam='+idTypeRam;
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                alert(reponse.data.message);
                if(!searchTypeRam.nomTypeRam||searchTypeRam.nomTypeRam=='') {
                    getAllTypeRam();
                    countNbPagesTypeRam();
                } else {
                    rechercheTypeRamByName();
                    countNbPagesTypeRamRecherche();
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
        getAllTypeRam();
        countNbPagesTypeRam();
    }); 

    watch(() => route.params.numPage, () => {
        if(!searchTypeRam.nomTypeRam||searchTypeRam.nomTypeRam=='') {
            getAllTypeRam();
            countNbPagesTypeRam();
        } else {
            rechercheTypeRamByName();
            countNbPagesTypeRamRecherche();
        }
    });

    watch(() => searchTypeRam.nomTypeRam, () => {
        rechercheTypeRamByName();
        countNbPagesTypeRamRecherche();
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
                                <NormalSearch place-holder-search="Rechercher un type de ram" :search-function="rechercheTypeRamByName" :reactive-object="searchTypeRam" reactive-reference="nomTypeRam"/>
                                <H4CardTitle card-title="Type de ram"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead :title-header="['Numero', 'Nom']"/>
                                        <tbody>
                                            <TableContain v-for="typeRam in allTypeRam.data" :liste-elements="[typeRam.idTypeRam, typeRam.nomTypeRam]" :modif-function="() => loadFormModif(typeRam.idTypeRam)" :delete-function="() => deleteTypeRamById(typeRam.idTypeRam)"/>
                                        </tbody>
                                    </table>
                                    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" url="listeTypeRam"/>
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