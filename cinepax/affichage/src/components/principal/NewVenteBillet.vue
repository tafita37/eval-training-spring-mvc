<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertplaceVenteBilletInput } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';

    var placeVenteBillet=reactive({
        place : {
            idPlace : ""
        },
        venteBillet : {
            billet : {
                idBillet : ""
            }
        }
    });
    var responseInsertPlaceVenteBillet=reactive({});
    const allBillet=reactive({});
    const allPlace=reactive({});

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/billet/vendreBillet";
            const response=axios.post(url, placeVenteBillet);
            response.then(reponse => {
                Object.assign(responseInsertPlaceVenteBillet, reponse.data);
                console.log(responseInsertPlaceVenteBillet);
                if(responseInsertPlaceVenteBillet.status==201) {
                    alert(responseInsertPlaceVenteBillet.message);
                    // router.push("listeplaceVenteBillet/1");
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

    const getAllBillet = async () => {
        try {
            const urlApiSpringBoot = baseUrl+'/billet/allBillet';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allBillet, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const getAllPlace = async () => {
        try {
            const urlApiSpringBoot = baseUrl+'/billet/place/allPlace';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allPlace, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const listeSelect = [
        {
            labelName : "Billet",
            idHTML : "inputMarque",
            boucleObject : allBillet,
            stringAfficher : "nomMarque",
            stringId : "idMarque",
            vModel : placeVenteBillet,
            sousObject : "marque",
            defaultChoice : "Choisissez une de marque"
        },
        {
            labelName : "Place",
            idHTML : "inputPlace",
            boucleObject : allPlace,
            stringAfficher : "nomPlace",
            stringId : "idPlace",
            vModel : placeVenteBillet,
            sousObject : "Place",
            defaultChoice : "Choisissez un Place"
        },
        {
            labelName : "Ram",
            idHTML : "inputRam",
            boucleObject : allRam,
            stringAfficher : "capacite",
            stringId : "idRam",
            vModel : placeVenteBillet,
            sousObject : "ram",
            defaultChoice : "Choisissez une ram"
        },
        {
            labelName : "Disque dur",
            idHTML : "inputDisque",
            boucleObject : allDisque,
            stringAfficher : "capacite",
            stringId : "idDisque",
            vModel : placeVenteBillet,
            sousObject : "disque",
            defaultChoice : "Choisissez un disque dur"
        }
    ]

    onMounted(() => {
        getAllBillet();
        getAllPlace();
        getAllRam();
        getAllDisque();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="placeVenteBillet" title-form="placeVenteBillet" description-form="Insertion" :liste-input="insertplaceVenteBilletInput" :submit-function="handleSubmit" submit-message="Inserer" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>