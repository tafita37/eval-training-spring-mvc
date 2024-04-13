<script setup>
    import { onMounted, reactive, ref } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';

    var transfertLaptop=reactive({
        idPointVente:"",
        idLaptop:""
    });

    const allLaptop=reactive({});
    const allPdv=reactive({});

    const getAllLaptop = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/allLaptop';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allLaptop, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const getAllPdv = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/pdv/allPointVente';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allPdv, response.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const getDatas = async () => {
        getAllLaptop();
        getAllPdv();
    }

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/laptop/transfererLaptop";
            const data= {
                pointVente : {
                    idPointVente : transfertLaptop.idPointVente
                },
                laptop: {
                    idLaptop: transfertLaptop.idLaptop
                },
                quantiteLaptop: transfertLaptop.quantiteLaptop,
                dateTransfertLaptop: transfertLaptop.dateTransfertLaptop
            };
            const response=axios.post(url, data);
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

    onMounted(getDatas);
</script>
<template>
    <FormInsert title-props="Transferer un laptop" :handle-submit="handleSubmit" submit-message="Transferer">
        <select v-model="transfertLaptop.idPointVente" id="">
            <option value="">
                Choisissez un point de vente
            </option>
            <option v-for="(pdv) in allPdv.data" :key="pdv.idPointVente" :value="pdv.idPointVente">
                {{ pdv.nomPointVente }}
            </option>
        </select>
        <select v-model="transfertLaptop.idLaptop" id="">
            <option value="">
                Choisissez un laptop
            </option>
            <option v-for="(laptop) in allLaptop.data" :key="laptop.idLaptop" :value="laptop.idLaptop">
                {{ laptop.model }}
            </option>
        </select>
        <input v-model="transfertLaptop.quantiteLaptop" type="text" placeholder="Quantite">
        <input v-model="transfertLaptop.dateTransfertLaptop" type="date" placeholder="Date">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>