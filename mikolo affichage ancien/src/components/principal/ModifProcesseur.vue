<script setup> 
    import { onMounted, reactive} from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const idProcesseur=route.query.idProcesseur;
    var processeur=reactive({});
    var responseInsertProcesseur=reactive({});

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/laptop/processeur/insertProcesseur";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const data= {
                idProcesseur : processeur.idProcesseur,
                nomProcesseur: processeur.nomProcesseur,
                frequence: processeur.frequence,
                nbCoeur: processeur.nbCoeur
            };
            const response=axios.post(url, data, {headers});
            response.then(reponse => {
                Object.assign(responseInsertProcesseur, reponse.data);
                router.push("/allProcesseur")
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    const getProcesseurById = () => {
        try {
            const url="http://localhost:8080/laptop/processeur/findProcesseurById?idProcesseur="+idProcesseur;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.get(url, { headers });
            response.then(reponse => {
                Object.assign(processeur, reponse.data.data);
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    }

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {    
            getProcesseurById();
        }
    });

</script>
<template>
    <FormInsert title-props="Modifier le processeur" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="processeur.nomProcesseur" type="text" placeholder="Nom du processeur" name="nomProcesseur">
        <input v-model="processeur.frequence" type="number" placeholder="Frequence du processeur" name="frequenceProcesseur">
        <input v-model="processeur.nbCoeur" type="number" placeholder="Nombre de coeur" name="nbCoeur">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>