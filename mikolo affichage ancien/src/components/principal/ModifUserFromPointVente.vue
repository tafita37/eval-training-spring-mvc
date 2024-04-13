<script setup>
    import router from '@/router';
    import axios from 'axios';
    import { reactive, onMounted } from 'vue';
    import { useRoute } from 'vue-router';
    import FormInsert from '../reuse/FormInsert.vue';

    const route=useRoute();
    const idUser=route.query.idUser;
    const idPointVente=route.query.idPointVente;
    const pdvUser = reactive({
        idPointVente: idPointVente,
        idUser: idUser
    });
    const allPointVente=reactive({});

    const getAllPointVente = () => {
        try {
            const url="http://localhost:8080/pdv/allPointVente";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.get(url, {headers});
            response.then(reponse => {
                Object.assign(allPointVente, reponse.data.data);
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    }

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/pdv/modifPdvOfUser";
            const data= {
                pointVente: {
                    idPointVente : pdvUser.idPointVente,
                },
                user : {
                    idUser: pdvUser.idUser
                }
            };
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, data, {headers});
            response.then(reponse => {
                if(reponse.data.status==200) {
                    alert(reponse.data.message);
                    router.push("/listePointVente");
                } 
            })
            .catch(errors => {
                alert(errors);
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
            getAllPointVente();
        }
    });

</script>
<template>
    <FormInsert title-props="Modifier le point de vente" :handle-submit="handleSubmit" submit-message="Modifier">
        <select  v-model="pdvUser.idPointVente" name="idPointVente" id="">
            <option value="">Choisissez un point de vente</option>
            <option v-for="(pointVente) in allPointVente" :key="pointVente.idPointVente" :value="pointVente.idPointVente">
                {{ pointVente.nomPointVente }}
            </option>
        </select>
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>