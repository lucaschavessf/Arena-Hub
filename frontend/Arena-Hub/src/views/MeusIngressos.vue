<template>
  <div class="page">
    <AppNavbar />

    <main class="main-tickets">
      <div class="tickets-container">
        <!-- Breadcrumb -->
        <div class="breadcrumb">
          <router-link to="/" class="breadcrumb-link">Início</router-link>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
          <span class="breadcrumb-current">Meus Ingressos</span>
        </div>

        <header class="tickets-header">
          <div class="header-text">
            <div class="title-wrapper">
              <h1 class="page-title">Meus Ingressos</h1>
              <div class="title-line"></div>
            </div>
            <p class="page-subtitle">Gerencie suas entradas e prepare seu QR Code</p>
          </div>
          <button class="btn-back" @click="$router.back()">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <path d="M19 12H5M12 19l-7-7 7-7"/>
            </svg>
            <span>VOLTAR</span>
          </button>
        </header>

        <div class="tickets-grid" v-if="meusIngressos.length > 0">
          <div v-for="(ing, index) in meusIngressos" :key="ing.id" 
               class="ticket-card" 
               :class="{ 'is-used': ing.status !== 'ativo' }"
               :style="{ animationDelay: `${index * 0.1}s` }">
            
            <div class="ticket-visual">
              <img :src="ing.image" :alt="ing.title" class="event-thumb" />
              <div class="image-overlay"></div>
              <div class="status-badge" :class="ing.status">
                <span class="status-dot"></span>
                {{ ing.status === 'ativo' ? 'Disponível' : 'Utilizado' }}
              </div>
            </div>

            <div class="ticket-body">
              <div class="ticket-main-info">
                <h2 class="event-name">{{ ing.title }}</h2>
                <div class="event-meta-group">
                  <div class="meta-inline">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
                    </svg>
                    <span>{{ ing.date }}</span>
                  </div>
                  <div class="meta-inline">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/>
                    </svg>
                    <span>{{ ing.time }}</span>
                  </div>
                  <div class="meta-inline">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/>
                    </svg>
                    <span>{{ ing.venue || 'Arena Pernambuco' }}</span>
                  </div>
                </div>
              </div>

              <div class="ticket-footer-details">
                <div class="detail-pill">
                  <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                    <path d="M2 9V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v4"/>
                  </svg>
                  <span class="label">SETOR:</span>
                  <span class="val">{{ ing.tipo }}</span>
                </div>
                <div class="detail-pill">
                  <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                    <circle cx="9" cy="9" r="2"/><circle cx="15" cy="9" r="2"/><path d="M5 15h14"/>
                  </svg>
                  <span class="label">QTD:</span>
                  <span class="val">{{ ing.quantidade }}x</span>
                </div>
                <div class="detail-pill">
                  <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                    <circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/>
                  </svg>
                  <span class="label">PEDIDO:</span>
                  <span class="val">#{{ ing.id.toString().padStart(6, '0') }}</span>
                </div>
              </div>
            </div>

            <div class="ticket-divider-wrapper">
              <div class="notch top"></div>
              <div class="dashed-line"></div>
              <div class="notch bottom"></div>
            </div>

            <div class="ticket-action-area">
              <button class="view-qr-btn" @click="toggleQr(ing.id)" :disabled="ing.status !== 'ativo'">
                <div class="qr-icon-wrapper">
                  <svg width="28" height="28" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <rect x="3" y="3" width="7" height="7"/>
                    <rect x="14" y="3" width="7" height="7"/>
                    <rect x="3" y="14" width="7" height="7"/>
                    <rect x="14" y="14" width="3" height="3"/>
                    <rect x="18" y="18" width="3" height="3"/>
                    <rect x="18" y="14" width="3" height="3"/>
                    <rect x="14" y="18" width="3" height="3"/>
                  </svg>
                </div>
                <span>ACESSAR QR</span>
                <svg v-if="ing.status === 'ativo'" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <polyline points="9 18 15 12 9 6"/>
                </svg>
              </button>
            </div>

            <transition name="fade-scale">
              <div v-if="activeQr === ing.id" class="qr-overlay" @click.self="activeQr = null">
                <div class="qr-modal">
                  <button class="close-qr" @click="activeQr = null">
                    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                      <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
                    </svg>
                  </button>
                  
                  <div class="qr-modal-header">
                    <div class="qr-icon-lg">
                      <svg width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/>
                        <rect x="3" y="14" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/>
                      </svg>
                    </div>
                    <h3 class="qr-title">Seu Ingresso</h3>
                    <p class="qr-subtitle">{{ ing.title }}</p>
                  </div>
                  
                  <div class="qr-code-container">
                    <img 
                      src="https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=ARENAHUB-12345" 
                      alt="QR Code do ingresso" 
                    />
                    <div class="qr-code-border"></div>
                  </div>
                  
                  <div class="qr-info">
                    <div class="qr-hash-wrapper">
                      <code class="qr-hash">{{ ing.qrCode || 'ARENA-HUB-ING-2026' }}</code>
                      <button class="copy-btn" @click="copyToClipboard(ing.qrCode || 'ARENA-HUB-ING-2026')">
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                          <rect x="9" y="9" width="13" height="13" rx="2"/><path d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"/>
                        </svg>
                      </button>
                    </div>
                    <p class="qr-warning">
                      <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                        <circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/>
                      </svg>
                      Apresente este código na entrada do evento
                    </p>
                  </div>
                </div>
              </div>
            </transition>
          </div>
        </div>

        <div v-else class="empty-tickets">
          <div class="empty-icon">
            <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
              <path d="M2 9V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v4"/>
              <path d="M2 15v4a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-4"/>
              <path d="M2 9a3 3 0 0 1 0 6"/>
              <path d="M22 9a3 3 0 0 0 0 6"/>
            </svg>
          </div>
          <h3>Você ainda não possui ingressos</h3>
          <p>Explore os eventos disponíveis e garanta sua presença!</p>
          <button class="btn-explore" @click="$router.push('/')">
            <span>Explorar Eventos</span>
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <line x1="5" y1="12" x2="19" y2="12"/><polyline points="12 5 19 12 12 19"/>
            </svg>
          </button>
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

