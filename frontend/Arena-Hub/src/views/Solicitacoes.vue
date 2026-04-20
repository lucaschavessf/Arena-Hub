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
        
        <router-link to="/admin/agenda" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
          </svg>
          <span>Agenda</span>
        </router-link>

        <router-link to="/admin/solicitacoes" class="nav-item active">
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
          <span class="breadcrumb-current">Solicitações</span>
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
                :class="{ active: filtroAtivo === tab.value }"
                @click="filtroAtivo = tab.value"
              >
                {{ tab.label }}
                <span class="tab-count">{{ contarPorStatus(tab.value) }}</span>
              </button>
            </div>
          </div>
        </header>

        <!-- Stats Cards -->
        <section class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon" style="background: rgba(245, 158, 11, 0.1);">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#f59e0b" stroke-width="2">
                <circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Pendentes</span>
              <div class="stat-value">{{ solicitacoesPendentes.length }}</div>
            </div>
          </div>
          
          <div class="stat-card">
            <div class="stat-icon" style="background: rgba(139, 92, 246, 0.1);">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#8b5cf6" stroke-width="2">
                <circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Em Análise</span>
              <div class="stat-value">{{ solicitacoesEmAnalise.length }}</div>
            </div>
          </div>
          
          <div class="stat-card">
            <div class="stat-icon" style="background: rgba(16, 185, 129, 0.1);">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#10b981" stroke-width="2.5">
                <polyline points="20 6 9 17 4 12"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Aprovadas (Mês)</span>
              <div class="stat-value">{{ solicitacoesAprovadas.length }}</div>
            </div>
          </div>
          
          <div class="stat-card">
            <div class="stat-icon" style="background: rgba(239, 68, 68, 0.1);">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#ef4444" stroke-width="2.5">
                <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Rejeitadas</span>
              <div class="stat-value">{{ solicitacoesRejeitadas.length }}</div>
            </div>
          </div>
        </section>

        <!-- Barra de busca e filtros -->
        <div class="search-bar-container">
          <div class="search-wrapper">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/>
            </svg>
            <input 
              v-model="termoBusca" 
              type="text" 
              placeholder="Buscar por solicitante, evento ou e-mail..." 
            />
          </div>
          
          <select v-model="filtroCategoria" class="filter-select">
            <option value="">Todas as categorias</option>
            <option value="Show">Show</option>
            <option value="Esportes">Esportes</option>
            <option value="Corporativo">Corporativo</option>
            <option value="Teatro">Teatro</option>
            <option value="Comédia">Comédia</option>
          </select>
        </div>

        <!-- Tabela de Solicitações -->
        <section class="card table-card">
          <div class="table-wrapper">
            <table class="admin-table">
              <thead>
                <tr>
                  <th>Solicitante</th>
                  <th>Evento</th>
                  <th>Categoria</th>
                  <th>Data Solicitada</th>
                  <th>Data do Evento</th>
                  <th>Status</th>
                  <th>Ações</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="solicitacao in solicitacoesFiltradas" :key="solicitacao.id">
                  <td>
                    <div class="cliente-info">
                      <div class="cliente-avatar">{{ solicitacao.solicitante.charAt(0) }}</div>
                      <div>
                        <span class="cliente-nome">{{ solicitacao.solicitante }}</span>
                        <span class="cliente-email">{{ solicitacao.email }}</span>
                      </div>
                    </div>
                  </td>
                  <td>
                    <span class="evento-nome">{{ solicitacao.nomeEvento }}</span>
                  </td>
                  <td>
                    <span class="categoria-badge" :class="getCategoriaClass(solicitacao.categoria)">
                      {{ solicitacao.categoria }}
                    </span>
                  </td>
                  <td>{{ formatarData(solicitacao.dataSolicitacao) }}</td>
                  <td>{{ formatarData(solicitacao.dataEvento) }}</td>
                  <td>
                    <span class="status-badge" :class="getStatusClass(solicitacao.status)">
                      <span class="status-dot"></span>
                      {{ solicitacao.status }}
                    </span>
                  </td>
                  <td>
                    <div class="acoes-cell">
                      <button class="acao-btn visualizar" @click="visualizarSolicitacao(solicitacao)" title="Visualizar">
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                          <circle cx="12" cy="12" r="3"/><path d="M22 12c-2.667 4.667-6 7-10 7s-7.333-2.333-10-7c2.667-4.667 6-7 10-7s7.333 2.333 10 7z"/>
                        </svg>
                      </button>
                      <button 
                        v-if="solicitacao.status === 'Pendente' || solicitacao.status === 'Em Análise'"
                        class="acao-btn aprovar" 
                        @click="aprovarSolicitacao(solicitacao)" 
                        title="Aprovar"
                      >
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                          <polyline points="20 6 9 17 4 12"/>
                        </svg>
                      </button>
                      <button 
                        v-if="solicitacao.status === 'Pendente' || solicitacao.status === 'Em Análise'"
                        class="acao-btn rejeitar" 
                        @click="rejeitarSolicitacao(solicitacao)" 
                        title="Rejeitar"
                      >
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                          <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
                        </svg>
                      </button>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

          <!-- Empty State -->
          <div v-if="solicitacoesFiltradas.length === 0" class="empty-state">
            <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
              <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/>
              <polyline points="14 2 14 8 20 8"/>
              <path d="M9 15h6M9 11h4"/>
            </svg>
            <h3>Nenhuma solicitação encontrada</h3>
            <p>Tente ajustar os filtros ou termo de busca.</p>
          </div>
        </section>
      </main>
    </div>

    <!-- Modal de Visualização -->
    <transition name="fade-scale">
      <div v-if="solicitacaoSelecionada" class="modal-overlay" @click.self="fecharModal">
        <div class="modal-card">
          <button class="modal-close" @click="fecharModal">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
            </svg>
          </button>
          
          <div class="modal-header">
            <div class="modal-icon" :class="getCategoriaClass(solicitacaoSelecionada.categoria)">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M4 20h16a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-7.5L9 3H4a2 2 0 0 0-2 2v13a2 2 0 0 0 2 2z"/>
              </svg>
            </div>
            <div class="modal-title-wrapper">
              <h2 class="modal-title">{{ solicitacaoSelecionada.nomeEvento }}</h2>
              <span class="modal-status" :class="getStatusClass(solicitacaoSelecionada.status)">
                {{ solicitacaoSelecionada.status }}
              </span>
            </div>
          </div>

          <div class="modal-body">
            <div class="modal-section">
              <h4 class="section-title">Informações do Solicitante</h4>
              <div class="info-grid">
                <div class="info-item">
                  <label>Nome Completo</label>
                  <p>{{ solicitacaoSelecionada.solicitante }}</p>
                </div>
                <div class="info-item">
                  <label>E-mail</label>
                  <p>{{ solicitacaoSelecionada.email }}</p>
                </div>
                <div class="info-item">
                  <label>WhatsApp</label>
                  <p>{{ solicitacaoSelecionada.whatsapp }}</p>
                </div>
                <div class="info-item">
                  <label>Instagram</label>
                  <p>{{ solicitacaoSelecionada.instagram || 'Não informado' }}</p>
                </div>
              </div>
            </div>

            <div class="modal-section">
              <h4 class="section-title">Detalhes do Evento</h4>
              <div class="info-grid">
                <div class="info-item">
                  <label>Categoria</label>
                  <p>{{ solicitacaoSelecionada.categoria }}</p>
                </div>
                <div class="info-item">
                  <label>Data do Evento</label>
                  <p>{{ formatarData(solicitacaoSelecionada.dataEvento) }}</p>
                </div>
                <div class="info-item">
                  <label>Cidade</label>
                  <p>{{ solicitacaoSelecionada.cidade }}</p>
                </div>
                <div class="info-item">
                  <label>Estado</label>
                  <p>{{ solicitacaoSelecionada.estado }}</p>
                </div>
              </div>
            </div>

            <div class="modal-section">
              <h4 class="section-title">Descrição</h4>
              <p class="descricao-text">{{ solicitacaoSelecionada.descricao }}</p>
            </div>

            <div class="modal-section">
              <h4 class="section-title">Configurações</h4>
              <div class="config-item">
                <span>Menores Acompanhados</span>
                <span class="config-badge" :class="solicitacaoSelecionada.menoresAcompanhados ? 'permitido' : 'nao-permitido'">
                  {{ solicitacaoSelecionada.menoresAcompanhados ? 'Permitido' : 'Não permitido' }}
                </span>
              </div>
            </div>

            <div class="modal-section" v-if="solicitacaoSelecionada.observacoes">
              <h4 class="section-title">Observações Internas</h4>
              <textarea 
                v-model="solicitacaoSelecionada.observacoes" 
                placeholder="Adicione observações internas..."
                rows="3"
                class="observacoes-input"
              ></textarea>
            </div>
          </div>

          <div class="modal-footer">
            <template v-if="solicitacaoSelecionada.status === 'Pendente' || solicitacaoSelecionada.status === 'Em Análise'">
              <button class="btn-modal btn-rejeitar" @click="rejeitarSolicitacao(solicitacaoSelecionada)">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
                </svg>
                Rejeitar
              </button>
              <button class="btn-modal btn-aprovar" @click="aprovarSolicitacao(solicitacaoSelecionada)">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <polyline points="20 6 9 17 4 12"/>
                </svg>
                Aprovar
              </button>
            </template>
            <template v-else>
              <button class="btn-modal btn-fechar" @click="fecharModal">
                Fechar
              </button>
            </template>
          </div>
        </div>
      </div>
    </transition>

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
const filtroAtivo = ref('todos')
const filtroCategoria = ref('')
const termoBusca = ref('')
const solicitacaoSelecionada = ref<any>(null)

