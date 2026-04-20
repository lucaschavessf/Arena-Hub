<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-content">
      <div class="checkout-container" v-if="evento">
        <section class="event-summary-card">
          <div class="event-image">
            <img :src="evento.image" :alt="evento.title" />
            <div class="image-overlay"></div>
          </div>
          <div class="event-details">
            <span class="category-badge">{{ evento.category }}</span>
            <h1 class="event-title">{{ evento.title }}</h1>
            <div class="event-meta">
              <div class="meta-item">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/></svg>
                <span>{{ evento.date }}</span>
              </div>
              <div class="meta-item">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/></svg>
                <span>{{ evento.time }}</span>
              </div>
              <div class="meta-item">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/></svg>
                <span>{{ evento.venue }}</span>
              </div>
            </div>
            
            <p class="event-description" v-if="evento.description">{{ evento.description }}</p>
          </div>
        </section>

        <section class="ticket-selection-area">
          <header class="section-header">
            <h2 class="section-title">Escolha seus Ingressos</h2>
            <p class="section-subtitle">Selecione a quantidade para cada setor disponível</p>
          </header>

          <div class="tickets-list">
            <div class="table-header-desktop">
              <span>Setor / Tipo</span>
              <span class="text-center">Quantidade</span>
              <span class="text-center">Preço Unit.</span>
              <span class="text-right">Subtotal</span>
            </div>

            <div v-for="(ing, idx) in evento.ingressos" :key="idx" 
                 class="ticket-item" 
                 :class="{ 'is-selected': (cart.ingressos[idx] > 0) }">
              
              <div class="ticket-info">
                <span class="ticket-name">{{ ing.tipo }}</span>
                <span v-if="ing.subtipo" class="ticket-subtext">{{ ing.subtipo }}</span>
                <div class="mobile-price-tag">R$ {{ ing.preco.toFixed(2) }}</div>
              </div>

              <div class="ticket-qty-control">
                <button class="control-btn" @click="alterarQuantidade(idx, -1)" :disabled="!cart.ingressos[idx]">
                  <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3"><line x1="5" y1="12" x2="19" y2="12"/></svg>
                </button>
                <span class="qty-display">{{ cart.ingressos[idx] || 0 }}</span>
                <button class="control-btn" @click="alterarQuantidade(idx, 1)">
                  <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3"><line x1="12" y1="5" x2="12" y2="19"/><line x1="5" y1="12" x2="19" y2="12"/></svg>
                </button>
              </div>

              <div class="desktop-only text-center price-unit">R$ {{ ing.preco.toFixed(2) }}</div>
              
              <div class="ticket-subtotal text-right">
                <span class="subtotal-label">Subtotal:</span>
                <span class="subtotal-value">R$ {{ calcularSubtotalLocal(idx).toFixed(2) }}</span>
              </div>
            </div>
          </div>

          <footer class="checkout-footer">
            <div class="checkout-summary-details" v-if="cart.totalItens > 0">
              <div class="summary-line">
                <span>Ingressos ({{ cart.totalItens }}x):</span>
                <span class="white-text">R$ {{ totalNominal.toFixed(2) }}</span>
              </div>
              <div class="summary-line">
                <span>Taxas de conveniência:</span>
                <span class="white-text">R$ {{ totalTaxas.toFixed(2) }}</span>
              </div>
              <div class="summary-line highlight">
                <span>Total do Pedido:</span>
                <span class="gold-text">R$ {{ totalGeral.toFixed(2) }}</span>
              </div>
            </div>

            <div class="checkout-actions">
              <div class="total-display" v-if="totalGeral > 0">
                <span class="label">VALOR TOTAL</span>
                <span class="val">R$ {{ totalGeral.toFixed(2) }}</span>
              </div>
              <div class="empty-msg" v-else>Selecione ao menos um ingresso</div>

              <button class="btn-checkout" :disabled="totalGeral === 0" @click="irParaPagamento">
                <span>FECHAR PEDIDO</span>
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><line x1="5" y1="12" x2="19" y2="12"/><polyline points="12 5 19 12 12 19"/></svg>
              </button>
            </div>
          </footer>
        </section>
      </div>
    </main>
    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'
