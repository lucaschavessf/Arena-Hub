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

        <router-link to="/admin/agenda" class="nav-item">
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
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="8" r="4"/>
            <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
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

        <router-link to="/admin/eventos" class="nav-item active">
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
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2"/>
            <circle cx="9" cy="7" r="4"/>
            <line x1="19" y1="8" x2="19" y2="14"/>
            <line x1="22" y1="11" x2="16" y2="11"/>
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
          <span class="breadcrumb-current">Eventos Aprovados</span>
        </div>

        <header class="content-header">
          <div class="header-titles">
            <h1>Eventos Aprovados</h1>
            <p>Gerencie todos os eventos que foram aprovados</p>
          </div>
          <div class="header-actions">
            <button class="btn-export" @click="exportarRelatorio">
              <svg
                width="16"
                height="16"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4M7 10l5 5 5-5M12 15V3" />
              </svg>
              <span>Exportar</span>
            </button>
          </div>
        </header>

        <div class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon" style="background: rgba(201, 168, 76, 0.1)">
              <svg
                width="20"
                height="20"
                viewBox="0 0 24 24"
                fill="none"
                stroke="#c9a84c"
                stroke-width="2"
              >
                <rect x="3" y="4" width="18" height="18" rx="2" ry="2" />
                <line x1="16" y1="2" x2="16" y2="6" />
                <line x1="8" y1="2" x2="8" y2="6" />
                <line x1="3" y1="10" x2="21" y2="10" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Total de Eventos</span>
              <div class="stat-value">{{ eventos.length }}</div>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon" style="background: rgba(245, 158, 11, 0.1)">
              <svg
                width="20"
                height="20"
                viewBox="0 0 24 24"
                fill="none"
                stroke="#f59e0b"
                stroke-width="2"
              >
                <circle cx="12" cy="12" r="10" />
                <path d="M12 6v6l4 2" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Agendados</span>
              <div class="stat-value">{{ eventosAgendados.length }}</div>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon" style="background: rgba(16, 185, 129, 0.1)">
              <svg
                width="20"
                height="20"
                viewBox="0 0 24 24"
                fill="none"
                stroke="#10b981"
                stroke-width="2"
              >
                <polyline points="20 6 9 17 4 12" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Finalizados</span>
              <div class="stat-value">{{ eventosFinalizados.length }}</div>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon" style="background: rgba(239, 68, 68, 0.1)">
              <svg
                width="20"
                height="20"
                viewBox="0 0 24 24"
                fill="none"
                stroke="#ef4444"
                stroke-width="2"
              >
                <line x1="18" y1="6" x2="6" y2="18" />
                <line x1="6" y1="6" x2="18" y2="18" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Cancelados</span>
              <div class="stat-value">{{ eventosCancelados.length }}</div>
            </div>
          </div>
        </div>

        <div class="filtros-bar">
          <div class="status-filtros">
            <button
              v-for="tab in tabs"
              :key="tab.value"
              class="filtro-btn"
              :class="{ active: filtroStatus === tab.value }"
              @click="filtroStatus = tab.value"
            >
              {{ tab.label }}
              <span class="count">{{ contarPorStatus(tab.value) }}</span>
            </button>
          </div>
          <div class="search-box">
            <svg
              width="18"
              height="18"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <circle cx="11" cy="11" r="8" />
              <line x1="21" y1="21" x2="16.65" y2="16.65" />
            </svg>
            <input v-model="busca" type="text" placeholder="Buscar evento ou produtor..." />
          </div>
        </div>

        <div class="eventos-lista">
          <div v-for="evento in eventosFiltrados" :key="evento.id" class="evento-card">
            <div
              class="evento-status"
              :class="`status-${evento.status.toLowerCase().replace('_', '')}`"
            >
              {{ getStatusLabel(evento.status) }}
            </div>

            <div class="evento-conteudo">
              <div class="evento-imagem">
                <div class="imagem-placeholder" :class="getCategoriaClass(evento.categoria)">
                  <svg
                    width="32"
                    height="32"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="1.5"
                  >
                    <path d="M20 12V8H4v16h16V20M12 12v8M8 12v4M16 12v4" />
                  </svg>
                </div>
              </div>

              <div class="evento-info">
                <h3 class="evento-nome">{{ evento.nome }}</h3>
                <p class="evento-produtor">{{ evento.produtor }}</p>

                <div class="evento-detalhes">
                  <div class="detalhe">
                    <svg
                      width="14"
                      height="14"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                    >
                      <circle cx="12" cy="12" r="10" />
                      <polyline points="12 6 12 12 16 14" />
                    </svg>
                    <span>{{ formatarData(evento.data) }}</span>
                  </div>
                  <div class="detalhe">
                    <svg
                      width="14"
                      height="14"
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
                    <span>{{ evento.horario }}</span>
                  </div>
                  <div class="detalhe">
                    <svg
                      width="14"
                      height="14"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                    >
                      <circle cx="12" cy="12" r="10" />
                      <path d="M12 6v6l4 2" />
                    </svg>
                    <span>{{ evento.ocupacao }}% ocupado</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="evento-acoes">
              <button class="btn-dashboard" @click="verDashboard(evento)">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <circle cx="12" cy="12" r="3" />
                  <path
                    d="M22 12c-2.667 4.667-6 7-10 7s-7.333-2.333-10-7c2.667-4.667 6-7 10-7s7.333 2.333 10 7z"
                  />
                </svg>
                Dashboard
              </button>
              <button
                v-if="evento.status !== 'CANCELADO' && evento.status !== 'FINALIZADO'"
                class="btn-cancelar"
                @click="abrirModalCancelar(evento)"
              >
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2.5"
                >
                  <line x1="18" y1="6" x2="6" y2="18" />
                  <line x1="6" y1="6" x2="18" y2="18" />
                </svg>
                Cancelar
              </button>
              <button
                v-if="evento.status === 'AGENDADO'"
                class="btn-finalizar"
                @click="finalizarEvento(evento)"
              >
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2.5"
                >
                  <polyline points="20 6 9 17 4 12" />
                </svg>
                Finalizar
              </button>
            </div>
          </div>

          <div v-if="eventosFiltrados.length === 0" class="empty-state">
            <svg
              width="64"
              height="64"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="1.5"
            >
              <rect x="3" y="4" width="18" height="18" rx="2" ry="2" />
              <line x1="16" y1="2" x2="16" y2="6" />
              <line x1="8" y1="2" x2="8" y2="6" />
              <line x1="3" y1="10" x2="21" y2="10" />
            </svg>
            <h3>Nenhum evento encontrado</h3>
            <p>Tente ajustar os filtros ou termo de busca.</p>
          </div>
        </div>
      </main>
    </div>

    <div v-if="modalCancelarAberto" class="modal-overlay" @click.self="fecharModalCancelar">
      <div class="modal-container">
        <div class="modal-header">
          <h3>Cancelar Evento</h3>
          <button class="modal-fechar" @click="fecharModalCancelar">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <line x1="18" y1="6" x2="6" y2="18" />
              <line x1="6" y1="6" x2="18" y2="18" />
            </svg>
          </button>
        </div>
        <div class="modal-body">
          <p>
            Tem certeza que deseja cancelar o evento <strong>{{ eventoSelecionado?.nome }}</strong
            >?
          </p>
          <div class="form-group">
            <label>Motivo do cancelamento *</label>
            <textarea
              v-model="motivoCancelamento"
              rows="3"
              placeholder="Informe o motivo do cancelamento..."
              class="input-textarea"
            ></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn-modal-cancelar" @click="fecharModalCancelar">Voltar</button>
          <button class="btn-modal-confirmar" @click="confirmarCancelar">
            Confirmar Cancelamento
          </button>
        </div>
      </div>
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
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'