const tabs = [
  { label: 'Todas', value: 'todos' },
  { label: 'Pendentes', value: 'Pendente' },
  { label: 'Em Análise', value: 'Em Análise' },
  { label: 'Aprovadas', value: 'Aprovado' },
  { label: 'Rejeitadas', value: 'Rejeitado' }
]

// Dados mockados
const solicitacoes = ref([
  {
    id: 1,
    solicitante: 'Carlos Mendes',
    email: 'carlos@produtora.com',
    whatsapp: '(81) 99999-1111',
    nomeEvento: 'Festival de Jazz PE',
    categoria: 'Show',
    dataEvento: '2026-08-15',
    cidade: 'Recife',
    estado: 'PE',
    instagram: '@festivaljazzpe',
    descricao: 'Festival de jazz com artistas nacionais e internacionais, food park e área kids. Expectativa de público: 5.000 pessoas.',
    menoresAcompanhados: true,
    status: 'Pendente',
    dataSolicitacao: '2026-04-01',
    observacoes: ''
  },
  {
    id: 2,
    solicitante: 'Ana Oliveira',
    email: 'ana@eventosesportivos.com',
    whatsapp: '(81) 98888-2222',
    nomeEvento: 'Copa Nordeste de Vôlei',
    categoria: 'Esportes',
    dataEvento: '2026-07-20',
    cidade: 'Recife',
    estado: 'PE',
    instagram: '@copavoleinordeste',
    descricao: 'Competição regional de vôlei com equipes de todo o Nordeste. Estrutura necessária: quadra, arquibancadas e placar eletrônico.',
    menoresAcompanhados: true,
    status: 'Em Análise',
    dataSolicitacao: '2026-04-02',
    observacoes: 'Verificar disponibilidade de datas alternativas.'
  },
  {
    id: 3,
    solicitante: 'Tech Conference LTDA',
    email: 'contato@techconf.com',
    whatsapp: '(81) 97777-3333',
    nomeEvento: 'Recife Tech Summit 2026',
    categoria: 'Corporativo',
    dataEvento: '2026-09-10',
    cidade: 'Recife',
    estado: 'PE',
    instagram: '@recifetechsummit',
    descricao: 'Maior evento de tecnologia e inovação do Nordeste, com palestrantes internacionais e feira de startups.',
    menoresAcompanhados: false,
    status: 'Aprovado',
    dataSolicitacao: '2026-03-28',
    observacoes: 'Aprovado. Enviar contrato para assinatura.'
  },
  {
    id: 4,
    solicitante: 'Comédia Produções',
    email: 'contato@comediar.com',
    whatsapp: '(81) 96666-4444',
    nomeEvento: 'Noite do Riso',
    categoria: 'Comédia',
    dataEvento: '2026-06-05',
    cidade: 'Recife',
    estado: 'PE',
    instagram: '@noitedoriso',
    descricao: 'Show de stand-up comedy com 4 comediantes nacionais.',
    menoresAcompanhados: false,
    status: 'Rejeitado',
    dataSolicitacao: '2026-03-15',
    observacoes: 'Data indisponível. Sugerir nova data.'
  },
  {
    id: 5,
    solicitante: 'Festival Cultura Viva',
    email: 'cultura@viva.com',
    whatsapp: '(81) 95555-7777',
    nomeEvento: 'Festival Cultura Viva',
    categoria: 'Show',
    dataEvento: '2026-10-12',
    cidade: 'Recife',
    estado: 'PE',
    instagram: '@culturavivafest',
    descricao: 'Festival multicultural com música, dança, gastronomia e artesanato.',
    menoresAcompanhados: true,
    status: 'Pendente',
    dataSolicitacao: '2026-04-05',
    observacoes: ''
  }
])

