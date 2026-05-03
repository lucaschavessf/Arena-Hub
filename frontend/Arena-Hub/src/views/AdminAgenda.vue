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
            <p>Gestão de ocupação e horários técnicos</p>
          </div>

          <div class="header-actions">
            <button class="btn-add" @click="abrirModalNovoEvento">
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

        <!-- FullCalendar Container -->
        <div class="calendar-card">
          <FullCalendar
            ref="fullCalendarRef"
            :options="calendarOptions"
          />
        </div>

        <!-- Modal de Evento -->
        <div v-if="modalAberto" class="modal-overlay" @click="fecharModal">
          <div class="modal-content" @click.stop>
            <div class="modal-header">
              <h3>{{ eventoTemp.id ? 'Editar Evento' : 'Novo Evento' }}</h3>
              <button class="modal-close" @click="fecharModal">&times;</button>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label>Título</label>
                <input v-model="eventoTemp.title" type="text" class="form-input" placeholder="Ex: Show Banda XYZ" />
              </div>
              <div class="form-group">
                <label>Tipo de Evento</label>
                <select v-model="eventoTemp.tipo" class="form-input">
                  <option value="producao">Produção</option>
                  <option value="show">Show</option>
                  <option value="reuniao">Reunião</option>
                  <option value="ensaio">Ensaio</option>
                </select>
              </div>
              <div class="form-group">
                <label>Data e Horário</label>
                <div class="datetime-group">
                  <input type="date" v-model="eventoTemp.startDate" class="form-input" />
                  <input type="time" v-model="eventoTemp.startTime" class="form-input" />
                  <span>até</span>
                  <input type="time" v-model="eventoTemp.endTime" class="form-input" />
                </div>
              </div>
              <div class="form-group">
                <label>Local</label>
                <input v-model="eventoTemp.local" type="text" class="form-input" placeholder="Palco Principal, Sala de Produção..." />
              </div>
              <div class="form-group">
                <label>Descrição</label>
                <textarea v-model="eventoTemp.descricao" class="form-textarea" rows="3" placeholder="Detalhes do evento..."></textarea>
              </div>
            </div>
            <div class="modal-footer">
              <button class="btn-cancel" @click="fecharModal">Cancelar</button>
              <button class="btn-save" @click="salvarEvento">Salvar</button>
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
                <span class="summary-local">{{ ev.extendedProps?.local || 'Local não definido' }}</span>
              </div>
              <div class="summary-status" :style="{ background: getCorEvento(ev.extendedProps?.tipo) + '20', color: getCorEvento(ev.extendedProps?.tipo) }">
                {{ ev.extendedProps?.tipo || 'Evento' }}
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
import { ref, computed, onMounted } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import ptBrLocale from '@fullcalendar/core/locales/pt-br'

const isSidebarOpen = ref(false)
const fullCalendarRef = ref()
const modalAberto = ref(false)

interface Evento {
  id: string
  title: string
  start: string
  end: string
  tipo: string
  local: string
  descricao: string
  color?: string
  backgroundColor?: string
  borderColor?: string
}

const eventoTemp = ref({
  id: '',
  title: '',
  tipo: 'show',
  startDate: '',
  startTime: '10:00',
  endTime: '18:00',
  local: '',
  descricao: ''
})

// Eventos iniciais
const eventos = ref<Evento[]>([
  {
    id: '1',
    title: 'Check-in Staff',
    start: '2026-04-15T10:00:00',
    end: '2026-04-15T12:30:00',
    tipo: 'producao',
    local: 'Sala de Produção',
    descricao: 'Check-in da equipe técnica e produção',
    color: '#c9a84c'
  },
  {
    id: '2',
    title: 'Show: Rock Nacional',
    start: '2026-04-16T14:00:00',
    end: '2026-04-16T17:00:00',
    tipo: 'show',
    local: 'Palco Principal',
    descricao: 'Apresentação da banda de rock nacional',
    color: '#4c87c9'
  },
  {
    id: '3',
    title: 'Reunião Técnica',
    start: '2026-04-17T11:00:00',
    end: '2026-04-17T13:00:00',
    tipo: 'reuniao',
    local: 'Sala de Reuniões',
    descricao: 'Alinhamento técnico para evento do final de semana',
    color: '#c94c4c'
  },
  {
    id: '4',
    title: 'Ensaio Geral',
    start: '2026-04-18T18:00:00',
    end: '2026-04-18T21:00:00',
    tipo: 'ensaio',
    local: 'Palco Principal',
    descricao: 'Ensaio geral para apresentação',
    color: '#4cc98e'
  }
])