const router = useRouter()
const isSidebarOpen = ref(false)
const filtroStatus = ref('todos')
const busca = ref('')
const modalCancelarAberto = ref(false)
const eventoSelecionado = ref<any>(null)
const motivoCancelamento = ref('')

const tabs = [
  { label: 'Todos', value: 'todos' },
  { label: 'Agendados', value: 'AGENDADO' },
  { label: 'Em Andamento', value: 'EM_ANDAMENTO' },
  { label: 'Finalizados', value: 'FINALIZADO' },
  { label: 'Cancelados', value: 'CANCELADO' },
]

const eventos = ref([
  {
    id: 1,
    nome: 'Rock Nacional 2026',
    produtor: 'Recife Eventos LTDA',
    data: '2026-08-15',
    horario: '20:00',
    categoria: 'Show',
    ocupacao: 75,
    status: 'AGENDADO',
  },
  {
    id: 2,
    nome: 'Clássico Pernambucano',
    produtor: 'Federação Pernambucana de Futebol',
    data: '2026-07-20',
    horario: '16:00',
    categoria: 'Esportes',
    ocupacao: 92,
    status: 'AGENDADO',
  },
  {
    id: 3,
    nome: 'Festival de Verão',
    produtor: 'Verão Produções',
    data: '2026-06-10',
    horario: '18:00',
    categoria: 'Show',
    ocupacao: 100,
    status: 'FINALIZADO',
  },
  {
    id: 4,
    nome: 'Encontro de Startups',
    produtor: 'Tech PE',
    data: '2026-05-20',
    horario: '14:00',
    categoria: 'Corporativo',
    ocupacao: 45,
    status: 'CANCELADO',
  },
  {
    id: 5,
    nome: 'Show de Comédia',
    produtor: 'Comédia Produções',
    data: '2026-09-05',
    horario: '21:00',
    categoria: 'Comédia',
    ocupacao: 60,
    status: 'AGENDADO',
  },
])

