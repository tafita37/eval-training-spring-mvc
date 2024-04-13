<script setup>
    import { onMounted } from 'vue';
    import InputFormGroupControl from './InputFormGroupControl.vue';
import SelectFormGroupControl from './SelectFormGroupControl.vue';
    import SubmitButton from './SubmitButton.vue';

    const props=defineProps(["listeInput", "listeSelect", "submitFunction", "reactiveReferenceObject", "submitMessage"]);

    const handleSubmit = () => {
        props.submitFunction();
    };
</script>
<template>
    <form class="forms-sample" @submit.prevent="handleSubmit">
        <InputFormGroupControl v-for="input in props.listeInput" :reactive-reference="input.reactiveReference" :reactive-reference-object="props.reactiveReferenceObject" :label-name="input.labelName" :input-type="input.inputType" :place-holder="input.placeHolder" :id="input.idHTML"/>
        <SelectFormGroupControl v-for="select in props.listeSelect" :label-name="select.labelName" :v-model="select.vModel" :sous-object="select.sousObject" :default-choice="select.defaultChoice" :id="select.idHTML" :boucle-object="select.boucleObject" :string-id="select.stringId" :string-afficher="select.stringAfficher"/>
        <SubmitButton :submit-message="props.submitMessage"/>
    </form>
</template>
<style scoped>
</style>