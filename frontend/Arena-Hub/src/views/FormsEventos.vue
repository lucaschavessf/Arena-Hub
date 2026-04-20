<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-wizard">
      <div class="wizard-container">
        
        <nav class="stepper-nav" v-if="step < 3">
          <div class="step-item" :class="{ active: step >= 1 }">
            <span class="step-number">1</span>
            <span class="step-label">Sobre Você</span>
          </div>
          <div class="step-divider"></div>
          <div class="step-item" :class="{ active: step >= 2 }">
            <span class="step-number">2</span>
            <span class="step-label">O Evento</span>
          </div>
        </nav>

        <transition name="slide" mode="out-in">
          <div v-if="step === 1" class="wizard-card" key="step1">
            <header class="wizard-header">
              <h2 class="wizard-title">Dados do Organizador</h2>
              <p class="wizard-subtitle">Conte-nos quem está por trás deste grande evento.</p>
            </header>

            <div class="form-content">
              <div class="form-group">
                <label>Nome Completo</label>
                <input v-model="form.nome" type="text" placeholder="Como deseja ser chamado?" />
              </div>
              
              <div class="form-row">
                <div class="form-group">
                  <label>E-mail Corporativo</label>
                  <input v-model="form.email" type="email" placeholder="exemplo@empresa.com" />
                </div>
                <div class="form-group">
                  <label>WhatsApp / Telefone</label>
                  <input v-model="form.whatsapp" type="tel" placeholder="(00) 00000-0000" />
                </div>
              </div>

              <div class="form-actions">
                <span></span> 
                <button class="btn-next" @click="step = 2">
                  Próxima Etapa
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><line x1="5" y1="12" x2="19" y2="12"/><polyline points="12 5 19 12 12 19"/></svg>
                </button>
              </div>
            </div>
          </div>

          <div v-else-if="step === 2" class="wizard-card" key="step2">
            <header class="wizard-header">
              <h2 class="wizard-title">Detalhes do Evento</h2>
              <p class="wizard-subtitle">Preencha as informações técnicas da sua atração.</p>
            </header>

            <div class="form-content">
              <div class="form-group">
                <label>Título do Evento</label>
                <input v-model="form.nomeEvento" type="text" placeholder="Ex: Festival de Verão Arena" />
              </div>

              <div class="form-row triplet">
                <div class="form-group">
                  <label>Data</label>
                  <input v-model="form.dataEvento" type="date" />
                </div>
                <div class="form-group">
                  <label>Cidade</label>
                  <input v-model="form.cidade" type="text" placeholder="Ex: Recife" />
                </div>
                <div class="form-group">
                  <label>Estado</label>
                  <input v-model="form.estado" type="text" placeholder="Ex: PE" />
                </div>
              </div>

              <div class="form-row">
                <div class="form-group">
                  <label>Categoria</label>
                  <select v-model="form.categoria" class="custom-select">
                    <option value="" disabled selected>Selecione...</option>
                    <option>Show</option>
                    <option>Esportes</option>
                    <option>Corporativo</option>
                  </select>
                </div>
                <div class="form-group">
                  <label>Instagram</label>
                  <input v-model="form.instagram" type="text" placeholder="@seu_evento" />
                </div>
              </div>

              <div class="form-group">
                <label>Descrição do Evento</label>
                <textarea v-model="form.descricao" rows="4" placeholder="Fale brevemente sobre a proposta do evento..."></textarea>
              </div>

              <label class="custom-checkbox">
                <input type="checkbox" v-model="form.menoresAcompanhados" />
                <span class="checkmark"></span>
                <span class="label-text">Permitir entrada de menores acompanhados de responsável.</span>
              </label>

              <div class="form-actions">
                <button class="btn-ghost" @click="step = 1">
                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><line x1="19" y1="12" x2="5" y2="12"/><polyline points="12 19 5 12 12 5"/></svg>
                  Voltar
                </button>
                <button class="btn-submit" @click="submeter">
                  Solicitar Cadastro
                </button>
              </div>
            </div>
          </div>

          <div v-else-if="step === 3" class="success-screen" key="step3">
            <div class="success-badge">
              <svg width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="#0a0e17" stroke-width="3"><polyline points="20 6 9 17 4 12"/></svg>
            </div>
            <h2 class="success-title">Proposta Enviada!</h2>
            <p class="success-text">Sua solicitação foi encaminhada para nossa curadoria. Em até 48h nossa equipe entrará em contato via WhatsApp ou E-mail.</p>
            <button class="btn-finish" @click="$router.push('/')">Voltar ao Início</button>
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

    const response = await fetch('http://localhost:8080/eventos', {
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
.page { min-height: 100vh; display: flex; flex-direction: column; background: #0a0e17; }

/* CENTRALIZAÇÃO MESTRE */
.main-wizard { 
  flex: 1; 
  display: flex;
  align-items: flex-start; /* Alinhado ao topo com padding para Desktop */
  justify-content: center;
  padding: 80px 20px; 
}

.wizard-container { 
  width: 100%;
  max-width: 700px; /* Largura ideal para leitura e formulários no PC */
  margin: 0 auto; 
}

/* Stepper UI */
.stepper-nav {
  display: flex; 
  align-items: center; 
  justify-content: center;
  gap: 24px; 
  margin-bottom: 48px;
}
.step-item { display: flex; align-items: center; gap: 12px; opacity: 0.3; transition: 0.3s; }
.step-item.active { opacity: 1; }
.step-number {
  width: 36px; height: 36px; border-radius: 50%; border: 2px solid #c9a84c;
  display: flex; align-items: center; justify-content: center;
  font-weight: 800; font-size: 0.9rem; color: #c9a84c;
}
.active .step-number { background: #c9a84c; color: #0a0e17; }
.step-label { font-size: 0.8rem; font-weight: 700; text-transform: uppercase; letter-spacing: 1.5px; }
.step-divider { width: 60px; height: 1px; background: rgba(255,255,255,0.1); }

/* Card Wizard */
.wizard-card {
  background: #121826; 
  border-radius: 24px; 
  padding: 56px; /* Mais respiro para PC */
  border: 1px solid rgba(201, 168, 76, 0.15);
  box-shadow: 0 40px 80px -12px rgba(0, 0, 0, 0.6);
}

.wizard-header { margin-bottom: 40px; }
.wizard-title { font-size: 2rem; font-weight: 800; color: #fff; margin-bottom: 12px; letter-spacing: -0.5px; }
.wizard-subtitle { color: #8e9aaf; font-size: 1.05rem; line-height: 1.5; }

/* Form Content */
.form-content { display: flex; flex-direction: column; gap: 28px; }
.form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 24px; }
.form-row.triplet { grid-template-columns: 1.5fr 1.5fr 0.8fr; }

.form-group { display: flex; flex-direction: column; gap: 10px; }
.form-group label { font-size: 0.7rem; font-weight: 800; color: #c9a84c; text-transform: uppercase; letter-spacing: 1px; }

.form-group input, .form-group textarea, .custom-select {
  background: #1c2433; 
  border: 1.5px solid rgba(255,255,255,0.05); 
  border-radius: 12px;
  padding: 16px; 
  color: white; 
  font-size: 1rem; 
  outline: none; 
  transition: 0.2s;
}
.form-group input:focus, .form-group textarea:focus { border-color: #c9a84c; background: #242d3d; }

/* Checkbox */
.custom-checkbox {
  display: flex; align-items: flex-start; gap: 16px; cursor: pointer;
  padding: 20px; background: rgba(255,255,255,0.03); border-radius: 14px;
  transition: 0.2s;
}
.custom-checkbox:hover { background: rgba(255,255,255,0.05); }
.checkmark { width: 22px; height: 22px; border: 2px solid #c9a84c; border-radius: 6px; flex-shrink: 0; }

/* Actions */
.form-actions { display: flex; justify-content: space-between; align-items: center; margin-top: 12px; }

.btn-next, .btn-submit {
  background: #c9a84c; color: #0a0e17; border: none; padding: 16px 36px;
  border-radius: 12px; font-weight: 800; font-size: 1rem; cursor: pointer; display: flex; align-items: center; gap: 12px; transition: 0.3s;
}
.btn-next:hover, .btn-submit:hover { transform: translateY(-3px); box-shadow: 0 10px 20px rgba(201, 168, 76, 0.2); }

.btn-ghost { background: transparent; border: 1.5px solid rgba(255,255,255,0.1); color: #8e9aaf; padding: 14px 24px; border-radius: 12px; cursor: pointer; display: flex; align-items: center; gap: 10px; font-weight: 600; }

/* Success */
.success-screen { text-align: center; padding: 60px 0; }
.success-badge { width: 90px; height: 90px; background: #c9a84c; border-radius: 50%; display: flex; align-items: center; justify-content: center; margin: 0 auto 36px; }
.success-title { font-size: 2.5rem; font-weight: 900; color: #fff; }

/* Transições */
.slide-enter-active, .slide-leave-active { transition: all 0.4s ease; }
.slide-enter-from { opacity: 0; transform: translateX(30px); }
.slide-leave-to { opacity: 0; transform: translateX(-30px); }

/* Responsive Mobile */
@media (max-width: 650px) {
  .main-wizard { padding: 40px 16px; }
  .form-row, .form-row.triplet { grid-template-columns: 1fr; }
  .wizard-card { padding: 32px 24px; }
  .stepper-nav { gap: 10px; }
  .step-divider { width: 30px; }
}
</style>