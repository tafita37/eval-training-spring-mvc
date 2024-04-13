<script setup>
import GeneralContain from './GeneralContain.vue';

</script>
<template>
    <GeneralContain>
        <div class="container-fluid page-body-wrapper full-page-wrapper">
            <slot>

            </slot>
        </div>
    </GeneralContain>
</template>
<style scoped>
    @import "@/assets/css/reeuse/generalContainLogin.css";
</style>