<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-produtor">
      <div class="produtor-container">
        <div class="breadcrumb">
          <router-link to="/" class="breadcrumb-link">Início</router-link>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
          <router-link to="/produtor/dashboard" class="breadcrumb-link">Painel do Produtor</router-link>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
          <span class="breadcrumb-current">Gerenciar Ingressos</span>
        </div>

        <header class="produtor-header">
          <div class="header-info">
            <h1>{{ evento.nome }}</h1>
            <p>Gerencie os lotes de ingressos e valores</p>
          </div>
          <button class="btn-novo-lote" @click="abrirModalNovoLote">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <line x1="12" y1="5" x2="12" y2="19"/>
              <line x1="5" y1="12" x2="19" y2="12"/>
            </svg>
            Novo Lote
          </button>
        </header>

        <section class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon lotes">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="4" width="18" height="18" rx="2" ry="2"/>
                <line x1="16" y1="2" x2="16" y2="6"/>
                <line x1="8" y1="2" x2="8" y2="6"/>
                <line x1="3" y1="10" x2="21" y2="10"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Total de Lotes</span>
              <div class="stat-value">{{ lotes.length }}</div>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon ingressos">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M20 12V8H6a2 2 0 0 1-2-2c0-1.5 1.5-2 2-2h12M4 17v-2a2 2 0 0 1 2-2h14v-2"/>
                <path d="M16 22h2a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2h-2"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Ingressos Totais</span>
              <div class="stat-value">{{ totalIngressos }}</div>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon vendidos">
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <polyline points="20 6 9 17 4 12"/>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Ingressos Vendidos</span>
              <div class="stat-value">{{ ingressosVendidos }}</div>
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
              <span class="stat-label">Receita Estimada</span>
              <div class="stat-value">{{ formatCurrency(receitaTotal) }}</div>
            </div>
          </div>
        </section>

        <div class="lotes-section">
          <div class="section-header">
            <h2>Lotes de Ingressos</h2>
            <p>Gerencie os preços, quantidades e prazos de cada lote</p>
          </div>

          <div class="lotes-grid">
            <div v-for="(lote, index) in lotes" :key="lote.id" class="lote-card">
              <div class="lote-header">
                <div class="lote-titulo">
                  <h3>{{ lote.nome }}</h3>
                  <span class="lote-status" :class="lote.status">
                    {{ getStatusLabel(lote.status) }}
                  </span>
                </div>
                <div class="lote-acoes">
                  <button class="btn-editar" @click="abrirModalEditarLote(lote)">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M17 3l4 4-7 7H10v-4l7-7z"/>
                      <path d="M4 20h16"/>
                    </svg>
                  </button>
                  <button class="btn-excluir" @click="excluirLote(lote.nome)">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                      <line x1="18" y1="6" x2="6" y2="18"/>
                      <line x1="6" y1="6" x2="18" y2="18"/>
                    </svg>
                  </button>
                </div>
              </div>

              <div class="lote-body">
                <div class="info-row">
                  <span class="info-label">Data de Início</span>
                  <span class="info-value">{{ formatarData(lote.dataInicio) }}</span>
                </div>
                <div class="info-row" style="margin-bottom: 12px;">
                  <span class="info-label">Data de Término</span>
                  <span class="info-value">{{ formatarData(lote.dataFim) }}</span>
                </div>
                
                <h4 style="font-size: 0.85rem; color: #8e9aaf; margin-bottom: 8px;">Categorias</h4>
                <div v-for="item in lote.itens" :key="item.id" class="lote-item">
                  <div class="item-header">
                    <strong>{{ item.categoriaNome }}</strong>
                    <span>{{ formatCurrency(item.preco) }}</span>
                  </div>
                  <div class="item-stats">
                    <span>{{ item.vendidos }} / {{ item.quantidadeTotal }}</span>
                    <span :class="{ 'esgotando': item.disponiveis < 50 }">Restam {{ item.disponiveis }}</span>
                  </div>
                  <div class="progress-bar mini-progress">
                    <div class="progress-fill" :style="{ width: (item.vendidos / item.quantidadeTotal * 100) + '%' }"></div>
                  </div>
                </div>
              </div>

              <div class="lote-footer">
                <div class="progress-container">
                  <div class="progress-bar">
                    <div class="progress-fill" :style="{ width: ((lote.itens?.reduce((acc, i) => acc + i.vendidos, 0) / (lote.itens?.reduce((acc, i) => acc + i.quantidadeTotal, 0) || 1)) * 100) + '%' }"></div>
                  </div>
                  <span class="progress-text">{{ Math.round((lote.itens?.reduce((acc, i) => acc + i.vendidos, 0) / (lote.itens?.reduce((acc, i) => acc + i.quantidadeTotal, 0) || 1)) * 100) }}% vendido (Total)</span>
                </div>
              </div>
            </div>
          </div>

          <div v-if="lotes.length === 0" class="empty-state">
            <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
              <rect x="3" y="4" width="18" height="18" rx="2" ry="2"/>
              <line x1="16" y1="2" x2="16" y2="6"/>
              <line x1="8" y1="2" x2="8" y2="6"/>
              <line x1="3" y1="10" x2="21" y2="10"/>
            </svg>
            <h3>Nenhum lote criado</h3>
            <p>Clique em "Novo Lote" para começar a vender ingressos</p>
          </div>
        </div>
      </div>
    </main>

    <AppFooter />

    <div v-if="modalLote" class="modal-overlay" @click.self="fecharModalLote">
      <div class="modal-container modal-medium">
        <div class="modal-header">
          <h3>{{ loteEditando ? 'Editar Lote' : 'Novo Lote' }}</h3>
          <button class="modal-fechar" @click="fecharModalLote">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <line x1="18" y1="6" x2="6" y2="18"/>
              <line x1="6" y1="6" x2="18" y2="18"/>
            </svg>
          </button>
        </div>
        <div class="modal-body">
          <div class="form-field">
            <label>Nome do Lote *</label>
            <input type="text" v-model="formLote.nome" class="input-field" placeholder="Ex: 1º Lote, Promocional, Camarote..." />
          </div>

          <div class="lote-items-wrapper">
            <h4 style="font-size: 0.9rem; margin-bottom: 12px; border-bottom: 1px solid rgba(255,255,255,0.1); padding-bottom: 6px;">Itens do Lote</h4>
            <div v-for="(item, index) in formLote.itens" :key="index" class="form-row item-row" style="background: rgba(255,255,255,0.03); padding: 12px; border-radius: 8px; margin-bottom: 12px; align-items: flex-end;">
              <div class="form-field" style="flex: 2;">
                <label>Categoria</label>
                <select v-model="item.categoriaId" class="input-field">
                  <option :value="null">Selecione...</option>
                  <option v-for="cat in categoriasIngresso" :key="cat.id" :value="cat.id">{{ cat.nome }}</option>
                </select>
              </div>
              <div class="form-field" style="flex: 1.5;">
                <label>Preço</label>
                <input type="number" v-model="item.preco" class="input-field" step="0.01" />
              </div>
              <div class="form-field" style="flex: 1.5;">
                <label>Qtd.</label>
                <input type="number" v-model="item.quantidadeTotal" class="input-field" />
              </div>
              <button class="btn-excluir" style="padding: 10px; margin-bottom: 4px;" @click="removerLinha(index)" v-if="formLote.itens.length > 1">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <line x1="18" y1="6" x2="6" y2="18"/>
                  <line x1="6" y1="6" x2="18" y2="18"/>
                </svg>
              </button>
            </div>
            <button class="btn-novo-lote" style="width: 100%; justify-content: center; margin-bottom: 16px; background: transparent; border: 1px dashed rgba(201, 168, 76, 0.5); color: #c9a84c;" @click="adicionarLinha">+ Adicionar Categoria</button>
          </div>

          <div class="form-row">
            <div class="form-field">
              <label>Data de Início</label>
              <input type="datetime-local" v-model="formLote.dataInicio" class="input-field" />
            </div>
            <div class="form-field">
              <label>Data de Término</label>
              <input type="datetime-local" v-model="formLote.dataFim" class="input-field" />
            </div>
          </div>

          <div class="form-field">
            <label>Status</label>
            <select v-model="formLote.status" class="input-field">
              <option value="agendado">Agendado</option>
              <option value="ativo">Ativo</option>
              <option value="pausado">Pausado</option>
              <option value="encerrado">Encerrado</option>
            </select>
          </div>
        </div>
        <p v-if="erroValidacao" style="color: #ef4444; font-size: 0.85rem; padding: 0 24px; margin: 12px 0 0 0; text-align: center; font-weight: 600;">
          {{ erroValidacao }}
        </p>
        <div class="modal-footer">
          <button class="btn-modal-cancelar" @click="fecharModalLote">Cancelar</button>
          <button class="btn-modal-confirmar" @click="salvarLote">Salvar Lote</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const route = useRoute()
