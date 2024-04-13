<script setup>
    const props=defineProps(["cardTitle"]);
</script>
<template>
    <h4 class="card-title">
        {{ props.cardTitle }}
    </h4>
</template>
<style>
.card-title {
    margin-bottom: 0.75rem;
    color: #010101;
    margin-bottom: 1.2rem;
    text-transform: capitalize;
    font-size: 1.125rem;
    font-weight: 600;
}
</style>