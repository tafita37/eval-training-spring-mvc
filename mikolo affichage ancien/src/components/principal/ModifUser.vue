<script setup>
    import router from '@/router';
import axios from 'axios';
    import { onMounted, reactive } from 'vue';
    import { useRoute } from 'vue-router';
    import FormInsert from '../reuse/FormInsert.vue';

    const route=useRoute();
    const idUser=route.query.idUser;
    const userData = reactive({
        idUser:idUser
    });
    const user=reactive({});

    const geUserById = () => {
        try {
            const url="http://localhost:8080/pdv/user/findUserById?idUser="+idUser;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = axios.get(url, {headers});
            response.then(reponse => {
                Object.assign(user, reponse.data);
                userData.nomUser=user.data.nomUser;
                userData.prenomUser= user.data.prenomUser;
                userData.emailUser= user.data.emailUser;
                userData.dtnUser= user.data.dtnUser;
            }).catch(error => {
                console.log(error);
            })
        } catch (error) {
            console.log(error);
            alert(error);
        }
    }

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/pdv/user/modifUser";
            const data= {
                idUser: userData.idUser,
                nomUser: userData.nomUser,
                prenomUser: userData.prenomUser,
                emailUser: userData.emailUser,
                dtnUser: userData.dtnUser
            };
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, data, {headers});
            response.then(reponse => {
                if(reponse.data.status!=200) {
                    console.log(reponse.data);
                } else {
                    router.push("/listePointVente");
                }
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    onMounted(() => {
       geUserById(); 
       console.log(userData);
    });
</script>
<template>
    <FormInsert title-props="Modifier un utilisateur" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="userData.nomUser" type="text" placeholder="Nom" name="nomUser">
        <input v-model="userData.prenomUser" type="text" placeholder="Prenom" name="prenomUser">
        <input v-model="userData.emailUser" type="text" placeholder="Email" name="emailUser">
        <input v-model="userData.dtnUser" type="date" placeholder="Date de naissance" name="dtnUser">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>