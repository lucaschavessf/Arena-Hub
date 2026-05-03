<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-wizard">
      <div class="wizard-container">
        <div class="breadcrumb">
          <router-link to="/" class="breadcrumb-link">Início</router-link>
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <polyline points="9 18 15 12 9 6"/>
          </svg>
          <span class="breadcrumb-current">Solicitar Evento</span>
        </div>
        
        <nav class="stepper-nav" v-if="step < 3">
          <div class="step-item" :class="{ active: step >= 1, completed: step > 1 }">
            <span class="step-number">
              <span v-if="step > 1">✓</span>
              <span v-else>1</span>
            </span>
            <span class="step-label">Sobre Você</span>
          </div>
          <div class="step-divider" :class="{ active: step > 1 }"></div>
          <div class="step-item" :class="{ active: step >= 2 }">
            <span class="step-number">2</span>
            <span class="step-label">O Evento</span>
          </div>
        </nav>

        <transition name="slide" mode="out-in">
          <div v-if="step === 1" class="wizard-card" key="step1">
            <header class="wizard-header">
              <div class="header-icon">
                <svg width="28" height="28" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                  <circle cx="12" cy="7" r="4"/>
                </svg>
              </div>
              <div class="header-text">
                <h2 class="wizard-title">Dados do Organizador</h2>
                <p class="wizard-subtitle">Conte-nos quem está por trás deste grande evento.</p>
              </div>
            </header>

            <div class="form-content">
              <div class="form-group">
                <label>Nome Completo</label>
                <div class="input-with-icon">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/>
                  </svg>
                  <input v-model="form.nome" type="text" placeholder="Como deseja ser chamado?" />
                </div>
              </div>
              
              <div class="form-row">
                <div class="form-group">
                  <label>E-mail Corporativo</label>
                  <div class="input-with-icon">
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/>
                      <polyline points="22,6 12,13 2,6"/>
                    </svg>
                    <input v-model="form.email" type="email" placeholder="exemplo@empresa.com" />
                  </div>
                </div>
                <div class="form-group">
                  <label>WhatsApp / Telefone</label>
                  <div class="input-with-icon">
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"/>
                    </svg>
                    <input v-model="form.whatsapp" type="tel" placeholder="(00) 00000-0000" />
                  </div>
                </div>
              </div>

              <div class="info-note">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="12" r="10"/><path d="M12 16v-4M12 8h.01"/>
                </svg>
                <span>Seus dados serão usados apenas para contato sobre o evento.</span>
              </div>

              <div class="form-actions">
                <span></span> 
                <button class="btn-next" @click="step = 2">
                  <span>Próxima Etapa</span>
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                    <line x1="5" y1="12" x2="19" y2="12"/><polyline points="12 5 19 12 12 19"/>
                  </svg>
                </button>
              </div>
            </div>
          </div>

          <div v-else-if="step === 2" class="wizard-card" key="step2">
            <header class="wizard-header">
              <div class="header-icon">
                <svg width="28" height="28" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
                </svg>
              </div>
              <div class="header-text">
                <h2 class="wizard-title">Detalhes do Evento</h2>
                <p class="wizard-subtitle">Preencha as informações técnicas da sua atração.</p>
              </div>
            </header>

            <div class="form-content">
              <div class="form-group">
                <label>Título do Evento</label>
                <div class="input-with-icon">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M4 20h16a2 2 0 0 0 2-2V8a2 2 0 0 0-2-2h-7.5L9 3H4a2 2 0 0 0-2 2v13a2 2 0 0 0 2 2z"/>
                  </svg>
                  <input v-model="form.nomeEvento" type="text" placeholder="Ex: Festival de Verão Arena" />
                </div>
              </div>

              <div class="form-row triplet">
                <div class="form-group">
                  <label>Data</label>
                  <div class="input-with-icon">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
                    </svg>
                    <input v-model="form.dataEvento" type="date" />
                  </div>
                </div>
                <div class="form-group">
                  <label>Cidade</label>
                  <div class="input-with-icon">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/>
                    </svg>
                    <input v-model="form.cidade" type="text" placeholder="Ex: Recife" />
                  </div>
                </div>
                <div class="form-group">
                  <label>Estado</label>
                  <div class="input-with-icon">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M12 2L2 7v10l10 5 10-5V7l-10-5z"/><path d="M2 7l10 5 10-5M12 22V12"/>
                    </svg>
                    <input v-model="form.estado" type="text" placeholder="Ex: PE" maxlength="2" />
                  </div>
                </div>
              </div>

              <div class="form-row">
                <div class="form-group">
                  <label>Categoria</label>
                  <div class="input-with-icon">
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M20 7h-4.5L12 3 8.5 7H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/>
                    </svg>
                    <select v-model="form.categoria" class="custom-select">
                      <option value="" disabled selected>Selecione uma categoria...</option>
                      <option>Show</option>
                      <option>Esportes</option>
                      <option>Corporativo</option>
                      <option>Teatro</option>
                      <option>Comédia</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label>Instagram</label>
                  <div class="input-with-icon">
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <rect x="2" y="2" width="20" height="20" rx="5"/><circle cx="12" cy="12" r="4"/>
                    </svg>
                    <input v-model="form.instagram" type="text" placeholder="@seu_evento" />
                  </div>
                </div>
              </div>

              <div class="form-group">
                <label>Descrição do Evento</label>
                <textarea v-model="form.descricao" rows="4" placeholder="Fale brevemente sobre a proposta do evento, público esperado, atrações..."></textarea>
              </div>

              <label class="custom-checkbox">
                <input type="checkbox" v-model="form.menoresAcompanhados" />
                <span class="checkmark">
                  <svg v-if="form.menoresAcompanhados" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#0a0e17" stroke-width="3">
                    <polyline points="20 6 9 17 4 12"/>
                  </svg>
                </span>
                <span class="label-text">Permitir entrada de menores acompanhados de responsável.</span>
              </label>

              <div class="form-actions">
                <button class="btn-ghost" @click="step = 1">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                    <line x1="19" y1="12" x2="5" y2="12"/><polyline points="12 19 5 12 12 5"/>
                  </svg>
                  <span>Voltar</span>
                </button>
                <button class="btn-submit" @click="submeter">
                  <span>Solicitar Cadastro</span>
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                    <polyline points="20 6 9 17 4 12"/>
                  </svg>
                </button>
              </div>
            </div>
          </div>

          <div v-else-if="step === 3" class="success-screen" key="step3">
            <div class="success-badge">
              <svg width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="#0a0e17" stroke-width="3">
                <polyline points="20 6 9 17 4 12"/>
              </svg>
            </div>
            <h2 class="success-title">Proposta Enviada!</h2>
            <p class="success-text">Sua solicitação foi encaminhada para nossa curadoria. Em até 48h nossa equipe entrará em contato via WhatsApp ou E-mail.</p>
            
            <div class="success-details">
              <div class="detail-item">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"/>
                </svg>
                <span>Entraremos em contato pelo WhatsApp</span>
              </div>
              <div class="detail-item">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/>
                  <polyline points="22,6 12,13 2,6"/>
                </svg>
                <span>Confirmação enviada para seu e-mail</span>
              </div>
            </div>

            <button class="btn-finish" @click="$router.push('/')">
              <span>Voltar ao Início</span>
              <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <path d="M3 12h18M3 12l6-6M3 12l6 6"/>
              </svg>
            </button>
          </div>
        </transition>
      </div>
    </main>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const step = ref(1)
