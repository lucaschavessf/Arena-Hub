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

        <router-link to="/admin/produtores" class="nav-item active">
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
          <span class="breadcrumb-current">Produtores</span>
        </div>

        <header class="content-header">
          <div class="header-titles">
            <h1>Gestão de Produtores</h1>
            <p>Gerencie as solicitações de cadastro e produtores ativos</p>
          </div>
        </header>

        <section class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon pendentes">
              <svg
                width="22"
                height="22"
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
              <span class="stat-label">Solicitações</span>
              <div class="stat-value">{{ stats.solicitacoes }}</div>
              <span class="stat-sub">Aguardando análise</span>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon aprovados">
              <svg
                width="22"
                height="22"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <polyline points="20 6 9 17 4 12" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Produtores Ativos</span>
              <div class="stat-value">{{ stats.ativos }}</div>
              <span class="stat-sub">Parceiros ativos</span>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon eventos">
              <svg
                width="22"
                height="22"
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
              <span class="stat-label">Eventos Realizados</span>
              <div class="stat-value">{{ stats.eventos }}</div>
              <span class="stat-sub">Total de eventos</span>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon publico">
              <svg
                width="22"
                height="22"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z" />
                <circle cx="12" cy="12" r="3" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Público Total</span>
              <div class="stat-value">{{ stats.publico }}</div>
              <span class="stat-sub">Espectadores</span>
            </div>
          </div>
        </section>

        <div class="tabs-container">
          <button
            class="tab-btn"
            :class="{ active: tabAtivo === 'solicitacoes' }"
            @click="tabAtivo = 'solicitacoes'"
          >
            <svg
              width="16"
              height="16"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
            >
              <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z" />
              <polyline points="14 2 14 8 20 8" />
            </svg>
            Solicitações Pendentes
            <span class="tab-count">{{ solicitacoesPendentes.length }}</span>
          </button>
          <button
            class="tab-btn"
            :class="{ active: tabAtivo === 'ativos' }"
            @click="tabAtivo = 'ativos'"
          >
            <svg
              width="16"
              height="16"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
            >
              <polyline points="20 6 9 17 4 12" />
            </svg>
            Produtores Ativos
            <span class="tab-count">{{ produtoresAtivos.length }}</span>
          </button>
          <button
            class="tab-btn"
            :class="{ active: tabAtivo === 'reprovados' }"
            @click="tabAtivo = 'reprovados'"
          >
            <svg
              width="16"
              height="16"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
            >
              <line x1="18" y1="6" x2="6" y2="18" />
              <line x1="6" y1="6" x2="18" y2="18" />
            </svg>
            Reprovados
            <span class="tab-count">{{ solicitacoesReprovadas.length }}</span>
          </button>
        </div>

        <div v-if="tabAtivo === 'solicitacoes'" class="solicitacoes-list">
          <div
            v-for="solicitacao in solicitacoesPendentes"
            :key="solicitacao.id"
            class="solicitacao-card"
          >
            <div class="solicitacao-header">
              <div class="solicitacao-avatar">
                <span class="avatar-iniciais">{{ solicitacao.nome.charAt(0) }}</span>
              </div>
              <div class="solicitacao-info">
                <h3>{{ solicitacao.nome }}</h3>
                <p>{{ solicitacao.email }}</p>
              </div>
              <div class="solicitacao-data">
                <span>{{ formatarData(solicitacao.dataSolicitacao) }}</span>
              </div>
            </div>

            <div class="solicitacao-detalhes">
              <div class="detalhe-item">
                <span>Empresa: </span>
                <strong>{{ solicitacao.empresa || 'Não informada' }}</strong>
              </div>
            </div>

            <div class="solicitacao-footer">
              <button class="btn-ver" @click="verDetalhes(solicitacao)">
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
                Ver detalhes
              </button>
              <div class="acoes">
                <button class="btn-reprovar" @click="abrirModalReprovar(solicitacao)">
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
                  Reprovar
                </button>
                <button class="btn-aprovar" @click="aprovarSolicitacao(solicitacao)">
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
                  Aprovar
                </button>
              </div>
            </div>
          </div>

          <div v-if="solicitacoesPendentes.length === 0" class="empty-state">
            <svg
              width="64"
              height="64"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="1.5"
            >
              <circle cx="12" cy="12" r="10" />
              <path d="M12 8v4M12 16h.01" />
            </svg>
            <h3>Nenhuma solicitação pendente</h3>
            <p>Todos os produtores foram processados</p>
          </div>
        </div>

        <div v-if="tabAtivo === 'ativos'" class="produtores-list">
          <div v-for="produtor in produtoresAtivos" :key="produtor.id" class="produtor-card">
            <div class="produtor-header">
              <div class="produtor-avatar">
                <span class="avatar-iniciais">{{ produtor.nome.charAt(0) }}</span>
              </div>
              <div class="produtor-info">
                <h3>{{ produtor.nome }}</h3>
                <p>{{ produtor.email }}</p>
              </div>
              <div class="produtor-status ativo">
                <span class="status-dot"></span>
                Ativo
              </div>
            </div>

            <div class="produtor-stats">
              <div class="stat">
                <span class="stat-value">{{ produtor.eventosRealizados }}</span>
                <span class="stat-label">Eventos</span>
              </div>
              <div class="stat">
                <span class="stat-value">{{ produtor.publicoTotal.toLocaleString() }}</span>
                <span class="stat-label">Público</span>
              </div>
              <div class="stat">
                <span class="stat-value">{{ formatarData(produtor.dataCadastro) }}</span>
                <span class="stat-label">Desde</span>
              </div>
            </div>

            <div class="produtor-footer">
              <button class="btn-ver" @click="verDetalhesProdutor(produtor)">Ver detalhes</button>
              <button class="btn-bloquear" @click="bloquearProdutor(produtor)">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <circle cx="12" cy="12" r="10" />
                  <line x1="4.93" y1="4.93" x2="19.07" y2="19.07" />
                </svg>
                Bloquear
              </button>
            </div>
          </div>

          <div v-if="produtoresAtivos.length === 0" class="empty-state">
            <svg
              width="64"
              height="64"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="1.5"
            >
              <circle cx="12" cy="12" r="10" />
              <path d="M12 8v4M12 16h.01" />
            </svg>
            <h3>Nenhum produtor ativo</h3>
            <p>Aprove solicitações para adicionar produtores</p>
          </div>
        </div>

        <div v-if="tabAtivo === 'reprovados'" class="reprovados-list">
          <div
            v-for="solicitacao in solicitacoesReprovadas"
            :key="solicitacao.id"
            class="solicitacao-card reprovada"
          >
            <div class="solicitacao-header">
              <div class="solicitacao-avatar">
                <span class="avatar-iniciais">{{ solicitacao.nome.charAt(0) }}</span>
              </div>
              <div class="solicitacao-info">
                <h3>{{ solicitacao.nome }}</h3>
                <p>{{ solicitacao.email }}</p>
              </div>
              <div class="solicitacao-data">
                <span>{{ formatarData(solicitacao.dataSolicitacao) }}</span>
              </div>
            </div>

            <div class="motivo-reprovacao">
              <span>Motivo da reprovação:</span>
              <p>{{ solicitacao.motivoReprovacao }}</p>
            </div>

            <div class="solicitacao-footer">
              <button class="btn-ver" @click="verDetalhes(solicitacao)">Ver detalhes</button>
              <button class="btn-reenviar" @click="reenviarSolicitacao(solicitacao)">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z" />
                  <circle cx="12" cy="12" r="3" />
                </svg>
                Solicitar reanálise
              </button>
            </div>
          </div>

          <div v-if="solicitacoesReprovadas.length === 0" class="empty-state">
            <svg
              width="64"
              height="64"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="1.5"
            >
              <circle cx="12" cy="12" r="10" />
              <path d="M12 8v4M12 16h.01" />
            </svg>
            <h3>Nenhuma solicitação reprovada</h3>
            <p>Todas as solicitações foram aprovadas</p>
          </div>
        </div>
      </main>
    </div>

    <div v-if="modalDetalhes" class="modal-overlay" @click.self="fecharModalDetalhes">
      <div class="modal-container modal-large">
        <div class="modal-header">
          <h3>Detalhes do Produtor</h3>
          <button class="modal-fechar" @click="fecharModalDetalhes">
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
            <h4>Informações Pessoais</h4>
            <div class="info-grid">
              <div><strong>Nome:</strong> {{ solicitacaoSelecionada?.nome }}</div>
              <div><strong>E-mail:</strong> {{ solicitacaoSelecionada?.email }}</div>
              <div><strong>Telefone:</strong> {{ solicitacaoSelecionada?.telefone }}</div>
              <div>
                <strong>WhatsApp:</strong> {{ solicitacaoSelecionada?.whatsapp || 'Não informado' }}
              </div>
            </div>
          </div>

          <div class="info-section">
            <h4>Dados da Empresa</h4>
            <div class="info-grid">
              <div><strong>Razão Social:</strong> {{ solicitacaoSelecionada?.razaoSocial }}</div>
              <div>
                <strong>Nome Fantasia:</strong>
                {{ solicitacaoSelecionada?.nomeFantasia || 'Não informado' }}
              </div>
              <div><strong>CNPJ:</strong> {{ solicitacaoSelecionada?.cnpj }}</div>
              <div>
                <strong>Inscrição Estadual:</strong>
                {{ solicitacaoSelecionada?.inscricaoEstadual || 'Não informada' }}
              </div>
            </div>
          </div>

          <div class="info-section">
            <h4>Experiência</h4>
            <div>
              <strong>Anos de experiência:</strong> {{ solicitacaoSelecionada?.anosExperiencia }}
            </div>
            <div><strong>Tipos de evento:</strong></div>
            <div class="tipos-container">
              <span
                v-for="tipo in solicitacaoSelecionada?.tiposEvento"
                :key="tipo"
                class="tipo-badge"
              >
                {{ tipo }}
              </span>
            </div>
          </div>

          <div class="info-section">
            <h4>Portfólio</h4>
            <p>{{ solicitacaoSelecionada?.portfolio || 'Não informado' }}</p>
          </div>

          <div class="info-section">
            <h4>Motivação</h4>
            <p>{{ solicitacaoSelecionada?.motivacao || 'Não informado' }}</p>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn-modal-fechar" @click="fecharModalDetalhes">Fechar</button>
        </div>
      </div>
    </div>

    <div v-if="modalReprovar" class="modal-overlay" @click.self="fecharModalReprovar">
      <div class="modal-container">
        <div class="modal-header">
          <h3>Reprovar Solicitação</h3>
          <button class="modal-fechar" @click="fecharModalReprovar">
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
          <div class="form-field">
            <label>Motivo da reprovação *</label>
            <select v-model="motivoReprovacao" class="input-field">
              <option value="">Selecione um motivo...</option>
              <option value="Documentação incompleta">Documentação incompleta</option>
              <option value="CNPJ inválido ou irregular">CNPJ inválido ou irregular</option>
              <option value="Experiência insuficiente">Experiência insuficiente</option>
              <option value="Portfólio não atende aos requisitos">
                Portfólio não atende aos requisitos
              </option>
              <option value="Informações inconsistentes">Informações inconsistentes</option>
              <option value="Não atende aos critérios da Arena">
                Não atende aos critérios da Arena
              </option>
            </select>
          </div>
          <div class="form-field">
            <label>Observações adicionais</label>
            <textarea
              v-model="observacoesReprovacao"
              rows="3"
              class="input-field"
              placeholder="Detalhe o motivo da reprovação..."
            ></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn-modal-cancelar" @click="fecharModalReprovar">Cancelar</button>
          <button class="btn-modal-confirmar" @click="confirmarReprovacao">
            Confirmar Reprovação
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
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()
const isSidebarOpen = ref(false)
const tabAtivo = ref('solicitacoes')
const modalDetalhes = ref(false)
const modalReprovar = ref(false)
const solicitacaoSelecionada = ref<any>(null)
const solicitacaoParaReprovar = ref<any>(null)
const motivoReprovacao = ref('')
const observacoesReprovacao = ref('')

