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
        <a href="#" class="nav-link">Produtor de eventos</a>
        <a href="#" class="nav-link">Central de ajuda</a>
        <router-link to="/meus-ingressos" class="nav-link">Meus ingressos</router-link>
      </div>

      <div class="navbar__actions">
        <button class="icon-btn" title="Perfil">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="8" r="4"/><path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/>
          </svg>
        </button>
        <button class="icon-btn" title="Instagram">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="2" y="2" width="20" height="20" rx="5"/><circle cx="12" cy="12" r="4"/>
          </svg>
        </button>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
defineProps({ isAdmin: { type: Boolean, default: false } })
</script>

<style scoped>
.navbar {
  width: 100%; /* Ocupa todo o topo do main-wrapper */
}
.navbar__inner {
  max-width: 1200px; /* Mesmo valor do Dashboard */
  margin: 0 auto;    /* Centraliza o conteúdo da barra */
}
.navbar__logo { display: flex; align-items: center; gap: 10px; }
.logo-icon {
  width: 34px; height: 34px;
  background: var(--gold);
  border-radius: 8px;
  display: flex; align-items: center; justify-content: center;
  color: var(--navy);
}
.logo-text { display: flex; flex-direction: column; line-height: 1; }
.logo-arena { font-family: var(--font-display); font-size: 1.1rem; color: #fff; }
.logo-hub { font-size: 0.7rem; color: var(--gold); font-weight: 700; text-transform: uppercase; }

.navbar__links { display: flex; gap: 32px; }
.nav-link { font-size: 0.85rem; color: var(--muted); font-weight: 500; transition: 0.2s; }
.nav-link:hover { color: var(--gold); }

.navbar__actions { display: flex; gap: 8px; }
.icon-btn {
  width: 36px; height: 36px; background: transparent; color: var(--muted);
  border-radius: 50%; transition: 0.2s;
}
.icon-btn:hover { background: rgba(255,255,255,0.05); color: #fff; }
</style>