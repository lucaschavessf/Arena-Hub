<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-auth">
      <div class="auth-container">
        <div class="auth-card">
          
          <header class="auth-header">
            <div class="header-icon" :class="{ 'step-2': etapa === 2 }">
              <svg v-if="etapa === 1" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M20 4H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V6a2 2 0 0 0-2-2z"/>
                <polyline points="22,6 12,13 2,6"/>
                <line x1="2" y1="18" x2="7" y2="13"/>
                <line x1="22" y1="18" x2="17" y2="13"/>
              </svg>
              <svg v-else width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/>
                <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                <circle cx="12" cy="16" r="1"/>
                <line x1="12" y1="16" x2="12" y2="16"/>
              </svg>
            </div>
            <h2 class="auth-title">{{ etapa === 1 ? 'Verifique seu e-mail' : 'Criar nova senha' }}</h2>
            <p class="auth-subtitle">
              {{ etapa === 1 
                ? `Enviamos um código de 6 dígitos para ${email}` 
                : 'Digite sua nova senha abaixo' 
              }}
            </p>
          </header>

          <form @submit.prevent="proximaEtapa" class="auth-form">
            <div v-if="etapa === 1">
              <div class="form-group">
                <label>Código de verificação</label>
                <div class="code-inputs">
                  <input 
                    v-for="(digit, index) in codigo" 
                    :key="index"
                    ref="codeInputs"
                    type="text"
                    maxlength="1"
                    class="code-input"
                    :value="digit"
                    @input="handleCodeInput($event, index)"
                    @keydown="handleCodeKeydown($event, index)"
                    @paste="handlePaste"
                  />
                </div>
              </div>

              <div class="resend-wrapper">
                <button type="button" class="resend-btn" @click="reenviarCodigo" :disabled="countdown > 0">
                  {{ countdown > 0 ? `Reenviar em ${countdown}s` : 'Reenviar código' }}
                </button>
              </div>
            </div>

            <div v-if="etapa === 2">
              <div class="form-group">
                <label>Nova senha</label>
                <div class="input-with-icon">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/>
                    <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                  </svg>
                  <input 
                    v-model="novaSenha" 
                    :type="showPassword ? 'text' : 'password'"
                    placeholder="••••••••" 
                    required 
                  />
                  <button type="button" class="toggle-password" @click="showPassword = !showPassword">
                    <svg v-if="!showPassword" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                      <circle cx="12" cy="12" r="3"/>
                    </svg>
                    <svg v-else width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                      <line x1="1" y1="1" x2="23" y2="23"/>
                    </svg>
                  </button>
                </div>
              </div>

              <div class="form-group">
                <label>Confirmar senha</label>
                <div class="input-with-icon">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/>
                    <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                  </svg>
                  <input 
                    v-model="confirmarSenha" 
                    :type="showConfirmPassword ? 'text' : 'password'"
                    placeholder="••••••••" 
                    required 
                  />
                  <button type="button" class="toggle-password" @click="showConfirmPassword = !showConfirmPassword">
                    <svg v-if="!showConfirmPassword" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                      <circle cx="12" cy="12" r="3"/>
                    </svg>
                    <svg v-else width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                      <line x1="1" y1="1" x2="23" y2="23"/>
                    </svg>
                  </button>
                </div>
              </div>

              <div class="password-strength" v-if="novaSenha">
                <div class="strength-bar">
                  <div class="strength-fill" :class="strengthClass" :style="{ width: strengthPercent + '%' }"></div>
                </div>
                <span class="strength-text">{{ strengthText }}</span>
              </div>
            </div>

            <button type="submit" class="btn-submit" :disabled="loading">
              <span>{{ loading ? 'Processando...' : (etapa === 1 ? 'Verificar código' : 'Redefinir senha') }}</span>
              <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <line x1="5" y1="12" x2="19" y2="12"/>
                <polyline points="12 5 19 12 12 19"/>
              </svg>
            </button>
          </form>

          <footer class="auth-footer">
            <p><router-link to="/login">← Voltar para o login</router-link></p>
          </footer>
        </div>
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

const router = useRouter()
const email = ref('')
const etapa = ref(1)
const codigo = ref(['', '', '', '', '', ''])
const novaSenha = ref('')
const confirmarSenha = ref('')
const loading = ref(false)
const showPassword = ref(false)
const showConfirmPassword = ref(false)
const countdown = ref(0)
const codeInputs = ref<HTMLInputElement[]>([])