const stats = ref({
  solicitacoes: 3,
  ativos: 2,
  eventos: 28,
  publico: '125.5k',
})

const solicitacoesPendentes = ref([
  {
    id: 1,
    nome: 'João Silva',
    email: 'joao@produtora.com',
    telefone: '(81) 99999-1111',
    whatsapp: '(81) 98888-1111',
    empresa: 'Recife Eventos LTDA',
    cnpj: '12.345.678/0001-90',
    razaoSocial: 'Recife Eventos e Produções LTDA',
    nomeFantasia: 'Recife Eventos',
    inscricaoEstadual: '123456789',
    anosExperiencia: '5-10',
    tiposEvento: ['Show', 'Esportes', 'Corporativo'],
    portfolio: 'Festival de Jazz PE, Copa Nordeste de Vôlei',
    motivacao: 'Quero levar entretenimento de qualidade para o público pernambucano',
    dataSolicitacao: '2026-04-01',
    status: 'pendente',
  },
  {
    id: 2,
    nome: 'Ana Oliveira',
    email: 'ana@festivais.com',
    telefone: '(81) 97777-2222',
    whatsapp: '(81) 97777-2222',
    empresa: 'Festivais Nordeste',
    cnpj: '98.765.432/0001-11',
    razaoSocial: 'Festivais Nordeste Produções',
    nomeFantasia: 'Fest Nordeste',
    anosExperiencia: '3-5',
    tiposEvento: ['Show', 'Comédia'],
    portfolio: 'Festival Nordestino de Música',
    motivacao: 'Acredito no potencial cultural do Nordeste',
    dataSolicitacao: '2026-04-05',
    status: 'pendente',
  },
  {
    id: 3,
    nome: 'Tech Conference',
    email: 'contato@techconf.com',
    telefone: '(81) 98888-3333',
    empresa: 'Tech Conference Brasil',
    cnpj: '11.222.333/0001-44',
    razaoSocial: 'Tech Conference Eventos',
    anosExperiencia: '1-3',
    tiposEvento: ['Corporativo'],
    portfolio: 'Recife Tech Summit 2025',
    motivacao: 'Conectar profissionais de tecnologia',
    dataSolicitacao: '2026-04-08',
    status: 'pendente',
  },
])

