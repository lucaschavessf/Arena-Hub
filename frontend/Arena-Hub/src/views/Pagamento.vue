<template>
  <div class="page">
    <AppNavbar />

    <main class="main-payment">
      <div class="payment-container">
        <div class="breadcrumb-wrapper">
          <div class="breadcrumb">
            <router-link to="/" class="breadcrumb-link">Início</router-link>
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
            <router-link to="/eventos" class="breadcrumb-link">Eventos</router-link>
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
            <span class="breadcrumb-current">Pagamento</span>
          </div>
        </div>

        <div class="event-summary">
          <div class="event-image">
            <img :src="eventoInfo.imagem" :alt="eventoInfo.nome" />
          </div>
          <div class="event-info">
            <h2 class="event-name">{{ eventoInfo.nome }}</h2>
            <div class="event-meta">
              <div class="meta-item">
                <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="4" width="18" height="18" rx="2"/>
                  <path d="M16 2v4M8 2v4M3 10h18"/>
                </svg>
                <span>{{ eventoInfo.data }}</span>
              </div>
              <div class="meta-item">
                <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="12" r="10"/>
                  <path d="M12 6v6l4 2"/>
                </svg>
                <span>{{ eventoInfo.horario }}</span>
              </div>
              <div class="meta-item">
                <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/>
                  <circle cx="12" cy="10" r="3"/>
                </svg>
                <span>{{ eventoInfo.local }}</span>
              </div>
            </div>
          </div>
        </div>

        <div class="payment-methods">
          <header class="section-header">
            <div class="title-wrapper">
              <h1 class="page-title">Finalizar Pagamento</h1>
              <div class="title-line"></div>
            </div>
            <p class="page-subtitle">Escolha a forma de pagamento de sua preferência</p>
          </header>

          <div class="methods-grid">
            <label class="method-card" :class="{ active: metodo === 'pix' }">
              <input type="radio" v-model="metodo" value="pix" name="pagamento" />
              <div class="method-icon">
                <svg
                  viewBox="0 0 135 135"
                  width="24"
                  height="24"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                >
                  <g fill="#32BCAD">
                    <path d="M67.31 0L100.86 33.55 67.31 67.11 33.76 33.55 67.31 0z" />
                    <path d="M101.22 33.91L134.77 67.46 101.22 101.02 67.66 67.46 101.22 33.91z" />
                    <path d="M67.31 67.66L100.86 101.22 67.31 134.77 33.76 101.22 67.31 67.66z" />
                    <path d="M33.4 33.91L66.96 67.46 33.4 101.02-.15 67.46 33.4 33.91z" />
                  </g>
                </svg>
              </div>
              <div class="method-info">
                <span class="method-name">Pix</span>
                <span class="method-desc">Aprovação imediata • 5% de desconto</span>
              </div>
              <div class="discount-badge" v-if="metodo === 'pix'">-5%</div>
              <div class="check-container">
                <div class="check-mark"></div>
              </div>
            </label>

            <label class="method-card" :class="{ active: metodo === 'cartao' }">
              <input type="radio" v-model="metodo" value="cartao" name="pagamento" />
              <div class="method-icon">
                <svg
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <rect x="2" y="5" width="20" height="14" rx="2" />
                  <line x1="2" y1="10" x2="22" y2="10" />
                </svg>
              </div>
              <div class="method-info">
                <span class="method-name">Cartão de Crédito</span>
                <span class="method-desc">Até 12x • Master, Visa, Elo, Amex</span>
              </div>
              <div class="card-flags">
                <span class="flag">💳</span>
              </div>
              <div class="check-container">
                <div class="check-mark"></div>
              </div>
            </label>
          </div>

          <transition name="slide-down">
            <div v-if="metodo === 'cartao'" class="card-details-form">
              <div class="form-header">
                <svg
                  width="20"
                  height="20"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <rect x="2" y="5" width="20" height="14" rx="2" />
                </svg>
                <span>Dados do Cartão</span>
              </div>

              <div class="form-group full">
                <label>Número do Cartão</label>
                <div class="input-with-icon">
                  <svg
                    width="18"
                    height="18"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <rect x="2" y="5" width="20" height="14" rx="2" />
                  </svg>
                  <input type="text" placeholder="0000 0000 0000 0000" v-model="cartao.numero" />
                </div>
              </div>

              <div class="form-row">
                <div class="form-group">
                  <label>Validade</label>
                  <div class="input-with-icon">
                    <svg
                      width="16"
                      height="16"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                    >
                      <rect x="3" y="4" width="18" height="18" rx="2" />
                      <path d="M3 10h18" />
                    </svg>
                    <input type="text" placeholder="MM/AA" v-model="cartao.validade" />
                  </div>
                </div>
                <div class="form-group">
                  <label>CVV</label>
                  <div class="input-with-icon">
                    <svg
                      width="16"
                      height="16"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                    >
                      <rect x="3" y="11" width="18" height="11" rx="2" />
                      <path d="M7 11V7a5 5 0 0 1 10 0v4" />
                    </svg>
                    <input type="text" placeholder="123" v-model="cartao.cvv" />
                  </div>
                </div>
              </div>

              <div class="form-group full">
                <label>Nome no Cartão</label>
                <div class="input-with-icon">
                  <svg
                    width="18"
                    height="18"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2" />
                    <circle cx="12" cy="7" r="4" />
                  </svg>
                  <input type="text" placeholder="NOME IMPRESSO NO CARTÃO" v-model="cartao.nome" />
                </div>
              </div>

              <div class="form-group full">
                <label>Parcelas</label>
                <select v-model="parcelas" class="select-installments">
                  <option value="1">1x de R$ {{ totalComDesconto.toFixed(2) }} (sem juros)</option>
                  <option value="2">2x de R$ {{ (totalComDesconto / 2).toFixed(2) }} (sem juros)</option>
                  <option value="3">3x de R$ {{ (totalComDesconto / 3).toFixed(2) }} (sem juros)</option>
                  <option value="6">6x de R$ {{ (totalComDesconto / 6).toFixed(2) }} (sem juros)</option>
                  <option value="12">12x de R$ {{ (totalComDesconto / 12).toFixed(2) }} (sem juros)</option>
                </select>
              </div>
            </div>
          </transition>
        </div>

        <aside class="payment-summary">
          <div class="summary-card">
            <h3 class="summary-title">Resumo do Pedido</h3>

            <div class="order-items">
              <div class="order-items-header">
                <span>Item</span>
                <span>Qtd</span>
                <span>Preço</span>
                <span>Subtotal</span>
              </div>
              
              <div v-for="(item, index) in resumoItens" :key="index" class="order-item">
                <div class="item-info">
                  <span class="item-name">{{ item.nome }}</span>
                  <span class="item-setor" v-if="item.setor">{{ item.setor }}</span>
                </div>
                <span class="item-qty">{{ item.qtd }}x</span>
                <span class="item-price">R$ {{ item.precoUnitario.toFixed(2) }}</span>
                <span class="item-total">R$ {{ item.total.toFixed(2) }}</span>
              </div>
            </div>

            <div class="summary-divider"></div>

            <div class="summary-totals">
              <div class="total-line">
                <span>Subtotal</span>
                <span>R$ {{ subtotal.toFixed(2) }}</span>
              </div>
              <div class="total-line">
                <span>Taxas de conveniência (10%)</span>
                <span>R$ {{ taxas.toFixed(2) }}</span>
              </div>
              <div v-if="metodo === 'pix'" class="total-line discount-line">
                <span>Desconto Pix (5%)</span>
                <span class="discount-value">- R$ {{ (totalGeral * 0.05).toFixed(2) }}</span>
              </div>
              <div class="total-line main-total">
                <span>Total</span>
                <span class="gold-text">R$ {{ totalComDesconto.toFixed(2) }}</span>
              </div>
            </div>

            <button class="btn-pay" :disabled="!metodo" @click="confirmarPagamento">
              <svg
                width="20"
                height="20"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2.5"
              >
                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z" />
              </svg>
              <span>{{ metodo === 'pix' ? 'Gerar QR Code Pix' : 'Confirmar Pagamento' }}</span>
            </button>

            <p class="security-note">
              <svg
                width="15"
                height="15"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <rect x="3" y="11" width="18" height="13" rx="2" />
                <path d="M7 11V7a5 5 0 0 1 10 0v4" />
              </svg>
              <span>Pagamento processado com segurança pela Arena Hub</span>
            </p>
          </div>
        </aside>
      </div>
    </main>
    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'
