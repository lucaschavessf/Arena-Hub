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
          <router-link to="/admin/eventos" class="breadcrumb-link">Eventos</router-link>
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
          <span class="breadcrumb-current">{{ evento.nome }}</span>
        </div>

        <div class="evento-header">
          <div class="evento-titulo">
            <h1>{{ evento.nome }}</h1>
            <span
              class="status-badge"
              :class="`status-${evento.status.toLowerCase().replace('_', '')}`"
            >
              {{ getStatusLabel(evento.status) }}
            </span>
          </div>
          <div class="evento-acoes-header">
            <button class="btn-voltar" @click="$router.push('/admin/eventos')">
              <svg
                width="16"
                height="16"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <polyline points="15 18 9 12 15 6" />
              </svg>
              Voltar
            </button>
          </div>
        </div>

        <div class="dashboard-grid">
          <div class="coluna-esquerda">
            <div class="info-card">
              <h3 class="card-title">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <circle cx="12" cy="12" r="10" />
                  <path d="M12 8v4l3 3" />
                </svg>
                Resumo do Evento
              </h3>
              <div class="info-grid">
                <div class="info-item">
                  <label>Produtor</label>
                  <span>{{ evento.produtor }}</span>
                </div>
                <div class="info-item">
                  <label>Categoria</label>
                  <span class="categoria-tag" :class="getCategoriaClass(evento.categoria)">{{
                    evento.categoria
                  }}</span>
                </div>
                <div class="info-item">
                  <label>Data</label>
                  <span>{{ formatarData(evento.data) }}</span>
                </div>
                <div class="info-item">
                  <label>Horário</label>
                  <span>{{ evento.horario }}</span>
                </div>
                <div class="info-item">
                  <label>Descrição</label>
                  <span class="descricao">{{ evento.descricao }}</span>
                </div>
              </div>
            </div>

            <div class="info-card">
              <h3 class="card-title">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <rect x="3" y="3" width="18" height="18" rx="2" />
                </svg>
                Espaços e Capacidade
              </h3>
              <div class="capacidade-grid">
                <div class="capacidade-item">
                  <span class="capacidade-label">Capacidade Autorizada</span>
                  <span class="capacidade-valor"
                    >{{ evento.capacidadeAutorizada.toLocaleString() }} pessoas</span
                  >
                </div>
                <div class="capacidade-item">
                  <span class="capacidade-label">Público Estimado</span>
                  <span class="capacidade-valor"
                    >{{ evento.publicoEstimado.toLocaleString() }} pessoas</span
                  >
                </div>
                <div class="capacidade-item">
                  <span class="capacidade-label">Reservas Confirmadas</span>
                  <span class="capacidade-valor"
                    >{{ evento.reservasConfirmadas.toLocaleString() }} pessoas</span
                  >
                </div>
                <div class="capacidade-item">
                  <span class="capacidade-label">Capacidade Restante</span>
                  <span class="capacidade-valor" :class="{ alerta: evento.ocupacao >= 90 }">
                    {{
                      (evento.capacidadeAutorizada - evento.reservasConfirmadas).toLocaleString()
                    }}
                    pessoas
                  </span>
                </div>
              </div>

              <div class="ocupacao-container">
                <div class="ocupacao-header">
                  <span>Taxa de Ocupação</span>
                  <span class="ocupacao-percentual">{{ evento.ocupacao }}%</span>
                </div>
                <div class="progress-bar">
                  <div
                    class="progress-fill"
                    :style="{ width: evento.ocupacao + '%' }"
                    :class="{ 'progress-alerta': evento.ocupacao >= 90 }"
                  ></div>
                </div>
              </div>

              <div class="espacos-list">
                <div v-for="espaco in evento.espacos" :key="espaco" class="espaco-tag">
                  {{ espaco }}
                </div>
              </div>
            </div>

            <div class="info-card">
              <h3 class="card-title">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <circle cx="12" cy="12" r="10" />
                  <path d="M12 6v6l4 2" />
                </svg>
                Reservas por Setor
              </h3>
              <div class="setores-list">
                <div v-for="setor in evento.reservasPorSetor" :key="setor.nome" class="setor-item">
                  <div class="setor-info">
                    <span class="setor-nome">{{ setor.nome }}</span>
                    <span class="setor-quantidade"
                      >{{ setor.quantidade.toLocaleString() }} /
                      {{ setor.capacidade.toLocaleString() }}</span
                    >
                  </div>
                  <div class="setor-bar">
                    <div
                      class="setor-fill"
                      :style="{ width: (setor.quantidade / setor.capacidade) * 100 + '%' }"
                    ></div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="coluna-direita">
            <div class="info-card">
              <h3 class="card-title">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <circle cx="12" cy="12" r="10" />
                  <path d="M8 12h8M12 8v8" />
                </svg>
                Financeiro
              </h3>
              <div class="financeiro-grid">
                <div class="financeiro-item">
                  <span class="financeiro-label">Receita da Arena</span>
                  <span class="financeiro-valor">{{ formatCurrency(evento.receitaArena) }}</span>
                </div>
                <div class="financeiro-item">
                  <span class="financeiro-label">Receita com Ingressos</span>
                  <span class="financeiro-valor">{{
                    formatCurrency(evento.receitaIngressos)
                  }}</span>
                </div>
                <div class="financeiro-item total">
                  <span class="financeiro-label">Receita Total Estimada</span>
                  <span class="financeiro-valor total">{{
                    formatCurrency(evento.receitaTotal)
                  }}</span>
                </div>
              </div>
            </div>

            <div class="info-card">
              <h3 class="card-title">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <circle cx="12" cy="12" r="10" />
                  <polygon points="12 2 15 9 22 9 16 14 19 22 12 17 5 22 8 14 2 9 9 9 12 2" />
                </svg>
                Logística e Alertas
              </h3>
              <div class="alertas-list">
                <div
                  v-for="alerta in alertas"
                  :key="alerta.id"
                  class="alerta-item"
                  :class="alerta.tipo"
                >
                  <svg
                    width="14"
                    height="14"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <circle cx="12" cy="12" r="10" />
                    <path d="M12 8v4M12 16h.01" />
                  </svg>
                  <span>{{ alerta.mensagem }}</span>
                </div>
              </div>
              <div class="observacoes">
                <label>Observações Administrativas</label>
                <textarea
                  v-model="evento.observacoes"
                  rows="3"
                  placeholder="Adicione observações sobre o evento..."
                ></textarea>
              </div>
            </div>

            <div class="acoes-card">
              <h3 class="card-title">Ações</h3>
              <div class="acoes-buttons">
                <button
                  v-if="evento.status === 'AGENDADO'"
                  class="btn-acao finalizar"
                  @click="finalizarEvento"
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
                  Finalizar Evento
                </button>
                <button
                  v-if="evento.status !== 'CANCELADO' && evento.status !== 'FINALIZADO'"
                  class="btn-acao cancelar"
                  @click="abrirModalCancelar"
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
                  Cancelar Evento
                </button>
              </div>
            </div>
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
            Tem certeza que deseja cancelar o evento <strong>{{ evento.nome }}</strong
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
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'

