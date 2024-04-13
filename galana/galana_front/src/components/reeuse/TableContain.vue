<script setup>
import { onMounted } from 'vue';

    const props=defineProps(["listeElements", "modifFunction", "deleteFunction"]);

    const updateElement = () => {
        props.modifFunction();
    };

    const deleteElement = () => {
        props.deleteFunction();
    };
</script>
<template>
    <tr>
        <td v-for="element in props.listeElements">
            {{ element }}
        </td>
        <td v-if="props.modifFunction">
            <i class="fas fa-edit" @click="updateElement"></i>
        </td>
        <td v-if="props.deleteFunction">
            <i class="fas fa-trash" @click="deleteElement"></i>
        </td>
    </tr>
</template>
<style scoped>
    @import "@/assets/css/reeuse/tableContain.css";
    @import "@/assets/css/all.css";
    @import "@/assets/css/all.min.css";
</style>