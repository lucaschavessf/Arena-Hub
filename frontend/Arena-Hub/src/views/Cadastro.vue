<template>
  <div class="page">
    <AppNavbar />

    <main class="main-auth">
      <div class="auth-container">
        <div class="auth-card">
          <header class="auth-header">
            <div class="header-icon">
              <svg
                width="32"
                height="32"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2" />
                <circle cx="9" cy="7" r="4" />
                <path d="M22 21v-2a4 4 0 0 0-3-3.87" />
                <path d="M16 3.13a4 4 0 0 1 0 7.75" />
              </svg>
            </div>
            <h2 class="auth-title">{{ isProdutor ? 'Venha ser um produtor de Eventos da Arena' : 'Crie sua conta' }}</h2>
            <p class="auth-subtitle">{{ isProdutor ? 'Comece a criar e gerenciar seus eventos' : 'Junte-se à maior rede de eventos da região' }}</p>
          </header>

          <form @submit.prevent="cadastrar" class="auth-form">
            <div class="form-group">
              <label>Nome completo</label>
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
                <input v-model="form.nome" type="text" placeholder="João da Silva" required />
              </div>
            </div>

            <div class="form-group">
              <label>{{ isProdutor ? 'CNPJ' : 'CPF' }}</label>
              <div class="input-with-icon">
                <svg
                  width="18"
                  height="18"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <rect x="3" y="5" width="18" height="14" rx="2" />
                  <line x1="3" y1="10" x2="21" y2="10" />
                  <line x1="7" y1="15" x2="10" y2="15" />
                  <line x1="14" y1="15" x2="17" y2="15" />
                </svg>
                <input
                  v-if="!isProdutor"
                  v-model="form.cpf"
                  type="text"
                  placeholder="000.000.000-00"
                  required
                  maxlength="14"
                  @input="formatarCPF"
                />
                <input
                  v-else
                  v-model="form.cnpj"
                  type="text"
                  placeholder="00.000.000/0000-00"
                  required
                  maxlength="18"
                  @input="formatarCNPJ"
                />
              </div>
            </div>

            <div class="form-group">
              <label>Data de Nascimento</label>
              <div class="input-with-icon">
                <svg
                  width="18"
                  height="18"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                >
                  <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                  <line x1="16" y1="2" x2="16" y2="6"></line>
                  <line x1="8" y1="2" x2="8" y2="6"></line>
                  <line x1="3" y1="10" x2="21" y2="10"></line>
                </svg>
                
                <input
                  v-model="form.dataNascimento"
                  type="text"
                  placeholder="DD/MM/AAAA"
                  required
                  maxlength="10"
                  @input="formatarData"
                />
              </div>
            </div>

            <div class="form-group">
              <label>E-mail</label>
              <div class="input-with-icon">
                <svg
                  width="18"
                  height="18"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path
                    d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"
                  />
                  <polyline points="22,6 12,13 2,6" />
                </svg>
                <input v-model="form.email" type="email" placeholder="nome@email.com" required />
              </div>
            </div>

            <div class="form-row">
              <div class="form-group">
                <label>Senha</label>
                <div class="input-with-icon">
                  <svg
                    width="18"
                    height="18"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <rect x="3" y="11" width="18" height="11" rx="2" ry="2" />
                    <path d="M7 11V7a5 5 0 0 1 10 0v4" />
                  </svg>
                  <input v-model="form.senha" type="password" placeholder="••••••••" required />
                </div>
              </div>
              <div class="form-group">
                <label>Confirmar Senha</label>
                <div class="input-with-icon">
                  <svg
                    width="18"
                    height="18"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <rect x="3" y="11" width="18" height="11" rx="2" ry="2" />
                    <path d="M7 11V7a5 5 0 0 1 10 0v4" />
                    <path d="M12 15v3" />
                  </svg>
                  <input
                    v-model="form.confirmarSenha"
                    type="password"
                    placeholder="••••••••"
                    required
                  />
                </div>
              </div>
            </div>

            <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>

            <button type="submit" class="btn-submit" :disabled="loading">
              <span>{{ loading ? 'Cadastrando...' : 'Finalizar Cadastro' }}</span>
              <svg
                width="18"
                height="18"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2.5"
              >
                <line x1="5" y1="12" x2="19" y2="12" />
                <polyline points="12 5 19 12 12 19" />
              </svg>
            </button>
          </form>

          <footer class="auth-footer">
            <p>Já possui uma conta? <router-link to="/login">Fazer Login</router-link></p>
          </footer>
        </div>
      </div>
    </main>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, defineProps } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'