const route = useRoute()
const router = useRouter()
const isSidebarOpen = ref(false)
const modalCancelarAberto = ref(false)
const motivoCancelamento = ref('')

const evento = ref({
  id: 1,
  nome: 'Rock Nacional 2026',
  produtor: 'Recife Eventos LTDA',
  categoria: 'Show',
  data: '2026-08-15',
  horario: '20:00',
  descricao:
    'O maior festival de rock do Nordeste com bandas nacionais e internacionais. Estrutura completa com palco principal, camarotes e área VIP.',
  status: 'AGENDADO',
  capacidadeAutorizada: 25000,
  publicoEstimado: 30000,
  reservasConfirmadas: 22500,
  ocupacao: 90,
  espacos: ['Campo', 'Anel Inferior', 'Camarote'],
  receitaArena: 85000,
  receitaIngressos: 450000,
  receitaTotal: 535000,
  observacoes: 'Evento com grande expectativa de público. Reforçar segurança.',
  reservasPorSetor: [
    { nome: 'Campo', quantidade: 15000, capacidade: 18000 },
    { nome: 'Anel Inferior', quantidade: 6000, capacidade: 7000 },
    { nome: 'Camarote', quantidade: 1500, capacidade: 2000 },
  ],
})

const alertas = computed(() => {
  const alerts = []
  if (evento.value.ocupacao >= 90) {
    alerts.push({
      id: 1,
      tipo: 'alerta',
      mensagem: 'Evento próximo da capacidade máxima! Reforçar segurança e logística.',
    })
  }
  if (evento.value.publicoEstimado > evento.value.capacidadeAutorizada) {
    alerts.push({
      id: 2,
      tipo: 'critico',
      mensagem: 'Público estimado excede a capacidade autorizada!',
    })
  }
  return alerts
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
  return new Date(data).toLocaleDateString('pt-BR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
  })
}

