<template>
  <div id="app">
    <router-view v-slot="{ Component }">
      <transition name="page-fade" mode="out-in">
        <component :is="Component" />
      </transition>
    </router-view>
  </div>
</template>

<script setup lang="ts">
/**
 * Aqui você pode centralizar lógicas globais, 
 * como verificar se o usuário está logado ao carregar o app.
 */
</script>

<style>
/* 1. FONTES PREMIUM (Importação Centralizada) */
@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&display=swap');

/* 2. RESET MODERNO */
*, *::before, *::after { 
  box-sizing: border-box; 
  margin: 0; 
  padding: 0; 
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

:root {
  /* --- DESIGN SYSTEM ARENA HUB --- */
  
  /* Cores Dark Mode */
  --navy: #0a0e17;           /* Fundo Principal */
  --navy-mid: #121826;       /* Cards e Sidebar */
  --navy-light: #1c2433;     /* Inputs e Hovers */
  
  --gold: #c9a84c;
  --gold-light: #dfbc5e;
  --gold-dark: #a07830;
  --gold-glow: rgba(201, 168, 76, 0.15);
  
  --white: #ffffff;
  --muted: #8e9aaf;          /* Textos secundários */
  --border: rgba(255, 255, 255, 0.08);
  
  --red: #ff5e5e;
  --green: #4cc98e;
  
  /* Tipografia */
  --font-display: 'Bebas Neue', sans-serif;
  --font-body: 'Plus Jakarta Sans', sans-serif;
  
  /* Layout Global (Sincronização PC) */
  --container-max: 1200px;   /* LIMITE MESTRE PARA PC */
  --radius: 12px;
  --radius-lg: 20px;
  --shadow: 0 20px 40px rgba(0, 0, 0, 0.4);
  --transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* 3. ESTILO BASE */
html { 
  scroll-behavior: smooth; 
  font-size: 16px; 
  background: var(--navy); /* Evita flash branco ao carregar */
}

body {
  font-family: var(--font-body);
  background: var(--navy);
  color: var(--white);
  min-height: 100vh;
  line-height: 1.6;
  overflow-x: hidden;
}

#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* 4. SCROLLBAR PREMIUM (PC) */
::-webkit-scrollbar { width: 10px; }
::-webkit-scrollbar-track { background: var(--navy); }
::-webkit-scrollbar-thumb { 
  background: var(--navy-light); 
  border-radius: 10px; 
  border: 3px solid var(--navy);
}
::-webkit-scrollbar-thumb:hover { background: var(--gold); }

/* 5. TIPOGRAFIA GLOBAL */
h1, h2, h3, h4, .text-display { 
  font-family: var(--font-display); 
  letter-spacing: 0.05em; 
  text-transform: uppercase;
}

p { margin-bottom: 1rem; }

/* 6. COMPONENTES GLOBAIS REUTILIZÁVEIS */
button { 
  cursor: pointer; 
  border: none; 
  font-family: var(--font-body); 
  transition: var(--transition);
  border-radius: var(--radius);
}

/* Botão Primário (Usado em todo o site) */
.btn-primary {
  background: var(--gold);
  color: var(--navy);
  font-weight: 800;
  padding: 14px 32px;
  text-transform: uppercase;
  font-size: 0.85rem;
  letter-spacing: 1px;
}
.btn-primary:hover { 
  background: var(--gold-light); 
  transform: translateY(-3px);
  box-shadow: 0 10px 25px var(--gold-glow);
}
.btn-primary:disabled {
  opacity: 0.4;
  cursor: not-allowed;
  transform: none !important;
}

/* 7. ANIMAÇÃO DE TRANSIÇÃO DE PÁGINA (Fade suave) */
.page-fade-enter-active,
.page-fade-leave-active {
  transition: opacity 0.3s ease, transform 0.3s ease;
}

.page-fade-enter-from {
  opacity: 0;
  transform: translateY(8px);
}

.page-fade-leave-to {
  opacity: 0;
  transform: translateY(-8px);
}

/* 8. FORMULÁRIOS GLOBAIS */
input, textarea, select {
  background: var(--navy-light);
  border: 1.5px solid var(--border);
  border-radius: var(--radius);
  color: var(--white);
  padding: 12px 16px;
  font-size: 1rem;
}
input:focus {
  outline: none;
  border-color: var(--gold);
  background: #242d3d;
}

/* Garante que imagens não quebrem o layout */
img { max-width: 100%; height: auto; display: block; }
</style>