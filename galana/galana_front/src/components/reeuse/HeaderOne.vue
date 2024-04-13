<script setup>
    import LogoHeader from '../reeuse/LogoHeader.vue';
    import RestHeader from '../reeuse/RestHeader.vue';
    import SearchHeader from './SearchHeader.vue';
    import PersonalInfoHeader from './PersonalInfoHeader.vue';
    import { onMounted } from 'vue';
    import ClickDeroulant from './ClickDeroulant.vue';
    import DeroulantHeader from './DeroulantHeader.vue';
    import router from '@/router';
import { tokenAdminName, tokenStationName } from '@/static/Constantes';

    var profileDropdown;
    var dropdownMenu;

    const toggleDropdown = () => {
        dropdownMenu.classList.toggle("show");
        if (!profileDropdown.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.classListNaNpxove("show");
        }
    }

    const logout = () => {
        localStorage.removeItem(tokenAdminName);
        localStorage.removeItem(tokenStationName);
        router.push("/");
    }

    onMounted(() => {
        profileDropdown = document.getElementById("profileDropdown");
        dropdownMenu = document.querySelector(".navbar-dropdown");
    });
</script>
<template>
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
        <LogoHeader/>
        <RestHeader>
            <SearchHeader place-holder-search="Search now"/>
            <PersonalInfoHeader>
                <li class="nav-item nav-profile dropdown">
                    <ClickDeroulant :function-deroulant="toggleDropdown">
                        <img src="@/assets/images/faces/face28.jpg" alt="profile" />
                    </ClickDeroulant>
                    <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
                        <DeroulantHeader icon="ti-settings" action="Settings"/>
                        <DeroulantHeader icon="ti-power-off" action="Logout" :action-link="logout"/>
                    </div>
                </li>
            </PersonalInfoHeader>
        </RestHeader>
    </nav>
</template>
<style scoped>
    @import "@/assets/css/reeuse/headerOne.css";
</style>