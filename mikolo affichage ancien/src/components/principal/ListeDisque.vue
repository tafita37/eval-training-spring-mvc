<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import router from '@/router';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';

    const allDisque=reactive({});

    const getAllDisque = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/disque/allDisque';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allDisque, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const deleteDisqueById = async (idDisque) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/disque/deleteDisqueById?idDisque='+idDisque;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, {headers});
            if(response.data.status==200) {
                router.push("/allDisque");
            } else {
                alert(response.data.message);
            }
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const getStringCapaciteDisque = (disque) => {
        return disque.capacite+" go";
    }

    const getStringVitesseDisque = (disque) => {
        return disque.vitesse+" Hz";
    }

    const loadFormModif = (id) => {
        router.push({path : "/modifDisque", query: {idDisque : id}})
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {    
            getAllDisque();
        }
    });
</script>
<template>
    <TitleListe title-liste="Liste des disque dur"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(disque)  in allDisque.data" :key="disque.idDisque">
            <H5TitleListe :title-h5="disque.idDisque" labels="Numero"/>
            <SpanListe :title-span="getStringCapaciteDisque(disque)" labels="Capacite"/>
            <SpanListe :title-span="getStringVitesseDisque(disque)" labels="Vitesse"/>
            <SpanListe :title-span="disque.typeDisque.nomTypeDisque" labels="Type de disque"/>
            <ModifSupprButton :funct-modif="() => loadFormModif(disque.idDisque)" :funct-delete="() => deleteDisqueById(disque.idDisque)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>