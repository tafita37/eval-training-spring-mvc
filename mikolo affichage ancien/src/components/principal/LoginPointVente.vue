<script setup>
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
import router from '@/router';

    var reponseLoginPdv=reactive({});
    var sendLoginPdv=reactive({});

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/pdv/user/loginUser";
            const data= {
                emailUser: sendLoginPdv.emailUser,
                mdpUser: sendLoginPdv.mdpUser
            };
            const response=axios.post(url, data);
            response.then(reponse => {
                Object.assign(reponseLoginPdv, reponse.data);
                alert(reponseLoginPdv.message);
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    onMounted(() => {
        if(localStorage.getItem("tokenMagasin")) {
            router.push("/listeLaptop");
        }
    });

</script>
<template>
    <FormInsert title-props="Se connecter utilisateur" :handle-submit="handleSubmit" submit-message="Se connecter">
        <input v-model="sendLoginPdv.emailUser" type="email" placeholder="Votre email">
        <input v-model="sendLoginPdv.mdpUser" type="password" placeholder="Password">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>