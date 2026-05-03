<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-info" v-if="evento">
      <div class="info-container">
        <div class="info-grid">
          
          <aside class="info-sidebar">
            <div class="event-poster">
              <img :src="evento.image" :alt="evento.title" />
              <div class="poster-overlay"></div>
              <div class="category-tag">{{ evento.category }}</div>
            </div>
            
            <h1 class="sidebar-title">{{ evento.title }}</h1>
            
            <div class="quick-meta">
              <div class="meta-box">
                <div class="meta-icon">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
                  </svg>
                </div>
                <div class="meta-content">
                  <p class="meta-label">Data</p>
                  <p class="meta-val highlight">{{ evento.date }}</p>
                </div>
              </div>
              
              <div class="meta-box">
                <div class="meta-icon">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/>
                  </svg>
                </div>
                <div class="meta-content">
                  <p class="meta-label">Horário</p>
                  <p class="meta-val">{{ evento.time }}</p>
                </div>
              </div>
              
              <div class="meta-box">
                <div class="meta-icon">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/>
                  </svg>
                </div>
                <div class="meta-content">
                  <p class="meta-label">Local</p>
                  <p class="meta-val">{{ evento.venue }}</p>
                </div>
              </div>
            </div>

            <button class="btn-back-outline" @click="$router.back()">
              <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <polyline points="15 18 9 12 15 6"/>
              </svg>
              <span>VOLTAR</span>
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
                  <span class="d-label">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                      <circle cx="12" cy="12" r="10"/><path d="M12 16v-4M12 8h.01"/>
                    </svg>
                    Sobre o Evento
                  </span>
                  <p class="d-value text-description">{{ evento.description || 'Nenhuma descrição disponível.' }}</p>
                </div>

                <div class="detail-grid-inner">
                  <div class="detail-row">
                    <span class="d-label">
                      <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                        <circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/>
                      </svg>
                      Abertura dos Portões
                    </span>
                    <span class="d-value">{{ evento.time || '--:--' }}</span>
                  </div>
                  
                  <div class="detail-row">
                    <span class="d-label">
                      <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                        <path d="M12 2L2 7v10l10 5 10-5V7l-10-5z"/><path d="M2 7l10 5 10-5M12 22V12"/>
                      </svg>
                      Classificação
                    </span>
                    <span class="d-value badge-class">{{ evento.classificacao || 'Livre' }}</span>
                  </div>
                </div>

                <div class="detail-row footer-info">
                  <span class="d-label">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                      <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/>
                    </svg>
                    Produção
                  </span>
                  <span class="d-value">{{ evento.venue }} Arena Hub</span>
                </div>
              </div>
            </div>

            <div class="location-preview">
              <div class="loc-icon-wrapper">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/>
                </svg>
              </div>
              <span class="loc-text">{{ evento.venue }} — <span class="loc-link">Ver no mapa</span></span>
            </div>
            
            <button class="btn-buy-now" @click="$router.push(`/comprar/${evento.id}`)">
              <span>COMPRAR INGRESSOS AGORA</span>
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                <path d="M5 12h14M12 5l7 7-7 7"/>
              </svg>
            </button>
          </section>

          <aside class="info-lineup" v-if="evento.lineup && evento.lineup.length">
            <div class="lineup-card">
              <div class="lineup-header">
                <h3 class="lineup-heading">Cronograma</h3>
                <div class="lineup-icon">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                    <circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/>
                  </svg>
                </div>
              </div>
              
              <div class="timeline">
                <div v-for="(item, index) in evento.lineup" :key="item.banda" 
                     class="timeline-item" 
                     :class="{ 'is-main': item.isMain }"
                     :style="{ animationDelay: `${index * 0.1}s` }">
                  <div class="time-tag">{{ item.horario }}</div>
                  <div class="band-info">
                    <span class="band-name">{{ item.banda }}</span>
                    <span v-if="item.isMain" class="main-badge">Headliner</span>
                  </div>
                </div>
              </div>
            </div>
          </aside>
        </div>
      </div>
    </main>

    <main class="main-info loading-state" v-else>
      <div class="loading-spinner"></div>
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
import { eventos } from '../data/mock.js'

const route = useRoute()
const eventoId = computed(() => Number(route.params.id))
const evento = computed(() => eventos.find((e: any) => e.id === eventoId.value))
</script>

<style scoped>
.page { 
  min-height: 100vh; 
  background: linear-gradient(180deg, #0a0e17 0%, #0d111a 100%);
  color: white; 
  display: flex; 
  flex-direction: column; 
}

.main-info { 
  flex: 1; 
  padding: 60px 20px; 
  display: flex; 
  justify-content: center; 
}

.info-container { 
  width: 100%; 
  max-width: 1200px; 
}

.info-grid {
  display: grid;
  grid-template-columns: 300px 1fr 320px;
  gap: 40px;
  align-items: start;
}

.loading-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 20px;
  min-height: 400px;
}