const eventosAgendados = computed(() => eventos.value.filter((e) => e.status === 'AGENDADO'))
const eventosFinalizados = computed(() => eventos.value.filter((e) => e.status === 'FINALIZADO'))
const eventosCancelados = computed(() => eventos.value.filter((e) => e.status === 'CANCELADO'))

const eventosFiltrados = computed(() => {
  return eventos.value.filter((evento) => {
    const matchStatus = filtroStatus.value === 'todos' || evento.status === filtroStatus.value
    const matchBusca =
      !busca.value ||
      evento.nome.toLowerCase().includes(busca.value.toLowerCase()) ||
      evento.produtor.toLowerCase().includes(busca.value.toLowerCase())
    return matchStatus && matchBusca
  })
})

function getStatusLabel(status: string) {
  const map: Record<string, string> = {
    AGENDADO: 'Agendado',
    EM_ANDAMENTO: 'Em Andamento',
    FINALIZADO: 'Finalizado',
    CANCELADO: 'Cancelado',
  }
  return map[status] || status
}

function getCategoriaClass(categoria: string) {
  const map: Record<string, string> = {
    Show: 'categoria-show',
    Esportes: 'categoria-esportes',
    Corporativo: 'categoria-corporativo',
    Comédia: 'categoria-comedia',
  }
  return map[categoria] || ''
}

function formatarData(data: string) {
  return new Date(data).toLocaleDateString('pt-BR')
}

function contarPorStatus(status: string) {
  if (status === 'todos') return eventos.value.length
  return eventos.value.filter((e) => e.status === status).length
}

function verDashboard(evento: any) {
  router.push(`/admin/evento/${evento.id}`)
}

function abrirModalCancelar(evento: any) {
  eventoSelecionado.value = evento
  motivoCancelamento.value = ''
  modalCancelarAberto.value = true
}

function fecharModalCancelar() {
  modalCancelarAberto.value = false
  eventoSelecionado.value = null
  motivoCancelamento.value = ''
}

function confirmarCancelar() {
  if (!motivoCancelamento.value.trim()) {
    alert('Informe o motivo do cancelamento')
    return
  }

  const index = eventos.value.findIndex((e) => e.id === eventoSelecionado.value.id)
  if (index !== -1) {
    eventos.value[index].status = 'CANCELADO'
  }

  alert(
    `Evento "${eventoSelecionado.value.nome}" cancelado com sucesso!\nMotivo: ${motivoCancelamento.value}`,
  )
  fecharModalCancelar()
}

