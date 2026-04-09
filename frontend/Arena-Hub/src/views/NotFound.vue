<template>
  <div class="page">
    <AppNavbar />

    <main class="main-content">
      <section class="notfound-section">
        <!-- Partículas decorativas de fundo -->
        <div class="particles">
          <div class="particle" v-for="i in 20" :key="i" :style="getParticleStyle(i)"></div>
        </div>

        <div class="container">
          <div class="notfound-content">
            
            <!-- Código 404 com animação -->
            <div class="code-wrapper">
              <h1 class="code">
                <span class="digit">4</span>
                <span class="digit">0</span>
                <span class="digit">4</span>
              </h1>
              <div class="code-glitch"></div>
            </div>

            <!-- Ilustração decorativa -->
            <div class="illustration">
              <svg width="200" height="120" viewBox="0 0 200 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                <!-- Lua/Planeta -->
                <circle cx="100" cy="60" r="30" fill="none" stroke="#c9a84c" stroke-width="1.5" opacity="0.3"/>
                <circle cx="100" cy="60" r="15" fill="#c9a84c" opacity="0.1"/>
                
                <!-- Estrelas -->
                <circle cx="30" cy="25" r="2" fill="#c9a84c" opacity="0.6"/>
                <circle cx="170" cy="35" r="1.5" fill="#c9a84c" opacity="0.4"/>
                <circle cx="50" cy="80" r="1" fill="#c9a84c" opacity="0.5"/>
                <circle cx="150" cy="75" r="2" fill="#c9a84c" opacity="0.3"/>
                <circle cx="180" cy="20" r="1" fill="#c9a84c" opacity="0.6"/>
                <circle cx="20" cy="60" r="1.5" fill="#c9a84c" opacity="0.4"/>
                
                <!-- Linhas de conexão -->
                <line x1="30" y1="25" x2="50" y2="80" stroke="#c9a84c" stroke-width="0.5" opacity="0.15"/>
                <line x1="170" y1="35" x2="150" y2="75" stroke="#c9a84c" stroke-width="0.5" opacity="0.15"/>
                <line x1="100" y1="60" x2="30" y2="25" stroke="#c9a84c" stroke-width="0.5" opacity="0.1"/>
                <line x1="100" y1="60" x2="170" y2="35" stroke="#c9a84c" stroke-width="0.5" opacity="0.1"/>
              </svg>
            </div>

            <h2 class="title">
              Ops! Página não encontrada
            </h2>

            <p class="subtitle">
              Parece que você se aventurou em território desconhecido. 
              A página que você está procurando não existe ou foi movida.
            </p>

            <!-- Sugestões -->
            <div class="suggestions">
              <div class="suggestion-item">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/>
                </svg>
                <span>Verifique a URL digitada</span>
              </div>
              <div class="suggestion-item">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M2 12h20M12 2v20"/>
                </svg>
                <span>Use a busca para encontrar eventos</span>
              </div>
            </div>

            <!-- Botão único -->
            <div class="actions">
              <router-link to="/" class="btn-primary">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                  <path d="M3 12h18M3 12l6-6M3 12l6 6M21 12h-4"/>
                </svg>
                <span>Voltar para Home</span>
              </router-link>
            </div>

            <!-- Links rápidos -->
            <div class="quick-links">
              <span>Ou acesse:</span>
              <div class="links-row">
                <router-link to="/eventos">Eventos</router-link>
                <span class="separator">•</span>
                <router-link to="/meus-ingressos">Meus Ingressos</router-link>
                <span class="separator">•</span>
                <a href="#">Ajuda</a>
              </div>
            </div>

          </div>
        </div>
      </section>
    </main>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()

// Gerar posições aleatórias para partículas
const getParticleStyle = (index: number) => {
  const size = Math.random() * 4 + 2
  const left = Math.random() * 100
  const top = Math.random() * 100
  const delay = Math.random() * 5
  const duration = Math.random() * 10 + 10
  
  return {
    width: `${size}px`,
    height: `${size}px`,
    left: `${left}%`,
    top: `${top}%`,
    animationDelay: `${delay}s`,
    animationDuration: `${duration}s`
  }
}
</script>

<style scoped>
.page { 
  background: #0a0e17; 
  color: white; 
  min-height: 100vh; 
  display: flex;
  flex-direction: column;
  position: relative;
}

