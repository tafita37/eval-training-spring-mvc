<script setup>
</script>
<template>
    <div class="main-panel">
        <slot>

        </slot>
    </div>
</template>
<style scoped>
    .main-panel {
        transition: width 0.25s ease, margin 0.25s ease;
        width: calc(100% - 235px);
        min-height: calc(100vh - 60px);
        display: -webkit-flex;
        display: flex;
        -webkit-flex-direction: column;
        flex-direction: column;
    }

    @media (max-width: 991px) {
        .main-panel {
            margin-left: 0;
            width: 100%;
        }
    }
    
</style>