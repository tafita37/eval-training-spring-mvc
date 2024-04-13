<script setup>
    import GeneralContainLogin from '../reeuse/GeneralContainLogin.vue';
    import SignLoginForm from '../reeuse/SignLoginForm.vue';
    import InputLogin from '../reeuse/InputLogin.vue';
    import ValidateButton from '../reeuse/ValidateButton.vue';
    import SuppLinkSign from '../reeuse/SuppLinkSign.vue';
    import axios from 'axios';
    import { onMounted, reactive } from 'vue';
    import { baseUrl } from '@/static/Constantes.js';
    import router from '@/router';

    
    var sendLogin=reactive({
        nomMagasinUser : "M1",
        mdp : "0000"
    });
    var reponseLogin=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/magasin/loginMagasin";
            const response=axios.post(url, sendLogin);
            response.then(reponse => {
                Object.assign(reponseLogin, reponse.data);
                if(reponseLogin.status==200) {
                    localStorage.setItem("tokenMagasin", reponseLogin.data.token);
                    router.push("/laptop/listeLaptop/1")
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
        router.push("/loginUser");
    }

    onMounted(() => {
        if(localStorage.getItem("tokenMagasin")) {
            router.push("/laptop/listeLaptop/1");
        }
    });
</script>
<template>
    <GeneralContainLogin>  
        <SignLoginForm role="magasin" :handle-submit="handleSubmit">
            <InputLogin type-input="text" place-holder="Username" :reactive-cible="sendLogin" name-model="nomMagasinUser"/>
            <InputLogin type-input="password" place-holder="Password" :reactive-cible="sendLogin" name-model="mdp"/>
            <ValidateButton submit-message="SIGN IN"/>
            <SuppLinkSign message="Se connecter en tant qu'utilisateur" action="Login" :action-function="changeLogin"/>
        </SignLoginForm>
    </GeneralContainLogin>
</template>
<style scoped>
</style>