const produtoresAtivos = ref([
  {
    id: 1,
    nome: 'Carlos Mendes',
    email: 'carlos@producoes.com',
    telefone: '(81) 99999-4444',
    empresa: 'Mendes Produções',
    eventosRealizados: 12,
    publicoTotal: 45000,
    dataCadastro: '2025-01-15',
    status: 'ativo',
  },
  {
    id: 2,
    nome: 'Sport Promotion',
    email: 'contato@sportpromotion.com',
    telefone: '(81) 95555-5555',
    empresa: 'Sport Promotion LTDA',
    eventosRealizados: 16,
    publicoTotal: 80500,
    dataCadastro: '2025-03-20',
    status: 'ativo',
  },
])

const solicitacoesReprovadas = ref([
  {
    id: 4,
    nome: 'Maria Santos',
    email: 'maria@eventos.com',
    telefone: '(81) 96666-6666',
    empresa: 'Santos Produções',
    cnpj: '55.666.777/0001-88',
    anosExperiencia: 'Menos de 1 ano',
    tiposEvento: ['Show'],
    portfolio: 'Eventos pequenos',
    motivacao: 'Quero produzir eventos',
    dataSolicitacao: '2026-03-20',
    status: 'reprovado',
    motivoReprovacao: 'Documentação incompleta - CNPJ irregular',
  },
])

