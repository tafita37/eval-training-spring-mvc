<script setup>
    import ListeButtonContain from './ListeButtonContain.vue';
import SubmitButton from './SubmitButton.vue';

    const props=defineProps(["listeButton"]);
</script>
<template>
    <ListeButtonContain>
        <SubmitButton 
            v-for="button in props.listeButton" 
            :submit-message="button.submitMessage" 
            :submit-function="button.submitFunction"
        />
    </ListeButtonContain>
</template>
<style>
    
</style>