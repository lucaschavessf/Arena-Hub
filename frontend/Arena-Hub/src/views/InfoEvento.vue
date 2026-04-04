<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-info" v-if="evento">
      <div class="info-container">
        <div class="info-grid">
          
          <aside class="info-sidebar">
            <div class="event-poster">
              <img :src="evento.image" :alt="evento.title" />
              <div class="category-tag">{{ evento.category }}</div>
            </div>
            
            <h1 class="sidebar-title">{{ evento.title }}</h1>
            
            <div class="quick-meta">
              <div class="meta-box">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/></svg>
                <div>
                  <p class="meta-label">Data</p>
                  <p class="meta-val highlight">{{ evento.date }}</p>
                </div>
              </div>
              <div class="meta-box">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/></svg>
                <div>
                  <p class="meta-label">Horário</p>
                  <p class="meta-val">{{ evento.time }}</p>
                </div>
              </div>
              <div class="meta-box">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/></svg>
                <div>
                  <p class="meta-label">Local</p>
                  <p class="meta-val">{{ evento.venue }}</p>
                </div>
              </div>
            </div>

            <button class="btn-back-outline" @click="$router.back()">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="15 18 9 12 15 6"/></svg>
              VOLTAR
            </button>
          </aside>

          <section class="info-details">
            <div class="details-card">
              <div class="details-header">
                <h2 class="section-heading">Informações Gerais</h2>
                <div class="heading-line"></div>
              </div>
              
              <div class="details-list">
                <div class="detail-row">
                  <span class="d-label">Sobre o Evento</span>
                  <p class="d-value text-description">{{ evento.description || 'Nenhuma descrição disponível.' }}</p>
                </div>

                <div class="detail-grid-inner">
                  <div class="detail-row">
                    <span class="d-label">Abertura</span>
                    <span class="d-value">{{ evento.time || '--:--' }}</span>
                  </div>
                  <div class="detail-row">
                    <span class="d-label">Classificação</span>
                    <span class="d-value badge-class">{{ evento.classificacao || 'Livre' }}</span>
                  </div>
                </div>

                <div class="detail-row footer-info">
                  <span class="d-label">Produção</span>
                  <span class="d-value">{{ evento.venue }} Arena Hub</span>
                </div>
              </div>
            </div>

            <div class="location-preview">
              <span class="loc-icon">📍</span>
              <span>{{ evento.venue }} — Ver no mapa</span>
            </div>
            
            <button class="btn-buy-now" @click="$router.push(`/comprar/${evento.id}`)">
              COMPRAR INGRESSOS AGORA
            </button>
          </section>

          <aside class="info-lineup" v-if="evento.lineup && evento.lineup.length">
            <div class="lineup-card">
              <h3 class="lineup-heading">Cronograma</h3>
              <div class="timeline">
                <div v-for="item in evento.lineup" :key="item.banda" 
                     class="timeline-item" :class="{ 'is-main': item.isMain }">
                  <div class="time-tag">{{ item.horario }}</div>
                  <div class="band-info">
                    <span class="band-name">{{ item.banda }}</span>
                  </div>
                </div>
              </div>
            </div>
          </aside>
        </div>
      </div>
    </main>

    <main class="main-info empty" v-else>
      <p>Carregando detalhes do evento...</p>
    </main>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'
import { eventos } from '../data/mock.js' // Verifique se o caminho está correto

const route = useRoute()
const eventoId = computed(() => Number(route.params.id))
const evento = computed(() => eventos.find((e: any) => e.id === eventoId.value))
</script>

