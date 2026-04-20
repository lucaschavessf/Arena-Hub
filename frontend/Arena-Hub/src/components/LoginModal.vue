<template>
  <Teleport to="body">
    <transition name="fade">
      <div 
        v-if="isVisible"
        class="modal-overlay" 
        @click.self="handleClose" 
        @keydown.esc="handleClose"
      >
        <div 
          class="modal-card" 
          role="dialog" 
          aria-modal="true"
          :aria-label="recoverMode ? 'Recuperar senha' : 'Login'"
        >
          <button 
            class="close-btn" 
            @click="handleClose" 
            aria-label="Fechar modal"
            :disabled="isLoading"
          >
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
            </svg>
          </button>

          <!-- Modo Login -->
          <div v-if="!recoverMode" class="modal-content">
            <header class="modal-header">
              <h2 class="modal-title">Bem-vindo de volta</h2>
              <p class="modal-subtitle">Acesse sua conta Arena Hub</p>
            </header>

            <!-- Mensagem de erro geral -->
            <div v-if="loginError" class="error-banner" role="alert">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/>
              </svg>
              <span>{{ loginError }}</span>
            </div>

            <form @submit.prevent="handleLogin" class="modal-form" novalidate>
              <div class="input-group" :class="{ 'input-group--error': errors.email }">
                <label for="login-email">E-mail</label>
                <input 
                  id="login-email"
                  ref="emailInput"
                  v-model="form.email" 
                  type="email" 
                  placeholder="seu@email.com" 
                  required 
                  autocomplete="email"
                  :disabled="isLoading"
                  @blur="validateEmail"
                  @input="clearFieldError('email')"
                  aria-invalid="errors.email ? true : false"
                  :aria-describedby="errors.email ? 'email-error' : undefined"
                />
                <span v-if="errors.email" id="email-error" class="error-message" role="alert">
                  {{ errors.email }}
                </span>
              </div>
              
              <div class="input-group" :class="{ 'input-group--error': errors.password }">
                <div class="label-row">
                  <label for="login-password">Senha</label>
                  <span class="forgot-link" @click="!isLoading && (recoverMode = true)">
                    Esqueceu?
                  </span>
                </div>
                <div class="password-wrapper">
                  <input 
                    id="login-password"
                    v-model="form.password" 
                    :type="showPassword ? 'text' : 'password'"
                    placeholder="••••••••" 
                    required 
                    autocomplete="current-password"
                    :disabled="isLoading"
                    @blur="validatePassword"
                    @input="clearFieldError('password')"
                    aria-invalid="errors.password ? true : false"
                    :aria-describedby="errors.password ? 'password-error' : undefined"
                  />
                  <button 
                    type="button"
                    class="toggle-password" 
                    @click="showPassword = !showPassword"
                    :aria-label="showPassword ? 'Ocultar senha' : 'Mostrar senha'"
                    tabindex="-1"
                  >
                    <svg v-if="!showPassword" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                      <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                      <circle cx="12" cy="12" r="3"/>
                    </svg>
                    <svg v-else width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                      <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                      <line x1="1" y1="1" x2="23" y2="23"/>
                    </svg>
                  </button>
                </div>
                <span v-if="errors.password" id="password-error" class="error-message" role="alert">
                  {{ errors.password }}
                </span>
              </div>

              <div class="checkbox-group">
                <label class="checkbox-label">
                  <input 
                    type="checkbox" 
                    v-model="form.rememberMe"
                    :disabled="isLoading"
                  />
                  <span class="checkbox-custom"></span>
                  <span class="checkbox-text">Lembrar-me neste dispositivo</span>
                </label>
              </div>

              <div class="action-stack">
                <button 
                  type="submit" 
                  class="btn-primary w-full"
                  :class="{ 'btn--loading': isLoading }"
                  :disabled="isLoading || !isFormValid"
                >
                  <span v-if="!isLoading">Entrar na conta</span>
                  <span v-else class="loading-spinner"></span>
                </button>
                
                <button 
                  type="button" 
                  class="btn-ghost w-full" 
                  @click="handleSignup"
                  :disabled="isLoading"
                >
                  Não tem conta? Cadastre-se
                </button>
              </div>
            </form>
          </div>

          <!-- Modo Recuperação de Senha -->
          <div v-else class="modal-content">
            <header class="modal-header">
              <h2 class="modal-title">Recuperar Senha</h2>
              <p class="modal-subtitle">Enviaremos um link para o seu e-mail</p>
            </header>

            <div v-if="!recoverSent" class="modal-form">
              <div class="input-group" :class="{ 'input-group--error': recoverError }">
                <label for="recover-email">E-mail de cadastro</label>
                <input 
                  id="recover-email"
                  ref="recoverEmailInput"
                  v-model="recoverEmail" 
                  type="email" 
                  placeholder="seu@email.com" 
                  required 
                  :disabled="isLoading"
                  @input="recoverError = ''"
                />
                <span v-if="recoverError" class="error-message" role="alert">{{ recoverError }}</span>
              </div>
              
              <button 
                class="btn-primary w-full" 
                @click="handleRecover"
                :class="{ 'btn--loading': isLoading }"
                :disabled="isLoading || !recoverEmail"
              >
                <span v-if="!isLoading">Enviar Link de Resgate</span>
                <span v-else class="loading-spinner"></span>
              </button>
              
              <button 
                class="btn-back-link" 
                @click="recoverMode = false"
                :disabled="isLoading"
              >
                ← Voltar para o login
              </button>
            </div>

            <div v-else class="success-state">
              <div class="success-icon">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                  <polyline points="20 6 9 17 4 12"/>
                </svg>
              </div>
              <h3>E-mail enviado!</h3>
              <p>Instruções enviadas para <strong>{{ recoverEmail }}</strong></p>
              <p class="success-note">Verifique sua caixa de entrada e spam</p>
              <button class="btn-primary w-full" @click="handleClose">Entendido</button>
            </div>
          </div>
        </div>
      </div>
    </transition>
  </Teleport>
