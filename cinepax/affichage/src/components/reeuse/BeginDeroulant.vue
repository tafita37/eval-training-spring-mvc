<script setup>
    const props=defineProps(["titleDeroulant"]);
</script>
<style scoped>
    @import "@/assets/css/reeuse/beginDeroulant.css";
</style>
<template>
    <li class="nav-item active">
        <a class="nav-link">
            <i class="icon-grid menu-icon"></i>
            <span class="menu-title">{{ props.titleDeroulant }}</span>
        </a>
    </li>
</template>