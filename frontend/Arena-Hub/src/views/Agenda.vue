<template>
  <div class="admin-layout" :class="{ 'sidebar-open': isSidebarOpen }">
    <div class="sidebar-overlay" @click="isSidebarOpen = false"></div>

    <aside class="sidebar">
      <div class="sidebar-header">
        <router-link to="/" class="brand">
          <div class="logo-box">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <path d="M2 9V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v4"/>
              <path d="M2 15v4a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-4"/>
              <path d="M2 9a3 3 0 0 1 0 6"/>
              <path d="M22 9a3 3 0 0 0 0 6"/>
            </svg>
          </div>
          <span>Arena<span class="highlight">Hub</span></span>
        </router-link>
      </div>

      <nav class="sidebar-nav">
        <router-link to="/admin" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/>
            <rect x="3" y="14" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/>
          </svg>
          <span>Dashboard</span>
        </router-link>
        
        <router-link to="/admin/agenda" class="nav-item active">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
          </svg>
          <span>Agenda</span>
        </router-link>

        <router-link to="/admin/solicitacoes" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/>
            <polyline points="14 2 14 8 20 8"/>
            <path d="M9 15l2 2 4-4"/>
          </svg>
          <span>Solicitações</span>
        </router-link>

        <div class="nav-spacer"></div>

        <button @click="$router.push('/')" class="nav-item logout">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/>
          </svg>
          <span>Sair do Admin</span>
        </button>
      </nav>
    </aside>

    <div class="main-wrapper">
      <AppNavbar :isAdmin="true" />
      
      <main class="content-container">
        <!-- Breadcrumb -->
        <div class="breadcrumb">
          <router-link to="/admin" class="breadcrumb-link">Admin</router-link>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
          <span class="breadcrumb-current">Agenda</span>
        </div>

        <header class="content-header">
          <div class="header-titles">
            <h1>Cronograma da Arena</h1>
            <p>Gestão de ocupação e horários técnicos • {{ diasSemana[0] }} a {{ diasSemana[6] }}</p>
          </div>

          <div class="header-actions">
            <div class="date-navigator">
              <button class="nav-btn" @click="semanaAnterior">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <polyline points="15 18 9 12 15 6"/>
                </svg>
              </button>
              <span class="current-date">12 - 18 de Abril, 2026</span>
              <button class="nav-btn" @click="proximaSemana">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <polyline points="9 18 15 12 9 6"/>
                </svg>
              </button>
            </div>
            
            <div class="view-options">
              <button class="view-btn active">Semana</button>
              <button class="view-btn">Mês</button>
            </div>
            
            <button class="btn-add">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <line x1="12" y1="5" x2="12" y2="19"/><line x1="5" y1="12" x2="19" y2="12"/>
              </svg>
              <span>Novo Evento</span>
            </button>
          </div>
        </header>

        <!-- Legenda de Cores -->
        <div class="legend-bar">
          <span class="legend-label">Tipos de Evento:</span>
          <div class="legend-items">
            <div class="legend-item">
              <span class="color-dot" style="background: #c9a84c;"></span>
              <span>Produção</span>
            </div>
            <div class="legend-item">
              <span class="color-dot" style="background: #4c87c9;"></span>
              <span>Show</span>
            </div>
            <div class="legend-item">
              <span class="color-dot" style="background: #c94c4c;"></span>
              <span>Reunião</span>
            </div>
            <div class="legend-item">
              <span class="color-dot" style="background: #4cc98e;"></span>
              <span>Ensaio</span>
            </div>
          </div>
        </div>

        <div class="calendar-card">
          <div class="calendar-scroll-area">
            <div class="calendar-grid-header">
              <div class="time-col-header"></div>
              <div class="days-columns">
                <div v-for="(dia, idx) in diasSemana" :key="dia" class="day-label" :class="{ 'is-today': idx === 3 }">
                  <span class="day-name">{{ dia }}</span>
                  <span class="day-number">{{ 12 + idx }}</span>
                </div>
              </div>
            </div>

            <div class="calendar-grid-body">
              <div class="time-gutter">
                <div v-for="h in horas" :key="h" class="time-marker">
                  <span class="time-text">{{ h }}</span>
                </div>
              </div>

              <div class="event-columns">
                <div v-for="col in 7" :key="col" class="event-column">
                  <div v-for="h in horas" :key="h" class="hour-row"></div>
                  
                  <!-- Linha do horário atual -->
                  <div v-if="col === 3" class="current-time-line" :style="{ top: currentTimePosition + 'px' }">
                    <div class="time-indicator"></div>
                  </div>
                  
                  <div
                    v-for="ev in eventosCalendario.filter(e => e.col === col - 1)"
                    :key="ev.id"
                    class="event-block"
                    :style="{ 
                      top: ev.top + 'px', 
                      height: ev.height + 'px', 
                      borderLeftColor: ev.color 
                    }"
                    @click="abrirEvento(ev)"
                  >
                    <div class="event-inner" :style="{ background: ev.color + '15' }">
                      <span class="ev-title">{{ ev.title }}</span>
                      <span class="ev-time">{{ ev.horario }}</span>
                      <span v-if="ev.local" class="ev-local">{{ ev.local }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Resumo do Dia -->
        <div class="day-summary">
          <div class="summary-header">
            <h3>
              <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/>
              </svg>
              Próximos Eventos Hoje
            </h3>
          </div>
          <div class="summary-list">
            <div v-for="ev in eventosHoje" :key="ev.id" class="summary-item">
              <div class="summary-time">{{ ev.horarioInicio }}</div>
              <div class="summary-info">
                <span class="summary-title">{{ ev.title }}</span>
                <span class="summary-local">{{ ev.local }}</span>
              </div>
              <div class="summary-status" :style="{ background: ev.color + '20', color: ev.color }">
                {{ ev.status }}
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>

    <button class="menu-toggle" @click="isSidebarOpen = !isSidebarOpen">
      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
        <line x1="3" y1="12" x2="21" y2="12"/><line x1="3" y1="6" x2="21" y2="6"/><line x1="3" y1="18" x2="21" y2="18"/>
      </svg>
    </button>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'

const isSidebarOpen = ref(false)
const diasSemana = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb']
const horas = ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00']

const HOUR_HEIGHT = 80

function toTop(horaDecimal: number): number {
  return (horaDecimal - 10) * HOUR_HEIGHT
}

const eventosCalendario = [
  { 
    id: 1, 
    col: 1, 
    title: 'Check-in Staff', 
    horario: '10:00 - 12:30', 
    horarioInicio: '10:00',
    local: 'Sala de Produção',
    top: toTop(10), 
    height: HOUR_HEIGHT * 2.5, 
    color: '#c9a84c',
    status: 'Em andamento'
  },
  { 
    id: 2, 
    col: 2, 
    title: 'Show: Rock Nacional', 
    horario: '14:00 - 17:00', 
    horarioInicio: '14:00',
    local: 'Palco Principal',
    top: toTop(14), 
    height: HOUR_HEIGHT * 3, 
    color: '#4c87c9',
    status: 'Confirmado'
  },
  { 
    id: 3, 
    col: 4, 
    title: 'Reunião Técnica', 
    horario: '11:00 - 13:00', 
    horarioInicio: '11:00',
    local: 'Sala de Reuniões',
    top: toTop(11), 
    height: HOUR_HEIGHT * 2, 
    color: '#c94c4c',
    status: 'Agendado'
  },
  { 
    id: 4, 
    col: 5, 
    title: 'Ensaio Geral', 
    horario: '18:00 - 21:00', 
    horarioInicio: '18:00',
    local: 'Palco Principal',
    top: toTop(18), 
    height: HOUR_HEIGHT * 3, 
    color: '#4cc98e',
    status: 'Confirmado'
  },
]

const eventosHoje = computed(() => {
  return eventosCalendario.filter(e => e.col === 3)
})

const currentTimePosition = computed(() => {
  const now = new Date()
  const hours = now.getHours()
  const minutes = now.getMinutes()
  if (hours < 10 || hours > 21) return -100
  return (hours - 10) * HOUR_HEIGHT + (minutes / 60) * HOUR_HEIGHT
})

const semanaAnterior = () => {
  console.log('Semana anterior')
}

const proximaSemana = () => {
  console.log('Próxima semana')
}

const abrirEvento = (ev: any) => {
  console.log('Abrir evento:', ev)
}
</script>

<style scoped>
/* Estrutura Base */
.admin-layout { 
  display: flex; 
  min-height: 100vh; 
  background: #0a0e17; 
  color: #fff; 
  font-family: 'Plus Jakarta Sans', sans-serif; 
}

/* Sidebar */
.sidebar { 
  width: 260px; 
  background: linear-gradient(180deg, #111827 0%, #0d121c 100%);
  border-right: 1px solid rgba(201, 168, 76, 0.1);
  position: fixed; 
  height: 100vh; 
  z-index: 100; 
  display: flex;
  flex-direction: column;
}

.sidebar-header {
  padding: 28px 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.brand { 
  display: flex; 
  align-items: center; 
  gap: 10px; 
  text-decoration: none; 
  color: white; 
  font-weight: 700; 
  font-size: 1.2rem;
}

.logo-box {
  width: 40px;
  height: 40px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.2);
}

.brand .highlight {
  color: #c9a84c;
  font-size: 0.8rem;
  text-transform: uppercase;
  margin-left: 2px;
}

.sidebar-nav { 
  padding: 24px 16px; 
  display: flex; 
  flex-direction: column; 
  gap: 6px; 
  flex: 1;
}

.nav-item { 
  display: flex; 
  align-items: center; 
  gap: 12px; 
  padding: 12px 16px; 
  border-radius: 12px; 
  color: #8e9aaf; 
  text-decoration: none; 
  font-size: 0.9rem; 
  font-weight: 500;
  transition: all 0.3s ease;
}

.nav-item:hover { 
  background: rgba(201, 168, 76, 0.08); 
  color: #c9a84c; 
  transform: translateX(4px);
}

.nav-item.active { 
  background: linear-gradient(90deg, rgba(201, 168, 76, 0.15), rgba(201, 168, 76, 0.05));
  color: #c9a84c; 
  border-left: 3px solid #c9a84c;
}

.nav-spacer {
  flex: 1;
}

.logout { 
  margin-top: auto; 
  color: #ef4444; 
  background: none; 
  border: none; 
  width: 100%; 
  cursor: pointer; 
  text-align: left; 
}

.logout:hover {
  background: rgba(239, 68, 68, 0.08);
  color: #ef4444;
}

/* Layout Central */
.main-wrapper { 
  flex: 1; 
  margin-left: 260px; 
  display: flex; 
  flex-direction: column; 
  align-items: center; 
  width: calc(100% - 260px); 
}

.content-container { 
  width: 100%; 
  max-width: 1200px; 
  padding: 32px 40px; 
}

/* Breadcrumb */
.breadcrumb {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 20px;
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

/* Header */
.content-header { 
  display: flex; 
  justify-content: space-between; 
  align-items: center; 
  margin-bottom: 24px; 
  flex-wrap: wrap;
  gap: 20px;
}

.header-titles h1 { 
  font-family: 'Bebas Neue', sans-serif; 
  font-size: 2rem; 
  letter-spacing: 1px; 
  margin-bottom: 4px; 
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.header-titles p { 
  color: #8e9aaf; 
  font-size: 0.85rem; 
}

.header-actions { 
  display: flex; 
  align-items: center; 
  gap: 16px; 
  flex-wrap: wrap;
}

.date-navigator { 
  background: #1a2233; 
  padding: 8px 12px; 
  border-radius: 12px; 
  display: flex; 
  align-items: center; 
  gap: 12px; 
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.nav-btn { 
  background: none; 
  border: none; 
  color: #c9a84c; 
  cursor: pointer; 
  display: flex;
  align-items: center;
  justify-content: center;
  width: 28px;
  height: 28px;
  border-radius: 6px;
  transition: all 0.2s;
}

.nav-btn:hover {
  background: rgba(201, 168, 76, 0.1);
}

.current-date {
  color: #e0e0e0;
  font-weight: 500;
  font-size: 0.9rem;
}

.view-options {
  display: flex;
  gap: 4px;
  background: #1a2233;
  padding: 4px;
  border-radius: 10px;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.view-btn {
  padding: 6px 14px;
  background: transparent;
  border: none;
  color: #8e9aaf;
  font-size: 0.8rem;
  font-weight: 600;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.2s;
}

.view-btn.active {
  background: #c9a84c;
  color: #0a0e17;
}

.btn-add { 
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17; 
  border: none; 
  padding: 10px 20px; 
  border-radius: 10px; 
  font-weight: 700; 
  cursor: pointer; 
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 0.85rem;
  transition: all 0.3s ease;
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.15);
}

.btn-add:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(201, 168, 76, 0.25);
}

/* Legenda */
.legend-bar {
  display: flex;
  align-items: center;
  gap: 24px;
  margin-bottom: 20px;
  padding: 12px 16px;
  background: rgba(255, 255, 255, 0.02);
  border-radius: 12px;
  border: 1px solid rgba(255, 255, 255, 0.03);
}

.legend-label {
  color: #6b7280;
  font-size: 0.75rem;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  font-weight: 600;
}

.legend-items {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}

.legend-item {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #b0b8c5;
  font-size: 0.8rem;
}

.color-dot {
  width: 10px;
  height: 10px;
  border-radius: 3px;
}

/* Calendário */
.calendar-card { 
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px; 
  border: 1px solid rgba(255, 255, 255, 0.05); 
  overflow: hidden; 
  width: 100%;
  margin-bottom: 24px;
}

.calendar-scroll-area { 
  overflow-x: auto; 
}

.calendar-scroll-area::-webkit-scrollbar {
  height: 8px;
}

.calendar-scroll-area::-webkit-scrollbar-track {
  background: #1a2233;
  border-radius: 4px;
}

.calendar-scroll-area::-webkit-scrollbar-thumb {
  background: #2a3446;
  border-radius: 4px;
}

.calendar-grid-header { 
  display: flex; 
  border-bottom: 1px solid rgba(255, 255, 255, 0.05); 
  background: #1a2233; 
}

.time-col-header { 
  width: 80px; 
  flex-shrink: 0; 
}

.days-columns { 
  display: grid; 
  grid-template-columns: repeat(7, minmax(140px, 1fr)); 
  flex: 1; 
}

.day-label { 
  padding: 14px; 
  text-align: center; 
  border-left: 1px solid rgba(255, 255, 255, 0.05); 
}

.day-name { 
  display: block; 
  font-size: 0.75rem; 
  color: #8e9aaf; 
  text-transform: uppercase; 
  margin-bottom: 6px; 
  font-weight: 600;
  letter-spacing: 0.5px;
}

.day-number { 
  font-size: 1.3rem; 
  font-weight: 800; 
}

.is-today { 
  background: rgba(201, 168, 76, 0.08); 
}

.is-today .day-number { 
  color: #c9a84c; 
}

.calendar-grid-body { 
  display: flex; 
  position: relative; 
  height: calc(12 * 80px); 
}

.time-gutter { 
  width: 80px; 
  flex-shrink: 0; 
  border-right: 1px solid rgba(255, 255, 255, 0.03); 
}

.time-marker { 
  height: 80px; 
  font-size: 0.75rem; 
  color: #6b7280; 
  text-align: right; 
  padding: 8px 12px 0 0; 
  font-weight: 500;
  border-bottom: 1px solid rgba(255, 255, 255, 0.02);
}

.time-text {
  display: block;
}

.event-columns { 
  display: grid; 
  grid-template-columns: repeat(7, minmax(140px, 1fr)); 
  flex: 1; 
  position: relative; 
}

.event-column { 
  border-left: 1px solid rgba(255, 255, 255, 0.02); 
  position: relative; 
}

.hour-row { 
  height: 80px; 
  border-bottom: 1px solid rgba(255, 255, 255, 0.02); 
}

/* Linha do horário atual */
.current-time-line {
  position: absolute;
  left: 0;
  right: 0;
  height: 2px;
  z-index: 15;
  pointer-events: none;
}

.time-indicator {
  position: relative;
  width: 100%;
  height: 2px;
  background: #ef4444;
}

.time-indicator::before {
  content: '';
  position: absolute;
  left: -4px;
  top: -4px;
  width: 10px;
  height: 10px;
  background: #ef4444;
  border-radius: 50%;
}

.event-block {
  position: absolute; 
  left: 6px; 
  right: 6px; 
  border-left: 4px solid;
  border-radius: 8px; 
  z-index: 10; 
  cursor: pointer; 
  transition: all 0.2s ease;
}

.event-block:hover { 
  transform: scale(1.02); 
  z-index: 20; 
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.4); 
}

.event-inner { 
  height: 100%; 
  padding: 12px; 
  display: flex; 
  flex-direction: column; 
  border-radius: 6px;
}

.ev-title { 
  font-size: 0.85rem; 
  font-weight: 700; 
  margin-bottom: 4px; 
  color: #fff;
}

.ev-time { 
  font-size: 0.7rem; 
  opacity: 0.8; 
}

.ev-local {
  font-size: 0.65rem;
  opacity: 0.6;
  margin-top: 4px;
}

/* Resumo do Dia */
.day-summary {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 16px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  padding: 20px 24px;
}

.summary-header {
  margin-bottom: 16px;
}

.summary-header h3 {
  font-size: 0.9rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 1px;
  display: flex;
  align-items: center;
  gap: 8px;
}

.summary-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.summary-item {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 12px 0;
  border-bottom: 1px solid rgba(255, 255, 255, 0.03);
}

.summary-item:last-child {
  border-bottom: none;
}

.summary-time {
  width: 60px;
  font-size: 0.85rem;
  font-weight: 700;
  color: #c9a84c;
}

.summary-info {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.summary-title {
  font-size: 0.9rem;
  font-weight: 600;
  color: #e0e0e0;
}

.summary-local {
  font-size: 0.75rem;
  color: #6b7280;
}

.summary-status {
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 700;
}

/* Menu Toggle Mobile */
.menu-toggle {
  display: none;
  position: fixed;
  bottom: 20px;
  right: 20px;
  width: 50px;
  height: 50px;
  border-radius: 14px;
  background: #c9a84c;
  color: #0a0e17;
  border: none;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  z-index: 150;
  box-shadow: 0 8px 20px rgba(201, 168, 76, 0.3);
}

.sidebar-overlay {
  display: none;
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.7);
  backdrop-filter: blur(4px);
  z-index: 90;
}

/* Responsividade */
@media (max-width: 1024px) {
  .sidebar { 
    transform: translateX(-100%); 
    transition: transform 0.3s ease; 
  }
  
  .admin-layout.sidebar-open .sidebar {
    transform: translateX(0);
  }
  
  .admin-layout.sidebar-open .sidebar-overlay {
    display: block;
  }
  
  .main-wrapper { 
    margin-left: 0; 
    width: 100%; 
  }
  
  .menu-toggle {
    display: flex;
  }
  
  .content-container {
    padding: 24px 20px;
  }
  
  .content-header {
    flex-direction: column;
    align-items: flex-start;
  }
  
  .header-actions {
    width: 100%;
    justify-content: space-between;
  }
}

@media (max-width: 600px) {
  .header-titles h1 {
    font-size: 1.6rem;
  }
  
  .header-actions {
    flex-direction: column;
    align-items: stretch;
  }
  
  .date-navigator {
    justify-content: space-between;
  }
  
  .view-options {
    justify-content: center;
  }
  
  .btn-add {
    justify-content: center;
  }
  
  .legend-bar {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }
  
  .summary-item {
    flex-wrap: wrap;
    gap: 8px;
  }
  
  .summary-time {
    width: 100%;
  }
}
</style>