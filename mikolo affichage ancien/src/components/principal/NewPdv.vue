<script setup>
    import router from '@/router';
import axios from 'axios';
    import { reactive, onMounted } from 'vue';
    import FormInsert from '../reuse/FormInsert.vue';

    const pdv = reactive({});

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/pdv/insertPDV";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const data= {
                nomPointVente: pdv.nomPointVente
            };
            const response=axios.post(url, data, { headers });
            response.then(reponse => {
                alert(reponse.data.message);
                router.push("/listePointVente");
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
    <FormInsert title-props="Creer un nouveau point de vente" :handle-submit="handleSubmit" submit-message="Enregistrer">
        <input v-model="pdv.nomPointVente" type="text" placeholder="Nom" name="nomPointVente">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>