function formatarData(data: string) {
  if (!data) return ''
  return new Date(data).toLocaleDateString('pt-BR')
}

function verDetalhes(solicitacao: any) {
  solicitacaoSelecionada.value = solicitacao
  modalDetalhes.value = true
}

function verDetalhesProdutor(produtor: any) {
  alert(`Detalhes do produtor ${produtor.nome} - Em desenvolvimento`)
}

function fecharModalDetalhes() {
  modalDetalhes.value = false
  solicitacaoSelecionada.value = null
}

function abrirModalReprovar(solicitacao: any) {
  solicitacaoParaReprovar.value = solicitacao
  motivoReprovacao.value = ''
  observacoesReprovacao.value = ''
  modalReprovar.value = true
}

function fecharModalReprovar() {
  modalReprovar.value = false
  solicitacaoParaReprovar.value = null
}

function confirmarReprovacao() {
  if (!motivoReprovacao.value) {
    alert('Selecione um motivo para a reprovação')
    return
  }

  const index = solicitacoesPendentes.value.findIndex(
    (s) => s.id === solicitacaoParaReprovar.value.id,
  )
  if (index !== -1) {
    const item = solicitacoesPendentes.value[index]
    solicitacoesPendentes.value.splice(index, 1)
    solicitacoesReprovadas.value.unshift({
      ...item,
      status: 'reprovado',
      motivoReprovacao: `${motivoReprovacao.value}${observacoesReprovacao.value ? ` - ${observacoesReprovacao.value}` : ''}`,
    })
  }

  stats.value.solicitacoes = solicitacoesPendentes.value.length
  fecharModalReprovar()
  alert('Solicitação reprovada com sucesso!')
}

