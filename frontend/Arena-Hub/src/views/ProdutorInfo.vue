<template>
  <div class="page">
    <AppNavbar />

    <section class="hero-section">
      <div class="hero-overlay"></div>
      <div class="hero-content">
        <h1 class="hero-title">
          Torne-se um<br /><span class="highlight">Produtor ArenaHub</span>
        </h1>
        <p class="hero-subtitle">
          Realize seus eventos na maior arena do Nordeste com todo suporte que você precisa
        </p>
        <div class="hero-buttons">
          <button class="btn-primary" @click="irParaCadastro">
            Quero ser produtor
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <line x1="5" y1="12" x2="19" y2="12" />
              <polyline points="12 5 19 12 12 19" />
            </svg>
          </button>
          <button class="btn-secondary" @click="scrollParaBeneficios">Conhecer benefícios</button>
        </div>
      </div>
    </section>

    <section class="stats-section">
      <div class="container">
        <div class="stats-grid">
          <div class="stat-item">
            <span class="stat-number">+50</span>
            <span class="stat-label">Eventos realizados</span>
          </div>
          <div class="stat-divider"></div>
          <div class="stat-item">
            <span class="stat-number">+500k</span>
            <span class="stat-label">Público total</span>
          </div>
          <div class="stat-divider"></div>
          <div class="stat-item">
            <span class="stat-number">+30</span>
            <span class="stat-label">Produtores parceiros</span>
          </div>
          <div class="stat-divider"></div>
          <div class="stat-item">
            <span class="stat-number">100%</span>
            <span class="stat-label">Suporte dedicado</span>
          </div>
        </div>
      </div>
    </section>

    <section id="beneficios" ref="beneficiosSection" class="beneficios-section">
      <div class="container">
        <div class="section-header">
          <h2>Por que ser um <span>Produtor ArenaHub?</span></h2>
          <p>Oferecemos as melhores condições e suporte completo para seu evento</p>
        </div>
        <div class="beneficios-grid">
          <div class="beneficio-card">
            <div class="beneficio-icon infra">
              <svg
                width="32"
                height="32"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="1.8"
              >
                <rect x="3" y="3" width="18" height="18" rx="2" />
                <line x1="3" y1="9" x2="21" y2="9" />
                <line x1="9" y1="21" x2="9" y2="9" />
              </svg>
            </div>
            <h3>Infraestrutura Completa</h3>
            <p>
              Espaço amplo com capacidade para até 30 mil pessoas, camarotes, estacionamento e áreas
              VIP.
            </p>
          </div>
          <div class="beneficio-card">
            <div class="beneficio-icon suporte">
              <svg
                width="32"
                height="32"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="1.8"
              >
                <circle cx="12" cy="12" r="10" />
                <path d="M12 8v4l3 3" />
              </svg>
            </div>
            <h3>Suporte Dedicado</h3>
            <p>Equipe especializada para ajudar na produção, logística e operação do seu evento.</p>
          </div>
          <div class="beneficio-card">
            <div class="beneficio-icon plataforma">
              <svg
                width="32"
                height="32"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="1.8"
              >
                <path d="M4 4h16v16H4z" />
                <path d="M9 9h6v6H9z" />
              </svg>
            </div>
            <h3>Plataforma Integrada</h3>
            <p>Acesse métricas, vendas e acompanhe seu evento em tempo real pelo dashboard.</p>
          </div>
          <div class="beneficio-card">
            <div class="beneficio-icon marketing">
              <svg
                width="32"
                height="32"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="1.8"
              >
                <polygon
                  points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                />
              </svg>
            </div>
            <h3>Marketing e Divulgação</h3>
            <p>Divulgamos seu evento em nossos canais oficiais para atingir milhões de pessoas.</p>
          </div>
          <div class="beneficio-card">
            <div class="beneficio-icon seguranca">
              <svg
                width="32"
                height="32"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="1.8"
              >
                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z" />
              </svg>
            </div>
            <h3>Segurança e Conformidade</h3>
            <p>Toda estrutura segue normas de segurança, com bombeiros, brigada e protocolos.</p>
          </div>
          <div class="beneficio-card">
            <div class="beneficio-icon flexibilidade">
              <svg
                width="32"
                height="32"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="1.8"
              >
                <path
                  d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"
                />
              </svg>
            </div>
            <h3>Modelos Flexíveis</h3>
            <p>Diferentes modelos de parceria e condições especiais para produtores frequentes.</p>
          </div>
        </div>
      </div>
    </section>

    <section class="espacos-section">
      <div class="container">
        <div class="section-header">
          <h2>Nossos <span>Espaços Disponíveis</span></h2>
          <p>Escolha a melhor configuração para seu evento</p>
        </div>
        <div class="espacos-grid">
          <div class="espaco-card">
            <div class="espaco-imagem campo"></div>
            <div class="espaco-info">
              <h3>Campo</h3>
              <p>Ideal para shows, festivais e grandes eventos</p>
              <div class="espaco-meta">
                <span>🎯 Capacidade: 25.000 pessoas</span>
                <span>💰 Sob consulta</span>
              </div>
            </div>
          </div>
          <div class="espaco-card">
            <div class="espaco-imagem anel"></div>
            <div class="espaco-info">
              <h3>Anel Inferior</h3>
              <p>Área coberta com excelente visibilidade</p>
              <div class="espaco-meta">
                <span>🎯 Capacidade: 8.000 pessoas</span>
                <span>💰 Sob consulta</span>
              </div>
            </div>
          </div>
          <div class="espaco-card">
            <div class="espaco-imagem camarote"></div>
            <div class="espaco-info">
              <h3>Camarotes</h3>
              <p>Espaços exclusivos para experiências premium</p>
              <div class="espaco-meta">
                <span>🎯 Capacidade: 500 pessoas</span>
                <span>💰 Sob consulta</span>
              </div>
            </div>
          </div>
          <div class="espaco-card">
            <div class="espaco-imagem completo"></div>
            <div class="espaco-info">
              <h3>Pacote Completo</h3>
              <p>Todos os espaços integrados para megaeventos</p>
              <div class="espaco-meta">
                <span>🎯 Capacidade: 35.000+ pessoas</span>
                <span>💰 Sob consulta</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="depoimentos-section">
      <div class="container">
        <div class="section-header">
          <h2>O que dizem <span>nossos produtores</span></h2>
          <p>Depoimentos de quem já realiza eventos na ArenaHub</p>
        </div>
        <div class="depoimentos-grid">
          <p>Nenhum depoimento disponível no momento.</p>
        </div>
      </div>
    </section>

    <section class="faq-section">
      <div class="container">
        <div class="section-header">
          <h2>Perguntas <span>Frequentes</span></h2>
          <p>Tire suas dúvidas sobre como se tornar um produtor</p>
        </div>
        <div class="faq-grid">
          <div v-for="(faq, index) in faqs" :key="index" class="faq-item">
            <button class="faq-question" @click="toggleFaq(index)">
              <span>{{ faq.pergunta }}</span>
              <svg
                :class="{ rotated: faqAberto === index }"
                width="20"
                height="20"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2.5"
              >
                <polyline points="6 9 12 15 18 9" />
              </svg>
            </button>
            <div class="faq-answer" v-show="faqAberto === index">
              <p>{{ faq.resposta }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="cta-section">
      <div class="container">
        <div class="cta-content">
          <h2>Pronto para realizar seu evento?</h2>
          <p>
            Junte-se aos maiores produtores do Nordeste e realize eventos memoráveis na ArenaHub
          </p>
          <button class="btn-cta" @click="irParaCadastro">
            Quero me tornar produtor
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <line x1="5" y1="12" x2="19" y2="12" />
              <polyline points="12 5 19 12 12 19" />
            </svg>
          </button>
        </div>
      </div>
    </section>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()
const beneficiosSection = ref<HTMLElement | null>(null)
const faqAberto = ref<number | null>(null)

const faqs = [
  {
    pergunta: 'Quais são os requisitos para se tornar produtor?',
    resposta:
      'Para se tornar produtor, você precisa ter CNPJ ativo, experiência comprovada na produção de eventos e documentação regularizada. Nossa equipe fará uma análise do seu perfil.',
  },
  {
    pergunta: 'Como funciona o processo de aprovação de eventos?',
    resposta:
      'Após enviar sua solicitação, nossa equipe analisará a proposta em até 5 dias úteis. Se aprovado, seu evento entrará na agenda e você poderá começar a vender ingressos.',
  },
  {
    pergunta: 'Quais são as formas de pagamento?',
    resposta:
      'Trabalhamos com diferentes modelos: aluguel do espaço, porcentagem sobre vendas ou parceria estratégica. Cada caso é analisado individualmente.',
  },
  {
    pergunta: 'A ArenaHub ajuda na divulgação?',
    resposta:
      'Sim! Divulgamos seu evento em nossos canais oficiais (site, redes sociais e newsletter) e oferecemos suporte para ações de marketing.',
  },
  {
    pergunta: 'Qual é o público médio dos eventos?',
    resposta:
      'Nossos eventos têm público variado, desde 500 pessoas em eventos corporativos até 30.000 pessoas em grandes shows e festivais.',
  },
]

function irParaCadastro() {
  router.push('/produtor/cadastro')
}

function scrollParaBeneficios() {
  beneficiosSection.value?.scrollIntoView({ behavior: 'smooth' })
}

function toggleFaq(index: number) {
  faqAberto.value = faqAberto.value === index ? null : index
}
</script>

<style scoped>
.page {
  min-height: 100vh;
  background: #0a0e17;
}

.hero-section {
  position: relative;
  min-height: 80vh;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  background: linear-gradient(135deg, #0a0e17 0%, #0f131e 100%);
  overflow: hidden;
}

.hero-overlay {
  position: absolute;
  inset: 0;
  background: radial-gradient(circle at 70% 30%, rgba(201, 168, 76, 0.08), transparent);
}

.hero-content {
  position: relative;
  z-index: 2;
  max-width: 800px;
  padding: 60px 20px;
}

.hero-title {
  font-family: 'Bebas Neue', sans-serif;
  font-size: 4rem;
  letter-spacing: 2px;
  color: #fff;
  margin-bottom: 20px;
}

.hero-title .highlight {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.hero-subtitle {
  font-size: 1.2rem;
  color: #b0b8c5;
  margin-bottom: 32px;
  line-height: 1.5;
}

.hero-buttons {
  display: flex;
  gap: 20px;
  justify-content: center;
  flex-wrap: wrap;
}

.btn-primary {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border: none;
  padding: 14px 32px;
  border-radius: 40px;
  font-weight: 800;
  font-size: 1rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 10px;
  transition: all 0.3s;
}

.btn-primary:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 25px rgba(201, 168, 76, 0.3);
}

.btn-secondary {
  background: transparent;
  color: #c9a84c;
  border: 2px solid #c9a84c;
  padding: 14px 32px;
  border-radius: 40px;
  font-weight: 700;
  font-size: 1rem;
  cursor: pointer;
  transition: all 0.3s;
}

.btn-secondary:hover {
  background: rgba(201, 168, 76, 0.1);
  transform: translateY(-3px);
}

.stats-section {
  padding: 60px 20px;
  background: linear-gradient(180deg, #121826 0%, #0f131e 100%);
  border-top: 1px solid rgba(255, 255, 255, 0.05);
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.container {
  max-width: 1200px;
  margin: 0 auto;
}

.stats-grid {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 20px;
}

.stat-item {
  text-align: center;
  flex: 1;
}

.stat-number {
  display: block;
  font-size: 2.5rem;
  font-weight: 800;
  color: #c9a84c;
  margin-bottom: 8px;
}

.stat-label {
  font-size: 0.85rem;
  color: #8e9aaf;
}

.stat-divider {
  width: 1px;
  height: 40px;
  background: rgba(255, 255, 255, 0.1);
}

.section-header {
  text-align: center;
  margin-bottom: 48px;
}

.section-header h2 {
  font-family: 'Bebas Neue', sans-serif;
  font-size: 2.5rem;
  color: #fff;
  margin-bottom: 16px;
}

.section-header h2 span {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.section-header p {
  color: #8e9aaf;
  font-size: 1rem;
}

.beneficios-section {
  padding: 80px 20px;
}

.beneficios-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 32px;
}

.beneficio-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 32px;
  text-align: center;
  transition: all 0.3s;
}

.beneficio-card:hover {
  transform: translateY(-5px);
  border-color: rgba(201, 168, 76, 0.2);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3);
}

.beneficio-icon {
  width: 72px;
  height: 72px;
  background: rgba(201, 168, 76, 0.1);
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto 24px;
  color: #c9a84c;
}

.beneficio-card h3 {
  font-size: 1.2rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 12px;
}

.beneficio-card p {
  color: #b0b8c5;
  font-size: 0.9rem;
  line-height: 1.5;
}

.espacos-section {
  padding: 80px 20px;
  background: #0f131e;
}

.espacos-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 24px;
}

.espaco-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  overflow: hidden;
  transition: all 0.3s;
}