import { useCartStore } from '../stores/cart'

const router = useRouter()
const cart = useCartStore()
const metodo = ref('')
const parcelas = ref('1')
const cartao = ref({ numero: '', validade: '', cvv: '', nome: '' })

const eventoInfo = ref({
  nome: 'Rock Nacional 2026',
  data: '15 de Agosto de 2026',
  horario: '20:00',
  local: 'Arena Pernambuco',
  imagem: 'https://images.unsplash.com/photo-1540039155733-5bb30b53aa14?w=100&h=100&fit=crop'
})

const resumoItens = ref([
  { 
    id: 1,
    nome: 'Pista', 
    setor: 'Área principal',
    qtd: 2, 
    precoUnitario: 85.00, 
    total: 170.00 
  },
  { 
    id: 2,
    nome: 'Pista Premium', 
    setor: 'Acesso VIP',
    qtd: 1, 
    precoUnitario: 150.00, 
    total: 150.00 
  },
  { 
    id: 3,
    nome: 'Estacionamento', 
    setor: 'E1 - Coberto',
    qtd: 1, 
    precoUnitario: 45.00, 
    total: 45.00 
  }
])

const subtotal = computed(() => resumoItens.value.reduce((acc, item) => acc + item.total, 0))
const taxas = computed(() => subtotal.value * 0.1)
const totalGeral = computed(() => subtotal.value + taxas.value)
const totalComDesconto = computed(() => metodo.value === 'pix' ? totalGeral.value * 0.95 : totalGeral.value)

