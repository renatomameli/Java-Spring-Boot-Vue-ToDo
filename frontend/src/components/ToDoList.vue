<template>
  <div>
    <div class="add-item">
      <input type="text" v-model="newTodo" placeholder="New Todo" />
      <input type="date" v-model="newDeadline" placeholder="Deadline" />
      <button @click="addTodo">Add</button>
    </div>
    <div class="todo-list">
      <ul>
        <li v-for="todo in todos" :key="todo.id" class="todo-item">
          <div class="todo-left">
            <input type="checkbox" :checked="todo.done" @change="toggleDone(todo.id)" />
            <span>{{ todo.description }}</span>
          </div>
          <div class="todo-right">
            <span class="deadline">{{ formatDate(todo.deadline) }}</span>
            <i @click="deleteTodo(todo.id)" class="fas fa-trash-alt"></i>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      todos: [],
      newTodo: '',
      newDeadline: '',
      isLoggedIn: false
    };
  },
  methods: {
    async toggleDone(todoId) {
      try {
        const response = await axios.put(`http://localhost:8080/to-dos/${todoId}`, null, {
          headers: {
            'Content-Type': 'application/json',
          },
        });

        if (response.status === 204 || response.status === 200) {
          await this.fetchTodos();
        } else {
          console.error(`Failed to toggle to-do with id ${todoId}`);
        }
      } catch (error) {
        console.error(`An error occurred while toggling the to-do: ${error}`);
      }
    },

    async deleteTodo(todoId) {
      try {
        const response = await axios.delete(`http://localhost:8080/to-dos/${todoId}`);

        if (response.status === 204 || response.status === 200) {
          await this.fetchTodos();
        } else {
          console.error(`Failed to delete to-do with id ${todoId}`);
        }
      } catch (error) {
        console.error(`An error occurred while deleting the to-do: ${error}`);
      }
    },

    async fetchTodos() {
      try {
        const response = await axios.get("http://localhost:8080/to-dos");
        this.todos = response.data;
      } catch (error) {
        if (error.response && error.response.status === 403) {
          delete axios.defaults.headers.common['Authorization'];
          localStorage.removeItem('token');
          this.$router.push('/login');
        }
      }
    },

    async addTodo() {
      if (this.newTodo) {
        const newTodoItem = {
          description: this.newTodo,
          deadline: this.newDeadline
        };

        try {
          const response = await axios.post('http://localhost:8080/to-dos', newTodoItem, {
            headers: {
              'Content-Type': 'application/json',
            }
          });

          if (response.status === 200 || response.status === 201) {
            await this.fetchTodos();
            this.newTodo = '';
            this.newDeadline = '';
          }
        } catch (error) {
          console.error('There was a problem with the fetch operation:', error);
        }
      }
    },

    formatDate(date) {
      if (!date) return "";
      const d = new Date(date);
      const day = d.getDate().toString().padStart(2, "0");
      const month = (d.getMonth() + 1).toString().padStart(2, "0");
      const year = d.getFullYear();
      return `${day}.${month}.${year}`;
    },
  },

  watch: {
    todos: {
      handler() {
        console.log("Todos updated.");
      },
      deep: true,
    },
  },
  mounted() {
    this.fetchTodos();
  },
  name: 'ToDoList',
  props: {
    msg: String
  }
}
</script>

<style scoped>
.add-item {
  background-color: #fafafa;
  padding: 1.5rem 13rem 1.5rem 13rem;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
}

.add-item input[type="text"] {
  font-size: 1.2rem;
  padding: 0.5rem 1rem;
  width: 70%;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.add-item button {
  font-size: 1.2rem;
  padding: 0.5rem 1.5rem;
  border-radius: 5px;
  background-color: #050a52;
  color: white;
  cursor: pointer;
}

.todo-list {
  margin: 3rem 10rem 3rem 10rem;
}

.todo-left input[type="checkbox"] {
  width: 1.5rem;
  height: 1.5rem;
  margin-right: 2rem;
}

.todo-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1rem 0;
  padding: 1rem;
  border-bottom: 1px solid #ccc;
  font-size: 1.2rem;
}

.todo-left {
  flex: 1;
  display: flex;
  align-items: center;
}

.todo-right {
  display: flex;
  align-items: center;

}

.deadline {
  margin-right: 1rem;
}

.add-item input[type="date"] {
  font-size: 1.2rem;
  padding: 0.5rem;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.fas {
  cursor: pointer;
}
</style>