const form = reactive({
  nome: '', email: '', whatsapp: '',
  nomeEvento: '', dataEvento: '', cidade: '', estado: '',
  categoria: '', menoresAcompanhados: false,
  descricao: '', instagram: '',
})

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080'

async function submeter() { 
  try {
    const dataInicio = form.dataEvento ? `${form.dataEvento}T20:00:00` : new Date().toISOString().substring(0, 19);
    const dataFim = form.dataEvento ? `${form.dataEvento}T23:59:59` : new Date().toISOString().substring(0, 19);

    const payload = {
      nome: form.nomeEvento,
      descricao: form.descricao || "Sem descrição",
      dataInicio: dataInicio,
      dataFim: dataFim,
      expectativaPublico: 1000, // Valor padrão
      categoria: form.categoria || "Outros",
      espacoId: 1 // Valor fixo conforme solicitado
    };

    const response = await fetch(`${API_URL}/eventos`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    });

    if (!response.ok) {
      throw new Error('Erro na requisição para criar evento');
    }

    window.scrollTo({ top: 0, behavior: 'smooth' });
    step.value = 3; 
  } catch (error) {
    console.error('Erro ao enviar evento:', error);
    alert('Ocorreu um erro ao cadastrar o evento. Verifique a conexão com o servidor.');
  }
}
</script>

