<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, transfertStockLaptop } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var transfertLaptop=reactive({
        laptop : {
            idLaptop : ""
        },
        pointVente : {
            idPointVente : ""
        }
    });
    var responseInsertTransfertLaptop=reactive({});
    const allLaptop=reactive({});
    const allPointVente=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/transfererLaptop";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, transfertLaptop, {headers});
            response.then(reponse => {
                Object.assign(responseInsertTransfertLaptop, reponse.data);
                console.log(responseInsertTransfertLaptop);
                if(responseInsertTransfertLaptop.status==201) {
                    alert(responseInsertTransfertLaptop.message);
                    // router.push("listeStockLaptop/1");
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

    const getAllPointVente = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/pdv/allPointVente';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allPointVente, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const getAllLaptop = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/allLaptop';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allLaptop, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const listeSelect = [
        {
            labelName : "Point de vente",
            idHTML : "inputPointVente",
            boucleObject : allPointVente,
            stringAfficher : "nomPointVente",
            stringId : "idPointVente",
            vModel : transfertLaptop,
            sousObject : "pointVente",
            defaultChoice : "Choisissez un point de vente"
        },
        {
            labelName : "Laptop",
            idHTML : "inputLaptop",
            boucleObject : allLaptop,
            stringAfficher : "model",
            stringId : "idLaptop",
            vModel : transfertLaptop,
            sousObject : "laptop",
            defaultChoice : "Choisissez un laptop"
        }
    ]

    onMounted(() => {
        getAllPointVente();
        getAllLaptop();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="transfertLaptop" title-form="Stock du magasin" 
                    description-form="Transfert" :liste-input="transfertStockLaptop" :submit-function="handleSubmit" 
                    submit-message="Transferer" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>