import { useCartStore } from '../stores/cart'

const route = useRoute()
const router = useRouter()
const cart = useCartStore()

const eventoId = computed(() => Number(route.params.id))
const evento = ref<any>(null)

onMounted(async () => {
  window.scrollTo(0, 0)
  try {
    const response = await fetch(`http://localhost:8080/eventos/${eventoId.value}`)
    if (!response.ok) {
      throw new Error('Erro ao buscar evento da API')
    }
    const data = await response.json()
    
    evento.value = {
      id: data.id,
      title: data.nome,
      category: data.categoria || 'Show',
      date: data.dataInicio ? new Date(data.dataInicio).toLocaleDateString('pt-BR', { day: '2-digit', month: 'long', year: 'numeric' }) : 'Data a definir',
      time: data.dataInicio ? new Date(data.dataInicio).toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }) : '20:00',
      venue: 'Arena Hub',
      image: 'https://images.unsplash.com/photo-1540039155733-5bb30b53aa14?w=600&q=80',
      description: data.descricao,
      // Ingressos fixos (mock) para manter o checkout funcionando
      ingressos: [
        { tipo: 'PISTA', subtipo: null, preco: 85.00, taxa: 8.50 },
        { tipo: 'PISTA PREMIUM', subtipo: 'Acesso VIP', preco: 150.00, taxa: 15.00 }
      ]
    }
    cart.eventoSelecionado = evento.value
  } catch (error) {
    console.error('Erro ao carregar evento:', error)
  }
})

const alterarQuantidade = (idx: number, delta: number) => {
  const atual = cart.ingressos[idx] || 0
  cart.setQuantidade(idx, Math.max(0, atual + delta))
}

const calcularSubtotalLocal = (idx: number): number => {
  if (!evento.value) return 0
  const ing = evento.value.ingressos[idx]
  const qty = cart.ingressos[idx] || 0
  return qty * (ing.preco + ing.taxa)
}

const totalNominal = computed(() => {
  if (!evento.value) return 0
  return evento.value.ingressos.reduce((sum: number, ing: any, idx: number) => {
    return sum + ((cart.ingressos[idx] || 0) * ing.preco)
  }, 0)
})

const totalTaxas = computed(() => {
  if (!evento.value) return 0
  return evento.value.ingressos.reduce((sum: number, ing: any, idx: number) => {
    return sum + ((cart.ingressos[idx] || 0) * ing.taxa)
  }, 0)
})

const totalGeral = computed(() => totalNominal.value + totalTaxas.value)

const irParaPagamento = () => {
  if (totalGeral.value > 0) {
    router.push({ name: 'Pagamento' }).catch(err => console.error(err))
  }
}
</script>