function aprovarSolicitacao(solicitacao: any) {
  const index = solicitacoesPendentes.value.findIndex((s) => s.id === solicitacao.id)
  if (index !== -1) {
    solicitacoesPendentes.value.splice(index, 1)
    produtoresAtivos.value.unshift({
      id: Date.now(),
      nome: solicitacao.nome,
      email: solicitacao.email,
      telefone: solicitacao.telefone,
      empresa: solicitacao.empresa,
      eventosRealizados: 0,
      publicoTotal: 0,
      dataCadastro: new Date().toISOString().split('T')[0],
      status: 'ativo',
    })
  }

  stats.value.solicitacoes = solicitacoesPendentes.value.length
  stats.value.ativos = produtoresAtivos.value.length
  alert(`Produtor ${solicitacao.nome} aprovado com sucesso!`)
}

function bloquearProdutor(produtor: any) {
  if (confirm(`Deseja bloquear o produtor "${produtor.nome}"?`)) {
    const index = produtoresAtivos.value.findIndex((p) => p.id === produtor.id)
    if (index !== -1) {
      produtoresAtivos.value.splice(index, 1)
      stats.value.ativos = produtoresAtivos.value.length
      alert(`Produtor ${produtor.nome} bloqueado com sucesso!`)
    }
  }
}

function reenviarSolicitacao(solicitacao: any) {
  alert(`Solicitação reenviada para o produtor ${solicitacao.nome}. Ele poderá corrigir os dados.`)
}

onMounted(() => {
  window.scrollTo(0, 0)
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

.stats-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  margin-bottom: 32px;
}

.stat-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  padding: 20px;
  border-radius: 16px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  display: flex;
  align-items: center;
  gap: 14px;
  transition: all 0.3s;
}

