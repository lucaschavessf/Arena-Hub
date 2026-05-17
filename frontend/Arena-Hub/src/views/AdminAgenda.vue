<template>
  <div class="admin-layout" :class="{ 'sidebar-open': isSidebarOpen }">
    <div class="sidebar-overlay" @click="isSidebarOpen = false"></div>

    <aside class="sidebar">
      <div class="sidebar-header">
        <router-link to="/" class="brand">
          <div class="logo-box">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <path d="M2 9V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v4" />
              <path d="M2 15v4a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-4" />
              <path d="M2 9a3 3 0 0 1 0 6" />
              <path d="M22 9a3 3 0 0 0 0 6" />
            </svg>
          </div>
          <span>Arena<span class="highlight">Hub</span></span>
        </router-link>
      </div>

      <nav class="sidebar-nav">
        <router-link to="/admin" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <rect x="3" y="3" width="7" height="7" />
            <rect x="14" y="3" width="7" height="7" />
            <rect x="3" y="14" width="7" height="7" />
            <rect x="14" y="14" width="7" height="7" />
          </svg>
          <span>Dashboard</span>
        </router-link>

        <router-link to="/admin/agenda" class="nav-item active">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <rect x="3" y="4" width="18" height="18" rx="2" />
            <path d="M16 2v4M8 2v4M3 10h18" />
          </svg>
          <span>Agenda</span>
        </router-link>

        <router-link to="/admin/produtores" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <circle cx="12" cy="8" r="4" />
            <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7" />
          </svg>
          <span>Produtores</span>
        </router-link>

        <router-link to="/admin/solicitacoes" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z" />
            <polyline points="14 2 14 8 20 8" />
            <path d="M9 15l2 2 4-4" />
          </svg>
          <span>Solicitações</span>
        </router-link>

        <router-link to="/admin/eventos" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <rect x="3" y="4" width="18" height="18" rx="2" ry="2" />
            <line x1="16" y1="2" x2="16" y2="6" />
            <line x1="8" y1="2" x2="8" y2="6" />
            <line x1="3" y1="10" x2="21" y2="10" />
          </svg>
          <span>Eventos</span>
        </router-link>

        <router-link to="/admin/precos" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <circle cx="12" cy="12" r="10" />
            <path d="M12 6v6l4 2" />
          </svg>
          <span>Preços</span>
        </router-link>

        <div class="nav-spacer"></div>

        <router-link to="/admin/perfil" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <circle cx="12" cy="8" r="4" />
            <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7" />
          </svg>
          <span>Perfil</span>
        </router-link>

        <router-link to="/admin/criar-conta" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2" />
            <circle cx="9" cy="7" r="4" />
            <line x1="19" y1="8" x2="19" y2="14" />
            <line x1="22" y1="11" x2="16" y2="11" />
          </svg>
          <span>Criar Admin</span>
        </router-link>

        <button @click="$router.push('/')" class="nav-item logout">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4" />
            <polyline points="16 17 21 12 16 7" />
            <line x1="21" y1="12" x2="9" y2="12" />
          </svg>
          <span>Sair do Admin</span>
        </button>
      </nav>
    </aside>

    <div class="main-wrapper">
      <AppNavbar :isAdmin="true" />

      <main class="content-container">
        <div class="breadcrumb">
          <router-link to="/admin" class="breadcrumb-link">Admin</router-link>
          <svg
            width="14"
            height="14"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2.5"
          >
            <polyline points="9 18 15 12 9 6" />
          </svg>
          <span class="breadcrumb-current">Agenda</span>
        </div>

        <header class="content-header">
          <div class="header-titles">
            <h1>Cronograma da Arena</h1>
            <p>Eventos aprovados e confirmados na agenda</p>
          </div>
        </header>

        <div class="legend-bar">
          <span class="legend-label">Tipos de Evento:</span>
          <div class="legend-items">
            <div class="legend-item">
              <span class="color-dot" style="background: #c9a84c"></span>
              <span>Produção</span>
            </div>
            <div class="legend-item">
              <span class="color-dot" style="background: #4c87c9"></span>
              <span>Show</span>
            </div>
            <div class="legend-item">
              <span class="color-dot" style="background: #c94c4c"></span>
              <span>Reunião</span>
            </div>
            <div class="legend-item">
              <span class="color-dot" style="background: #4cc98e"></span>
              <span>Ensaio</span>
            </div>
          </div>
        </div>

        <div v-if="carregando" class="loading-container">
          <div class="spinner"></div>
          <p>Carregando eventos aprovados...</p>
        </div>

        <div v-else class="calendar-card">
          <FullCalendar ref="fullCalendarRef" :options="calendarOptions" :key="calendarKey" />
        </div>

        <div class="day-summary">
          <div class="summary-header">
            <h3>
              <svg
                width="18"
                height="18"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <circle cx="12" cy="12" r="10" />
                <polyline points="12 6 12 12 16 14" />
              </svg>
              Hoje na Arena
            </h3>
          </div>
          <div class="summary-list">
            <div v-if="eventosHoje.length === 0" class="empty-summary">
              Nenhum evento agendado para hoje
            </div>
            <div v-for="ev in eventosHoje" :key="ev.id" class="summary-item">
              <div class="summary-time">{{ formatHorario(ev.start) }}</div>
              <div class="summary-info">
                <span class="summary-title">{{ ev.title }}</span>
                <span class="summary-local">{{
                  ev.extendedProps?.local || 'Local não definido'
                }}</span>
              </div>
              <div
                class="summary-status"
                :style="{
                  background: getCorEvento(ev.extendedProps?.tipo) + '20',
                  color: getCorEvento(ev.extendedProps?.tipo),
                }"
              >
                {{ ev.extendedProps?.tipo || 'Evento' }}
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>

    <button class="menu-toggle" @click="isSidebarOpen = !isSidebarOpen">
      <svg
        width="24"
        height="24"
        viewBox="0 0 24 24"
        fill="none"
        stroke="currentColor"
        stroke-width="2.5"
      >
        <line x1="3" y1="12" x2="21" y2="12" />
        <line x1="3" y1="6" x2="21" y2="6" />
        <line x1="3" y1="18" x2="21" y2="18" />
      </svg>
    </button>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, watch } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import ptBrLocale from '@fullcalendar/core/locales/pt-br'