function copyToClipboard(text: string) {
  navigator.clipboard?.writeText(text)
  alert('Código copiado!')
}
</script>

<style scoped>
.page { 
  background: #0a0e17; 
  min-height: 100vh; 
  display: flex; 
  flex-direction: column; 
}

.main-tickets { 
  flex: 1; 
  padding: 60px 20px; 
  display: flex;
  justify-content: center;
}

.tickets-container { 
  width: 100%;
  max-width: 1000px;
  margin: 0 auto;
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 24px;
  font-size: 0.85rem;
  color: #8e9aaf;
}

.breadcrumb-link {
  color: #8e9aaf;
  text-decoration: none;
  transition: color 0.2s;
}

.breadcrumb-link:hover {
  color: #c9a84c;
}

.breadcrumb-current {
  color: #c9a84c;
  font-weight: 600;
}

.breadcrumb svg {
  color: #4a5568;
}

.tickets-header { 
  display: flex; 
  justify-content: space-between; 
  align-items: flex-end; 
  margin-bottom: 40px; 
}

.header-text {
  flex: 1;
}

.title-wrapper {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 8px;
}

.page-title { 
  font-size: 2.2rem; 
  font-weight: 900; 
  color: #fff; 
  margin: 0;
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.title-line {
  flex: 1;
  height: 2px;
  background: linear-gradient(90deg, #c9a84c, transparent);
  border-radius: 1px;
}

.page-subtitle { 
  color: #8e9aaf; 
  font-size: 0.95rem; 
  margin-left: 4px;
}

.btn-back {
  background: transparent;
  border: 1.5px solid rgba(255, 255, 255, 0.1);
  color: #fff;
  padding: 12px 20px;
  border-radius: 12px;
  font-weight: 700;
  font-size: 0.85rem;
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.btn-back:hover {
  background: rgba(255, 255, 255, 0.05);
  border-color: rgba(201, 168, 76, 0.3);
  transform: translateX(-4px);
}

.btn-back svg {
  transition: transform 0.3s ease;
}

.btn-back:hover svg {
  transform: translateX(-2px);
}

.tickets-grid { 
  display: flex; 
  flex-direction: column; 
  gap: 28px; 
}

.ticket-card {
  display: flex;
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 24px;
  overflow: visible;
  border: 1px solid rgba(255, 255, 255, 0.06);
  position: relative;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  animation: slideInUp 0.5s ease-out forwards;
  opacity: 0;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
}

@keyframes slideInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.ticket-card:hover {
  transform: translateY(-4px);
  border-color: rgba(201, 168, 76, 0.25);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3);
}

.ticket-card.is-used {
  opacity: 0.7;
}

.ticket-visual { 
  position: relative; 
  width: 240px; 
  flex-shrink: 0; 
  overflow: hidden; 
  border-radius: 24px 0 0 24px; 
}

.event-thumb { 
  width: 100%; 
  height: 100%; 
  object-fit: cover; 
  transition: transform 0.6s ease;
}

.ticket-card:hover .event-thumb {
  transform: scale(1.05);
}

.image-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(135deg, 
    rgba(10, 14, 23, 0.3) 0%, 
    transparent 50%,
    rgba(10, 14, 23, 0.6) 100%
  );
}

.status-badge {
  position: absolute; 
  top: 16px; 
  left: 16px;
  padding: 6px 12px; 
  border-radius: 30px; 
  font-size: 0.7rem; 
  font-weight: 800;
  text-transform: uppercase; 
  letter-spacing: 0.5px;
  display: flex;
  align-items: center;
  gap: 6px;
  backdrop-filter: blur(8px);
}

.status-badge.ativo { 
  background: rgba(76, 201, 142, 0.15);
  color: #4cc98e; 
  border: 1px solid rgba(76, 201, 142, 0.3);
}

.status-badge.utilizado { 
  background: rgba(142, 154, 175, 0.15);
  color: #8e9aaf; 
  border: 1px solid rgba(142, 154, 175, 0.3);
}

.status-dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: currentColor;
  animation: pulse 2s infinite;
}