.loading-spinner {
  width: 50px;
  height: 50px;
  border: 3px solid rgba(201, 168, 76, 0.2);
  border-top-color: #c9a84c;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

.info-sidebar { 
  position: sticky; 
  top: 100px; 
}

.event-poster { 
  position: relative;
  border-radius: 20px; 
  overflow: hidden; 
  box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.5);
  margin-bottom: 24px;
  border: 1px solid rgba(255, 255, 255, 0.08);
  transition: transform 0.3s ease;
}

.event-poster:hover {
  transform: scale(1.02);
}

.event-poster img { 
  width: 100%; 
  display: block; 
  transition: transform 0.6s ease;
}

.event-poster:hover img {
  transform: scale(1.1);
}

.poster-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(135deg, 
    rgba(10, 14, 23, 0.3) 0%, 
    transparent 50%,
    rgba(10, 14, 23, 0.6) 100%
  );
}

.category-tag { 
  position: absolute; 
  top: 16px; 
  left: 16px; 
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17; 
  font-size: 0.75rem; 
  font-weight: 800; 
  padding: 6px 14px; 
  border-radius: 8px; 
  text-transform: uppercase;
  letter-spacing: 1px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(8px);
}

.sidebar-title { 
  font-size: 2rem; 
  font-weight: 800; 
  margin-bottom: 28px; 
  color: #fff; 
  line-height: 1.2;
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.quick-meta { 
  display: flex; 
  flex-direction: column; 
  gap: 20px; 
  margin-bottom: 32px; 
}

.meta-box { 
  display: flex; 
  gap: 16px; 
  align-items: flex-start;
  padding: 12px;
  background: rgba(18, 24, 38, 0.5);
  border-radius: 14px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  transition: all 0.3s ease;
}

.meta-box:hover {
  background: rgba(18, 24, 38, 0.8);
  border-color: rgba(201, 168, 76, 0.2);
  transform: translateX(4px);
}

.meta-icon {
  width: 40px;
  height: 40px;
  background: linear-gradient(135deg, rgba(201, 168, 76, 0.15), rgba(201, 168, 76, 0.05));
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #c9a84c;
}

.meta-content {
  flex: 1;
}

.meta-label { 
  font-size: 0.7rem; 
  color: #8e9aaf; 
  text-transform: uppercase; 
  margin: 0 0 4px 0;
  letter-spacing: 0.5px;
  font-weight: 600;
}

.meta-val { 
  font-size: 1.1rem; 
  font-weight: 700; 
  margin: 0;
  color: #e0e0e0;
}

.meta-val.highlight { 
  color: #c9a84c; 
  font-weight: 800;
}

.btn-back-outline {
  width: 100%; 
  padding: 16px; 
  background: transparent; 
  border: 2px solid rgba(255, 255, 255, 0.08);
  color: #fff; 
  border-radius: 14px; 
  cursor: pointer; 
  display: flex; 
  align-items: center; 
  justify-content: center; 
  gap: 10px; 
  font-weight: 700; 
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  font-size: 0.9rem;
  letter-spacing: 1px;
  position: relative;
  overflow: hidden;
}

.btn-back-outline::before {
  content: '';
  position: absolute;
  inset: 0;
  background: linear-gradient(135deg, #c9a84c, #a07830);
  opacity: 0;
  transition: opacity 0.3s;
}

.btn-back-outline:hover {
  border-color: transparent;
  transform: translateX(-4px);
}

.btn-back-outline:hover::before {
  opacity: 1;
}

.btn-back-outline svg,
.btn-back-outline span {
  position: relative;
  z-index: 1;
}

.btn-back-outline:hover svg,
.btn-back-outline:hover span {
  color: #0a0e17;
}

.details-card { 
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 24px; 
  padding: 40px; 
  border: 1px solid rgba(255, 255, 255, 0.05);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3);
}

.details-header {
  margin-bottom: 32px;
}

.section-heading { 
  font-size: 1.6rem; 
  font-weight: 800; 
  margin-bottom: 12px; 
  text-transform: uppercase;
  letter-spacing: 1px;
}

.heading-line { 
  width: 60px; 
  height: 4px; 
  background: linear-gradient(90deg, #c9a84c, transparent);
  border-radius: 2px; 
}

.text-description { 
  font-size: 1.05rem; 
  line-height: 1.8; 
  color: #b0b8c5; 
}

.detail-grid-inner { 
  display: grid; 
  grid-template-columns: 1fr 1fr; 
  gap: 32px; 
  margin: 32px 0; 
  padding: 32px 0; 
  border-top: 1px solid rgba(255, 255, 255, 0.05); 
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.d-label { 
  font-size: 0.75rem; 
  color: #c9a84c; 
  font-weight: 700; 
  text-transform: uppercase; 
  margin-bottom: 10px; 
  display: flex;
  align-items: center;
  gap: 8px;
  letter-spacing: 0.5px;
}

.d-label svg {
  opacity: 0.8;
}

.d-value { 
  font-size: 1.1rem; 
  color: #fff; 
  font-weight: 600;
}

.badge-class {
  display: inline-block;
  padding: 4px 12px;
  background: rgba(201, 168, 76, 0.15);
  border-radius: 20px;
  color: #c9a84c;
  font-size: 0.9rem;
  font-weight: 700;
  border: 1px solid rgba(201, 168, 76, 0.3);
}

.location-preview {
  margin: 24px 0;
  padding: 16px 20px;
  background: rgba(18, 24, 38, 0.6);
  border-radius: 14px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  display: flex;
  align-items: center;
  gap: 12px;
  transition: all 0.3s ease;
  cursor: pointer;
}

.location-preview:hover {
  background: rgba(18, 24, 38, 0.9);
  border-color: rgba(201, 168, 76, 0.2);
  transform: translateY(-2px);
}

.loc-icon-wrapper {
  width: 40px;
  height: 40px;
  background: rgba(201, 168, 76, 0.1);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #c9a84c;
}

.loc-text {
  color: #b0b8c5;
  font-size: 0.95rem;
}

.loc-link {
  color: #c9a84c;
  font-weight: 600;
  text-decoration: underline;
  text-underline-offset: 2px;
}

.btn-buy-now {
  width: 100%; 
  margin-top: 32px; 
  padding: 20px 32px; 
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17; 
  border: none; 
  border-radius: 16px; 
  font-weight: 800;
  font-size: 1.1rem; 
  cursor: pointer; 
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  position: relative;
  overflow: hidden;
  box-shadow: 0 8px 20px rgba(201, 168, 76, 0.2);
}

.btn-buy-now::before {
  content: '';
  position: absolute;
  inset: 0;
  background: linear-gradient(135deg, #d4af37, #c9a84c);
  opacity: 0;
  transition: opacity 0.3s;
}

.btn-buy-now:hover {
  transform: translateY(-4px);
  box-shadow: 0 15px 30px rgba(201, 168, 76, 0.3);
}

.btn-buy-now:hover::before {
  opacity: 1;
}

.btn-buy-now span,
.btn-buy-now svg {
  position: relative;
  z-index: 1;
}

.btn-buy-now svg {
  transition: transform 0.3s;
}

.btn-buy-now:hover svg {
  transform: translateX(4px);
}

.lineup-card { 
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 24px; 
  padding: 32px; 
  border: 1px solid rgba(255, 255, 255, 0.05);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3);
  position: sticky;
  top: 100px;
}

.lineup-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 28px;
}

.lineup-heading {
  font-size: 1.3rem;
  font-weight: 800;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin: 0;
}

.lineup-icon {
  width: 40px;
  height: 40px;
  background: rgba(201, 168, 76, 0.1);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #c9a84c;
}

.timeline { 
  border-left: 2px solid rgba(201, 168, 76, 0.2); 
  margin-left: 10px; 
}

.timeline-item { 
  position: relative; 
  padding: 0 0 32px 24px;
  animation: slideIn 0.5s ease-out forwards;
  opacity: 0;
}

@keyframes slideIn {
  from {
    opacity: 0;
    transform: translateX(-10px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

.timeline-item::before {
  content: ""; 
  position: absolute; 
  left: -8px; 
  top: 8px; 
  width: 14px; 
  height: 14px;
  background: #0a0e17; 
  border: 2.5px solid #c9a84c; 
  border-radius: 50%;
  transition: all 0.3s ease;
}

.timeline-item:hover::before {
  transform: scale(1.2);
  box-shadow: 0 0 0 4px rgba(201, 168, 76, 0.2);
}

.is-main::before { 
  background: #c9a84c; 
  transform: scale(1.3);
  box-shadow: 0 0 0 4px rgba(201, 168, 76, 0.15);
}

.is-main:hover::before {
  transform: scale(1.5);
}

.time-tag {
  font-size: 0.8rem;
  color: #c9a84c;
  font-weight: 700;
  margin-bottom: 6px;
  letter-spacing: 0.5px;
}

.band-info {
  display: flex;
  align-items: center;
  gap: 10px;
  flex-wrap: wrap;
}

.band-name {
  font-size: 1.1rem;
  font-weight: 700;
  color: #fff;
}

.main-badge {
  font-size: 0.65rem;
  padding: 2px 8px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border-radius: 20px;
  font-weight: 800;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

@media (max-width: 1150px) {
  .info-grid { 
    grid-template-columns: 280px 1fr; 
  }
  
  .info-lineup { 
    grid-column: span 2; 
    margin-top: 32px; 
  }
  
  .lineup-card {
    position: static;
  }
}

@media (max-width: 800px) {
  .info-grid { 
    grid-template-columns: 1fr; 
    gap: 30px;
  }
  
  .info-sidebar, 
  .info-lineup { 
    grid-column: auto; 
    position: static; 
  }
  
  .details-card {
    padding: 30px 20px;
  }
  
  .detail-grid-inner {
    grid-template-columns: 1fr;
    gap: 20px;
  }
  
  .sidebar-title {
    font-size: 1.8rem;
  }
  
  .btn-buy-now {
    font-size: 1rem;
    padding: 18px 24px;
  }
}

@media (max-width: 480px) {
  .main-info {
    padding: 40px 16px;
  }
  
  .category-tag {
    top: 12px;
    left: 12px;
    font-size: 0.65rem;
    padding: 4px 10px;
  }
  
  .meta-box {
    padding: 10px;
  }
  
  .meta-val {
    font-size: 1rem;
  }
}
</style>