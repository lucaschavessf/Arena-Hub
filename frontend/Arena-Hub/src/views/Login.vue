<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-auth">
      <div class="auth-container">
        <div class="auth-card">
          
          <header class="auth-header">
            <div class="header-icon">
              <svg width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4M10 17l5-5-5-5M13.8 12H3"/>
              </svg>
            </div>
            <h2 class="auth-title">Bem-vindo de volta</h2>
            <p class="auth-subtitle">Acesse sua conta para continuar</p>
          </header>

          <form @submit.prevent="login" class="auth-form">
            <div class="form-group">
              <label>E-mail</label>
              <div class="input-with-icon">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/>
                  <polyline points="22,6 12,13 2,6"/>
                </svg>
                <input 
                  v-model="form.email" 
                  type="email" 
                  placeholder="nome@email.com" 
                  required 
                  autocomplete="email"
                />
              </div>
            </div>

            <div class="form-group">
              <div class="label-wrapper">
                <label>Senha</label>
                <a href="#" class="forgot-link">Esqueceu?</a>
              </div>
              <div class="input-with-icon">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/>
                  <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                </svg>
                <input 
                  v-model="form.senha" 
                  type="password" 
                  placeholder="••••••••" 
                  required 
                  autocomplete="current-password"
                />
              </div>
            </div>

            <button type="submit" class="btn-submit">
              <span>Entrar</span>
              <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <line x1="5" y1="12" x2="19" y2="12"/>
                <polyline points="12 5 19 12 12 19"/>
              </svg>
            </button>
          </form>

          <footer class="auth-footer">
            <p>Não tem conta? <router-link to="/cadastro">Criar conta</router-link></p>
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
  email: '',
  senha: ''
})

function login() {
  if (!form.email || !form.senha) {
    alert("Preencha todos os campos")
    return
  }

  console.log('Logando:', form)
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
  max-width: 440px;
  animation: fadeInUp 0.6s cubic-bezier(0.4, 0, 0.2, 1);
}

.auth-card {
  background: #121826;
  border: 1px solid rgba(201, 168, 76, 0.12);
  border-radius: 24px;
  padding: 44px 40px;
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.5), 
              0 0 0 1px rgba(201, 168, 76, 0.05);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.auth-card:hover {
  box-shadow: 0 35px 70px rgba(0, 0, 0, 0.6), 
              0 0 0 1px rgba(201, 168, 76, 0.1);
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

.label-wrapper {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.form-group label {
  font-size: 0.75rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.forgot-link {
  font-size: 0.75rem;
  color: #8e9aaf;
  text-decoration: none;
  transition: color 0.2s ease;
  font-weight: 500;
}

.forgot-link:hover {
  color: #c9a84c;
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

.form-group input:hover {
  border-color: rgba(201, 168, 76, 0.2);
  background: #1e2638;
}

.form-group input:focus {
  border-color: #c9a84c;
  box-shadow: 0 0 0 4px rgba(201, 168, 76, 0.1);
  background: #1e2638;
}

.form-group input::placeholder {
  color: #5a6375;
  font-weight: 300;
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
  transition: opacity 0.3s ease;
  pointer-events: none;
}

.input-with-icon input:focus ~ svg,
.input-with-icon input:hover ~ svg {
  opacity: 1;
}

.input-with-icon input { 
  padding-left: 46px; 
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

.btn-submit:hover {
  background: #d4af37;
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(201, 168, 76, 0.3);
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
  text-align: center;
}

.auth-footer p { 
  color: #8e9aaf; 
  font-size: 0.9rem;
  margin: 0;
}

.auth-footer a { 
  color: #c9a84c; 
  font-weight: 700; 
  text-decoration: none;
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

/* Responsivo */
@media (max-width: 480px) {
  .auth-card {
    padding: 32px 24px;
  }
  
  .auth-title {
    font-size: 1.5rem;
  }
  
  .header-icon {
    width: 56px;
    height: 56px;
  }
  
  .btn-submit {
    padding: 14px;
    font-size: 0.9rem;
  }
}
</style>