const solicitacoesPendentes = computed(() => solicitacoes.value.filter(s => s.status === 'Pendente'))
const solicitacoesEmAnalise = computed(() => solicitacoes.value.filter(s => s.status === 'Em Análise'))
const solicitacoesAprovadas = computed(() => solicitacoes.value.filter(s => s.status === 'Aprovado'))
const solicitacoesRejeitadas = computed(() => solicitacoes.value.filter(s => s.status === 'Rejeitado'))

const contarPorStatus = (status: string) => {
  if (status === 'todos') return solicitacoes.value.length
  return solicitacoes.value.filter(s => s.status === status).length
}

const solicitacoesFiltradas = computed(() => {
  return solicitacoes.value.filter(s => {
    const matchStatus = filtroAtivo.value === 'todos' || s.status === filtroAtivo.value
    const matchCategoria = !filtroCategoria.value || s.categoria === filtroCategoria.value
    const matchBusca = !termoBusca.value || 
      s.solicitante.toLowerCase().includes(termoBusca.value.toLowerCase()) ||
      s.nomeEvento.toLowerCase().includes(termoBusca.value.toLowerCase()) ||
      s.email.toLowerCase().includes(termoBusca.value.toLowerCase())
    return matchStatus && matchCategoria && matchBusca
  })
})

