<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';
    import ButtonListe from '../reuse/ButtonListe.vue';

    const allPointVente=reactive({});

    const getAllPointVente = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/pdv/allPointVente';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allPointVente, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const loadFormModif = (idPointVente) => {
        router.push({path : "/modifPointVente", query: {idPointVente : idPointVente}});
    }

    const loadListeUserOfPointVente = (idPointVente) => {
        router.push({path : "/listeUserOfPdv", query : {idPointVente: idPointVente}});
    }

    const deletePointVenteById = async (idPointVente) => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/pdv/deletePointVenteById?idPointVente='+idPointVente;
            const response = await axios.get(urlApiSpringBoot, { headers });
            if(response.data.status==200) {
                router.push("/listePointVente");
            } else {
                alert(response.data.message);
            }
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {    
            getAllPointVente();
        }
    });
</script>
<template>
    <TitleListe title-liste="Liste des points de ventes"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(pointVente)  in allPointVente.data" :key="pointVente.idPointVente">
            <H5TitleListe :title-h5="pointVente.idPointVente" labels="Numero"/>
            <SpanListe :title-span="pointVente.nomPointVente" labels="Nom"/>
            <ModifSupprButton :funct-modif="() => loadFormModif(pointVente.idPointVente)" :funct-delete="() => deletePointVenteById(pointVente.idPointVente)"/>
            <ButtonListe contain-button="Liste des utilisateurs" bg="blue" :event-click="() => loadListeUserOfPointVente(pointVente.idPointVente)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>