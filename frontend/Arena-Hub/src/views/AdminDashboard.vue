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
        <router-link to="/admin" class="nav-item active">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/><rect x="3" y="14" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/></svg>
          Dashboard
        </router-link>
        <router-link to="/admin/agenda" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/></svg>
          Agenda
        </router-link>
        <div class="nav-spacer"></div>
        <button @click="$router.push('/')" class="nav-item logout">Sair do Admin</button>
      </nav>
    </aside>

    <div class="main-wrapper">
      <AppNavbar :isAdmin="true" />
      
      <main class="content-container">
        <header class="content-header">
          <div class="header-titles">
            <h1>Visão Geral de Vendas</h1>
            <p>Análise de performance em tempo real • Unidade Arena Pernambuco</p>
          </div>
          <div class="header-actions">
            <button class="btn-export">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4M7 10l5 5 5-5M12 15V3"/></svg>
              Relatório PDF
            </button>
          </div>
        </header>

        <section class="stats-grid">
          <div v-for="(val, label) in formattedStats" :key="label" class="stat-card">
            <div class="stat-info">
              <span class="stat-label">{{ label }}</span>
              <div class="stat-value">{{ val }}</div>
            </div>
            <div class="stat-trend" :class="label === 'Taxa de Compra' ? 'down' : 'up'">
              {{ label === 'Taxa de Compra' ? '↓ 2%' : '↑ 12%' }}
            </div>
          </div>
        </section>

        <div class="dashboard-main-grid">
          
          <section class="card chart-card">
            <div class="card-header">
              <h3 class="card-title">Fluxo de Receita Mensal</h3>
              <select class="mini-select"><option>Ano 2026</option></select>
            </div>
            <div class="chart-container">
              <div class="chart-y-axis">
                <span v-for="v in yLabels" :key="v">{{ v >= 1000 ? v/1000 + 'k' : v }}</span>
              </div>
              <div class="chart-body">
                <svg :viewBox="`0 0 ${svgW} ${svgH}`" class="chart-svg" preserveAspectRatio="none">
                  <defs>
                    <linearGradient id="goldGrad" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="0%" stop-color="#c9a84c" stop-opacity="0.2"/>
                      <stop offset="100%" stop-color="#c9a84c" stop-opacity="0"/>
                    </linearGradient>
                  </defs>
                  <polygon :points="areaPoints" fill="url(#goldGrad)" />
                  <polyline :points="linePoints" fill="none" stroke="#c9a84c" stroke-width="2.5" />
                  <circle v-for="(pt, i) in chartPoints" :key="i" :cx="pt.x" :cy="pt.y" r="3.5" fill="#c9a84c"/>
                </svg>
                <div class="chart-x-axis">
                  <span v-for="d in chartData" :key="d.mes">{{ d.mes.substring(0,3) }}</span>
                </div>
              </div>
            </div>
          </section>

          <aside class="side-info-stack">
            <section class="card mini-card">
              <h3 class="card-title">Vendas por Categoria</h3>
              <div class="bar-chart">
                <div class="bar-item" v-for="cat in ['Shows', 'Futebol', 'Outros']" :key="cat">
                  <div class="bar-info">
                    <span>{{ cat }}</span>
                    <span>{{ cat === 'Shows' ? '65%' : cat === 'Futebol' ? '25%' : '10%' }}</span>
                  </div>
                  <div class="bar-track">
                    <div class="bar-fill" :style="{ width: cat === 'Shows' ? '65%' : cat === 'Futebol' ? '25%' : '10%' }"></div>
                  </div>
                </div>
              </div>
            </section>

            <section class="card mini-card">
              <h3 class="card-title">Status da Arena</h3>
              <div class="status-list">
                <div class="status-item"><span class="dot green"></span> Arena Operacional</div>
                <div class="status-item"><span class="dot gold"></span> 12 Eventos este mês</div>
              </div>
            </section>
          </aside>

          <section class="card table-card">
            <h3 class="card-title">Últimos Ingressos Vendidos</h3>
            <table class="admin-table">
              <thead>
                <tr>
                  <th>Cliente</th>
                  <th>Evento</th>
                  <th>Setor</th>
                  <th>Data/Hora</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="i in 3" :key="i">
                  <td>João Silva</td>
                  <td>Rock Nacional 2026</td>
                  <td>Cadeira Premium</td>
                  <td>Há 5 minutos</td>
                  <td><span class="badge success">Aprovado</span></td>
                </tr>
              </tbody>
            </table>
          </section>

        </div>
      </main>
    </div>

    <button class="menu-toggle" @click="isSidebarOpen = !isSidebarOpen">☰</button>
  </div>
</template>