<style scoped>
.page { background: #0a0e17; min-height: 100vh; display: flex; flex-direction: column; }

.main-content { 
  flex: 1;
  padding: 60px 20px; 
  display: flex;
  justify-content: center;
  width: 100%;
}

.checkout-container { 
  width: 100%;
  max-width: 1100px; /* ALINHAMENTO MESTRE */
  display: flex; 
  flex-direction: column; 
  gap: 32px; 
}

/* Event Summary Card - Desktop Proportions */
.event-summary-card {
  display: grid; 
  grid-template-columns: 380px 1fr;
  background: #121826; 
  border-radius: 20px; 
  overflow: hidden;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.event-image { position: relative; height: 100%; }
.event-image img { width: 100%; height: 100%; object-fit: cover; }
.image-overlay { position: absolute; inset: 0; background: linear-gradient(to right, transparent, #121826); }

.event-details { padding: 40px; display: flex; flex-direction: column; justify-content: center; }
.category-badge { background: #c9a84c; color: #0a0e17; padding: 4px 12px; border-radius: 20px; font-size: 0.75rem; font-weight: 800; text-transform: uppercase; margin-bottom: 16px; width: fit-content; }
.event-title { font-size: 2rem; font-weight: 800; margin-bottom: 20px; color: #fff; }
.event-meta { display: flex; flex-wrap: wrap; gap: 24px; color: #8e9aaf; font-size: 0.95rem; }
.meta-item { display: flex; align-items: center; gap: 8px; }
.meta-item svg { color: #c9a84c; }
.event-description { margin-top: 24px; color: #d1d5db; font-size: 1.05rem; line-height: 1.6; }

/* Tickets Area */
.ticket-selection-area { background: #121826; border-radius: 20px; padding: 40px; border: 1px solid rgba(255, 255, 255, 0.05); }
.section-header { margin-bottom: 32px; }
.section-title { font-size: 1.5rem; font-weight: 700; color: #fff; }
.section-subtitle { color: #8e9aaf; font-size: 1rem; }

/* Grid de Ingressos Otimizado */
.table-header-desktop {
  display: grid; 
  grid-template-columns: 1fr 160px 140px 160px;
  padding: 0 20px 16px; 
  border-bottom: 1px solid rgba(255,255,255,0.1);
  color: #c9a84c; 
  font-size: 0.8rem; 
  font-weight: 700; 
  text-transform: uppercase;
}

.ticket-item {
  display: grid; 
  grid-template-columns: 1fr 160px 140px 160px;
  align-items: center; 
  padding: 24px 20px; 
  border-bottom: 1px solid rgba(255,255,255,0.05);
  transition: background 0.3s;
}
.is-selected { background: rgba(201, 168, 76, 0.05); }

.ticket-name { display: block; font-size: 1.2rem; font-weight: 700; color: #fff; }
.ticket-subtext { font-size: 0.85rem; color: #8e9aaf; }
.price-unit { color: #fff; font-weight: 500; }

/* Qty Controls */
.ticket-qty-control { display: flex; align-items: center; gap: 16px; justify-content: center; }
.control-btn {
  width: 36px; height: 36px; border-radius: 50%; border: 2px solid #c9a84c;
  background: transparent; color: #c9a84c; display: flex; align-items: center; justify-content: center;
  cursor: pointer; transition: 0.2s;
}
.control-btn:hover:not(:disabled) { background: #c9a84c; color: #121826; }
.qty-display { font-size: 1.2rem; font-weight: 800; min-width: 30px; text-align: center; color: #fff; }

/* Resumo de Pagamento */
.checkout-footer {
  margin-top: 40px; background: #1c2433; border-radius: 20px;
  padding: 40px; border: 1px solid rgba(201, 168, 76, 0.2);
}
.checkout-summary-details {
  display: flex; flex-direction: column; gap: 14px;
  padding-bottom: 30px; margin-bottom: 30px; border-bottom: 1px solid rgba(255,255,255,0.1);
}
.summary-line { display: flex; justify-content: space-between; font-size: 1rem; color: #8e9aaf; }
.summary-line.highlight { font-size: 1.3rem; font-weight: 800; margin-top: 10px; padding-top: 15px; border-top: 1px dashed rgba(255,255,255,0.2); }
.white-text { color: #fff; }
.gold-text { color: #c9a84c; }

.checkout-actions { display: flex; justify-content: space-between; align-items: center; }
.total-display .label { font-size: 0.85rem; color: #8e9aaf; font-weight: 700; display: block; }
.total-display .val { font-size: 2.2rem; font-weight: 900; color: #c9a84c; }

.btn-checkout {
  background: #c9a84c; color: #0a0e17; border: none; padding: 20px 48px;
  border-radius: 14px; font-weight: 800; font-size: 1.1rem; cursor: pointer;
  display: flex; align-items: center; gap: 12px; transition: 0.3s;
}
.btn-checkout:hover:not(:disabled) { transform: translateY(-3px); box-shadow: 0 10px 25px rgba(201, 168, 76, 0.3); }

/* Responsive adjustments */
@media (max-width: 900px) {
  .event-summary-card { grid-template-columns: 1fr; }
  .table-header-desktop { display: none; }
  .ticket-item { grid-template-columns: 1fr auto; }
  .checkout-actions { flex-direction: column; gap: 30px; text-align: center; }
  .btn-checkout { width: 100%; }
}

.text-center { text-align: center; }
.text-right { text-align: right; }
</style>