function finalizarEvento(evento: any) {
  const index = eventos.value.findIndex((e) => e.id === evento.id)
  if (index !== -1) {
    eventos.value[index].status = 'FINALIZADO'
  }
  alert(`Evento "${evento.nome}" finalizado com sucesso!`)
}

function exportarRelatorio() {
  alert('Funcionalidade de exportação em desenvolvimento')
}
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
  margin-bottom: 28px;
  flex-wrap: wrap;
  gap: 16px;
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
  margin-bottom: 28px;
}
.stat-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  padding: 20px;
  border-radius: 16px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  display: flex;
  align-items: center;
  gap: 14px;
  transition: all 0.3s ease;
}
.stat-card:hover {
  transform: translateY(-3px);
  border-color: rgba(201, 168, 76, 0.15);
}
.stat-icon {
  width: 44px;
  height: 44px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
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
  margin-bottom: 4px;
}
.stat-value {
  font-size: 1.6rem;
  font-weight: 800;
  line-height: 1.2;
}
.filtros-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  flex-wrap: wrap;
  gap: 16px;
}
.status-filtros {
  display: flex;
  gap: 8px;
  background: #1a2233;
  padding: 6px;
  border-radius: 12px;
  flex-wrap: wrap;
}
.filtro-btn {
  padding: 8px 16px;
  background: transparent;
  border: none;
  color: #8e9aaf;
  font-size: 0.8rem;
  font-weight: 600;
  border-radius: 8px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s;
}
.filtro-btn:hover {
  color: #c9a84c;
}
.filtro-btn.active {
  background: #c9a84c;
  color: #0a0e17;
}
.filtro-btn .count {
  background: rgba(255, 255, 255, 0.1);
  padding: 2px 6px;
  border-radius: 10px;
  font-size: 0.7rem;
}
.filtro-btn.active .count {
  background: rgba(10, 14, 23, 0.2);
}
.search-box {
  display: flex;
  align-items: center;
  gap: 10px;
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 10px 16px;
  min-width: 250px;
}
.search-box svg {
  color: #6b7280;
}
.search-box input {
  background: transparent;
  border: none;
  color: white;
  font-size: 0.9rem;
  outline: none;
  width: 100%;
}
.eventos-lista {
  display: flex;
  flex-direction: column;
  gap: 16px;
}
.evento-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 20px 24px;
  position: relative;
  transition: all 0.3s ease;
}
.evento-card:hover {
  transform: translateY(-2px);
  border-color: rgba(201, 168, 76, 0.15);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
}
.evento-status {
  position: absolute;
  top: 20px;
  right: 24px;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 700;
}
.status-agendado {
  background: rgba(245, 158, 11, 0.15);
  color: #f59e0b;
  border: 1px solid rgba(245, 158, 11, 0.3);
}
.status-emandamento {
  background: rgba(59, 130, 246, 0.15);
  color: #3b82f6;
  border: 1px solid rgba(59, 130, 246, 0.3);
}
.status-finalizado {
  background: rgba(16, 185, 129, 0.15);
  color: #10b981;
  border: 1px solid rgba(16, 185, 129, 0.3);
}
.status-cancelado {
  background: rgba(239, 68, 68, 0.15);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}
