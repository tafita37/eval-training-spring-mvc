<script setup>
    const props=defineProps(['role', 'handleSubmit']);

    const handleSubmit = () => {
        props.handleSubmit();
    };
</script>
<template>
    <div class="content-wrapper d-flex align-items-center auth px-0">
        <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                <div class="brand-logo">
                    <img src="@/assets/images/logo.svg" alt="logo">
                </div>
                <h4>Se connecter en tant que {{ props.role }}</h4>
                <form class="pt-3" @submit.prevent="handleSubmit">
                    <slot>

                    </slot>
                </form>
            </div>
        </div>
    </div>
</template>
<style scoped>
    @import "@/assets/css/reeuse/signLoginForm.css";
</style>