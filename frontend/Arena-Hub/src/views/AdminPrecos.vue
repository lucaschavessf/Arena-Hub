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

        <router-link to="/admin/precos" class="nav-item active">
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
          <span class="breadcrumb-current">Preços dos Espaços</span>
        </div>

        <header class="content-header">
          <div class="header-titles">
            <h1>Gestão de Preços</h1>
            <p>Configure os valores e espaços da Arena Pernambuco</p>
          </div>
          <button class="btn-novo-espaco" @click="abrirModalNovoEspaco">
            <svg
              width="18"
              height="18"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <line x1="12" y1="5" x2="12" y2="19" />
              <line x1="5" y1="12" x2="19" y2="12" />
            </svg>
            Novo Espaço
          </button>
        </header>

        <section class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon total">
              <svg
                width="22"
                height="22"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <rect x="3" y="3" width="18" height="18" rx="2" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Total de Espaços</span>
              <div class="stat-value">{{ espacos.length }}</div>
            </div>
          </div>
          <div class="stat-card">
            <div class="stat-icon ativos">
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
              <span class="stat-label">Ativos</span>
              <div class="stat-value">{{ espacosAtivos.length }}</div>
            </div>
          </div>
          <div class="stat-card">
            <div class="stat-icon capacidade">
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
              <span class="stat-label">Capacidade Total</span>
              <div class="stat-value">{{ capacidadeTotal.toLocaleString() }}</div>
            </div>
          </div>
          <div class="stat-card">
            <div class="stat-icon receita">
              <svg
                width="22"
                height="22"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <circle cx="12" cy="12" r="10" />
                <path d="M8 12h8M12 8v8" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Receita Potencial</span>
              <div class="stat-value">{{ formatCurrency(receitaPotencial) }}</div>
            </div>
          </div>
        </section>

        <div v-if="loading" class="loading-state">
          <div class="spinner"></div>
          <p>Carregando espaços...</p>
        </div>

        <div v-else class="espacos-grid">
          <div v-for="espaco in espacos" :key="espaco.id" class="espaco-card">
            <div class="espaco-header">
              <div class="espaco-icon" :class="getIconClass(espaco.nome)">
                <svg
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="1.5"
                >
                  <path :d="getIconPath(espaco.nome)" />
                </svg>
              </div>
              <div class="espaco-info">
                <h3 class="espaco-nome">{{ espaco.nome }}</h3>
                <span class="espaco-status" :class="getStatusClass(espaco.status)">
                  {{ getStatusLabel(espaco.status) }}
                </span>
              </div>
              <button class="btn-deletar" @click="deletarEspaco(espaco)" title="Excluir espaço">
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
            </div>

            <div class="espaco-body">
              <div class="info-row">
                <span class="info-label">Localização</span>
                <span class="info-value">{{ espaco.localizacao || 'Não informada' }}</span>
              </div>
             <div class="info-row">
                <span class="info-label">Capacidade</span>
                <div class="capacidade-edit" v-if="editando === espaco.id">
                  <input
                    type="number"
                    v-model.number="espaco.capacidade"
                    class="capacidade-input"
                  />
                  <span class="capacidade-unidade"> Pessoas</span>
                </div>
                <div v-else class="info-value">
                  {{ espaco.capacidade.toLocaleString() }} pessoas
                </div>
              </div>
              <div class="info-row">
                <span class="info-label">Preço Base</span>
                <div class="preco-edit">
                  <span class="preco-simbolo">R$</span>
                  <input
                    type="number"
                    v-model.number="espaco.preco"
                    class="preco-input"
                    :disabled="editando !== espaco.id"
                  />
                </div>
              </div>
              <div class="info-row">
                <span class="info-label">Tipo Cobrança</span>
                <select
                  v-model="espaco.tipoCobranca"
                  class="tipo-select"
                  :disabled="editando !== espaco.id"
                >
                  <option value="HORA">Por Hora</option>
                  <option value="DIA">Por Dia</option>
                </select>
              </div>
            </div>

            <div class="espaco-footer">
              <button
                v-if="editando !== espaco.id"
                class="btn-editar"
                @click="iniciarEdicao(espaco.id)"
              >
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path d="M17 3l4 4-7 7H10v-4l7-7z" />
                </svg>
                Editar
              </button>
              <div v-else class="edit-actions">
                <button class="btn-salvar" @click="salvarEdicao(espaco)">
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
                  Salvar
                </button>
                <button class="btn-cancelar" @click="cancelarEdicao(espaco.id)">Cancelar</button>
              </div>

              <select
                v-model="espaco.status"
                class="status-select"
                @change="alterarStatus(espaco, $event)"
                :disabled="editando === espaco.id"
              >
                <option value="ATIVO">Ativo</option>
                <option value="INATIVO">Inativo</option>
                <option value="MANUTENCAO">Manutenção</option>
              </select>
            </div>
          </div>
        </div>

        <div class="referencia-card">
          <h3 class="card-title">Tabela de Referência</h3>
          <div class="referencia-grid">
            <div class="referencia-item">
              <span class="ref-label">Por Hora (média)</span>
              <span class="ref-valor">{{ formatCurrency(precoMedioHora) }}</span>
            </div>
            <div class="referencia-item">
              <span class="ref-label">Por Dia (média)</span>
              <span class="ref-valor">{{ formatCurrency(precoMedioDia) }}</span>
            </div>
            <div class="referencia-item">
              <span class="ref-label">Capacidade Total</span>
              <span class="ref-valor">{{ capacidadeTotal.toLocaleString() }} pessoas</span>
            </div>
          </div>
        </div>
      </main>
    </div>

    <div v-if="modalNovoEspaco" class="modal-overlay" @click.self="fecharModalNovoEspaco">
      <div class="modal-container">
        <div class="modal-header">
          <h3>Criar Novo Espaço</h3>
          <button class="modal-fechar" @click="fecharModalNovoEspaco">
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
            <label>Nome do Espaço *</label>
            <input
              type="text"
              v-model="novoEspaco.nome"
              class="input-field"
              placeholder="Ex: Sala VIP, Área Exclusiva..."
            />
          </div>
          <div class="form-field">
            <label>Localização</label>
            <input
              type="text"
              v-model="novoEspaco.localizacao"
              class="input-field"
              placeholder="Ex: Setor Norte, Térreo..."
            />
          </div>
          <div class="form-row">
            <div class="form-field">
              <label>Capacidade (pessoas) *</label>
              <input type="number" v-model="novoEspaco.capacidade" class="input-field" />
            </div>
            <div class="form-field">
              <label>Preço Base (R$) *</label>
              <input type="number" v-model="novoEspaco.preco" class="input-field" />
            </div>
          </div>
          <div class="form-field">
            <label>Tipo de Cobrança</label>
            <select v-model="novoEspaco.tipoCobranca" class="input-field">
              <option value="HORA">Por Hora</option>
              <option value="DIA">Por Dia</option>
            </select>
          </div>
          <div class="form-field">
            <label>Status</label>
            <select v-model="novoEspaco.status" class="input-field">
              <option value="ATIVO">Ativo</option>
              <option value="INATIVO">Inativo</option>
              <option value="MANUTENCAO">Em Manutenção</option>
            </select>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn-modal-cancelar" @click="fecharModalNovoEspaco">Cancelar</button>
          <button class="btn-modal-confirmar" @click="criarNovoEspaco">Criar Espaço</button>
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
import api from '../services/api'

