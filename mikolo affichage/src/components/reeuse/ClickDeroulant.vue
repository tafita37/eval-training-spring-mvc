<script setup>
    const props=defineProps(["functionDeroulant"]);

    const derouler = () => {
        props.functionDeroulant();
    }
</script>
<template>
    <a class="nav-link dropdown-toggle" href="#" @click.prevent="derouler" id="profileDropdown">
        <slot>
            
        </slot>
    </a>
</template>
<style scoped>
    .nav-link {
        display: block;
        padding: .5rem 1rem;
        padding-right: 0;
        padding-left: 0;
        color: inherit;
        font-size: 1rem;
    }
</style>