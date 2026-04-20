<template>
  <div class="page">
    <AppNavbar />

    <main class="main-content">
      <section class="hero-section">
        <div class="hero-overlay"></div>
        <div class="hero-container">
          <div class="hero-text-content">
            <h1 class="hero-title">
              Viva o Extraordinário na <span class="text-gold">Arena</span>
            </h1>
            <p class="hero-subtitle">
              Sua porta de entrada para os maiores espetáculos de Pernambuco. 
              Shows históricos, clássicos do futebol e experiências únicas.
            </p>
            
            <div class="search-wrapper">
              <div class="search-bar">
                <svg class="search-icon" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/>
                </svg>
                <input 
                  v-model="search" 
                  type="text" 
                  placeholder="O que você quer viver hoje?" 
                  @focus="isSearching = true"
                  @blur="isSearching = false"
                />
              </div>
            </div>
          </div>
        </div>
      </section>

      <section class="filter-section">
        <div class="container">
          <div class="filter-wrapper">
            <div class="filter-chips">
              <button
                v-for="cat in categorias"
                :key="cat"
                class="chip-btn"
                :class="{ active: activeCategory === cat }"
                @click="activeCategory = cat"
              >
                {{ cat }}
              </button>
            </div>
          </div>
        </div>
      </section>

      <section class="events-section">
        <div class="container">
          <header class="events-header">
            <h2 class="section-label">
              {{ activeCategory === 'Todos' ? 'Próximos Eventos' : activeCategory }}
            </h2>
            <div class="results-count" v-if="eventosFiltrados.length">
              {{ eventosFiltrados.length }} eventos encontrados
            </div>
          </header>

          <div class="events-grid" v-if="eventosFiltrados.length">
            <EventCard 
              v-for="ev in eventosFiltrados" 
              :key="ev.id" 
              :event="ev" 
              class="animate-in"
            />
          </div>

          <div v-else class="empty-state">
            <div class="empty-icon">🔍</div>
            <h3>Nenhum evento encontrado</h3>
            <p>Tente ajustar sua busca ou mudar a categoria.</p>
            <button class="btn-clear" @click="resetFilters">Limpar tudo</button>
          </div>
        </div>
      </section>
    </main>

    <AppFooter />
    <LoginModal v-if="showLogin" @close="showLogin = false" />
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'
import EventCard from '../components/EventCard.vue'
import LoginModal from '../components/LoginModal.vue'

const search = ref('')
const activeCategory = ref('Todos')
const showLogin = ref(false)
const isSearching = ref(false)
const categorias = ['Todos', 'Show', 'Esportes', 'Comédia', 'Teatro', 'Corporativo']

const eventos = ref([])

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:8080/eventos')
    if (!response.ok) {
      throw new Error('Erro ao buscar eventos da API')
    }
    const data = await response.json()
    eventos.value = data.map(ev => ({
      id: ev.id,
      title: ev.nome,
      category: ev.categoria || 'Show',
      date: ev.dataInicio ? new Date(ev.dataInicio).toLocaleDateString('pt-BR', { day: '2-digit', month: 'long', year: 'numeric' }) : 'Data a definir',
      venue: 'Arena Hub',
      image: 'https://images.unsplash.com/photo-1540039155733-5bb30b53aa14?w=600&q=80'
    }))
  } catch (error) {
    console.error('Erro ao carregar eventos:', error)
  }
})

const eventosFiltrados = computed(() => {
  return eventos.value.filter(e => {
    const matchCat = activeCategory.value === 'Todos' || e.category === activeCategory.value
    const matchSearch = !search.value || e.title.toLowerCase().includes(search.value.toLowerCase())
    return matchCat && matchSearch
  })
})

const resetFilters = () => {
  search.value = ''
  activeCategory.value = 'Todos'
}
</script>

<style scoped>
.page { background: #0a0e17; color: white; min-height: 100vh; }

/* ALINHAMENTO MESTRE 1200px */
.container { 
  max-width: 1200px; 
  margin: 0 auto; 
  padding: 0 24px; 
  width: 100%;
}

/* Hero Section */
.hero-section {
  position: relative;
  padding: 120px 0 80px; /* Padding generoso para Desktop */
  background-image: url('https://images.unsplash.com/photo-1501281668745-f7f57925c3b4?auto=format&fit=crop&q=80&w=2070');
  background-size: cover;
  background-position: center;
  display: flex;
  justify-content: center;
}

.hero-overlay {
  position: absolute; inset: 0;
  background: linear-gradient(to bottom, rgba(10, 14, 23, 0.6), #0a0e17);
}

.hero-container { position: relative; z-index: 2; width: 100%; max-width: 1200px; padding: 0 24px; }
.hero-text-content { max-width: 700px; margin: 0; text-align: left; } /* Alinhado à esquerda no PC fica mais premium */

.hero-title {
  font-size: 4rem;
  font-weight: 900;
  line-height: 1.05;
  margin-bottom: 24px;
  letter-spacing: -2px;
}
.text-gold { color: #c9a84c; }

.hero-subtitle {
  font-size: 1.2rem;
  color: #9ca3af;
  margin-bottom: 40px;
  line-height: 1.6;
}

/* Busca */
.search-wrapper { max-width: 550px; }
.search-bar {
  background: rgba(28, 36, 51, 0.8);
  backdrop-filter: blur(12px);
  border: 1px solid rgba(201, 168, 76, 0.3);
  border-radius: 14px;
  padding: 16px 24px;
  display: flex;
  align-items: center;
  gap: 14px;
}

/* Filtros */
.filter-section { padding: 40px 0; border-bottom: 1px solid rgba(255,255,255,0.05); }
.filter-chips { display: flex; gap: 12px; flex-wrap: wrap; }

.chip-btn {
  background: #111827; color: #9ca3af; border: 1px solid rgba(255,255,255,0.05);
  padding: 12px 28px; border-radius: 50px; font-weight: 600; cursor: pointer; transition: 0.3s;
}
.chip-btn.active { background: #c9a84c; color: #0a0e17; border-color: #c9a84c; }

/* Grid de Eventos */
.events-section { padding: 80px 0; }
.events-header { display: flex; justify-content: space-between; align-items: flex-end; margin-bottom: 48px; }
.section-label { font-size: 2rem; font-weight: 800; }

.events-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr)); /* Cards maiores no PC */
  gap: 40px;
}

/* Empty State */
.empty-state { text-align: center; padding: 100px 0; }
.empty-icon { font-size: 4rem; margin-bottom: 24px; }

@media (max-width: 1024px) {
  .hero-text-content { text-align: center; margin: 0 auto; }
  .hero-title { font-size: 3rem; }
  .events-grid { grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 24px; }
}

.animate-in {
  animation: slideUp 0.6s ease-out forwards;
}
@keyframes slideUp {
  from { opacity: 0; transform: translateY(30px); }
  to { opacity: 1; transform: translateY(0); }
}
</style>