const formatarData = (data: string) => {
  return new Date(data).toLocaleDateString('pt-BR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric'
  })
}

const getCategoriaClass = (categoria: string) => {
  const map: Record<string, string> = {
    'Show': 'categoria-show',
    'Esportes': 'categoria-esportes',
    'Corporativo': 'categoria-corporativo',
    'Teatro': 'categoria-teatro',
    'Comédia': 'categoria-comedia'
  }
  return map[categoria] || ''
}

const getStatusClass = (status: string) => {
  const map: Record<string, string> = {
    'Pendente': 'status-pendente',
    'Em Análise': 'status-analise',
    'Aprovado': 'status-aprovado',
    'Rejeitado': 'status-rejeitado'
  }
  return map[status] || ''
}

const visualizarSolicitacao = (solicitacao: any) => {
  solicitacaoSelecionada.value = { ...solicitacao }
}

const fecharModal = () => {
  solicitacaoSelecionada.value = null
}

const aprovarSolicitacao = (solicitacao: any) => {
  const index = solicitacoes.value.findIndex(s => s.id === solicitacao.id)
  if (index !== -1) {
    solicitacoes.value[index].status = 'Aprovado'
  }
  fecharModal()
}

const rejeitarSolicitacao = (solicitacao: any) => {
  const index = solicitacoes.value.findIndex(s => s.id === solicitacao.id)
  if (index !== -1) {
    solicitacoes.value[index].status = 'Rejeitado'
  }
  fecharModal()
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

.header-actions {
  display: flex;
  gap: 16px;
}

.filter-tabs {
  display: flex;
  gap: 4px;
  background: #1a2233;
  padding: 4px;
  border-radius: 12px;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.filter-tab {
  padding: 8px 16px;
  background: transparent;
  border: none;
  color: #8e9aaf;
  font-size: 0.8rem;
  font-weight: 600;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  gap: 6px;
  white-space: nowrap;
}

.filter-tab:hover {
  color: #c9a84c;
}

.filter-tab.active {
  background: #c9a84c;
  color: #0a0e17;
}

.tab-count {
  background: rgba(255, 255, 255, 0.1);
  padding: 2px 6px;
  border-radius: 10px;
  font-size: 0.7rem;
}

.filter-tab.active .tab-count {
  background: rgba(10, 14, 23, 0.2);
}

/* Stats Cards */
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
  font-size: 1.8rem; 
  font-weight: 800; 
  line-height: 1.2;
}

/* Barra de Busca */
.search-bar-container {
  display: flex;
  gap: 16px;
  margin-bottom: 24px;
}

.search-wrapper {
  flex: 1;
  display: flex;
  align-items: center;
  gap: 12px;
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 12px 18px;
  transition: all 0.3s ease;
}

.search-wrapper:focus-within {
  border-color: #c9a84c;
  box-shadow: 0 0 0 3px rgba(201, 168, 76, 0.08);
}

.search-wrapper svg {
  color: #6b7280;
  flex-shrink: 0;
}

.search-wrapper input {
  background: transparent;
  border: none;
  color: white;
  font-size: 0.95rem;
  width: 100%;
  outline: none;
}

.search-wrapper input::placeholder {
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
}

/* Tabela */
.card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  padding: 24px;
}