</template>

<script setup lang="ts">
import { ref, reactive, computed, watch, nextTick, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

interface LoginForm {
  email: string
  password: string
  rememberMe: boolean
}

interface FormErrors {
  email?: string
  password?: string
}

const props = defineProps<{
  isVisible?: boolean
}>()

const emit = defineEmits<{
  close: []
  login: [credentials: { email: string; password: string; rememberMe: boolean }]
  recover: [email: string]
}>()

const router = useRouter()

// Refs
const emailInput = ref<HTMLInputElement>()
const recoverEmailInput = ref<HTMLInputElement>()
const showPassword = ref(false)
const isLoading = ref(false)
const recoverMode = ref(false)
const recoverSent = ref(false)
const recoverEmail = ref('')
const recoverError = ref('')
const loginError = ref('')

// Form state
const form = reactive<LoginForm>({
  email: '',
  password: '',
  rememberMe: false
})

const errors = reactive<FormErrors>({})

// Computed
const isFormValid = computed(() => {
  return form.email && 
         form.password && 
         !errors.email && 
         !errors.password &&
         form.email.includes('@') &&
         form.password.length >= 6
})

// Validações
const validateEmail = () => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!form.email) {
    errors.email = 'E-mail é obrigatório'
  } else if (!emailRegex.test(form.email)) {
    errors.email = 'E-mail inválido'
  } else {
    delete errors.email
  }
}

const validatePassword = () => {
  if (!form.password) {
    errors.password = 'Senha é obrigatória'
  } else if (form.password.length < 6) {
    errors.password = 'Senha deve ter pelo menos 6 caracteres'
  } else {
    delete errors.password
  }
}

const clearFieldError = (field: keyof FormErrors) => {
  delete errors[field]
  loginError.value = ''
}

// Handlers
const handleClose = () => {
  if (isLoading.value) return
  
  // Reset state
  recoverMode.value = false
  recoverSent.value = false
  recoverError.value = ''
  loginError.value = ''
  form.email = ''
  form.password = ''
  form.rememberMe = false
  showPassword.value = false
  Object.keys(errors).forEach(key => delete errors[key as keyof FormErrors])
  
  emit('close')
}

const handleLogin = async () => {
  // Validar todos os campos
  validateEmail()
  validatePassword()
  
  if (!isFormValid.value) return
  
  isLoading.value = true
  loginError.value = ''
  
  try {
    // Simular chamada API
    await new Promise(resolve => setTimeout(resolve, 1500))
    
    // Emitir evento de login
    emit('login', {
      email: form.email,
      password: form.password,
      rememberMe: form.rememberMe
    })
    
    handleClose()
  } catch (error) {
    loginError.value = 'E-mail ou senha incorretos. Tente novamente.'
  } finally {
    isLoading.value = false
  }
}

