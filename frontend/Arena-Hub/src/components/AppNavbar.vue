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

      <!-- Menu desktop -->
      <div class="navbar__links" v-if="!isAdmin">
        <a href="#" class="nav-link">Produtor de eventos</a>
        <a href="#" class="nav-link">Central de ajuda</a>
        <router-link to="/meus-ingressos" class="nav-link">Meus ingressos</router-link>
      </div>

      <div class="navbar__actions">
        <button class="icon-btn" title="Perfil" aria-label="Perfil">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="8" r="4"/><path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
          </svg>
        </button>
        <button class="icon-btn" title="Instagram" aria-label="Instagram">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="2" y="2" width="20" height="20" rx="5"/><circle cx="12" cy="12" r="4"/>
          </svg>
        </button>
        
        <!-- Botão menu mobile -->
        <button 
          class="icon-btn mobile-menu-btn" 
          @click="mobileMenuOpen = !mobileMenuOpen"
          aria-label="Menu"
          aria-expanded="mobileMenuOpen"
          v-if="!isAdmin"
        >
          <svg v-if="!mobileMenuOpen" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="3" y1="12" x2="21" y2="12"/><line x1="3" y1="6" x2="21" y2="6"/><line x1="3" y1="18" x2="21" y2="18"/>
          </svg>
          <svg v-else width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
          </svg>
        </button>
      </div>
    </div>

    <!-- Menu mobile dropdown -->
    <Transition name="slide-fade">
      <div class="navbar__mobile-menu" v-if="mobileMenuOpen && !isAdmin">
        <a href="#" class="mobile-link" @click="mobileMenuOpen = false">Produtor de eventos</a>
        <a href="#" class="mobile-link" @click="mobileMenuOpen = false">Central de ajuda</a>
        <router-link to="/meus-ingressos" class="mobile-link" @click="mobileMenuOpen = false">Meus ingressos</router-link>
      </div>
    </Transition>
  </nav>
</template>

<script setup lang="ts">
import { ref } from 'vue'

defineProps({ 
  isAdmin: { 
    type: Boolean, 
    default: false 
  } 
})

const mobileMenuOpen = ref(false)
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

/* Desktop menu */
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

/* Mobile menu button - visível apenas em telas pequenas */
.mobile-menu-btn {
  display: flex;
}

@media (min-width: 768px) {
  .mobile-menu-btn {
    display: none;
  }
}

/* Mobile dropdown menu */
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
  gap: 12px;
  z-index: 100;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

@media (min-width: 768px) {
  .navbar__mobile-menu {
    display: none;
  }
}

.mobile-link {
  padding: 12px 16px;
  color: #fff;
  text-decoration: none;
  font-size: 1rem;
  font-weight: 500;
  border-radius: 8px;
  transition: background 0.2s;
}

.mobile-link:hover {
  background: rgba(255, 255, 255, 0.05);
}

.mobile-link:focus-visible {
  outline: 2px solid var(--gold, #c9a84c);
  outline-offset: 2px;
}

/* Transição para o menu mobile */
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

/* Admin mode */
.navbar--admin .navbar__inner {
  justify-content: space-between;
}

.navbar--admin .navbar__actions {
  margin-left: auto;
}

/* Variáveis de fallback */
:root {
  --navy: #0a0e1a;
  --navy-light: #131a2a;
  --gold: #c9a84c;
  --muted: #8a8f9e;
}
</style>