<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-auth">
      <div class="auth-container">
        <div class="auth-card">
          
          <header class="auth-header">
            <div class="header-icon">
              <svg width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9" />
                <path d="M13.73 21a2 2 0 0 1-3.46 0" />
              </svg>
            </div>
            <h2 class="auth-title">Esqueceu a senha?</h2>
            <p class="auth-subtitle">Digite seu e-mail para receber o código de verificação</p>
          </header>

          <form @submit.prevent="enviarCodigo" class="auth-form">
            <div class="form-group">
              <label>E-mail</label>
              <div class="input-with-icon">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/>
                  <polyline points="22,6 12,13 2,6"/>
                </svg>
                <input 
                  v-model="email" 
                  type="email" 
                  placeholder="nome@email.com" 
                  required 
                  autocomplete="email"
                />
              </div>
            </div>

            <button type="submit" class="btn-submit" :disabled="loading">
              <span>{{ loading ? 'Enviando...' : 'Enviar código' }}</span>
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
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()
const email = ref('')
const loading = ref(false)

function enviarCodigo() {
  if (!email.value) {
    alert('Digite seu e-mail')
    return
  }

  loading.value = true
  
  setTimeout(() => {
    loading.value = false
    sessionStorage.setItem('resetEmail', email.value)
    router.push('/recuperar-senha')
  }, 1500)
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
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.5);
  transition: transform 0.3s ease;
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

.form-group label {
  font-size: 0.75rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.5px;
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

.input-with-icon input:focus ~ svg {
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

.btn-submit:hover:not(:disabled) {
  background: #d4af37;
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(201, 168, 76, 0.3);
}

.btn-submit:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.btn-submit svg {
  transition: transform 0.3s ease;
}

.btn-submit:hover:not(:disabled) svg {
  transform: translateX(4px);
}

.auth-footer {
  margin-top: 32px;
  text-align: center;
}

.auth-footer p { 
  color: #8e9aaf; 
  font-size: 0.9rem;
}

.auth-footer a { 
  color: #c9a84c; 
  font-weight: 700; 
  text-decoration: none;
  transition: all 0.2s ease;
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
}
</style>