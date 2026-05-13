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

        <router-link to="/admin/produtores" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="8" r="4"/>
            <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
          </svg>
          <span>Produtores</span>
        </router-link>

        <router-link to="/admin/solicitacoes" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/>
            <polyline points="14 2 14 8 20 8"/>
            <path d="M9 15l2 2 4-4"/>
          </svg>
          <span>Solicitações</span>
        </router-link>

        <router-link to="/admin/eventos" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="4" width="18" height="18" rx="2" ry="2"/>
            <line x1="16" y1="2" x2="16" y2="6"/>
            <line x1="8" y1="2" x2="8" y2="6"/>
            <line x1="3" y1="10" x2="21" y2="10"/>
          </svg>
          <span>Eventos</span>
        </router-link>

        <router-link to="/admin/precos" class="nav-item active">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"/>
            <path d="M12 6v6l4 2"/>
          </svg>
          <span>Preços</span>
        </router-link>

        <div class="nav-spacer"></div>

        <router-link to="/admin/perfil" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="8" r="4"/>
            <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
          </svg>
          <span>Perfil</span>
        </router-link>

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
        <div class="breadcrumb">
          <router-link to="/admin" class="breadcrumb-link">Admin</router-link>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
          <span class="breadcrumb-current">Preços dos Espaços</span>
        </div>

        <header class="content-header">
          <div class="header-titles">
            <h1>Gestão de Preços</h1>
            <p>Configure os valores e espaços da Arena Pernambuco</p>
          </div>
          <button class="btn-novo-espaco" @click="abrirModalNovoEspaco">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <line x1="12" y1="5" x2="12" y2="19"/>
              <line x1="5" y1="12" x2="19" y2="12"/>
            </svg>
            Novo Espaço
          </button>
        </header>

        <!-- Stats Resumo -->
        <section class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon total">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="3" width="18" height="18" rx="2"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Total de Espaços</span>
              <div class="stat-value">{{ espacos.length }}</div>
            </div>
          </div>
          <div class="stat-card">
            <div class="stat-icon ativos">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <polyline points="20 6 9 17 4 12"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Ativos</span>
              <div class="stat-value">{{ espacosAtivos.length }}</div>
            </div>
          </div>
          <div class="stat-card">
            <div class="stat-icon capacidade">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                <circle cx="12" cy="12" r="3"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Capacidade Total</span>
              <div class="stat-value">{{ capacidadeTotal.toLocaleString() }}</div>
            </div>
          </div>
          <div class="stat-card">
            <div class="stat-icon receita">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"/>
                <path d="M8 12h8M12 8v8"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Receita Potencial</span>
              <div class="stat-value">{{ formatCurrency(receitaPotencial) }}</div>
            </div>
          </div>
        </section>

        <!-- Espaços Grid -->
        <div class="espacos-grid">
          <div v-for="espaco in espacos" :key="espaco.id" class="espaco-card">
            <div class="espaco-header">
              <div class="espaco-icon" :class="espaco.iconClass">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
                  <path :d="espaco.iconPath" />
                </svg>
              </div>
              <div class="espaco-info">
                <h3 class="espaco-nome">{{ espaco.nome }}</h3>
                <span class="espaco-status" :class="espaco.ativo ? 'ativo' : 'inativo'">
                  {{ espaco.ativo ? 'Ativo' : 'Inativo' }}
                </span>
              </div>
              <button class="btn-deletar" @click="deletarEspaco(espaco)" title="Excluir espaço">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <line x1="18" y1="6" x2="6" y2="18"/>
                  <line x1="6" y1="6" x2="18" y2="18"/>
                </svg>
              </button>
            </div>

            <div class="espaco-body">
              <div class="info-row">
                <span class="info-label">Descrição</span>
                <span class="info-value">{{ espaco.descricao }}</span>
              </div>
              <div class="info-row">
                <span class="info-label">Capacidade</span>
                <div class="info-value-edit">
                  <span class="info-value">{{ espaco.capacidade.toLocaleString() }} pessoas</span>
                  <button v-if="editando === espaco.id" class="btn-inline-edit" @click="editarCapacidade(espaco)">
                    <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M17 3l4 4-7 7H10v-4l7-7z"/>
                    </svg>
                  </button>
                </div>
              </div>
              <div class="info-row">
                <span class="info-label">Preço Base</span>
                <div class="preco-edit">
                  <span class="preco-simbolo">R$</span>
                  <input
                    type="number"
                    v-model.number="espaco.precoBase"
                    class="preco-input"
                    :disabled="editando !== espaco.id"
                  />
                </div>
              </div>
              <div class="info-row">
                <span class="info-label">Tipo Cobrança</span>
                <select v-model="espaco.tipoCobranca" class="tipo-select" :disabled="editando !== espaco.id">
                  <option value="evento">Por Evento</option>
                  <option value="hora">Por Hora</option>
                  <option value="diaria">Por Diária</option>
                </select>
              </div>
            </div>

            <div class="espaco-footer">
              <button v-if="editando !== espaco.id" class="btn-editar" @click="iniciarEdicao(espaco.id)">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M17 3l4 4-7 7H10v-4l7-7z"/>
                </svg>
                Editar
              </button>
              <div v-else class="edit-actions">
                <button class="btn-salvar" @click="salvarEdicao(espaco)">
                  <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                    <polyline points="20 6 9 17 4 12"/>
                  </svg>
                  Salvar
                </button>
                <button class="btn-cancelar" @click="cancelarEdicao(espaco.id)">Cancelar</button>
              </div>
              <button class="btn-toggle" @click="toggleStatus(espaco)">
                <span class="toggle-slider" :class="espaco.ativo ? 'active' : ''"></span>
              </button>
            </div>
          </div>
        </div>
      </main>
    </div>

    <!-- Modal Novo Espaço -->
    <div v-if="modalNovoEspaco" class="modal-overlay" @click.self="fecharModalNovoEspaco">
      <div class="modal-container">
        <div class="modal-header">
          <h3>Criar Novo Espaço</h3>
          <button class="modal-fechar" @click="fecharModalNovoEspaco">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <line x1="18" y1="6" x2="6" y2="18"/>
              <line x1="6" y1="6" x2="18" y2="18"/>
            </svg>
          </button>
        </div>
        <div class="modal-body">
          <div class="form-field">
            <label>Nome do Espaço *</label>
            <input type="text" v-model="novoEspaco.nome" class="input-field" placeholder="Ex: Sala VIP, Área Exclusiva..." />
          </div>
          <div class="form-field">
            <label>Descrição</label>
            <textarea v-model="novoEspaco.descricao" rows="2" class="input-field" placeholder="Descreva o espaço..."></textarea>
          </div>
          <div class="form-row">
            <div class="form-field">
              <label>Capacidade (pessoas)</label>
              <input type="number" v-model="novoEspaco.capacidade" class="input-field" />
            </div>
            <div class="form-field">
              <label>Preço Base (R$)</label>
              <input type="number" v-model="novoEspaco.precoBase" class="input-field" />
            </div>
          </div>
          <div class="form-field">
            <label>Tipo de Cobrança</label>
            <select v-model="novoEspaco.tipoCobranca" class="input-field">
              <option value="evento">Por Evento</option>
              <option value="hora">Por Hora</option>
              <option value="diaria">Por Diária</option>
            </select>
          </div>
          <div class="form-field">
            <label>Ícone do Espaço</label>
            <div class="icones-grid">
              <button v-for="icone in iconesDisponiveis" :key="icone.id" type="button" class="icone-btn" :class="{ active: novoEspaco.iconClass === icone.class }" @click="selecionarIcone(icone)">
                <div class="icone-preview" :class="icone.class">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
                    <path :d="icone.path" />
                  </svg>
                </div>
              </button>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn-modal-cancelar" @click="fecharModalNovoEspaco">Cancelar</button>
          <button class="btn-modal-confirmar" @click="criarNovoEspaco">Criar Espaço</button>
        </div>
      </div>
    </div>

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
const editando = ref<number | null>(null)
const modalNovoEspaco = ref(false)

