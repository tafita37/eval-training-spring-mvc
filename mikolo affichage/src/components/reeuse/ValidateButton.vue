<script setup>
    const props=defineProps(["submitMessage"]);
</script>
<template>
    <div class="mt-3">
        <button class="btn-validate">
            {{ props.submitMessage }}
        </button>
    </div>
</template>
<style scoped>
    @import "@/assets/css/reeuse/validateButton.css";
</style>