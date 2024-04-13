<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FormulairePrincipal from '../reeuse/FormulairePrincipal.vue';
    import { baseUrl, insertLaptopInput } from '@/static/Constantes';
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FooterOne from '../reeuse/FooterOne.vue';
    import router from '@/router';
    import { useRoute } from 'vue-router';

    var laptop=reactive({
        marque : {
            idMarque : ""
        },
        processeur : {
            idProcesseur : ""
        },
        ram : {
            idRam : ""
        },
        disque : {
            idDisque : ""
        }
    });
    var responseInsertLaptop=reactive({});
    const allMarque=reactive({});
    const allProcesseur=reactive({});
    const allRam=reactive({});
    const allDisque=reactive({});
    const route=useRoute();

    const handleSubmit = () => {
        try {
            const url=baseUrl+"/laptop/insertLaptop";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.post(url, laptop, {headers});
            response.then(reponse => {
                Object.assign(responseInsertLaptop, reponse.data);
                console.log(responseInsertLaptop);
                if(responseInsertLaptop.status==201) {
                    alert(responseInsertLaptop.message);
                    router.push("/laptop/listeLaptop/1");
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

    const getAllMarque = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/marque/allMarque';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allMarque, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const getAllProcesseur = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/processeur/allProcesseur';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allProcesseur, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const getAllRam = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/ram/allRam';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allRam, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const findLaptopById = () => {
        try {
            const idLaptop=route.params.idLaptop;
            const url=baseUrl+"/laptop/findLaptopById?idLaptop="+idLaptop;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response=axios.get(url, {headers});
            response.then(reponse => {
                Object.assign(laptop, reponse.data.data);
                console.log(laptop);
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    const getAllDisque = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/disque/allDisque';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allDisque, response.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const listeSelect = [
        {
            labelName : "Marque",
            idHTML : "inputMarque",
            boucleObject : allMarque,
            stringAfficher : "nomMarque",
            stringId : "idMarque",
            vModel : laptop,
            sousObject : "marque",
            defaultChoice : "Choisissez une de marque"
        },
        {
            labelName : "Processeur",
            idHTML : "inputProcesseur",
            boucleObject : allProcesseur,
            stringAfficher : "nomProcesseur",
            stringId : "idProcesseur",
            vModel : laptop,
            sousObject : "processeur",
            defaultChoice : "Choisissez un processeur"
        },
        {
            labelName : "Ram",
            idHTML : "inputRam",
            boucleObject : allRam,
            stringAfficher : "capacite",
            stringId : "idRam",
            vModel : laptop,
            sousObject : "ram",
            defaultChoice : "Choisissez une ram"
        },
        {
            labelName : "Disque dur",
            idHTML : "inputDisque",
            boucleObject : allDisque,
            stringAfficher : "capacite",
            stringId : "idDisque",
            vModel : laptop,
            sousObject : "disque",
            defaultChoice : "Choisissez un disque dur"
        }
    ]

    onMounted(() => {
        getAllMarque();
        getAllProcesseur();
        getAllRam();
        getAllDisque();
        findLaptopById();
    });
</script>
<template>
    <GeneralContain>
        <HeaderOne/>
        <SousGeneralContain>
            <MenuDeroulantOne/>
            <PrincipalContain>
                <SousPrincipal>
                    <FormulairePrincipal :reference-reactive-object="laptop" title-form="Laptop" description-form="Insertion" :liste-input="insertLaptopInput" :submit-function="handleSubmit" submit-message="Inserer" :liste-select="listeSelect"/>
                </SousPrincipal>
                <FooterOne/>
            </PrincipalContain>
        </SousGeneralContain>
    </GeneralContain>
</template>
<style scoped>
</style>