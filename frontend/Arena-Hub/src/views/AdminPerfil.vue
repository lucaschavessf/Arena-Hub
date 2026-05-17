<template>
  <div class="admin-layout" :class="{ 'sidebar-open': isSidebarOpen }">
    <div class="sidebar-overlay" @click="isSidebarOpen = false"></div>

    <aside class="sidebar">
      <div class="sidebar-header">
        <router-link to="/" class="brand">
          <div class="logo-box">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <path d="M2 9V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v4" />
              <path d="M2 15v4a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-4" />
              <path d="M2 9a3 3 0 0 1 0 6" />
              <path d="M22 9a3 3 0 0 0 0 6" />
            </svg>
          </div>
          <span>Arena<span class="highlight">Hub</span></span>
        </router-link>
      </div>

      <nav class="sidebar-nav">
        <router-link to="/admin" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <rect x="3" y="3" width="7" height="7" />
            <rect x="14" y="3" width="7" height="7" />
            <rect x="3" y="14" width="7" height="7" />
            <rect x="14" y="14" width="7" height="7" />
          </svg>
          <span>Dashboard</span>
        </router-link>

        <router-link to="/admin/agenda" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <rect x="3" y="4" width="18" height="18" rx="2" />
            <path d="M16 2v4M8 2v4M3 10h18" />
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
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z" />
            <polyline points="14 2 14 8 20 8" />
            <path d="M9 15l2 2 4-4" />
          </svg>
          <span>Solicitações</span>
        </router-link>

        <router-link to="/admin/eventos" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <rect x="3" y="4" width="18" height="18" rx="2" ry="2" />
            <line x1="16" y1="2" x2="16" y2="6" />
            <line x1="8" y1="2" x2="8" y2="6" />
            <line x1="3" y1="10" x2="21" y2="10" />
          </svg>
          <span>Eventos</span>
        </router-link>

        <router-link to="/admin/precos" class="nav-item">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <circle cx="12" cy="12" r="10" />
            <path d="M12 6v6l4 2" />
          </svg>
          <span>Preços</span>
        </router-link>

        <div class="nav-spacer"></div>

        <router-link to="/admin/perfil" class="nav-item active">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <circle cx="12" cy="8" r="4" />
            <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7" />
          </svg>
          <span>Perfil</span>
        </router-link>

        <router-link to="/admin/criar-conta" class="nav-item">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2"/>
            <circle cx="9" cy="7" r="4"/>
            <line x1="19" y1="8" x2="19" y2="14"/>
            <line x1="22" y1="11" x2="16" y2="11"/>
          </svg>
          <span>Criar Admin</span>
        </router-link>

        <button @click="$router.push('/')" class="nav-item logout">
          <svg
            width="18"
            height="18"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
          >
            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4" />
            <polyline points="16 17 21 12 16 7" />
            <line x1="21" y1="12" x2="9" y2="12" />
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
          <svg
            width="14"
            height="14"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2.5"
          >
            <polyline points="9 18 15 12 9 6" />
          </svg>
          <span class="breadcrumb-current">Meu Perfil</span>
        </div>

        <div class="perfil-wrapper">
          <div class="perfil-card foto-card">
            <div class="foto-area">
              <div class="avatar-circle">
                <span class="avatar-iniciais">{{ admin.nome.charAt(0) }}</span>
              </div>
              <button class="btn-upload">
                <svg
                  width="14"
                  height="14"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path d="M12 4v10M8 10l4 4 4-4" />
                </svg>
                Alterar Foto
              </button>
            </div>
            <div class="info-area">
              <h2>{{ admin.nome }}</h2>
              <span class="role-badge">Administrador</span>
              <div class="info-stats">
                <div class="stat">
                  <span class="stat-value">12</span>
                  <span class="stat-label">Eventos aprovados</span>
                </div>
                <div class="stat">
                  <span class="stat-value">8</span>
                  <span class="stat-label">Solicitações</span>
                </div>
              </div>
            </div>
          </div>

          <div class="perfil-card">
            <div class="card-title-bar">
              <h3>Dados Pessoais</h3>
              <button class="btn-icon" @click="editandoDados = !editandoDados">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path d="M17 3l4 4-7 7H10v-4l7-7z" />
                  <path d="M4 20h16" />
                </svg>
                {{ editandoDados ? 'Cancelar' : 'Editar' }}
              </button>
            </div>

            <div class="form-row">
              <div class="form-field">
                <label>Nome completo</label>
                <input
                  type="text"
                  v-model="admin.nome"
                  :disabled="!editandoDados"
                  class="input-field"
                />
              </div>
              <div class="form-field">
                <label>E-mail</label>
                <input type="email" v-model="admin.email" disabled class="input-field" />
              </div>
            </div>

            <div class="form-row">
              <div class="form-field">
                <label>Telefone</label>
                <input
                  type="tel"
                  v-model="admin.telefone"
                  :disabled="!editandoDados"
                  class="input-field"
                />
              </div>
              <div class="form-field">
                <label>Cargo</label>
                <input type="text" v-model="admin.cargo" disabled class="input-field" />
              </div>
            </div>

            <div v-if="editandoDados" class="form-actions">
              <button class="btn-salvar" @click="salvarDados">Salvar alterações</button>
            </div>
          </div>

          <div class="perfil-card">
            <h3>Alterar senha</h3>

            <div class="form-row">
              <div class="form-field">
                <label>Senha atual</label>
                <input type="password" v-model="senhaAtual" class="input-field" />
              </div>
            </div>

            <div class="form-row">
              <div class="form-field">
                <label>Nova senha</label>
                <input type="password" v-model="novaSenha" class="input-field" />
              </div>
              <div class="form-field">
                <label>Confirmar nova senha</label>
                <input type="password" v-model="confirmarSenha" class="input-field" />
              </div>
            </div>

            <button class="btn-senha" @click="alterarSenha">
              <svg
                width="16"
                height="16"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <rect x="3" y="11" width="18" height="11" rx="2" ry="2" />
                <path d="M7 11V7a5 5 0 0 1 10 0v4" />
              </svg>
              Atualizar senha
            </button>
          </div>
        </div>
      </main>
    </div>

    <button class="menu-toggle" @click="isSidebarOpen = !isSidebarOpen">
      <svg
        width="24"
        height="24"
        viewBox="0 0 24 24"
        fill="none"
        stroke="currentColor"
        stroke-width="2.5"
      >
        <line x1="3" y1="12" x2="21" y2="12" />
        <line x1="3" y1="6" x2="21" y2="6" />
        <line x1="3" y1="18" x2="21" y2="18" />
      </svg>
    </button>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'
