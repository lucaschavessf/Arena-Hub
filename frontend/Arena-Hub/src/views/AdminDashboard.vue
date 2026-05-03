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
        <router-link to="/admin" class="nav-item active">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/>
            <rect x="3" y="14" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/>
          </svg>
          <span>Dashboard</span>
        </router-link>
        
        <router-link to="/admin/agenda" class="nav-item">
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
          <span class="breadcrumb-current">Dashboard</span>
        </div>

        <header class="content-header">
          <div class="header-titles">
            <h1>Visão Geral de Vendas</h1>
            <p>Análise de performance em tempo real • Unidade Arena Pernambuco</p>
          </div>
          <div class="header-actions">
            <button class="btn-export" @click="exportarRelatorio">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4M7 10l5 5 5-5M12 15V3"/>
              </svg>
              <span>Relatório PDF</span>
            </button>
          </div>
        </header>

        <section class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon sales">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M3 12h2M21 12h-2M12 3v2M12 19v2M5.64 5.64l1.5 1.5M16.86 16.86l1.5 1.5M18.36 5.64l-1.5 1.5M7.14 16.86l-1.5 1.5M12 8a4 4 0 1 0 0 8 4 4 0 0 0 0-8z"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Vendas Totais (Mês)</span>
              <div class="stat-value">{{ formatCurrency(vendasTotais) }}</div>
              <div class="stat-trend up">
                <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                  <polyline points="18 15 12 9 6 15"/>
                </svg>
                <span>+{{ crescimentoVendas }}% vs mês anterior</span>
              </div>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon ticket">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M20 12V8H6a2 2 0 0 1-2-2c0-1.5 1.5-2 2-2h12M4 17v-2a2 2 0 0 1 2-2h14v-2"/>
                <path d="M16 22h2a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2h-2"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Ticket Médio</span>
              <div class="stat-value">{{ formatCurrency(ticketMedio) }}</div>
              <div class="stat-trend up">
                <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                  <polyline points="18 15 12 9 6 15"/>
                </svg>
                <span>+{{ crescimentoTicket }}% vs mês anterior</span>
              </div>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon conversion">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M21 12a9 9 0 0 1-9 9m9-9a9 9 0 0 0-9-9m9 9H3m9 9a9 9 0 0 1-9-9m9 9c1.66 0 3-4 3-9s-1.34-9-3-9m0 18c-1.66 0-3-4-3-9s1.34-9 3-9"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Taxa de Conversão</span>
              <div class="stat-value">{{ taxaConversao }}%</div>
              <div class="stat-trend up">
                <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                  <polyline points="18 15 12 9 6 15"/>
                </svg>
                <span>+{{ crescimentoConversao }}% vs mês anterior</span>
              </div>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon visitors">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                <circle cx="12" cy="12" r="3"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Visualizações (Mês)</span>
              <div class="stat-value">{{ formatVisualizacoes(visualizacoes) }}</div>
              <div class="stat-trend up">
                <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                  <polyline points="18 15 12 9 6 15"/>
                </svg>
                <span>+{{ crescimentoVisualizacoes }}% vs mês anterior</span>
              </div>
            </div>
          </div>
        </section>

        <div class="dashboard-main-grid">
          
          <section class="card chart-card">
            <div class="card-header">
              <h3 class="card-title">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M21 12v-2a5 5 0 0 0-5-5H8a5 5 0 0 0-5 5v2"/><circle cx="12" cy="16" r="5"/><path d="M12 11v5"/>
                </svg>
                Fluxo de Receita Mensal
              </h3>
              <select class="mini-select" v-model="anoSelecionado" @change="atualizarGrafico">
                <option value="2025">Ano 2025</option>
                <option value="2026">Ano 2026</option>
              </select>
            </div>
            <div class="chart-container">
              <canvas ref="receitaChart"></canvas>
            </div>
          </section>

          <aside class="side-info-stack">
            <section class="card mini-card">
              <h3 class="card-title">
                <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 7h-4.5L12 3 8.5 7H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/>
                </svg>
                Vendas por Categoria
              </h3>
              <div class="bar-chart">
                <div class="bar-item" v-for="cat in categoriasVendas" :key="cat.nome">
                  <div class="bar-info">
                    <span>{{ cat.nome }}</span>
                    <span>{{ cat.percentual }}%</span>
                  </div>
                  <div class="bar-track">
                    <div class="bar-fill" :style="{ width: cat.percentual + '%' }"></div>
                  </div>
                </div>
              </div>
            </section>

            <section class="card mini-card">
              <h3 class="card-title">
                <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/>
                </svg>
                Status da Arena
              </h3>
              <div class="status-list">
                <div class="status-item">
                  <span class="dot green"></span>
                  <div class="status-content">
                    <span class="status-label">Arena Operacional</span>
                    <span class="status-time">{{ ultimaAtualizacao }}</span>
                  </div>
                </div>
                <div class="status-item">
                  <span class="dot gold"></span>
                  <div class="status-content">
                    <span class="status-label">{{ eventosMes }} Eventos este mês</span>
                    <span class="status-time">+{{ eventosCrescimento }} vs mês anterior</span>
                  </div>
                </div>
                <div class="status-item">
                  <span class="dot blue"></span>
                  <div class="status-content">
                    <span class="status-label">{{ ingressosVendidos }} Ingressos Vendidos</span>
                    <span class="status-time">Ocupação média de {{ ocupacaoMedia }}%</span>
                  </div>
                </div>
              </div>
            </section>
          </aside>

          <section class="card table-card">
            <h3 class="card-title">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
              </svg>
              Últimos Ingressos Vendidos
            </h3>
            <div class="table-wrapper">
              <table class="admin-table">
                <thead>
                  <tr>
                    <th>Cliente</th>
                    <th>Evento</th>
                    <th>Setor</th>
                    <th>Data/Hora</th>
                    <th>Valor</th>
                    <th>Status</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(venda, i) in ultimasVendas" :key="i">
                    <td>
                      <div class="cliente-info">
                        <div class="cliente-avatar">{{ venda.cliente.charAt(0) }}</div>
                        <span>{{ venda.cliente }}</span>
                      </div>
                    </td>
                    <td>{{ venda.evento }}</td>
                    <td>{{ venda.setor }}</td>
                    <td>{{ venda.horario }}</td>
                    <td>{{ formatCurrency(venda.valor) }}</td>
                    <td><span class="badge success">{{ venda.status }}</span></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </section>

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
import { ref, onMounted, computed } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'
import { Chart, registerables } from 'chart.js'

