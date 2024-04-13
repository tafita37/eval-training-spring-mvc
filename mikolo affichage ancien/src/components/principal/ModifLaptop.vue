<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const idLaptop=route.query.idLaptop;
    const laptop = reactive({
        idMarque : "",
        idProcesseur : "",
        idRam : "",
        idDisque : ""
    });
    const responseInsertLaptop = reactive({});
    const allMarque=reactive({});
    const allProcesseur=reactive({});
    const allRam=reactive({});
    const allDisque=reactive({});

    const getLaptopById = async() => {
        var urlApiSpringBoot = 'http://localhost:8080/laptop/findLaptopById?idLaptop='+idLaptop;
        const token=localStorage.getItem("tokenMagasin");
        const headers = {
            Authorization: `Bearer ${token}`,
        };
        var response = await axios.get(urlApiSpringBoot, {headers});
        const data= {
            model: response.data.data.model,
            tailleEcran: response.data.data.tailleEcran,
            idLaptop : idLaptop,
            idMarque : response.data.data.marque.idMarque,
            idProcesseur : response.data.data.processeur.idProcesseur,
            idRam : response.data.data.ram.idRam,
            idDisque : response.data.data.disque.idDisque,
            prixAchat : response.data.data.prixAchat,
            prixVente : response.data.data.prixVente
        };
        Object.assign(laptop, data);
    }

    const getAllMarque = async() => {
        try {
            var urlApiSpringBoot = 'http://localhost:8080/laptop/marque/allMarque';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            var response = await axios.get(urlApiSpringBoot, {headers});
            Object.assign(allMarque, response.data);
        } catch (error) {
            alert(error);
        }
    }

    const getAllProcesseur = async() => {
        try {
            var urlApiSpringBoot = 'http://localhost:8080/laptop/processeur/allProcesseur';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            var response = await axios.get(urlApiSpringBoot, {headers});
            Object.assign(allProcesseur, response.data);
        } catch (error) {
            alert(error);
        }
    }

    const getAllRam = async() => {
        try {
            var urlApiSpringBoot = 'http://localhost:8080/laptop/ram/allRam';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            var response = await axios.get(urlApiSpringBoot, {headers});
            Object.assign(allRam, response.data);
        } catch (error) {
            alert(error);
        }
    }

    const getAllDisque = async() => {
        try {
            var urlApiSpringBoot = 'http://localhost:8080/laptop/disque/allDisque';
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            var response = await axios.get(urlApiSpringBoot, {headers});
            Object.assign(allDisque, response.data);
        } catch (error) {
            alert(error);
        }
    }

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/laptop/insertLaptop';
            const data = {
                idLaptop: idLaptop,
                model: laptop.model,
                tailleEcran: laptop.tailleEcran,
                marque:  
                {
                    idMarque : laptop.idMarque
                },
                processeur:  
                {
                    idProcesseur : laptop.idProcesseur
                },
                ram:  
                {
                    idRam : laptop.idRam
                },
                disque:  
                {
                    idDisque : laptop.idDisque
                },
                prixAchat: laptop.prixAchat,
                prixVente: laptop.prixVente
            };
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.post(url, data, {headers});
            Object.assign(responseInsertLaptop, response.data);
            console.log(response.data);
            if(response.data.status!=200) {
                alert(response.data.message);
            } else {
                router.push("/listeLaptop")
            }
        } catch (error) {
            console.log(error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {    
            getLaptopById();
            getAllMarque();
            getAllProcesseur();
            getAllRam();
            getAllDisque();
        }
    });
</script>
<template>
    <FormInsert title-props="Modifier le laptop" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="laptop.model" type="text" placeholder="Reference du laptop" name="model">
        <input v-model="laptop.tailleEcran" type="number" placeholder="Taille de l'ecran" name="tailleEcran">
        <select  v-model="laptop.idMarque" name="idMarque" id="">
            <option value="">Choisissez une marque</option>
            <option v-for="(marque) in allMarque.data" :key="marque.idMarque" :value="marque.idMarque">
                {{ marque.nomMarque }}
            </option>
        </select>
        <select  v-model="laptop.idProcesseur" name="idProcesseur" id="">
            <option value="">Choisissez un processeur</option>
            <option v-for="(processeur) in allProcesseur.data" :key="processeur.idProcesseur" :value="processeur.idProcesseur">
                {{ processeur.nomProcesseur }}
            </option>
        </select>
        <select  v-model="laptop.idRam" name="idRam" id="">
            <option value="">Choisissez une ram</option>
            <option v-for="(ram) in allRam.data" :key="ram.idRam" :value="ram.idRam">
                {{ ram.capacite+" Go "+ram.typeRam.nomTypeRam }}
            </option>
        </select>
        <select  v-model="laptop.idDisque" name="idDisque" id="">
            <option value="">Choisissez un disque dur</option>
            <option v-for="(disque) in allDisque.data" :key="disque.idDisque" :value="disque.idDisque">
                {{ disque.capacite+" Go "+disque.typeDisque.nomTypeDisque }}
            </option>
        </select>
        <input v-model="laptop.prixAchat" type="number" placeholder="Prix d'achat" name="prixAchat">
        <input v-model="laptop.prixVente" type="number" placeholder="Prix de vente" name="prixVente">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>