const router = useRouter()
interface Espaco {
  id: number
  nome: string
  capacidade: number
  localizacao: string
  tipoCobranca: 'HORA' | 'DIA'
  preco: number
  status: 'ATIVO' | 'INATIVO' | 'MANUTENCAO'
}

const isSidebarOpen = ref(false)
const editando = ref<number | null>(null)
const modalNovoEspaco = ref(false)
const loading = ref(true)

const espacos = ref<Espaco[]>([])
const precoOriginal = ref<Record<number, number>>({})

const novoEspaco = ref({
  nome: '',
  capacidade: 0,
  localizacao: '',
  tipoCobranca: 'DIA' as 'HORA' | 'DIA',
  preco: 0,
  status: 'ATIVO' as 'ATIVO' | 'INATIVO' | 'MANUTENCAO',
})

const espacosAtivos = computed(() => espacos.value.filter((e) => e.status === 'ATIVO'))
const capacidadeTotal = computed(() => espacos.value.reduce((acc, e) => acc + numeroSeguro(e.capacidade), 0))
const receitaPotencial = computed(() =>
  espacos.value.reduce((acc, e) => acc + (e.status === 'ATIVO' ? numeroSeguro(e.preco) : 0), 0),
)

const precoMedioHora = computed(() => {
  const horaSpaces = espacos.value.filter((e) => e.tipoCobranca === 'HORA' && e.status === 'ATIVO')
  if (horaSpaces.length === 0) return 0
  return horaSpaces.reduce((acc, e) => acc + numeroSeguro(e.preco), 0) / horaSpaces.length
})

