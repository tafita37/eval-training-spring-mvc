<script setup>
    import GeneralContainLogin from '../reeuse/GeneralContainLogin.vue';
    import SignLoginForm from '../reeuse/SignLoginForm.vue';
    import InputLogin from '../reeuse/InputLogin.vue';
    import ValidateButton from '../reeuse/ValidateButton.vue';
    import SuppLinkSign from '../reeuse/SuppLinkSign.vue';
    import axios from 'axios';
    import { onMounted, reactive } from 'vue';
    import { baseUrl, tokenStationName } from '@/static/Constantes.js';
    import router from '@/router';

    
    var sendLogin=reactive({
        mail : "station1@gmail.com",
        mdp : "0000"
    });
    var reponseLogin=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/login/station/loginStation";
            const response=axios.post(url, sendLogin);
            response.then(reponse => {
                Object.assign(reponseLogin, reponse.data);
                if(reponseLogin.status==200) {
                    localStorage.setItem(tokenStationName, reponseLogin.data.token);
                    // console.log(localStorage.getItem(tokenStationName));
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
        router.push("/");
    }

    onMounted(() => {
        if(localStorage.getItem(tokenStationName)) {
            router.push("/stock/ajoutStock");
        }
    });
</script>
<template>
    <GeneralContainLogin>  
        <SignLoginForm role="Station" :handle-submit="handleSubmit">
            <InputLogin type-input="text" place-holder="Email de station" :reactive-cible="sendLogin" name-model="mail"/>
            <InputLogin type-input="password" place-holder="Password" :reactive-cible="sendLogin" name-model="mdp"/>
            <ValidateButton submit-message="SIGN IN"/>
            <SuppLinkSign message="Se connecter en tant que station" action="Login" :action-function="changeLogin"/>
        </SignLoginForm>
    </GeneralContainLogin>
</template>
<style scoped>
</style>