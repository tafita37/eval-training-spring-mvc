<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertTypeDisque } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';
    import { useRoute } from 'vue-router';

    var typeDisque=reactive({});
    var responseInsertTypeDisque=reactive({});
    const route=useRoute();

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/disque/insertTypeDisque";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, typeDisque, {headers});
            response.then(reponse => {
                Object.assign(responseInsertTypeDisque, reponse.data);
                console.log(responseInsertTypeDisque);
                router.push("/disque/typeDisque/listeTypeDisque/1");
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    const findTypeDisqueById = () => {
        try {
            const idTypeDisque=route.params.idTypeDisque;
            const url=baseUrl+"/laptop/disque/findTypeDisqueById?idTypeDisque="+idTypeDisque;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.get(url, {headers});
            response.then(reponse => {
                Object.assign(typeDisque, reponse.data.data);
                console.log(typeDisque);
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    onMounted(() => {
        findTypeDisqueById();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="typeDisque" title-form="Type de Disque" description-form="Insertion" :liste-input="insertTypeDisque" :submit-function="handleSubmit" submit-message="Inserer"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>