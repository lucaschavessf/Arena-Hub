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

        <router-link to="/admin/solicitacoes" class="nav-item active">
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
          <span class="breadcrumb-current">Solicitações de Eventos</span>
        </div>

        <header class="content-header">
          <div class="header-titles">
            <h1>Solicitações de Eventos</h1>
            <p>Gerencie as solicitações enviadas pelos produtores</p>
          </div>
          <div class="header-actions">
            <div class="filter-tabs">
              <button
                v-for="tab in tabs"
                :key="tab.value"
                class="filter-tab"
                :class="{ active: filtroStatus === tab.value }"
                @click="filtroStatus = tab.value"
              >
                {{ tab.label }}
                <span class="tab-count">{{ contarPorStatus(tab.value) }}</span>
              </button>
            </div>
          </div>
        </header>

        <section class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon pendentes">
              <svg
                width="24"
                height="24"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <circle cx="12" cy="12" r="10" />
                <path d="M12 6v6l4 2" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-value">{{ stats.pendentes }}</span>
              <span class="stat-label">Pendentes</span>
            </div>
          </div>
          <div class="stat-card">
            <div class="stat-icon aprovadas">
              <svg
                width="24"
                height="24"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <polyline points="20 6 9 17 4 12" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-value">{{ stats.aprovadas }}</span>
              <span class="stat-label">Aprovadas</span>
            </div>
          </div>
          <div class="stat-card">
            <div class="stat-icon rejeitadas">
              <svg
                width="24"
                height="24"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <line x1="18" y1="6" x2="6" y2="18" />
                <line x1="6" y1="6" x2="18" y2="18" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-value">{{ stats.rejeitadas }}</span>
              <span class="stat-label">Rejeitadas</span>
            </div>
          </div>
          <div class="stat-card">
            <div class="stat-icon total">
              <svg
                width="24"
                height="24"
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
            </div>
            <div class="stat-info">
              <span class="stat-value">{{ stats.total }}</span>
              <span class="stat-label">Total</span>
            </div>
          </div>
        </section>

        <div class="filters-container">
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
            <input v-model="termoBusca" type="text" placeholder="Buscar por nome do evento..." />
          </div>
          <select v-model="filtroCategoria" class="filter-select">
            <option value="">Todas as categorias</option>
            <option v-for="cat in categoriasUnicas" :key="cat" :value="cat">{{ cat }}</option>
          </select>
        </div>

        <div v-if="carregando" class="loading-container">
          <div class="spinner"></div>
          <p>Carregando solicitações...</p>
        </div>

        <div v-else-if="solicitacoesFiltradas.length === 0" class="empty-state">
          <svg
            width="80"
            height="80"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="1.5"
          >
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z" />
            <polyline points="14 2 14 8 20 8" />
            <path d="M9 15l2 2 4-4" />
          </svg>
          <h3>Nenhuma solicitação encontrada</h3>
          <p>Tente ajustar os filtros ou o termo de busca.</p>
        </div>

        <div v-else class="solicitacoes-list-strip">
          <div
            v-for="solicitacao in solicitacoesFiltradas"
            :key="solicitacao.id"
            class="solicitacao-strip-card"
          >
            <div class="strip-left">
              <div class="event-strip-icon">
                <svg
                  width="20"
                  height="20"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path
                    d="M4 20h16a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-7.5L9 3H4a2 2 0 0 0-2 2v13a2 2 0 0 0 2 2z"
                  />
                </svg>
              </div>
              <div class="title-block">
                <div class="title-row">
                  <h3>{{ solicitacao.nome }}</h3>
                  <span class="event-id">#{{ solicitacao.id }}</span>
                </div>
                <p class="subtitle-sent">
                  Enviado em {{ formatarData(solicitacao.dataInicio).split(',')[0] }}
                </p>
              </div>
            </div>

            <div class="strip-metadata">
              <div class="meta-item">
                <svg
                  width="16"
                  height="16"
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
                <span>{{ formatarData(solicitacao.dataInicio).split(' ')[0] }}</span>
              </div>

              <div class="meta-item">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2" />
                  <circle cx="9" cy="7" r="4" />
                  <path d="M23 21v-2a4 4 0 0 0-3-3.87" />
                  <path d="M16 3.13a4 4 0 0 1 0 7.75" />
                </svg>
                <span>{{ (solicitacao.expectativaPublico || 0).toLocaleString() }} pessoas</span>
              </div>

              <div class="meta-item">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <rect x="3" y="3" width="18" height="18" rx="2" />
                  <path d="M9 3v18M15 3v18M3 9h18M3 15h18" />
                </svg>
                <span>{{ solicitacao.espacoNome || 'Estacionamento' }}</span>
              </div>
            </div>

            <div class="strip-actions">
              <button class="btn-view-strip" @click="abrirDetalhes(solicitacao)">
                Ver detalhes
              </button>

              <div v-if="solicitacao.status === 'PENDENTE'" class="action-buttons-strip">
                <button
                  class="btn-icon-reject"
                  @click="abrirModalRejeitar(solicitacao)"
                  title="Rejeitar Solicitação"
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
                </button>
                <button
                  class="btn-icon-approve"
                  @click="aprovarSolicitacao(solicitacao)"
                  title="Aprovar Solicitação"
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
                </button>
              </div>

              <div class="card-status-strip" :class="getStatusClass(solicitacao.status)">
                {{ getStatusText(solicitacao.status) }}
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>

    <div v-if="modalDetalhes" class="modal-overlay" @click.self="fecharModalDetalhes">
      <div class="modal-detalhes">
        <div class="modal-header">
          <h2>{{ solicitacaoAtual?.nome }}</h2>
          <button class="modal-close" @click="fecharModalDetalhes">
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
          <div class="info-section">
            <h4>Informações do Evento</h4>
            <div class="info-grid">
              <div>
                <span>Nome:</span> <strong>{{ solicitacaoAtual?.nome }}</strong>
              </div>
              <div>
                <span>Categoria:</span>
                <strong>{{ solicitacaoAtual?.categoriaNome || 'Não informada' }}</strong>
              </div>
              <div>
                <span>Classificação:</span>
                <strong>{{ solicitacaoAtual?.classificacaoIndicativa || 'Livre' }}</strong>
              </div>
              <div>
                <span>Descrição:</span>
                <strong>{{ solicitacaoAtual?.descricao || 'Sem descrição' }}</strong>
              </div>
            </div>
          </div>

          <div class="info-section">
            <h4>Data e Local</h4>
            <div class="info-grid">
              <div>
                <span>Data Início:</span>
                <strong>{{ formatarData(solicitacaoAtual?.dataInicio) }}</strong>
              </div>
              <div>
                <span>Data Fim:</span>
                <strong>{{ formatarData(solicitacaoAtual?.dataFim) }}</strong>
              </div>
              <div>
                <span>Espaço:</span>
                <strong>{{ solicitacaoAtual?.espacoNome || 'Não informado' }}</strong>
              </div>
              <div>
                <span>Público:</span>
                <strong
                  >{{
                    (solicitacaoAtual?.expectativaPublico || 0).toLocaleString()
                  }}
                  pessoas</strong
                >
              </div>
            </div>
          </div>

          <div class="info-section" v-if="solicitacaoAtual?.lotes?.length">
            <h4>Lotes de Ingressos</h4>
            <div class="lotes-list">
              <div v-for="lote in solicitacaoAtual.lotes" :key="lote.id" class="lote-item">
                <div><span>Nome:</span> {{ lote.nome || 'Lote' }}</div>
                <div><span>Preço:</span> R$ {{ (lote.preco || 0).toFixed(2) }}</div>
                <div><span>Quantidade:</span> {{ lote.quantidade || 0 }}</div>
                <div><span>Disponível:</span> {{ lote.disponivel || 0 }}</div>
              </div>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button class="btn-close" @click="fecharModalDetalhes">Fechar</button>
        </div>
      </div>
    </div>

    <div v-if="modalRejeitar" class="modal-overlay" @click.self="fecharModalRejeitar">
      <div class="modal-rejeitar">
        <div class="modal-header">
          <h3>Rejeitar Solicitação</h3>
          <button class="modal-close" @click="fecharModalRejeitar">
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
          <div class="form-group">
            <label>Motivo da rejeição</label>
            <select v-model="motivoRejeicao" class="form-select">
              <option value="">Selecione um motivo</option>
              <option value="Data indisponível">Data indisponível</option>
              <option value="Público estimado acima da capacidade">
                Público estimado acima da capacidade
              </option>
              <option value="Informações insuficientes">Informações insuficientes</option>
              <option value="Evento incompatível com os espaços">
                Evento incompatível com os espaços
              </option>
              <option value="Documentação pendente">Documentação pendente</option>
              <option value="Não atende aos critérios da Arena">
                Não atende aos critérios da Arena
              </option>
            </select>
          </div>
          <div class="form-group">
            <label>Observações (opcional)</label>
            <textarea
              v-model="observacoesRejeicao"
              rows="3"
              placeholder="Detalhe o motivo da rejeição..."
            ></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn-cancel" @click="fecharModalRejeitar">Cancelar</button>
          <button class="btn-confirm" @click="confirmarRejeicao">Confirmar</button>
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
import AppNavbar from '../components/AppNavbar.vue'
import api from '../services/api'

