<template>
  <div class="top-bar">
    <router-link class="title-link" to="/"><h1>My To-Do List</h1></router-link>
    <nav v-if="showLogout">
      <a @click="logout">Logout</a>
    </nav>
  </div>
  <router-view></router-view>
</template>

<script>
import axios from 'axios';
export default {
  name: 'App',
  computed: {
    showLogout() {
      // Hide the Logout button on the /login and /register pages
      return this.$route.path !== '/login' && this.$route.path !== '/register';
    }
  },
  methods: {
    logout() {
      localStorage.removeItem('token');
      delete axios.defaults.headers.common['Authorization'];
      this.$router.push('/login');
    },
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

.top-bar nav a:hover {
  text-decoration: underline;
}

body {
  margin: 0;
  padding: 0;
  background: #fffb;
  font-family: 'Noto Sans', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #050a52;
}

</style>
