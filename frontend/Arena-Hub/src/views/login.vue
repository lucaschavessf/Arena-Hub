<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-auth">
      <div class="auth-container">
        <div class="auth-card">
          
          <header class="auth-header">
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
                <input v-model="form.email" type="email" placeholder="nome@email.com" required />
              </div>
            </div>

            <div class="form-group">
              <label>Senha</label>
              <input v-model="form.senha" type="password" placeholder="••••••••" required />
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
  // Simulação (depois tu liga no backend)
  if (!form.email || !form.senha) {
    alert("Preenche tudo direito");
    return;
  }

  // Aqui vai ser tua API depois
  console.log('Logando:', form)

  router.push('/')
}
</script>

<style scoped>
.page { min-height: 100vh; display: flex; flex-direction: column; background: #0a0e17; }

.main-auth {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 60px 20px;
}

.auth-container {
  width: 100%;
  max-width: 420px; /* menor que cadastro */
  animation: fadeIn 0.5s ease-out;
}

.auth-card {
  background: #121826;
  border: 1px solid rgba(201, 168, 76, 0.15);
  border-radius: 20px;
  padding: 40px;
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.5);
}

.auth-header { text-align: center; margin-bottom: 28px; }
.auth-title { font-size: 1.6rem; font-weight: 800; color: #fff; }
.auth-subtitle { color: #8e9aaf; font-size: 0.9rem; }

.auth-form { display: flex; flex-direction: column; gap: 20px; }

.form-group { display: flex; flex-direction: column; gap: 6px; }

.form-group label {
  font-size: 0.7rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
}

.form-group input {
  background: #1c2433;
  border: 1.5px solid rgba(255,255,255,0.05);
  border-radius: 10px;
  padding: 14px;
  color: white;
  outline: none;
}

.form-group input:focus {
  border-color: #c9a84c;
  box-shadow: 0 0 0 3px rgba(201,168,76,0.1);
}

.input-with-icon { position: relative; }
.input-with-icon svg {
  position: absolute;
  left: 14px;
  top: 50%;
  transform: translateY(-50%);
  color: #c9a84c;
}
.input-with-icon input { padding-left: 40px; }

.btn-submit {
  margin-top: 10px;
  background: #c9a84c;
  color: #0a0e17;
  border-radius: 10px;
  padding: 14px;
  font-weight: 800;
  cursor: pointer;
  display: flex;
  justify-content: center;
  gap: 10px;
}

.auth-footer {
  margin-top: 24px;
  text-align: center;
}

.auth-footer p { color: #8e9aaf; }
.auth-footer a { color: #c9a84c; font-weight: 700; }

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(10px); }
  to { opacity: 1; transform: translateY(0); }
}
</style>