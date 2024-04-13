<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertProduit, tokenAdminName } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';
    import SubmitButton from '../reeuse/SubmitButton.vue';

    var produit=reactive({});
    var responseInsertProduit=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/produit/insertProduit";
            const token=localStorage.getItem(tokenAdminName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, produit, { headers });
            response.then(reponse => {
                Object.assign(responseInsertProduit, reponse.data);
                if(responseInsertProduit.status==201) {
                    // router.push("/produit/listeproduit/1");
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

    onMounted(() => {
        const obj={id:1, nom:"sfdcds"};
        console.log(Object.keys(obj));
    });

</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal 
                        :reference-reactive-object="produit" 
                        title-form="Produit" 
                        description-form="Insertion" 
                        :liste-input="insertProduit" :submit-function="handleSubmit" submit-message="Inserer"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
        <SubmitButton submit-message="Exporter en CSV" :submit-function="exportToCSV"/>
    </GeneralContain>
</template>
<style scoped>
</style>