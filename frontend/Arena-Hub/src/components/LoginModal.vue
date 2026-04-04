<template>
  <Teleport to="body">
    <transition name="fade">
      <div class="modal-overlay" @click.self="handleClose" @keydown.esc="handleClose">
        <div class="modal-card" role="dialog" aria-modal="true">
          <button class="close-btn" @click="handleClose" aria-label="Fechar modal">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
            </svg>
          </button>

          <div v-if="!recoverMode" class="modal-content">
            <header class="modal-header">
              <h2 class="modal-title">Bem-vindo de volta</h2>
              <p class="modal-subtitle">Acesse sua conta Arena Hub</p>
            </header>

            <form @submit.prevent="handleLogin" class="modal-form">
              <div class="input-group">
                <label for="login-email">E-mail</label>
                <input 
                  id="login-email"
                  v-model="email" 
                  type="email" 
                  placeholder="seu@email.com" 
                  required 
                  autocomplete="email"
                />
              </div>
              
              <div class="input-group">
                <div class="label-row">
                  <label for="login-password">Senha</label>
                  <span class="forgot-link" @click="recoverMode = true">Esqueceu?</span>
                </div>
                <input 
                  id="login-password"
                  v-model="senha" 
                  type="password" 
                  placeholder="••••••••" 
                  required 
                  autocomplete="current-password"
                />
              </div>

              <div class="action-stack">
                <button type="submit" class="btn-primary w-full">Entrar na conta</button>
                <button 
                  type="button" 
                  class="btn-ghost w-full" 
                  @click="$router.push('/cadastro'); handleClose()"
                >
                  Não tem conta? Cadastre-se
                </button>
              </div>
            </form>
          </div>

          <div v-else class="modal-content">
            <header class="modal-header">
              <h2 class="modal-title">Recuperar Senha</h2>
              <p class="modal-subtitle">Enviaremos um link para o seu e-mail</p>
            </header>

            <div v-if="!recoverSent" class="modal-form">
              <div class="input-group">
                <label for="recover-email">E-mail de cadastro</label>
                <input 
                  id="recover-email"
                  v-model="recoverEmail" 
                  type="email" 
                  placeholder="seu@email.com" 
                  required 
                />
              </div>
              <button class="btn-primary w-full" @click="handleRecover">Enviar Link de Resgate</button>
              <button class="btn-back-link" @click="recoverMode = false">← Voltar para o login</button>
            </div>

            <div v-else class="success-state">
              <div class="success-icon">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                  <polyline points="20 6 9 17 4 12"/>
                </svg>
              </div>
              <h3>E-mail enviado!</h3>
              <p>Instruções enviadas para <strong>{{ recoverEmail }}</strong></p>
              <button class="btn-primary w-full" @click="handleClose">Entendido</button>
            </div>
          </div>
        </div>
      </div>
    </transition>
  </Teleport>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'

const emit = defineEmits(['close'])

const email = ref('')
const senha = ref('')
const recoverMode = ref(false)
const recoverEmail = ref('')
const recoverSent = ref(false)

// Fecha o modal e limpa os campos
const handleClose = () => {
  recoverMode.value = false
  recoverSent.value = false
  email.value = ''
  senha.value = ''
  emit('close')
}

const handleLogin = () => {
  // Aqui você chamaria sua API ou Store de autenticação
  console.log('Login efetuado:', email.value)
  handleClose()
}

const handleRecover = () => {
  if (recoverEmail.value) {
    recoverSent.value = true
  }
}

// Atalho de teclado para fechar no ESC
const handleEsc = (e: KeyboardEvent) => {
  if (e.key === 'Escape') handleClose()
}

onMounted(() => window.addEventListener('keydown', handleEsc))
onUnmounted(() => window.removeEventListener('keydown', handleEsc))
</script>

<style scoped>
/* Estilos permanecem os mesmos conforme sua versão anterior, 
   garantindo a consistência visual premium. */
.modal-overlay {
  position: fixed; inset: 0;
  background: rgba(10, 14, 23, 0.85);
  backdrop-filter: blur(8px);
  display: flex; align-items: center; justify-content: center;
  z-index: 2000;
  padding: 20px;
}

.modal-card {
  background: var(--navy-mid);
  border: 1px solid rgba(255, 255, 255, 0.08);
  border-radius: 24px;
  width: 100%;
  max-width: 400px;
  position: relative;
  box-shadow: 0 30px 60px rgba(0,0,0,0.5);
  overflow: hidden;
}

.close-btn {
  position: absolute; top: 20px; right: 20px;
  background: rgba(255, 255, 255, 0.05); border: none;
  color: var(--muted); cursor: pointer;
  width: 36px; height: 36px;
  display: flex; align-items: center; justify-content: center;
  border-radius: 50%; transition: all 0.2s;
  z-index: 10;
}
.close-btn:hover { background: var(--red); color: white; transform: rotate(90deg); }

.modal-content { padding: 40px 32px; }

.modal-header { text-align: center; margin-bottom: 32px; }
.modal-title { font-size: 1.5rem; font-weight: 800; color: white; margin-bottom: 8px; }
.modal-subtitle { color: var(--muted); font-size: 0.9rem; }

.modal-form { display: flex; flex-direction: column; gap: 20px; }

.input-group { display: flex; flex-direction: column; gap: 8px; }
.label-row { display: flex; justify-content: space-between; align-items: center; }

.input-group label { font-size: 0.7rem; font-weight: 700; color: var(--gold); text-transform: uppercase; letter-spacing: 0.5px; }

.input-group input {
  background: var(--navy-light);
  border: 1.5px solid rgba(255,255,255,0.05);
  border-radius: 12px;
  padding: 14px 16px;
  font-size: 0.95rem;
  color: white; outline: none; transition: 0.2s;
}
.input-group input:focus { border-color: var(--gold); background: #242d3d; }

.forgot-link { font-size: 0.75rem; color: var(--muted); cursor: pointer; transition: 0.2s; }
.forgot-link:hover { color: var(--gold); }

.action-stack { display: flex; flex-direction: column; gap: 12px; margin-top: 10px; }
.w-full { width: 100%; justify-content: center; }

.btn-back-link {
  background: none; border: none; color: var(--muted);
  font-size: 0.85rem; cursor: pointer; margin-top: 16px;
  align-self: center; transition: 0.2s;
}
.btn-back-link:hover { color: white; }

.success-state { text-align: center; padding: 20px 0; }
.success-icon {
  width: 60px; height: 60px; background: var(--green);
  color: var(--navy); border-radius: 50%;
  display: flex; align-items: center; justify-content: center;
  margin: 0 auto 20px;
}
.success-state h3 { margin-bottom: 8px; color: white; }
.success-state p { color: var(--muted); font-size: 0.9rem; margin-bottom: 24px; }

.fade-enter-active, .fade-leave-active { transition: opacity 0.3s ease; }
.fade-enter-from, .fade-leave-to { opacity: 0; }
</style>