Chart.register(...registerables)

const isSidebarOpen = ref(false)
const receitaChart = ref<HTMLCanvasElement | null>(null)
let chartInstance: Chart | null = null
const anoSelecionado = ref('2026')

const vendasMensais = {
  2025: [42500, 48750, 52300, 49800, 58750, 62300, 65400, 68900, 64500, 71200, 75800, 82300],
  2026: [85000, 89700, 94500, 89500, 102300, 115000, 124500, 132000, 128500, 141000, 149000, 158000]
}

const meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']

const vendasTotais = computed(() => {
  const mesAtual = new Date().getMonth()
  return vendasMensais[anoSelecionado.value as keyof typeof vendasMensais][mesAtual]
})

const ticketMedio = computed(() => 185.50)

const taxaConversao = computed(() => 23.8)

const visualizacoes = computed(() => 48500)

const crescimentoVendas = computed(() => 12.5)
const crescimentoTicket = computed(() => 5.8)
const crescimentoConversao = computed(() => 3.2)
const crescimentoVisualizacoes = computed(() => 18.5)

const categoriasVendas = ref([
  { nome: 'Shows e Festivais', percentual: 58 },
  { nome: 'Eventos Esportivos', percentual: 28 },
  { nome: 'Teatro e Cultura', percentual: 9 },
  { nome: 'Outros Eventos', percentual: 5 }
])

const eventosMes = computed(() => 14)
const eventosCrescimento = computed(() => 3)
const ingressosVendidos = computed(() => 18500)
const ocupacaoMedia = computed(() => 72)
const ultimaAtualizacao = computed(() => {
  const agora = new Date()
  return `Atualizado ${agora.toLocaleTimeString('pt-BR')}`
})

const ultimasVendas = ref([
  { cliente: 'João Silva', evento: 'Rock Nacional 2026', setor: 'Cadeira Premium', horario: 'Há 5 minutos', valor: 250.00, status: 'Aprovado' },
  { cliente: 'Maria Santos', evento: 'Clássico Pernambucano', setor: 'Arquibancada', horario: 'Há 12 minutos', valor: 80.00, status: 'Aprovado' },
  { cliente: 'Pedro Costa', evento: 'Festival de Verão', setor: 'Pista', horario: 'Há 25 minutos', valor: 180.00, status: 'Aprovado' },
  { cliente: 'Ana Beatriz', evento: 'Rock Nacional 2026', setor: 'Camarote', horario: 'Há 38 minutos', valor: 450.00, status: 'Pendente' },
  { cliente: 'Lucas Pereira', evento: 'Teatro Mágico', setor: 'Plateia VIP', horario: 'Há 1 hora', valor: 120.00, status: 'Aprovado' }
])

function formatCurrency(value: number): string {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  }).format(value)
}

function formatVisualizacoes(value: number): string {
  if (value >= 1000) {
    return (value / 1000).toFixed(1) + 'k'
  }
  return value.toString()
}

