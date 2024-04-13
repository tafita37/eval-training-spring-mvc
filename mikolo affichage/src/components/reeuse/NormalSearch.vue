<script setup>
import { onMounted } from 'vue';
import SubmitButton from './SubmitButton.vue';

    const props=defineProps(
        ["placeHolderSearch", "searchFunction", "reactiveObject", "reactiveReference", "listeSelect", "listeInput"]
    );

    const handleSubmit = () => {
        props.searchFunction();
    };

    onMounted(() => {
        console.log(props.listeSelect);
    });
</script>
<template>
    <ul class="navbar-nav mr-lg-2">
        <li class="nav-item nav-search d-none d-lg-block">
            <div class="input-group">
                <form @submit.prevent="handleSubmit">
                    <input :type="input.typeInput" class="form-control" v-for="input in props.listeInput" 
                    id="navbar-search-input" :placeholder="input.placeHolder" 
                    v-model="props.reactiveObject[input.reactiveReference]">
                    <select v-model="props.reactiveObject[select.sousObject][select.stringId]" class="form-control" 
                    v-for="select in props.listeSelect">
                        <option :value=select.defaultValue>
                            {{ select.defaultChoice }}
                        </option>
                        <option v-for="object in select.boucleObject" :value="object[select.stringId]">
                            {{ object[select.stringAfficher] }}
                        </option>
                    </select>
                    <SubmitButton submit-message="Rechercher"/>
                </form>
            </div>
        </li>
    </ul>
</template>
<style scoped>
    @import "@/assets/css/reeuse/normalSearch.css";
    @import "@/assets/css/feather.css";
</style>