.evento-conteudo {
  display: flex;
  gap: 20px;
}
.evento-imagem {
  flex-shrink: 0;
}
.imagem-placeholder {
  width: 70px;
  height: 70px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.categoria-show {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
}
.categoria-esportes {
  background: rgba(76, 201, 142, 0.15);
  color: #4cc98e;
}
.categoria-corporativo {
  background: rgba(59, 130, 246, 0.15);
  color: #3b82f6;
}
.categoria-comedia {
  background: rgba(236, 72, 153, 0.15);
  color: #ec4899;
}
.evento-info {
  flex: 1;
}
.evento-nome {
  font-size: 1.1rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 4px;
}
.evento-produtor {
  font-size: 0.8rem;
  color: #8e9aaf;
  margin-bottom: 12px;
}
.evento-detalhes {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}
.detalhe {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 0.8rem;
  color: #b0b8c5;
}
.evento-acoes {
  display: flex;
  gap: 12px;
  margin-top: 16px;
  padding-top: 16px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
  justify-content: flex-end;
}
.btn-dashboard {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 8px 16px;
  border-radius: 8px;
  color: #0a0e17;
  font-weight: 600;
  font-size: 0.8rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s;
}
.btn-dashboard:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.3);
}
.btn-cancelar {
  background: rgba(239, 68, 68, 0.15);
  border: 1px solid rgba(239, 68, 68, 0.3);
  padding: 8px 16px;
  border-radius: 8px;
  color: #ef4444;
  font-weight: 600;
  font-size: 0.8rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s;
}
.btn-cancelar:hover {
  background: rgba(239, 68, 68, 0.25);
}
.btn-finalizar {
  background: rgba(16, 185, 129, 0.15);
  border: 1px solid rgba(16, 185, 129, 0.3);
  padding: 8px 16px;
  border-radius: 8px;
  color: #10b981;
  font-weight: 600;
  font-size: 0.8rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s;
}
.btn-finalizar:hover {
  background: rgba(16, 185, 129, 0.25);
}
.empty-state {
  text-align: center;
  padding: 60px 20px;
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
  border: 1px solid rgba(255, 255, 255, 0.05);
}
.empty-state svg {
  color: #c9a84c;
  opacity: 0.4;
  margin-bottom: 20px;
}
.empty-state h3 {
  font-size: 1.2rem;
  font-weight: 600;
  color: #e0e0e0;
  margin-bottom: 8px;
}
.empty-state p {
  color: #6b7280;
}
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.8);
  backdrop-filter: blur(8px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}
.modal-container {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(201, 168, 76, 0.15);
  border-radius: 24px;
  width: 90%;
  max-width: 450px;
}
.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}
.modal-header h3 {
  font-size: 1.2rem;
  font-weight: 700;
  color: #c9a84c;
  margin: 0;
}
.modal-fechar {
  background: transparent;
  border: none;
  color: #8e9aaf;
  cursor: pointer;
  transition: color 0.2s;
}
.modal-fechar:hover {
  color: #ef4444;
}
.modal-body {
  padding: 24px;
}
.modal-body p {
  color: #e0e0e0;
  margin-bottom: 16px;
}
.form-group label {
  display: block;
  font-size: 0.75rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  margin-bottom: 8px;
}
.input-textarea {
  width: 100%;
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 12px;
  color: white;
  font-size: 0.9rem;
  font-family: inherit;
  resize: vertical;
  outline: none;
}
.input-textarea:focus {
  border-color: #c9a84c;
}
.modal-footer {
  display: flex;
  gap: 12px;
  padding: 20px 24px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
  justify-content: flex-end;
}
.btn-modal-cancelar {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 10px 20px;
  border-radius: 10px;
  color: #8e9aaf;
  font-weight: 600;
  cursor: pointer;
}
.btn-modal-cancelar:hover {
  background: #1e2638;
}
.btn-modal-confirmar {
  background: #ef4444;
  border: none;
  padding: 10px 20px;
  border-radius: 10px;
  color: white;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
}
.btn-modal-confirmar:hover {
  background: #dc2626;
  transform: translateY(-2px);
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
  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 600px) {
  .stats-grid {
    grid-template-columns: 1fr;
  }
  .filtros-bar {
    flex-direction: column;
    align-items: stretch;
  }
  .status-filtros {
    justify-content: center;
  }
  .evento-conteudo {
    flex-direction: column;
  }
  .evento-imagem {
    display: flex;
    justify-content: center;
  }
  .evento-acoes {
    flex-wrap: wrap;
    justify-content: center;
  }
  .evento-detalhes {
    flex-direction: column;
    gap: 8px;
  }
}
</style>
