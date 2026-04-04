<template>
  <div class="page">
    <AppNavbar />

    <main class="main-tickets">
      <div class="tickets-container">
        <header class="tickets-header">
          <div class="header-text">
            <h1 class="page-title">Meus Ingressos</h1>
            <p class="page-subtitle">Gerencie suas entradas e prepare seu QR Code</p>
          </div>
          <button class="btn-back" @click="$router.back()">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><path d="M19 12H5M12 19l-7-7 7-7"/></svg>
            VOLTAR
          </button>
        </header>

        <div class="tickets-grid" v-if="meusIngressos.length > 0">
          <div v-for="ing in meusIngressos" :key="ing.id" 
               class="ticket-card" :class="{ 'is-used': ing.status !== 'ativo' }">
            
            <div class="ticket-visual">
              <img :src="ing.image" :alt="ing.title" class="event-thumb" />
              <div class="status-badge" :class="ing.status">
                {{ ing.status === 'ativo' ? '• Disponível' : 'Utilizado' }}
              </div>
            </div>

            <div class="ticket-body">
              <div class="ticket-main-info">
                <h2 class="event-name">{{ ing.title }}</h2>
                <div class="event-meta-group">
                  <div class="meta-inline">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/></svg>
                    <span>{{ ing.date }}</span>
                  </div>
                  <div class="meta-inline">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/></svg>
                    <span>{{ ing.time }}</span>
                  </div>
                </div>
              </div>

              <div class="ticket-footer-details">
                <div class="detail-pill">
                  <span class="label">SETOR:</span>
                  <span class="val">{{ ing.tipo }}</span>
                </div>
                <div class="detail-pill">
                  <span class="label">QTD:</span>
                  <span class="val">{{ ing.quantidade }}x</span>
                </div>
              </div>
            </div>

            <div class="ticket-divider-wrapper">
              <div class="notch top"></div>
              <div class="dashed-line"></div>
              <div class="notch bottom"></div>
            </div>

            <div class="ticket-action-area">
              <button class="view-qr-btn" @click="toggleQr(ing.id)">
                <div class="qr-icon-wrapper">
                  <svg width="28" height="28" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/><rect x="3" y="14" width="7" height="7"/><rect x="14" y="14" width="3" height="3"/><rect x="18" y="18" width="3" height="3"/><rect x="18" y="14" width="3" height="3"/><rect x="14" y="18" width="3" height="3"/>
                  </svg>
                </div>
                <span>ACESSAR QR</span>
              </button>
            </div>

            <transition name="fade-scale">
              <div v-if="activeQr === ing.id" class="qr-overlay" @click.self="activeQr = null">
                <div class="qr-modal">
                  <button class="close-qr" @click="activeQr = null">✕</button>
                  <h3 class="qr-title">Seu Ingresso</h3>
                  <p class="qr-subtitle">{{ ing.title }}</p>
                  
                  <div class="qr-code-container">
                    <img src="https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=ARENAHUB-12345" alt="QR Code" />
                  </div>
                  
                  <code class="qr-hash">{{ ing.qrCode || 'ARENA-HUB-ING-2026' }}</code>
                  <p class="qr-warning">Apresente este código na entrada do evento</p>
                </div>
              </div>
            </transition>
          </div>
        </div>

        <div v-else class="empty-tickets">
          <div class="empty-art">🎟️</div>
          <h3>Você ainda não possui ingressos</h3>
          <p>Explore os eventos disponíveis e garanta sua presença!</p>
          <button class="btn-explore" @click="$router.push('/')">Explorar Eventos</button>
        </div>
      </div>
    </main>
    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'
import { meusIngressos } from '../data/mock.js'

const activeQr = ref<number | null>(null)
function toggleQr(id: number) { 
  activeQr.value = activeQr.value === id ? null : id 
}
</script>

