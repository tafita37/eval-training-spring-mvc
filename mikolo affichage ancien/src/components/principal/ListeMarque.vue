<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allMarque=reactive({});

    const getAllMarque = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/laptop/marque/allMarque';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allMarque, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const loadFormModif = (idMarque) => {
        router.push({path : "/modifMarque", query: {idMarque : idMarque}});
    }

    const deleteMarqueById = async (idMarque) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/marque/deleteMarqueById?idMarque='+idMarque;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
            if(response.data.status==200) {
                alert(response.data.message);
                router.push("/allMarque");
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
            getAllMarque();
        }
    });
</script>
<template>
    <TitleListe title-liste="Liste des marque"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(marque)  in allMarque.data" :key="marque.idMarque">
            <H5TitleListe :title-h5="marque.idMarque" labels="Numero"/>
            <SpanListe :title-span="marque.nomMarque" labels="Marque"/>
            <ModifSupprButton :funct-modif="() => loadFormModif(marque.idMarque)" :funct-delete="() => deleteMarqueById(marque.idMarque)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>