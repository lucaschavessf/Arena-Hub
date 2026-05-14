<template>
  <div class="page">
    <AppNavbar />

    <main class="main-cadastro">
      <div class="cadastro-container">
        <div class="breadcrumb">
          <router-link to="/" class="breadcrumb-link">Início</router-link>
          <svg
            width="14"
            height="14"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2.5"
          >
            <polyline points="9 18 15 12 9 6" />
          </svg>
          <router-link to="/produtor" class="breadcrumb-link">Produtor</router-link>
          <svg
            width="14"
            height="14"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2.5"
          >
            <polyline points="9 18 15 12 9 6" />
          </svg>
          <span class="breadcrumb-current">Cadastro</span>
        </div>

        <div class="cadastro-grid">
          <div class="form-card">
            <div class="form-header">
              <h1>Cadastre-se como Produtor</h1>
              <p>Preencha os dados abaixo para iniciar sua parceria com a ArenaHub</p>
            </div>

            <form @submit.prevent="enviarCadastro" class="cadastro-form">
              <div class="form-section">
                <h3 class="section-title">
                  <svg
                    width="20"
                    height="20"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <circle cx="12" cy="8" r="4" />
                    <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7" />
                  </svg>
                  Dados Pessoais
                </h3>
                <div class="form-row">
                  <div class="form-field">
                    <label>Nome completo</label>
                    <input type="text" v-model="form.nome" class="input-field" required />
                  </div>
                  <div class="form-field">
                    <label>E-mail</label>
                    <input type="email" v-model="form.email" class="input-field" required />
                  </div>
                </div>
                <div class="form-row">
                  <div class="form-field">
                    <label>Telefone</label>
                    <input
                      type="tel"
                      v-model="form.telefone"
                      class="input-field"
                      required
                      placeholder="(81) 99999-9999"
                    />
                  </div>
                  <div class="form-field">
                    <label>WhatsApp</label>
                    <input
                      type="tel"
                      v-model="form.whatsapp"
                      class="input-field"
                      placeholder="(81) 99999-9999"
                    />
                  </div>
                </div>
              </div>

              <div class="form-section">
                <h3 class="section-title">
                  <svg
                    width="20"
                    height="20"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <rect x="3" y="3" width="18" height="18" rx="2" />
                    <line x1="9" y1="9" x2="15" y2="15" />
                    <line x1="15" y1="9" x2="9" y2="15" />
                  </svg>
                  Dados da Empresa
                </h3>
                <div class="form-row">
                  <div class="form-field">
                    <label>Razão Social</label>
                    <input type="text" v-model="form.razaoSocial" class="input-field" required />
                  </div>
                  <div class="form-field">
                    <label>Nome Fantasia</label>
                    <input type="text" v-model="form.nomeFantasia" class="input-field" />
                  </div>
                </div>
                <div class="form-row">
                  <div class="form-field">
                    <label>CNPJ</label>
                    <input
                      type="text"
                      v-model="form.cnpj"
                      class="input-field"
                      required
                      placeholder="xx.xxx.xxx/xxxx-xx"
                    />
                  </div>
                  <div class="form-field">
                    <label>Inscrição Estadual</label>
                    <input type="text" v-model="form.inscricaoEstadual" class="input-field" />
                  </div>
                </div>
              </div>

              <div class="form-section">
                <h3 class="section-title">
                  <svg
                    width="20"
                    height="20"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <polygon
                      points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                    />
                  </svg>
                  Experiência e Portfólio
                </h3>
                <div class="form-field">
                  <label>Anos de experiência *</label>
                  <select v-model="form.anosExperiencia" class="input-field" required>
                    <option value="">Selecione</option>
                    <option value="1">Menos de 1 ano</option>
                    <option value="1-3">1 a 3 anos</option>
                    <option value="3-5">3 a 5 anos</option>
                    <option value="5-10">5 a 10 anos</option>
                    <option value="10+">Mais de 10 anos</option>
                  </select>
                </div>
                <div class="form-field">
                  <label>Tipos de evento que realiza *</label>
                  <div class="checkbox-group">
                    <label class="checkbox-item">
                      <input type="checkbox" value="Show" v-model="form.tiposEvento" />
                      <span>Shows/Festivais</span>
                    </label>
                    <label class="checkbox-item">
                      <input type="checkbox" value="Esportes" v-model="form.tiposEvento" />
                      <span>Eventos Esportivos</span>
                    </label>
                    <label class="checkbox-item">
                      <input type="checkbox" value="Corporativo" v-model="form.tiposEvento" />
                      <span>Eventos Corporativos</span>
                    </label>
                    <label class="checkbox-item">
                      <input type="checkbox" value="Teatro" v-model="form.tiposEvento" />
                      <span>Teatro/Cultura</span>
                    </label>
                    <label class="checkbox-item">
                      <input type="checkbox" value="Comédia" v-model="form.tiposEvento" />
                      <span>Comédia</span>
                    </label>
                    <label class="checkbox-item">
                      <input type="checkbox" value="Outros" v-model="form.tiposEvento" />
                      <span>Outros</span>
                    </label>
                  </div>
                </div>
                <div class="form-field">
                  <label>Portfólio (links de eventos anteriores)</label>
                  <textarea
                    v-model="form.portfolio"
                    rows="3"
                    class="input-field"
                    placeholder="Adicione links de eventos que já realizou, redes sociais, etc..."
                  ></textarea>
                </div>
                <div class="form-field">
                  <label>Por que quer produzir eventos na ArenaHub?</label>
                  <textarea
                    v-model="form.motivacao"
                    rows="3"
                    class="input-field"
                    placeholder="Conte-nos sua história e motivação..."
                  ></textarea>
                </div>
              </div>

              <div class="form-actions">
                <button type="button" class="btn-cancelar" @click="$router.push('/produtor')">
                  Cancelar
                </button>
                <button type="submit" class="btn-enviar" :disabled="enviando">
                  {{ enviando ? 'Enviando...' : 'Enviar Solicitação' }}
                </button>
              </div>
            </form>
          </div>

          <div class="info-sidebar">
            <div class="info-card">
              <h3>Após o cadastro</h3>
              <ul>
                <li> Receberá um e-mail de confirmação </li>
                <li> Nossa equipe analisará seu perfil em até 5 dias úteis </li>
                <li> Entraremos em contato para alinhar próximos passos </li>
                <li> Após aprovação, você poderá solicitar eventos </li>
              </ul>
            </div>

            <div class="info-card destaque">
              <h3>Dúvidas?</h3>
              <p>Entre em contato com nossa equipe:</p>
              <div class="contato-info">
                <a href="mailto:produtores@arenahub.com">
                  <svg
                    width="18"
                    height="18"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <rect x="2" y="4" width="20" height="16" rx="2" />
                    <path d="M22 7l-10 7L2 7" />
                  </svg>
                  produtores@arenahub.com
                </a>
                <a href="tel:81999998888">
                  <svg
                    width="18"
                    height="18"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <path
                      d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72c.127.96.362 1.903.7 2.81a2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45c.907.338 1.85.573 2.81.7A2 2 0 0 1 22 16.92z"
                    />
                  </svg>
                  (81) 99999-8888
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()
const enviando = ref(false)

