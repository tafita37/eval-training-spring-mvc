<script setup> 
    import { reactive, ref, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
import router from '@/router';

    var nomTypeDisque=ref('');
    var responseInsertTypeDisque=reactive({});

    const handleSubmit = () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const url="http://localhost:8080/laptop/disque/insertTypeDisque";
            const data= {
                nomTypeDisque: nomTypeDisque.value
            };
            const response=axios.post(url, data, {headers});
            response.then(reponse => {
                Object.assign(responseInsertTypeDisque, reponse.data);
                console.log(responseInsertTypeDisque);
                router.push("/newDisque");
            })
            .catch(errors => {
                console.log(errors);
            })
        } catch(error) {
            console.log(error);
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        }
    });
</script>
<template>
    <FormInsert title-props="Inserer un nouveau type de disque" :handle-submit="handleSubmit" submit-message="Inserer le type de disque">
        <input v-model="nomTypeDisque" type="text" placeholder="Nom du type de disque" name="nomTypeDisque">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>