.stat-card:hover {
  transform: translateY(-3px);
  border-color: rgba(201, 168, 76, 0.15);
}

.stat-icon {
  width: 48px;
  height: 48px;
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
.stat-icon.aprovados {
  background: rgba(16, 185, 129, 0.15);
}
.stat-icon.aprovados svg {
  color: #10b981;
}
.stat-icon.eventos {
  background: rgba(59, 130, 246, 0.15);
}
.stat-icon.eventos svg {
  color: #3b82f6;
}
.stat-icon.publico {
  background: rgba(201, 168, 76, 0.15);
}
.stat-icon.publico svg {
  color: #c9a84c;
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

.stat-sub {
  font-size: 0.65rem;
  color: #6b7280;
  display: block;
}

.tabs-container {
  display: flex;
  gap: 8px;
  margin-bottom: 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  padding-bottom: 12px;
}

.tab-btn {
  background: transparent;
  border: none;
  padding: 10px 20px;
  color: #8e9aaf;
  font-size: 0.85rem;
  font-weight: 600;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  border-radius: 10px;
  transition: all 0.3s;
}

.tab-btn:hover {
  color: #c9a84c;
  background: rgba(201, 168, 76, 0.05);
}

.tab-btn.active {
  color: #c9a84c;
  background: rgba(201, 168, 76, 0.1);
  border-bottom: 2px solid #c9a84c;
}

.tab-count {
  background: rgba(255, 255, 255, 0.1);
  padding: 2px 6px;
  border-radius: 10px;
  font-size: 0.7rem;
}

.solicitacao-card,
.produtor-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 16px;
  padding: 20px;
  margin-bottom: 16px;
  transition: all 0.3s;
}

.solicitacao-card:hover,
.produtor-card:hover {
  transform: translateY(-2px);
  border-color: rgba(201, 168, 76, 0.15);
}

.solicitacao-header,
.produtor-header {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 16px;
}

.solicitacao-avatar,
.produtor-avatar {
  width: 50px;
  height: 50px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.avatar-iniciais {
  font-size: 1.2rem;
  font-weight: 800;
  color: #0a0e17;
}

.solicitacao-info h3,
.produtor-info h3 {
  font-size: 1rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 4px;
}

.solicitacao-info p,
.produtor-info p {
  font-size: 0.8rem;
  color: #8e9aaf;
}

.solicitacao-data {
  margin-left: auto;
  font-size: 0.75rem;
  color: #6b7280;
}

.solicitacao-detalhes {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
  margin-bottom: 12px;
  padding: 12px 0;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.detalhe-item {
  font-size: 0.8rem;
  color: #b0b8c5;
}

.detalhe-item strong {
  color: #fff;
}

.solicitacao-tipos {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-bottom: 12px;
}

.tipo-badge {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 600;
}

.solicitacao-portfolio p {
  font-size: 0.8rem;
  color: #b0b8c5;
  margin: 8px 0;
}

.solicitacao-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 16px;
}

.acoes {
  display: flex;
  gap: 12px;
}

.btn-ver,
.btn-reprovar,
.btn-aprovar,
.btn-bloquear,
.btn-reenviar {
  padding: 8px 16px;
  border-radius: 8px;
  font-weight: 600;
  font-size: 0.8rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  transition: all 0.3s;
}

.btn-ver {
  background: rgba(201, 168, 76, 0.15);
  border: none;
  color: #c9a84c;
}

.btn-ver:hover {
  background: rgba(201, 168, 76, 0.25);
}

.btn-reprovar {
  background: rgba(239, 68, 68, 0.15);
  border: 1px solid rgba(239, 68, 68, 0.3);
  color: #ef4444;
}

.btn-reprovar:hover {
  background: rgba(239, 68, 68, 0.25);
}

.btn-aprovar {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  color: #0a0e17;
}

.btn-aprovar:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.3);
}

