<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-content">
      <div class="checkout-container" v-if="evento">
        <div class="breadcrumb">
          <router-link to="/" class="breadcrumb-link">Início</router-link>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
          <router-link :to="`/evento/${evento.id}`" class="breadcrumb-link">{{ evento.nome }}</router-link>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
          <span class="breadcrumb-current">Comprar Ingressos</span>
        </div>

        <section class="event-summary-card">
          <div class="event-image">
            <img src="https://images.unsplash.com/photo-1540039155733-5bb30b53aa14?w=600&q=80" :alt="evento.nome" />
            <div class="image-overlay"></div>
          </div>
          <div class="event-details">
            <span class="category-badge">{{ evento.categoria || 'Sem categoria' }}</span>
            <h1 class="event-title">{{ evento.nome }}</h1>
            <div class="event-meta">
              <div class="meta-item">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
                </svg>
                <span>{{ formatDate(evento.dataInicio) }}</span>
              </div>
              <div class="meta-item">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/>
                </svg>
                <span>{{ evento.espaco || 'Local não informado' }}</span>
              </div>
            </div>
            <p class="event-description" v-if="evento.descricao">{{ evento.descricao }}</p>
          </div>
        </section>

        <section class="points-card" v-if="usuarioLogado">
          <div class="points-header">
            <div class="points-header-left">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round">
                <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2" />
              </svg>
              <div>
                <h3>Seus Pontos</h3>
                <p>Acumule e troque por descontos!</p>
              </div>
            </div>
            <div class="points-value-large">
              <span class="points-number">{{ usuarioPontos }}</span>
              <span class="points-label">pontos</span>
            </div>
          </div>

          <div class="points-conversion" v-if="usuarioPontos >= 100 && totalGeral > 0">
            <div class="conversion-info">
              <span class="conversion-rate">💰 100 pontos = R$ 10,00 de desconto</span>
              <span class="conversion-max">🔒 Máximo: 50% do valor do pedido</span>
            </div>
            
            <div class="slider-container">
              <input 
                type="range" 
                v-model.number="pontosUsar" 
                :min="0" 
                :max="pontosMaximos" 
                :step="100"
                class="points-slider"
              />
              <div class="slider-values">
                <span>0</span>
                <span>{{ pontosMaximos }}</span>
              </div>
            </div>

            <div class="conversion-control">
              <button class="control-btn-small" @click="diminuirPontos" :disabled="pontosUsar < 100">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                  <line x1="5" y1="12" x2="19" y2="12"/>
                </svg>
              </button>
              <div class="pontos-selecionados">
                <span class="pontos-quantidade">{{ pontosUsar }}</span>
                <span class="pontos-equivalente">pontos = R$ {{ (pontosUsar / 10).toFixed(2) }}</span>
              </div>
              <button class="control-btn-small" @click="aumentarPontos" :disabled="pontosUsar >= pontosMaximos">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                  <line x1="12" y1="5" x2="12" y2="19"/><line x1="5" y1="12" x2="19" y2="12"/>
                </svg>
              </button>
            </div>
          </div>

          <div v-else-if="usuarioPontos < 100 && totalGeral > 0" class="pontos-insuficientes">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="12" r="10" />
              <path d="M12 8v4M12 16h.01" />
            </svg>
            <span>💡 Acumule mais {{ 100 - usuarioPontos }} pontos para começar a usar descontos!</span>
          </div>
        </section>

        <section class="ticket-selection-area">
          <header class="section-header">
            <div class="header-content">
              <h2 class="section-title">Escolha seus Ingressos</h2>
              <div class="title-line"></div>
            </div>
            <p class="section-subtitle">Selecione a quantidade para cada setor disponível</p>
          </header>

          <div class="tickets-list" v-if="!lotes || lotes.length === 0">
            <div class="empty-msg" style="justify-content: center; font-size: 1.1rem; padding: 30px;">
              Nenhum Ingresso Disponível
            </div>
          </div>

          <div class="tickets-list" v-else>
            <div class="table-header-desktop">
              <span>Setor / Tipo</span>
              <span class="text-center">Quantidade</span>
              <span class="text-center">Preço Unit.</span>
              <span class="text-right">Subtotal</span>
            </div>

            <div v-for="lote in lotes" :key="lote.id" 
                 class="ticket-item" 
                 :class="{ 'is-selected': (quantidades[lote.id] > 0) }">
              
              <div class="ticket-info">
                <span class="ticket-name">{{ lote.categoria || 'Ingresso' }}</span>
                <div class="mobile-price-tag">R$ {{ lote.preco.toFixed(2) }}</div>
              </div>

              <div class="ticket-qty-control">
                <button class="control-btn" @click="alterarQuantidade(lote.id, -1)" :disabled="!quantidades[lote.id]">
                  <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                    <line x1="5" y1="12" x2="19" y2="12"/>
                  </svg>
                </button>
                <span class="qty-display">{{ quantidades[lote.id] || 0 }}</span>
                <button class="control-btn" @click="alterarQuantidade(lote.id, 1)" :disabled="quantidades[lote.id] >= lote.quantidadeDisponivel">
                  <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                    <line x1="12" y1="5" x2="12" y2="19"/><line x1="5" y1="12" x2="19" y2="12"/>
                  </svg>
                </button>
              </div>

              <div class="desktop-only text-center price-unit">
                <span class="price-value">R$ {{ lote.preco.toFixed(2) }}</span>
              </div>
              
              <div class="ticket-subtotal text-right">
                <span class="subtotal-label">Subtotal:</span>
                <span class="subtotal-value">R$ {{ calcularSubtotalLocal(lote).toFixed(2) }}</span>
              </div>
            </div>
          </div>

          <footer class="checkout-footer">
            <div class="checkout-summary-details" v-if="totalItens > 0">
              <div class="summary-line">
                <span>Ingressos ({{ totalItens }}x):</span>
                <span class="white-text">R$ {{ totalGeral.toFixed(2) }}</span>
              </div>
              <div class="summary-line discount-line" v-if="descontoPontos > 0">
                <span>🎁 Desconto por pontos:</span>
                <span class="discount-value">- R$ {{ descontoPontos.toFixed(2) }}</span>
              </div>
              <div class="summary-line highlight">
                <span>Total do Pedido:</span>
                <span class="gold-text">R$ {{ totalComDesconto.toFixed(2) }}</span>
              </div>
              <div class="summary-line points-saved-line" v-if="descontoPontos > 0">
                <span>✨ Você economizou {{ (descontoPontos * 10).toFixed(0) }} pontos!</span>
              </div>
            </div>

            <div class="checkout-actions">
              <div class="total-display" v-if="totalGeral > 0">
                <span class="label">VALOR TOTAL</span>
                <span class="val">R$ {{ totalComDesconto.toFixed(2) }}</span>
                <span class="installment-info">em até 12x sem juros</span>
                <span v-if="descontoPontos > 0" class="original-price">Original: R$ {{ totalGeral.toFixed(2) }}</span>
              </div>
              <div class="empty-msg" v-else>
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/>
                </svg>
                <span>Selecione ao menos um ingresso para continuar</span>
              </div>

              <button class="btn-checkout" :disabled="totalGeral === 0" @click="irParaPagamento">
                <span>FECHAR PEDIDO</span>
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <line x1="5" y1="12" x2="19" y2="12"/>
                  <polyline points="12 5 19 12 12 19"/>
                </svg>
              </button>
            </div>
          </footer>
        </section>
      </div>
      <div v-else style="color: white; font-size: 1.2rem;">Carregando dados do evento...</div>
    </main>
    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'
