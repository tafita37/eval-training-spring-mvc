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
    import { baseUrl, tokenStationName } from '@/static/Constantes';
    import router from '@/router';
    import Pagination from '../reeuse/Pagination.vue';
    import ListeButtonExport from '../reeuse/ListeButtonExport.vue';

    const allEntreeStock=reactive({});
    const nbPageEntreeStock=reactive({});
    const listeNumPages=reactive([]);
    const route=useRoute();
    const listeHeader=[
        'Nom du produit', 
        'Quantite', 
        'Date d\'ajout', 
        'Prix de revient', 
        'Prix de vente', 
        'Mail de station'
    ];

    const getAllEntreeStock = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem(tokenStationName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/produit/listeEntreeStock/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allEntreeStock, response.data);
            console.log(allEntreeStock);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const countNbPagesEntreeStock = async () => {
        try {
            const token=localStorage.getItem(tokenStationName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/produit/countNbPageEntreeStock';
            listeNumPages.splice(0, listeNumPages.length);
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(nbPageEntreeStock, response.data);
            for(var i=1; i<=nbPageEntreeStock.data; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const convertToCSV = (data) => {
        const headers=["nomEntreeStock", "prixRevient", "prixVente", "pourcentageEvaporationJ"];
        // const headers=Object.keys(data[0]);
        const csvHeaders=listeHeader;
        var rows=[];
        for(let i=0; i<data.length; i++) {
            rows[i]=[];
            rows[i].push(data[i].produit.nomProduit);
            rows[i].push(data[i].entreeProduit);
            rows[i].push(data[i].dateMouvementProduit);
            rows[i].push(data[i].prixRevient);
            rows[i].push(data[i].prixVente);
            rows[i].push(data[i].station.mailStation);
        }
        console.log(rows);
        console.log(rows);
        const headerRows=csvHeaders.join(",");
        const csvRows=[headerRows, ...rows.map(row => row.join(","))];
        return csvRows.join("\n");
    }

    const exportToCSV = () => {
        try {
            const csvContent=convertToCSV(allEntreeStock.data);
            const blob=new Blob([csvContent], {type : 'text/csv;charset=utf-8'});
            const url=URL.createObjectURL(blob);
            const link=document.createElement('a');
            link.href=url;
            link.setAttribute('download', 'Liste des EntreeStocks existants.csv');
            link.click();
        } catch (error) {
            console.error(error);
        }
    };

    const loadFormModif = (idEntreeStock) => {
        router.push({
            name : "modifEntreeStock",
            params : {
                idEntreeStock : idEntreeStock
            }
        })
    }
    
    const listeButtonExport=[
        {
            submitMessage : "Exporter csv",
            submitFunction : exportToCSV
        }
    ]

    const deleteEntreeStockById = (idEntreeStock) => {
        try {
            const token=localStorage.getItem(tokenStationName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/EntreeStock/deleteEntreeStockById?idEntreeStock='+idEntreeStock;
            const response = axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                console.log(reponse);
                alert(reponse.data.message);
                if(reponse.data.status==200) {
                    getAllEntreeStock();
                    countNbPagesEntreeStock();
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
        getAllEntreeStock();
        countNbPagesEntreeStock();
    }); 

    watch(() => route.params.numPage, () => {
        getAllEntreeStock();
        countNbPagesEntreeStock();
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
                                <H4CardTitle card-title="Entree de stock"/>
                                <CardDescription card-description="Liste"/>
                                <div class="table-responsive">
                                    <table class="table">
                                        <TableHead 
                                            :title-header="listeHeader"
                                        />
                                        <tbody>
                                            <TableContain 
                                                v-for="entreeStock in allEntreeStock.data" 
                                                :liste-elements="[
                                                    entreeStock.produit.nomProduit, 
                                                    entreeStock.entreeProduit, 
                                                    entreeStock.dateMouvementProduit, 
                                                    entreeStock.prixRevient,
                                                    entreeStock.prixVente,
                                                    entreeStock.station.mailStation
                                                ]"
                                            />
                                        </tbody>
                                    </table>
                                    <Pagination 
                                        :actual-num-page="route.params.numPage" 
                                        :liste-num-pages="listeNumPages" 
                                        url="listeEntreeStock"
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