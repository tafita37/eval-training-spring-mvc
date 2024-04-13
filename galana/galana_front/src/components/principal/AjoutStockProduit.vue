<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantTwo from '../reeuse/MenuDeroulantTwo.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertStockStation, tokenStationName } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';
import SubmitButton from '../reeuse/SubmitButton.vue';

    const ajoutStockProduit=reactive({
        produit : {
            idProduit : ""
        }
    });
    var responseInsertAjoutStockProduit=reactive({});
    const allProduit=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/produit/ajoutStock";
            const token=localStorage.getItem(tokenStationName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, ajoutStockProduit, {headers});
            response.then(reponse => {
                Object.assign(responseInsertAjoutStockProduit, reponse.data);
                console.log(responseInsertajoutStockProduit);
                if(responseInsertajoutStockProduit.status==201) {
                    alert(responseInsertajoutStockProduit.message);
                }
            })
            .catch(errors => {
                console.log(errors);
                alert(errors.response.data.message);
            })
        } catch(error) {
            console.log(error);
        }
    };

    const getAllProduit = async () => {
        try {
            const token=localStorage.getItem(tokenStationName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/produit/allProduit';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allProduit, response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const listeSelect = [
        {
            labelName : "Produit",
            idHTML : "inputProduit",
            boucleObject : allProduit,
            stringAfficher : "nomProduit",
            stringId : "idProduit",
            vModel : ajoutStockProduit,
            sousObject : "produit",
            defaultChoice : "Choisissez un produit"
        }
    ]

    onMounted(() => {
        if(!localStorage.getItem(tokenStationName)) {
            router.push("/");
        }
        getAllProduit();
    });

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
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantTwo/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal 
                        :reference-reactive-object="ajoutStockProduit" 
                        title-form="Stock de produit" 
                        description-form="Ajout" 
                        :liste-input="insertStockStation" 
                        :submit-function="handleSubmit" 
                        submit-message="Ajouter" 
                        :liste-select="listeSelect"
                    />
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
        <SubmitButton submit-message="Exporter en CSV" :submit-function="exportToCSV"/>
    </GeneralContain>
</template>
<style scoped>
</style>