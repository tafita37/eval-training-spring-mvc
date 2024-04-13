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
    import { baseUrl, tokenAdminName } from '@/static/Constantes';
    import router from '@/router';
    import Pagination from '../reeuse/Pagination.vue';
    import SubmitButton from '../reeuse/SubmitButton.vue';
    import ListeButtonContain from '../reeuse/ListeButtonContain.vue';
import ListeButtonExport from '../reeuse/ListeButtonExport.vue';

    const allProduit=reactive({});
    const nbPageProduit=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();

    const getAllProduit = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem(tokenAdminName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/produit/allProduit/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allProduit, response.data);
            console.log(allProduit);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const countNbPagesProduit = async () => {
        try {
            const token=localStorage.getItem(tokenAdminName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/produit/countNbPageProduit';
            listeNumPages.splice(0, listeNumPages.length);
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageProduit, response.data);
            for(var i=1; i<=nbPageProduit.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const convertToCSV = (data) => {
        const headers=["nomProduit", "prixRevient", "prixVente", "pourcentageEvaporationJ"];
        // const headers=Object.keys(data[0]);
        const csvHeaders=["Nom du produit", "Prix de revient", "Prix de vente", "Pourcentage d'evaporation par jour"];
        const rows=data.map(obj => headers.map(header => obj[header]));
        console.log(rows);
        const headerRows=csvHeaders.join(",");
        const csvRows=[headerRows, ...rows.map(row => row.join(","))];
        return csvRows.join("\n");
    }

    const exportToCSV = () => {
        try {
            const csvContent=convertToCSV(allProduit.data);
            const blob=new Blob([csvContent], {type : 'text/csv;charset=utf-8'});
            const url=URL.createObjectURL(blob);
            const link=document.createElement('a');
            link.href=url;
            link.setAttribute('download', 'Liste des produits existants.csv');
            link.click();
        } catch (error) {
            console.error(error);
        }
    };

    const loadFormModif = (idproduit) => {
        router.push({
            name : "modifproduit",
            params : {
                idproduit : idproduit
            }
        })
    }
    
    const listeButtonExport=[
        {
            submitMessage : "Exporter csv",
            submitFunction : exportToCSV
        }
    ]

    const deleteproduitById = (idproduit) => {
        try {
            const token=localStorage.getItem(tokenAdminName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/produit/deleteproduitById?idproduit='+idproduit;
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                console.log(reponse);
                alert(reponse.data.message);
                if(reponse.data.status==200) {
                    getAllProduit();
                    countNbPagesProduit();
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
        getAllProduit();
        countNbPagesProduit();
    }); 

    watch(() => route.params.numPage, () => {
        getAllProduit();
        countNbPagesProduit();
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
                                <H4CardTitle card-title="Produits"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead 
                                            :title-header="[
                                                'Numero', 
                                                'Nom', 
                                                'Prix de revient', 
                                                'Prix de vente', 
                                                'Pourcentage d\'evaporartion/jour'
                                            ]"
                                        />
                                        <tbody>
                                            <TableContain 
                                                v-for="produit in allProduit.data" 
                                                :liste-elements="[
                                                    produit.idProduit, 
                                                    produit.nomProduit, 
                                                    produit.prixRevient, 
                                                    produit.prixVente,
                                                    produit.pourcentageEvaporationJ
                                                ]"
                                            />
                                        </tbody>
                                    </table>
                                    <Pagination 
                                        :actual-num-page="route.params.numPage" 
                                        :liste-num-pages="listeNumPages" 
                                        url="listeProduit"
                                    />
                                </div>
                            </div>
                        </div>
                        <ListeButtonExport :liste-button="listeButtonExport"/>
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