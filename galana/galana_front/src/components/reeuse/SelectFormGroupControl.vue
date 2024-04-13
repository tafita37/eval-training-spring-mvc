<script setup>
    const props=defineProps(
        [
            "labelName", 
            "vModel", 
            "defaultChoice", 
            "id", 
            "boucleObject", 
            "stringId", 
            "stringAfficher", 
            "sousObject"
        ]
    );
</script>
<template>
    <div class="form-group">
        <label :for="props.id">
            {{ props.labelName }}
        </label>
        <select class="form-control" v-model="props.vModel[props.sousObject][props.stringId]" :id="props.id">
            <option value="">
                {{ props.defaultChoice }}
            </option>
            <option v-for="object in props.boucleObject.data" :value="object[props.stringId]">
                {{ object[props.stringAfficher] }}
            </option>
        </select>
    </div>
</template>
<style scoped>
    @import "@/assets/css/reeuse/inputFormGroup.css";
</style>