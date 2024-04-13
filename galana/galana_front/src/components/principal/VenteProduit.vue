<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertventeProduit, tokenStationName } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';

    const venteProduit=reactive({
        choix : {
            idChoix : ""
        },
        produit : {
            idProduit : ""
        }
    });
    var responseVenteProduit=reactive({});
    const allProduit=reactive({});
    const allChoix = reactive({
        data : [
            {
                idChoix : 1,
                nomChoix : "Montant"
            },
            {
                idChoix : 2,
                nomChoix : "Quantite"
            }
        ]
    })

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/produit/vendreProduit";
            const token=localStorage.getItem(tokenStationName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, venteProduit, {headers});
            response.then(reponse => {
                Object.assign(responseVenteProduit, reponse.data);
                console.log(responseVenteProduit);
                if(responseVenteProduit.status==201) {
                    alert(responseVenteProduit.message);
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

    const getAllProduit = async () => {
        try {
            const token=localStorage.getItem(tokenStationName);
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/produit/allProduit';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allProduit, response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const listeSelect = [
        {
            labelName : "Produit",
            idHTML : "inputProduit",
            boucleObject : allProduit,
            stringAfficher : "nomProduit",
            stringId : "idProduit",
            vModel : venteProduit,
            sousObject : "produit",
            defaultChoice : "Choisissez un Produit"
        },
        {
            labelName : "Choix",
            idHTML : "inputChoix",
            boucleObject : allChoix,
            stringAfficher : "nomChoix",
            stringId : "idChoix",
            vModel : venteProduit,
            sousObject : "choix",
            defaultChoice : "Choisissez"
        }
    ]

    onMounted(() => {
        getAllProduit();
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
                        :reference-reactive-object="venteProduit" 
                        title-form="Vente de produit" 
                        description-form="Vente" 
                        :liste-input="insertventeProduit" 
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