import api from '../services/api'

const isSidebarOpen = ref(false)
const fullCalendarRef = ref()
const carregando = ref(false)
const calendarKey = ref(0)

interface Evento {
  id: string
  title: string
  start: string
  end: string
  tipo: string
  local: string
  descricao: string
  color?: string
}

const eventos = ref<Evento[]>([])

function getCorEvento(tipo: string): string {
  const cores: Record<string, string> = {
    producao: '#c9a84c',
    show: '#4c87c9',
    reuniao: '#c94c4c',
    ensaio: '#4cc98e',
  }
  return cores[tipo] || '#6b7280'
}

function mapearCategoriaParaTipo(categoria: string): string {
  const map: Record<string, string> = {
    Show: 'show',
    Esportes: 'show',
    Corporativo: 'reuniao',
    Teatro: 'show',
    Comédia: 'show',
    Jogo: 'show',
  }
  return map[categoria] || 'show'
}

function formatHorario(dataStr: string): string {
  if (!dataStr) return ''
  const data = new Date(dataStr)
  return data.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' })
}

function converterEventoParaCalendario(evento: any): Evento | null {
  try {
    const dataInicio = new Date(evento.dataInicio)
    const dataFim = new Date(evento.dataFim)

    if (isNaN(dataInicio.getTime()) || isNaN(dataFim.getTime())) {
      console.warn('Data inválida para evento:', evento)
      return null
    }

    const tipo = mapearCategoriaParaTipo(evento.categoriaNome)

    return {
      id: evento.id.toString(),
      title: evento.nome,
      start: dataInicio.toISOString(),
      end: dataFim.toISOString(),
      tipo: tipo,
      local: evento.espacoNome || 'Local não informado',
      descricao: evento.descricao || '',
      color: getCorEvento(tipo),
    }
  } catch (error) {
    console.error('Erro ao converter evento:', error)
    return null
  }
}

async function carregarEventosAprovados() {
  carregando.value = true
  try {
    const response = await api.get('/api/eventos')
    const todosEventos = Array.isArray(response.data) ? response.data : []

    console.log('Eventos recebidos da API:', todosEventos)

    const eventosAprovados = todosEventos.filter((e: any) => e.status === 'APROVADO')

    console.log('Eventos aprovados:', eventosAprovados)

    const eventosCalendario = eventosAprovados
      .map(converterEventoParaCalendario)
      .filter((e) => e !== null)

    eventos.value = eventosCalendario

    calendarKey.value++

    if (fullCalendarRef.value) {
      const calendarApi = fullCalendarRef.value.getApi()
      calendarApi.removeAllEvents()
      calendarApi.addEventSource(eventos.value)
      calendarApi.refetchEvents()
    }
  } catch (error) {
    console.error('Erro ao carregar eventos aprovados:', error)
    eventos.value = []
  } finally {
    carregando.value = false
  }
}

