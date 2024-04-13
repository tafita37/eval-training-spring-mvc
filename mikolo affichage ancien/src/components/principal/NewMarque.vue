<script setup> 
    import { reactive, ref, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var nomMarque=ref('');
    var responseInsertMarque=reactive({});

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/laptop/marque/insertMarque";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const data= {
                nomMarque: nomMarque.value
            };
            const response=axios.post(url, data, {headers});
            response.then(reponse => {
                Object.assign(responseInsertMarque, reponse.data);
                console.log(responseInsertMarque);
                router.push("/allMarque")
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
    <FormInsert title-props="Inserer une nouvelle marque" :handle-submit="handleSubmit" submit-message="Inserer la marque">
        <input v-model="nomMarque" type="text" placeholder="Nom de la marque" name="nomMarque">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>