// Mapeamento de cores por tipo
const getCorEvento = (tipo: string) => {
  const cores = {
    producao: '#c9a84c',
    show: '#4c87c9',
    reuniao: '#c94c4c',
    ensaio: '#4cc98e'
  }
  return cores[tipo as keyof typeof cores] || '#6b7280'
}

// Formatar horário
const formatHorario = (dataStr: string) => {
  const data = new Date(dataStr)
  return data.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' })
}

// Configuração do FullCalendar
const calendarOptions = ref({
  plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
  initialView: 'timeGridWeek',
  locale: ptBrLocale,
  headerToolbar: {
    left: 'prev,next today',
    center: 'title',
    right: 'timeGridWeek,timeGridDay,dayGridMonth'
  },
  slotMinTime: '08:00:00',
  slotMaxTime: '23:00:00',
  allDaySlot: false,
  slotDuration: '01:00:00',
  slotLabelInterval: '01:00',
  slotLabelFormat: {
    hour: '2-digit',
    minute: '2-digit',
    hour12: false
  },
  events: eventos.value,
  editable: true,
  selectable: true,
  selectMirror: true,
  dayMaxEvents: true,
  weekends: true,
  nowIndicator: true,
  eventClick: (info: any) => {
    info.jsEvent.preventDefault()
    abrirModalEditar(info.event)
  },
  select: (info: any) => {
    abrirModalNovoComData(info)
  },
  eventDrop: (info: any) => {
    const evento = eventos.value.find(e => e.id === info.event.id)
    if (evento) {
      evento.start = info.event.startStr
      evento.end = info.event.endStr
      console.log('Evento movido:', evento)
    }
  },
  eventResize: (info: any) => {
    const evento = eventos.value.find(e => e.id === info.event.id)
    if (evento) {
      evento.end = info.event.endStr
      console.log('Evento redimensionado:', evento)
    }
  },
  height: 'auto',
  contentHeight: 'auto',
  themeSystem: 'standard'
})

// Eventos de hoje
const eventosHoje = computed(() => {
  const hoje = new Date().toISOString().split('T')[0]
  return eventos.value.filter(evento => evento.start.startsWith(hoje))
})

const abrirModalNovoEvento = () => {
  const hoje = new Date().toISOString().split('T')[0]
  eventoTemp.value = {
    id: '',
    title: '',
    tipo: 'show',
    startDate: hoje,
    startTime: '10:00',
    endTime: '18:00',
    local: '',
    descricao: ''
  }
  modalAberto.value = true
}

const abrirModalNovoComData = (info: any) => {
  const start = new Date(info.start)
  const end = new Date(info.end)
  
  eventoTemp.value = {
    id: '',
    title: '',
    tipo: 'show',
    startDate: start.toISOString().split('T')[0],
    startTime: start.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }),
    endTime: end.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }),
    local: '',
    descricao: ''
  }
  modalAberto.value = true
}

const abrirModalEditar = (event: any) => {
  const start = new Date(event.start)
  const end = new Date(event.end)
  
  eventoTemp.value = {
    id: event.id,
    title: event.title,
    tipo: event.extendedProps.tipo || 'show',
    startDate: start.toISOString().split('T')[0],
    startTime: start.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }),
    endTime: end.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }),
    local: event.extendedProps.local || '',
    descricao: event.extendedProps.descricao || ''
  }
  modalAberto.value = true
}