const precoMedioDia = computed(() => {
  const diaSpaces = espacos.value.filter((e) => e.tipoCobranca === 'DIA' && e.status === 'ATIVO')
  if (diaSpaces.length === 0) return 0
  return diaSpaces.reduce((acc, e) => acc + numeroSeguro(e.preco), 0) / diaSpaces.length
})

function numeroSeguro(valor: unknown): number {
  const numero = Number(valor || 0)
  return Number.isFinite(numero) ? numero : 0
}

function formatCurrency(value: number): string {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(numeroSeguro(value))
}

function getStatusLabel(status: string): string {
  const map: Record<string, string> = {
    ATIVO: 'Ativo',
    INATIVO: 'Inativo',
    MANUTENCAO: 'Manutenção',
  }
  return map[status] || status
}

function getStatusClass(status: string): string {
  const map: Record<string, string> = {
    ATIVO: 'ativo',
    INATIVO: 'inativo',
    MANUTENCAO: 'manutencao',
  }
  return map[status] || ''
}

function getIconClass(nome: string): string {
  const nomeLower = nome.toLowerCase()
  if (nomeLower.includes('campo')) return 'icon-campo'
  if (nomeLower.includes('anel')) return 'icon-anel'
  if (nomeLower.includes('camarote')) return 'icon-camarote'
  if (nomeLower.includes('estacionamento')) return 'icon-estacionamento'
  return 'icon-padrao'
}

function getIconPath(nome: string): string {
  const nomeLower = nome.toLowerCase()
  if (nomeLower.includes('campo')) return 'M4 4h16v16H4z M8 8h8v8H8z'
  if (nomeLower.includes('anel'))
    return 'M12 2a10 10 0 1 0 10 10 10 10 0 0 0-10-10z M12 8a4 4 0 1 0 4 4 4 4 0 0 0-4-4z'
  if (nomeLower.includes('camarote'))
    return 'M12 2a10 10 0 1 0 10 10 10 10 0 0 0-10-10z M2 12h20 M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z'
  if (nomeLower.includes('estacionamento')) return 'M4 4h16v16H4z M8 12h8 M12 8v8'
  return 'M12 2a10 10 0 1 0 10 10 10 10 0 0 0-10-10z'
}

async function carregarEspacos() {
  loading.value = true
  try {
    const response = await api.get('/api/espaco')
    const dados = Array.isArray(response.data) ? response.data : []
    espacos.value = dados.map((item: any) => ({
      ...item,
      status: item.status || 'INATIVO',
    }))
    console.log('Espaços carregados:', espacos.value)
  } catch (error) {
    console.error('Erro ao carregar espaços:', error)
    espacos.value = []
    alert('Erro ao carregar espaços')
  } finally {
    loading.value = false
  }
}