.status-badge.ativo .status-dot {
  background: #4cc98e;
  box-shadow: 0 0 10px #4cc98e;
}

@keyframes pulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.5; }
}

.ticket-body { 
  flex: 1; 
  padding: 28px; 
  display: flex; 
  flex-direction: column; 
  justify-content: space-between; 
}

.event-name { 
  font-size: 1.5rem; 
  font-weight: 800; 
  color: #fff; 
  margin-bottom: 16px; 
  line-height: 1.3;
}

.event-meta-group { 
  display: flex; 
  flex-wrap: wrap;
  gap: 20px; 
}

.meta-inline { 
  display: flex; 
  align-items: center; 
  gap: 8px; 
  font-size: 0.9rem; 
  color: #b0b8c5; 
}

.meta-inline svg { 
  color: #c9a84c; 
  opacity: 0.8;
}

.ticket-footer-details { 
  display: flex; 
  flex-wrap: wrap;
  gap: 12px; 
  margin-top: 24px; 
}

.detail-pill { 
  background: rgba(28, 36, 51, 0.6);
  padding: 8px 16px; 
  border-radius: 30px; 
  font-size: 0.8rem; 
  display: flex;
  align-items: center;
  gap: 8px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  backdrop-filter: blur(4px);
}

.detail-pill svg {
  color: #c9a84c;
  opacity: 0.7;
}

