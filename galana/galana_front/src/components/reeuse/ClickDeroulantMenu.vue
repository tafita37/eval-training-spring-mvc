<script setup>
    import router from '@/router';
import { onMounted } from 'vue';

    const props=defineProps(["idDeroulant", "titleDeroulant", "classIcon", "pagesInformation"]);

    var profileDropdown;
    var dropdownMenu;
    var navItem;

    const toggleDropdown = (id) => {
        if(dropdownMenu!=null) {
            dropdownMenu.classList.toggle("show");
        }
        navItem.classList.toggle("active");
        // if (!profileDropdown.contains(event.target) && !dropdownMenu.contains(event.target)) {
        //     dropdownMenu.classList.remove("show");
        // }
        // console.log(dropdownMenu);
    }

    const changePath = (path) => {
        router.push(path);
    }

    onMounted(() => {
        profileDropdown = document.querySelector(".nav-link");
        navItem = document.querySelector(`.nav-item[data-key="${props.idDeroulant}"]`);
        if(navItem!=null) {
            dropdownMenu = navItem.querySelector(".collapse");
        }
    });
</script>
<template>
    <li class="nav-item" :key="props.idDeroulant" :data-key="props.idDeroulant">
        <a class="nav-link" @click.native="() => toggleDropdown(props.idDeroulant)">
            <i :class="props.classIcon"></i>
            <span class="menu-title">{{ props.titleDeroulant }}</span>
        </a>
        <div class="collapse" id="ui-basic">
            <ul class="nav flex-column sub-menu">
                <li class="nav-item liste-pages" v-for="page in props.pagesInformation" :key="page.id">
                    <a class="nav-link" @click.prevent="() => changePath(page.path)">
                        {{ page.name }}
                    </a>
                </li>
            </ul>
        </div>
    </li>
</template>
<style scoped>
    @import "@/assets/css/reeuse/clickDeroulantMenu.css";
</style>