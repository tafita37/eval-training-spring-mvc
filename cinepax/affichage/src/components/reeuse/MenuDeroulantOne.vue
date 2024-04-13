<script setup>
    import BeginDeroulant from './BeginDeroulant.vue';
    import ClickDeroulantMenu from './ClickDeroulantMenu.vue';
    import { listeClickDeroulant } from '@/static/Constantes.js';
    import { onMounted } from 'vue';
    import { animate, spring, timeline } from 'motion';

    onMounted(() => {
        const animation=animate(".mikolo", { scale : 1.1 }, 
        {
            duration : 1,
            easing : spring(),
            repeat : Infinity,
            direction : "alternate"
        })
    });
</script>
<template>
    <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
            <BeginDeroulant title-deroulant="Mikolo" class="mikolo"/>
            <ClickDeroulantMenu v-for="clickDeroulant in listeClickDeroulant" :key="clickDeroulant.id" :id-deroulant="clickDeroulant.id" :title-deroulant="clickDeroulant.titleDeroulant" :class-icon="clickDeroulant.classIcon" :pages-information="clickDeroulant.pages"/>
        </ul>
    </nav>
</template>
<style scoped>
    @import "@/assets/css/reeuse/menuDeroulant.css";
</style>