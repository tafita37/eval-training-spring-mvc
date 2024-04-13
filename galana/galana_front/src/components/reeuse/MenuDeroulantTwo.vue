<script setup>
    import BeginDeroulant from './BeginDeroulant.vue';
    import ClickDeroulantMenu from './ClickDeroulantMenu.vue';
    import { listeClickDeroulantTwo } from '@/static/Constantes.js';
</script>
<style scoped>
    @import "@/assets/css/reeuse/menuDeroulant.css";
</style>
<template>
    <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
            <BeginDeroulant title-deroulant="Mikolo"/>
            <ClickDeroulantMenu v-for="clickDeroulant in listeClickDeroulantTwo" :key="clickDeroulant.id" :id-deroulant="clickDeroulant.id" :title-deroulant="clickDeroulant.titleDeroulant" :class-icon="clickDeroulant.classIcon" :pages-information="clickDeroulant.pages"/>
        </ul>
    </nav>
</template>