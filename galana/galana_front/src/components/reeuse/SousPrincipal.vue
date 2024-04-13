<script setup>
</script>
<template>
    <div class="content-wrapper">
        <div class="row">
            <slot>
                
            </slot>
        </div>
    </div>
</template>
<style scoped>
.content-wrapper {
    background: #F5F7FF;
    padding: 2.375rem 2.375rem;
    width: 100%;
    -webkit-flex-grow: 1;
    flex-grow: 1;
}

@media (max-width: 767px) {
    .content-wrapper {
        padding: 1.5rem 1.5rem;
    }
}

.row {
  display: flex;
  flex-wrap: wrap;
  margin-right: -15px;
  margin-left: -15px;
}   
</style>