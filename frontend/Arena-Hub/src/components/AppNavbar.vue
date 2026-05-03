<template>
  <nav class="navbar" :class="{ 'navbar--admin': isAdmin }">
    <div class="navbar__inner">
      <router-link to="/" class="navbar__logo">
        <div class="logo-icon">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round">
            <path d="M2 9V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v4"></path>
            <path d="M2 15v4a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-4"></path>
            <path d="M2 9a3 3 0 0 1 0 6"></path>
            <path d="M22 9a3 3 0 0 0 0 6"></path>
          </svg>
        </div>
        <div class="logo-text">
          <span class="logo-arena">Arena</span>
          <span class="logo-hub">Hub</span>
        </div>
      </router-link>

      <div class="navbar__links" v-if="!isAdmin">
        <a href="#" class="nav-link" @click.prevent="$router.push('/solicitar-evento')">Produtor de eventos</a>
        <router-link to="/meus-ingressos" class="nav-link">Meus ingressos</router-link>
      </div>

      <div class="navbar__actions">
        <div class="profile-dropdown">
          <button 
            class="icon-btn profile-btn" 
            :class="{ 'profile-btn--active': dropdownOpen }"
            title="Perfil" 
            aria-label="Perfil" 
            @click="toggleDropdown"
          >
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="8" r="4"/>
              <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
            </svg>
          </button>

          <Transition name="dropdown-fade">
            <div class="dropdown-menu" v-if="dropdownOpen">
              <!-- Usuário logado -->
              <div v-if="userStore.isLoggedIn" class="dropdown-header">
                <div class="user-avatar">
                  <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
                    <circle cx="12" cy="8" r="4"/>
                    <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
                  </svg>
                </div>
                <div class="user-info">
                  <span class="user-name">{{ userStore.user.name }}</span>
                  <span class="user-points">
                    <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <circle cx="12" cy="12" r="10"/>
                      <path d="M12 6v6l4 2"/>
                    </svg>
                    {{ userStore.user.points }} pontos
                  </span>
                </div>
              </div>

              <!-- Visitante -->
              <div v-else class="dropdown-header">
                <div class="user-avatar">
                  <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
                    <circle cx="12" cy="8" r="4"/>
                    <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
                  </svg>
                </div>
                <div class="user-info">
                  <span class="user-name">Visitante</span>
                  <span class="user-email">faça login para acessar</span>
                </div>
              </div>
              
              <div class="dropdown-divider"></div>
              
              <!-- Loja (nova) -->
              <router-link to="/loja" class="dropdown-item" @click="closeDropdown">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="7" width="18" height="14" rx="2"/>
                  <path d="M8 7V5a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/>
                </svg>
                <span>Loja de Pontos</span>
              </router-link>
              
              <!-- Meus ingressos -->
              <router-link to="/meus-ingressos" class="dropdown-item" @click="closeDropdown">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/>
                  <polyline points="14 2 14 8 20 8"/>
                  <line x1="12" y1="18" x2="12" y2="12"/>
                  <line x1="9" y1="15" x2="15" y2="15"/>
                </svg>
                <span>Meus ingressos</span>
              </router-link>
              
              <!-- Área Admin (se for admin) -->
              <router-link to="/admin" class="dropdown-item" @click="closeDropdown">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="3" width="7" height="7"/>
                  <rect x="14" y="3" width="7" height="7"/>
                  <rect x="3" y="14" width="7" height="7"/>
                  <rect x="14" y="14" width="7" height="7"/>
                </svg>
                <span>Área Admin</span>
              </router-link>
              
              <!-- Sair (apenas se logado) -->
              <div v-if="userStore.isLoggedIn">
                <div class="dropdown-divider"></div>
                <button class="dropdown-item logout-item" @click="handleLogout">
                  <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/>
                    <polyline points="16 17 21 12 16 7"/>
                    <line x1="21" y1="12" x2="9" y2="12"/>
                  </svg>
                  <span>Sair</span>
                </button>
              </div>
              
              <!-- Entrar (apenas se não logado) -->
              <router-link v-else to="/login" class="dropdown-item" @click="closeDropdown">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4"/>
                  <polyline points="10 17 15 12 10 7"/>
                  <line x1="15" y1="12" x2="3" y2="12"/>
                </svg>
                <span>Entrar / Cadastrar</span>
              </router-link>
            </div>
          </Transition>
        </div>

        <button 
          class="icon-btn mobile-menu-btn" 
          @click="mobileMenuOpen = !mobileMenuOpen"
          aria-label="Menu"
          :aria-expanded="mobileMenuOpen"
          v-if="!isAdmin"
        >
          <svg v-if="!mobileMenuOpen" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="3" y1="12" x2="21" y2="12"/>
            <line x1="3" y1="6" x2="21" y2="6"/>
            <line x1="3" y1="18" x2="21" y2="18"/>
          </svg>
          <svg v-else width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="18" y1="6" x2="6" y2="18"/>
            <line x1="6" y1="6" x2="18" y2="18"/>
          </svg>
        </button>
      </div>
    </div>

    <Transition name="slide-fade">
      <div class="navbar__mobile-menu" v-if="mobileMenuOpen && !isAdmin">
        <a href="#" class="mobile-link" @click="mobileMenuOpen = false">Produtor de eventos</a>
        <router-link to="/meus-ingressos" class="mobile-link" @click="mobileMenuOpen = false">Meus ingressos</router-link>
        <div class="mobile-divider"></div>
        <router-link to="/login" class="mobile-link" @click="mobileMenuOpen = false">
          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4"/>
            <polyline points="10 17 15 12 10 7"/>
            <line x1="15" y1="12" x2="3" y2="12"/>
          </svg>
          Entrar / Cadastrar
        </router-link>
        <router-link to="/admin" class="mobile-link" @click="mobileMenuOpen = false">
          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="3" width="7" height="7"/>
            <rect x="14" y="3" width="7" height="7"/>
            <rect x="3" y="14" width="7" height="7"/>
            <rect x="14" y="14" width="7" height="7"/>
          </svg>
          Área Admin
        </router-link>
      </div>
    </Transition>
  </nav>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/userStore'