import api from '../services/api'
import { useUserStore } from '../stores/userStore'

const props = defineProps({
  isProdutor: {
    type: Boolean,
    default: false
  }
})

const router = useRouter()
const userStore = useUserStore()

const form = reactive({
  nome: '',
  cpf: '',
  cnpj: '',
  email: '',
  senha: '',
  confirmarSenha: '',
})

const loading = ref(false)
const errorMessage = ref('')

function formatarCPF(event: Event) {
  const input = event.target as HTMLInputElement
  let value = input.value.replace(/\D/g, '')

  if (value.length > 11) {
    value = value.slice(0, 11)
  }

  if (value.length > 9) {
    value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4')
  } else if (value.length > 6) {
    value = value.replace(/(\d{3})(\d{3})(\d{3})/, '$1.$2.$3')
  } else if (value.length > 3) {
    value = value.replace(/(\d{3})(\d{3})/, '$1.$2')
  }

  form.cpf = value
}

function formatarCNPJ(event: Event) {
  const input = event.target as HTMLInputElement
  let value = input.value.replace(/\D/g, '')

  if (value.length > 14) {
    value = value.slice(0, 14)
  }

  if (value.length > 12) {
    value = value.replace(/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5')
  } else if (value.length > 8) {
    value = value.replace(/^(\d{2})(\d{3})(\d{3})(\d{4})/, '$1.$2.$3/$4')
  } else if (value.length > 5) {
    value = value.replace(/^(\d{2})(\d{3})(\d{3})/, '$1.$2.$3')
  } else if (value.length > 2) {
    value = value.replace(/^(\d{2})(\d{3})/, '$1.$2')
  }

  form.cnpj = value
}

function validarCPF(cpf: string): boolean {
  const cpfLimpo = cpf.replace(/\D/g, '')

  if (cpfLimpo.length !== 11) return false

  if (/^(\d)\1{10}$/.test(cpfLimpo)) return false

  let soma = 0
  for (let i = 0; i < 9; i++) {
    soma += parseInt(cpfLimpo.charAt(i)) * (10 - i)
  }
  let resto = 11 - (soma % 11)
  const digito1 = resto > 9 ? 0 : resto

  if (digito1 !== parseInt(cpfLimpo.charAt(9))) return false

  soma = 0
  for (let i = 0; i < 10; i++) {
    soma += parseInt(cpfLimpo.charAt(i)) * (11 - i)
  }
  resto = 11 - (soma % 11)
  const digito2 = resto > 9 ? 0 : resto

  return digito2 === parseInt(cpfLimpo.charAt(10))
}

function validarCNPJ(cnpj: string): boolean {
  const cnpjLimpo = cnpj.replace(/\D/g, '')
  if (cnpjLimpo.length !== 14) return false
  if (/^(\d)\1{13}$/.test(cnpjLimpo)) return false
  return true 
}

async function cadastrar() {
  errorMessage.value = ''

  if (!form.nome || !form.email || !form.senha || !form.confirmarSenha) {
    errorMessage.value = 'Preencha todos os campos obrigatórios.'
    return
  }
  
  if (props.isProdutor) {
    if (!form.cnpj || !validarCNPJ(form.cnpj)) {
      errorMessage.value = 'CNPJ inválido.'
      return
    }
  } else {
    if (!form.cpf || !validarCPF(form.cpf)) {
      errorMessage.value = 'CPF inválido.'
      return
    }
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailRegex.test(form.email)) {
    errorMessage.value = 'E-mail inválido.'
    return
  }

  if (form.senha !== form.confirmarSenha) {
    errorMessage.value = 'As senhas não coincidem.'
    return
  }

  if (form.senha.length < 6) {
    errorMessage.value = 'A senha deve ter pelo menos 6 caracteres.'
    return
  }

  loading.value = true

  try {
    const endpoint = props.isProdutor ? '/user/produtor' : '/user/cliente'
    const payload = {
      name: form.nome,
      email: form.email,
      password: form.senha,
      ...(props.isProdutor ? { cnpj: form.cnpj.replace(/\D/g, '') } : { cpf: form.cpf.replace(/\D/g, '') })
    }
    
    await api.post(endpoint, payload)
    
    const loginResponse = await api.post('/auth/login', {
      email: form.email,
      password: form.senha
    })
    
    userStore.login({ 
      name: loginResponse.data.name, 
      token: loginResponse.data.token, 
      tipo: loginResponse.data.tipo 
    })
    
    router.push(props.isProdutor ? '/produtor/dashboard' : '/')
  } catch (error: any) {
    errorMessage.value = error.response?.data?.message || 'Ocorreu um erro no cadastro.'
  } finally {
    loading.value = false
  }
}
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
  max-width: 520px;
  animation: fadeInUp 0.6s cubic-bezier(0.4, 0, 0.2, 1);
}