const router = useRouter()
const eventoId = ref(Number(route.params.id))

const modalLote = ref(false)
const loteEditando = ref<any>(null)

const formLote = ref({
  nome: '',
  dataInicio: '',
  dataFim: '',
  status: 'agendado',
  itens: [] as Array<{ categoriaId: number | null, preco: number, quantidadeTotal: number }>
})

const erroValidacao = ref('')

const evento = ref<any>({})
const lotes = ref<any[]>([])
const categoriasIngresso = ref<any[]>([])
const capacidadeMaxima = ref(0)
const carregando = ref(false)

import api from '../services/api'

const carregarDados = async () => {
  carregando.value = true
  try {
    const resEvento = await api.get(`/api/eventos/${eventoId.value}`)
    evento.value = resEvento.data
    capacidadeMaxima.value = evento.value.espaco?.capacidade || 0

    const resCats = await api.get('/api/categorias-ingresso')
    categoriasIngresso.value = Array.isArray(resCats.data) ? resCats.data : []

    await carregarLotes()
  } catch (error) {
    console.error('Erro ao carregar dados:', error)
    evento.value = {}
    categoriasIngresso.value = []
    lotes.value = []
  } finally {
    carregando.value = false
  }
}

const carregarLotes = async () => {
  try {
    const resLotes = await api.get(`/api/eventos/${eventoId.value}/lotes`)
    lotes.value = Array.isArray(resLotes.data) ? resLotes.data : []
  } catch (error) {
    console.error('Erro ao carregar lotes:', error)
    lotes.value = []
  }
}
const totalIngressos = computed(() => {
  return lotes.value.reduce((acc, lote) => {
    return acc + (lote.itens?.reduce((accItens: number, item: any) => accItens + numeroSeguro(item.quantidadeTotal), 0) || 0)
  }, 0)
})