<style scoped>
.page { min-height: 100vh; background: #0a0e17; color: white; display: flex; flex-direction: column; }
.main-info { flex: 1; padding: 60px 20px; display: flex; justify-content: center; }
.info-container { width: 100%; max-width: 1200px; }

.info-grid {
  display: grid;
  grid-template-columns: 280px 1fr 300px;
  gap: 40px;
  align-items: start;
}

/* Sidebar PC */
.info-sidebar { position: sticky; top: 100px; }
.event-poster { 
  border-radius: 16px; overflow: hidden; 
  box-shadow: 0 20px 50px rgba(0,0,0,0.5); margin-bottom: 24px;
  border: 1px solid rgba(255,255,255,0.1);
}
.event-poster img { width: 100%; display: block; }
.category-tag { 
  position: absolute; top: 12px; left: 12px; background: #c9a84c; 
  color: #0a0e17; font-size: 0.7rem; font-weight: 800; padding: 4px 10px; 
  border-radius: 6px; text-transform: uppercase;
}

.sidebar-title { font-size: 1.8rem; font-weight: 800; margin-bottom: 24px; color: #fff; }
.quick-meta { display: flex; flex-direction: column; gap: 20px; margin-bottom: 32px; }
.meta-box { display: flex; gap: 14px; align-items: center; }
.meta-box svg { color: #c9a84c; }
.meta-label { font-size: 0.7rem; color: #8e9aaf; text-transform: uppercase; margin: 0; }
.meta-val { font-size: 1rem; font-weight: 600; margin: 0; }
.meta-val.highlight { color: #c9a84c; }

.btn-back-outline {
  width: 100%; padding: 14px; background: transparent; border: 1.5px solid rgba(255,255,255,0.1);
  color: #fff; border-radius: 12px; cursor: pointer; display: flex; align-items: center; 
  justify-content: center; gap: 8px; font-weight: 700; transition: 0.3s;
}
.btn-back-outline:hover { background: #fff; color: #000; }

/* Conteúdo Central */
.details-card { 
  background: #121826; border-radius: 24px; padding: 40px; 
  border: 1px solid rgba(255,255,255,0.05);
}
.section-heading { font-size: 1.4rem; font-weight: 800; margin-bottom: 8px; }
.heading-line { width: 50px; height: 4px; background: #c9a84c; border-radius: 2px; margin-bottom: 32px; }

.text-description { font-size: 1.05rem; line-height: 1.8; color: #9ca3af; }
.detail-grid-inner { 
  display: grid; grid-template-columns: 1fr 1fr; gap: 32px; 
  margin: 32px 0; padding: 32px 0; border-top: 1px solid rgba(255,255,255,0.05); border-bottom: 1px solid rgba(255,255,255,0.05);
}
.d-label { font-size: 0.75rem; color: #c9a84c; font-weight: 700; text-transform: uppercase; margin-bottom: 8px; display: block; }
.d-value { font-size: 1.1rem; color: #fff; }

.btn-buy-now {
  width: 100%; margin-top: 32px; padding: 20px; background: #c9a84c; 
  color: #0a0e17; border: none; border-radius: 14px; font-weight: 800;
  font-size: 1.1rem; cursor: pointer; transition: 0.3s;
}
.btn-buy-now:hover { transform: translateY(-3px); box-shadow: 0 10px 20px rgba(201, 168, 76, 0.3); }

/* Lineup */
.lineup-card { background: #121826; border-radius: 24px; padding: 32px; border: 1px solid rgba(255,255,255,0.05); }
.timeline { border-left: 2px solid rgba(201, 168, 76, 0.2); margin-left: 10px; }
.timeline-item { position: relative; padding: 0 0 32px 24px; }
.timeline-item::before {
  content: ""; position: absolute; left: -7px; top: 6px; width: 12px; height: 12px;
  background: #0a0e17; border: 2px solid #c9a84c; border-radius: 50%;
}
.is-main::before { background: #c9a84c; transform: scale(1.4); }

/* Responsive */
@media (max-width: 1150px) {
  .info-grid { grid-template-columns: 280px 1fr; }
  .info-lineup { grid-column: span 2; margin-top: 32px; }
}
@media (max-width: 800px) {
  .info-grid { grid-template-columns: 1fr; }
  .info-sidebar, .info-lineup { grid-column: auto; position: static; }
}
</style>