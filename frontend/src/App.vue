<template>
  <div class="top-bar">
    <router-link class="title-link" to="/"><h1>My To-Do List</h1></router-link>
    <nav>
      <router-link to="/login">Login</router-link>
      <a @click="logout">Logout</a>
    </nav>
  </div>
  <router-view></router-view>
</template>

<script>
import axios from 'axios';
export default {
  name: 'App',
  methods: {
    logout() {
      // Remove the token from localStorage
      localStorage.removeItem('token');

      // Reset the Axios authorization header
      delete axios.defaults.headers.common['Authorization'];

      // Optionally, you can redirect the user to the login page or home page
      this.$router.push('/login');
    },
    // ... your other methods
  }
}

</script>

<style>
.top-bar {
  background-color: #050a52;
  color: #fff;
  padding: 2em 1em 2em 10em;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.top-bar nav {
  display: flex;
  gap: 50px;
  padding-right: 20px;
}

.title-link, .title-link h1 {
  color: #fff;
  margin: 0;
  padding: 0;
  text-decoration: none;
}

.top-bar nav a {
  color: #fff;
  text-decoration: none;
  font-size: 1.5em;
  cursor: pointer;
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