async function criarNovoEspaco() {
  if (!novoEspaco.value.nome) {
    alert('Preencha o nome do espaço')
    return
  }
  if (novoEspaco.value.capacidade <= 0) {
    alert('Informe uma capacidade válida')
    return
  }
  if (novoEspaco.value.preco <= 0) {
    alert('Informe um preço base válido')
    return
  }

  try {
    const response = await api.post('/api/espaco', novoEspaco.value)
    espacos.value.push(response.data)
    fecharModalNovoEspaco()
    alert(`Espaço "${response.data.nome}" criado com sucesso!`)
  } catch (error) {
    console.error('Erro ao criar espaço:', error)
    alert('Erro ao criar espaço')
  }
}

async function atualizarEspaco(id: number, updates: Partial<Espaco>) {
  try {
    const response = await api.put(`/api/espaco/${id}`, updates)
    const index = espacos.value.findIndex((e) => e.id === id)
    if (index !== -1) {
      espacos.value[index] = response.data
    }
    return response.data
  } catch (error) {
    console.error('Erro ao atualizar espaço:', error)
    alert('Erro ao atualizar espaço')
    throw error
  }
}

async function deletarEspaco(espaco: Espaco) {
  if (confirm(`Tem certeza que deseja excluir o espaço "${espaco.nome}"?`)) {
    try {
      await api.delete(`/api/espaco/${espaco.id}`)
      const index = espacos.value.findIndex((e) => e.id === espaco.id)
      if (index !== -1) {
        espacos.value.splice(index, 1)
      }
      alert(`Espaço "${espaco.nome}" removido com sucesso!`)
    } catch (error) {
      console.error('Erro ao excluir espaço:', error)
      alert('Erro ao excluir espaço')
    }
  }
}

async function alterarStatus(espaco: Espaco, event: Event) {
  const select = event.target as HTMLSelectElement
  const novoStatus = select.value as 'ATIVO' | 'INATIVO' | 'MANUTENCAO'

  try {
    const dadosCompletos = {
      nome: espaco.nome,
      capacidade: espaco.capacidade,
      localizacao: espaco.localizacao,
      tipoCobranca: espaco.tipoCobranca,
      preco: espaco.preco,
      status: novoStatus,
    }

    const response = await api.put(`/api/espaco/${espaco.id}`, dadosCompletos)
    const index = espacos.value.findIndex((e) => e.id === espaco.id)
    if (index !== -1) {
      espacos.value[index] = response.data
    }
    alert(`${espaco.nome} alterado para ${getStatusLabel(novoStatus)} com sucesso!`)
  } catch (error) {
    console.error('Erro ao alterar status:', error)
    alert('Erro ao alterar status')
    const index = espacos.value.findIndex((e) => e.id === espaco.id)
    if (index !== -1) {
      const espacoAtual = espacos.value[index]
      if (espacoAtual) espacoAtual.status = espaco.status
    }
  }
}

function iniciarEdicao(id: number) {
  const espaco = espacos.value.find((e) => e.id === id)
  if (espaco) {
    precoOriginal.value[id] = espaco.preco
    editando.value = id
  }
}

async function salvarEdicao(espaco: Espaco) {
  try {
    const dadosCompletos = {
      nome: espaco.nome,
      capacidade: espaco.capacidade,
      localizacao: espaco.localizacao,
      tipoCobranca: espaco.tipoCobranca,
      preco: espaco.preco,
      status: espaco.status,
    }

    const response = await api.put(`/api/espaco/${espaco.id}`, dadosCompletos)
    const index = espacos.value.findIndex((e) => e.id === espaco.id)
    if (index !== -1) {
      espacos.value[index] = response.data
    }
    editando.value = null
    delete precoOriginal.value[espaco.id]
    alert(`Espaço "${espaco.nome}" atualizado com sucesso!`)
  } catch (error) {
    console.error('Erro ao atualizar espaço:', error)
    alert('Erro ao atualizar espaço')
    cancelarEdicao(espaco.id)
  }
}

function cancelarEdicao(id: number) {
  const espaco = espacos.value.find((e) => e.id === id)
  if (espaco && precoOriginal.value[id]) {
    espaco.preco = precoOriginal.value[id]
  }
  editando.value = null
}