const iconesDisponiveis = ref([
  { id: 1, nome: 'Campo', class: 'icon-campo', path: 'M4 4h16v16H4z M8 8h8v8H8z' },
  { id: 2, nome: 'Anel', class: 'icon-anel', path: 'M12 2a10 10 0 1 0 10 10 10 10 0 0 0-10-10z M12 8a4 4 0 1 0 4 4 4 4 0 0 0-4-4z' },
  { id: 3, nome: 'Camarote', class: 'icon-camarote', path: 'M12 2a10 10 0 1 0 10 10 10 10 0 0 0-10-10z M2 12h20 M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z' },
  { id: 4, nome: 'Estacionamento', class: 'icon-estacionamento', path: 'M4 4h16v16H4z M8 12h8 M12 8v8' },
  { id: 5, nome: 'Sala', class: 'icon-sala', path: 'M20 7h-4.5L12 3 8.5 7H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z' },
  { id: 6, nome: 'Palco', class: 'icon-palco', path: 'M4 20h16M8 16h8M12 4v12' },
])

const novoEspaco = ref({
  nome: '',
  descricao: '',
  capacidade: 0,
  precoBase: 0,
  tipoCobranca: 'evento',
  iconClass: 'icon-campo',
  iconPath: 'M4 4h16v16H4z M8 8h8v8H8z',
  ativo: true,
})

