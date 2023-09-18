<template>
  <div>
    <h1>Login</h1>
    <form @submit.prevent="login">
      <div>
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" />
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" />
      </div>
      <button type="submit">Login</button>
    </form>
    <div class="center-container">
      <a @click="register">No User yet? Register!</a>
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
      password: ''
    }
  },
  methods: {

    async login() {
      try {
        const response = await axios.post('http://localhost:8080/auth/login', {
          email: this.email,
          password: this.password
        });

        // Save token to Axios header for future requests
        axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;

        // You can also save the token in localStorage or Vuex for later use
        localStorage.setItem('token', response.data.token);
        this.$router.push('/');

        // Handle the response as needed
        console.log('User logged in:', response.data);
      } catch (error) {
        console.error('An error occurred while logging in:', error);
      }
    },

    register() {
      this.$router.push('/register');
    }
  }
}
</script>

<style scoped>
h1 {
  text-align: center;
  margin-bottom: 1rem;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

div {
  margin-bottom: 1rem;
}

label {
  font-weight: bold;
}

input[type="email"],
input[type="password"] {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 0.25rem;
  width: 100%;
  max-width: 300px;
}

button[type="submit"] {
  padding: 0.5rem 1rem;
  background-color: #007bff;
  border: 1px solid #007bff;
  color: white;
  border-radius: 0.25rem;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}

a {
  display: inline-block;
  margin-top: 1rem;
  border: 2px solid #007bff;
  padding: 0.25rem 0.5rem;
  color: #007bff;
  text-decoration: none;
  border-radius: 0.25rem;
  cursor: pointer;
}

a:hover {
  background-color: #007bff;
  color: white;
}

.center-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 1rem;
}
</style>
