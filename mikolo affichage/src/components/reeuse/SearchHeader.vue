<script setup>
    const props=defineProps(["placeHolderSearch", "searchFunction", "reactiveObject", "reactiveReference"]);

    const handleSubmit = () => {
        props.searchFunction();
    };
</script>
<template>
    <ul class="navbar-nav mr-lg-2">
        <li class="nav-item nav-search d-none d-lg-block">
            <div class="input-group">
                <div class="input-group-prepend hover-cursor" id="navbar-search-icon">
                    <span class="input-group-text" id="search">
                        <i class="icon-search"></i>
                    </span>
                </div>
                <form @submit.prevent="handleSubmit">
                    <input type="text" class="form-control" id="navbar-search-input" :placeholder="props.placeHolderSearch" v-if="props.reactiveObject!=null" v-model="props.reactiveObject[props.reactiveReference]">
                    <input type="text" class="form-control" id="navbar-search-input" :placeholder="props.placeHolderSearch" v-else>
                </form>
            </div>
        </li>
    </ul>
</template>
<style scoped>
    @import "@/assets/css/reeuse/searchHeader.css";
</style>