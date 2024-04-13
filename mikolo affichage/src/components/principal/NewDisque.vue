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

    var disque=reactive({
        typeDisque : {
            idTypeDisque : ""
        }
    });
    var responseInsertDisque=reactive({});
    const allTypeDisque=reactive({});

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
                    router.push("listeDisque/1");
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
            // console.log(allTypeRam);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const listeSelect = [
        {
            labelName : "Type de disque",
            idHTML : "inputTypeDisque",
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
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="disque" title-form="Disque" description-form="Insertion" :liste-input="insertDisqueInput" :submit-function="handleSubmit" submit-message="Inserer" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>