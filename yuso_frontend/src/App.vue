<template>
  <div id="app">
    <IndexPage />
  </div>
</template>

<style>
#app {
  color: #2c3e50;
  padding: 20px;
  max-width: 1024px;
  /*
    全局最大宽度
  */
  margin: 0 auto;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
<script setup lang="ts">
import IndexPage from "@/views/IndexPage.vue";
</script>