import { useUserStore } from '../stores/userStore'

const isSidebarOpen = ref(false)
const editandoDados = ref(false)
const userStore = useUserStore()

const admin = ref({
  nome: userStore.user.name || 'Administrador',
  email: userStore.user.email || '',
  telefone: '',
  cargo: 'Não informado',
})

const senhaAtual = ref('')
const novaSenha = ref('')
const confirmarSenha = ref('')

function salvarDados() {
  editandoDados.value = false
  alert('Dados atualizados com sucesso!')
}

function alterarSenha() {
  if (!senhaAtual.value) {
    alert('Digite sua senha atual')
    return
  }
  if (novaSenha.value.length < 6) {
    alert('A nova senha deve ter no mínimo 6 caracteres')
    return
  }
  if (novaSenha.value !== confirmarSenha.value) {
    alert('As senhas não coincidem')
    return
  }

  alert('Senha alterada com sucesso!')
  senhaAtual.value = ''
  novaSenha.value = ''
  confirmarSenha.value = ''
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
  max-width: 1000px;
  padding: 32px 40px;
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 28px;
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

.perfil-wrapper {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.perfil-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 28px 32px;
}

.foto-card {
  display: flex;
  gap: 32px;
  align-items: center;
}

.foto-area {
  text-align: center;
}

.avatar-circle {
  width: 100px;
  height: 100px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 12px;
}

.avatar-iniciais {
  font-size: 2.5rem;
  font-weight: 800;
  color: #0a0e17;
}

.btn-upload {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 6px 12px;
  border-radius: 8px;
  color: #c9a84c;
  font-size: 0.7rem;
  font-weight: 600;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s;
}

.btn-upload:hover {
  background: rgba(201, 168, 76, 0.1);
  border-color: #c9a84c;
}

.info-area h2 {
  font-size: 1.5rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 6px;
}

.role-badge {
  display: inline-block;
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
  border: 1px solid rgba(201, 168, 76, 0.3);
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 600;
  margin-bottom: 16px;
}

.info-stats {
  display: flex;
  gap: 24px;
  margin-top: 8px;
}

.stat {
  text-align: center;
}

.stat-value {
  display: block;
  font-size: 1.2rem;
  font-weight: 800;
  color: #c9a84c;
}

.stat-label {
  font-size: 0.7rem;
  color: #6b7280;
}

.card-title-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  padding-bottom: 12px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.card-title-bar h3 {
  font-size: 0.9rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin: 0;
}

.perfil-card h3 {
  font-size: 0.9rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin-bottom: 24px;
  padding-bottom: 12px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.btn-icon {
  background: transparent;
  border: none;
  color: #8e9aaf;
  font-size: 0.75rem;
  font-weight: 600;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: color 0.2s;
}

.btn-icon:hover {
  color: #c9a84c;
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
  font-size: 0.7rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.input-field {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 12px 16px;
  color: white;
  font-size: 0.9rem;
  outline: none;
  transition: all 0.3s ease;
}

.input-field:focus {
  border-color: #c9a84c;
  box-shadow: 0 0 0 4px rgba(201, 168, 76, 0.1);
}

.input-field:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
}

.btn-salvar {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 12px 28px;
  border-radius: 12px;
  color: #0a0e17;
  font-weight: 700;
  font-size: 0.85rem;
  cursor: pointer;
  transition: all 0.3s ease;
}

.btn-salvar:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(201, 168, 76, 0.3);
}

.btn-senha {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 12px 24px;
  border-radius: 12px;
  color: #c9a84c;
  font-weight: 600;
  font-size: 0.85rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s ease;
  margin-top: 8px;
}

.btn-senha:hover {
  background: rgba(201, 168, 76, 0.1);
  border-color: #c9a84c;
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

  .foto-card {
    flex-direction: column;
    text-align: center;
  }

  .info-stats {
    justify-content: center;
  }

  .form-row {
    grid-template-columns: 1fr;
    gap: 16px;
  }
}

@media (max-width: 600px) {
  .perfil-card {
    padding: 20px;
  }

  .avatar-circle {
    width: 80px;
    height: 80px;
  }

  .avatar-iniciais {
    font-size: 2rem;
  }

  .info-area h2 {
    font-size: 1.2rem;
  }
}
</style>