const router = useRouter()
const userStore = useUserStore()
const dropdownOpen = ref(false)

const toggleDropdown = () => {
  dropdownOpen.value = !dropdownOpen.value
}

const closeDropdown = () => {
  dropdownOpen.value = false
}

const handleLogout = () => {
  userStore.logout()
  closeDropdown()
  router.push('/')
}
</script>

<style scoped>
.navbar {
  width: 100%;
  position: relative;
  background: var(--navy, #0a0e1a);
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  padding: 0 1rem;
}

.navbar__inner {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 64px;
}

.navbar__logo { 
  display: flex; 
  align-items: center; 
  gap: 10px; 
  text-decoration: none;
  flex-shrink: 0;
}

.logo-icon {
  width: 34px; 
  height: 34px;
  background: var(--gold, #c9a84c);
  border-radius: 8px;
  display: flex; 
  align-items: center; 
  justify-content: center;
  color: var(--navy, #0a0e1a);
}

.logo-text { 
  display: flex; 
  flex-direction: column; 
  line-height: 1; 
}

.logo-arena { 
  font-family: var(--font-display, serif); 
  font-size: 1.1rem; 
  color: #fff; 
}

.logo-hub { 
  font-size: 0.7rem; 
  color: var(--gold, #c9a84c); 
  font-weight: 700; 
  text-transform: uppercase; 
  letter-spacing: 0.5px;
}

.navbar__links { 
  display: none;
  gap: 32px; 
  margin: 0 auto 0 48px;
}

@media (min-width: 768px) {
  .navbar {
    padding: 0 2rem;
  }
  
  .navbar__links { 
    display: flex; 
  }
}

.nav-link { 
  font-size: 0.85rem; 
  color: var(--muted, #8a8f9e); 
  font-weight: 500; 
  transition: color 0.2s; 
  text-decoration: none;
  white-space: nowrap;
}

.nav-link:hover { 
  color: var(--gold, #c9a84c); 
}

.nav-link:focus-visible {
  outline: 2px solid var(--gold, #c9a84c);
  outline-offset: 4px;
  border-radius: 2px;
}

.navbar__actions { 
  display: flex; 
  gap: 4px;
  flex-shrink: 0;
  position: relative;
}

.icon-btn {
  width: 36px; 
  height: 36px; 
  background: transparent; 
  color: var(--muted, #8a8f9e);
  border: none;
  border-radius: 50%; 
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: background 0.2s, color 0.2s;
}

.icon-btn:hover { 
  background: rgba(255, 255, 255, 0.05); 
  color: #fff; 
}

.icon-btn:focus-visible {
  outline: 2px solid var(--gold, #c9a84c);
  outline-offset: 2px;
}

.profile-btn {
  transition: all 0.2s;
}

.profile-btn--active {
  background: rgba(201, 168, 76, 0.15);
  color: var(--gold, #c9a84c);
}


.profile-dropdown {
  position: relative;
}

.dropdown-menu {
  position: absolute;
  top: calc(100% + 8px);
  right: 0;
  width: 260px;
  background: linear-gradient(135deg, #131a2a 0%, #0f1420 100%);
  border: 1px solid rgba(255, 255, 255, 0.08);
  border-radius: 12px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
  overflow: hidden;
  z-index: 1000;
}

.dropdown-header {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 16px;
  background: rgba(255, 255, 255, 0.02);
}

.user-avatar {
  width: 40px;
  height: 40px;
  background: rgba(201, 168, 76, 0.15);
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--gold, #c9a84c);
}

.user-info {
  flex: 1;
}

.user-name {
  display: block;
  font-size: 0.85rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 2px;
}

.user-email {
  display: block;
  font-size: 0.7rem;
  color: var(--muted, #8a8f9e);
}

.dropdown-divider {
  height: 1px;
  background: rgba(255, 255, 255, 0.05);
  margin: 4px 0;
}

.dropdown-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  color: #e0e0e0;
  text-decoration: none;
  font-size: 0.85rem;
  transition: all 0.2s;
  cursor: pointer;
}

.dropdown-item svg {
  color: var(--muted, #8a8f9e);
  transition: color 0.2s;
}

.dropdown-item:hover {
  background: rgba(201, 168, 76, 0.08);
  color: var(--gold, #c9a84c);
}

.dropdown-item:hover svg {
  color: var(--gold, #c9a84c);
}

.dropdown-fade-enter-active,
.dropdown-fade-leave-active {
  transition: all 0.2s ease;
}

.dropdown-fade-enter-from {
  transform: translateY(-10px);
  opacity: 0;
}

.dropdown-fade-leave-to {
  transform: translateY(-10px);
  opacity: 0;
}

.mobile-menu-btn {
  display: flex;
}

.user-points {
  display: flex;
  align-items: center;
  gap: 4px;
  font-size: 0.7rem;
  color: #c9a84c;
  margin-top: 4px;
}

.logout-item {
  color: #ef4444 !important;
}

.logout-item:hover {
  background: rgba(239, 68, 68, 0.1) !important;
  color: #ef4444 !important;
}

.logout-item:hover svg {
  color: #ef4444 !important;
}


@media (min-width: 768px) {
  .mobile-menu-btn {
    display: none;
  }
}

.navbar__mobile-menu {
  position: absolute;
  top: 64px;
  left: 0;
  right: 0;
  background: var(--navy-light, #131a2a);
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  padding: 1rem;
  display: flex;
  flex-direction: column;
  gap: 8px;
  z-index: 100;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

@media (min-width: 768px) {
  .navbar__mobile-menu {
    display: none;
  }
}

.mobile-link {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  color: #fff;
  text-decoration: none;
  font-size: 1rem;
  font-weight: 500;
  border-radius: 8px;
  transition: background 0.2s;
}

.mobile-link svg {
  width: 18px;
  height: 18px;
  color: var(--muted, #8a8f9e);
}

.mobile-link:hover {
  background: rgba(255, 255, 255, 0.05);
}

.mobile-divider {
  height: 1px;
  background: rgba(255, 255, 255, 0.05);
  margin: 8px 0;
}

.mobile-link:focus-visible {
  outline: 2px solid var(--gold, #c9a84c);
  outline-offset: 2px;
}

.slide-fade-enter-active {
  transition: all 0.2s ease;
}

.slide-fade-leave-active {
  transition: all 0.15s ease;
}

.slide-fade-enter-from {
  transform: translateY(-10px);
  opacity: 0;
}

.slide-fade-leave-to {
  transform: translateY(-10px);
  opacity: 0;
}

.navbar--admin .navbar__inner {
  justify-content: space-between;
}

.navbar--admin .navbar__actions {
  margin-left: auto;
}

:root {
  --navy: #0a0e1a;
  --navy-light: #131a2a;
  --gold: #c9a84c;
  --muted: #8a8f9e;
}
</style>