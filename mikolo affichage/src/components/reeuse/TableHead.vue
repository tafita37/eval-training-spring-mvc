<script setup>
    const props=defineProps(["titleHeader"]);
</script>
<template>
    <thead>
        <tr>
            <th v-for="title in titleHeader">
                {{ title }}
            </th>
        </tr>
    </thead>
</template>
<style scoped>
    @import "@/assets/css/reeuse/tableHead.css";
</style>