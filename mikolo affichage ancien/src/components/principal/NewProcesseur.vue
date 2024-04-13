<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
import router from '@/router';

    var processeur=reactive({});
    var responseInsertProcesseur=reactive({});

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/laptop/processeur/insertProcesseur";
            const data= {
                nomProcesseur: processeur.nomProcesseur,
                frequence: processeur.frequence,
                nbCoeur: processeur.nbCoeur
            };
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, data, { headers });
            response.then(reponse => {
                Object.assign(responseInsertProcesseur, reponse.data);
                console.log(responseInsertProcesseur);
                router.push("/allProcesseur")
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        }
    });
</script>
<template>
    <FormInsert title-props="Inserer une nouveau processeur" :handle-submit="handleSubmit" submit-message="Inserer le processeur">
        <input v-model="processeur.nomProcesseur" type="text" placeholder="Nom du processeur" name="nomProcesseur">
        <input v-model="processeur.frequence" type="number" placeholder="Frequence du processeur" name="frequenceProcesseur">
        <input v-model="processeur.nbCoeur" type="number" placeholder="Nombre de coeur" name="nbCoeur">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>