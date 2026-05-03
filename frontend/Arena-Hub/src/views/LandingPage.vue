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
              <div class="search-bar" :class="{ 'is-focused': isSearching }">
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
                <button v-if="search" class="clear-search" @click="search = ''">
                  <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                    <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
                  </svg>
                </button>
              </div>
              
              <div class="search-suggestions">
                <span class="suggestion-label">Populares:</span>
                <button class="suggestion-tag" @click="search = 'Rock'">Rock</button>
                <button class="suggestion-tag" @click="search = 'Futebol'">Futebol</button>
                <button class="suggestion-tag" @click="search = 'Festival'">Festival</button>
              </div>
            </div>
          </div>
          
          <div class="hero-stats">
            <div class="stat-item">
              <span class="stat-number">50+</span>
              <span class="stat-label">Eventos por ano</span>
            </div>
            <div class="stat-divider"></div>
            <div class="stat-item">
              <span class="stat-number">100k+</span>
              <span class="stat-label">Espectadores</span>
            </div>
            <div class="stat-divider"></div>
            <div class="stat-item">
              <span class="stat-number">4.8</span>
              <span class="stat-label">Avaliação</span>
            </div>
          </div>
        </div>
        
        <div class="hero-wave">
          <svg viewBox="0 0 1440 120" fill="none" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none">
            <path d="M0 120L60 105C120 90 240 60 360 50C480 40 600 50 720 55C840 60 960 60 1080 50C1200 40 1320 20 1380 10L1440 0V120H1380C1320 120 1200 120 1080 120C960 120 840 120 720 120C600 120 480 120 360 120C240 120 120 120 60 120H0V120Z" fill="#0a0e17"/>
          </svg>
        </div>
      </section>

      <section class="filter-section">
        <div class="container">
          <div class="filter-wrapper">
            <div class="filter-label">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <polygon points="22 3 2 3 10 12 10 19 14 21 14 12 22 3"/>
              </svg>
              <span>Filtrar por:</span>
            </div>
            <div class="filter-chips">
              <button
                v-for="cat in categorias"
                :key="cat"
                class="chip-btn"
                :class="{ active: activeCategory === cat }"
                @click="activeCategory = cat"
              >
                <span class="chip-text">{{ cat }}</span>
              </button>
            </div>
          </div>
        </div>
      </section>

      <section class="events-section">
        <div class="container">
          <header class="events-header">
            <div class="header-content">
              <h2 class="section-label">
                <span v-if="activeCategory === 'Todos'">Próximos Eventos</span>
                <span v-else>{{ activeCategory }}</span>
              </h2>
            </div>
            <div class="results-count" v-if="eventosFiltrados.length">
              <span class="count-number">{{ eventosFiltrados.length }}</span>
              <span class="count-text">{{ eventosFiltrados.length === 1 ? 'evento encontrado' : 'eventos encontrados' }}</span>
            </div>
          </header>

          <div class="events-grid" v-if="eventosFiltrados.length">
            <EventCard 
              v-for="(ev, index) in eventosFiltrados" 
              :key="ev.id" 
              :event="ev" 
              class="animate-in"
              :style="{ animationDelay: `${index * 0.05}s` }"
            />
          </div>

          <div v-else class="empty-state">
            <div class="empty-icon">
              <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
                <circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/>
              </svg>
            </div>
            <h3>Nenhum evento encontrado</h3>
            <p>Tente ajustar sua busca ou mudar a categoria.</p>
            <button class="btn-clear" @click="resetFilters">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <path d="M3 6h18M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/>
              </svg>
              <span>Limpar filtros</span>
            </button>
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
    const response = await fetch('https://api.torreszx.space/eventos')
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
.page { 
  background: #0a0e17; 
  color: white; 
  min-height: 100vh; 
  position: relative;
}

.container { 
  max-width: 1200px; 
  margin: 0 auto; 
  padding: 0 24px; 
  width: 100%;
}

.hero-section {
  position: relative;
  padding: 100px 0 120px;
  background-image: url('https://images.unsplash.com/photo-1501281668745-f7f57925c3b4?auto=format&fit=crop&q=80&w=2070');
  background-size: cover;
  background-position: center 30%;
  display: flex;
  justify-content: center;
}

