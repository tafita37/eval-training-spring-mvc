<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertTypeRam } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';
    import { useRoute } from 'vue-router';

    var typeRam=reactive({});
    var responseInsertTypeRam=reactive({});
    const route=useRoute();

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
                router.push("/ram/typeRam/listeTypeRam/1");
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    const findTypeRamById = () => {
        try {
            const idTypeRam=route.params.idTypeRam;
            const url=baseUrl+"/laptop/ram/findTypeRamById?idTypeRam="+idTypeRam;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.get(url, {headers});
            response.then(reponse => {
                Object.assign(typeRam, reponse.data.data);
                console.log(typeRam);
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    onMounted(() => {
        findTypeRamById();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="typeRam" title-form="Type de ram" description-form="Insertion" :liste-input="insertTypeRam" :submit-function="handleSubmit" submit-message="Modifier"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>