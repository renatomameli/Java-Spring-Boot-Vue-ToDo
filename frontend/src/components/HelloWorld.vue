<template>
  <div class="hello">
    <div>
      <h1>Meine Todos</h1>
      <ul>
        <li v-for="todo in todos" :key="todo.id">
          {{ todo.description }}
          <input type="checkbox" :checked="todo.done" @change="toggleDone(todo.id)" />
          <span @click="deleteTodo(todo.id)" style="cursor: pointer;">✖</span>
        </li>
      </ul>
    </div>
    <div>
      <input type="text" v-model="newTodo" placeholder="New Todo" />
      <button @click="addTodo">Add Todo</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      todos: [],
      newTodo: ''
    };
  },
  methods: {
    async toggleDone(todoId) {
      try {
        const response = await fetch(`http://localhost:8080/to-dos/${todoId}`, {
          method: 'PUT',
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
        const response = await fetch(`http://localhost:8080/to-dos/${todoId}`, {
          method: 'DELETE'
        });

        if (response.status === 204 || response.status === 200) {
          await this.fetchTodos(); // Refresh your to-do list
        } else {
          console.error(`Failed to delete to-do with id ${todoId}`);
        }
      } catch (error) {
        console.error(`An error occurred while deleting the to-do: ${error}`);
      }
    },

    async fetchTodos() {
      fetch("http://localhost:8080/to-dos")
          .then(response => response.json())
          .then(data => {
            this.todos = data;
          }).catch((error) => console.error('Error fetching data:', error));
    },

    async addTodo() {
      if (this.newTodo) {
        const newTodoDescription = this.newTodo;

        try {
          const response = await fetch('http://localhost:8080/to-dos', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: newTodoDescription,
          });

          if (response.ok) {
            await this.fetchTodos();
            this.newTodo = '';
          } else {
            console.error(`Failed to create todo, status: ${response.status}`);
          }
        } catch (error) {
          console.error('There was a problem with the fetch operation:', error);
        }
      }
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
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 0 10px;
}
a {
  color: #42b983;
}
span {
  color: red;
}
</style>
