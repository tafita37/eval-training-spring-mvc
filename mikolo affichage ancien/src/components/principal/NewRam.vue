<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    const ram = reactive({
        idTypeRam : ""
    });
    const responseInsertRam = reactive({});
    const allTypeRam=reactive({});

    const getAllTypeRam = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/ram/allTypeRam';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allTypeRam, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/laptop/ram/insertRam';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const data = {
                capacite: ram.capacite,
                frequence: ram.frequence,
                typeRam: 
                {
                    idTypeRam : ram.idTypeRam
                },
            };
            const response = await axios.post(url, data, { headers });
            Object.assign(responseInsertRam, response.data);
            console.log(responseInsertRam);
            router.push("/allRam")
        } catch (error) {
            console.log(error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {
            getAllTypeRam();
        }
    });
</script>
<template>
    <FormInsert title-props="Inserer une nouvel ram" :handle-submit="handleSubmit" submit-message="Inserer la ram">
        <input v-model="ram.capacite" type="number" placeholder="Capacite de la ram" name="capacite">
        <input v-model="ram.frequence" type="number" placeholder="Frequence de la ram" name="frequence">
        <select  v-model="ram.idTypeRam" name="idTypeRam" id="">
            <option value="">Choisissez un type de ram</option>
            <option v-for="(typeRam) in allTypeRam.data" :key="typeRam.idTypeRam" :value="typeRam.idTypeRam">
                {{ typeRam.nomTypeRam }}
            </option>
        </select>
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>