.table-card {
  padding: 0;
  overflow: hidden;
}

.table-wrapper {
  overflow-x: auto;
}

.admin-table {
  width: 100%;
  border-collapse: collapse;
}

.admin-table th {
  text-align: left;
  padding: 16px 16px 14px;
  font-size: 0.7rem;
  color: #6b7280;
  text-transform: uppercase;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  font-weight: 700;
  letter-spacing: 0.5px;
  background: rgba(0, 0, 0, 0.2);
}

.admin-table td {
  padding: 16px;
  font-size: 0.85rem;
  border-bottom: 1px solid rgba(255, 255, 255, 0.02);
  color: #b0b8c5;
}

.cliente-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.cliente-avatar {
  width: 36px;
  height: 36px;
  border-radius: 10px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 700;
  font-size: 0.9rem;
  flex-shrink: 0;
}

.cliente-nome {
  display: block;
  color: #e0e0e0;
  font-weight: 500;
  margin-bottom: 2px;
}

.cliente-email {
  display: block;
  font-size: 0.7rem;
  color: #6b7280;
}

.evento-nome {
  font-weight: 500;
  color: #e0e0e0;
}

.categoria-badge {
  display: inline-block;
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.3px;
}

.categoria-show {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
  border: 1px solid rgba(201, 168, 76, 0.3);
}

.categoria-esportes {
  background: rgba(76, 201, 142, 0.15);
  color: #4cc98e;
  border: 1px solid rgba(76, 201, 142, 0.3);
}

.categoria-corporativo {
  background: rgba(59, 130, 246, 0.15);
  color: #3b82f6;
  border: 1px solid rgba(59, 130, 246, 0.3);
}

.categoria-teatro {
  background: rgba(139, 92, 246, 0.15);
  color: #8b5cf6;
  border: 1px solid rgba(139, 92, 246, 0.3);
}

.categoria-comedia {
  background: rgba(236, 72, 153, 0.15);
  color: #ec4899;
  border: 1px solid rgba(236, 72, 153, 0.3);
}

.status-badge {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  padding: 5px 10px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 700;
}

.status-dot {
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background: currentColor;
}

.status-pendente {
  background: rgba(245, 158, 11, 0.15);
  color: #f59e0b;
  border: 1px solid rgba(245, 158, 11, 0.3);
}

.status-analise {
  background: rgba(139, 92, 246, 0.15);
  color: #8b5cf6;
  border: 1px solid rgba(139, 92, 246, 0.3);
}

.status-aprovado {
  background: rgba(16, 185, 129, 0.15);
  color: #10b981;
  border: 1px solid rgba(16, 185, 129, 0.3);
}

.status-rejeitado {
  background: rgba(239, 68, 68, 0.15);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}

/* Ações */
.acoes-cell {
  display: flex;
  gap: 6px;
}

.acao-btn {
  width: 34px;
  height: 34px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.2s;
  border: none;
  background: transparent;
  color: #6b7280;
}

.acao-btn.visualizar:hover {
  color: #3b82f6;
  background: rgba(59, 130, 246, 0.1);
}

.acao-btn.aprovar:hover {
  color: #10b981;
  background: rgba(16, 185, 129, 0.1);
}

.acao-btn.rejeitar:hover {
  color: #ef4444;
  background: rgba(239, 68, 68, 0.1);
}