.btn-bloquear {
  background: rgba(239, 68, 68, 0.15);
  border: 1px solid rgba(239, 68, 68, 0.3);
  color: #ef4444;
}

.btn-bloquear:hover {
  background: rgba(239, 68, 68, 0.25);
}

.btn-reenviar {
  background: rgba(59, 130, 246, 0.15);
  border: 1px solid rgba(59, 130, 246, 0.3);
  color: #3b82f6;
}

.btn-reenviar:hover {
  background: rgba(59, 130, 246, 0.25);
}

.produtor-status {
  margin-left: auto;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 700;
  display: flex;
  align-items: center;
  gap: 6px;
}

.produtor-status.ativo {
  background: rgba(16, 185, 129, 0.15);
  color: #10b981;
  border: 1px solid rgba(16, 185, 129, 0.3);
}

.status-dot {
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background: #10b981;
}

.produtor-stats {
  display: flex;
  gap: 24px;
  padding: 16px 0;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  margin-bottom: 16px;
}

.produtor-stats .stat {
  text-align: center;
}

.produtor-stats .stat-value {
  display: block;
  font-size: 1rem;
  font-weight: 700;
  color: #c9a84c;
}

.produtor-stats .stat-label {
  font-size: 0.65rem;
  color: #8e9aaf;
}

.produtor-footer {
  display: flex;
  gap: 12px;
}

.motivo-reprovacao {
  background: rgba(239, 68, 68, 0.1);
  padding: 12px;
  border-radius: 8px;
  margin: 12px 0;
  border-left: 3px solid #ef4444;
}

.motivo-reprovacao span {
  font-size: 0.75rem;
  font-weight: 700;
  color: #ef4444;
  display: block;
  margin-bottom: 4px;
}

.motivo-reprovacao p {
  font-size: 0.85rem;
  color: #ef4444;
  margin: 0;
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
  margin-bottom: 16px;
}

.empty-state h3 {
  font-size: 1.1rem;
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
  max-width: 700px;
  max-height: 85vh;
  overflow-y: auto;
}

.modal-large {
  max-width: 800px;
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
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  padding: 20px 24px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.btn-modal-cancelar,
.btn-modal-fechar {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 10px 20px;
  border-radius: 10px;
  color: #8e9aaf;
  font-weight: 600;
  cursor: pointer;
}

.btn-modal-confirmar {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 10px 20px;
  border-radius: 10px;
  color: #0a0e17;
  font-weight: 700;
  cursor: pointer;
  transition: all 0.3s;
}

.btn-modal-confirmar:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.3);
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
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.info-grid div {
  font-size: 0.85rem;
  color: #b0b8c5;
}

.info-grid strong {
  color: #e0e0e0;
}

.tipos-container {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-top: 8px;
}

.form-field {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-bottom: 16px;
}

.form-field label {
  font-size: 0.75rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.input-field,
textarea.input-field {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 10px;
  padding: 10px 14px;
  color: white;
  font-size: 0.85rem;
  outline: none;
  transition: all 0.3s;
}

.input-field:focus,
textarea.input-field:focus {
  border-color: #c9a84c;
  box-shadow: 0 0 0 3px rgba(201, 168, 76, 0.1);
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

  .solicitacao-detalhes {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .stats-grid {
    grid-template-columns: 1fr;
  }

  .solicitacao-footer {
    flex-direction: column;
    gap: 12px;
  }

  .acoes {
    width: 100%;
  }

  .btn-reprovar,
  .btn-aprovar {
    flex: 1;
    justify-content: center;
  }

  .info-grid {
    grid-template-columns: 1fr;
  }

  .produtor-stats {
    flex-direction: column;
    gap: 12px;
  }

  .produtor-footer {
    flex-direction: column;
  }

  .solicitacao-header,
  .produtor-header {
    flex-wrap: wrap;
  }

  .solicitacao-data {
    margin-left: 0;
  }
}
</style>