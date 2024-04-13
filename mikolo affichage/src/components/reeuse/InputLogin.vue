<script setup>
    import { onMounted } from 'vue';

    const props=defineProps(["typeInput", "placeHolder", "reactiveCible", "nameModel"]);
</script>
<template>
    <div class="form-group">
        <input :type="props.typeInput" class="form-control form-control-lg" :placeholder="props.placeHolder" v-model="props.reactiveCible[props.nameModel]">
    </div>
</template>
<style scoped>
    @import "@/assets/css/reeuse/inputLogin.css";
</style>