.main-content {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.container { 
  max-width: 1200px; 
  margin: 0 auto; 
  padding: 0 24px; 
  width: 100%;
}

/* Partículas animadas */
.particles {
  position: absolute;
  inset: 0;
  overflow: hidden;
  pointer-events: none;
}

.particle {
  position: absolute;
  background: #c9a84c;
  border-radius: 50%;
  opacity: 0.15;
  animation: floatParticle linear infinite;
}

@keyframes floatParticle {
  0% {
    transform: translateY(0) rotate(0deg);
  }
  25% {
    transform: translateY(-20px) rotate(90deg);
  }
  50% {
    transform: translateY(-40px) rotate(180deg);
  }
  75% {
    transform: translateY(-20px) rotate(270deg);
  }
  100% {
    transform: translateY(0) rotate(360deg);
  }
}

/* 404 Section */
.notfound-section {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 80px 0;
  position: relative;
  z-index: 2;
}

.notfound-content {
  max-width: 650px;
  margin: 0 auto;
  text-align: center;
  width: 100%;
  animation: fadeInUp 0.8s ease-out;
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* Código 404 Animado */
.code-wrapper {
  position: relative;
  margin-bottom: 24px;
}

.code {
  font-size: clamp(8rem, 18vw, 12rem);
  font-weight: 900;
  margin: 0;
  line-height: 1;
  display: flex;
  justify-content: center;
  gap: 8px;
}

.digit {
  display: inline-block;
  background: linear-gradient(135deg, #c9a84c 0%, #d4af37 50%, #c9a84c 100%);
  background-size: 200% 200%;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  animation: gradientShift 3s ease infinite, float 3s ease-in-out infinite;
  text-shadow: 0 0 30px rgba(201, 168, 76, 0.3);
}

.digit:nth-child(1) { animation-delay: 0s; }
.digit:nth-child(2) { animation-delay: 0.2s; }
.digit:nth-child(3) { animation-delay: 0.4s; }

@keyframes gradientShift {
  0%, 100% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
}

@keyframes float {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-10px); }
}

.code-glitch {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(90deg, 
    transparent 0%, 
    rgba(201, 168, 76, 0.1) 45%, 
    rgba(201, 168, 76, 0.1) 55%, 
    transparent 100%
  );
  animation: glitchSweep 4s ease-in-out infinite;
  pointer-events: none;
}

@keyframes glitchSweep {
  0%, 100% { transform: translateX(-100%); }
  10%, 30% { transform: translateX(100%); }
  40%, 90% { transform: translateX(-100%); }
}

/* Ilustração */
.illustration {
  margin-bottom: 32px;
  animation: fadeIn 1s ease-out 0.3s both;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

/* Título */
.title {
  font-size: clamp(1.8rem, 4vw, 2.2rem);
  font-weight: 800;
  margin-bottom: 16px;
  color: #fff;
  animation: slideIn 0.6s ease-out 0.2s both;
}

@keyframes slideIn {
  from {
    opacity: 0;
    transform: translateX(-20px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

/* Subtítulo */
.subtitle {
  color: #8e9aaf;
  margin-bottom: 32px;
  font-size: 1rem;
  line-height: 1.7;
  max-width: 480px;
  margin-left: auto;
  margin-right: auto;
  animation: slideIn 0.6s ease-out 0.3s both;
}

/* Sugestões */
.suggestions {
  display: flex;
  flex-direction: column;
  gap: 12px;
  max-width: 320px;
  margin: 0 auto 32px;
  animation: slideIn 0.6s ease-out 0.4s both;
}

.suggestion-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  background: rgba(28, 36, 51, 0.5);
  border-radius: 12px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  color: #b0b8c5;
  font-size: 0.9rem;
}

.suggestion-item svg {
  color: #c9a84c;
  opacity: 0.7;
  flex-shrink: 0;
}

/* Botão */
.actions {
  margin-bottom: 32px;
  animation: slideIn 0.6s ease-out 0.5s both;
}

.btn-primary {
  padding: 16px 36px;
  border-radius: 14px;
  font-weight: 800;
  font-size: 1rem;
  text-decoration: none;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  text-transform: uppercase;
  letter-spacing: 1px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border: none;
  box-shadow: 0 8px 20px rgba(201, 168, 76, 0.25);
  position: relative;
  overflow: hidden;
}

.btn-primary::before {
  content: '';
  position: absolute;
  inset: 0;
  background: linear-gradient(135deg, #d4af37, #c9a84c);
  opacity: 0;
  transition: opacity 0.3s ease;
}

.btn-primary:hover {
  transform: translateY(-3px);
  box-shadow: 0 15px 30px rgba(201, 168, 76, 0.35);
}

.btn-primary:hover::before {
  opacity: 1;
}

.btn-primary svg,
.btn-primary span {
  position: relative;
  z-index: 1;
}

.btn-primary svg {
  transition: transform 0.3s ease;
}

.btn-primary:hover svg {
  transform: translateX(-3px);
}

/* Links rápidos */
.quick-links {
  animation: fadeIn 0.8s ease-out 0.6s both;
  padding-top: 24px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.quick-links span {
  color: #6b7280;
  font-size: 0.85rem;
  display: block;
  margin-bottom: 12px;
}

.links-row {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  flex-wrap: wrap;
}

.links-row a {
  color: #b0b8c5;
  text-decoration: none;
  font-size: 0.9rem;
  font-weight: 500;
  transition: color 0.2s ease;
  position: relative;
}

.links-row a:hover {
  color: #c9a84c;
}

.links-row a::after {
  content: '';
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 100%;
  height: 1px;
  background: #c9a84c;
  transform: scaleX(0);
  transition: transform 0.3s ease;
  transform-origin: right;
}

.links-row a:hover::after {
  transform: scaleX(1);
  transform-origin: left;
}

.separator {
  color: #4a5568 !important;
  margin: 0 !important;
}

/* Responsivo */
@media (max-width: 600px) {
  .notfound-section {
    padding: 50px 0;
  }
  
  .code {
    font-size: 7rem;
    gap: 4px;
  }
  
  .title {
    font-size: 1.6rem;
  }
  
  .subtitle {
    font-size: 0.9rem;
    padding: 0 16px;
  }
  
  .suggestions {
    padding: 0 16px;
  }
  
  .btn-primary {
    padding: 14px 28px;
    font-size: 0.9rem;
  }
  
  .quick-links {
    padding-left: 16px;
    padding-right: 16px;
  }
  
  .illustration svg {
    width: 160px;
    height: 100px;
  }
}

@media (max-width: 400px) {
  .code {
    font-size: 6rem;
  }
  
  .links-row {
    flex-direction: column;
    gap: 8px;
  }
  
  .separator {
    display: none;
  }
}

/* Redução de movimento */
@media (prefers-reduced-motion: reduce) {
  .digit,
  .code-glitch,
  .particle,
  .notfound-content,
  .title,
  .subtitle,
  .suggestions,
  .actions,
  .quick-links {
    animation: none;
  }
  
  .digit {
    text-shadow: none;
  }
}
</style>