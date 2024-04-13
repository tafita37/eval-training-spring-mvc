<script setup>
    import CardDescription from './CardDescription.vue';
    import FormulaireConteneur from './FormulaireConteneur.vue';
    import H4CardTitle from './H4CardTitle.vue';
    import SampleForme from './SampleForme.vue';

    const props = defineProps(["titleForm", "descriptionForm", "listeInput", "listeSelect", "submitFunction", "referenceReactiveObject", "submitMessage"]);
</script>
<template>
    <FormulaireConteneur >
        <H4CardTitle :card-title="props.titleForm"/>
        <CardDescription :card-description="props.descriptionForm"/>
        <SampleForme :liste-select="props.listeSelect" :liste-input="props.listeInput" :submit-function="props.submitFunction" :reactive-reference-object="props.referenceReactiveObject" :submit-message="props.submitMessage"/>
    </FormulaireConteneur>
</template>
<style scoped>
</style>