function abrirModalNovoEspaco() {
  novoEspaco.value = {
    nome: '',
    capacidade: 0,
    localizacao: '',
    tipoCobranca: 'DIA',
    preco: 0,
    status: 'ATIVO',
  }
  modalNovoEspaco.value = true
}

function fecharModalNovoEspaco() {
  modalNovoEspaco.value = false
}

onMounted(() => {
  const stored = localStorage.getItem('arena_hub_auth')
  if (!stored) {
    alert('Você precisa estar logado para acessar esta página')
    router.push('/login')
    return
  }
  carregarEspacos()
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
  flex-wrap: wrap;
  gap: 16px;
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

.btn-novo-espaco {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border: none;
  padding: 12px 24px;
  border-radius: 12px;
  font-weight: 700;
  font-size: 0.85rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s;
}

.btn-novo-espaco:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(201, 168, 76, 0.3);
}

.capacidade-edit {
  display: flex;
  align-items: center;
  gap: 8px;
  background: #1a2233;
  border-radius: 8px;
  padding: 6px 12px;
}

.capacidade-input {
  background: transparent;
  border: none;
  color: #e0e0e0;
  font-size: 0.9rem;
  font-weight: 500;
  width: 120px;
  outline: none;
  text-align: right;
}

.capacidade-unidade {
  font-size: 0.75rem;
  color: #6b7280;
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

.stat-icon.total {
  background: rgba(201, 168, 76, 0.15);
}
.stat-icon.total svg {
  color: #c9a84c;
}
.stat-icon.ativos {
  background: rgba(16, 185, 129, 0.15);
}
.stat-icon.ativos svg {
  color: #10b981;
}
.stat-icon.capacidade {
  background: rgba(59, 130, 246, 0.15);
}
.stat-icon.capacidade svg {
  color: #3b82f6;
}
.stat-icon.receita {
  background: rgba(139, 92, 246, 0.15);
}
.stat-icon.receita svg {
  color: #8b5cf6;
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
  font-size: 1.4rem;
  font-weight: 800;
  line-height: 1.2;
}

.loading-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 60px 20px;
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
  border: 1px solid rgba(255, 255, 255, 0.05);
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

.loading-state p {
  color: #8e9aaf;
  font-size: 0.9rem;
}

.espacos-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(380px, 1fr));
  gap: 24px;
  margin-bottom: 32px;
}

.espaco-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 24px;
  transition: all 0.3s ease;
  position: relative;
}

.espaco-card:hover {
  transform: translateY(-4px);
  border-color: rgba(201, 168, 76, 0.2);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
}

.espaco-header {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 20px;
}

.espaco-icon {
  width: 52px;
  height: 52px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.icon-campo {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
}
.icon-anel {
  background: rgba(76, 135, 201, 0.15);
  color: #4c87c9;
}
.icon-camarote {
  background: rgba(139, 92, 246, 0.15);
  color: #8b5cf6;
}
.icon-estacionamento {
  background: rgba(76, 201, 142, 0.15);
  color: #4cc98e;
}
.icon-padrao {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
}

.espaco-info {
  flex: 1;
}

.espaco-nome {
  font-size: 1.1rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 4px;
}

.espaco-status {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 12px;
  font-size: 0.65rem;
  font-weight: 700;
}

.espaco-status.ativo {
  background: rgba(16, 185, 129, 0.15);
  color: #10b981;
  border: 1px solid rgba(16, 185, 129, 0.3);
}

.espaco-status.inativo {
  background: rgba(239, 68, 68, 0.15);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}

.espaco-status.manutencao {
  background: rgba(245, 158, 11, 0.15);
  color: #f59e0b;
  border: 1px solid rgba(245, 158, 11, 0.3);
}

.btn-deletar {
  background: rgba(239, 68, 68, 0.15);
  border: none;
  border-radius: 8px;
  padding: 6px;
  cursor: pointer;
  color: #ef4444;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-deletar:hover {
  background: #ef4444;
  color: white;
}

.espaco-body {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 20px;
}

.info-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.info-label {
  font-size: 0.75rem;
  color: #6b7280;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.info-value {
  font-size: 0.9rem;
  font-weight: 500;
  color: #e0e0e0;
}

.info-value-edit {
  display: flex;
  align-items: center;
  gap: 8px;
}

.btn-inline-edit {
  background: rgba(201, 168, 76, 0.15);
  border: none;
  border-radius: 6px;
  padding: 4px;
  cursor: pointer;
  color: #c9a84c;
  transition: all 0.2s;
}

.btn-inline-edit:hover {
  background: rgba(201, 168, 76, 0.3);
}

.preco-edit {
  display: flex;
  align-items: center;
  gap: 6px;
  background: #1a2233;
  border-radius: 8px;
  padding: 6px 12px;
}

.preco-simbolo {
  font-size: 0.8rem;
  color: #c9a84c;
}

.preco-input {
  background: transparent;
  border: none;
  color: #e0e0e0;
  font-size: 0.95rem;
  font-weight: 600;
  width: 100px;
  outline: none;
}

.tipo-select {
  background: #1a2233;
  border: none;
  color: #e0e0e0;
  padding: 6px 12px;
  border-radius: 8px;
  font-size: 0.85rem;
  cursor: pointer;
}

.espaco-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 16px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
  gap: 12px;
}

