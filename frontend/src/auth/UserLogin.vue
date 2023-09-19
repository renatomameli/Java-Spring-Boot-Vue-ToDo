<template>
  <div class="login-container">
    <h1>Login</h1>
    <form @submit.prevent="login" class="login-form">
      <div class="input-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" />
      </div>
      <div class="input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" />
      </div>
      <button type="submit" class="login-button">Login</button>
    </form>
    <div v-if="loginFailed" class="error-message">Login failed. Please try again.</div>
    <div @click="register" class="center-container">
      <a >No User yet? Register!</a>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserLogin',
  data() {
    return {
      email: '',
      password: '',
      loginFailed: false
    }
  },
  methods: {

    async login() {
      try {
        const response = await axios.post('http://localhost:8080/auth/login', {
          email: this.email,
          password: this.password
        });

        axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
        localStorage.setItem('token', response.data.token);
        this.$router.push('/');

      } catch (error) {
        this.loginFailed = true;
      }
    },

    register() {
      this.$router.push('/register');
    }
  }
}
</script>

<style scoped>
.login-container {
  margin: 2rem auto;
  max-width: 400px;
  padding: 1rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

h1 {
  text-align: center;
  margin-bottom: 1rem;
}

.input-group {
  margin-bottom: 1rem;
}

label {
  display: inline-block;
  width: 100px;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.login-button {
  font-size: 1rem;
  width: 100%;
  background-color: #050a52;
  color: white;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
  border: 1px solid transparent;
}

.login-button:hover {
  background-color: #fff;
  color: #050a52;
  border-color: #050a52;
}

.error-message {
  color: red;
  text-align: center;
  margin-top: 1rem;
}

.center-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 1rem;
  border: 1px solid #050a52;
  border-radius: 0.25rem;
  cursor: pointer;
  padding: 0.5rem;
  color: white;
  margin-bottom: 20px;
  background: #050a52;
}

.center-container:hover {
  color: #050a52;
  background: white;
}
</style>