import api from '@/services/api'

const route = useRoute()
const router = useRouter()

const eventoId = computed(() => Number(route.params.id))
const evento = ref<any>(null)
const lotes = ref<any[]>([])

const quantidades = ref<Record<number, number>>({})

const usuarioLogado = ref(true)
const usuarioPontos = ref(1250)

const pontosUsar = ref(0)
const pontosMaximos = ref(0)
const descontoPontos = ref(0)

const formatDate = (dateStr: string) => {
  if (!dateStr) return '';
  const d = new Date(dateStr);
  return d.toLocaleString('pt-BR', { dateStyle: 'short', timeStyle: 'short' });
}

const calcularPontosMaximos = () => {
  const maxDescontoPercentual = 50
  const maxDescontoValor = totalGeral.value * (maxDescontoPercentual / 100)
  const maxDescontoPontos = Math.floor(maxDescontoValor * 10)
  return Math.min(usuarioPontos.value, maxDescontoPontos)
}

const atualizarDesconto = () => {
  descontoPontos.value = pontosUsar.value / 10
}

const totalItens = computed(() => {
  return Object.values(quantidades.value).reduce((acc, qty) => acc + qty, 0)
})

const totalGeral = computed(() => {
  if (!lotes.value) return 0
  return lotes.value.reduce((sum: number, lote: any) => {
    return sum + ((quantidades.value[lote.id] || 0) * lote.preco)
  }, 0)
})

