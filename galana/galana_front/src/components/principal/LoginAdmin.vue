<script setup>
    import GeneralContainLogin from '../reeuse/GeneralContainLogin.vue';
    import SignLoginForm from '../reeuse/SignLoginForm.vue';
    import InputLogin from '../reeuse/InputLogin.vue';
    import ValidateButton from '../reeuse/ValidateButton.vue';
    import SuppLinkSign from '../reeuse/SuppLinkSign.vue';
    import axios from 'axios';
    import { onMounted, reactive } from 'vue';
    import { baseUrl, tokenAdminName } from '@/static/Constantes.js';
    import router from '@/router';

    
    var sendLogin=reactive({
        mail : "admin@gmail.com",
        mdp : "0000"
    });
    var reponseLogin=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/login/admin/loginAdmin";
            const response=axios.post(url, sendLogin);
            response.then(reponse => {
                Object.assign(reponseLogin, reponse.data);
                if(reponseLogin.status==200) {
                    localStorage.setItem(tokenAdminName, reponseLogin.data.token);
                    router.push("/produit/newProduit");
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

    const changeLogin = () => {
        router.push("/station/loginStation");
    }

    onMounted(() => {
        // if(localStorage.getItem("tokenMagasin")) {
        //     router.push("/laptop/listeLaptop/1");
        // }
    });
</script>
<template>
    <GeneralContainLogin>  
        <SignLoginForm role="Admin" :handle-submit="handleSubmit">
            <InputLogin type-input="text" place-holder="Email" :reactive-cible="sendLogin" name-model="mail"/>
            <InputLogin type-input="password" place-holder="Password" :reactive-cible="sendLogin" name-model="mdp"/>
            <ValidateButton submit-message="SIGN IN"/>
            <SuppLinkSign message="Se connecter en tant que station" action="Login" :action-function="changeLogin"/>
        </SignLoginForm>
    </GeneralContainLogin>
</template>
<style scoped>
</style>