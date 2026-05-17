<template>
  <div class="admin-layout" :class="{ 'sidebar-open': isSidebarOpen }">
    <div class="sidebar-overlay" @click="isSidebarOpen = false"></div>

    <aside class="sidebar">
      <div class="sidebar-header">
        <router-link to="/" class="brand">
          <div class="logo-box">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <path d="M2 9V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v4"/>
              <path d="M2 15v4a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-4"/>
              <path d="M2 9a3 3 0 0 1 0 6"/>
              <path d="M22 9a3 3 0 0 0 0 6"/>
            </svg>
          </div>
          <span>Arena<span class="highlight">Hub</span></span>
        </router-link>
      </div>

      <nav class="sidebar-nav">
        <router-link to="/admin" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/>
            <rect x="3" y="14" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/>
          </svg>
          <span>Dashboard</span>
        </router-link>
        
        <router-link to="/admin/agenda" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
          </svg>
          <span>Agenda</span>
        </router-link>

        <router-link to="/admin/produtores" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="8" r="4"/>
            <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
          </svg>
          <span>Produtores</span>
        </router-link>

        <router-link to="/admin/solicitacoes" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/>
            <polyline points="14 2 14 8 20 8"/>
            <path d="M9 15l2 2 4-4"/>
          </svg>
          <span>Solicitações</span>
        </router-link>

        <router-link to="/admin/eventos" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="4" width="18" height="18" rx="2" ry="2"/>
            <line x1="16" y1="2" x2="16" y2="6"/>
            <line x1="8" y1="2" x2="8" y2="6"/>
            <line x1="3" y1="10" x2="21" y2="10"/>
          </svg>
          <span>Eventos</span>
        </router-link>

        <router-link to="/admin/precos" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"/>
            <path d="M12 6v6l4 2"/>
          </svg>
          <span>Preços</span>
        </router-link>

        <div class="nav-spacer"></div>

        <router-link to="/admin/perfil" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="8" r="4"/>
            <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
          </svg>
          <span>Perfil</span>
        </router-link>

        <router-link to="/admin/criar-conta" class="nav-item active">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2"/>
            <circle cx="9" cy="7" r="4"/>
            <line x1="19" y1="8" x2="19" y2="14"/>
            <line x1="22" y1="11" x2="16" y2="11"/>
          </svg>
          <span>Criar Admin</span>
        </router-link>

        <button @click="$router.push('/')" class="nav-item logout">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/>
          </svg>
          <span>Sair do Admin</span>
        </button>
      </nav>
    </aside>

    <div class="main-wrapper">
      <AppNavbar :isAdmin="true" />
      
      <main class="content-container">
        <div class="breadcrumb">
          <router-link to="/admin" class="breadcrumb-link">Admin</router-link>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
          <span class="breadcrumb-current">Criar Administrador</span>
        </div>

        <header class="content-header">
          <div class="header-titles">
            <h1>Criar Nova Conta</h1>
            <p>Adicione novos administradores ao sistema</p>
          </div>
        </header>

        <div class="form-container">
          <form @submit.prevent="criarConta" class="admin-form">
            <div class="form-section">
              <h3 class="section-title">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="8" r="4"/>
                  <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
                </svg>
                Dados Pessoais
              </h3>
              
              <div class="form-row">
                <div class="form-field">
                  <label>Nome completo *</label>
                  <input type="text" v-model="form.nome" class="input-field" required />
                </div>
                <div class="form-field">
                  <label>E-mail *</label>
                  <input type="email" v-model="form.email" class="input-field" required />
                </div>
              </div>

              <div class="form-row">
                <div class="form-field">
                  <label>Telefone</label>
                  <input type="tel" v-model="form.telefone" class="input-field" placeholder="(81) 99999-9999" />
                </div>
              </div>
            </div>

            <div class="form-section">
              <h3 class="section-title">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/>
                  <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                </svg>
                Credenciais de Acesso
              </h3>

              <div class="form-row">
                <div class="form-field">
                  <label>Senha *</label>
                  <div class="password-wrapper">
                    <input :type="showPassword ? 'text' : 'password'" v-model="form.senha" class="input-field" required />
                    <button type="button" class="toggle-password" @click="showPassword = !showPassword">
                      <svg v-if="!showPassword" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                        <circle cx="12" cy="12" r="3"/>
                      </svg>
                      <svg v-else width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                        <line x1="1" y1="1" x2="23" y2="23"/>
                      </svg>
                    </button>
                  </div>
                </div>
                <div class="form-field">
                  <label>Confirmar Senha *</label>
                  <div class="password-wrapper">
                    <input :type="showConfirmPassword ? 'text' : 'password'" v-model="form.confirmarSenha" class="input-field" required />
                    <button type="button" class="toggle-password" @click="showConfirmPassword = !showConfirmPassword">
                      <svg v-if="!showConfirmPassword" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                        <circle cx="12" cy="12" r="3"/>
                      </svg>
                      <svg v-else width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                        <line x1="1" y1="1" x2="23" y2="23"/>
                      </svg>
                    </button>
                  </div>
                </div>
              </div>

              <div class="password-strength" v-if="form.senha">
                <div class="strength-bar">
                  <div class="strength-fill" :class="strengthClass" :style="{ width: strengthPercent + '%' }"></div>
                </div>
                <span class="strength-text">{{ strengthText }}</span>
              </div>
            </div>

            <div class="form-actions">
              <button type="button" class="btn-cancelar" @click="resetForm">Limpar</button>
              <button type="submit" class="btn-criar" :disabled="loading">
                {{ loading ? 'Criando...' : 'Criar Administrador' }}
              </button>
            </div>
          </form>

          <div class="admins-list">
            <div class="list-header">
              <h3>Administradores do Sistema</h3>
              <span class="total-count">{{ administradores.length }} administradores</span>
            </div>
            
            <div class="admins-table-wrapper">
              <table class="admins-table">
                <thead>
                  <tr>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Status</th>
                    <th>Último Acesso</th>
                    <th>Ações</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="admin in administradores" :key="admin.id">
                    <td>
                      <div class="admin-info">
                        <div class="admin-avatar">{{ admin.nome.charAt(0) }}</div>
                        <span>{{ admin.nome }}</span>
                      </div>
                    </td>
                    <td>{{ admin.email }}</td>
                    <td>
                      <span class="status-badge" :class="admin.status">
                        {{ admin.status === 'ativo' ? 'Ativo' : 'Inativo' }}
                      </span>
                    </td>
                    <td>{{ admin.ultimoAcesso || 'Nunca' }}</td>
                    <td>
                      <div class="table-actions">
                        <button class="action-btn editar" @click="editarAdmin(admin)" title="Editar">
                          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <path d="M17 3l4 4-7 7H10v-4l7-7z"/>
                            <path d="M4 20h16"/>
                          </svg>
                        </button>
                        <button class="action-btn desativar" @click="desativarAdmin(admin)" title="Desativar">
                          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <circle cx="12" cy="12" r="10"/>
                            <line x1="4.93" y1="4.93" x2="19.07" y2="19.07"/>
                          </svg>
                        </button>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </main>
    </div>

    <button class="menu-toggle" @click="isSidebarOpen = !isSidebarOpen">
      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
        <line x1="3" y1="12" x2="21" y2="12"/><line x1="3" y1="6" x2="21" y2="6"/><line x1="3" y1="18" x2="21" y2="18"/>
      </svg>
    </button>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'