const totalComDesconto = computed(() => {
  const comDesconto = totalGeral.value - descontoPontos.value
  return comDesconto > 0 ? comDesconto : 0
})

watch([totalGeral, () => usuarioPontos.value], () => {
  pontosMaximos.value = calcularPontosMaximos()
  if (pontosUsar.value > pontosMaximos.value) {
    pontosUsar.value = pontosMaximos.value
    atualizarDesconto()
  }
  if (totalGeral.value === 0 && pontosUsar.value > 0) {
    pontosUsar.value = 0
    atualizarDesconto()
  }
})

const aumentarPontos = () => {
  const incremento = 100
  const novoValor = pontosUsar.value + incremento
  if (novoValor <= pontosMaximos.value && novoValor <= usuarioPontos.value) {
    pontosUsar.value = novoValor
    atualizarDesconto()
  }
}

const diminuirPontos = () => {
  const decremento = 100
  const novoValor = pontosUsar.value - decremento
  if (novoValor >= 0) {
    pontosUsar.value = novoValor
    atualizarDesconto()
  }
}

const alterarQuantidade = (loteId: number, delta: number) => {
  const atual = quantidades.value[loteId] || 0
  const nova = Math.max(0, atual + delta)
  quantidades.value[loteId] = nova
}

const calcularSubtotalLocal = (lote: any): number => {
  const qty = quantidades.value[lote.id] || 0
  return qty * lote.preco
}

const irParaPagamento = () => {
  if (totalComDesconto.value > 0) {
    router.push('/pagamento')
  }
}

const carregarEvento = async () => {
  try {
    const response = await api.get(`/eventos/${eventoId.value}`)
    evento.value = response.data
    lotes.value = response.data.lotes || []
    
    // Iniciar quantidades zeradas
    lotes.value.forEach((lote: any) => {
      quantidades.value[lote.id] = 0
    })
  } catch (error) {
    console.error('Erro ao carregar dados do evento:', error)
  }
}

onMounted(() => {
  window.scrollTo(0, 0)
  carregarEvento()
})
</script>

<style scoped>
.page { 
  background: #0a0e17; 
  min-height: 100vh; 
  display: flex; 
  flex-direction: column; 
}

.main-content { 
  flex: 1;
  padding: 60px 20px; 
  display: flex;
  justify-content: center;
  width: 100%;
}