// Força da senha
const strengthMap = [
  { regex: /.{6,}/, text: 'Muito fraca', class: 'very-weak', percent: 20 },
  { regex: /(?=.*[a-z])/, text: 'Fraca', class: 'weak', percent: 40 },
  { regex: /(?=.*[A-Z])/, text: 'Média', class: 'medium', percent: 60 },
  { regex: /(?=.*\d)/, text: 'Forte', class: 'strong', percent: 80 },
  { regex: /(?=.*[@$!%*?&])/, text: 'Muito forte', class: 'very-strong', percent: 100 }
]

const strengthPercent = computed(() => {
  let score = 0
  for (const criteria of strengthMap) {
    if (criteria.regex.test(novaSenha.value)) {
      score = criteria.percent
    }
  }
  return score
})

const strengthClass = computed(() => {
  if (strengthPercent.value <= 20) return 'very-weak'
  if (strengthPercent.value <= 40) return 'weak'
  if (strengthPercent.value <= 60) return 'medium'
  if (strengthPercent.value <= 80) return 'strong'
  return 'very-strong'
})

const strengthText = computed(() => {
  if (strengthPercent.value <= 20) return 'Muito fraca'
  if (strengthPercent.value <= 40) return 'Fraca'
  if (strengthPercent.value <= 60) return 'Média'
  if (strengthPercent.value <= 80) return 'Forte'
  return 'Muito forte'
})

function handleCodeInput(event: Event, index: number) {
  const input = event.target as HTMLInputElement
  const value = input.value.replace(/[^0-9]/g, '')
  codigo.value[index] = value
  
  if (value && index < 5) {
    codeInputs.value[index + 1]?.focus()
  }
}

function handleCodeKeydown(event: KeyboardEvent, index: number) {
  if (event.key === 'Backspace' && !codigo.value[index] && index > 0) {
    codeInputs.value[index - 1]?.focus()
  }
}

function handlePaste(event: ClipboardEvent) {
  event.preventDefault()
  const paste = event.clipboardData?.getData('text') || ''
  const digits = paste.replace(/[^0-9]/g, '').slice(0, 6).split('')
  
  digits.forEach((digit, i) => {
    if (i < 6) codigo.value[i] = digit
  })
  
  if (digits.length === 6) {
    setTimeout(() => proximaEtapa(), 100)
  }
}

function proximaEtapa() {
  if (etapa.value === 1) {
    const codigoCompleto = codigo.value.join('')
    if (codigoCompleto.length !== 6) {
      alert('Digite o código de 6 dígitos')
      return
    }
    
    loading.value = true
    setTimeout(() => {
      loading.value = false
      etapa.value = 2
    }, 1000)
  } else {
    if (!novaSenha.value || !confirmarSenha.value) {
      alert('Preencha todos os campos')
      return
    }
    
    if (novaSenha.value !== confirmarSenha.value) {
      alert('As senhas não coincidem')
      return
    }
    
    if (novaSenha.value.length < 6) {
      alert('A senha deve ter no mínimo 6 caracteres')
      return
    }
    
    loading.value = true
    setTimeout(() => {
      loading.value = false
      alert('Senha redefinida com sucesso!')
      sessionStorage.removeItem('resetEmail')
      router.push('/login')
    }, 1500)
  }
}

function reenviarCodigo() {
  if (countdown.value > 0) return
  
  loading.value = true
  setTimeout(() => {
    loading.value = false
    alert('Código reenviado para seu e-mail!')
    countdown.value = 60
    
    const interval = setInterval(() => {
      countdown.value--
      if (countdown.value <= 0) clearInterval(interval)
    }, 1000)
  }, 1000)
}

onMounted(() => {
  const resetEmail = sessionStorage.getItem('resetEmail')
  if (resetEmail) {
    email.value = resetEmail
  } else {
    router.push('/esqueceu-senha')
  }
})
</script>

<style scoped>
.page { 
  min-height: 100vh; 
  display: flex; 
  flex-direction: column; 
  background: #0a0e17;
}

.main-auth {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 60px 20px;
}

.auth-container {
  width: 100%;
  max-width: 440px;
  animation: fadeInUp 0.6s cubic-bezier(0.4, 0, 0.2, 1);
}

.auth-card {
  background: #121826;
  border: 1px solid rgba(201, 168, 76, 0.12);
  border-radius: 24px;
  padding: 44px 40px;
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.5);
}

.auth-header { 
  text-align: center; 
  margin-bottom: 32px; 
}