<style scoped>
.page { 
  min-height: 100vh; 
  display: flex; 
  flex-direction: column; 
  background: #0a0e17; 
}

.main-wizard { 
  flex: 1; 
  display: flex;
  align-items: flex-start;
  justify-content: center;
  padding: 60px 20px 80px; 
}

.wizard-container { 
  width: 100%;
  max-width: 720px;
  margin: 0 auto; 
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 24px;
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

.breadcrumb svg {
  color: #4a5568;
}

.stepper-nav {
  display: flex; 
  align-items: center; 
  justify-content: center;
  gap: 20px; 
  margin-bottom: 40px;
}

.step-item { 
  display: flex; 
  align-items: center; 
  gap: 10px; 
  opacity: 0.4; 
  transition: all 0.3s ease; 
}

.step-item.active { 
  opacity: 1; 
}

.step-item.completed {
  opacity: 0.8;
}

.step-number {
  width: 40px; 
  height: 40px; 
  border-radius: 12px; 
  border: 2px solid rgba(201, 168, 76, 0.4);
  display: flex; 
  align-items: center; 
  justify-content: center;
  font-weight: 800; 
  font-size: 1rem; 
  color: #c9a84c;
  transition: all 0.3s ease;
  background: transparent;
}

.active .step-number { 
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17; 
  border-color: #c9a84c;
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.2);
}

.completed .step-number {
  background: #4cc98e;
  border-color: #4cc98e;
  color: #0a0e17;
}

.step-label { 
  font-size: 0.8rem; 
  font-weight: 700; 
  text-transform: uppercase; 
  letter-spacing: 1px;
  color: #b0b8c5;
}

.active .step-label {
  color: #fff;
}

.step-divider { 
  width: 60px; 
  height: 2px; 
  background: rgba(255, 255, 255, 0.08);
  border-radius: 1px;
  transition: all 0.3s ease;
}

.step-divider.active {
  background: linear-gradient(90deg, #4cc98e, #c9a84c);
}

.wizard-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 28px; 
  padding: 44px; 
  border: 1px solid rgba(201, 168, 76, 0.12);
  box-shadow: 0 30px 60px rgba(0, 0, 0, 0.4);
}

.wizard-header { 
  margin-bottom: 36px; 
  display: flex;
  align-items: flex-start;
  gap: 16px;
}

.header-icon {
  width: 52px;
  height: 52px;
  background: rgba(201, 168, 76, 0.08);
  border-radius: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #c9a84c;
  border: 1px solid rgba(201, 168, 76, 0.15);
  flex-shrink: 0;
}

.header-text {
  flex: 1;
}

