<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertAffectation } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var pointVenteUser=reactive({
        pointVente : {
            idPointVente : ""
        },
        user : {
            idUser : ""
        } 
    });
    var responseInsertPdvUser=reactive({});
    const allPointVente=reactive({});
    const allUser=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/pdv/affectUserToPdv";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, pointVenteUser, {headers});
            response.then(reponse => {
                Object.assign(responseInsertPdvUser, reponse.data);
                console.log(responseInsertPdvUser);
                if(responseInsertPdvUser.status==201) {
                    alert(responseInsertPdvUser.message);
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

    const getAllUser = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/pdv/user/allUser';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allUser, response.data);
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
            vModel : pointVenteUser,
            sousObject : "pointVente",
            defaultChoice : "Choisissez un point de vente"
        },
        {
            labelName : "Utilisateurs",
            idHTML : "inputUser",
            boucleObject : allUser,
            stringAfficher : "nomUser",
            stringId : "idUser",
            vModel : pointVenteUser,
            sousObject : "user",
            defaultChoice : "Choisissez un utilisateur"
        }
    ]

    onMounted(() => {
        getAllPointVente();
        getAllUser();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="pointVenteUser" title-form="Point de vente et user" description-form="Insertion" :submit-function="handleSubmit" submit-message="Inserer" :liste-input="insertAffectation" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>