// Estado
const isSidebarOpen = ref(false)
const carregando = ref(false)
const filtroStatus = ref('todos')
const filtroCategoria = ref('')
const termoBusca = ref('')
const modalDetalhes = ref(false)
const modalRejeitar = ref(false)
const solicitacaoAtual = ref<any>(null)
const solicitacaoParaRejeitar = ref<any>(null)
const motivoRejeicao = ref('')
const observacoesRejeicao = ref('')

// Dados
const solicitacoes = ref<any[]>([])

// Tabs
const tabs = [
  { label: 'Todas', value: 'todos' },
  { label: 'Pendentes', value: 'PENDENTE' },
  { label: 'Aprovadas', value: 'APROVADO' },
  { label: 'Rejeitadas', value: 'REJEITADO' },
]

// Computed
const stats = computed(() => ({
  pendentes: solicitacoes.value.filter((s) => s.status === 'PENDENTE').length,
  aprovadas: solicitacoes.value.filter((s) => s.status === 'APROVADO').length,
  rejeitadas: solicitacoes.value.filter((s) => s.status === 'REJEITADO').length,
  total: solicitacoes.value.length,
}))

const categoriasUnicas = computed(() => {
  const cats = solicitacoes.value.map((s) => s.categoriaNome).filter(Boolean)
  return [...new Set(cats)]
})

