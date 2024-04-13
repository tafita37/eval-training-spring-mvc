<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertReceptionRenvoieLaptop } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';

    var receptionRenvoieLaptop=reactive({
        renvoieLaptop : {
            idRenvoieLaptop : ""
        }
    });
    var responseInsertReceptionRenvoieLaptop=reactive({});
    const allRenvoieLaptop=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/receptionRenvoieLaptop";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, receptionRenvoieLaptop, {headers});
            response.then(reponse => {
                Object.assign(responseInsertReceptionRenvoieLaptop, reponse.data);
                console.log(responseInsertReceptionRenvoieLaptop);
                if(responseInsertReceptionRenvoieLaptop.status==201) {
                    alert(responseInsertReceptionRenvoieLaptop.message);
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

    const getAllRenvoieLaptop = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/allRenvoieLaptop';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allRenvoieLaptop, response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const listeSelect = [
        {
            labelName : "Renvoie",
            idHTML : "inputRenvoieLaptop",
            boucleObject : allRenvoieLaptop,
            stringAfficher : "idRenvoieLaptop",
            stringId : "idRenvoieLaptop",
            vModel : receptionRenvoieLaptop,
            sousObject : "renvoieLaptop",
            defaultChoice : "Choisissez un renvoie de laptop"
        }
    ]

    onMounted(() => {
        getAllRenvoieLaptop();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="receptionRenvoieLaptop" 
                    title-form="Reception de renvoie de laptop" description-form="Reception de renvoie" 
                    :liste-input="insertReceptionRenvoieLaptop" :submit-function="handleSubmit" submit-message="Recu" 
                    :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>