onMounted(() => {
  if (cart.totalItens > 0 && cart.eventoSelecionado) {
    eventoInfo.value = {
      nome: cart.eventoSelecionado.title || 'Evento',
      data: cart.eventoSelecionado.date || 'Data a definir',
      horario: cart.eventoSelecionado.time || '20:00',
      local: cart.eventoSelecionado.venue || 'Arena Pernambuco',
      imagem: cart.eventoSelecionado.image || 'https://images.unsplash.com/photo-1540039155733-5bb30b53aa14?w=100&h=100&fit=crop'
    }
    
    if (cart.eventoSelecionado.ingressos && cart.ingressos) {
      const itens: any[] = []
      for (const [idx, qtd] of Object.entries(cart.ingressos)) {
        if (qtd > 0 && cart.eventoSelecionado.ingressos[Number(idx)]) {
          const ingresso = cart.eventoSelecionado.ingressos[Number(idx)]
          itens.push({
            id: Number(idx),
            nome: ingresso.tipo,
            setor: ingresso.subtipo || 'Setor único',
            qtd: qtd,
            precoUnitario: ingresso.preco,
            total: ingresso.preco * Number(qtd)
          })
        }
      }
      if (itens.length > 0) {
        resumoItens.value = itens
      }
    }
  }
})

function confirmarPagamento() {
  if (!metodo.value) {
    alert('Selecione uma forma de pagamento')
    return
  }
  
  if (metodo.value === 'cartao') {
    if (!cartao.value.numero || !cartao.value.validade || !cartao.value.cvv || !cartao.value.nome) {
      alert('Preencha todos os dados do cartão')
      return
    }
  }
  
  alert(`Pagamento processado com sucesso!${metodo.value === 'pix' ? ' QR Code enviado por e-mail.' : ''}`)
  cart.limparCarrinho()
  router.push('/meus-ingressos')
}
</script>

<style scoped>
.page {
  background: #0a0e17;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.main-payment {
  flex: 1;
  padding: 40px 20px 60px;
  display: flex;
  justify-content: center;
  width: 100%;
}