const espacos = ref([
  {
    id: 1,
    nome: 'Campo',
    descricao: 'Área principal para eventos de grande porte',
    capacidade: 30000,
    precoBase: 50000,
    tipoCobranca: 'evento',
    ativo: true,
    iconClass: 'icon-campo',
    iconPath: 'M4 4h16v16H4z M8 8h8v8H8z',
  },
  {
    id: 2,
    nome: 'Anel Inferior',
    descricao: 'Arquibancada coberta com visão privilegiada',
    capacidade: 15000,
    precoBase: 30000,
    tipoCobranca: 'evento',
    ativo: true,
    iconClass: 'icon-anel',
    iconPath: 'M12 2a10 10 0 1 0 10 10 10 10 0 0 0-10-10z M12 8a4 4 0 1 0 4 4 4 4 0 0 0-4-4z',
  },
  {
    id: 3,
    nome: 'Anel Superior',
    descricao: 'Arquibancada superior com vista panorâmica',
    capacidade: 10000,
    precoBase: 20000,
    tipoCobranca: 'evento',
    ativo: true,
    iconClass: 'icon-anel',
    iconPath: 'M12 2a10 10 0 1 0 10 10 10 10 0 0 0-10-10z M12 8a4 4 0 1 0 4 4 4 4 0 0 0-4-4z',
  },
  {
    id: 4,
    nome: 'Camarote',
    descricao: 'Espaço premium com serviço diferenciado',
    capacidade: 500,
    precoBase: 15000,
    tipoCobranca: 'evento',
    ativo: true,
    iconClass: 'icon-camarote',
    iconPath: 'M12 2a10 10 0 1 0 10 10 10 10 0 0 0-10-10z M2 12h20 M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z',
  },
  {
    id: 5,
    nome: 'Estacionamento',
    descricao: 'Área de estacionamento com 2000 vagas',
    capacidade: 2000,
    precoBase: 10000,
    tipoCobranca: 'evento',
    ativo: true,
    iconClass: 'icon-estacionamento',
    iconPath: 'M4 4h16v16H4z M8 12h8 M12 8v8',
  },
])

const precoOriginal = ref<Record<number, number>>({})
const capacidadeOriginal = ref<Record<number, number>>({})

const espacosAtivos = computed(() => espacos.value.filter(e => e.ativo))
const capacidadeTotal = computed(() => espacos.value.reduce((acc, e) => acc + e.capacidade, 0))
const receitaPotencial = computed(() => espacos.value.reduce((acc, e) => acc + (e.ativo ? e.precoBase : 0), 0))

const referencias = ref([
  { label: 'Campo + Anel Inferior', valor: 75000 },
  { label: 'Campo + Anel Superior', valor: 65000 },
  { label: 'Todos os Espaços', valor: 120000 },
  { label: 'Camarote + Estacionamento', valor: 22000 },
  { label: 'Evento Completo', valor: 150000 },
])

function formatCurrency(value: number) {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  }).format(value)
}

function iniciarEdicao(id: number) {
  const espaco = espacos.value.find(e => e.id === id)
  if (espaco) {
    precoOriginal.value[id] = espaco.precoBase
    capacidadeOriginal.value[id] = espaco.capacidade
    editando.value = id
  }
}

function salvarEdicao(espaco: any) {
  editando.value = null
  delete precoOriginal.value[espaco.id]
  delete capacidadeOriginal.value[espaco.id]
  alert(`Espaço "${espaco.nome}" atualizado com sucesso!`)
}

function cancelarEdicao(id: number) {
  const espaco = espacos.value.find(e => e.id === id)
  if (espaco) {
    if (precoOriginal.value[id]) espaco.precoBase = precoOriginal.value[id]
    if (capacidadeOriginal.value[id]) espaco.capacidade = capacidadeOriginal.value[id]
  }
  editando.value = null
}

function toggleStatus(espaco: any) {
  espaco.ativo = !espaco.ativo
  alert(`${espaco.nome} ${espaco.ativo ? 'ativado' : 'inativado'} com sucesso`)
}

