<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertStockCentral } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var stockLaptop=reactive({
        laptop : {
            idLaptop : ""
        }
    });
    var responseInsertStockLaptop=reactive({});
    const allLaptop=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/newStockLaptop";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, stockLaptop, {headers});
            response.then(reponse => {
                Object.assign(responseInsertStockLaptop, reponse.data);
                console.log(responseInsertStockLaptop);
                if(responseInsertStockLaptop.status==201) {
                    alert(responseInsertStockLaptop.message);
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
            labelName : "Laptop",
            idHTML : "inputLaptop",
            boucleObject : allLaptop,
            stringAfficher : "model",
            stringId : "idLaptop",
            vModel : stockLaptop,
            sousObject : "laptop",
            defaultChoice : "Choisissez un laptop"
        }
    ]

    onMounted(() => {
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
                    <FormulairePrincipal :reference-reactive-object="stockLaptop" title-form="Stock du magasin" 
                    description-form="Ajout" :liste-input="insertStockCentral" :submit-function="handleSubmit" 
                    submit-message="Ajouter" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>