<style scoped>
.page { background: #0a0e17; min-height: 100vh; display: flex; flex-direction: column; }

/* CENTRALIZAÇÃO MESTRE */
.main-tickets { 
  flex: 1; 
  padding: 80px 20px; 
  display: flex;
  justify-content: center;
}

.tickets-container { 
  width: 100%;
  max-width: 1000px; /* Largura ideal para tickets horizontais */
  margin: 0 auto;
}

/* Header */
.tickets-header { display: flex; justify-content: space-between; align-items: flex-end; margin-bottom: 48px; }
.page-title { font-size: 2.2rem; font-weight: 900; color: #fff; margin-bottom: 4px; }
.page-subtitle { color: #9ca3af; font-size: 1rem; }

/* Ticket Card - Desktop Layout */
.tickets-grid { display: flex; flex-direction: column; gap: 32px; }

.ticket-card {
  display: flex;
  background: #111827;
  border-radius: 24px;
  overflow: visible; /* Para os notches aparecerem corretamente */
  border: 1px solid rgba(255,255,255,0.05);
  position: relative;
  transition: transform 0.3s ease, border-color 0.3s;
}

.ticket-card:hover {
  transform: translateY(-4px);
  border-color: rgba(201, 168, 76, 0.3);
}

/* Visual Side */
.ticket-visual { position: relative; width: 240px; flex-shrink: 0; overflow: hidden; border-radius: 24px 0 0 24px; }
.event-thumb { width: 100%; height: 100%; object-fit: cover; }
.status-badge {
  position: absolute; top: 16px; left: 16px;
  padding: 6px 14px; border-radius: 8px; font-size: 0.7rem; font-weight: 800;
  text-transform: uppercase; letter-spacing: 0.5px;
}
.status-badge.ativo { background: #4cc98e; color: #0a0e17; }

/* Body Side */
.ticket-body { flex: 1; padding: 32px; display: flex; flex-direction: column; justify-content: space-between; }
.event-name { font-size: 1.5rem; font-weight: 800; color: #fff; margin-bottom: 12px; }
.event-meta-group { display: flex; gap: 24px; }
.meta-inline { display: flex; align-items: center; gap: 8px; font-size: 0.9rem; color: #9ca3af; }
.meta-inline svg { color: #c9a84c; }

.ticket-footer-details { display: flex; gap: 16px; margin-top: 24px; }
.detail-pill { background: rgba(255,255,255,0.05); padding: 8px 16px; border-radius: 10px; font-size: 0.8rem; }
.detail-pill .label { color: #c9a84c; font-weight: 800; margin-right: 8px; }

/* Divider com Notches Profissional */
.ticket-divider-wrapper {
  width: 40px; position: relative; display: flex; justify-content: center; align-items: center;
}
.dashed-line { width: 1px; height: 60%; border-left: 2px dashed rgba(255,255,255,0.15); }
.notch { 
  width: 32px; height: 32px; background: #0a0e17; 
  border-radius: 50%; position: absolute; left: 4px;
}
.notch.top { top: -16px; box-shadow: inset 0 -4px 5px rgba(0,0,0,0.3); }
.notch.bottom { bottom: -16px; box-shadow: inset 0 4px 5px rgba(0,0,0,0.3); }

/* Botão QR */
.ticket-action-area { display: flex; align-items: center; padding-right: 20px; }
.view-qr-btn {
  padding: 32px; background: transparent; border: none; cursor: pointer;
  display: flex; flex-direction: column; align-items: center; gap: 16px; 
  transition: 0.3s; width: 180px; border-radius: 0 24px 24px 0;
}
.view-qr-btn:hover { background: rgba(201, 168, 76, 0.05); }
.qr-icon-wrapper { color: #c9a84c; }
.view-qr-btn span { font-size: 0.75rem; font-weight: 800; color: #9ca3af; letter-spacing: 1px; }

/* QR Modal */
.qr-overlay {
  position: fixed; inset: 0; background: rgba(10, 14, 23, 0.95);
  z-index: 2000; display: flex; align-items: center; justify-content: center; padding: 20px;
  backdrop-filter: blur(10px);
}
.qr-modal {
  background: white; color: #0a0e17; width: 100%; max-width: 380px;
  padding: 48px 32px; border-radius: 32px; text-align: center; position: relative;
}
.qr-code-container { padding: 24px; background: #f3f4f6; border-radius: 20px; margin: 24px 0; }
.qr-code-container img { width: 100%; max-width: 200px; }

/* Mobile Adaptations */
@media (max-width: 850px) {
  .ticket-card { flex-direction: column; }
  .ticket-visual { width: 100%; height: 160px; border-radius: 24px 24px 0 0; }
  .ticket-divider-wrapper { height: 40px; width: 100%; }
  .dashed-line { width: 80%; height: 1px; border-left: none; border-top: 2px dashed rgba(255,255,255,0.15); }
  .notch { left: auto; top: 4px; }
  .notch.top { left: -16px; top: 4px; }
  .notch.bottom { right: -16px; top: 4px; }
  .view-qr-btn { width: 100%; border-radius: 0 0 24px 24px; padding: 20px; }
}

.fade-scale-enter-active, .fade-scale-leave-active { transition: 0.3s ease; }
.fade-scale-enter-from, .fade-scale-leave-to { opacity: 0; transform: scale(0.9); }
</style>