const uploadContrato = ref<HTMLInputElement>()
const uploadComprovante = ref<HTMLInputElement>()
const contratoNome = ref('')
const comprovanteNome = ref('')

const form = ref({
  nome: '',
  email: '',
  telefone: '',
  whatsapp: '',
  razaoSocial: '',
  nomeFantasia: '',
  cnpj: '',
  inscricaoEstadual: '',
  endereco: '',
  cidade: '',
  estado: '',
  cep: '',
  anosExperiencia: '',
  tiposEvento: [] as string[],
  portfolio: '',
  motivacao: '',
  aceiteTermos: false,
  aceiteComunicacao: false,
})

function handleContratoUpload(event: Event) {
  const input = event.target as HTMLInputElement
  if (input.files && input.files[0]) {
    contratoNome.value = input.files[0].name
  }
}

function handleComprovanteUpload(event: Event) {
  const input = event.target as HTMLInputElement
  if (input.files && input.files[0]) {
    comprovanteNome.value = input.files[0].name
  }
}

function enviarCadastro() {
  if (!form.value.aceiteTermos) {
    alert('Você precisa aceitar os Termos de Uso para continuar')
    return
  }

  if (!contratoNome.value) {
    alert('Faça o upload do Contrato Social')
    return
  }

  if (!comprovanteNome.value) {
    alert('Faça o upload do Comprovante de Endereço')
    return
  }

  enviando.value = true

  setTimeout(() => {
    enviando.value = false
    alert('Solicitação enviada com sucesso! Entraremos em contato em breve.')
    router.push('/produtor')
  }, 2000)
}

function abrirTermos() {
  alert('Termos de Uso - Em desenvolvimento')
}

function abrirPolitica() {
  alert('Política de Privacidade - Em desenvolvimento')
}
</script>

