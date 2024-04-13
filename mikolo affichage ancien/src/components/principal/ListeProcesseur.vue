<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allProcesseur=reactive({});

    const getAllProcesseur = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/processeur/allProcesseur';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allProcesseur, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const getStringFrequence = (processeur) => {
        return processeur.frequence+" Hz";
    }

    const loadFormModif = (idProcesseur) => {
        router.push({path : "/modifProcesseur", query: {idProcesseur : idProcesseur}});
    }

    const deleteProcesseurById = async (idProcesseur) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/processeur/deleteProcesseurById?idProcesseur='+idProcesseur;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, {headers});
            if(response.data.status==200) {
                router.push("/allProcesseur");
            } else {
                alert(response.data.message);
            }
        } catch (error) {
            console.error(error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {
            getAllProcesseur();
        }
    });
</script>
<template>
    <TitleListe title-liste="Liste des processeurs"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(processeur)  in allProcesseur.data" :key="processeur.idProcesseur">
            <H5TitleListe :title-h5="processeur.idProcesseur" labels="Numero"/>
            <SpanListe :title-span="processeur.nomProcesseur" labels="Processeur"/>
            <SpanListe :title-span="getStringFrequence(processeur)" labels="Frequence"/>
            <SpanListe :title-span="processeur.nbCoeur" labels="Nombre de coeur"/>
            <ModifSupprButton :funct-modif="() => loadFormModif(processeur.idProcesseur)" :funct-delete="() => deleteProcesseurById(processeur.idProcesseur)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>