const salvarEvento = () => {
  const startDate = `${eventoTemp.value.startDate}T${eventoTemp.value.startTime}:00`
  const endDate = `${eventoTemp.value.startDate}T${eventoTemp.value.endTime}:00`
  
  const eventoData = {
    id: eventoTemp.value.id || Date.now().toString(),
    title: eventoTemp.value.title,
    start: startDate,
    end: endDate,
    tipo: eventoTemp.value.tipo,
    local: eventoTemp.value.local,
    descricao: eventoTemp.value.descricao,
    color: getCorEvento(eventoTemp.value.tipo)
  }
  
  if (eventoTemp.value.id) {
    // Editar evento existente
    const index = eventos.value.findIndex(e => e.id === eventoTemp.value.id)
    if (index !== -1) {
      eventos.value[index] = eventoData as Evento
    }
  } else {
    // Adicionar novo evento
    eventos.value.push(eventoData as Evento)
  }
  
  // Atualizar o calendário
  if (fullCalendarRef.value) {
    const calendarApi = fullCalendarRef.value.getApi()
    calendarApi.refetchEvents()
  }
  
  fecharModal()
}

const fecharModal = () => {
  modalAberto.value = false
}

onMounted(() => {
  // Configurar data atual para exibição no resumo
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

.header-actions { 
  display: flex; 
  align-items: center; 
  gap: 16px; 
  flex-wrap: wrap;
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
  --fc-list-event-hover-bg-color: rgba(255, 255, 255, 0.05);
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
  transition: all 0.3s ease;
}

:deep(.fc-button-primary:not(:disabled):hover) {
  background: rgba(201, 168, 76, 0.15);
}

:deep(.fc-button-primary:focus) {
  box-shadow: none;
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

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.8);
  backdrop-filter: blur(8px);
  z-index: 200;
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  background: linear-gradient(135deg, #1a2233 0%, #121826 100%);
  border-radius: 20px;
  width: 90%;
  max-width: 500px;
  max-height: 90vh;
  overflow-y: auto;
  border: 1px solid rgba(255, 255, 255, 0.1);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.5);
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.modal-header h3 {
  font-size: 1.2rem;
  color: #c9a84c;
  margin: 0;
}

.modal-close {
  background: none;
  border: none;
  color: #8e9aaf;
  font-size: 28px;
  cursor: pointer;
  transition: color 0.2s;
}

.modal-close:hover {
  color: #ef4444;
}

.modal-body {
  padding: 24px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #8e9aaf;
  font-size: 0.85rem;
  font-weight: 600;
}

.form-input,
.form-textarea {
  width: 100%;
  padding: 10px 12px;
  background: #0f131e;
  border: 1px solid rgba(255, 255, 255, 0.1);
  border-radius: 8px;
  color: #e0e0e0;
  font-size: 0.9rem;
  transition: all 0.2s;
}

.form-input:focus,
.form-textarea:focus {
  outline: none;
  border-color: #c9a84c;
}

.datetime-group {
  display: flex;
  gap: 8px;
  align-items: center;
  flex-wrap: wrap;
}

.datetime-group input {
  flex: 1;
  min-width: 100px;
}

.datetime-group span {
  color: #8e9aaf;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  padding: 16px 24px 24px;
}

.btn-cancel,
.btn-save {
  padding: 10px 20px;
  border-radius: 10px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.btn-cancel {
  background: rgba(239, 68, 68, 0.1);
  border: 1px solid rgba(239, 68, 68, 0.3);
  color: #ef4444;
}

.btn-cancel:hover {
  background: rgba(239, 68, 68, 0.2);
}

.btn-save {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  color: #0a0e17;
}

.btn-save:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.3);
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
  
  .btn-add {
    justify-content: center;
  }
  
  .legend-bar {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }
  
  .calendar-card {
    padding: 12px;
  }
  
  .datetime-group {
    flex-direction: column;
    align-items: stretch;
  }
  
  .datetime-group span {
    text-align: center;
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