function atualizarGrafico() {
  if (chartInstance) {
    const dados = vendasMensais[anoSelecionado.value as keyof typeof vendasMensais]
    chartInstance.data.datasets[0].data = dados
    chartInstance.update()
  }
}

function initChart() {
  if (receitaChart.value) {
    const ctx = receitaChart.value.getContext('2d')
    if (ctx) {
      if (chartInstance) {
        chartInstance.destroy()
      }
      
      const dados = vendasMensais[anoSelecionado.value as keyof typeof vendasMensais]
      
      chartInstance = new Chart(ctx, {
        type: 'line',
        data: {
          labels: meses,
          datasets: [{
            label: 'Receita (R$)',
            data: dados,
            borderColor: '#c9a84c',
            backgroundColor: 'rgba(201, 168, 76, 0.05)',
            borderWidth: 3,
            fill: true,
            tension: 0.4,
            pointBackgroundColor: '#c9a84c',
            pointBorderColor: '#0a0e17',
            pointBorderWidth: 2,
            pointRadius: 4,
            pointHoverRadius: 6
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: true,
          plugins: {
            legend: {
              display: false
            },
            tooltip: {
              backgroundColor: '#1a2233',
              titleColor: '#c9a84c',
              bodyColor: '#e0e0e0',
              borderColor: '#c9a84c',
              borderWidth: 1,
              callbacks: {
                label: function(context) {
                  return `Receita: ${formatCurrency(context.parsed.y)}`
                }
              }
            }
          },
          scales: {
            y: {
              grid: {
                color: 'rgba(255, 255, 255, 0.05)'
              },
              ticks: {
                color: '#8e9aaf',
                callback: function(value) {
                  return formatCurrency(value as number)
                }
              }
            },
            x: {
              grid: {
                display: false
              },
              ticks: {
                color: '#8e9aaf'
              }
            }
          }
        }
      })
    }
  }
}

function exportarRelatorio() {
  console.log('Exportando relatório PDF...')
  alert('Funcionalidade de exportação em desenvolvimento')
}

onMounted(() => {
  initChart()
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
  margin-bottom: 32px; 
}

.header-titles h1 { 
  font-family: 'Bebas Neue', sans-serif; 
  font-size: 2.2rem; 
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

.btn-export { 
  background: #1a2233; 
  color: #fff; 
  border: 1px solid rgba(255, 255, 255, 0.08);
  padding: 10px 18px; 
  border-radius: 10px; 
  font-size: 0.8rem; 
  display: flex; 
  align-items: center;
  gap: 8px; 
  cursor: pointer; 
  font-weight: 600;
  transition: all 0.3s ease;
}

.btn-export:hover {
  background: #1e2638;
  border-color: rgba(201, 168, 76, 0.3);
  transform: translateY(-2px);
}

.stats-grid { 
  display: grid; 
  grid-template-columns: repeat(4, 1fr); 
  gap: 20px; 
  margin-bottom: 32px; 
}

.stat-card { 
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  padding: 22px; 
  border-radius: 18px; 
  border: 1px solid rgba(255, 255, 255, 0.05);
  display: flex; 
  align-items: flex-start; 
  gap: 14px;
  transition: all 0.3s ease;
}

.stat-card:hover {
  transform: translateY(-4px);
  border-color: rgba(201, 168, 76, 0.15);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.stat-icon.sales {
  background: linear-gradient(135deg, rgba(201, 168, 76, 0.15), rgba(201, 168, 76, 0.05));
}

.stat-icon.sales svg {
  color: #c9a84c;
}

.stat-icon.ticket {
  background: linear-gradient(135deg, rgba(76, 135, 201, 0.15), rgba(76, 135, 201, 0.05));
}

.stat-icon.ticket svg {
  color: #4c87c9;
}

.stat-icon.conversion {
  background: linear-gradient(135deg, rgba(76, 201, 142, 0.15), rgba(76, 201, 142, 0.05));
}

.stat-icon.conversion svg {
  color: #4cc98e;
}

.stat-icon.visitors {
  background: linear-gradient(135deg, rgba(239, 68, 68, 0.15), rgba(239, 68, 68, 0.05));
}

.stat-icon.visitors svg {
  color: #ef4444;
}

.stat-info {
  flex: 1;
}

.stat-label { 
  color: #8e9aaf; 
  font-size: 0.7rem; 
  text-transform: uppercase; 
  letter-spacing: 0.8px; 
  display: block;
  margin-bottom: 6px;
}

.stat-value { 
  font-size: 1.6rem; 
  font-weight: 800; 
  line-height: 1.2;
  margin-bottom: 6px;
}

.stat-trend { 
  font-size: 0.7rem; 
  padding: 4px 8px; 
  border-radius: 20px; 
  font-weight: 700; 
  display: inline-flex;
  align-items: center;
  gap: 4px;
}

.stat-trend.up { 
  background: rgba(16, 185, 129, 0.1); 
  color: #10b981; 
}

.stat-trend.down { 
  background: rgba(239, 68, 68, 0.1); 
  color: #ef4444; 
}

.dashboard-main-grid { 
  display: grid; 
  grid-template-columns: 2fr 1fr; 
  gap: 24px; 
  align-items: start; 
}

.card { 
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px; 
  border: 1px solid rgba(255, 255, 255, 0.05);
  padding: 24px; 
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.card-title { 
  font-size: 0.9rem; 
  font-weight: 700; 
  color: #c9a84c; 
  text-transform: uppercase; 
  letter-spacing: 1px; 
  display: flex;
  align-items: center;
  gap: 8px;
}

.mini-select {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.08);
  color: #b0b8c5;
  padding: 6px 12px;
  border-radius: 8px;
  font-size: 0.75rem;
  cursor: pointer;
  font-weight: 600;
}

.chart-container { 
  height: 280px; 
  position: relative;
}

.chart-container canvas {
  max-height: 100%;
  width: 100% !important;
}

.bar-chart { 
  display: flex; 
  flex-direction: column; 
  gap: 16px; 
}

.bar-info { 
  display: flex; 
  justify-content: space-between; 
  font-size: 0.8rem; 
  color: #b0b8c5; 
  margin-bottom: 6px;
}

.bar-track { 
  height: 6px; 
  background: #1a2233; 
  border-radius: 10px; 
  overflow: hidden; 
}

.bar-fill { 
  height: 100%; 
  background: linear-gradient(90deg, #c9a84c, #d4af37);
  border-radius: 10px; 
  transition: width 0.5s ease;
}

.side-info-stack {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.mini-card {
  padding: 22px;
}

.status-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.status-item { 
  display: flex; 
  align-items: flex-start; 
  gap: 12px; 
}

.dot { 
  width: 8px; 
  height: 8px; 
  border-radius: 50%; 
  margin-top: 6px;
  flex-shrink: 0;
}

.green { 
  background: #10b981; 
  box-shadow: 0 0 10px rgba(16, 185, 129, 0.5);
}

.gold { 
  background: #c9a84c; 
  box-shadow: 0 0 10px rgba(201, 168, 76, 0.5);
}

.blue {
  background: #4c87c9;
  box-shadow: 0 0 10px rgba(76, 135, 201, 0.5);
}

.status-content {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.status-label {
  color: #e0e0e0;
  font-size: 0.9rem;
  font-weight: 500;
}

.status-time {
  color: #6b7280;
  font-size: 0.7rem;
}

.table-card { 
  grid-column: span 2; 
}

.table-wrapper {
  overflow-x: auto;
  margin-top: 16px;
}

.admin-table { 
  width: 100%; 
  border-collapse: collapse; 
}

.admin-table th { 
  text-align: left; 
  padding: 12px 12px 16px; 
  font-size: 0.7rem; 
  color: #6b7280; 
  text-transform: uppercase; 
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  font-weight: 700;
  letter-spacing: 0.5px;
}

.admin-table td { 
  padding: 14px 12px; 
  font-size: 0.85rem; 
  border-bottom: 1px solid rgba(255, 255, 255, 0.02);
  color: #b0b8c5;
}

.cliente-info {
  display: flex;
  align-items: center;
  gap: 10px;
}

.cliente-avatar {
  width: 32px;
  height: 32px;
  border-radius: 8px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 700;
  font-size: 0.9rem;
}

.badge { 
  padding: 4px 10px; 
  border-radius: 20px; 
  font-size: 0.7rem; 
  font-weight: 700; 
  background: rgba(16, 185, 129, 0.1); 
  color: #10b981; 
  border: 1px solid rgba(16, 185, 129, 0.2);
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
  
  .stats-grid { 
    grid-template-columns: repeat(2, 1fr); 
  }
  
  .dashboard-main-grid { 
    grid-template-columns: 1fr; 
  }
  
  .table-card { 
    grid-column: span 1; 
  }
  
  .menu-toggle {
    display: flex;
  }
  
  .content-container {
    padding: 24px 20px;
  }
}

@media (max-width: 600px) {
  .stats-grid { 
    grid-template-columns: 1fr; 
  }
  
  .content-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 16px;
  }
  
  .header-titles h1 {
    font-size: 1.8rem;
  }
  
  .chart-container {
    height: 240px;
  }
  
  .admin-table {
    font-size: 0.8rem;
  }
  
  .admin-table th,
  .admin-table td {
    padding: 10px 8px;
  }
}
</style>