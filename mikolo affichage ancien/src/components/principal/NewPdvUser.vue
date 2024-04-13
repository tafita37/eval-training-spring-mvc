<script setup>
    import axios from 'axios';
    import { reactive, onMounted } from 'vue';
    import FormInsert from '../reuse/FormInsert.vue';

    const pdvUser = reactive({
        idPointVente: "",
        idUser: ""
    });
    const allPointVente=reactive({});
    const allUtilisateur=reactive({});

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

    const getAllUsers = () => {
        try {
            const url="http://localhost:8080/pdv/user/allUser";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.get(url, {headers});
            response.then(reponse => {
                Object.assign(allUtilisateur, reponse.data.data);
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
            const url="http://localhost:8080/pdv/affectUserToPdv";
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
                    alert(reponse.data.message)
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
            getAllUsers();
        }
    });

</script>
<template>
    <FormInsert title-props="Affecter un utilisateur a un point de vente" :handle-submit="handleSubmit" submit-message="Affecter">
        <select  v-model="pdvUser.idPointVente" name="idPointVente" id="">
            <option value="">Choisissez un point de vente</option>
            <option v-for="(pointVente) in allPointVente" :key="pointVente.idPointVente" :value="pointVente.idPointVente">
                {{ pointVente.nomPointVente }}
            </option>
        </select>
        <select  v-model="pdvUser.idUser" name="idUser" id="">
            <option value="">Choisissez un utilisateur</option>
            <option v-for="(user) in allUtilisateur" :key="user.idUser" :value="user.idUser">
                {{ user.prenomUser }}
            </option>
        </select>
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>