<script setup>

</script>

<template>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">E.SUN Vote</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <RouterLink to="/user" class="nav-link">使用者投票</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink to="/admin" class="nav-link">後台管理</RouterLink>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>
    
<style scoped></style>