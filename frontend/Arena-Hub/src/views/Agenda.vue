<template>
  <div class="admin-layout" :class="{ 'sidebar-open': isSidebarOpen }">
    <div class="sidebar-overlay" @click="isSidebarOpen = false"></div>

    <aside class="sidebar">
      <div class="sidebar-header">
        <router-link to="/" class="brand">
          <div class="logo-box">A</div>
          <span>Arena<span>Hub</span></span>
        </router-link>
      </div>

      <nav class="sidebar-nav">
        <router-link to="/admin" class="nav-item">
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/><rect x="3" y="14" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/></svg>
          Dashboard
        </router-link>
        
        <router-link to="/admin/agenda" class="nav-item active">
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><line x1="16" y1="2" x2="16" y2="6"/><line x1="8" y1="2" x2="8" y2="6"/><line x1="3" y1="10" x2="21" y2="10"/></svg>
          Agenda
        </router-link>

        <div class="nav-spacer"></div>

        <button @click="$router.push('/')" class="nav-item logout">
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/></svg>
          Sair do Admin
        </button>
      </nav>
    </aside>

    <div class="main-wrapper">
      <main class="content-container">
        <header class="content-header">
          <div class="header-titles">
            <h1>Cronograma da Arena</h1>
            <p>Gestão de ocupação e horários técnicos</p>
          </div>

          <div class="header-actions">
            <div class="date-navigator">
              <button class="nav-btn">‹</button>
              <span class="current-date">12 - 18 de Abril</span>
              <button class="nav-btn">›</button>
            </div>
            <button class="btn-add">+ Novo Evento</button>
          </div>
        </header>

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
                <div v-for="h in horas" :key="h" class="time-marker">{{ h }}</div>
              </div>

              <div class="event-columns">
                <div v-for="col in 7" :key="col" class="event-column">
                  <div v-for="h in horas" :key="h" class="hour-row"></div>
                  
                  <div
                    v-for="ev in eventosCalendario.filter(e => e.col === col - 1)"
                    :key="ev.id"
                    class="event-block"
                    :style="{ top: ev.top + 'px', height: ev.height + 'px', borderLeftColor: ev.color }"
                  >
                    <div class="event-inner" :style="{ background: ev.color + '15' }">
                      <span class="ev-title">{{ ev.title }}</span>
                      <span class="ev-time">{{ ev.horario }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>

    <button class="menu-toggle" @click="isSidebarOpen = !isSidebarOpen">☰</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const isSidebarOpen = ref(false)
const diasSemana = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb']
const horas = ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00']

const HOUR_HEIGHT = 80

function toTop(horaDecimal) {
  return (horaDecimal - 10) * HOUR_HEIGHT
}

const eventosCalendario = [
  { id: 1, col: 1, title: 'Check-in Staff', horario: '10:00 - 12:30', top: toTop(10), height: HOUR_HEIGHT * 2.5, color: '#c9a84c' },
  { id: 2, col: 2, title: 'Show: Rock Nacional', horario: '14:00 - 17:00', top: toTop(14), height: HOUR_HEIGHT * 3, color: '#4c87c9' },
  { id: 3, col: 4, title: 'Reunião Técnica', horario: '11:00 - 13:00', top: toTop(11), height: HOUR_HEIGHT * 2, color: '#c94c4c' },
  { id: 4, col: 5, title: 'Ensaio Geral', horario: '18:00 - 21:00', top: toTop(18), height: HOUR_HEIGHT * 3, color: '#4cc98e' },
]
</script>

<style scoped>
/* ESTRUTURA BASE */
.admin-layout { display: flex; min-height: 100vh; background: #0a0e17; width: 100%; }

/* SIDEBAR FIXA */
.sidebar {
  width: 260px; background: #111827; border-right: 1px solid rgba(255,255,255,0.05);
  position: fixed; height: 100vh; left: 0; z-index: 100;
}
.sidebar-header { padding: 32px 24px; }
.brand { display: flex; align-items: center; gap: 12px; font-weight: 800; color: white; text-decoration: none; }
.logo-box { background: #c9a84c; color: #0a0e17; width: 32px; height: 32px; border-radius: 8px; display: grid; place-items: center; }
.brand span span { color: #c9a84c; }

.sidebar-nav { flex: 1; padding: 0 16px; display: flex; flex-direction: column; gap: 4px; }
.nav-item {
  display: flex; align-items: center; gap: 12px; padding: 12px 16px;
  border-radius: 10px; color: #9ca3af; text-decoration: none; font-size: 0.95rem;
}
.nav-item.active { background: rgba(201, 168, 76, 0.1); color: #c9a84c; font-weight: 600; }
.nav-spacer { flex: 1; }
.logout { margin-bottom: 24px; color: #ef4444; background: none; border: none; cursor: pointer; }

/* CENTRALIZAÇÃO DO CONTEÚDO NO PC */
.main-wrapper {
  flex: 1;
  margin-left: 260px; /* Compensa a sidebar */
  display: flex;
  flex-direction: column;
  align-items: center; /* Centraliza horizontalmente */
  width: calc(100% - 260px);
}

.content-container {
  width: 100%;
  max-width: 1200px; /* Limite de largura para não esticar no PC */
  padding: 40px 30px;
}

.content-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 40px; width: 100%; }
.header-titles h1 { font-size: 1.8rem; font-weight: 800; margin-bottom: 4px; }
.header-titles p { color: #9ca3af; }

.header-actions { display: flex; align-items: center; gap: 20px; }
.date-navigator { background: #1f2937; padding: 8px 16px; border-radius: 12px; display: flex; align-items: center; gap: 16px; }
.nav-btn { background: none; border: none; color: #c9a84c; cursor: pointer; font-size: 1.2rem; }
.btn-add { background: #c9a84c; color: #0a0e17; border: none; padding: 10px 20px; border-radius: 10px; font-weight: 700; cursor: pointer; }

/* CALENDÁRIO */
.calendar-card { 
  background: #111827; 
  border-radius: 20px; 
  border: 1px solid rgba(255,255,255,0.05); 
  overflow: hidden; 
  width: 100%;
}
.calendar-scroll-area { overflow-x: auto; }

.calendar-grid-header { display: flex; border-bottom: 1px solid rgba(255,255,255,0.05); background: #1f2937; }
.time-col-header { width: 80px; flex-shrink: 0; }
.days-columns { display: grid; grid-template-columns: repeat(7, minmax(150px, 1fr)); flex: 1; }
.day-label { padding: 16px; text-align: center; border-left: 1px solid rgba(255,255,255,0.05); }
.day-name { display: block; font-size: 0.75rem; color: #9ca3af; text-transform: uppercase; margin-bottom: 4px; }
.day-number { font-size: 1.2rem; font-weight: 800; }
.is-today { background: rgba(201, 168, 76, 0.1); }
.is-today .day-number { color: #c9a84c; }

.calendar-grid-body { display: flex; position: relative; height: calc(12 * 80px); }
.time-gutter { width: 80px; flex-shrink: 0; border-right: 1px solid rgba(255,255,255,0.05); }
.time-marker { height: 80px; font-size: 0.75rem; color: #6b7280; text-align: center; padding-top: 10px; }

.event-columns { display: grid; grid-template-columns: repeat(7, minmax(150px, 1fr)); flex: 1; position: relative; }
.event-column { border-left: 1px solid rgba(255,255,255,0.02); position: relative; }
.hour-row { height: 80px; border-bottom: 1px solid rgba(255,255,255,0.02); }

.event-block {
  position: absolute; left: 8px; right: 8px; border-left: 4px solid;
  border-radius: 8px; z-index: 10; cursor: pointer; transition: 0.2s;
}
.event-block:hover { transform: scale(1.02); z-index: 20; box-shadow: 0 10px 20px rgba(0,0,0,0.4); }
.event-inner { height: 100%; padding: 12px; display: flex; flex-direction: column; }
.ev-title { font-size: 0.85rem; font-weight: 700; margin-bottom: 4px; }
.ev-time { font-size: 0.7rem; opacity: 0.7; }

/* Mobile */
@media (max-width: 1024px) {
  .sidebar { transform: translateX(-100%); transition: 0.3s; }
  .sidebar-open .sidebar { transform: translateX(0); }
  .main-wrapper { margin-left: 0; width: 100%; align-items: stretch; }
  .menu-toggle { position: fixed; bottom: 20px; right: 20px; background: #c9a84c; color: #0a0e17; width: 50px; height: 50px; border-radius: 50%; z-index: 200; border: none; font-size: 1.5rem; }
}
</style>