<style scoped>
.page {
  min-height: 100vh;
  background: #0a0e17;
}

.main-cadastro {
  flex: 1;
  padding: 40px 20px;
}

.cadastro-container {
  max-width: 1200px;
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

.cadastro-grid {
  display: grid;
  grid-template-columns: 1fr 320px;
  gap: 32px;
}

.form-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 24px;
  padding: 32px;
}

.form-header {
  margin-bottom: 32px;
  text-align: center;
}

.form-header h1 {
  font-family: 'Bebas Neue', sans-serif;
  font-size: 2rem;
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin-bottom: 8px;
}

.form-header p {
  color: #8e9aaf;
  font-size: 0.9rem;
}

.form-section {
  margin-bottom: 32px;
  padding-bottom: 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.form-section:last-child {
  border-bottom: none;
  padding-bottom: 0;
}

.section-title {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 1rem;
  font-weight: 700;
  color: #c9a84c;
  margin-bottom: 20px;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
  margin-bottom: 20px;
}

.form-field {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-bottom: 16px;
}

.form-field.full-width {
  grid-column: span 2;
}

.form-field label {
  font-size: 0.7rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.input-field {
  background: #1a2233;
  border: 1.5px solid rgba(255, 255, 255, 0.06);
  border-radius: 10px;
  padding: 11px 14px;
  color: white;
  font-size: 0.9rem;
  outline: none;
  transition: all 0.3s;
}

.input-field:focus {
  border-color: #c9a84c;
  box-shadow: 0 0 0 3px rgba(201, 168, 76, 0.1);
}

textarea.input-field {
  resize: vertical;
  font-family: inherit;
}

select.input-field {
  cursor: pointer;
}

.checkbox-group {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  margin-top: 8px;
}

.checkbox-item {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  color: #e0e0e0;
  font-size: 0.85rem;
}

.checkbox-item input {
  width: 16px;
  height: 16px;
  cursor: pointer;
  accent-color: #c9a84c;
}

.file-upload {
  background: #1a2233;
  border: 2px dashed rgba(201, 168, 76, 0.3);
  border-radius: 12px;
  padding: 20px;
  text-align: center;
  cursor: pointer;
  transition: all 0.3s;
  color: #8e9aaf;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
}

.file-upload:hover {
  border-color: #c9a84c;
  background: rgba(201, 168, 76, 0.05);
}

.file-upload svg {
  color: #c9a84c;
}

.termos {
  margin-top: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.termos .checkbox-item a {
  color: #c9a84c;
  text-decoration: none;
}

.termos .checkbox-item a:hover {
  text-decoration: underline;
}

.form-actions {
  display: flex;
  gap: 16px;
  justify-content: flex-end;
  margin-top: 32px;
}

.btn-cancelar {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 12px 24px;
  border-radius: 10px;
  color: #8e9aaf;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s;
}

.btn-cancelar:hover {
  background: #1e2638;
}

.btn-enviar {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 12px 32px;
  border-radius: 10px;
  color: #0a0e17;
  font-weight: 700;
  cursor: pointer;
  transition: all 0.3s;
}

.btn-enviar:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 6px 15px rgba(201, 168, 76, 0.3);
}

.btn-enviar:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.info-sidebar {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.info-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 24px;
}

.info-card h3 {
  font-size: 1rem;
  font-weight: 700;
  color: #c9a84c;
  margin-bottom: 16px;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.info-card ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.info-card li {
  padding: 8px 0;
  color: #b0b8c5;
  font-size: 0.85rem;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.info-card li:last-child {
  border-bottom: none;
}

.info-card.destaque {
  background: linear-gradient(135deg, #1a2538 0%, #121826 100%);
  border-color: rgba(201, 168, 76, 0.2);
}

.contato-info {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-top: 16px;
}

.contato-info a {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #c9a84c;
  text-decoration: none;
  font-size: 0.85rem;
  transition: all 0.3s;
}

.contato-info a:hover {
  text-decoration: underline;
}

@media (max-width: 900px) {
  .cadastro-grid {
    grid-template-columns: 1fr;
  }

  .form-row {
    grid-template-columns: 1fr;
    gap: 16px;
  }

  .form-field.full-width {
    grid-column: span 1;
  }

  .checkbox-group {
    flex-direction: column;
    gap: 10px;
  }
}

@media (max-width: 600px) {
  .form-card {
    padding: 24px 20px;
  }

  .form-actions {
    flex-direction: column;
  }

  .btn-cancelar,
  .btn-enviar {
    width: 100%;
    justify-content: center;
  }
}
</style>