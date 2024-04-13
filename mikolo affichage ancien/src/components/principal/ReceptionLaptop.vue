<script setup>
    import { onMounted, reactive, ref } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';

    var receptionLaptop=reactive({
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
            const url="http://localhost:8080/laptop/receptionLaptop";
            const data= {
                pointVente : {
                    idPointVente : receptionLaptop.idPointVente
                },
                laptop: {
                    idLaptop: receptionLaptop.idLaptop
                },
                quantiteLaptopEntrant: receptionLaptop.quantiteLaptopEntrant,
                dateMouvement: receptionLaptop.dateMouvement
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
    <FormInsert title-props="Reception de laptop" :handle-submit="handleSubmit" submit-message="Recu">
        <select v-model="receptionLaptop.idPointVente" id="">
            <option value="">
                Choisissez un point de vente
            </option>
            <option v-for="(pdv) in allPdv.data" :key="pdv.idPointVente" :value="pdv.idPointVente">
                {{ pdv.nomPointVente }}
            </option>
        </select>
        <select v-model="receptionLaptop.idLaptop" id="">
            <option value="">
                Choisissez un laptop
            </option>
            <option v-for="(laptop) in allLaptop.data" :key="laptop.idLaptop" :value="laptop.idLaptop">
                {{ laptop.model }}
            </option>
        </select>
        <input v-model="receptionLaptop.quantiteLaptopEntrant" type="text" placeholder="Quantite">
        <input v-model="receptionLaptop.dateMouvement" type="date" placeholder="Date">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>