/* Empty State */
.empty-state {
  text-align: center;
  padding: 60px 20px;
  color: #6b7280;
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

/* Modal */
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(10, 14, 23, 0.9);
  backdrop-filter: blur(8px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
  padding: 20px;
}

.modal-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(201, 168, 76, 0.15);
  border-radius: 24px;
  width: 100%;
  max-width: 650px;
  max-height: 85vh;
  overflow-y: auto;
  position: relative;
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.5);
}

.modal-close {
  position: absolute;
  top: 20px;
  right: 20px;
  width: 36px;
  height: 36px;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.05);
  border: none;
  color: #8e9aaf;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.2s;
  z-index: 10;
}

.modal-close:hover {
  background: #ef4444;
  color: white;
}

.modal-header {
  padding: 28px 28px 20px;
  display: flex;
  align-items: flex-start;
  gap: 16px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.modal-icon {
  width: 52px;
  height: 52px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.modal-icon.categoria-show {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
}

.modal-title-wrapper {
  flex: 1;
}

.modal-title {
  font-size: 1.4rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 6px;
}

.modal-status {
  display: inline-block;
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 700;
}

.modal-body {
  padding: 24px 28px;
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.modal-section {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.section-title {
  font-size: 0.8rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.8px;
  margin: 0;
}

.info-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
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
  letter-spacing: 0.3px;
}

.info-item p {
  color: #e0e0e0;
  font-size: 0.95rem;
  margin: 0;
}

.descricao-text {
  color: #b0b8c5;
  font-size: 0.95rem;
  line-height: 1.7;
  margin: 0;
  padding: 14px;
  background: rgba(255, 255, 255, 0.03);
  border-radius: 12px;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.config-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0;
  color: #e0e0e0;
}

.config-badge {
  padding: 5px 12px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 700;
}

.config-badge.permitido {
  background: rgba(16, 185, 129, 0.15);
  color: #10b981;
  border: 1px solid rgba(16, 185, 129, 0.3);
}

.config-badge.nao-permitido {
  background: rgba(239, 68, 68, 0.15);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}

.observacoes-input {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 14px;
  color: white;
  font-size: 0.9rem;
  outline: none;
  resize: vertical;
  font-family: inherit;
}

.observacoes-input:focus {
  border-color: #c9a84c;
}

.modal-footer {
  padding: 20px 28px 28px;
  display: flex;
  gap: 12px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.btn-modal {
  flex: 1;
  padding: 14px;
  border-radius: 12px;
  font-weight: 700;
  font-size: 0.9rem;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  cursor: pointer;
  transition: all 0.3s;
  border: none;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.btn-aprovar {
  background: linear-gradient(135deg, #10b981, #059669);
  color: white;
}

.btn-aprovar:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(16, 185, 129, 0.3);
}

.btn-rejeitar {
  background: transparent;
  border: 1.5px solid rgba(239, 68, 68, 0.3);
  color: #ef4444;
}

.btn-rejeitar:hover {
  background: rgba(239, 68, 68, 0.1);
  border-color: #ef4444;
}

.btn-fechar {
  background: #1a2233;
  color: #e0e0e0;
  border: 1px solid rgba(255, 255, 255, 0.1);
}

.btn-fechar:hover {
  background: #1e2638;
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

/* Animações */
.fade-scale-enter-active,
.fade-scale-leave-active {
  transition: all 0.3s ease;
}

.fade-scale-enter-from,
.fade-scale-leave-to {
  opacity: 0;
  transform: scale(0.95);
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
  
  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }
  
  .content-header {
    flex-direction: column;
    align-items: flex-start;
  }
  
  .filter-tabs {
    flex-wrap: wrap;
  }
}

@media (max-width: 600px) {
  .stats-grid {
    grid-template-columns: 1fr;
  }
  
  .search-bar-container {
    flex-direction: column;
  }
  
  .header-titles h1 {
    font-size: 1.6rem;
  }
  
  .modal-card {
    max-width: 100%;
  }
  
  .modal-header {
    padding: 20px 20px 16px;
  }
  
  .modal-body {
    padding: 20px;
  }
  
  .modal-footer {
    padding: 16px 20px 24px;
    flex-direction: column;
  }
  
  .info-grid {
    grid-template-columns: 1fr;
  }
  
  .acoes-cell {
    flex-wrap: wrap;
  }
}
</style>