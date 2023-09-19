<template>
  <div class="register-container">
    <h1>Register</h1>
    <form @submit.prevent="register" class="register-form">
      <div class="input-group">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" v-model="firstName" required>
      </div>
      <div class="input-group">
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" v-model="lastName" required>
      </div>
      <div class="input-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required>
      </div>
      <div class="input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <button type="submit" class="register-button">Register</button>
      <button type="button" @click="goToLogin" class="register-button">Already have an account? Back to login</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserRegister',
  data() {
    return {
      firstName: '',
      lastName: '',
      email: '',
      password: ''
    }
  },
  methods: {
    async register() {
      try {
        const response = await axios.post('http://localhost:8080/auth/register', {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password
        });

        localStorage.setItem('jwtToken', response.data.token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
        this.$router.push('/');
      } catch (error) {
        console.error('An error occurred while registering:', error);
      }
    },
    goToLogin() {
      this.$router.push('/login');
    }
  }
}
</script>



<style scoped>
.register-container {
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

.register-button {
  width: 100%;
  background-color: #050a52;
  color: white;
  padding: 10px;
  border: 1px solid #050a52;
  border-radius: 0.25rem;
  cursor: pointer;
  margin-bottom: 1rem;
}

.register-button:hover {
  background-color: white;
  color: #050a52;
}
</style>
