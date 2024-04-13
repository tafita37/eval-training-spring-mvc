<script setup>
    const props=defineProps(["cardDescription"]);
</script>
<template>
    <p class="card-description">
        {{ props.cardDescription }}
    </p>
</template>
<style scoped>
.card-description {
    margin-bottom: .875rem;
    font-weight: 400;
    color: #76838f;
}
    
</style>