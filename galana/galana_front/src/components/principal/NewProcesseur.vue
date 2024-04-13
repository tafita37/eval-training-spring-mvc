<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertProcesseur } from '@/static/Constantes';
    import { reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var processeur=reactive({
        nomProcesseur : ""
    });
    var responseInsertProcesseur=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/processeur/insertProcesseur";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, processeur, {headers});
            response.then(reponse => {
                Object.assign(responseInsertProcesseur, reponse.data);
                console.log(responseInsertProcesseur);
                if(responseInsertProcesseur.status==201) {
                    router.push("/processeur/listeProcesseur/1");
                }
            })
            .catch(errors => {
                alert(errors.response.data.message);
                console.log(errors);
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
                    <FormulairePrincipal :reference-reactive-object="processeur" title-form="Processeur" description-form="Insertion" :liste-input="insertProcesseur" :submit-function="handleSubmit" submit-message="Inserer"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>