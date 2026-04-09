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
  --navy: #0a0e17;
  --navy-mid: #121826;
  --navy-light: #1c2433;
  
  --gold: #c9a84c;
  --gold-light: #dfbc5e;
  --gold-dark: #a07830;
  --gold-glow: rgba(201, 168, 76, 0.15);
  
  --white: #ffffff;
  --muted: #8e9aaf;
  --border: rgba(255, 255, 255, 0.08);
  
  --red: #ff5e5e;
  --green: #4cc98e;
  
  /* Tipografia */
  --font-display: 'Bebas Neue', sans-serif;
  --font-body: 'Plus Jakarta Sans', sans-serif;
  
  /* Layout Global */
  --container-max: 1200px;
  --radius-sm: 8px;
  --radius: 12px;
  --radius-lg: 20px;
  --shadow: 0 20px 40px rgba(0, 0, 0, 0.4);
  --shadow-hover: 0 25px 50px -12px rgba(0, 0, 0, 0.5);
  --transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* 3. ESTILO BASE */
html { 
  scroll-behavior: smooth; 
  font-size: 16px; 
  background: var(--navy);
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

/* 4. SCROLLBAR PREMIUM */
::-webkit-scrollbar { 
  width: 10px; 
  height: 10px;
}

::-webkit-scrollbar-track { 
  background: var(--navy-mid); 
}

::-webkit-scrollbar-thumb { 
  background: linear-gradient(180deg, var(--navy-light) 0%, #2a3446 100%);
  border-radius: 10px; 
  border: 2px solid var(--navy);
}

::-webkit-scrollbar-thumb:hover { 
  background: linear-gradient(180deg, var(--gold-dark) 0%, var(--gold) 100%);
}

/* 5. TIPOGRAFIA GLOBAL */
h1, h2, h3, h4, .text-display { 
  font-family: var(--font-display); 
  letter-spacing: 0.05em; 
  text-transform: uppercase;
  font-weight: 600;
  line-height: 1.2;
}

h1 {
  font-size: clamp(2.5rem, 5vw, 4rem);
  background: linear-gradient(135deg, #fff 0%, var(--gold) 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

h2 {
  font-size: clamp(2rem, 4vw, 3rem);
  color: var(--white);
}

h3 {
  font-size: clamp(1.5rem, 3vw, 2rem);
  color: var(--white);
}

p { 
  margin-bottom: 1rem; 
  color: var(--muted);
  font-weight: 400;
}

/* 6. COMPONENTES GLOBAIS REUTILIZÁVEIS */
button { 
  cursor: pointer; 
  border: none; 
  font-family: var(--font-body); 
  transition: var(--transition);
  border-radius: var(--radius);
  font-weight: 600;
  position: relative;
  overflow: hidden;
}

button:active {
  transform: scale(0.98);
}

/* Botão Primário */
.btn-primary {
  background: linear-gradient(135deg, var(--gold) 0%, var(--gold-light) 100%);
  color: var(--navy);
  font-weight: 800;
  padding: 14px 32px;
  text-transform: uppercase;
  font-size: 0.85rem;
  letter-spacing: 1.5px;
  border-radius: var(--radius);
  box-shadow: 0 4px 15px var(--gold-glow);
  position: relative;
  isolation: isolate;
}

.btn-primary::before {
  content: '';
  position: absolute;
  inset: 0;
  background: linear-gradient(135deg, var(--gold-light) 0%, var(--gold) 100%);
  opacity: 0;
  transition: opacity 0.3s ease;
  z-index: -1;
}

.btn-primary:hover { 
  transform: translateY(-3px);
  box-shadow: 0 10px 30px var(--gold-glow);
}

.btn-primary:hover::before {
  opacity: 1;
}

.btn-primary:disabled {
  opacity: 0.4;
  cursor: not-allowed;
  transform: none !important;
  box-shadow: none !important;
}

/* 7. ANIMAÇÃO DE TRANSIÇÃO DE PÁGINA */
.page-fade-enter-active,
.page-fade-leave-active {
  transition: opacity 0.4s cubic-bezier(0.4, 0, 0.2, 1), 
              transform 0.4s cubic-bezier(0.4, 0, 0.2, 1);
}

.page-fade-enter-from {
  opacity: 0;
  transform: translateY(12px);
}

.page-fade-leave-to {
  opacity: 0;
  transform: translateY(-12px);
}

/* 8. FORMULÁRIOS GLOBAIS */
input, 
textarea, 
select {
  background: var(--navy-light);
  border: 1.5px solid var(--border);
  border-radius: var(--radius);
  color: var(--white);
  padding: 12px 16px;
  font-size: 1rem;
  font-family: var(--font-body);
  transition: all 0.2s ease;
}

input:hover,
textarea:hover,
select:hover {
  border-color: rgba(201, 168, 76, 0.3);
  background: #202a3a;
}

input:focus,
textarea:focus,
select:focus {
  outline: none;
  border-color: var(--gold);
  background: #242d3d;
  box-shadow: 0 0 0 3px rgba(201, 168, 76, 0.1);
}

input::placeholder,
textarea::placeholder {
  color: var(--muted);
  opacity: 0.6;
  font-weight: 300;
}

/* 9. LINKS GLOBAIS */
a {
  color: inherit;
  text-decoration: none;
  transition: color 0.2s ease;
}

a:hover {
  color: var(--gold);
}

/* 10. SELEÇÃO DE TEXTO */
::selection {
  background: var(--gold);
  color: var(--navy);
}

::-moz-selection {
  background: var(--gold);
  color: var(--navy);
}

/* 11. GARANTE QUE IMAGENS NÃO QUEBREM O LAYOUT */
img { 
  max-width: 100%; 
  height: auto; 
  display: block; 
}

img[loading="lazy"] {
  background: linear-gradient(90deg, 
    var(--navy-mid) 0%, 
    var(--navy-light) 50%, 
    var(--navy-mid) 100%
  );
  background-size: 200% 100%;
  animation: shimmer 1.5s infinite;
}

@keyframes shimmer {
  0% { background-position: 200% 0; }
  100% { background-position: -200% 0; }
}

/* 12. RESPONSIVIDADE */
@media (max-width: 768px) {
  html {
    font-size: 14px;
  }
  
  .btn-primary {
    padding: 12px 24px;
    font-size: 0.8rem;
  }
}

@media (max-width: 480px) {
  html {
    font-size: 13px;
  }
}

/* 13. REDUÇÃO DE MOVIMENTO */
@media (prefers-reduced-motion: reduce) {
  *,
  *::before,
  *::after {
    animation-duration: 0.01ms !important;
    animation-iteration-count: 1 !important;
    transition-duration: 0.01ms !important;
    scroll-behavior: auto !important;
  }
  
  html {
    scroll-behavior: auto;
  }
}
</style>