.header-icon {
  width: 64px;
  height: 64px;
  margin: 0 auto 20px;
  background: rgba(201, 168, 76, 0.1);
  border-radius: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #c9a84c;
  border: 1px solid rgba(201, 168, 76, 0.2);
  transition: all 0.3s ease;
}

.header-icon.step-2 {
  background: rgba(76, 135, 201, 0.1);
  color: #4c87c9;
  border-color: rgba(76, 135, 201, 0.2);
}

.auth-title { 
  font-size: 1.8rem; 
  font-weight: 800; 
  color: #fff; 
  margin-bottom: 8px;
}

.auth-subtitle { 
  color: #8e9aaf; 
  font-size: 0.95rem; 
  font-weight: 400;
}

.auth-form { 
  display: flex; 
  flex-direction: column; 
  gap: 22px; 
}

.form-group { 
  display: flex; 
  flex-direction: column; 
  gap: 8px; 
}

.form-group label {
  font-size: 0.75rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.code-inputs {
  display: flex;
  gap: 12px;
  justify-content: center;
}

.code-input {
  width: 50px;
  height: 56px;
  text-align: center;
  font-size: 1.5rem;
  font-weight: 700;
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  color: #fff;
  outline: none;
  transition: all 0.3s ease;
}

.code-input:focus {
  border-color: #c9a84c;
  box-shadow: 0 0 0 4px rgba(201, 168, 76, 0.1);
}

.form-group input {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 14px 16px;
  color: white;
  outline: none;
  font-size: 0.95rem;
  transition: all 0.3s ease;
  width: 100%;
}

.form-group input:focus {
  border-color: #c9a84c;
  box-shadow: 0 0 0 4px rgba(201, 168, 76, 0.1);
}

.input-with-icon { 
  position: relative; 
}

.input-with-icon svg {
  position: absolute;
  left: 16px;
  top: 50%;
  transform: translateY(-50%);
  color: #c9a84c;
  opacity: 0.7;
  pointer-events: none;
}

.input-with-icon input { 
  padding-left: 46px; 
  padding-right: 46px;
}

.toggle-password {
  position: absolute;
  right: 16px;
  top: 50%;
  transform: translateY(-50%);
  background: none;
  border: none;
  color: #8e9aaf;
  cursor: pointer;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.toggle-password:hover {
  color: #c9a84c;
}

.password-strength {
  margin-top: 4px;
}

.strength-bar {
  height: 4px;
  background: #1a2233;
  border-radius: 4px;
  overflow: hidden;
  margin-bottom: 8px;
}

.strength-fill {
  height: 100%;
  transition: width 0.3s ease, background 0.3s ease;
}

.strength-fill.very-weak { background: #ef4444; }
.strength-fill.weak { background: #f59e0b; }
.strength-fill.medium { background: #eab308; }
.strength-fill.strong { background: #10b981; }
.strength-fill.very-strong { background: #10b981; }

.strength-text {
  font-size: 0.7rem;
  color: #8e9aaf;
}

.resend-wrapper {
  text-align: center;
}

.resend-btn {
  background: none;
  border: none;
  color: #c9a84c;
  font-size: 0.85rem;
  cursor: pointer;
  padding: 8px;
  transition: opacity 0.3s ease;
}

.resend-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.resend-btn:hover:not(:disabled) {
  text-decoration: underline;
}

.btn-submit {
  margin-top: 8px;
  background: #c9a84c;
  color: #0a0e17;
  border-radius: 14px;
  padding: 16px;
  font-weight: 800;
  font-size: 1rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  border: none;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  box-shadow: 0 4px 15px rgba(201, 168, 76, 0.2);
  text-transform: uppercase;
  letter-spacing: 1px;
}

.btn-submit:hover:not(:disabled) {
  background: #d4af37;
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(201, 168, 76, 0.3);
}

.btn-submit:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.auth-footer {
  margin-top: 32px;
  text-align: center;
}

.auth-footer a { 
  color: #c9a84c; 
  font-weight: 700; 
  text-decoration: none;
}

.auth-footer a:hover {
  text-decoration: underline;
}

@keyframes fadeInUp {
  from { 
    opacity: 0; 
    transform: translateY(20px); 
  }
  to { 
    opacity: 1; 
    transform: translateY(0); 
  }
}

@media (max-width: 480px) {
  .auth-card {
    padding: 32px 24px;
  }
  
  .auth-title {
    font-size: 1.5rem;
  }
  
  .code-input {
    width: 45px;
    height: 50px;
    font-size: 1.3rem;
  }
  
  .code-inputs {
    gap: 8px;
  }
}
</style>