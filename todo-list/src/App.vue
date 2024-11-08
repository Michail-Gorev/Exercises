<template>
  <div class="container">
    <h1>Список дел</h1>

    <div class="input-group mb-3">
      <input
        type="text"
        class="form-control"
        placeholder="Добавить дело..."
        v-model="newTask.text"
      >
      <button class="btn btn-success" type="button" @click="addTask">
        Добавить
      </button>
    </div>

    <ul class="list-group">
      <li
        v-for="(task, index) in tasks"
        :key="index"
        class="list-group-item d-flex justify-content-between align-items-center"
      >
        <div
          :class="{ 'text-decoration-line-through': task.completed }"
        >
          <input
            type="checkbox"
            :checked="task.completed"
            @click="toggleTask(index)"
          >
          {{ task.text }}
          <span class="text-muted">
            (Создано: {{ task.createdAt }})
            <span v-if="task.completed">
              (Завершено: {{ task.completedAt }})
            </span>
          </span>
        </div>
        <button
          class="btn btn-danger"
          type="button"
          @click="deleteTask(index)"
        >
          Удалить
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
import { ref, onMounted, computed } from 'vue'

export default {
  setup() {
    const tasks = ref([])
    const newTask = ref({
      text: '',
      completed: false,
      createdAt: '',
      completedAt: null,
    })

    onMounted(() => {
      loadTasks()
    })

    const addTask = () => {
      if (newTask.value.text.trim() !== '') {
        const options = {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
      }
        newTask.value.createdAt = new Date().toLocaleDateString('ru-RU', options)
        tasks.value.push({ ...newTask.value })
        newTask.value.text = ''
        saveTasks()
      }
    }

    const deleteTask = (index) => {
      tasks.value.splice(index, 1)
      saveTasks()
    }

    const toggleTask = (index) => {
      const options = {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
      }
      tasks.value[index].completed = !tasks.value[index].completed
      tasks.value[index].completedAt = tasks.value[index].completed ? new Date().toLocaleDateString('ru-RU', options) : null
      saveTasks()
    }

    const formatDate = (date) => {
      if (!date) return ''
      const options = {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
      }
      return new Date(date).toLocaleDateString('ru-RU', options)
    }

    const loadTasks = () => {
      fetch('./tasks.json')
        .then(response => response.json())
        .then(data => {
          tasks.value = data
        })
        .catch(error => {
          console.error('Ошибка загрузки данных:', error)
        })
    }

    const saveTasks = () => {
      const data = JSON.stringify(tasks.value)
      localStorage.setItem('tasks', data)
    }

    return {
      tasks,
      newTask,
      addTask,
      deleteTask,
      toggleTask,
      formatDate,
    }
  },
}
</script>

<style scoped>
</style>