.espaco-card:hover {
  transform: translateY(-5px);
  border-color: rgba(201, 168, 76, 0.2);
}

.espaco-imagem {
  height: 200px;
  background-size: cover;
  background-position: center;
}

.espaco-imagem.campo {
  background:
    linear-gradient(135deg, #1a2233, #0f131e),
    url('https://images.unsplash.com/photo-1522778119026-d364fceffa7e?w=600&q=80');
  background-blend-mode: overlay;
}

.espaco-imagem.anel {
  background:
    linear-gradient(135deg, #1a2233, #0f131e),
    url('https://images.unsplash.com/photo-1522778119026-d364fceffa7e?w=600&q=80');
  background-blend-mode: overlay;
}

.espaco-imagem.camarote {
  background:
    linear-gradient(135deg, #1a2233, #0f131e),
    url('https://images.unsplash.com/photo-1492684223066-81342ee5ff30?w=600&q=80');
  background-blend-mode: overlay;
}

.espaco-imagem.completo {
  background:
    linear-gradient(135deg, #1a2233, #0f131e),
    url('https://images.unsplash.com/photo-1540039155733-5bb30b53aa14?w=600&q=80');
  background-blend-mode: overlay;
}

.espaco-info {
  padding: 24px;
}

.espaco-info h3 {
  font-size: 1.2rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 8px;
}

.espaco-info p {
  color: #8e9aaf;
  font-size: 0.85rem;
  margin-bottom: 16px;
}

.espaco-meta {
  display: flex;
  gap: 16px;
  font-size: 0.8rem;
  color: #c9a84c;
}

.depoimentos-section {
  padding: 80px 20px;
}

.depoimentos-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 32px;
}

.depoimento-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 28px;
  transition: all 0.3s;
}

.depoimento-card:hover {
  transform: translateY(-5px);
  border-color: rgba(201, 168, 76, 0.2);
}

.depoimento-texto p {
  font-size: 0.95rem;
  color: #b0b8c5;
  line-height: 1.6;
  font-style: italic;
  margin-bottom: 20px;
}

.depoimento-autor {
  display: flex;
  align-items: center;
  gap: 12px;
}

.autor-avatar {
  width: 48px;
  height: 48px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 800;
  color: #0a0e17;
}

.autor-info strong {
  display: block;
  color: #fff;
  font-size: 0.9rem;
}

.autor-info span {
  font-size: 0.75rem;
  color: #8e9aaf;
}

.faq-section {
  padding: 80px 20px;
  background: #0f131e;
}

.faq-grid {
  max-width: 800px;
  margin: 0 auto;
}

.faq-item {
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.faq-question {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 0;
  background: transparent;
  border: none;
  color: #fff;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  text-align: left;
}

.faq-question svg {
  transition: transform 0.3s;
  color: #c9a84c;
}

.faq-question svg.rotated {
  transform: rotate(180deg);
}

.faq-answer {
  padding-bottom: 20px;
  color: #b0b8c5;
  font-size: 0.9rem;
  line-height: 1.5;
}

.cta-section {
  padding: 80px 20px;
}

.cta-content {
  background: linear-gradient(135deg, #1a2538, #0f1420);
  border: 1px solid rgba(201, 168, 76, 0.2);
  border-radius: 32px;
  padding: 60px;
  text-align: center;
}

.cta-content h2 {
  font-family: 'Bebas Neue', sans-serif;
  font-size: 2.5rem;
  color: #fff;
  margin-bottom: 16px;
}

.cta-content p {
  color: #8e9aaf;
  font-size: 1rem;
  margin-bottom: 32px;
}

.btn-cta {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border: none;
  padding: 16px 40px;
  border-radius: 40px;
  font-weight: 800;
  font-size: 1rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 10px;
  transition: all 0.3s;
}

.btn-cta:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 30px rgba(201, 168, 76, 0.3);
}

@media (max-width: 900px) {
  .hero-title {
    font-size: 2.5rem;
  }

  .beneficios-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .depoimentos-grid {
    grid-template-columns: 1fr;
  }

  .espacos-grid {
    grid-template-columns: 1fr;
  }

  .stats-grid {
    flex-direction: column;
  }

  .stat-divider {
    width: 60px;
    height: 1px;
  }
}

@media (max-width: 600px) {
  .beneficios-grid {
    grid-template-columns: 1fr;
  }

  .hero-buttons {
    flex-direction: column;
  }

  .btn-primary,
  .btn-secondary {
    width: 100%;
    justify-content: center;
  }

  .cta-content {
    padding: 40px 20px;
  }
}
</style>
