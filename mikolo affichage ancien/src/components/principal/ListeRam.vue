<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allRam=reactive({});

    const getAllRam = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/ram/allRam';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allRam, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const getStringCapaciteRam = (ram) => {
        return ram.capacite+" go";
    }

    const getStringFrequenceRam = (ram) => {
        return ram.frequence+" Hz";
    }

    const loadFormModif = (idRam) => {
        router.push({path : "/modifRam", query: {idRam : idRam}})
    };

    const deleteRamById = (idRam) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/ram/deleteRamById?idRam='+idRam;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response =  axios.get(urlApiSpringBoot, { headers });
            response.then(reponse => {
                if(reponse.data.status!=200) {
                    alert(reponse.data.message);
                }
            })
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {    
            getAllRam();
        }
    });
</script>
<template>
    <TitleListe title-liste="Liste des ram"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(ram)  in allRam.data" :key="ram.idRam">
            <H5TitleListe :title-h5="ram.idRam" labels="Numero"/>
            <SpanListe :title-span="getStringCapaciteRam(ram)" labels="Capacite"/>
            <SpanListe :title-span="getStringFrequenceRam(ram)" labels="Frequence"/>
            <SpanListe :title-span="ram.typeRam.nomTypeRam" labels="Type de ram"/>
            <ModifSupprButton :funct-modif="() => loadFormModif(ram.idRam)" :funct-delete="() => deleteRamById(ram.idRam)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>