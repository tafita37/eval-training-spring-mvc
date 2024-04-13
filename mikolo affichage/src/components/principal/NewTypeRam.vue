<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertTypeRam } from '@/static/Constantes';
    import { reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var typeRam=reactive({
        nomTypeRam : ""
    });
    var responseInsertTypeRam=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/ram/insertTypeRam";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, typeRam, {headers});
            response.then(reponse => {
                Object.assign(responseInsertTypeRam, reponse.data);
                console.log(responseInsertTypeRam);
                if(responseInsertTypeRam.status==201) {
                    router.push("listeTypeRam/1");
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
                    <FormulairePrincipal :reference-reactive-object="typeRam" title-form="Type de ram" description-form="Insertion" :liste-input="insertTypeRam" :submit-function="handleSubmit" submit-message="Inserer"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>