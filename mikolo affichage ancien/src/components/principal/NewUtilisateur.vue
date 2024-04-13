<script setup>
    import axios from 'axios';
    import { reactive } from 'vue';
    import FormInsert from '../reuse/FormInsert.vue';

    const userData = reactive({});

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/pdv/user/insertUser";
            const data= {
                nomUser: userData.nomUser,
                prenomUser: userData.prenomUser,
                emailUser: userData.emailUser,
                dtnUser: userData.dtnUser,
                mdpUser: userData.mdpUser
            };
            const response=axios.post(url, data);
            response.then(reponse => {
                if(reponse.data.status!=200) {
                    alert(reponse.data.message);
                    console.log(reponse.data);
                } 
                console.log(reponse.data);
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Creer un utilisateur" :handle-submit="handleSubmit" submit-message="Enregistrer le nouvel utilisateur">
        <input v-model="userData.nomUser" type="text" placeholder="Nom" name="nomUser">
        <input v-model="userData.prenomUser" type="text" placeholder="Prenom" name="prenomUser">
        <input v-model="userData.emailUser" type="text" placeholder="Email" name="emailUser">
        <input v-model="userData.dtnUser" type="date" placeholder="Date de naissance" name="dtnUser">
        <input v-model="userData.mdpUser" type="password" placeholder="Password" name="mdpUser">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>