.payment-container {
  width: 100%;
  max-width: 1100px;
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.breadcrumb-wrapper {
  grid-column: 1 / -1;
  margin-bottom: 8px;
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 8px;
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

.breadcrumb svg {
  color: #4a5568;
}

@media (min-width: 951px) {
  .payment-container {
    display: grid;
    grid-template-columns: 1fr 380px;
    gap: 40px;
    align-items: start;
  }

  .breadcrumb-wrapper,
  .event-summary {
    grid-column: 1 / -1;
  }
}

.event-summary {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(201, 168, 76, 0.12);
  border-radius: 20px;
  padding: 20px 24px;
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 8px;
}

.event-image {
  width: 70px;
  height: 70px;
  border-radius: 12px;
  overflow: hidden;
  flex-shrink: 0;
}

.event-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.event-name {
  font-size: 1.1rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 8px;
}

.event-meta {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 0.75rem;
  color: #8e9aaf;
}

.meta-item svg {
  color: #c9a84c;
  width: 12px;
  height: 12px;
}

.section-header {
  margin-bottom: 32px;
}

.title-wrapper {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 8px;
}

.page-title {
  font-size: 2rem;
  font-weight: 900;
  color: #fff;
  margin: 0;
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.title-line {
  flex: 1;
  height: 2px;
  background: linear-gradient(90deg, #c9a84c, transparent);
  border-radius: 1px;
}

.page-subtitle {
  color: #8e9aaf;
  font-size: 0.95rem;
  margin-left: 4px;
}

.methods-grid {
  display: flex;
  flex-direction: column;
  gap: 14px;
}

.method-card {
  background: #121826;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 18px;
  padding: 22px 24px;
  display: flex;
  align-items: center;
  gap: 18px;
  cursor: pointer;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  position: relative;
}

.method-card:hover {
  border-color: rgba(201, 168, 76, 0.3);
  background: #161d2b;
  transform: translateX(4px);
}

.method-card.active {
  border-color: #c9a84c;
  background: linear-gradient(135deg, rgba(201, 168, 76, 0.08), rgba(201, 168, 76, 0.02));
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.1);
}

.method-card input {
  display: none;
}

.method-icon {
  width: 52px;
  height: 52px;
  background: linear-gradient(135deg, #1a2233, #151c2a);
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #c9a84c;
  border: 1px solid rgba(201, 168, 76, 0.15);
}

.active .method-icon {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border-color: #c9a84c;
}

.method-info {
  display: flex;
  flex-direction: column;
  flex: 1;
}

.method-name {
  font-weight: 800;
  font-size: 1.15rem;
  color: #fff;
  margin-bottom: 2px;
}

.method-desc {
  font-size: 0.85rem;
  color: #8e9aaf;
}

.discount-badge {
  background: linear-gradient(135deg, #4cc98e, #34a86c);
  color: white;
  font-size: 0.7rem;
  font-weight: 800;
  padding: 4px 8px;
  border-radius: 20px;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.card-flags {
  font-size: 1.5rem;
  opacity: 0.7;
}

.check-container {
  margin-left: auto;
}

.check-mark {
  width: 24px;
  height: 24px;
  border: 2px solid rgba(255, 255, 255, 0.15);
  border-radius: 50%;
  position: relative;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.active .check-mark {
  background: #c9a84c;
  border-color: #c9a84c;
  box-shadow: 0 0 0 3px rgba(201, 168, 76, 0.2);
}

.active .check-mark::after {
  content: '✓';
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: #0a0e17;
  font-size: 14px;
  font-weight: 900;
}

.card-details-form {
  margin-top: 20px;
  padding: 28px;
  background: linear-gradient(135deg, #121826, #0f131e);
  border-radius: 18px;
  display: grid;
  gap: 20px;
  border: 1px solid rgba(201, 168, 76, 0.15);
}

.form-header {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #c9a84c;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  font-size: 0.85rem;
  margin-bottom: 8px;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-group.full {
  grid-column: 1 / -1;
}

.form-group label {
  font-size: 0.7rem;
  font-weight: 800;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.8px;
  margin-left: 4px;
}

.input-with-icon {
  position: relative;
  display: flex;
  align-items: center;
}

.input-with-icon svg {
  position: absolute;
  left: 14px;
  color: #c9a84c;
  opacity: 0.6;
  pointer-events: none;
}

.form-group input,
.select-installments {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 12px 14px;
  color: white;
  font-size: 0.95rem;
  outline: none;
  transition: all 0.3s ease;
  width: 100%;
}

.input-with-icon input {
  padding-left: 44px;
}

.form-group input:hover,
.select-installments:hover {
  border-color: rgba(201, 168, 76, 0.2);
  background: #1e2638;
}

.form-group input:focus,
.select-installments:focus {
  border-color: #c9a84c;
  background: #1e2638;
  box-shadow: 0 0 0 3px rgba(201, 168, 76, 0.08);
}

.form-group input::placeholder {
  color: #5a6375;
  font-weight: 300;
}

.select-installments {
  cursor: pointer;
}

.select-installments option {
  background: #121826;
  padding: 8px;
}

.payment-summary {
  position: sticky;
  top: 100px;
}

.summary-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(201, 168, 76, 0.15);
  border-radius: 24px;
  padding: 32px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3);
}

.summary-title {
  font-size: 1.4rem;
  font-weight: 800;
  margin-bottom: 24px;
  border-bottom: 2px solid rgba(201, 168, 76, 0.2);
  padding-bottom: 20px;
  color: #fff;
}

.order-items {
  max-height: 300px;
  overflow-y: auto;
  padding-right: 4px;
}

.order-items::-webkit-scrollbar {
  width: 4px;
}

.order-items::-webkit-scrollbar-thumb {
  background: rgba(201, 168, 76, 0.3);
  border-radius: 2px;
}

.order-items-header {
  display: grid;
  grid-template-columns: 1fr 50px 70px 80px;
  padding-bottom: 12px;
  margin-bottom: 12px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  font-size: 0.7rem;
  color: #6b7280;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  font-weight: 700;
}

.order-item {
  display: grid;
  grid-template-columns: 1fr 50px 70px 80px;
  align-items: center;
  font-size: 0.85rem;
  margin-bottom: 16px;
}

.item-info {
  display: flex;
  flex-direction: column;
}

.item-name {
  color: #e0e0e0;
  font-weight: 600;
}

.item-setor {
  font-size: 0.7rem;
  color: #6b7280;
  margin-top: 2px;
}

.item-qty {
  color: #c9a84c;
  font-weight: 700;
  text-align: center;
}

.item-price {
  color: #b0b8c5;
  text-align: center;
}

.item-total {
  color: #fff;
  font-weight: 700;
  text-align: right;
}

.summary-divider {
  height: 1px;
  background: linear-gradient(90deg, transparent, rgba(201, 168, 76, 0.2), transparent);
  margin: 20px 0;
}

.summary-totals {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-top: 20px;
}

.total-line {
  display: flex;
  justify-content: space-between;
  color: #8e9aaf;
  font-size: 0.95rem;
}

.discount-line {
  color: #4cc98e;
}

.discount-value {
  color: #4cc98e;
  font-weight: 700;
}

.main-total {
  font-size: 1.6rem;
  font-weight: 900;
  color: white;
  padding-top: 20px;
  margin-top: 8px;
  border-top: 2px dashed rgba(201, 168, 76, 0.3);
}

.gold-text {
  color: #c9a84c;
}

.order-info {
  margin-top: 20px;
  padding-top: 16px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.info-line {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 0.7rem;
  color: #6b7280;
  margin-bottom: 10px;
}

.info-line svg {
  color: #c9a84c;
  flex-shrink: 0;
}

.btn-pay {
  width: 100%;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border: none;
  padding: 18px;
  border-radius: 14px;
  font-weight: 800;
  font-size: 1rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  margin-top: 24px;
  text-transform: uppercase;
  letter-spacing: 1px;
  box-shadow: 0 4px 15px rgba(201, 168, 76, 0.2);
}

.btn-pay:hover:not(:disabled) {
  transform: translateY(-3px);
  box-shadow: 0 10px 25px rgba(201, 168, 76, 0.3);
}

.btn-pay:disabled {
  opacity: 0.4;
  cursor: not-allowed;
  box-shadow: none;
}

.btn-pay svg {
  transition: transform 0.3s ease;
}

.btn-pay:hover:not(:disabled) svg {
  transform: scale(1.1);
}

.security-note {
  text-align: center;
  font-size: 0.75rem;
  color: #6b7280;
  margin-top: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  padding-top: 16px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.security-note svg {
  color: #4cc98e;
}

.slide-down-enter-active,
.slide-down-leave-active {
  transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
}

.slide-down-enter-from,
.slide-down-leave-to {
  opacity: 0;
  transform: translateY(-20px);
}

@media (max-width: 950px) {
  .payment-summary {
    position: static;
    order: -1;
  }

  .main-payment {
    padding: 30px 16px;
  }

  .page-title {
    font-size: 1.6rem;
  }
  
  .event-summary {
    flex-direction: column;
    text-align: center;
  }
  
  .event-meta {
    justify-content: center;
  }
}

@media (max-width: 600px) {
  .method-card {
    padding: 18px;
    gap: 12px;
  }

  .method-icon {
    width: 44px;
    height: 44px;
  }

  .method-name {
    font-size: 1rem;
  }

  .method-desc {
    font-size: 0.75rem;
  }

  .card-details-form {
    padding: 20px;
  }

  .form-row {
    grid-template-columns: 1fr;
    gap: 16px;
  }

  .summary-card {
    padding: 24px 20px;
  }

  .main-total {
    font-size: 1.4rem;
  }
  
  .order-items-header {
    display: none;
  }
  
  .order-item {
    grid-template-columns: 1fr;
    gap: 8px;
    margin-bottom: 20px;
    padding-bottom: 16px;
    border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  }
  
  .item-qty, .item-price, .item-total {
    text-align: left;
    display: flex;
    justify-content: space-between;
  }
  
  .item-qty::before {
    content: "Quantidade:";
    color: #6b7280;
    font-weight: normal;
  }
  
  .item-price::before {
    content: "Preço unitário:";
    color: #6b7280;
    font-weight: normal;
  }
  
  .item-total::before {
    content: "Subtotal:";
    color: #6b7280;
    font-weight: normal;
  }
}
</style>