function deletarEspaco(espaco: any) {
  if (confirm(`Tem certeza que deseja excluir o espaço "${espaco.nome}"?`)) {
    const index = espacos.value.findIndex(e => e.id === espaco.id)
    if (index !== -1) {
      espacos.value.splice(index, 1)
      alert(`Espaço "${espaco.nome}" removido com sucesso!`)
    }
  }
}

function abrirModalNovoEspaco() {
  novoEspaco.value = {
    nome: '',
    descricao: '',
    capacidade: 0,
    precoBase: 0,
    tipoCobranca: 'evento',
    iconClass: 'icon-campo',
    iconPath: 'M4 4h16v16H4z M8 8h8v8H8z',
    ativo: true,
  }
  modalNovoEspaco.value = true
}

function fecharModalNovoEspaco() {
  modalNovoEspaco.value = false
}

function selecionarIcone(icone: any) {
  novoEspaco.value.iconClass = icone.class
  novoEspaco.value.iconPath = icone.path
}

function criarNovoEspaco() {
  if (!novoEspaco.value.nome) {
    alert('Preencha o nome do espaço')
    return
  }
  if (novoEspaco.value.capacidade <= 0) {
    alert('Informe uma capacidade válida')
    return
  }
  if (novoEspaco.value.precoBase <= 0) {
    alert('Informe um preço base válido')
    return
  }

  espacos.value.push({
    id: Date.now(),
    nome: novoEspaco.value.nome,
    descricao: novoEspaco.value.descricao || 'Sem descrição',
    capacidade: novoEspaco.value.capacidade,
    precoBase: novoEspaco.value.precoBase,
    tipoCobranca: novoEspaco.value.tipoCobranca,
    ativo: true,
    iconClass: novoEspaco.value.iconClass,
    iconPath: novoEspaco.value.iconPath,
  })

  fecharModalNovoEspaco()
  alert(`Espaço "${novoEspaco.value.nome}" criado com sucesso!`)
}

function editarCapacidade(espaco: any) {
  const novaCapacidade = prompt('Nova capacidade (pessoas):', espaco.capacidade)
  if (novaCapacidade && !isNaN(Number(novaCapacidade))) {
    espaco.capacidade = Number(novaCapacidade)
    alert(`Capacidade do ${espaco.nome} atualizada para ${espaco.capacidade.toLocaleString()} pessoas`)
  }
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
.stat-icon.total { background: rgba(201, 168, 76, 0.15); }
.stat-icon.total svg { color: #c9a84c; }
.stat-icon.ativos { background: rgba(16, 185, 129, 0.15); }
.stat-icon.ativos svg { color: #10b981; }
.stat-icon.capacidade { background: rgba(59, 130, 246, 0.15); }
.stat-icon.capacidade svg { color: #3b82f6; }
.stat-icon.receita { background: rgba(139, 92, 246, 0.15); }
.stat-icon.receita svg { color: #8b5cf6; }
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
.icon-campo { background: rgba(201, 168, 76, 0.15); color: #c9a84c; }
.icon-anel { background: rgba(76, 135, 201, 0.15); color: #4c87c9; }
.icon-camarote { background: rgba(139, 92, 246, 0.15); color: #8b5cf6; }
.icon-estacionamento { background: rgba(76, 201, 142, 0.15); color: #4cc98e; }
.icon-sala { background: rgba(236, 72, 153, 0.15); color: #ec4899; }
.icon-palco { background: rgba(245, 158, 11, 0.15); color: #f59e0b; }
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
.btn-toggle {
  width: 44px;
  height: 24px;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 30px;
  cursor: pointer;
  position: relative;
  border: none;
  padding: 2px;
}
.toggle-slider {
  display: block;
  width: 20px;
  height: 20px;
  background: #6b7280;
  border-radius: 50%;
  transition: transform 0.3s ease;
}
.toggle-slider.active {
  transform: translateX(20px);
  background: #10b981;
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
textarea.input-field {
  resize: vertical;
  font-family: inherit;
}
.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
}
.icones-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}
.icone-btn {
  background: #1a2233;
  border: 2px solid rgba(255, 255, 255, 0.1);
  border-radius: 12px;
  padding: 8px;
  cursor: pointer;
  transition: all 0.2s;
}
.icone-btn.active {
  border-color: #c9a84c;
  background: rgba(201, 168, 76, 0.1);
}
.icone-preview {
  width: 40px;
  height: 40px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
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
}
</style>