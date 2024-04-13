<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantTwo from '../reeuse/MenuDeroulantTwo.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertReceptionLaptop } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var receptionLaptop=reactive({
        transfertLaptop : {
            idTransfertLaptop : ""
        }
    });
    var responseInsertReceptionLaptop=reactive({});
    const allTransfertLaptop=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/receptionLaptop";
            const token=localStorage.getItem("tokenUser");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, receptionLaptop, {headers});
            response.then(reponse => {
                Object.assign(responseInsertReceptionLaptop, reponse.data);
                console.log(responseInsertReceptionLaptop);
                if(responseInsertReceptionLaptop.status==201) {
                    alert(responseInsertReceptionLaptop.message);
                }
            }).catch(errors => {
                console.log(errors);
                alert(errors.response.data.message);
            })
        } catch(error) {
            console.log(error);
        }
    };

    const getAllTransfertLaptop = async () => {
        try {
            const token=localStorage.getItem("tokenUser");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/allTransfertLaptop';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allTransfertLaptop, response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const listeSelect = [
        {
            labelName : "Transfert",
            idHTML : "inputTransfertLaptop",
            boucleObject : allTransfertLaptop,
            stringAfficher : "idTransfertLaptop",
            stringId : "idTransfertLaptop",
            vModel : receptionLaptop,
            sousObject : "transfertLaptop",
            defaultChoice : "Choisissez un tranfert de laptop"
        }
    ]

    onMounted(() => {
        getAllTransfertLaptop();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantTwo/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="receptionLaptop" title-form="Reception de laptop" description-form="Reception" :liste-input="insertReceptionLaptop" :submit-function="handleSubmit" submit-message="Recu" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>