const calendarOptions = computed(() => ({
  plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
  initialView: 'timeGridWeek',
  locale: ptBrLocale,
  headerToolbar: {
    left: 'prev,next today',
    center: 'title',
    right: 'timeGridWeek,timeGridDay,dayGridMonth',
  },
  slotMinTime: '08:00:00',
  slotMaxTime: '23:00:00',
  allDaySlot: false,
  slotDuration: '01:00:00',
  slotLabelInterval: '01:00',
  slotLabelFormat: {
    hour: '2-digit',
    minute: '2-digit',
    hour12: false,
  },
  events: eventos.value,
  editable: false,
  selectable: false,
  dayMaxEvents: true,
  weekends: true,
  nowIndicator: true,
  height: 'auto',
  contentHeight: 'auto',
  themeSystem: 'standard',
}))

const eventosHoje = computed(() => {
  const hoje = new Date()
  hoje.setHours(0, 0, 0, 0)
  const amanha = new Date(hoje)
  amanha.setDate(amanha.getDate() + 1)

  return eventos.value.filter((evento) => {
    const dataEvento = new Date(evento.start)
    return dataEvento >= hoje && dataEvento < amanha
  })
})

watch(
  eventos,
  (novosEventos) => {
    if (fullCalendarRef.value) {
      const calendarApi = fullCalendarRef.value.getApi()
      calendarApi.removeAllEvents()
      calendarApi.addEventSource(novosEventos)
      calendarApi.refetchEvents()
    }
  },
  { deep: true },
)

onMounted(async () => {
  await carregarEventosAprovados()
})
</script>

<style scoped>
.admin-layout {
  display: flex;
  min-height: 100vh;
  background: #0a0e17;
  color: #fff;
  font-family: 'Plus Jakarta Sans', sans-serif;
}

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

.loading-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 60px;
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
}

.spinner {
  width: 40px;
  height: 40px;
  border: 3px solid rgba(201, 168, 76, 0.3);
  border-top-color: #c9a84c;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin-bottom: 16px;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}

.calendar-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  overflow: hidden;
  width: 100%;
  margin-bottom: 24px;
  padding: 20px;
}

:deep(.fc) {
  --fc-border-color: rgba(255, 255, 255, 0.08);
  --fc-button-bg-color: transparent;
  --fc-button-border-color: rgba(255, 255, 255, 0.1);
  --fc-button-hover-bg-color: rgba(201, 168, 76, 0.1);
  --fc-button-hover-border-color: rgba(201, 168, 76, 0.3);
  --fc-button-active-bg-color: #c9a84c;
  --fc-button-active-border-color: #c9a84c;
  --fc-button-text-color: #e0e0e0;
  --fc-event-bg-color: rgba(201, 168, 76, 0.15);
  --fc-event-border-color: #c9a84c;
  --fc-event-text-color: #fff;
  --fc-today-bg-color: rgba(201, 168, 76, 0.05);
  --fc-page-bg-color: transparent;
  --fc-neutral-bg-color: transparent;
}

:deep(.fc) {
  background: transparent;
  color: #e0e0e0;
}

:deep(.fc-toolbar-title) {
  color: #fff;
  font-family: 'Bebas Neue', sans-serif;
  font-size: 1.5rem;
  letter-spacing: 1px;
}

:deep(.fc-button) {
  text-transform: capitalize;
  font-size: 0.85rem;
  padding: 6px 12px;
  border-radius: 8px;
}

:deep(.fc-day-today) {
  background: rgba(201, 168, 76, 0.05) !important;
}

:deep(.fc-day-header) {
  padding: 12px;
  color: #8e9aaf;
  font-weight: 600;
  text-transform: uppercase;
  font-size: 0.75rem;
}

:deep(.fc-daygrid-day-number) {
  color: #e0e0e0;
}

:deep(.fc-timegrid-slot-label) {
  color: #6b7280;
  font-size: 0.75rem;
}

:deep(.fc-event) {
  border-radius: 8px;
  border-left: 4px solid;
  cursor: pointer;
  transition: transform 0.2s ease;
}

:deep(.fc-event:hover) {
  transform: scale(1.01);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
}

:deep(.fc-event-title) {
  font-weight: 600;
  font-size: 0.85rem;
  padding: 2px 4px;
}

:deep(.fc-event-time) {
  font-size: 0.7rem;
  opacity: 0.8;
}

:deep(.fc-timegrid-now-indicator-line) {
  border-color: #ef4444;
}

:deep(.fc-timegrid-now-indicator-arrow) {
  border-color: #ef4444;
  color: #ef4444;
}

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

.empty-summary {
  color: #6b7280;
  text-align: center;
  padding: 20px;
  font-size: 0.9rem;
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
  text-transform: capitalize;
}

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
}

@media (max-width: 600px) {
  .header-titles h1 {
    font-size: 1.6rem;
  }

  .legend-bar {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }

  .calendar-card {
    padding: 12px;
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
