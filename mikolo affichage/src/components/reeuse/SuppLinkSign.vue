<script setup>
    const props=defineProps(["message", "action", "actionFunction"]);

    const handleSubmit = () => {
        props.actionFunction();
    };
</script>
<template>
    <div class="text-center mt-4 font-weight-light">
        {{ props.message }} <a @click.prevent="handleSubmit" class="text-primary">{{ props.action }}</a>
    </div>
</template>
<style scoped>
    @import "@/assets/css/reeuse/suppLinkSign.css";
</style>