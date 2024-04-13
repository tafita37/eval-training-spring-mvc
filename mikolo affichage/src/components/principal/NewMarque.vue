<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertMarque } from '@/static/Constantes';
    import { reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var marque=reactive({
        nomMarque : ""
    });
    var responseInsertMarque=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/marque/insertMarque";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, marque, {headers});
            response.then(reponse => {
                Object.assign(responseInsertMarque, reponse.data);
                if(responseInsertMarque.status==201) {
                    router.push("/marque/listeMarque/1");
                }
            })
            .catch(errors => {
                console.log(errors.response);
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
                    <FormulairePrincipal :reference-reactive-object="marque" title-form="Marque" description-form="Insertion" 
                    :liste-input="insertMarque" :submit-function="handleSubmit" submit-message="Inserer"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>