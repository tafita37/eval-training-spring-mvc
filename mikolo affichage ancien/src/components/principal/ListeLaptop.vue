<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allLaptop=reactive({});

    const getAllLaptop = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/laptop/allLaptop';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allLaptop, response.data);
            console.log(allLaptop);
        } catch (error) {
            const status=error.response.status;
            if(status==403) {
                localStorage.removeItem("tokenMagasin");
                router.push("/");
            }
            // alert(error);
        }
    };

    const getStringDisque = (element, type) => {
        return element+" Go "+type;
    }

    const getStringPrix = (prix) => {
        return prix+" ar";
    }

    const getStringTailleEcran = (laptop) => {
        return laptop.tailleEcran+" pouces";
    }

    const loadFormModif = (idLaptop) => {
        router.push({path : "/modifLaptop", query: {idLaptop : idLaptop}});
    }

    const deleteLaptopById = (idLaptop) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/deleteLaptopById?idLaptop='+idLaptop;
            const response = axios.get(urlApiSpringBoot);
            alert(response);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {
            getAllLaptop();
        }
    });
</script>
<template>
    <TitleListe title-liste="Liste des laptops"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(laptop)  in allLaptop.data" :key="laptop.idLaptop">
            <H5TitleListe labels="Model" :title-h5="laptop.model"/>
            <SpanListe :title-span="laptop.marque.nomMarque" labels="Marque"/>
            <SpanListe :title-span="laptop.processeur.nomProcesseur" labels="Processeur"/>
            <SpanListe :title-span="getStringDisque(laptop.ram.capacite, laptop.ram.typeRam.nomTypeRam)" labels="Ram"/>
            <SpanListe :title-span="getStringDisque(laptop.disque.capacite, laptop.disque.typeDisque.nomTypeDisque)" labels="Disque"/>
            <SpanListe :title-span="getStringTailleEcran(laptop)" labels="Taille de l'ecran"/>
            <SpanListe :title-span="getStringPrix(laptop.prixAchat)" labels="Prix d'achat"/>
            <SpanListe :title-span="getStringPrix(laptop.prixVente)" labels="Prix de vente"/>
            <ModifSupprButton :funct-modif="() => loadFormModif(laptop.idLaptop)" :funct-delete="() => deleteLaptopById(laptop.idLaptop)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>