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
        emailUser : "taf.rand37@gmail.com",
        mdpUser : "0000"
    });
    var reponseLogin=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/pdv/user/loginUser";
            const response=axios.post(url, sendLogin);
            response.then(reponse => {
                Object.assign(reponseLogin, reponse.data);
                if(reponseLogin.status==200) {
                    localStorage.setItem("tokenUser", reponseLogin.data.token);
                    // console.log(localStorage.getItem("tokenUser"));
                    router.push("/pdv/laptop/receptionLaptop");
                } else {
                    alert(reponseLogin.message);
                }
            }).catch(errors => {
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
        if(localStorage.getItem("tokenMagasin")) {
            router.push("/laptop/listeLaptop/1");
        }
    });
</script>
<template>
    <GeneralContainLogin>  
        <SignLoginForm role="Utilisateur" :handle-submit="handleSubmit">
            <InputLogin type-input="text" place-holder="Username" :reactive-cible="sendLogin" name-model="emailUser"/>
            <InputLogin type-input="password" place-holder="Password" :reactive-cible="sendLogin" name-model="mdpUser"/>
            <ValidateButton submit-message="SIGN IN"/>
            <SuppLinkSign message="Don't have an account?" action="Create"/>
            <SuppLinkSign message="Se connecter en tant que magasin" action="Login" :action-function="changeLogin"/>
        </SignLoginForm>
    </GeneralContainLogin>
</template>
<style scoped>
</style>