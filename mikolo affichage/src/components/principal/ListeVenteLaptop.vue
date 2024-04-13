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
    import NormalSearch from '../reeuse/NormalSearch.vue';
    import MenuDeroulantTwo from '../reeuse/MenuDeroulantTwo.vue';

    const allVenteLaptop=reactive({});
    const allLaptop=reactive({});
    const nbPageVenteLaptop=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();
    const searchVenteLaptop=reactive({
        laptop : {
            idLaptop:0
        }
    });

    const getAllVenteLaptop = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenUser");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/allVenteLaptop/'+numPage;
            const params=new URLSearchParams();
            if(searchVenteLaptop.laptop.idLaptop) {
                params.append("idLaptop", searchVenteLaptop.laptop.idLaptop);
            }
            if(searchVenteLaptop.prixMin) {
                params.append("prixMin", searchVenteLaptop.prixMin);
            }
            if(searchVenteLaptop.prixMax) {
                params.append("prixMax", searchVenteLaptop.prixMax);
            }
            const response = await axios.get(urlApiSpringBoot, { headers, params });
            Object.assign(allVenteLaptop, response.data);
            console.log(allVenteLaptop);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                localStorage.removeItem("tokenUser");
                router.push("/");
            }
        }
    };

    const getAllLaptop = async () => {
        try {
            const token=localStorage.getItem("tokenUser");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/allLaptop';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allLaptop, response.data.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                localStorage.removeItem("tokenMagasin");
                router.push("/");
            }
        }
    };

    const countNbPagesVenteLaptop = async () => {
        try {
            const token=localStorage.getItem("tokenUser");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/nbPagesVenteLaptop';
            listeNumPages.splice(0, listeNumPages.length);
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageVenteLaptop, response.data);
            // console.log(nbPageVenteLaptop);
            for(var i=1; i<=nbPageVenteLaptop.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const listeSelect = [
        {
            idHTML : "inputListeLaptop",
            boucleObject : allLaptop,
            stringAfficher : "model",
            stringId : "idLaptop",
            sousObject : "laptop",
            defaultChoice : "Choisissez un model de laptop",
            defaultValue : 0
        }
    ]

    const listeInput = [
        {
            typeInput : "number",
            placeHolder : "Prix minimum",
            reactiveReference : "prixMin"
        },
        {
            typeInput : "number",
            placeHolder : "Prix maximum",
            reactiveReference : "prixMax"
        }
    ]

    onMounted(() => {
        getAllVenteLaptop();
        countNbPagesVenteLaptop();
        getAllLaptop();
    }); 

    watch(() => route.params.numPage, () => {
        getAllVenteLaptop();
    });

    const getStringPrix = (prix) => {
        return prix+" ar";
    };
</script>
<template>
    <GeneralContain>
        <HeaderOne />
        <SousGeneralContain>
            <MenuDeroulantTwo />
            <PrincipalContain>
                <SousPrincipal>
                    <NormalSearch :liste-select="listeSelect" :liste-input="listeInput" :reactive-object="searchVenteLaptop" :search-function="getAllVenteLaptop"/>
                    <div class="col-lg-6 grid-margin stretch-card mx-auto">
                        <div class="card">
                            <div class="card-body">
                                <H4CardTitle card-title="Laptop"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead 
                                        :title-header="['Model', 'Point de vente', 'Date de vente', 'Quantite', 'Prix de vente']
                                        "/>
                                        <tbody>
                                            <TableContain v-for="venteLaptop in allVenteLaptop.data" 
                                            :liste-elements="
                                            [
                                                venteLaptop.laptop.model, 
                                                venteLaptop.pointVente.nomPointVente, 
                                                venteLaptop.dateVenteLaptop, 
                                                venteLaptop.quantiteLaptop, 
                                                getStringPrix(venteLaptop.prixVente)
                                            ]"
                                            />
                                        </tbody>
                                    </table>
                                    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" 
                                    url="listeVenteLaptop"/>
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