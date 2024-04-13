<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertUsers } from '@/static/Constantes';
    import { reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';

    var user=reactive({
        nomUser : ""
    });
    var responseInsertUser=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/pdv/user/insertUser";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, user, {headers});
            response.then(reponse => {
                Object.assign(responseInsertUser, reponse.data);
                if(responseInsertUser.status==201) {
                    alert(responseInsertUser.message);
                }
            })
            .catch(errors => {
                console.log(errors);
                alert(errors.response.data.message);
            })
        } catch(error) {
            console.log(error);
        }
    };

</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="user" title-form="Utilisateur" description-form="Insertion" :liste-input="insertUsers" :submit-function="handleSubmit" submit-message="Inserer"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>