.hero-overlay {
  position: absolute; 
  inset: 0;
  background: linear-gradient(135deg, 
    rgba(10, 14, 23, 0.92) 0%, 
    rgba(10, 14, 23, 0.7) 50%,
    rgba(10, 14, 23, 0.92) 100%
  );
  z-index: 1;
}

.hero-wave {
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 100%;
  z-index: 2;
  line-height: 0;
}

.hero-wave svg {
  width: 100%;
  height: 60px;
}

.hero-container { 
  position: relative; 
  z-index: 3; 
  width: 100%; 
  max-width: 1200px; 
  padding: 0 24px;
  display: flex;
  flex-direction: column;
  gap: 40px;
}

.hero-text-content { 
  max-width: 650px; 
  margin: 0; 
  text-align: left;
}

.hero-title {
  font-size: clamp(2.8rem, 5vw, 4.5rem);
  font-weight: 900;
  line-height: 1.1;
  margin-bottom: 20px;
  letter-spacing: -1px;
  text-transform: uppercase;
  color: #fff;
  text-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
}

.text-gold { 
  color: #c9a84c; 
}

.hero-subtitle {
  font-size: clamp(1rem, 2vw, 1.2rem);
  color: #e0e0e0;
  margin-bottom: 32px;
  line-height: 1.6;
  font-weight: 400;
  text-shadow: 0 1px 8px rgba(0, 0, 0, 0.3);
}

.hero-stats {
  display: flex;
  align-items: center;
  gap: 32px;
}

.stat-item {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.stat-number {
  font-size: 2rem;
  font-weight: 800;
  color: #c9a84c;
  line-height: 1.2;
}

.stat-label {
  font-size: 0.8rem;
  color: #b0b8c5;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.stat-divider {
  width: 1px;
  height: 40px;
  background: rgba(255, 255, 255, 0.15);
}

.search-wrapper { 
  max-width: 550px; 
}

.search-bar {
  background: rgba(18, 24, 38, 0.95);
  backdrop-filter: blur(12px);
  border: 1.5px solid rgba(201, 168, 76, 0.15);
  border-radius: 16px;
  padding: 16px 20px;
  display: flex;
  align-items: center;
  gap: 14px;
  transition: border-color 0.2s ease, box-shadow 0.2s ease;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
}

.search-bar:hover {
  border-color: rgba(201, 168, 76, 0.3);
}

.search-bar.is-focused {
  border-color: #c9a84c;
  box-shadow: 0 4px 20px rgba(201, 168, 76, 0.1);
}

.search-icon {
  color: #c9a84c;
  opacity: 0.8;
  flex-shrink: 0;
}

.search-bar input {
  background: transparent;
  border: none;
  font-size: 1rem;
  width: 100%;
  outline: none;
  color: #c9a84c;
}

.search-bar input::placeholder {
  color: #6b7280;
  font-weight: 400;
}

.clear-search {
  background: transparent;
  border: none;
  color: #6b7280;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 4px;
  border-radius: 50%;
  transition: all 0.2s;
}

.clear-search:hover {
  background: rgba(255, 255, 255, 0.1);
  color: #fff;
}

.search-suggestions {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-top: 12px;
  flex-wrap: wrap;
}

.suggestion-label {
  color: #8e9aaf;
  font-size: 0.8rem;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.suggestion-tag {
  background: rgba(255, 255, 255, 0.05);
  border: 1px solid rgba(255, 255, 255, 0.08);
  color: #b0b8c5;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.8rem;
  cursor: pointer;
  transition: all 0.2s;
}

.suggestion-tag:hover {
  background: rgba(201, 168, 76, 0.15);
  border-color: rgba(201, 168, 76, 0.3);
  color: #c9a84c;
}

.filter-section { 
  padding: 28px 0; 
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  background: #0a0e17;
}

.filter-wrapper {
  display: flex;
  align-items: center;
  gap: 24px;
  flex-wrap: wrap;
}

.filter-label {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #8e9aaf;
  font-size: 0.85rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.8px;
}

.filter-label svg {
  color: #c9a84c;
}

.filter-chips { 
  display: flex; 
  gap: 8px; 
  flex-wrap: wrap; 
}

.chip-btn {
  background: transparent; 
  color: #8e9aaf; 
  border: 1.5px solid rgba(255, 255, 255, 0.1);
  padding: 10px 22px; 
  border-radius: 40px; 
  font-weight: 600; 
  font-size: 0.85rem;
  cursor: pointer; 
  transition: all 0.25s ease;
  text-transform: uppercase;
  letter-spacing: 0.3px;
}

.chip-btn:hover {
  color: #c9a84c;
  border-color: rgba(201, 168, 76, 0.4);
  transform: translateY(-2px);
}

.chip-btn.active { 
  background: #c9a84c; 
  color: #0a0e17; 
  border-color: #c9a84c;
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.2);
}

.events-section { 
  padding: 60px 0 80px; 
  background: #0a0e17;
}

.events-header { 
  display: flex; 
  justify-content: space-between; 
  align-items: baseline; 
  margin-bottom: 40px; 
}

.header-content {
  flex: 1;
}

.section-label { 
  font-size: clamp(1.8rem, 4vw, 2.4rem);
  font-weight: 800; 
  text-transform: uppercase;
  letter-spacing: 0.5px;
  margin: 0;
  color: #fff;
}

.results-count {
  color: #6b7280;
  font-size: 0.9rem;
  display: flex;
  align-items: baseline;
  gap: 6px;
}

.count-number {
  font-size: 1.4rem;
  font-weight: 700;
  color: #c9a84c;
}

.count-text {
  text-transform: lowercase;
}

.events-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 32px;
}

