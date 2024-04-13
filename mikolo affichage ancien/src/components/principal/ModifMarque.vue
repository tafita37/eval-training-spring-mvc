<script setup> 
    import { reactive, ref, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const idMarque=route.query.idMarque;
    const nomMarque=ref('');
    var responseInsertMarque=reactive({});

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/laptop/marque/insertMarque";
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const data= {
                idMarque: idMarque,
                nomMarque: nomMarque.value
            };
            const response=axios.post(url, data, { headers });
            response.then(reponse => {
                Object.assign(responseInsertMarque, reponse.data);
                console.log(reponse.data);
                router.push("/allMarque");
            })
            .catch(errors => {
                alert(errors);
            })
            // router.push("/allMarque");
        } catch(error) {
            alert(error);
        }
    };
    
    const getMarqueById = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/marque/findMarqueById?idMarque='+idMarque;
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.get(urlApiSpringBoot, { headers });
            const status=response.data.status;
            const message=response.data.message;
            if(status!=200) {
                alert(message);
            }
            nomMarque.value = response.data.data.nomMarque;
        } catch (error) {
            if(error.response.status==403) {
                localStorage.removeItem("tokenMagasin");
                router.push("/");
            } else {
                alert(error);
            }
        }
    };

    onMounted(() => {
        if(!localStorage.getItem("tokenMagasin")) {
            router.push("/");
        } else {    
            getMarqueById();
        }
    });
    
</script>
<template>
    <FormInsert title-props="Modifier la marque" :handle-submit="handleSubmit" submit-message="Modifier la marque">
        <input v-model="nomMarque" type="text" placeholder="Nom de la marque" name="nomMarque">
    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>