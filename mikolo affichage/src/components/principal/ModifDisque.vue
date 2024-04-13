<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertDisqueInput } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';
    import { useRoute } from 'vue-router';

    var disque=reactive({
        typeDisque : {
            idTypeDisque : ""
        }
    });
    var responseInsertDisque=reactive({});
    const allTypeDisque=reactive({});
    const route=useRoute();

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/disque/insertDisque";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, disque, {headers});
            response.then(reponse => {
                Object.assign(responseInsertDisque, reponse.data);
                console.log(responseInsertDisque);
                if(responseInsertDisque.status==201) {
                    alert(responseInsertDisque.message);
                    router.push("/disque/listeDisque/1");
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

    const getAllTypeDisque = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/disque/allTypeDisque';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allTypeDisque, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const findDisqueById = async () => {
        try {
            const idDisque=route.params.idDisque;
            const urlApiSpringBoot = 'http://localhost:8080/laptop/disque/findDisqueById?idDisque='+idDisque;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(disque, response.data.data);
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    };

    const listeSelect = [
        {
            labelName : "Type de disque",
            idHTML : "inputTypeDisque",
            reactiveReference : "capacite",
            boucleObject : allTypeDisque,
            stringAfficher : "nomTypeDisque",
            stringId : "idTypeDisque",
            vModel : disque,
            sousObject : "typeDisque",
            defaultChoice : "Choisissez un type de disque"
        }
    ]

    onMounted(() => {
        getAllTypeDisque();
        findDisqueById();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="disque" title-form="Disque" description-form="Modification" :liste-input="insertDisqueInput" :submit-function="handleSubmit" submit-message="Modifier" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>