function formatCurrency(value: number) {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(value)
}

function finalizarEvento() {
  evento.value.status = 'FINALIZADO'
  alert(`Evento "${evento.value.nome}" finalizado com sucesso!`)
  router.push('/admin/eventos')
}

function abrirModalCancelar() {
  modalCancelarAberto.value = true
}

function fecharModalCancelar() {
  modalCancelarAberto.value = false
  motivoCancelamento.value = ''
}

function confirmarCancelar() {
  if (!motivoCancelamento.value.trim()) {
    alert('Informe o motivo do cancelamento')
    return
  }

  evento.value.status = 'CANCELADO'
  alert(`Evento "${evento.value.nome}" cancelado com sucesso!\nMotivo: ${motivoCancelamento.value}`)
  fecharModalCancelar()
  router.push('/admin/eventos')
}

onMounted(() => {
  const id = route.params.id
  console.log('Carregando evento ID:', id)
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
  flex-wrap: wrap;
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
.evento-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 28px;
  flex-wrap: wrap;
  gap: 16px;
}
.evento-titulo {
  display: flex;
  align-items: center;
  gap: 16px;
  flex-wrap: wrap;
}
.evento-titulo h1 {
  font-family: 'Bebas Neue', sans-serif;
  font-size: 2rem;
  letter-spacing: 1px;
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin: 0;
}
.status-badge {
  padding: 6px 14px;
  border-radius: 20px;
  font-size: 0.75rem;
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
.btn-voltar {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.08);
  padding: 8px 16px;
  border-radius: 10px;
  color: #8e9aaf;
  font-size: 0.8rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s;
}
.btn-voltar:hover {
  background: #1e2638;
  color: #c9a84c;
}
.dashboard-grid {
  display: grid;
  grid-template-columns: 1fr 380px;
  gap: 24px;
}
.coluna-esquerda {
  display: flex;
  flex-direction: column;
  gap: 24px;
}
.coluna-direita {
  display: flex;
  flex-direction: column;
  gap: 24px;
}
.info-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 24px;
}
.card-title {
  font-size: 0.9rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 8px;
}
.info-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}
.info-item {
  display: flex;
  flex-direction: column;
  gap: 4px;
}
.info-item label {
  font-size: 0.7rem;
  color: #6b7280;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}
.info-item span {
  font-size: 0.9rem;
  color: #e0e0e0;
}
.descricao {
  line-height: 1.5;
}
.categoria-tag {
  display: inline-block;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 600;
  width: fit-content;
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
.capacidade-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
  margin-bottom: 20px;
}
.capacidade-item {
  display: flex;
  flex-direction: column;
  gap: 4px;
}
.capacidade-label {
  font-size: 0.7rem;
  color: #6b7280;
  text-transform: uppercase;
}
.capacidade-valor {
  font-size: 1rem;
  font-weight: 700;
  color: #e0e0e0;
}
.capacidade-valor.alerta {
  color: #ef4444;
}
.ocupacao-container {
  margin-bottom: 20px;
}
.ocupacao-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
  font-size: 0.8rem;
  color: #b0b8c5;
}
.ocupacao-percentual {
  font-weight: 700;
  color: #c9a84c;
}
.progress-bar {
  height: 8px;
  background: #1a2233;
  border-radius: 10px;
  overflow: hidden;
}
.progress-fill {
  height: 100%;
  background: linear-gradient(90deg, #c9a84c, #d4af37);
  border-radius: 10px;
  transition: width 0.3s ease;
}
.progress-fill.progress-alerta {
  background: linear-gradient(90deg, #ef4444, #dc2626);
}
.espacos-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.espaco-tag {
  background: rgba(201, 168, 76, 0.1);
  border: 1px solid rgba(201, 168, 76, 0.2);
  padding: 6px 14px;
  border-radius: 20px;
  font-size: 0.8rem;
  color: #c9a84c;
}
.setores-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}
.setor-item {
  width: 100%;
}
.setor-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 6px;
  font-size: 0.8rem;
}
.setor-nome {
  color: #e0e0e0;
}
.setor-quantidade {
  color: #8e9aaf;
}
.setor-bar {
  height: 6px;
  background: #1a2233;
  border-radius: 10px;
  overflow: hidden;
}
.setor-fill {
  height: 100%;
  background: linear-gradient(90deg, #c9a84c, #d4af37);
  border-radius: 10px;
  transition: width 0.3s ease;
}
.financeiro-grid {
  display: flex;
  flex-direction: column;
  gap: 16px;
}
.financeiro-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}
.financeiro-item.total {
  border-bottom: none;
  padding-top: 16px;
  margin-top: 8px;
  border-top: 2px solid rgba(201, 168, 76, 0.3);
}
.financeiro-label {
  font-size: 0.85rem;
  color: #8e9aaf;
}
.financeiro-valor {
  font-size: 1rem;
  font-weight: 700;
  color: #c9a84c;
}
.financeiro-valor.total {
  font-size: 1.2rem;
  color: #c9a84c;
}
.alertas-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 20px;
}
.alerta-item {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 12px;
  border-radius: 12px;
  font-size: 0.85rem;
}
.alerta-item.alerta {
  background: rgba(245, 158, 11, 0.1);
  border-left: 3px solid #f59e0b;
  color: #f59e0b;
}
.alerta-item.critico {
  background: rgba(239, 68, 68, 0.1);
  border-left: 3px solid #ef4444;
  color: #ef4444;
}
.observacoes {
  display: flex;
  flex-direction: column;
  gap: 8px;
}
.observacoes label {
  font-size: 0.75rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
}
.observacoes textarea {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 12px;
  color: white;
  font-size: 0.85rem;
  font-family: inherit;
  resize: vertical;
  outline: none;
}
.observacoes textarea:focus {
  border-color: #c9a84c;
}
.acoes-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 24px;
}
.acoes-buttons {
  display: flex;
  gap: 12px;
  flex-direction: column;
}
.btn-acao {
  padding: 12px;
  border-radius: 12px;
  font-weight: 700;
  font-size: 0.9rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  transition: all 0.3s ease;
  border: none;
}
.btn-acao.finalizar {
  background: linear-gradient(135deg, #10b981, #059669);
  color: white;
}
.btn-acao.finalizar:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(16, 185, 129, 0.3);
}
.btn-acao.cancelar {
  background: rgba(239, 68, 68, 0.15);
  border: 1px solid rgba(239, 68, 68, 0.3);
  color: #ef4444;
}
.btn-acao.cancelar:hover {
  background: rgba(239, 68, 68, 0.25);
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
  .dashboard-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 600px) {
  .evento-titulo {
    flex-direction: column;
    align-items: flex-start;
  }
  .info-grid {
    grid-template-columns: 1fr;
  }
  .capacidade-grid {
    grid-template-columns: 1fr;
  }
}
</style>
