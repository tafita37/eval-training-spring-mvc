<script setup>
    const props=defineProps(["labelName", "inputType", "placeHolder", "id", "reactiveReference", "reactiveReferenceObject"]);
</script>
<template>
    <div class="form-group">
        <label :for="props.id">
            {{ props.labelName }}
        </label>
        <input :type="props.inputType" class="form-control" :id="props.id" :placeholder="props.placeHolder" v-model="props.reactiveReferenceObject[props.reactiveReference]">
    </div>
</template>
<style scoped>
    @import "@/assets/css/reeuse/inputFormGroup.css";
</style>