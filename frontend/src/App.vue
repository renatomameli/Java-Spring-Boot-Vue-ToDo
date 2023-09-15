<template>
  <div class="top-bar">
    <h1>My To-Do List</h1>
  </div>
  <HelloWorld msg="Welcome to Your Vue.js App"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
.top-bar {
  background-color: #050a52;
  color: #fff;
  padding: 1em 1em 1em 10em;
}

body {
  margin: 0;
  padding: 0;
  background: #fffb;
  font-family: 'Noto Sans', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

</style>
