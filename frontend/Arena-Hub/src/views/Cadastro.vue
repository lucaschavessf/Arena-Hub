<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-auth">
      <div class="auth-container">
        <div class="auth-card">
          <header class="auth-header">
            <div class="header-icon">
              <svg width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2"/>
                <circle cx="9" cy="7" r="4"/>
                <path d="M22 21v-2a4 4 0 0 0-3-3.87"/>
                <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
              </svg>
            </div>
            <h2 class="auth-title">Crie sua conta</h2>
            <p class="auth-subtitle">Junte-se à maior rede de eventos da região</p>
          </header>

          <form @submit.prevent="cadastrar" class="auth-form">
            <div class="form-group">
              <label>Nome completo</label>
              <div class="input-with-icon">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                  <circle cx="12" cy="7" r="4"/>
                </svg>
                <input v-model="form.nome" type="text" placeholder="João da Silva" required />
              </div>
            </div>

            <div class="form-group">
              <label>CPF</label>
              <div class="input-with-icon">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="5" width="18" height="14" rx="2"/>
                  <line x1="3" y1="10" x2="21" y2="10"/>
                  <line x1="7" y1="15" x2="10" y2="15"/>
                  <line x1="14" y1="15" x2="17" y2="15"/>
                </svg>
                <input 
                  v-model="form.cpf" 
                  type="text" 
                  placeholder="000.000.000-00" 
                  required 
                  maxlength="14"
                  @input="formatarCPF"
                />
              </div>
            </div>

            <div class="form-group">
              <label>E-mail</label>
              <div class="input-with-icon">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/>
                  <polyline points="22,6 12,13 2,6"/>
                </svg>
                <input v-model="form.email" type="email" placeholder="nome@email.com" required />
              </div>
            </div>

            <div class="form-row">
              <div class="form-group">
                <label>Senha</label>
                <div class="input-with-icon">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/>
                    <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                  </svg>
                  <input v-model="form.senha" type="password" placeholder="••••••••" required />
                </div>
              </div>
              <div class="form-group">
                <label>Confirmar Senha</label>
                <div class="input-with-icon">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/>
                    <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                    <path d="M12 15v3"/>
                  </svg>
                  <input v-model="form.confirmarSenha" type="password" placeholder="••••••••" required />
                </div>
              </div>
            </div>

            <button type="submit" class="btn-submit">
              <span>Finalizar Cadastro</span>
              <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <line x1="5" y1="12" x2="19" y2="12"/>
                <polyline points="12 5 19 12 12 19"/>
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
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()
const form = reactive({ 
  nome: '', 
  cpf: '', 
  email: '', 
  senha: '', 
  confirmarSenha: '' 
})

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

function cadastrar() {
  if (!form.nome || !form.cpf || !form.email || !form.senha || !form.confirmarSenha) {
    alert("Preencha todos os campos obrigatórios!")
    return
  }
  
  if (!validarCPF(form.cpf)) {
    alert("CPF inválido!")
    return
  }
  
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailRegex.test(form.email)) {
    alert("E-mail inválido!")
    return
  }
  
  if (form.senha !== form.confirmarSenha) {
    alert("As senhas não coincidem!")
    return
  }
  
  if (form.senha.length < 6) {
    alert("A senha deve ter pelo menos 6 caracteres!")
    return
  }
  
  console.log('Cadastrando:', {
    nome: form.nome,
    cpf: form.cpf.replace(/\D/g, ''),
    email: form.email
  })
  
  router.push('/')
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
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.5), 
              0 0 0 1px rgba(201, 168, 76, 0.05);
  transition: box-shadow 0.3s ease;
}

.auth-card:hover {
  box-shadow: 0 35px 70px rgba(0, 0, 0, 0.6), 
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