.wizard-title { 
  font-size: 1.8rem; 
  font-weight: 800; 
  color: #fff; 
  margin-bottom: 6px;
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.wizard-subtitle { 
  color: #8e9aaf; 
  font-size: 0.95rem; 
  line-height: 1.5; 
}

.form-content { 
  display: flex; 
  flex-direction: column; 
  gap: 24px; 
}

.form-row { 
  display: grid; 
  grid-template-columns: 1fr 1fr; 
  gap: 20px; 
}

.form-row.triplet { 
  grid-template-columns: 1.5fr 1.5fr 0.8fr; 
}

.form-group { 
  display: flex; 
  flex-direction: column; 
  gap: 8px; 
}

.form-group label { 
  font-size: 0.7rem; 
  font-weight: 800; 
  color: #c9a84c; 
  text-transform: uppercase; 
  letter-spacing: 0.8px;
  margin-left: 4px;
}

.input-with-icon {
  position: relative;
  display: flex;
  align-items: center;
}

.input-with-icon svg {
  position: absolute;
  left: 16px;
  color: #c9a84c;
  opacity: 0.6;
  pointer-events: none;
  z-index: 1;
}

.form-group input, 
.form-group textarea, 
.custom-select {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 14px;
  padding: 14px 16px;
  color: white; 
  font-size: 0.95rem; 
  outline: none; 
  transition: all 0.3s ease;
  width: 100%;
  font-family: var(--font-body);
}

.input-with-icon input,
.input-with-icon .custom-select {
  padding-left: 48px;
}

.form-group input:hover,
.form-group textarea:hover,
.custom-select:hover {
  border-color: rgba(201, 168, 76, 0.2);
  background: #1e2638;
}

.form-group input:focus, 
.form-group textarea:focus,
.custom-select:focus {
  border-color: #c9a84c; 
  background: #1e2638;
  box-shadow: 0 0 0 4px rgba(201, 168, 76, 0.08);
}

.form-group input::placeholder,
.form-group textarea::placeholder {
  color: #5a6375;
  font-weight: 300;
}

.custom-select {
  cursor: pointer;
  appearance: none;
  background-image: url("data:image/svg+xml,%3Csvg width='14' height='14' viewBox='0 0 24 24' fill='none' stroke='%23c9a84c' stroke-width='2.5'%3E%3Cpolyline points='6 9 12 15 18 9'/%3E%3C/svg%3E");
  background-repeat: no-repeat;
  background-position: right 16px center;
  background-size: 14px;
}

.custom-select option {
  background: #121826;
  padding: 12px;
}

textarea {
  resize: vertical;
  min-height: 100px;
  line-height: 1.6;
}

.info-note {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 18px;
  background: rgba(201, 168, 76, 0.05);
  border-radius: 14px;
  border: 1px solid rgba(201, 168, 76, 0.1);
  color: #b0b8c5;
  font-size: 0.85rem;
}

.info-note svg {
  color: #c9a84c;
  flex-shrink: 0;
}

.custom-checkbox {
  display: flex; 
  align-items: flex-start; 
  gap: 14px; 
  cursor: pointer;
  padding: 18px 20px; 
  background: rgba(255, 255, 255, 0.02);
  border-radius: 14px;
  transition: all 0.3s ease;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.custom-checkbox:hover { 
  background: rgba(255, 255, 255, 0.05);
  border-color: rgba(201, 168, 76, 0.15);
}

.custom-checkbox input {
  display: none;
}

.checkmark { 
  width: 22px; 
  height: 22px; 
  border: 2px solid rgba(201, 168, 76, 0.4);
  border-radius: 6px; 
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s ease;
}

.custom-checkbox input:checked + .checkmark {
  background: #c9a84c;
  border-color: #c9a84c;
}

.label-text {
  color: #b0b8c5;
  font-size: 0.9rem;
  line-height: 1.5;
}

.form-actions { 
  display: flex; 
  justify-content: space-between; 
  align-items: center; 
  margin-top: 12px; 
}

.btn-next, 
.btn-submit {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17; 
  border: none; 
  padding: 16px 32px;
  border-radius: 14px; 
  font-weight: 800; 
  font-size: 1rem; 
  cursor: pointer; 
  display: flex; 
  align-items: center; 
  gap: 12px; 
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  text-transform: uppercase;
  letter-spacing: 0.5px;
  box-shadow: 0 4px 15px rgba(201, 168, 76, 0.15);
}

.btn-next:hover, 
.btn-submit:hover { 
  transform: translateY(-3px); 
  box-shadow: 0 10px 25px rgba(201, 168, 76, 0.25);
}

.btn-next svg,
.btn-submit svg {
  transition: transform 0.3s ease;
}

.btn-next:hover svg,
.btn-submit:hover svg {
  transform: translateX(4px);
}

.btn-ghost { 
  background: transparent; 
  border: 1.5px solid rgba(255, 255, 255, 0.1);
  color: #b0b8c5; 
  padding: 14px 24px; 
  border-radius: 14px; 
  cursor: pointer; 
  display: flex; 
  align-items: center; 
  gap: 10px; 
  font-weight: 600;
  transition: all 0.3s ease;
}

.btn-ghost:hover {
  background: rgba(255, 255, 255, 0.05);
  border-color: rgba(201, 168, 76, 0.3);
  color: #fff;
}

.btn-ghost svg {
  transition: transform 0.3s ease;
}

.btn-ghost:hover svg {
  transform: translateX(-4px);
}

.success-screen { 
  text-align: center; 
  padding: 60px 20px; 
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 28px;
  border: 1px solid rgba(201, 168, 76, 0.12);
}

.success-badge { 
  width: 100px; 
  height: 100px; 
  background: linear-gradient(135deg, #4cc98e, #34a86c);
  border-radius: 30px; 
  display: flex; 
  align-items: center; 
  justify-content: center; 
  margin: 0 auto 32px;
  box-shadow: 0 10px 30px rgba(76, 201, 142, 0.3);
}

.success-title { 
  font-size: 2.2rem; 
  font-weight: 900; 
  color: #fff; 
  margin-bottom: 16px;
}

.success-text {
  color: #8e9aaf;
  font-size: 1rem;
  line-height: 1.7;
  max-width: 450px;
  margin: 0 auto 32px;
}

.success-details {
  display: flex;
  flex-direction: column;
  gap: 12px;
  max-width: 350px;
  margin: 0 auto 40px;
}

.detail-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  background: rgba(255, 255, 255, 0.03);
  border-radius: 12px;
  color: #b0b8c5;
  font-size: 0.9rem;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.detail-item svg {
  color: #4cc98e;
  flex-shrink: 0;
}

.btn-finish {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border: none;
  padding: 16px 36px;
  border-radius: 14px;
  font-weight: 800;
  font-size: 1rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 12px;
  transition: all 0.3s ease;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  box-shadow: 0 4px 15px rgba(201, 168, 76, 0.2);
}

.btn-finish:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 25px rgba(201, 168, 76, 0.3);
}

.btn-finish svg {
  transition: transform 0.3s ease;
}

.btn-finish:hover svg {
  transform: translateX(4px);
}

.slide-enter-active, 
.slide-leave-active { 
  transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1); 
}

.slide-enter-from { 
  opacity: 0; 
  transform: translateX(30px); 
}

.slide-leave-to { 
  opacity: 0; 
  transform: translateX(-30px); 
}

@media (max-width: 650px) {
  .main-wizard { 
    padding: 40px 16px; 
  }
  
  .form-row, 
  .form-row.triplet { 
    grid-template-columns: 1fr; 
  }
  
  .wizard-card { 
    padding: 32px 24px; 
  }
  
  .stepper-nav { 
    gap: 10px; 
  }
  
  .step-divider { 
    width: 30px; 
  }
  
  .wizard-header {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
  
  .wizard-title {
    font-size: 1.5rem;
  }
  
  .form-actions {
    flex-direction: column-reverse;
    gap: 12px;
  }
  
  .btn-next,
  .btn-submit,
  .btn-ghost {
    width: 100%;
    justify-content: center;
  }
  
  .success-badge {
    width: 80px;
    height: 80px;
  }
  
  .success-title {
    font-size: 1.8rem;
  }
}

@media (max-width: 450px) {
  .step-label {
    display: none;
  }
  
  .stepper-nav {
    gap: 20px;
  }
  
  .step-divider {
    width: 20px;
  }
}
</style>