.auth-card {
  background: #121826;
  border: 1px solid rgba(201, 168, 76, 0.12);
  border-radius: 24px;
  padding: 44px 40px;
  box-shadow:
    0 30px 60px rgba(0, 0, 0, 0.5),
    0 0 0 1px rgba(201, 168, 76, 0.05);
  transition: box-shadow 0.3s ease;
}

.auth-card:hover {
  box-shadow:
    0 35px 70px rgba(0, 0, 0, 0.6),
    0 0 0 1px rgba(201, 168, 76, 0.1);
}

.auth-header {
  text-align: center;
  margin-bottom: 36px;
}

.header-icon {
  width: 64px;
  height: 64px;
  margin: 0 auto 20px;
  background: rgba(201, 168, 76, 0.08);
  border-radius: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #c9a84c;
  border: 1px solid rgba(201, 168, 76, 0.15);
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
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-group label {
  font-size: 0.7rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.8px;
  margin-left: 4px;
}

.form-group input {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 14px 16px;
  color: #fff;
  font-size: 0.95rem;
  transition: all 0.3s ease;
  outline: none;
  width: 100%;
}

.form-group input:hover {
  border-color: rgba(201, 168, 76, 0.2);
  background: #1e2638;
}

.form-group input:focus {
  border-color: #c9a84c;
  background: #1e2638;
  box-shadow: 0 0 0 4px rgba(201, 168, 76, 0.08);
}

.form-group input::placeholder {
  color: #5a6375;
  font-weight: 300;
}

.input-with-icon {
  position: relative;
  display: flex;
  align-items: center;
}

.input-with-icon svg {
  position: absolute;
  left: 16px;
  color: #c9a84c;
  opacity: 0.6;
  transition: opacity 0.3s ease;
  pointer-events: none;
  z-index: 1;
}

.input-with-icon input:focus ~ svg,
.input-with-icon input:hover ~ svg {
  opacity: 1;
}

.input-with-icon input {
  padding-left: 48px;
}

.btn-submit {
  margin-top: 12px;
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
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  border: none;
  text-transform: uppercase;
  letter-spacing: 1px;
  box-shadow: 0 4px 15px rgba(201, 168, 76, 0.15);
}

.btn-submit:hover {
  background: #d4af37;
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(201, 168, 76, 0.25);
}

.btn-submit svg {
  transition: transform 0.3s ease;
}

.btn-submit:hover svg {
  transform: translateX(4px);
}

.btn-submit:active {
  transform: translateY(-1px);
}

.btn-submit:disabled {
  opacity: 0.6;
  cursor: not-allowed;
  transform: none;
}

.error-message {
  color: #e05c5c;
  font-size: 0.85rem;
  text-align: center;
  margin: 0;
}

.auth-footer {
  margin-top: 32px;
  padding-top: 24px;
  border-top: 1px solid rgba(255, 255, 255, 0.06);
  text-align: center;
}

.auth-footer p {
  font-size: 0.9rem;
  color: #8e9aaf;
  margin: 0;
}

.auth-footer a {
  color: #c9a84c;
  text-decoration: none;
  font-weight: 700;
  transition: all 0.2s ease;
  position: relative;
}

.auth-footer a::after {
  content: '';
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 100%;
  height: 1px;
  background: #c9a84c;
  transform: scaleX(0);
  transition: transform 0.3s ease;
  transform-origin: right;
}

.auth-footer a:hover::after {
  transform: scaleX(1);
  transform-origin: left;
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

@media (max-width: 600px) {
  .form-row {
    grid-template-columns: 1fr;
    gap: 20px;
  }

  .auth-card {
    padding: 36px 24px;
  }

  .auth-title {
    font-size: 1.6rem;
  }

  .header-icon {
    width: 56px;
    height: 56px;
  }

  .btn-submit {
    padding: 14px;
    font-size: 0.95rem;
  }
}
</style>