.detail-pill .label { 
  color: #8e9aaf; 
  font-weight: 700; 
  margin-right: 4px; 
  letter-spacing: 0.3px;
}

.detail-pill .val {
  color: #fff;
  font-weight: 700;
}

.ticket-divider-wrapper {
  width: 40px; 
  position: relative; 
  display: flex; 
  justify-content: center; 
  align-items: center;
}

.dashed-line { 
  width: 1px; 
  height: 70%; 
  border-left: 2px dashed rgba(201, 168, 76, 0.2);
}

.notch { 
  width: 32px; 
  height: 32px; 
  background: #0a0e17; 
  border-radius: 50%; 
  position: absolute; 
  left: 4px;
}

.notch.top { 
  top: -16px; 
  box-shadow: inset 0 -2px 8px rgba(0, 0, 0, 0.3);
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.notch.bottom { 
  bottom: -16px; 
  box-shadow: inset 0 2px 8px rgba(0, 0, 0, 0.3);
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.ticket-action-area { 
  display: flex; 
  align-items: center; 
  padding-right: 16px; 
}

.view-qr-btn {
  padding: 24px 20px; 
  background: transparent; 
  border: none; 
  cursor: pointer;
  display: flex; 
  flex-direction: column; 
  align-items: center; 
  gap: 12px; 
  transition: all 0.3s ease; 
  width: 160px; 
  border-radius: 0 24px 24px 0;
  color: #b0b8c5;
}

.view-qr-btn:hover:not(:disabled) { 
  background: linear-gradient(90deg, rgba(201, 168, 76, 0.08), transparent);
  color: #c9a84c;
}

.view-qr-btn:disabled {
  opacity: 0.4;
  cursor: not-allowed;
}

.qr-icon-wrapper { 
  color: currentColor;
  transition: transform 0.3s ease;
}

.view-qr-btn:hover:not(:disabled) .qr-icon-wrapper {
  transform: scale(1.1);
}

.view-qr-btn span { 
  font-size: 0.75rem; 
  font-weight: 800; 
  letter-spacing: 1.5px;
}

.view-qr-btn svg:last-child {
  transition: transform 0.3s ease;
}

.view-qr-btn:hover:not(:disabled) svg:last-child {
  transform: translateX(4px);
}

.qr-overlay {
  position: fixed; 
  inset: 0; 
  background: rgba(10, 14, 23, 0.95);
  z-index: 2000; 
  display: flex; 
  align-items: center; 
  justify-content: center; 
  padding: 20px;
  backdrop-filter: blur(12px);
}

.qr-modal {
  background: linear-gradient(135deg, #ffffff 0%, #f8f9fa 100%);
  color: #0a0e17; 
  width: 100%; 
  max-width: 400px;
  padding: 40px 32px; 
  border-radius: 32px; 
  text-align: center; 
  position: relative;
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.5);
}

.close-qr {
  position: absolute;
  top: 20px;
  right: 20px;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  background: rgba(10, 14, 23, 0.05);
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: #5a6375;
  transition: all 0.2s ease;
}

.close-qr:hover {
  background: #ff5e5e;
  color: white;
  transform: rotate(90deg);
}

.qr-modal-header {
  margin-bottom: 24px;
}

.qr-icon-lg {
  width: 64px;
  height: 64px;
  margin: 0 auto 16px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border-radius: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #0a0e17;
}

.qr-title {
  font-size: 1.5rem;
  font-weight: 800;
  margin-bottom: 4px;
  color: #0a0e17;
}

.qr-subtitle {
  font-size: 0.95rem;
  color: #6b7280;
}

.qr-code-container { 
  padding: 24px; 
  background: white; 
  border-radius: 20px; 
  margin: 20px 0;
  position: relative;
  display: inline-block;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.qr-code-container img { 
  width: 100%; 
  max-width: 200px;
  display: block;
}

.qr-code-border {
  position: absolute;
  inset: -2px;
  border-radius: 22px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  opacity: 0.3;
  z-index: -1;
}

.qr-info {
  margin-top: 20px;
}

.qr-hash-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  margin-bottom: 16px;
}

.qr-hash {
  background: rgba(201, 168, 76, 0.1);
  padding: 10px 16px;
  border-radius: 30px;
  font-size: 0.9rem;
  font-family: monospace;
  color: #0a0e17;
  border: 1px solid rgba(201, 168, 76, 0.2);
}

.copy-btn {
  width: 36px;
  height: 36px;
  border-radius: 10px;
  background: rgba(201, 168, 76, 0.1);
  border: 1px solid rgba(201, 168, 76, 0.2);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: #c9a84c;
  transition: all 0.2s ease;
}

.copy-btn:hover {
  background: #c9a84c;
  color: #0a0e17;
}

.qr-warning {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  font-size: 0.8rem;
  color: #6b7280;
  padding: 12px;
  background: rgba(76, 201, 142, 0.1);
  border-radius: 12px;
}

.qr-warning svg {
  color: #4cc98e;
}

.empty-tickets {
  text-align: center;
  padding: 80px 20px;
}

.empty-icon {
  margin-bottom: 24px;
  color: #c9a84c;
  opacity: 0.5;
}

.empty-tickets h3 {
  font-size: 1.5rem;
  font-weight: 700;
  margin-bottom: 12px;
  color: #fff;
}

.empty-tickets p {
  color: #8e9aaf;
  margin-bottom: 32px;
}

.btn-explore {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border: none;
  padding: 16px 32px;
  border-radius: 14px;
  font-weight: 800;
  font-size: 1rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 12px;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  text-transform: uppercase;
  letter-spacing: 1px;
  box-shadow: 0 4px 15px rgba(201, 168, 76, 0.2);
}

.btn-explore:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 25px rgba(201, 168, 76, 0.3);
}

.btn-explore svg {
  transition: transform 0.3s ease;
}

.btn-explore:hover svg {
  transform: translateX(4px);
}

.fade-scale-enter-active, 
.fade-scale-leave-active { 
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1); 
}