const handleRecover = async () => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  
  if (!recoverEmail.value) {
    recoverError.value = 'E-mail é obrigatório'
    return
  }
  
  if (!emailRegex.test(recoverEmail.value)) {
    recoverError.value = 'E-mail inválido'
    return
  }
  
  isLoading.value = true
  recoverError.value = ''
  
  try {
    // Simular chamada API
    await new Promise(resolve => setTimeout(resolve, 1500))
    
    emit('recover', recoverEmail.value)
    recoverSent.value = true
  } catch (error) {
    recoverError.value = 'Erro ao enviar e-mail. Tente novamente.'
  } finally {
    isLoading.value = false
  }
}

const handleSignup = () => {
  router.push('/cadastro')
  handleClose()
}

// Foco automático
watch(() => recoverMode.value, async (newMode) => {
  await nextTick()
  if (newMode) {
    recoverEmailInput.value?.focus()
  } else {
    emailInput.value?.focus()
  }
})

// Atalho de teclado
const handleEsc = (e: KeyboardEvent) => {
  if (e.key === 'Escape' && !isLoading.value) {
    handleClose()
  }
}

onMounted(() => {
  window.addEventListener('keydown', handleEsc)
  emailInput.value?.focus()
})

onUnmounted(() => {
  window.removeEventListener('keydown', handleEsc)
})
</script>

<style scoped>
/* Estilos base (mantidos do original) */
.modal-overlay {
  position: fixed; 
  inset: 0;
  background: rgba(10, 14, 23, 0.85);
  backdrop-filter: blur(8px);
  display: flex; 
  align-items: center; 
  justify-content: center;
  z-index: 2000;
  padding: 20px;
}

.modal-card {
  background: var(--navy-mid, #131a2a);
  border: 1px solid rgba(255, 255, 255, 0.08);
  border-radius: 24px;
  width: 100%;
  max-width: 420px;
  position: relative;
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.5);
  overflow: hidden;
}