<style scoped>
/* ESTRUTURA E FONTES (Sincronizado com Agenda) */
.admin-layout { display: flex; min-height: 100vh; background: #0a0e17; color: #fff; font-family: 'Plus Jakarta Sans', sans-serif; }

.sidebar { width: 240px; background: #111827; border-right: 1px solid rgba(255,255,255,0.03); position: fixed; height: 100vh; z-index: 100; }
.brand { display: flex; align-items: center; gap: 10px; padding: 30px; text-decoration: none; color: white; font-weight: 700; }
.logo-box { background: #c9a84c; color: #000; padding: 4px 10px; border-radius: 6px; font-family: 'Bebas Neue', sans-serif; }

.sidebar-nav { padding: 0 15px; display: flex; flex-direction: column; gap: 5px; }
.nav-item { display: flex; align-items: center; gap: 12px; padding: 12px 15px; border-radius: 8px; color: #8e9aaf; text-decoration: none; font-size: 0.9rem; transition: 0.2s; }
.nav-item:hover, .nav-item.active { background: rgba(201, 168, 76, 0.08); color: #c9a84c; }
.logout { margin-top: auto; margin-bottom: 20px; color: #ef4444; background: none; border: none; width: 100%; cursor: pointer; text-align: left; }

/* LAYOUT CENTRAL */
.main-wrapper { flex: 1; margin-left: 240px; display: flex; flex-direction: column; align-items: center; width: calc(100% - 240px); }
.content-container { width: 100%; max-width: 1200px; padding: 40px; }

/* HEADER */
.content-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }
.header-titles h1 { font-family: 'Bebas Neue', sans-serif; font-size: 2.2rem; letter-spacing: 1px; margin-bottom: 4px; }
.header-titles p { color: #8e9aaf; font-size: 0.85rem; }

.btn-export { background: #1c2433; color: #fff; border: 1px solid rgba(255,255,255,0.1); padding: 10px 16px; border-radius: 8px; font-size: 0.8rem; display: flex; gap: 8px; cursor: pointer; }

/* STATS */
.stats-grid { display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px; margin-bottom: 25px; }
.stat-card { background: #111827; padding: 24px; border-radius: 16px; border: 1px solid rgba(255,255,255,0.05); display: flex; justify-content: space-between; align-items: flex-start; }
.stat-label { color: #8e9aaf; font-size: 0.7rem; text-transform: uppercase; letter-spacing: 1px; }
.stat-value { font-size: 1.6rem; font-weight: 800; margin-top: 4px; }
.stat-trend { font-size: 0.7rem; padding: 4px 8px; border-radius: 20px; font-weight: 700; }
.stat-trend.up { background: rgba(16, 185, 129, 0.1); color: #10b981; }
.stat-trend.down { background: rgba(239, 68, 68, 0.1); color: #ef4444; }

/* GRID PRINCIPAL 2fr 1fr */
.dashboard-main-grid { display: grid; grid-template-columns: 2fr 1fr; gap: 20px; align-items: start; }
.card { background: #111827; border-radius: 16px; border: 1px solid rgba(255,255,255,0.05); padding: 24px; }
.card-title { font-size: 0.9rem; font-weight: 700; color: #c9a84c; text-transform: uppercase; letter-spacing: 1px; margin-bottom: 20px; }

/* GRÁFICOS E TABELAS */
.chart-container { display: flex; gap: 15px; height: 250px; }
.chart-y-axis { display: flex; flex-direction: column; justify-content: space-between; color: #4b5563; font-size: 0.65rem; padding-bottom: 30px; }
.chart-body { flex: 1; display: flex; flex-direction: column; }
.chart-x-axis { display: flex; justify-content: space-between; padding-top: 15px; color: #8e9aaf; font-size: 0.65rem; }

.bar-chart { display: flex; flex-direction: column; gap: 15px; }
.bar-track { height: 6px; background: #1c2433; border-radius: 10px; overflow: hidden; margin-top: 6px; }
.bar-fill { height: 100%; background: #c9a84c; border-radius: 10px; }
.bar-info { display: flex; justify-content: space-between; font-size: 0.8rem; color: #8e9aaf; }

.table-card { grid-column: span 2; }
.admin-table { width: 100%; border-collapse: collapse; margin-top: 10px; }
.admin-table th { text-align: left; padding: 12px; font-size: 0.75rem; color: #4b5563; text-transform: uppercase; border-bottom: 1px solid rgba(255,255,255,0.05); }
.admin-table td { padding: 16px 12px; font-size: 0.85rem; border-bottom: 1px solid rgba(255,255,255,0.02); }
.badge { padding: 4px 10px; border-radius: 6px; font-size: 0.7rem; font-weight: 700; background: rgba(16, 185, 129, 0.1); color: #10b981; }

.status-item { font-size: 0.85rem; display: flex; align-items: center; gap: 10px; color: #8e9aaf; margin-bottom: 12px; }
.dot { width: 6px; height: 6px; border-radius: 50%; }
.green { background: #10b981; }
.gold { background: #c9a84c; }

@media (max-width: 1024px) {
  .sidebar { transform: translateX(-100%); transition: 0.3s; }
  .main-wrapper { margin-left: 0; width: 100%; }
  .stats-grid { grid-template-columns: 1fr 1fr; }
  .dashboard-main-grid { grid-template-columns: 1fr; }
  .table-card { grid-column: span 1; }
}
</style>

<script setup>
import { ref, computed } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'

const isSidebarOpen = ref(false)
const chartData = [
  { mes: 'Jan', valor: 8000 }, { mes: 'Fev', valor: 9500 }, { mes: 'Mar', valor: 11000 },
  { mes: 'Abr', valor: 10500 }, { mes: 'Mai', valor: 13000 }, { mes: 'Jun', valor: 15000 },
  { mes: 'Jul', valor: 14000 }, { mes: 'Ago', valor: 12000 }, { mes: 'Set', valor: 10000 }
]

const formattedStats = computed(() => ({
  "Vendas Totais": "R$ 42.850",
  "Ticket Médio": "R$ 215,00",
  "Taxa de Compra": "18.5%",
  "Visualizações": "12.402"
}))

const svgW = 800, svgH = 200
const maxVal = 15000
const yLabels = [0, 5000, 10000, 15000].reverse()

const chartPoints = computed(() =>
  chartData.map((d, i) => ({
    x: (i / (chartData.length - 1)) * (svgW - 20) + 10,
    y: svgH - 10 - ((d.valor / maxVal) * (svgH - 30)),
  }))
)
const linePoints = computed(() => chartPoints.value.map(p => `${p.x},${p.y}`).join(' '))
const areaPoints = computed(() => {
  const pts = chartPoints.value
  return `${pts[0].x},${svgH} ${linePoints.value} ${pts[pts.length-1].x},${svgH}`
})
</script>