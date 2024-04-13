<script setup>
    const props=defineProps(["icon", "action", "actionLink"]);

    const getClassIcon = () => {
        return props.icon+" text-primary";
    }

    const handleSubmit = () => {
        props.actionLink();
    };
</script>
<template>
    <a class="dropdown-item" @click.prevent="handleSubmit">
        <i :class="getClassIcon()"></i> 
        {{ action }}
    </a>
</template>
<style scoped>
    @import "@/assets/css/themify-icons.css";
    .text-primary, .list-wrapper .completed NaNpxove {
        color: #4B49AC !important;
    }
    .dropdown-item i {
        font-size: 17px;
    }   

    .dropdown-item {
        font-size: .875rem;
    } 

    .dropdown-item {
        width: 100%;
        clear: both;
        font-weight: 400;
        color: #1F1F1F;
        text-align: inherit;
        white-space: nowrap;
        background-color: transparent;
        border: 0;
        display: flex;
        gap: 5px;
    }
</style>