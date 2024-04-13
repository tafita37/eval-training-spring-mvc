<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertVenteLaptop } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var venteLaptop=reactive({
        laptop : {
            idLaptop : ""
        },
        pointVente : {
            idPointVente : ""
        }
    });
    var responseVenteLaptop=reactive({});
    const allLaptop=reactive({});
    const allPointVente=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/vendreLaptop";
            const token=localStorage.getItem("tokenUser");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, venteLaptop, {headers});
            response.then(reponse => {
                Object.assign(responseVenteLaptop, reponse.data);
                console.log(responseVenteLaptop);
                if(responseVenteLaptop.status==201) {
                    alert(responseVenteLaptop.message);
                    // router.push("listeRam/1");
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
            const token=localStorage.getItem("tokenUser");
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
            const token=localStorage.getItem("tokenUser");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/allLaptop';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allLaptop, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                localStorage.removeItem("tokenMagasin");
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
            vModel : venteLaptop,
            sousObject : "laptop",
            defaultChoice : "Choisissez un laptop"
        },
        {
            labelName : "Point de vente",
            idHTML : "inputPointVente",
            boucleObject : allPointVente,
            stringAfficher : "nomPointVente",
            stringId : "idPointVente",
            vModel : venteLaptop,
            sousObject : "pointVente",
            defaultChoice : "Choisissez un point de vente"
        }
    ]

    onMounted(() => {
        getAllLaptop();
        getAllPointVente();
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
                        :reference-reactive-object="venteLaptop" 
                        title-form="Laptop" 
                        description-form="Vente" 
                        :liste-input="insertVenteLaptop" 
                        :submit-function="handleSubmit" 
                        submit-message="Vendre" 
                        :liste-select="listeSelect"
                    />
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>