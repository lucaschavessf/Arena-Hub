<template>
  <div class="page">
    <AppNavbar />

    <main class="main-payment">
      <div class="payment-container">
        <div class="payment-methods">
          <header class="section-header">
            <h1 class="page-title">Finalizar Pagamento</h1>
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
              <div class="form-group full">
                <label>Número do Cartão</label>
                <input type="text" placeholder="0000 0000 0000 0000" v-model="cartao.numero" />
              </div>
              <div class="form-row">
                <div class="form-group">
                  <label>Validade</label>
                  <input type="text" placeholder="MM/AA" v-model="cartao.validade" />
                </div>
                <div class="form-group">
                  <label>CVV</label>
                  <input type="text" placeholder="123" v-model="cartao.cvv" />
                </div>
              </div>
              <div class="form-group full">
                <label>Nome no Cartão</label>
                <input type="text" placeholder="NOME IMPRESSO" v-model="cartao.nome" />
              </div>
            </div>
          </transition>
        </div>

        <aside class="payment-summary">
          <div class="summary-card">
            <h3 class="summary-title">Resumo do Pedido</h3>
            
            <div class="order-items">
              <div v-for="item in resumoItens" :key="item.nome" class="order-item">
                <span class="item-qty">{{ item.qtd }}x</span>
                <span class="item-name">{{ item.nome }}</span>
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
              <div class="total-line main-total">
                <span>Total</span>
                <span class="gold-text">R$ {{ totalGeral.toFixed(2) }}</span>
              </div>
            </div>

            <button class="btn-pay" :disabled="!metodo" @click="confirmarPagamento">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/>
              </svg>
              {{ metodo === 'pix' ? 'Gerar QR Code Pix' : 'Confirmar Pagamento' }}
            </button>
            
            <p class="security-note">
              <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="11" width="18" height="11" rx="2"/><path d="M7 11V7a5 5 0 0 1 10 0v4"/></svg>
              Pagamento processado pela Arena Hub
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
.page { background: #0a0e17; min-height: 100vh; display: flex; flex-direction: column; }

/* CENTRALIZAÇÃO MESTRE */
.main-payment { 
  flex: 1; 
  padding: 60px 20px; 
  display: flex;
  justify-content: center;
  width: 100%;
}

.payment-container { 
  width: 100%;
  max-width: 1100px; /* ALINHAMENTO MESTRE */
  display: grid; 
  grid-template-columns: 1fr 380px; 
  gap: 40px; 
  align-items: start;
}

/* Header */
.section-header { margin-bottom: 40px; }
.page-title { font-size: 2.2rem; font-weight: 900; color: #fff; margin-bottom: 8px; letter-spacing: -1px; }
.page-subtitle { color: #9ca3af; font-size: 1rem; }

/* Métodos de Pagamento */
.methods-grid { display: flex; flex-direction: column; gap: 16px; }
.method-card {
  background: #111827; border: 1.5px solid rgba(255,255,255,0.05);
  border-radius: 18px; padding: 24px; display: flex; align-items: center;
  gap: 20px; cursor: pointer; transition: 0.3s; position: relative;
}
.method-card input { display: none; }
.method-card:hover { border-color: rgba(201, 168, 76, 0.4); background: #161d2b; }
.method-card.active { border-color: #c9a84c; background: rgba(201, 168, 76, 0.05); }

.method-icon {
  width: 52px; height: 52px; background: #1c2433; border-radius: 14px;
  display: flex; align-items: center; justify-content: center; color: #c9a84c;
}
.method-info { display: flex; flex-direction: column; flex: 1; }
.method-name { font-weight: 800; font-size: 1.15rem; color: #fff; }
.method-desc { font-size: 0.9rem; color: #9ca3af; margin-top: 2px; }

.check-mark {
  width: 24px; height: 24px; border: 2px solid rgba(255,255,255,0.1); border-radius: 50%;
  position: relative; transition: 0.3s;
}
.active .check-mark { background: #c9a84c; border-color: #c9a84c; }
.active .check-mark::after {
  content: "✓"; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);
  color: #0a0e17; font-size: 14px; font-weight: 900;
}

/* Formulário Cartão */
.card-details-form {
  margin-top: 24px; padding: 32px; background: #1c2433; border-radius: 18px;
  display: grid; gap: 24px; border: 1px solid rgba(201, 168, 76, 0.2);
}
.form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 24px; }
.form-group { display: flex; flex-direction: column; gap: 10px; }
.form-group label { font-size: 0.7rem; font-weight: 800; color: #c9a84c; text-transform: uppercase; letter-spacing: 1px; }
.form-group input {
  background: #0a0e17; border: 1.5px solid rgba(255,255,255,0.05); border-radius: 10px;
  padding: 14px; color: white; font-size: 1rem; outline: none; transition: 0.2s;
}
.form-group input:focus { border-color: #c9a84c; }

/* Aside / Resumo */
.payment-summary { position: sticky; top: 100px; } /* STICKY PARA PC */
.summary-card {
  background: #111827; border: 1px solid rgba(201, 168, 76, 0.25);
  border-radius: 24px; padding: 32px; box-shadow: 0 20px 40px rgba(0,0,0,0.3);
}
.summary-title { font-size: 1.4rem; font-weight: 800; margin-bottom: 24px; border-bottom: 1px solid rgba(255,255,255,0.05); padding-bottom: 20px; }

.order-item { display: grid; grid-template-columns: 35px 1fr auto; font-size: 0.95rem; gap: 10px; margin-bottom: 16px; }
.item-qty { color: #c9a84c; font-weight: 800; }
.item-name { color: #9ca3af; }
.item-price { color: #fff; font-weight: 600; }

.summary-totals { display: flex; flex-direction: column; gap: 14px; margin-top: 24px; }
.total-line { display: flex; justify-content: space-between; color: #9ca3af; }
.main-total { font-size: 1.8rem; font-weight: 900; color: white; padding-top: 20px; border-top: 2px dashed rgba(255,255,255,0.1); }
.gold-text { color: #c9a84c; }

.btn-pay {
  width: 100%; background: #c9a84c; color: #0a0e17; border: none; padding: 20px;
  border-radius: 14px; font-weight: 900; font-size: 1.1rem; cursor: pointer;
  display: flex; align-items: center; justify-content: center; gap: 12px; transition: 0.3s;
}
.btn-pay:hover:not(:disabled) { transform: translateY(-3px); box-shadow: 0 10px 25px rgba(201, 168, 76, 0.3); }

.security-note {
  text-align: center; font-size: 0.75rem; color: #5c667a; margin-top: 20px;
  display: flex; align-items: center; justify-content: center; gap: 8px;
}

/* Responsive Mobile */
@media (max-width: 950px) {
  .payment-container { grid-template-columns: 1fr; }
  .payment-summary { position: static; order: -1; }
  .main-payment { padding: 30px 16px; }
}

.slide-down-enter-active, .slide-down-leave-active { transition: all 0.4s ease; }
.slide-down-enter-from, .slide-down-leave-to { opacity: 0; transform: translateY(-20px); }
</style>