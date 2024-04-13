<script setup>
    import { onMounted, reactive, ref } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';

    var ajoutStock=reactive({
        idLaptop:""
    });
    const allLaptop=reactive({});

    const getAllLaptop = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/allLaptop';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, {headers});
            Object.assign(allLaptop, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/laptop/newStockLaptop";
            const data= {
                laptop: {
                    idLaptop: ajoutStock.idLaptop
                },
                quantiteLaptopEntrant: ajoutStock.quantiteLaptopEntrant,
                dateMouvement: ajoutStock.dateMouvement
            };
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, data, {headers});
            response.then(reponse => {
                alert(reponse.data.message);
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
        } else {    
            getAllLaptop();
        }
    });
</script>
<template>
    <FormInsert title-props="Faire un ajout de stock" :handle-submit="handleSubmit" submit-message="Ajouter">
        <select v-model="ajoutStock.idLaptop" id="">
            <option value="">
                Choisissez un laptop
            </option>
            <option v-for="(laptop) in allLaptop.data" :key="laptop.idLaptop" :value="laptop.idLaptop">
                {{ laptop.model }}
            </option>
        </select>
        <input v-model="ajoutStock.quantiteLaptopEntrant" type="text" placeholder="Quantite">
        <input v-model="ajoutStock.dateMouvement" type="date" placeholder="Date">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>