.btn-editar {
  background: rgba(201, 168, 76, 0.15);
  border: none;
  padding: 8px 16px;
  border-radius: 8px;
  color: #c9a84c;
  font-weight: 600;
  font-size: 0.8rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s;
}

.btn-editar:hover {
  background: rgba(201, 168, 76, 0.25);
  transform: translateY(-2px);
}

.edit-actions {
  display: flex;
  gap: 8px;
}

.btn-salvar {
  background: linear-gradient(135deg, #10b981, #059669);
  border: none;
  padding: 8px 16px;
  border-radius: 8px;
  color: white;
  font-weight: 600;
  font-size: 0.8rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
}

.btn-cancelar {
  background: rgba(239, 68, 68, 0.15);
  border: none;
  padding: 8px 16px;
  border-radius: 8px;
  color: #ef4444;
  font-weight: 600;
  font-size: 0.8rem;
  cursor: pointer;
}

.status-select {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  border-radius: 8px;
  padding: 6px 12px;
  color: #e0e0e0;
  font-size: 0.8rem;
  cursor: pointer;
  transition: all 0.2s;
  min-width: 110px;
}

.status-select:hover:not(:disabled) {
  border-color: rgba(201, 168, 76, 0.5);
}

.status-select:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.status-select option {
  background: #1a2233;
  color: #e0e0e0;
}

.referencia-card {
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

.referencia-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 16px;
}

.referencia-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  background: rgba(255, 255, 255, 0.02);
  border-radius: 12px;
}

.ref-label {
  font-size: 0.85rem;
  color: #8e9aaf;
}

.ref-valor {
  font-size: 1rem;
  font-weight: 700;
  color: #c9a84c;
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
  max-width: 550px;
  max-height: 85vh;
  overflow-y: auto;
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
  gap: 20px;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  padding: 20px 24px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.form-field {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-field label {
  font-size: 0.75rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.input-field {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 10px;
  padding: 12px 14px;
  color: white;
  font-size: 0.9rem;
  outline: none;
  transition: all 0.3s;
}

.input-field:focus {
  border-color: #c9a84c;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
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

.btn-modal-confirmar {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 10px 20px;
  border-radius: 10px;
  color: #0a0e17;
  font-weight: 700;
  cursor: pointer;
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
  .espacos-grid {
    grid-template-columns: 1fr;
  }
  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 600px) {
  .stats-grid {
    grid-template-columns: 1fr;
  }
  .form-row {
    grid-template-columns: 1fr;
  }
  .content-header {
    flex-direction: column;
    align-items: flex-start;
  }
  .referencia-grid {
    grid-template-columns: 1fr;
  }
  .espaco-footer {
    flex-wrap: wrap;
  }
  .status-select {
    width: 100%;
  }
}
</style>
