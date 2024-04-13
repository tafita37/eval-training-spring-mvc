<script setup>
    const props=defineProps(["submitMessage", "submitFunction"]);

    const handleSubmit = () => {
        props.submitFunction();
    };
</script>
<template>
    <button v-if="props.submitFunction" class="btn btn-primary mr-2" @click.prevent="handleSubmit">
        {{ props.submitMessage }}
    </button>
    <button v-else type="submit" class="btn btn-primary mr-2">
        {{ props.submitMessage }}
    </button>
</template>
<style scoped>
    @import "@/assets/css/reeuse/submitButton.css";
</style>