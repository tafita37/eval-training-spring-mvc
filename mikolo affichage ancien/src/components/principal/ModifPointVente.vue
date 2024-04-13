<script setup>
    import router from '@/router';
    import axios from 'axios';
    import { reactive, onMounted } from 'vue';
    import { useRoute } from 'vue-router';
    import FormInsert from '../reuse/FormInsert.vue';

    const route=useRoute();
    const pdv = reactive({
        idPointVente : route.query.idPointVente
    });

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/pdv/insertPDV";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const data= {
                idPointVente: pdv.idPointVente,
                nomPointVente: pdv.nomPointVente
            };
            const response=axios.post(url, data, { headers });
            response.then(reponse => {
                alert(reponse.data.message)
                router.push("/listePointVente")
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    const getPointVenteById = async () => {
        try {
            const url="http://localhost:8080/pdv/findPointVenteById?idPointVente="+route.query.idPointVente;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response= await axios.get(url, { headers });
            pdv.nomPointVente=response.data.data.nomPointVente;
        } catch(error) {
            console.log(error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        }
        getPointVenteById();
    });
</script>
<template>
    <FormInsert title-props="Modifier le point de vente" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="pdv.nomPointVente" type="text" placeholder="Nom" name="nomPointVente">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>