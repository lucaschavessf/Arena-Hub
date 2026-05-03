<template>
  <div class="page">
    <AppNavbar />

    <main class="main-payment">
      <div class="payment-container">
        <div class="breadcrumb-wrapper">
          <div class="breadcrumb">
            <router-link to="/" class="breadcrumb-link">Início</router-link>
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <polyline points="9 18 15 12 9 6"/>
            </svg>
            <router-link to="/" class="breadcrumb-link">Eventos</router-link>
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <polyline points="9 18 15 12 9 6"/>
            </svg>
            <span class="breadcrumb-current">Pagamento</span>
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
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M12 2L3 12l9 10 9-10-9-10zM12 8l4 4-4 4-4-4 4-4z"/>
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
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="2" y="5" width="20" height="14" rx="2"/><line x1="2" y1="10" x2="22" y2="10"/>
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

            <label class="method-card" :class="{ active: metodo === 'boleto' }">
              <input type="radio" v-model="metodo" value="boleto" name="pagamento" />
              <div class="method-icon">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <line x1="8" y1="6" x2="8" y2="18"/><line x1="12" y1="6" x2="12" y2="18"/><line x1="16" y1="6" x2="16" y2="18"/><rect x="3" y="3" width="18" height="18" rx="2"/>
                </svg>
              </div>
              <div class="method-info">
                <span class="method-name">Boleto Bancário</span>
                <span class="method-desc">Processamento em até 3 dias úteis</span>
              </div>
              <div class="check-container">
                <div class="check-mark"></div>
              </div>
            </label>
          </div>

          <transition name="slide-down">
            <div v-if="metodo === 'cartao'" class="card-details-form">
              <div class="form-header">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="2" y="5" width="20" height="14" rx="2"/>
                </svg>
                <span>Dados do Cartão</span>
              </div>
              
              <div class="form-group full">
                <label>Número do Cartão</label>
                <div class="input-with-icon">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <rect x="2" y="5" width="20" height="14" rx="2"/>
                  </svg>
                  <input type="text" placeholder="0000 0000 0000 0000" v-model="cartao.numero" />
                </div>
              </div>
              
              <div class="form-row">
                <div class="form-group">
                  <label>Validade</label>
                  <div class="input-with-icon">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M3 10h18"/>
                    </svg>
                    <input type="text" placeholder="MM/AA" v-model="cartao.validade" />
                  </div>
                </div>
                <div class="form-group">
                  <label>CVV</label>
                  <div class="input-with-icon">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <rect x="3" y="11" width="18" height="11" rx="2"/><path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                    </svg>
                    <input type="text" placeholder="123" v-model="cartao.cvv" />
                  </div>
                </div>
              </div>
              
              <div class="form-group full">
                <label>Nome no Cartão</label>
                <div class="input-with-icon">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/>
                  </svg>
                  <input type="text" placeholder="NOME IMPRESSO NO CARTÃO" v-model="cartao.nome" />
                </div>
              </div>

              <div class="form-group full">
                <label>Parcelas</label>
                <select class="select-installments">
                  <option value="1">1x de R$ {{ totalGeral.toFixed(2) }} (sem juros)</option>
                  <option value="2">2x de R$ {{ (totalGeral / 2).toFixed(2) }} (sem juros)</option>
                  <option value="3">3x de R$ {{ (totalGeral / 3).toFixed(2) }} (sem juros)</option>
                  <option value="6">6x de R$ {{ (totalGeral / 6).toFixed(2) }} (sem juros)</option>
                  <option value="12">12x de R$ {{ (totalGeral / 12).toFixed(2) }} (sem juros)</option>
                </select>
              </div>
            </div>
          </transition>
        </div>

        <aside class="payment-summary">
          <div class="summary-card">
            <h3 class="summary-title">Resumo do Pedido</h3>
            
            <div class="order-items">
              <div v-for="(item, index) in resumoItens" :key="index" class="order-item">
                <span class="item-qty">{{ item.qtd }}x</span>
                <div class="item-details">
                  <span class="item-name">{{ item.nome }}</span>
                </div>
                <span class="item-price">R$ {{ item.total.toFixed(2) }}</span>
              </div>
            </div>

            <div class="summary-divider"></div>

            <div class="summary-totals">
              <div class="total-line">
                <span>Subtotal</span>
                <span>R$ {{ subtotal.toFixed(2) }}</span>
              </div>
              <div class="total-line">
                <span>Taxas</span>
                <span>R$ {{ taxas.toFixed(2) }}</span>
              </div>
              <div v-if="metodo === 'pix'" class="total-line discount-line">
                <span>Desconto Pix (5%)</span>
                <span class="discount-value">- R$ {{ (totalGeral * 0.05).toFixed(2) }}</span>
              </div>
              <div class="total-line main-total">
                <span>Total</span>
                <span class="gold-text">R$ {{ (metodo === 'pix' ? totalGeral * 0.95 : totalGeral).toFixed(2) }}</span>
              </div>
            </div>

            <button class="btn-pay" :disabled="!metodo" @click="confirmarPagamento">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/>
              </svg>
              <span>{{ metodo === 'pix' ? 'Gerar QR Code Pix' : 'Confirmar Pagamento' }}</span>
            </button>
            
            <p class="security-note">
              <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="11" width="18" height="11" rx="2"/><path d="M7 11V7a5 5 0 0 1 10 0v4"/>
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
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()
const metodo = ref('')
const cartao = ref({ numero: '', validade: '', cvv: '', nome: '' })

const resumoItens = [
  { qtd: 2, nome: 'Cadeira Superior - Rock Nacional', total: 320.00 },
  { qtd: 1, nome: 'Estacionamento E1', total: 45.00 }
]

const subtotal = computed(() => resumoItens.reduce((acc, item) => acc + item.total, 0))
const taxas = computed(() => subtotal.value * 0.1)
const totalGeral = computed(() => subtotal.value + taxas.value)

function confirmarPagamento() {
  alert('Pagamento processado com sucesso!')
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
  
  .breadcrumb-wrapper {
    grid-column: 1 / -1;
  }
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
  content: "✓"; 
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

.order-item { 
  display: grid; 
  grid-template-columns: 35px 1fr auto; 
  font-size: 0.95rem; 
  gap: 10px; 
  margin-bottom: 16px; 
  align-items: start;
}

.item-qty { 
  color: #c9a84c; 
  font-weight: 800; 
}

.item-name { 
  color: #b0b8c5; 
  line-height: 1.4;
}

.item-price { 
  color: #fff; 
  font-weight: 600; 
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
}
</style>