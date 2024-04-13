<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
import router from '@/router';

    const disque = reactive({
        idTypeDisque : ""
    });
    const responseInsertDisque = reactive({});
    const allTypeDisque=reactive({});

    const getAllTypeDisque = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/disque/allTypeDisque';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, {headers});
            Object.assign(allTypeDisque, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/laptop/disque/insertDisque';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const data = {
                capacite: disque.capacite,
                vitesse: disque.vitesse,
                typeDisque: 
                {
                    idTypeDisque : disque.idTypeDisque
                },
            };
            const response = await axios.post(url, data, {headers});
            Object.assign(responseInsertDisque, response.data);
            console.log(responseInsertDisque);
            router.push("/allDisque");
        } catch (error) {
            console.log(error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {    
            getAllTypeDisque();
        }
    });
</script>
<template>
    <FormInsert title-props="Inserer un nouveau disque" :handle-submit="handleSubmit" submit-message="Inserer le disque">
        <input v-model="disque.capacite" type="number" placeholder="Capacite du disque" name="capacite">
        <input v-model="disque.vitesse" type="number" placeholder="Vitesse du disque" name="vitesse">
        <select  v-model="disque.idTypeDisque" name="idTypeDisque" id="">
            <option value="">Choisissez un type de disque</option>
            <option v-for="(typeDisque) in allTypeDisque.data" :key="typeDisque.idTypeDisque" :value="typeDisque.idTypeDisque">
                {{ typeDisque.nomTypeDisque }}
            </option>
        </select>
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>