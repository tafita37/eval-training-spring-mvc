<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantTwo from '../reeuse/MenuDeroulantTwo.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertRenvoieLaptop } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var renvoieLaptop=reactive({
        laptop : {
            idLaptop : ""
        },
        pointVente : {
            idPointVente : ""
        }
    });
    var responseInsertRenvoieLaptop=reactive({});
    const allLaptop=reactive({});
    const allPointVente=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/renvoieLaptop";
            const token=localStorage.getItem("tokenUser");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, renvoieLaptop, {headers});
            response.then(reponse => {
                Object.assign(responseInsertRenvoieLaptop, reponse.data);
                console.log(responseInsertRenvoieLaptop);
                if(responseInsertRenvoieLaptop.status==201) {
                    alert(responseInsertRenvoieLaptop.message);
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
            vModel : renvoieLaptop,
            sousObject : "pointVente",
            defaultChoice : "Choisissez un point de vente"
        },
        {
            labelName : "Laptop",
            idHTML : "inputLaptop",
            boucleObject : allLaptop,
            stringAfficher : "model",
            stringId : "idLaptop",
            vModel : renvoieLaptop,
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
            <MenuDeroulantTwo/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="renvoieLaptop" title-form="Stock du point de vente" 
                    description-form="Renvoie" :liste-input="insertRenvoieLaptop" :submit-function="handleSubmit" 
                    submit-message="Renvoyer" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>