.checkout-container { 
  width: 100%;
  max-width: 1100px;
  display: flex; 
  flex-direction: column; 
  gap: 32px; 
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 8px;
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

.event-summary-card {
  display: grid; 
  grid-template-columns: 300px 1fr;
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 24px; 
  overflow: hidden;
  border: 1px solid rgba(201, 168, 76, 0.12);
}

.event-image { 
  position: relative; 
  height: 100%;
  min-height: 280px;
}

.event-image img { 
  width: 100%; 
  height: 100%; 
  object-fit: cover; 
}

.image-overlay { 
  position: absolute; 
  inset: 0; 
  background: linear-gradient(to right, transparent, #121826); 
}

.event-details { 
  padding: 32px; 
  display: flex; 
  flex-direction: column; 
  justify-content: center; 
}

.category-badge { 
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17; 
  padding: 4px 12px; 
  border-radius: 20px; 
  font-size: 0.7rem; 
  font-weight: 800; 
  text-transform: uppercase; 
  margin-bottom: 16px; 
  width: fit-content; 
}

.event-title { 
  font-size: 1.6rem; 
  font-weight: 800;
  margin-bottom: 16px; 
  color: #fff; 
}

.event-meta { 
  display: flex; 
  flex-wrap: wrap; 
  gap: 16px; 
  color: #8e9aaf; 
  font-size: 0.85rem; 
  margin-bottom: 16px;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 6px;
}

.meta-item svg {
  color: #c9a84c;
  width: 16px;
  height: 16px;
}

.event-description { 
  color: #b0b8c5;
  font-size: 0.9rem;
  line-height: 1.5;
  margin: 0;
}

.points-card {
  background: linear-gradient(135deg, #1a2538 0%, #121826 100%);
  border-radius: 20px;
  padding: 24px;
  border: 1px solid rgba(201, 168, 76, 0.2);
}

.points-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  flex-wrap: wrap;
  gap: 16px;
}

.points-header-left {
  display: flex;
  align-items: center;
  gap: 12px;
}

.points-header-left svg {
  color: #c9a84c;
  width: 32px;
  height: 32px;
}

.points-header-left h3 {
  font-size: 1rem;
  font-weight: 700;
  color: #c9a84c;
  margin-bottom: 4px;
}

.points-header-left p {
  font-size: 0.75rem;
  color: #8e9aaf;
  margin: 0;
}

.points-value-large {
  text-align: center;
  background: rgba(201, 168, 76, 0.1);
  padding: 8px 20px;
  border-radius: 20px;
}

.points-number {
  font-size: 1.8rem;
  font-weight: 800;
  color: #c9a84c;
}

.points-label {
  font-size: 0.75rem;
  color: #8e9aaf;
  margin-left: 6px;
}

.points-conversion {
  background: rgba(255, 255, 255, 0.02);
  border-radius: 16px;
  padding: 20px;
}

.conversion-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
  flex-wrap: wrap;
  gap: 8px;
}

.conversion-rate {
  color: #c9a84c;
  font-weight: 600;
  font-size: 0.85rem;
}

.conversion-max {
  color: #6b7280;
  font-size: 0.75rem;
}

.slider-container {
  margin-bottom: 20px;
}

.points-slider {
  width: 100%;
  height: 4px;
  -webkit-appearance: none;
  background: #1a2233;
  border-radius: 2px;
  outline: none;
}

.points-slider::-webkit-slider-thumb {
  -webkit-appearance: none;
  width: 16px;
  height: 16px;
  border-radius: 50%;
  background: #c9a84c;
  cursor: pointer;
}

.slider-values {
  display: flex;
  justify-content: space-between;
  margin-top: 6px;
  font-size: 0.7rem;
  color: #6b7280;
}

.conversion-control {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 20px;
}

.control-btn-small {
  width: 36px;
  height: 36px;
  border-radius: 10px;
  border: 2px solid rgba(201, 168, 76, 0.5);
  background: transparent;
  color: #c9a84c;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.3s;
}

.control-btn-small:hover:not(:disabled) {
  background: #c9a84c;
  color: #0a0e17;
}

.control-btn-small:disabled {
  opacity: 0.3;
  cursor: not-allowed;
}

.pontos-selecionados {
  text-align: center;
  min-width: 120px;
}

.pontos-quantidade {
  display: block;
  font-size: 1.3rem;
  font-weight: 800;
  color: #c9a84c;
}

.pontos-equivalente {
  font-size: 0.7rem;
  color: #6b7280;
}

.pontos-insuficientes {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  padding: 16px;
  background: rgba(239, 68, 68, 0.1);
  border-radius: 12px;
  color: #f59e0b;
  font-size: 0.85rem;
}

.ticket-selection-area { 
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 24px; 
  padding: 32px; 
  border: 1px solid rgba(201, 168, 76, 0.12);
}

.section-header { 
  margin-bottom: 28px; 
}

.header-content {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 6px;
}

.section-title { 
  font-size: 1.3rem; 
  font-weight: 700; 
  color: #fff; 
  margin: 0;
}

.title-line {
  flex: 1;
  height: 2px;
  background: linear-gradient(90deg, #c9a84c, transparent);
}

.section-subtitle { 
  color: #8e9aaf; 
  font-size: 0.85rem; 
  margin-left: 8px;
}

.table-header-desktop {
  display: grid; 
  grid-template-columns: 1fr 140px 120px 140px;
  padding: 0 20px 12px; 
  border-bottom: 2px solid rgba(201, 168, 76, 0.2);
  color: #c9a84c; 
  font-size: 0.7rem; 
  font-weight: 700; 
  text-transform: uppercase;
}

.ticket-item {
  display: grid; 
  grid-template-columns: 1fr 140px 120px 140px;
  align-items: center; 
  padding: 20px; 
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  transition: all 0.3s;
  border-radius: 12px;
}

.ticket-item:hover {
  background: rgba(28, 36, 51, 0.5);
}

.is-selected { 
  background: linear-gradient(90deg, rgba(201, 168, 76, 0.08), transparent);
  border-left: 3px solid #c9a84c;
}

.ticket-name { 
  display: block; 
  font-size: 1rem; 
  font-weight: 700; 
  color: #fff; 
  margin-bottom: 4px;
}

.ticket-subtext { 
  font-size: 0.75rem; 
  color: #8e9aaf; 
}

.ticket-qty-control { 
  display: flex; 
  align-items: center; 
  gap: 12px; 
  justify-content: center; 
}

.control-btn {
  width: 32px; 
  height: 32px; 
  border-radius: 10px; 
  border: 2px solid rgba(201, 168, 76, 0.5);
  background: transparent; 
  color: #c9a84c; 
  display: flex; 
  align-items: center; 
  justify-content: center;
  cursor: pointer; 
  transition: all 0.3s;
}

.control-btn:hover:not(:disabled) { 
  background: #c9a84c; 
  color: #121826; 
}

.control-btn:disabled {
  opacity: 0.3;
  cursor: not-allowed;
}

.qty-display { 
  font-size: 1.1rem; 
  font-weight: 800; 
  min-width: 30px; 
  text-align: center; 
  color: #fff; 
}

.price-unit {
  text-align: center;
}

.price-value {
  color: #fff; 
  font-weight: 600;
  font-size: 0.95rem;
}

.tax-info {
  font-size: 0.65rem;
  color: #8e9aaf;
  display: block;
}

.ticket-subtotal {
  text-align: right;
}

.subtotal-label {
  font-size: 0.65rem;
  color: #8e9aaf;
  display: block;
}

.subtotal-value {
  font-size: 1rem;
  font-weight: 700;
  color: #c9a84c;
  display: block;
}

.text-center { text-align: center; }
.text-right { text-align: right; }
.desktop-only { display: block; }
.mobile-price-tag { display: none; }

.checkout-footer {
  margin-top: 32px; 
  background: linear-gradient(135deg, #1a2233 0%, #151c2a 100%);
  border-radius: 20px;
  padding: 28px; 
  border: 1px solid rgba(201, 168, 76, 0.15);
}

.checkout-summary-details {
  display: flex; 
  flex-direction: column; 
  gap: 12px;
  padding-bottom: 24px; 
  margin-bottom: 24px; 
  border-bottom: 1px solid rgba(255, 255, 255, 0.08);
}

.summary-line { 
  display: flex; 
  justify-content: space-between; 
  font-size: 0.9rem; 
  color: #8e9aaf; 
}

.summary-line.highlight { 
  font-size: 1.2rem; 
  font-weight: 800; 
  margin-top: 12px; 
  padding-top: 16px; 
  border-top: 2px dashed rgba(201, 168, 76, 0.3);
}

.white-text { 
  color: #fff; 
  font-weight: 600;
}

.gold-text { 
  color: #c9a84c; 
  font-size: 1.3rem;
}

.discount-line {
  color: #10b981;
}

.discount-value {
  color: #10b981;
  font-weight: 700;
}

.points-saved-line {
  font-size: 0.75rem;
  color: #10b981;
  justify-content: flex-end;
}

.checkout-actions { 
  display: flex; 
  justify-content: space-between; 
  align-items: center; 
  flex-wrap: wrap;
  gap: 20px;
}

.total-display {
  text-align: left;
}

.total-display .label { 
  font-size: 0.7rem; 
  color: #8e9aaf; 
  font-weight: 700; 
  display: block; 
  letter-spacing: 1px;
}

.total-display .val { 
  font-size: 2rem; 
  font-weight: 900; 
  color: #c9a84c; 
  line-height: 1.2;
}

.installment-info {
  font-size: 0.7rem;
  color: #8e9aaf;
  display: block;
}

.original-price {
  display: block;
  font-size: 0.7rem;
  color: #6b7280;
  text-decoration: line-through;
  margin-top: 4px;
}

.empty-msg {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #8e9aaf;
  font-size: 0.85rem;
  padding: 10px 16px;
  background: rgba(255, 94, 94, 0.05);
  border-radius: 10px;
}

.empty-msg svg {
  color: #ff5e5e;
  width: 18px;
  height: 18px;
}

.btn-checkout {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17; 
  border: none; 
  padding: 16px 40px;
  border-radius: 14px; 
  font-weight: 800; 
  font-size: 1rem; 
  cursor: pointer;
  display: flex; 
  align-items: center; 
  gap: 10px; 
  transition: all 0.3s;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.btn-checkout:hover:not(:disabled) { 
  transform: translateY(-2px); 
  box-shadow: 0 8px 20px rgba(201, 168, 76, 0.3);
}

.btn-checkout:disabled {
  opacity: 0.4;
  cursor: not-allowed;
}

@media (max-width: 900px) {
  .event-summary-card { 
    grid-template-columns: 1fr; 
  }
  
  .image-overlay {
    background: linear-gradient(to bottom, transparent, #121826);
  }
  
  .table-header-desktop { 
    display: none; 
  }
  
  .ticket-item { 
    grid-template-columns: 1fr auto; 
    gap: 12px;
  }
  
  .desktop-only { 
    display: none; 
  }
  
  .mobile-price-tag {
    display: block;
    color: #c9a84c;
    font-weight: 700;
    font-size: 0.85rem;
    margin-top: 6px;
  }
  
  .ticket-subtotal {
    grid-column: span 2;
    text-align: left;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .subtotal-label, .subtotal-value {
    display: inline-block;
  }
  
  .subtotal-value {
    font-size: 1rem;
  }
  
  .checkout-actions { 
    flex-direction: column; 
    text-align: center; 
  }
  
  .btn-checkout { 
    width: 100%; 
    justify-content: center;
  }
  
  .total-display {
    text-align: center;
  }
}

@media (max-width: 600px) {
  .main-content {
    padding: 40px 16px;
  }
  
  .event-details {
    padding: 24px;
  }
  
  .event-title {
    font-size: 1.3rem;
  }
  
  .ticket-selection-area {
    padding: 24px 16px;
  }
  
  .checkout-footer {
    padding: 20px;
  }
  
  .ticket-item {
    padding: 16px;
  }
  
  .points-header {
    flex-direction: column;
    text-align: center;
  }
  
  .points-header-left {
    flex-direction: column;
  }
  
  .conversion-info {
    flex-direction: column;
    text-align: center;
  }
  
  .total-display .val {
    font-size: 1.6rem;
  }
  
  .btn-checkout {
    padding: 14px 24px;
    font-size: 0.9rem;
  }
}
</style>