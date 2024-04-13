<script setup>
</script>
<template>
    <ul class="navbar-nav navbar-nav-right">
        <slot></slot>
    </ul>
</template>
<style scoped>
    .flex-row, .email-wrapper .message-body .attachments-sections ul li, .navbar-nav {
        flex-direction: row !important;
    }

    .navbar-nav {
        display: flex;
        flex-direction: column;
        padding-left: 0;
        margin-bottom: 0;
        list-style: none;
    }
</style>