.fade-scale-enter-from, 
.fade-scale-leave-to { 
  opacity: 0; 
  transform: scale(0.9); 
}

@media (max-width: 850px) {
  .ticket-card { 
    flex-direction: column; 
  }
  
  .ticket-visual { 
    width: 100%; 
    height: 180px; 
    border-radius: 24px 24px 0 0; 
  }
  
  .ticket-divider-wrapper { 
    height: 40px; 
    width: 100%; 
  }
  
  .dashed-line { 
    width: 80%; 
    height: 1px; 
    border-left: none; 
    border-top: 2px dashed rgba(201, 168, 76, 0.2); 
  }
  
  .notch { 
    left: auto; 
    top: 4px; 
  }
  
  .notch.top { 
    left: -16px; 
    top: 4px; 
  }
  
  .notch.bottom { 
    right: -16px; 
    top: 4px; 
  }
  
  .view-qr-btn { 
    width: 100%; 
    border-radius: 0 0 24px 24px; 
    padding: 20px;
    flex-direction: row;
    justify-content: center;
  }
  
  .tickets-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 20px;
  }
  
  .btn-back {
    width: 100%;
    justify-content: center;
  }
}

@media (max-width: 600px) {
  .main-tickets {
    padding: 40px 16px;
  }
  
  .page-title {
    font-size: 1.8rem;
  }
  
  .event-name {
    font-size: 1.3rem;
  }
  
  .event-meta-group {
    gap: 12px;
  }
  
  .ticket-footer-details {
    gap: 8px;
  }
  
  .detail-pill {
    padding: 6px 12px;
    font-size: 0.7rem;
  }
  
  .qr-modal {
    padding: 32px 20px;
  }
}
</style>