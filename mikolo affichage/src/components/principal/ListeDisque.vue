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

    const allDisque=reactive({});
    const nbPageDisque=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();

    const getAllDisque = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/disque/allDisque/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allDisque, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const countNbPagesDisque = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/disque/nbPagesDisque';
            listeNumPages.splice(0, listeNumPages.length);
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageDisque, response.data);
            for(var i=1; i<=nbPageDisque.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const loadFormModif = (idDisque) => {
        router.push({
            name : "modifDisque",
            params : {
                idDisque : idDisque
            }
        })
    }

    const deleteDisqueById = (idDisque) => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/disque/deleteDisqueById?idDisque='+idDisque;
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                console.log(reponse);
                alert(reponse.data.message);
                if(reponse.data.status==200) {
                    getAllDisque();
                    countNbPagesDisque();
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
        getAllDisque();
        countNbPagesDisque();
    }); 

    watch(() => route.params.numPage, () => {
        getAllDisque();
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
                                <H4CardTitle card-title="Disque dur"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead :title-header="['Numero', 'Capacite', 'Vitesse', 'Type de disque']"/>
                                        <tbody>
                                            <TableContain v-for="disque in allDisque.data" :liste-elements="[disque.idDisque, getStringCapacite(disque.capacite), getStringFrequence(disque.vitesse), disque.typeDisque.nomTypeDisque]" :modif-function="() => loadFormModif(disque.idDisque)" :delete-function="() => deleteDisqueById(disque.idDisque)"/>
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
</style>