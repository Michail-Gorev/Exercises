<template>
  <div class="container">
    <h1>Статьи</h1>
    <div class="row mt-3">
      <div class="col-md-4">
        <input
          type="text"
          v-model="authorSearch"
          class="form-control"
          placeholder="Поиск по автору"
        >
      </div>
      <div class="col-md-4">
        <input
          type="text"
          v-model="topicSearch"
          class="form-control"
          placeholder="Поиск по теме"
        >
      </div>
      <div class="col-md-4">
        <input
          type="text"
          v-model="titleSearch"
          class="form-control"
          placeholder="Поиск по названию"
        >
      </div>
    </div>

    <div class="row mt-4">
      <div class="col-md-12">
        <div class="row articles-grid">
          <div
            v-for="article in filteredArticles"
            :key="article.id"
            class="col-md-4 mb-4"
          >
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">{{ article.title }}</h5>
                <p class="card-text">Автор: {{ article.author }}</p>
                <p class="card-text">Тема: {{ article.topic }}</p>
                <p class="card-text">{{ article.content }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, computed } from 'vue'

export default {
  setup() {
    const articles = ref([])
    const authorSearch = ref('')
    const topicSearch = ref('')
    const titleSearch = ref('')

    onMounted(() => {
      fetch('./articles.json')
        .then(response => response.json())
        .then(data => {
          articles.value = data
        })
        .catch(error => {
          console.error('Ошибка загрузки данных:', error)
        })
    })

    const filteredArticles = computed(() => {
      if (!authorSearch.value && !topicSearch.value && !titleSearch.value) {
        return articles.value
      }
      const authorSearchLower = authorSearch.value.toLowerCase()
      const topicSearchLower = topicSearch.value.toLowerCase()
      const titleSearchLower = titleSearch.value.toLowerCase()
      return articles.value.filter(article => {
        return (
          (!authorSearch.value || article.author.toLowerCase().includes(authorSearchLower)) &&
          (!topicSearch.value || article.topic.toLowerCase().includes(topicSearchLower)) &&
          (!titleSearch.value || article.title.toLowerCase().includes(titleSearchLower))
        )
      })
    })

    return {
      articles,
      authorSearch,
      topicSearch,
      titleSearch,
      filteredArticles,
    }
  },
}
</script>

<style scoped>
.articles-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}
</style>