const solicitacoesFiltradas = computed(() => {
  return solicitacoes.value.filter((s) => {
    const matchStatus = filtroStatus.value === 'todos' || s.status === filtroStatus.value
    const matchCategoria = !filtroCategoria.value || s.categoriaNome === filtroCategoria.value
    const matchBusca =
      !termoBusca.value ||
      s.nome?.toLowerCase().includes(termoBusca.value.toLowerCase()) ||
      s.categoriaNome?.toLowerCase().includes(termoBusca.value.toLowerCase())
    return matchStatus && matchCategoria && matchBusca
  })
})

// Funções auxiliares
function formatarData(data: string): string {
  if (!data) return 'Não definida'
  return new Date(data).toLocaleDateString('pt-BR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  })
}

function truncarTexto(texto: string, tamanho: number): string {
  if (!texto) return ''
  if (texto.length <= tamanho) return texto
  return texto.substring(0, tamanho) + '...'
}

function getStatusClass(status: string): string {
  const map: Record<string, string> = {
    PENDENTE: 'status-pendente',
    APROVADO: 'status-aprovado',
    REJEITADO: 'status-rejeitado',
  }
  return map[status] || ''
}

function getStatusText(status: string): string {
  const map: Record<string, string> = {
    PENDENTE: 'Pendente',
    APROVADO: 'Aprovado',
    REJEITADO: 'Rejeitado',
  }
  return map[status] || status
}

function contarPorStatus(status: string): number {
  if (status === 'todos') return solicitacoes.value.length
  return solicitacoes.value.filter((s) => s.status === status).length
}

// API Calls
async function carregarSolicitacoes() {
  carregando.value = true
  try {
    const response = await api.get('/api/eventos')
    if (Array.isArray(response.data)) {
      solicitacoes.value = response.data
    } else {
      solicitacoes.value = []
    }
  } catch (error) {
    console.error('Erro ao carregar solicitações:', error)
    solicitacoes.value = []
  } finally {
    carregando.value = false
  }
}

// Ações
function abrirDetalhes(solicitacao: any) {
  solicitacaoAtual.value = solicitacao
  modalDetalhes.value = true
}

function fecharModalDetalhes() {
  modalDetalhes.value = false
  solicitacaoAtual.value = null
}

async function aprovarSolicitacao(solicitacao: any) {
  if (!confirm(`Deseja aprovar o evento "${solicitacao.nome}"?`)) return

  try {
    await api.post(`/api/eventos/${solicitacao.id}/aprovar`)
    alert('Solicitação aprovada com sucesso!')
    await carregarSolicitacoes()
  } catch (error: any) {
    console.error('Erro ao aprovar:', error)
    alert(error.response?.data?.message || 'Erro ao aprovar solicitação')
  }
}

function abrirModalRejeitar(solicitacao: any) {
  solicitacaoParaRejeitar.value = solicitacao
  motivoRejeicao.value = ''
  observacoesRejeicao.value = ''
  modalRejeitar.value = true
}

function fecharModalRejeitar() {
  modalRejeitar.value = false
  solicitacaoParaRejeitar.value = null
}

async function confirmarRejeicao() {
  if (!motivoRejeicao.value) {
    alert('Selecione um motivo para a rejeição')
    return
  }

  try {
    await api.post(`/api/eventos/${solicitacaoParaRejeitar.value.id}/rejeitar`, {
      motivo: `${motivoRejeicao.value}${observacoesRejeicao.value ? ` - ${observacoesRejeicao.value}` : ''}`,
    })
    alert('Solicitação rejeitada com sucesso!')
    await carregarSolicitacoes()
    fecharModalRejeitar()
  } catch (error: any) {
    console.error('Erro ao rejeitar:', error)
    alert(error.response?.data?.message || 'Erro ao rejeitar solicitação')
  }
}

// Lifecycle
onMounted(() => {
  carregarSolicitacoes()
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

.content-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 28px;
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

.filter-tabs {
  display: flex;
  gap: 8px;
  background: #1a2233;
  padding: 6px;
  border-radius: 12px;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.filter-tab {
  padding: 8px 20px;
  background: transparent;
  border: none;
  color: #8e9aaf;
  font-size: 0.85rem;
  font-weight: 600;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  gap: 8px;
}

.filter-tab:hover {
  color: #c9a84c;
}

.filter-tab.active {
  background: #c9a84c;
  color: #0a0e17;
}

.tab-count {
  background: rgba(0, 0, 0, 0.2);
  padding: 2px 8px;
  border-radius: 20px;
  font-size: 0.7rem;
}

.filter-tab.active .tab-count {
  background: rgba(10, 14, 23, 0.2);
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  margin-bottom: 32px;
}

.stat-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 16px;
  padding: 20px;
  display: flex;
  align-items: center;
  gap: 16px;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.stat-icon {
  width: 52px;
  height: 52px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.stat-icon.pendentes {
  background: rgba(245, 158, 11, 0.15);
}
.stat-icon.pendentes svg {
  color: #f59e0b;
}
.stat-icon.aprovadas {
  background: rgba(16, 185, 129, 0.15);
}
.stat-icon.aprovadas svg {
  color: #10b981;
}
.stat-icon.rejeitadas {
  background: rgba(239, 68, 68, 0.15);
}
.stat-icon.rejeitadas svg {
  color: #ef4444;
}
.stat-icon.total {
  background: rgba(201, 168, 76, 0.15);
}
.stat-icon.total svg {
  color: #c9a84c;
}

.stat-info {
  text-align: center;
}

.stat-value {
  font-size: 2rem;
  font-weight: 800;
  display: block;
}

.stat-label {
  font-size: 0.7rem;
  color: #8e9aaf;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.filters-container {
  display: flex;
  gap: 16px;
  margin-bottom: 24px;
  flex-wrap: wrap;
}

.search-box {
  flex: 1;
  display: flex;
  align-items: center;
  gap: 12px;
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 12px 18px;
}

.search-box svg {
  color: #6b7280;
}

.search-box input {
  background: transparent;
  border: none;
  color: white;
  font-size: 0.9rem;
  width: 100%;
  outline: none;
}

.search-box input::placeholder {
  color: #5a6375;
}

.filter-select {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 12px 36px 12px 18px;
  color: white;
  font-size: 0.9rem;
  cursor: pointer;
  outline: none;
  appearance: none;
  background-image: url("data:image/svg+xml,%3Csvg width='14' height='14' viewBox='0 0 24 24' fill='none' stroke='%23c9a84c' stroke-width='2.5'%3E%3Cpolyline points='6 9 12 15 18 9'/%3E%3Csvg%3E");
  background-repeat: no-repeat;
  background-position: right 14px center;
  min-width: 180px;
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

.empty-state {
  text-align: center;
  padding: 60px;
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
}

.empty-state svg {
  color: #c9a84c;
  opacity: 3;
  margin-bottom: 20px;
}

.empty-state h3 {
  font-size: 1.2rem;
  margin-bottom: 8px;
}

.empty-state p {
  color: #6b7280;
}

/* ==========================================================================
   ESTILIZAÇÃO DO NOVO GRID EM FORMATO DE LINHAS DE SOLICITAÇÃO (STRIPS)
   ========================================================================== */

.solicitacoes-list-strip {
  display: flex;
  flex-direction: column;
  gap: 12px;
  width: 100%;
}

.solicitacao-strip-card {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #0d121f; /* Fundo escuro idêntico à imagem enviada */
  border: 1px solid rgba(255, 255, 255, 0.04);
  border-radius: 12px;
  padding: 16px 24px;
  transition: all 0.25s cubic-bezier(0.4, 0, 0.2, 1);
  gap: 24px;
}

.solicitacao-strip-card:hover {
  border-color: rgba(201, 168, 76, 0.25);
  background: #111827;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
}

/* Esquerda: Agrupamento de Título e Ícone */
.strip-left {
  display: flex;
  align-items: center;
  gap: 16px;
  min-width: 260px;
}

.event-strip-icon {
  width: 44px;
  height: 44px;
  background: rgba(255, 255, 255, 0.02);
  border: 1px solid rgba(201, 168, 76, 0.15);
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #c9a84c;
}

.title-block {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.title-row {
  display: flex;
  align-items: center;
  gap: 8px;
}

.title-block h3 {
  font-size: 1rem;
  font-weight: 700;
  color: #ffffff;
  margin: 0;
}

.subtitle-sent {
  font-size: 0.8rem;
  color: #6b7280;
  margin: 0;
}

/* Centro: Metadados Horizontais */
.strip-metadata {
  display: flex;
  align-items: center;
  gap: 32px;
  flex: 1;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #9ca3af;
  font-size: 0.85rem;
  white-space: nowrap;
}

.meta-item svg {
  color: #5a6375;
}

/* Direita: Botões e Posicionamento do Status */
.strip-actions {
  display: flex;
  align-items: center;
  gap: 14px;
  position: relative;
  padding-right: 120px; /* Alocação estruturada para o badge dinâmico à direita */
}

.btn-view-strip {
  background: rgba(201, 168, 76, 0.08);
  border: 1px solid rgba(201, 168, 76, 0.15);
  padding: 8px 16px;
  border-radius: 8px;
  color: #c9a84c;
  font-weight: 600;
  font-size: 0.85rem;
  cursor: pointer;
  transition: all 0.2s;
  white-space: nowrap;
}

.btn-view-strip:hover {
  background: rgba(201, 168, 76, 0.2);
  color: #d4af37;
}

.action-buttons-strip {
  display: flex;
  gap: 6px;
}

/* Ações Rápidas em formato de ícone compacto */
.btn-icon-reject,
.btn-icon-approve {
  width: 34px;
  height: 34px;
  border-radius: 8px;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.2s;
}

.btn-icon-reject {
  background: rgba(239, 68, 68, 0.1);
  color: #ef4444;
}
.btn-icon-reject:hover {
  background: rgba(239, 68, 68, 0.25);
}

.btn-icon-approve {
  background: rgba(16, 185, 129, 0.1);
  color: #10b981;
}
.btn-icon-approve:hover {
  background: rgba(16, 185, 129, 0.25);
}

/* Badge de Status fixo na lateral direita */
.card-status-strip {
  position: absolute;
  right: 0;
  padding: 5px 14px;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: 700;
  min-width: 90px;
  text-align: center;
}

.status-pendente {
  background: rgba(245, 158, 11, 0.06);
  color: #f59e0b;
  border: 1px solid rgba(245, 158, 11, 0.2);
}

.status-aprovado {
  background: rgba(16, 185, 129, 0.06);
  color: #10b981;
  border: 1px solid rgba(16, 185, 129, 0.2);
}

.status-rejeitado {
  background: rgba(239, 68, 68, 0.06);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.2);
}

/* ==========================================================================
   MODAIS E COMPONENTES GERAIS
   ========================================================================== */

.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.8);
  backdrop-filter: blur(8px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
  padding: 20px;
}

.modal-detalhes,
.modal-rejeitar {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(201, 168, 76, 0.15);
  border-radius: 24px;
  width: 100%;
  max-width: 600px;
  max-height: 85vh;
  overflow-y: auto;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  position: sticky;
  top: 0;
  background: inherit;
}

.modal-header h2,
.modal-header h3 {
  font-size: 1.2rem;
  font-weight: 700;
  color: #c9a84c;
  margin: 0;
}

.modal-close {
  background: transparent;
  border: none;
  color: #8e9aaf;
  cursor: pointer;
  transition: color 0.2s;
}

.modal-close:hover {
  color: #ef4444;
}

.modal-body {
  padding: 24px;
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.info-section h4 {
  font-size: 0.85rem;
  font-weight: 700;
  color: #c9a84c;
  margin-bottom: 12px;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.info-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 12px;
}

.info-grid div {
  display: flex;
  flex-direction: column;
  gap: 4px;
  font-size: 0.85rem;
}

.info-grid span {
  color: #6b7280;
  font-size: 0.7rem;
  text-transform: uppercase;
}

.info-grid strong {
  color: #e0e0e0;
  font-weight: 500;
}

.lotes-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.lote-item {
  background: rgba(255, 255, 255, 0.02);
  border-radius: 12px;
  padding: 12px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 8px;
  font-size: 0.8rem;
}

.modal-footer {
  padding: 16px 24px 24px;
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.btn-close,
.btn-cancel {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 10px 20px;
  border-radius: 8px;
  color: #8e9aaf;
  font-weight: 600;
  cursor: pointer;
}

.btn-confirm {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  color: #0a0e17;
  font-weight: 700;
  cursor: pointer;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-bottom: 16px;
}

.form-group label {
  font-size: 0.75rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
}

.form-select,
.form-group textarea {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 10px;
  padding: 12px;
  color: white;
  font-size: 0.9rem;
  outline: none;
}

.form-select:focus,
.form-group textarea:focus {
  border-color: #c9a84c;
}

.form-group textarea {
  resize: vertical;
  font-family: inherit;
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

/* ==========================================================================
   REGRAS DE RESPONSIVIDADE (MEDIA QUERIES)
   ========================================================================== */

@media (max-width: 1200px) {
  .strip-metadata {
    gap: 16px;
  }
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

  /* Conversão de linha para bloco em tablets */
  .solicitacao-strip-card {
    flex-direction: column;
    align-items: flex-start;
    gap: 16px;
    padding: 20px;
  }
  .strip-metadata {
    width: 100%;
    justify-content: flex-start;
  }
  .strip-actions {
    width: 100%;
    justify-content: flex-start;
    padding-right: 0;
  }
  .card-status-strip {
    position: static;
  }
}

@media (max-width: 768px) {
  .stats-grid {
    grid-template-columns: 1fr;
  }
  .filters-container {
    flex-direction: column;
  }
  .filter-select {
    width: 100%;
  }
  .strip-metadata {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px;
  }
  .strip-actions {
    flex-wrap: wrap;
    gap: 12px;
  }
  .info-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 600px) {
  .filter-tabs {
    flex-wrap: wrap;
  }
  .strip-left {
    min-width: 100%;
  }
}
</style>