<script setup>
    import { reactive, ref, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var reponseLogin=reactive({});
    var sendLogin=reactive({});
    var responseHTTP=ref(false);

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/magasin/loginMagasin";
            const data= {
                nomMagasinUser: sendLogin.nomMagasin,
                mdp: sendLogin.mdp
            };
            const response=axios.post(url, data);
            response.then(reponse => {
                Object.assign(reponseLogin, reponse.data);
                responseHTTP.value=true;
                if(reponseLogin.status==200) {
                    localStorage.setItem("tokenMagasin", reponseLogin.data.token);
                    router.push("/listeLaptop");
                } else {
                    alert(reponseLogin.message);
                }
            })
            .catch(errors => {
                alert(errors);
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
    <FormInsert title-props="Se connecter magasin" :handle-submit="handleSubmit" submit-message="Se connecter">
        <input v-model="sendLogin.nomMagasin" type="text" placeholder="Nom du magasin" name="nomMagasin">
        <input v-model="sendLogin.mdp" type="password" placeholder="Password" name="mdp">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>