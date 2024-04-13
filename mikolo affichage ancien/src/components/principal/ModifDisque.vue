<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const idDisque=route.query.idDisque;
    const disque = reactive({
        idDisque : idDisque,
        idTypeDisque : ""
    });
    const responseInsertDisque = reactive({});
    const allTypeDisque=reactive({});

    const findDisqueById = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/disque/findDisqueById?idDisque='+idDisque;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
            const disqueTest= {
                idDisque: response.data.data.idDisque,
                capacite: response.data.data.capacite,
                vitesse: response.data.data.vitesse,
                idTypeDisque: response.data.data.typeDisque.idTypeDisque
            }
            Object.assign(disque, disqueTest);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    const getAllTypeDisque = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/disque/allTypeDisque';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
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
                idDisque: disque.idDisque,
                capacite: disque.capacite,
                vitesse: disque.vitesse,
                typeDisque: 
                {
                    idTypeDisque : disque.idTypeDisque
                },
            };
            const response = await axios.post(url, data, { headers });
            Object.assign(responseInsertDisque, response.data);
            router.push("/allDisque")
        } catch (error) {
            console.log(error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {    
            findDisqueById();
            getAllTypeDisque();
        }
    });
</script>
<template>
    <FormInsert title-props="Modifier le disque dur" :handle-submit="handleSubmit" submit-message="Modifier le disque">
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