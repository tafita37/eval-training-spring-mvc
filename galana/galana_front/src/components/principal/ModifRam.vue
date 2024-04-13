<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertRamInput } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';
    import { useRoute } from 'vue-router';

    var ram=reactive({
        typeRam : {
            idTypeRam : ""
        }
    });
    const route=useRoute();

    var responseInsertRam=reactive({});
    const allTypeRam=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/ram/insertRam";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, ram, {headers});
            response.then(reponse => {
                Object.assign(responseInsertRam, reponse.data);
                console.log(responseInsertRam);
                if(responseInsertRam.status==201) {
                    router.push("/ram/listeRam/1");
                }
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    const getAllTypeRam = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/ram/allTypeRam';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allTypeRam, response.data);
            // console.log(allTypeRam);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const findRamById = async () => {
        try {
            const idRam=route.params.idRam;
            const urlApiSpringBoot = 'http://localhost:8080/laptop/ram/findRamById?idRam='+idRam;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(ram, response.data.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const listeSelect = [
        {
            labelName : "Type de ram",
            idHTML : "inputTypeRam",
            reactiveReference : "capacite",
            boucleObject : allTypeRam,
            stringAfficher : "nomTypeRam",
            stringId : "idTypeRam",
            vModel : ram,
            sousObject : "typeRam",
            defaultChoice : "Choisissez un type de ram"
        }
    ]

    onMounted(() => {
        getAllTypeRam();
        findRamById();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="ram" title-form="Ram" description-form="Modifier" :liste-input="insertRamInput" :submit-function="handleSubmit" submit-message="Modifier" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>