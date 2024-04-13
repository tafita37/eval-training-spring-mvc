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
    import SelectFormGroupControl from '../reeuse/SelectFormGroupControl.vue';

    const allLaptop=reactive({});
    const nbPageLaptop=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();

    const getAllLaptop = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/allLaptop/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allLaptop, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                localStorage.removeItem("tokenMagasin");
                router.push("/");
            }
        }
    };

    const countNbPagesLaptop = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/nbPagesLaptop';
            listeNumPages.splice(0, listeNumPages.length);
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageLaptop, response.data);
            for(var i=1; i<=nbPageLaptop.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const loadFormModif = (idLaptop) => {
        router.push({
            name : "modifLaptop",
            params : {
                idLaptop : idLaptop
            }
        })
    }

    const deleteLaptopById = (idLaptop) => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/deleteLaptopById?idLaptop='+idLaptop;
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                console.log(reponse);
                alert(reponse.data.message);
                if(reponse.data.status==200) {
                    getAllLaptop();
                    countNbPagesLaptop();
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
        getAllLaptop();
        countNbPagesLaptop();
    }); 

    watch(() => route.params.numPage, () => {
        getAllLaptop();
    });
    
    const getStringRam = (ram) => {
        return ram.capacite+" go de ram "+ram.typeRam.nomTypeRam;
    }
    
    const getStringDisque = (disque) => {
        return disque.capacite+" go "+disque.typeDisque.nomTypeDisque;
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
                                <H4CardTitle card-title="Laptop"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead :title-header="['Model', 'Marque', 'Processeur', 'Ram', 'Taille de l\'ecran', 'Disque dur', 'Prix d\'achat', 'Prix de vente']"/>
                                        <tbody>
                                            <TableContain v-for="laptop in allLaptop.data" :liste-elements="[laptop.model, laptop.marque.nomMarque, laptop.processeur.nomProcesseur, getStringRam(laptop.ram), laptop.tailleEcran, getStringDisque(laptop.disque), laptop.prixAchat, laptop.prixVente]" :modif-function="() => loadFormModif(laptop.idLaptop)" :delete-function="() => deleteLaptopById(laptop.idLaptop)"/>
                                        </tbody>
                                    </table>
                                    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" url="listeDisque"/>
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
    @import "@/assets/css/all.css";
    @import "@/assets/css/all.min.css";
</style>