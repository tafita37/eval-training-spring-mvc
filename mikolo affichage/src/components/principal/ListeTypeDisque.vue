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

    const allTypeDisque=reactive({});
    const nbPageTypeDisque=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();
    const searchTypeDisque=reactive({});

    const getAllTypeDisque = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/disque/allTypeDisque/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allTypeDisque, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const countNbPagesTypeDisque = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/disque/nbPagesTypeDisque';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageTypeDisque, response.data);
            listeNumPages.splice(0, listeNumPages.length);
            for(var i=1; i<=nbPageTypeDisque.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            console.log(error);
        }
    };

    const loadFormModif = (idTypeDisque) => {
        router.push({
            name : "modifTypeDisque",
            params : {
                idTypeDisque : idTypeDisque
            }
        })
    }

    const rechercheTypeDisqueByName = () => {
        try {
            const numPage=route.params.numPage;
            const token = localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl + '/laptop/disque/findTypeDisqueByName';
            const params=new URLSearchParams();
            params.append("nomTypeDisque", searchTypeDisque.nomTypeDisque);
            params.append("numPage", numPage);
            const response = axios.post(urlApiSpringBoot, null, { params, headers });
            response.then(reponse => {
                Object.assign(allTypeDisque, reponse.data);
            }).catch(error => {
                console.log(error);
            })
        } catch (error) {
            console.log(error);
        }
    }

    const countNbPagesTypeDisqueRecherche = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/disque/nbPagesTypeDisqueRecherche';
            const params=new URLSearchParams();
            params.append("nomTypeDisque", searchTypeDisque.nomTypeDisque);
            const response = await axios.get(urlApiSpringBoot, { params, headers });
            Object.assign(nbPageTypeDisque, response.data);
            listeNumPages.splice(0, listeNumPages.length);
            console.log(nbPageTypeDisque);
            for(var i=1; i<=nbPageTypeDisque.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            console.log(error);
        }
    };

    const deleteTypeDisqueById = (idTypeDisque) => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/disque/deleteTypeDisqueById?idTypeDisque='+idTypeDisque;
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                alert(reponse.data.message);
                if(!searchTypeDisque.nomTypeDisque||searchTypeDisque.nomTypeDisque=='') {
                    getAllTypeDisque();
                    countNbPagesTypeDisque();
                } else {
                    rechercheTypeDisqueByName();
                    countNbPagesTypeDisqueRecherche();
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
        getAllTypeDisque();
        countNbPagesTypeDisque();
    }); 

    watch(() => route.params.numPage, () => {
        if(!searchTypeDisque.nomTypeDisque||searchTypeDisque.nomTypeDisque=='') {
            getAllTypeDisque();
            countNbPagesTypeDisque();
        } else {
            rechercheTypeDisqueByName();
            countNbPagesTypeDisqueRecherche();
        }
    });

    watch(() => searchTypeDisque.nomTypeDisque, () => {
        rechercheTypeDisqueByName();
        countNbPagesTypeDisqueRecherche();
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
                                <NormalSearch place-holder-search="Rechercher un type de disque" :search-function="rechercheTypeDisqueByName" :reactive-object="searchTypeDisque" reactive-reference="nomTypeDisque"/>
                                <H4CardTitle card-title="Type de disque"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead :title-header="['Numero', 'Nom']"/>
                                        <tbody>
                                            <TableContain v-for="typeDisque in allTypeDisque.data" :liste-elements="[typeDisque.idTypeDisque, typeDisque.nomTypeDisque]" :modif-function="() => loadFormModif(typeDisque.idTypeDisque)" :delete-function="() => deleteTypeDisqueById(typeDisque.idTypeDisque)"/>
                                        </tbody>
                                    </table>
                                    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" url="listeTypeDisque"/>
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