const ingressosVendidos = computed(() => {
  return lotes.value.reduce((acc, lote) => {
    return acc + (lote.itens?.reduce((accItens: number, item: any) => accItens + numeroSeguro(item.vendidos), 0) || 0)
  }, 0)
})

const receitaTotal = computed(() => {
  return lotes.value.reduce((acc, lote) => {
    return acc + (lote.itens?.reduce((accItens: number, item: any) => accItens + (numeroSeguro(item.vendidos) * numeroSeguro(item.preco)), 0) || 0)
  }, 0)
})

function numeroSeguro(valor: unknown): number {
  const numero = Number(valor || 0)
  return Number.isFinite(numero) ? numero : 0
}

function formatCurrency(value: number) {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  }).format(value)
}

function formatarData(data: string) {
  if (!data) return 'Não definido'
  return new Date(data).toLocaleDateString('pt-BR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  })
}

function getStatusLabel(status: string) {
  const map: Record<string, string> = {
    ativo: 'Ativo',
    pausado: 'Pausado',
    encerrado: 'Encerrado'
  }
  return map[status] || status
}

function abrirModalNovoLote() {
  loteEditando.value = null
  formLote.value = {
    nome: '',
    dataInicio: '',
    dataFim: '',
    status: 'agendado',
    itens: [{ categoriaId: null, preco: 0, quantidadeTotal: 0 }]
  }
  erroValidacao.value = ''
  modalLote.value = true
}

function abrirModalEditarLote(lote: any) {
  loteEditando.value = lote
  formLote.value = {
    nome: lote.nome,
    dataInicio: lote.dataInicio || '',
    dataFim: lote.dataFim || '',
    status: lote.status,
    itens: lote.itens.map((i: any) => ({
      categoriaId: i.categoriaId,
      preco: i.preco,
      quantidadeTotal: i.quantidadeTotal
    }))
  }
  erroValidacao.value = ''
  modalLote.value = true
}

function adicionarLinha() {
  formLote.value.itens.push({ categoriaId: null, preco: 0, quantidadeTotal: 0 })
}

function removerLinha(index: number) {
  formLote.value.itens.splice(index, 1)
}

async function salvarLote() {
  if (!formLote.value.nome) {
    alert('Informe o nome do lote')
    return
  }
  
  if (formLote.value.dataInicio && formLote.value.dataFim) {
    if (new Date(formLote.value.dataFim) <= new Date(formLote.value.dataInicio)) {
      erroValidacao.value = 'A data de término deve ser posterior à data de início.'
      return
    }
  }

  if (formLote.value.itens.length === 0) {
    alert('Adicione pelo menos um item ao lote')
    return
  }

  for (const item of formLote.value.itens) {
    if (!item.categoriaId || item.preco <= 0 || item.quantidadeTotal <= 0) {
      alert('Preencha todos os campos dos itens com valores válidos')
      return
    }
  }

  try {
    await api.post(`/api/eventos/${eventoId.value}/lotes`, formLote.value)
    alert(loteEditando.value ? 'Lote atualizado com sucesso!' : 'Novo lote criado com sucesso!')
    await carregarLotes()
    fecharModalLote()
  } catch (error: any) {
    const status = error.response?.status
    const msg = error.response?.data?.message || error.response?.data?.error || ''
    if (status === 401 || status === 403) {
      erroValidacao.value = 'Sessão expirada ou sem permissão. Faça login novamente.'
    } else if (msg.includes('sobreposição')) {
      erroValidacao.value = 'Conflito de Calendário: Existe sobreposição de datas com outro lote deste evento. Ajuste o período.'
    } else {
      erroValidacao.value = msg || 'Erro ao salvar lote (verifique a capacidade do espaço).'
    }
  }
}

async function excluirLote(nome: string) {
  if (confirm(`Tem certeza que deseja excluir o lote "${nome}"?`)) {
    try {
      await api.delete(`/api/eventos/${eventoId.value}/lotes/${nome}`)
      alert('Lote excluído com sucesso!')
      await carregarLotes()
    } catch (error) {
      alert('Erro ao excluir lote')
    }
  }
}

function fecharModalLote() {
  modalLote.value = false
  loteEditando.value = null
}

onMounted(() => {
  window.scrollTo(0, 0)
  carregarDados()
})
</script>

<style scoped>
.page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: #0a0e17;
}