.empty-state { 
  text-align: center; 
  padding: 80px 20px; 
}

.empty-icon { 
  margin-bottom: 24px; 
  color: #c9a84c;
  opacity: 0.4;
}

.empty-state h3 {
  font-size: 1.4rem;
  font-weight: 700;
  margin-bottom: 8px;
  color: #fff;
}

.empty-state p {
  color: #6b7280;
  margin-bottom: 28px;
}

.btn-clear {
  background: transparent;
  border: 1.5px solid rgba(201, 168, 76, 0.3);
  color: #c9a84c;
  padding: 12px 24px;
  border-radius: 12px;
  font-weight: 600;
  display: inline-flex;
  align-items: center;
  gap: 10px;
  transition: all 0.25s;
  font-size: 0.85rem;
  text-transform: uppercase;
  letter-spacing: 0.8px;
  cursor: pointer;
}

.btn-clear:hover {
  background: rgba(201, 168, 76, 0.08);
  border-color: #c9a84c;
  transform: translateY(-2px);
}

.animate-in {
  animation: slideUp 0.5s ease-out forwards;
  opacity: 0;
}

@keyframes slideUp {
  from { 
    opacity: 0; 
    transform: translateY(30px); 
  }
  to { 
    opacity: 1; 
    transform: translateY(0); 
  }
}

@media (max-width: 1024px) {
  .hero-container {
    align-items: center;
  }
  
  .hero-text-content { 
    text-align: center; 
    margin: 0 auto; 
  }
  
  .search-wrapper {
    margin: 0 auto;
  }
  
  .search-suggestions {
    justify-content: center;
  }
  
  .hero-stats {
    justify-content: center;
  }
}

@media (max-width: 768px) {
  .hero-section {
    padding: 80px 0 100px;
  }
  
  .filter-wrapper {
    flex-direction: column;
    align-items: flex-start;
    gap: 16px;
  }
  
  .events-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px;
  }
  
  .events-grid { 
    grid-template-columns: 1fr; 
    gap: 24px; 
  }
  
  .search-bar {
    padding: 14px 18px;
  }
  
  .search-bar input {
    font-size: 0.95rem;
  }
  
  .hero-stats {
    gap: 20px;
  }
  
  .stat-number {
    font-size: 1.6rem;
  }
  
  .stat-divider {
    height: 30px;
  }
}

@media (max-width: 480px) {
  .hero-title {
    font-size: 2.2rem;
  }
  
  .hero-stats {
    flex-direction: column;
    gap: 16px;
    align-items: flex-start;
  }
  
  .stat-divider {
    display: none;
  }
  
  .search-suggestions {
    justify-content: flex-start;
  }
  
  .chip-btn {
    padding: 8px 16px;
    font-size: 0.8rem;
  }
}
</style>