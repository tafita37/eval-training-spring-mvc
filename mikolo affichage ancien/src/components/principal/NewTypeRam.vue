<script setup> 
    import { reactive, ref, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
import router from '@/router';

    var nomTypeRam=ref('');
    var responseInsertTypeRam=reactive({});

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/laptop/ram/insertTypeRam";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const data= {
                nomTypeRam: nomTypeRam.value
            };
            const response=axios.post(url, data, { headers });
            response.then(reponse => {
                Object.assign(responseInsertTypeRam, reponse.data);
                console.log(responseInsertTypeRam);
                router.push("/newRam");
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
    <FormInsert title-props="Inserer une nouveau type de ram" :handle-submit="handleSubmit" submit-message="Inserer le type de ram">
        <input v-model="nomTypeRam" type="text" placeholder="Nom du type de ram" name="nomTypeRam">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>