.close-btn {
  position: absolute; 
  top: 20px; 
  right: 20px;
  background: rgba(255, 255, 255, 0.05); 
  border: none;
  color: var(--muted, #8a8f9e); 
  cursor: pointer;
  width: 36px; 
  height: 36px;
  display: flex; 
  align-items: center; 
  justify-content: center;
  border-radius: 50%; 
  transition: all 0.2s;
  z-index: 10;
}

.close-btn:hover:not(:disabled) { 
  background: var(--red, #ef4444); 
  color: white; 
  transform: rotate(90deg); 
}

.close-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.close-btn:focus-visible {
  outline: 2px solid var(--gold, #c9a84c);
  outline-offset: 2px;
}

.modal-content { 
  padding: 40px 32px; 
}

.modal-header { 
  text-align: center; 
  margin-bottom: 32px; 
}

.modal-title { 
  font-size: 1.5rem; 
  font-weight: 800; 
  color: white; 
  margin-bottom: 8px; 
}

.modal-subtitle { 
  color: var(--muted, #8a8f9e); 
  font-size: 0.9rem; 
}

/* Formulário */
.modal-form { 
  display: flex; 
  flex-direction: column; 
  gap: 20px; 
}

.input-group { 
  display: flex; 
  flex-direction: column; 
  gap: 8px; 
}

.input-group--error input {
  border-color: var(--red, #ef4444) !important;
}

.label-row { 
  display: flex; 
  justify-content: space-between; 
  align-items: center; 
}

.input-group label { 
  font-size: 0.7rem; 
  font-weight: 700; 
  color: var(--gold, #c9a84c); 
  text-transform: uppercase; 
  letter-spacing: 0.5px; 
}

.input-group input {
  background: var(--navy-light, #1a2332);
  border: 1.5px solid rgba(255, 255, 255, 0.05);
  border-radius: 12px;
  padding: 14px 16px;
  font-size: 0.95rem;
  color: white; 
  outline: none; 
  transition: 0.2s;
}

.input-group input:focus {
  border-color: var(--gold, #c9a84c); 
  background: #242d3d;
}

.input-group input:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.input-group input:focus-visible {
  outline: 2px solid var(--gold, #c9a84c);
  outline-offset: 1px;
}

/* Password wrapper */
.password-wrapper {
  position: relative;
}

.password-wrapper input {
  width: 100%;
  padding-right: 45px;
}

.toggle-password {
  position: absolute;
  right: 12px;
  top: 50%;
  transform: translateY(-50%);
  background: none;
  border: none;
  color: var(--muted, #8a8f9e);
  cursor: pointer;
  padding: 4px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: color 0.2s;
}

.toggle-password:hover {
  color: var(--gold, #c9a84c);
}

/* Mensagens de erro */
.error-message {
  font-size: 0.7rem;
  color: var(--red, #ef4444);
  margin-top: 4px;
}

.error-banner {
  background: rgba(239, 68, 68, 0.1);
  border: 1px solid rgba(239, 68, 68, 0.3);
  border-radius: 8px;
  padding: 12px;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 8px;
  color: var(--red, #ef4444);
  font-size: 0.8rem;
}

/* Checkbox */
.checkbox-group {
  margin-top: -4px;
}

.checkbox-label {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  font-size: 0.85rem;
  color: var(--muted, #8a8f9e);
}

.checkbox-label input {
  position: absolute;
  opacity: 0;
  width: 0;
  height: 0;
}

.checkbox-custom {
  width: 18px;
  height: 18px;
  border: 2px solid rgba(255, 255, 255, 0.1);
  border-radius: 4px;
  background: var(--navy-light, #1a2332);
  transition: all 0.2s;
  position: relative;
}

.checkbox-label input:checked + .checkbox-custom {
  background: var(--gold, #c9a84c);
  border-color: var(--gold, #c9a84c);
}

.checkbox-label input:checked + .checkbox-custom::after {
  content: '';
  position: absolute;
  left: 5px;
  top: 2px;
  width: 4px;
  height: 8px;
  border: solid var(--navy, #0a0e1a);
  border-width: 0 2px 2px 0;
  transform: rotate(45deg);
}

.checkbox-label input:focus-visible + .checkbox-custom {
  outline: 2px solid var(--gold, #c9a84c);
  outline-offset: 2px;
}

.checkbox-text {
  user-select: none;
}

/* Links e botões */
.forgot-link { 
  font-size: 0.75rem; 
  color: var(--muted, #8a8f9e); 
  cursor: pointer; 
  transition: 0.2s; 
}

.forgot-link:hover { 
  color: var(--gold, #c9a84c); 
}

.action-stack { 
  display: flex; 
  flex-direction: column; 
  gap: 12px; 
  margin-top: 10px; 
}

.w-full { 
  width: 100%; 
  justify-content: center; 
}

.btn--loading {
  position: relative;
  color: transparent !important;
}

.loading-spinner {
  position: absolute;
  width: 20px;
  height: 20px;
  border: 2px solid rgba(255, 255, 255, 0.3);
  border-top-color: white;
  border-radius: 50%;
  animation: spin 0.6s linear infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

.btn-back-link {
  background: none; 
  border: none; 
  color: var(--muted, #8a8f9e);
  font-size: 0.85rem; 
  cursor: pointer; 
  margin-top: 16px;
  align-self: center; 
  transition: 0.2s;
}

.btn-back-link:hover:not(:disabled) { 
  color: white; 
}

.btn-back-link:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

/* Estado de sucesso */
.success-state { 
  text-align: center; 
  padding: 20px 0; 
}

.success-icon {
  width: 60px; 
  height: 60px; 
  background: var(--green, #10b981);
  color: var(--navy, #0a0e1a); 
  border-radius: 50%;
  display: flex; 
  align-items: center; 
  justify-content: center;
  margin: 0 auto 20px;
}

.success-state h3 { 
  margin-bottom: 8px; 
  color: white; 
}

.success-state p { 
  color: var(--muted, #8a8f9e); 
  font-size: 0.9rem; 
  margin-bottom: 8px; 
}

.success-note {
  font-size: 0.8rem !important;
  opacity: 0.7;
  margin-bottom: 24px !important;
}

/* Animações */
.fade-enter-active, 
.fade-leave-active { 
  transition: opacity 0.3s ease; 
}

.fade-enter-from, 
.fade-leave-to { 
  opacity: 0; 
}

/* Variáveis de fallback */
:root {
  --navy-mid: #131a2a;
  --navy-light: #1a2332;
  --navy: #0a0e1a;
  --gold: #c9a84c;
  --muted: #8a8f9e;
  --red: #ef4444;
  --green: #10b981;
}
</style>