const router = useRouter()
const isSidebarOpen = ref(false)
const loading = ref(false)
const showPassword = ref(false)
const showConfirmPassword = ref(false)

const form = ref({
  nome: '',
  email: '',
  telefone: '',
  cargo: '',
  senha: '',
  confirmarSenha: '',
  permissoes: {
    dashboard: true,
    agenda: true,
    produtores: false,
    solicitacoes: true,
    eventos: true,
    precos: false,
    criarAdmin: false,
    relatorios: false
  }
})

const administradores = ref<any[]>([])

const strengthPercent = computed(() => {
  let score = 0
  const senha = form.value.senha
  
  if (senha.length >= 6) score += 20
  if (senha.match(/[a-z]/)) score += 20
  if (senha.match(/[A-Z]/)) score += 20
  if (senha.match(/[0-9]/)) score += 20
  if (senha.match(/[^a-zA-Z0-9]/)) score += 20
  
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

function resetForm() {
  form.value = {
    nome: '',
    email: '',
    telefone: '',
    cargo: '',
    senha: '',
    confirmarSenha: '',
    permissoes: {
      dashboard: true,
      agenda: true,
      produtores: false,
      solicitacoes: true,
      eventos: true,
      precos: false,
      criarAdmin: false,
      relatorios: false
    }
  }
}

function criarConta() {
  if (!form.value.nome || !form.value.email || !form.value.cargo || !form.value.senha) {
    alert('Preencha todos os campos obrigatórios')
    return
  }
  
  if (form.value.senha !== form.value.confirmarSenha) {
    alert('As senhas não coincidem')
    return
  }
  
  if (form.value.senha.length < 6) {
    alert('A senha deve ter no mínimo 6 caracteres')
    return
  }
  
  loading.value = true
  
  setTimeout(() => {
    administradores.value.unshift({
      id: Date.now(),
      nome: form.value.nome,
      email: form.value.email,
      cargo: form.value.cargo,
      status: 'ativo',
      ultimoAcesso: 'Nunca'
    })
    
    loading.value = false
    alert(`Administrador ${form.value.nome} criado com sucesso!`)
    resetForm()
  }, 1500)
}

function editarAdmin(admin: any) {
  alert(`Editar administrador ${admin.nome} - Em desenvolvimento`)
}

function desativarAdmin(admin: any) {
  if (confirm(`Deseja desativar o administrador "${admin.nome}"?`)) {
    admin.status = admin.status === 'ativo' ? 'inativo' : 'ativo'
    alert(`Administrador ${admin.nome} ${admin.status === 'ativo' ? 'ativado' : 'desativado'} com sucesso!`)
  }
}
</script>

<style scoped>
.admin-layout {
  display: flex;
  min-height: 100vh;
  background: #0a0e17;
  color: #fff;
  font-family: 'Plus Jakarta Sans', sans-serif;
}

.sidebar {
  width: 260px;
  background: linear-gradient(180deg, #111827 0%, #0d121c 100%);
  border-right: 1px solid rgba(201, 168, 76, 0.1);
  position: fixed;
  height: 100vh;
  z-index: 100;
  display: flex;
  flex-direction: column;
}

.sidebar-header {
  padding: 28px 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.brand {
  display: flex;
  align-items: center;
  gap: 10px;
  text-decoration: none;
  color: white;
  font-weight: 700;
  font-size: 1.2rem;
}

.logo-box {
  width: 40px;
  height: 40px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.2);
}

.brand .highlight {
  color: #c9a84c;
  font-size: 0.8rem;
  text-transform: uppercase;
  margin-left: 2px;
}

.sidebar-nav {
  padding: 24px 16px;
  display: flex;
  flex-direction: column;
  gap: 6px;
  flex: 1;
}

.nav-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  border-radius: 12px;
  color: #8e9aaf;
  text-decoration: none;
  font-size: 0.9rem;
  font-weight: 500;
  transition: all 0.3s ease;
}

.nav-item:hover {
  background: rgba(201, 168, 76, 0.08);
  color: #c9a84c;
  transform: translateX(4px);
}

.nav-item.active {
  background: linear-gradient(90deg, rgba(201, 168, 76, 0.15), rgba(201, 168, 76, 0.05));
  color: #c9a84c;
  border-left: 3px solid #c9a84c;
}

.nav-spacer {
  flex: 1;
}

.logout {
  margin-top: auto;
  color: #ef4444;
  background: none;
  border: none;
  width: 100%;
  cursor: pointer;
  text-align: left;
}

.logout:hover {
  background: rgba(239, 68, 68, 0.08);
  color: #ef4444;
}

.main-wrapper {
  flex: 1;
  margin-left: 260px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: calc(100% - 260px);
}

.content-container {
  width: 100%;
  max-width: 1200px;
  padding: 32px 40px;
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 20px;
  font-size: 0.85rem;
  color: #8e9aaf;
}

.breadcrumb-link {
  color: #8e9aaf;
  text-decoration: none;
  transition: color 0.2s;
}

.breadcrumb-link:hover {
  color: #c9a84c;
}

.breadcrumb-current {
  color: #c9a84c;
  font-weight: 600;
}

.content-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 32px;
}

.header-titles h1 {
  font-family: 'Bebas Neue', sans-serif;
  font-size: 2.2rem;
  letter-spacing: 1px;
  margin-bottom: 4px;
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.header-titles p {
  color: #8e9aaf;
  font-size: 0.85rem;
}

.form-container {
  display: flex;
  flex-direction: column;
  gap: 32px;
}

.admin-form {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  padding: 32px;
}

.form-section {
  margin-bottom: 32px;
  padding-bottom: 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.form-section:last-child {
  border-bottom: none;
  padding-bottom: 0;
  margin-bottom: 0;
}

.section-title {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 1rem;
  font-weight: 700;
  color: #c9a84c;
  margin-bottom: 24px;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
  margin-bottom: 20px;
}

.form-field {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-field label {
  font-size: 0.75rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.input-field {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 10px;
  padding: 12px 14px;
  color: white;
  font-size: 0.9rem;
  outline: none;
  transition: all 0.3s;
}

.input-field:focus {
  border-color: #c9a84c;
  box-shadow: 0 0 0 3px rgba(201, 168, 76, 0.1);
}

.password-wrapper {
  position: relative;
}

.password-wrapper input {
  padding-right: 40px;
  width: 100%;
}

.toggle-password {
  position: absolute;
  right: 12px;
  top: 50%;
  transform: translateY(-50%);
  background: transparent;
  border: none;
  color: #8e9aaf;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}

.toggle-password:hover {
  color: #c9a84c;
}

.password-strength {
  margin-top: 12px;
}

.strength-bar {
  height: 4px;
  background: #1a2233;
  border-radius: 4px;
  overflow: hidden;
  margin-bottom: 6px;
}

.strength-fill {
  height: 100%;
  transition: width 0.3s, background 0.3s;
}

.strength-fill.very-weak { background: #ef4444; width: 20%; }
.strength-fill.weak { background: #f59e0b; width: 40%; }
.strength-fill.medium { background: #eab308; width: 60%; }
.strength-fill.strong { background: #10b981; width: 80%; }
.strength-fill.very-strong { background: #10b981; width: 100%; }

.strength-text {
  font-size: 0.7rem;
  color: #8e9aaf;
}

.permissions-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;
}

.permission-item {
  display: flex;
  align-items: center;
  gap: 10px;
  cursor: pointer;
  color: #e0e0e0;
  font-size: 0.85rem;
  padding: 8px 12px;
  background: rgba(255, 255, 255, 0.02);
  border-radius: 8px;
  transition: all 0.2s;
}

.permission-item:hover {
  background: rgba(201, 168, 76, 0.05);
}

.permission-item input {
  width: 16px;
  height: 16px;
  cursor: pointer;
  accent-color: #c9a84c;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 16px;
  margin-top: 24px;
}

.btn-cancelar {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 12px 24px;
  border-radius: 10px;
  color: #8e9aaf;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s;
}

.btn-cancelar:hover {
  background: #1e2638;
}

.btn-criar {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 12px 32px;
  border-radius: 10px;
  color: #0a0e17;
  font-weight: 700;
  cursor: pointer;
  transition: all 0.3s;
}

.btn-criar:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.3);
}

.btn-criar:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.admins-list {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  padding: 32px;
}

.list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  padding-bottom: 16px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.list-header h3 {
  font-size: 1rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.total-count {
  font-size: 0.8rem;
  color: #6b7280;
}

.admins-table-wrapper {
  overflow-x: auto;
}

.admins-table {
  width: 100%;
  border-collapse: collapse;
}

.admins-table th {
  text-align: left;
  padding: 12px 12px;
  font-size: 0.7rem;
  color: #6b7280;
  text-transform: uppercase;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.admins-table td {
  padding: 14px 12px;
  font-size: 0.85rem;
  color: #b0b8c5;
  border-bottom: 1px solid rgba(255, 255, 255, 0.02);
}

.admin-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.admin-avatar {
  width: 32px;
  height: 32px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 700;
  color: #0a0e17;
}

.status-badge {
  display: inline-block;
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 700;
}

.status-badge.ativo {
  background: rgba(16, 185, 129, 0.15);
  color: #10b981;
  border: 1px solid rgba(16, 185, 129, 0.3);
}

.status-badge.inativo {
  background: rgba(239, 68, 68, 0.15);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}

.table-actions {
  display: flex;
  gap: 8px;
}

.action-btn {
  background: transparent;
  border: none;
  padding: 6px;
  border-radius: 6px;
  cursor: pointer;
  color: #8e9aaf;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.action-btn.editar:hover {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
}

.action-btn.desativar:hover {
  background: rgba(239, 68, 68, 0.15);
  color: #ef4444;
}

.menu-toggle {
  display: none;
  position: fixed;
  bottom: 20px;
  right: 20px;
  width: 50px;
  height: 50px;
  border-radius: 14px;
  background: #c9a84c;
  color: #0a0e17;
  border: none;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  z-index: 150;
  box-shadow: 0 8px 20px rgba(201, 168, 76, 0.3);
}

.sidebar-overlay {
  display: none;
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.7);
  backdrop-filter: blur(4px);
  z-index: 90;
}

@media (max-width: 1024px) {
  .sidebar {
    transform: translateX(-100%);
    transition: transform 0.3s ease;
  }
  
  .admin-layout.sidebar-open .sidebar {
    transform: translateX(0);
  }
  
  .admin-layout.sidebar-open .sidebar-overlay {
    display: block;
  }
  
  .main-wrapper {
    margin-left: 0;
    width: 100%;
  }
  
  .menu-toggle {
    display: flex;
  }
  
  .content-container {
    padding: 24px 20px;
  }
  
  .form-row {
    grid-template-columns: 1fr;
  }
  
  .permissions-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 600px) {
  .permissions-grid {
    grid-template-columns: 1fr;
  }
  
  .list-header {
    flex-direction: column;
    gap: 8px;
    align-items: flex-start;
  }
  
  .admin-form {
    padding: 24px 20px;
  }
  
  .admins-list {
    padding: 24px 20px;
  }
  
  .admins-table th,
  .admins-table td {
    padding: 10px 8px;
  }
  
  .admin-info {
    flex-direction: column;
    text-align: center;
  }
}
</style>