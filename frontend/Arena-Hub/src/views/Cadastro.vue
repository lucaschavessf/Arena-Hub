<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-auth">
      <div class="auth-container">
        <div class="auth-card">
          <header class="auth-header">
            <h2 class="auth-title">Crie sua conta</h2>
            <p class="auth-subtitle">Junte-se à maior rede de eventos da região</p>
          </header>

          <form @submit.prevent="cadastrar" class="auth-form">
            <div class="form-row">
              <div class="form-group">
                <label>Nome</label>
                <input v-model="form.nome" type="text" placeholder="Ex: João" required />
              </div>
              <div class="form-group">
                <label>Sobrenome</label>
                <input v-model="form.sobrenome" type="text" placeholder="Ex: Silva" required />
              </div>
            </div>

            <div class="form-group">
              <label>E-mail</label>
              <div class="input-with-icon">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/><polyline points="22,6 12,13 2,6"/></svg>
                <input v-model="form.email" type="email" placeholder="nome@email.com" required />
              </div>
            </div>

            <div class="form-group">
              <label>Cidade</label>
              <input v-model="form.cidade" type="text" placeholder="Recife, PE" />
            </div>

            <div class="form-row">
              <div class="form-group">
                <label>Senha</label>
                <input v-model="form.senha" type="password" placeholder="••••••••" required />
              </div>
              <div class="form-group">
                <label>Confirmar Senha</label>
                <input v-model="form.confirmarSenha" type="password" placeholder="••••••••" required />
              </div>
            </div>

            <button type="submit" class="btn-submit">
              <span>Finalizar Cadastro</span>
              <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><line x1="5" y1="12" x2="19" y2="12"/><polyline points="12 5 19 12 12 19"/></svg>
            </button>
          </form>

          <footer class="auth-footer">
            <p>Já possui uma conta? <router-link to="/">Fazer Login</router-link></p>
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
  sobrenome: '', 
  email: '', 
  cidade: '', 
  senha: '', 
  confirmarSenha: '' 
})

function cadastrar() { 
  if (form.senha !== form.confirmarSenha) {
    alert("As senhas não coincidem!");
    return;
  }
  router.push('/') 
}
</script>

<style scoped>
.page { min-height: 100vh; display: flex; flex-direction: column; background: #0a0e17; }

.main-auth {
  flex: 1;
  display: flex;
  align-items: center; /* Centraliza verticalmente no PC */
  justify-content: center; /* Centraliza horizontalmente no PC */
  padding: 60px 20px;
}

.auth-container {
  width: 100%;
  max-width: 500px; /* Largura otimizada para Desktop sem "esticar" o formulário */
  animation: fadeIn 0.5s ease-out;
}

.auth-card {
  background: #121826;
  border: 1px solid rgba(201, 168, 76, 0.15);
  border-radius: 20px;
  padding: 48px;
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.5);
}

.auth-header { text-align: center; margin-bottom: 32px; }
.auth-title { font-size: 1.8rem; font-weight: 800; color: #fff; margin-bottom: 8px; }
.auth-subtitle { color: #8e9aaf; font-size: 0.95rem; }

.auth-form { display: flex; flex-direction: column; gap: 24px; }

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.form-group { display: flex; flex-direction: column; gap: 8px; }
.form-group label {
  font-size: 0.7rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.8px;
}

.form-group input {
  background: #1c2433;
  border: 1.5px solid rgba(255, 255, 255, 0.05);
  border-radius: 10px;
  padding: 14px 16px;
  color: #fff;
  font-size: 1rem;
  transition: 0.2s;
  outline: none;
}

.form-group input:focus {
  border-color: #c9a84c;
  background: #242d3d;
  box-shadow: 0 0 0 4px rgba(201, 168, 76, 0.1);
}

.input-with-icon { position: relative; display: flex; align-items: center; }
.input-with-icon svg { position: absolute; left: 16px; color: #c9a84c; opacity: 0.7; }
.input-with-icon input { padding-left: 48px; width: 100%; }

.btn-submit {
  margin-top: 10px;
  background: #c9a84c;
  color: #0a0e17;
  border-radius: 10px;
  padding: 16px;
  font-weight: 800;
  font-size: 1rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  transition: 0.3s;
}

.btn-submit:hover {
  background: #dfbc5e;
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(201, 168, 76, 0.2);
}

.auth-footer {
  margin-top: 32px;
  padding-top: 24px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
  text-align: center;
}

.auth-footer p { font-size: 0.9rem; color: #8e9aaf; }
.auth-footer a { color: #c9a84c; text-decoration: none; font-weight: 700; }

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(15px); }
  to { opacity: 1; transform: translateY(0); }
}

@media (max-width: 600px) {
  .form-row { grid-template-columns: 1fr; }
  .auth-card { padding: 32px 24px; }
}
</style>