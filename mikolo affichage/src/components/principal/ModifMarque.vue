<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertMarque } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';
    import { useRoute } from 'vue-router';

    var marque=reactive({});
    var responseInsertMarque=reactive({});
    const route=useRoute();

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
                console.log(responseInsertMarque);
                router.push("/marque/listeMarque/1");
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    const findMarqueById = () => {
        try {
            const idMarque=route.params.idMarque;
            const url=baseUrl+"/laptop/marque/findMarqueById?idMarque="+idMarque;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.get(url, {headers});
            response.then(reponse => {
                Object.assign(marque, reponse.data.data);
                console.log(marque);
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    onMounted(() => {
        findMarqueById();
    });

</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="marque" title-form="Marque" description-form="Modification" :liste-input="insertMarque" :submit-function="handleSubmit" submit-message="Modifier"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>