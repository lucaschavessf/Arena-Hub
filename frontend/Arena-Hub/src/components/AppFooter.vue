<template>
  <footer class="footer">
    <div class="footer-inner">
      <!-- Logo e descrição -->
      <div class="footer__brand">
        <div class="footer__logo">
          <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M2 9V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v4"/>
            <path d="M2 15v4a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-4"/>
            <path d="M2 9a3 3 0 0 1 0 6"/>
            <path d="M22 9a3 3 0 0 0 0 6"/>
          </svg>
          <span>Arena<span class="highlight">Hub</span></span>
        </div>
        <p class="footer__description">
          A maior plataforma de eventos da Arena Pernambuco
        </p>
        
        <!-- Badge de status -->
        <div class="footer__status">
          <span class="status-dot"></span>
          <span>Online 24h</span>
        </div>
      </div>

      <!-- Links essenciais -->
      <div class="footer__links">
        <router-link to="/eventos" class="footer-link">
          <span>Eventos</span>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
        </router-link>
        <router-link to="/meus-ingressos" class="footer-link">
          <span>Meus Ingressos</span>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
        </router-link>
        <router-link to="/termos" class="footer-link">
          <span>Termos de Uso</span>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
        </router-link>
        <router-link to="/privacidade" class="footer-link">
          <span>Privacidade</span>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
        </router-link>
      </div>

      <!-- Redes sociais minimalistas -->
      <div class="footer__social">
        <a href="#" aria-label="Instagram" class="social-link">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="2" y="2" width="20" height="20" rx="5"/>
            <circle cx="12" cy="12" r="4"/>
          </svg>
        </a>
        <a href="#" aria-label="Facebook" class="social-link">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"/>
          </svg>
        </a>
        <a href="#" aria-label="Twitter" class="social-link">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z"/>
          </svg>
        </a>
      </div>

      <!-- Copyright com gradiente -->
      <div class="footer__copyright">
        <span>© 2026 Arena Pernambuco</span>
        <span class="separator">•</span>
        <span class="gradient-text">Bilheteria Digital</span>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  width: 100%;
  background: linear-gradient(180deg, #0a0e17 0%, #070a10 100%);
  border-top: 1px solid rgba(201, 168, 76, 0.1);
  padding: 48px 0 28px;
  position: relative;
  overflow: hidden;
}

/* Efeito de glow no topo */
.footer::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 1px;
  background: linear-gradient(90deg, transparent, #c9a84c, transparent);
  opacity: 0.3;
}

.footer-inner {
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 24px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 28px;
}

/* Brand */
.footer__brand {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  text-align: center;
}

.footer__logo {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 1.4rem;
  font-weight: 700;
  color: #fff;
}

.footer__logo svg {
  color: #c9a84c;
}

.footer__logo .highlight {
  color: #c9a84c;
  font-size: 0.85rem;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  margin-left: 2px;
}

.footer__description {
  color: #8e9aaf;
  font-size: 0.85rem;
  margin: 0;
}

.footer__status {
  display: flex;
  align-items: center;
  gap: 6px;
  color: #4cc98e;
  font-size: 0.7rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 1px;
  background: rgba(76, 201, 142, 0.08);
  padding: 4px 12px;
  border-radius: 20px;
  border: 1px solid rgba(76, 201, 142, 0.15);
}

.status-dot {
  width: 6px;
  height: 6px;
  background: #4cc98e;
  border-radius: 50%;
  animation: pulse 2s infinite;
  box-shadow: 0 0 8px #4cc98e;
}

@keyframes pulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.5; }
}

/* Links modernos */
.footer__links {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  gap: 8px;
}

.footer-link {
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 8px 16px;
  color: #8e9aaf;
  font-size: 0.85rem;
  font-weight: 500;
  text-decoration: none;
  border-radius: 30px;
  background: rgba(255, 255, 255, 0.02);
  border: 1px solid rgba(255, 255, 255, 0.03);
  transition: all 0.25s ease;
}

.footer-link svg {
  opacity: 0;
  transform: translateX(-5px);
  transition: all 0.25s ease;
}

.footer-link:hover {
  color: #c9a84c;
  background: rgba(201, 168, 76, 0.06);
  border-color: rgba(201, 168, 76, 0.15);
  transform: translateY(-1px);
}

.footer-link:hover svg {
  opacity: 1;
  transform: translateX(0);
}

/* Redes sociais */
.footer__social {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
}

.social-link {
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #8e9aaf;
  background: rgba(255, 255, 255, 0.02);
  border: 1px solid rgba(255, 255, 255, 0.04);
  border-radius: 12px;
  transition: all 0.25s ease;
  text-decoration: none;
}

.social-link:hover {
  color: #0a0e17;
  background: #c9a84c;
  border-color: #c9a84c;
  transform: translateY(-2px);
  box-shadow: 0 8px 16px rgba(201, 168, 76, 0.15);
}

/* Copyright */
.footer__copyright {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  gap: 6px;
  font-size: 0.75rem;
  color: #6b7280;
  padding-top: 12px;
  border-top: 1px solid rgba(255, 255, 255, 0.03);
  width: 100%;
}

.separator {
  opacity: 0.4;
}

.gradient-text {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  font-weight: 600;
}

/* Responsivo */
@media (min-width: 768px) {
  .footer-inner {
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  
  .footer__brand {
    align-items: flex-start;
    text-align: left;
  }
  
  .footer__copyright {
    width: auto;
    padding-top: 0;
    border-top: none;
  }
}

@media (max-width: 767px) {
  .footer {
    padding: 40px 0 24px;
  }
  
  .footer-link {
    padding: 6px 12px;
    font-size: 0.8rem;
  }
}
</style>