.main-produtor {
  flex: 1;
  padding: 40px 20px;
}

.produtor-container {
  max-width: 1200px;
  margin: 0 auto;
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

.produtor-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 32px;
  flex-wrap: wrap;
  gap: 16px;
}

.header-info h1 {
  font-family: 'Bebas Neue', sans-serif;
  font-size: 2rem;
  letter-spacing: 1px;
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin-bottom: 4px;
}

.header-info p {
  color: #8e9aaf;
  font-size: 0.85rem;
}

.btn-novo-lote {
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

.btn-novo-lote:hover {
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
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.stat-icon.lotes { background: rgba(201, 168, 76, 0.15); }
.stat-icon.lotes svg { color: #c9a84c; }
.stat-icon.ingressos { background: rgba(59, 130, 246, 0.15); }
.stat-icon.ingressos svg { color: #3b82f6; }
.stat-icon.vendidos { background: rgba(16, 185, 129, 0.15); }
.stat-icon.vendidos svg { color: #10b981; }
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
}

.lotes-section {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  padding: 32px;
}

.section-header {
  margin-bottom: 28px;
}

.section-header h2 {
  font-size: 1.3rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 6px;
}

.section-header p {
  color: #8e9aaf;
  font-size: 0.85rem;
}

.lotes-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 24px;
}

.lote-card {
  background: #1a2233;
  border-radius: 16px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  overflow: hidden;
  transition: all 0.3s;
}

.lote-card:hover {
  transform: translateY(-2px);
  border-color: rgba(201, 168, 76, 0.15);
}

.lote-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 20px;
  background: rgba(0, 0, 0, 0.2);
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.lote-titulo h3 {
  font-size: 1rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 4px;
}

.lote-status {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 12px;
  font-size: 0.65rem;
  font-weight: 700;
}

.lote-status.ativo {
  background: rgba(16, 185, 129, 0.15);
  color: #10b981;
  border: 1px solid rgba(16, 185, 129, 0.3);
}

.lote-status.pausado {
  background: rgba(245, 158, 11, 0.15);
  color: #f59e0b;
  border: 1px solid rgba(245, 158, 11, 0.3);
}

.lote-status.encerrado {
  background: rgba(239, 68, 68, 0.15);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}

.lote-acoes {
  display: flex;
  gap: 8px;
}

.btn-editar, .btn-excluir {
  background: rgba(255, 255, 255, 0.05);
  border: none;
  border-radius: 6px;
  padding: 6px;
  cursor: pointer;
  color: #8e9aaf;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-editar:hover {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
}

.btn-excluir:hover {
  background: rgba(239, 68, 68, 0.15);
  color: #ef4444;
}

.lote-body {
  padding: 16px 20px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.info-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 0.85rem;
}

.info-label {
  color: #6b7280;
}

.info-value {
  color: #e0e0e0;
  font-weight: 500;
}

.info-value.esgotando {
  color: #f59e0b;
  font-weight: 700;
}

.lote-item {
  background: rgba(0, 0, 0, 0.15);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 8px;
  padding: 10px 14px;
  margin-bottom: 8px;
  font-size: 0.8rem;
}

.item-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 4px;
  color: #e0e0e0;
}

.item-stats {
  display: flex;
  justify-content: space-between;
  color: #6b7280;
  margin-bottom: 6px;
  font-size: 0.75rem;
}

.item-stats .esgotando {
  color: #f59e0b;
}

.mini-progress {
  height: 4px;
}

.lote-footer {
  padding: 16px 20px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.progress-container {
  display: flex;
  align-items: center;
  gap: 12px;
}

.progress-bar {
  flex: 1;
  height: 6px;
  background: #0f131e;
  border-radius: 10px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background: linear-gradient(90deg, #c9a84c, #d4af37);
  border-radius: 10px;
  transition: width 0.3s;
}

.progress-text {
  font-size: 0.7rem;
  color: #6b7280;
  min-width: 60px;
  text-align: right;
}

.empty-state {
  text-align: center;
  padding: 60px 20px;
}

.empty-state svg {
  color: #c9a84c;
  opacity: 0.4;
  margin-bottom: 16px;
}

.empty-state h3 {
  font-size: 1.1rem;
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

@media (max-width: 900px) {
  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }
  
  .lotes-grid {
    grid-template-columns: 1fr;
  }
  
  .form-row {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 600px) {
  .stats-grid {
    grid-template-columns: 1fr;
  }
  
  .produtor-header {
    flex-direction: column;
    align-items: flex-start;
  }
  
  .lotes-section {
    padding: 20px;
  }
}
</style>
