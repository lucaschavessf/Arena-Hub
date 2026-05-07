<template>
  <div class="page">
    <AppNavbar />

    <main class="main-perfil">
      <div class="perfil-container">
        <div class="breadcrumb">
          <router-link to="/" class="breadcrumb-link">Home</router-link>
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
          <span class="breadcrumb-current">Meu Perfil</span>
        </div>

        <div class="perfil-grid">
          <div class="perfil-coluna esquerda">
            <div class="perfil-card foto-card">
              <div class="foto-area">
                <div class="avatar-circle">
                  <span class="avatar-iniciais">{{ usuario.nome.charAt(0) }}</span>
                </div>
                <button class="btn-editar-foto" @click="simularUpload">
                  <svg
                    width="14"
                    height="14"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <path d="M17 3l4 4-7 7H10v-4l7-7z" />
                    <path d="M4 20h16" />
                  </svg>
                  Editar foto
                </button>
              </div>
              <div class="info-area">
                <h2>{{ usuario.nome }}</h2>
                <span class="membro-badge">{{ usuario.tipoUsuario }}</span>
                <div class="info-stats">
                  <div class="stat">
                    <span class="stat-value">{{ stats.ingressos }}</span>
                    <span class="stat-label">Ingressos</span>
                  </div>
                  <div class="stat">
                    <span class="stat-value">{{ stats.pontos }}</span>
                    <span class="stat-label">Pontos</span>
                  </div>
                  <div class="stat">
                    <span class="stat-value">{{ stats.eventos }}</span>
                    <span class="stat-label">Eventos</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="perfil-card pontos-card">
              <h3 class="card-title">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <circle cx="12" cy="12" r="10" />
                  <path d="M12 6v6l4 2" />
                </svg>
                Meus Pontos
              </h3>
              <div class="pontos-destaque">
                <span class="pontos-valor">{{ usuario.pontos }}</span>
                <span class="pontos-label">pontos acumulados</span>
              </div>

              <div class="pontos-info">
                <div class="info-box">
                  <svg
                    width="14"
                    height="14"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="1.8"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                  >
                    <path d="M17 3l4 4-7 7H10v-4l7-7z" />
                    <path d="M4 20h16" />
                  </svg>
                  <div class="info-text">
                    <strong>Como usar seus pontos?</strong>
                    <p>
                      Na hora de comprar seu ingresso, você poderá trocar seus pontos por descontos
                      diretamente no checkout!
                    </p>
                  </div>
                </div>
                <div class="info-box">
                  <svg
                    width="20"
                    height="20"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <polyline points="20 6 9 17 4 12" />
                  </svg>
                  <div class="info-text">
                    <strong>Benefícios</strong>
                    <p>Cada 100 pontos = R$ 1,00 de desconto. Acumule e economize!</p>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="perfil-coluna direita">
            <div class="perfil-card">
              <div class="card-header">
                <h3 class="card-title">Dados Pessoais</h3>
                <button class="btn-editar" @click="editandoDados = !editandoDados">
                  <svg
                    width="14"
                    height="14"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <path d="M17 3l4 4-7 7H10v-4l7-7z" />
                    <path d="M4 20h16" />
                  </svg>
                  {{ editandoDados ? 'Cancelar' : 'Editar' }}
                </button>
              </div>

              <div class="form-grid">
                <div class="form-field">
                  <label>Nome completo</label>
                  <input
                    type="text"
                    v-model="usuario.nome"
                    :disabled="!editandoDados"
                    class="input-field"
                  />
                </div>
                <div class="form-field">
                  <label>E-mail</label>
                  <input type="email" v-model="usuario.email" disabled class="input-field" />
                </div>
                <div class="form-field">
                  <label>Telefone</label>
                  <input
                    type="tel"
                    v-model="usuario.telefone"
                    :disabled="!editandoDados"
                    class="input-field"
                  />
                </div>
                <div class="form-field">
                  <label>Data de nascimento</label>
                  <input
                    type="date"
                    v-model="usuario.dataNascimento"
                    :disabled="!editandoDados"
                    class="input-field"
                  />
                </div>
                <div class="form-field">
                  <label>CPF</label>
                  <input type="text" v-model="usuario.cpf" disabled class="input-field" />
                </div>
                <div class="form-field">
                  <label>Cidade</label>
                  <input
                    type="text"
                    v-model="usuario.cidade"
                    :disabled="!editandoDados"
                    class="input-field"
                  />
                </div>
              </div>

              <div v-if="editandoDados" class="form-actions">
                <button class="btn-salvar" @click="salvarDados">
                  <svg
                    width="16"
                    height="16"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2.5"
                  >
                    <polyline points="20 6 9 17 4 12" />
                  </svg>
                  Salvar alterações
                </button>
              </div>
            </div>

            <div class="perfil-card">
              <div class="card-header">
                <h3 class="card-title">Endereço</h3>
                <button class="btn-editar" @click="editandoEndereco = !editandoEndereco">
                  <svg
                    width="14"
                    height="14"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <path d="M17 3l4 4-7 7H10v-4l7-7z" />
                    <path d="M4 20h16" />
                  </svg>
                  {{ editandoEndereco ? 'Cancelar' : 'Editar' }}
                </button>
              </div>

              <div class="form-grid">
                <div class="form-field full-width">
                  <label>Endereço</label>
                  <input
                    type="text"
                    v-model="usuario.endereco"
                    :disabled="!editandoEndereco"
                    class="input-field"
                  />
                </div>
                <div class="form-field">
                  <label>Número</label>
                  <input
                    type="text"
                    v-model="usuario.numero"
                    :disabled="!editandoEndereco"
                    class="input-field"
                  />
                </div>
                <div class="form-field">
                  <label>Complemento</label>
                  <input
                    type="text"
                    v-model="usuario.complemento"
                    :disabled="!editandoEndereco"
                    class="input-field"
                  />
                </div>
                <div class="form-field">
                  <label>Bairro</label>
                  <input
                    type="text"
                    v-model="usuario.bairro"
                    :disabled="!editandoEndereco"
                    class="input-field"
                  />
                </div>
                <div class="form-field">
                  <label>CEP</label>
                  <input
                    type="text"
                    v-model="usuario.cep"
                    :disabled="!editandoEndereco"
                    class="input-field"
                  />
                </div>
              </div>

              <div v-if="editandoEndereco" class="form-actions">
                <button class="btn-salvar" @click="salvarEndereco">
                  <svg
                    width="16"
                    height="16"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2.5"
                  >
                    <polyline points="20 6 9 17 4 12" />
                  </svg>
                  Salvar endereço
                </button>
              </div>
            </div>

            <div class="perfil-card">
              <h3 class="card-title">Alterar Senha</h3>

              <div class="form-grid">
                <div class="form-field">
                  <label>Senha atual</label>
                  <input type="password" v-model="senhaAtual" class="input-field" />
                </div>
                <div class="form-field">
                  <label>Nova senha</label>
                  <input type="password" v-model="novaSenha" class="input-field" />
                </div>
                <div class="form-field">
                  <label>Confirmar nova senha</label>
                  <input type="password" v-model="confirmarSenha" class="input-field" />
                </div>
              </div>

              <button class="btn-senha" @click="alterarSenha">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2" />
                  <path d="M7 11V7a5 5 0 0 1 10 0v4" />
                </svg>
                Atualizar senha
              </button>
            </div>

            <div class="perfil-card">
              <h3 class="card-title">Preferências de Notificação</h3>

              <div class="preferencias-list">
                <label class="preferencia-item">
                  <input type="checkbox" v-model="preferencias.emailPromocoes" />
                  <span>Receber ofertas e promoções por e-mail</span>
                </label>
                <label class="preferencia-item">
                  <input type="checkbox" v-model="preferencias.emailEventos" />
                  <span>Receber novidades sobre eventos</span>
                </label>
                <label class="preferencia-item">
                  <input type="checkbox" v-model="preferencias.whatsapp" />
                  <span>Receber mensagens no WhatsApp</span>
                </label>
                <label class="preferencia-item">
                  <input type="checkbox" v-model="preferencias.sms" />
                  <span>Receber SMS com confirmações</span>
                </label>
              </div>

              <button class="btn-salvar-preferencias" @click="salvarPreferencias">
                Salvar preferências
              </button>
            </div>
          </div>
        </div>
      </div>
    </main>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const editandoDados = ref(false)
const editandoEndereco = ref(false)
const senhaAtual = ref('')
const novaSenha = ref('')
const confirmarSenha = ref('')

const usuario = ref({
  nome: 'João Silva',
  email: 'joao.silva@email.com',
  telefone: '(81) 99999-8888',
  dataNascimento: '1990-05-15',
  cpf: '123.456.789-00',
  cidade: 'Recife',
  tipoUsuario: 'Fã',
  pontos: 750,
  endereco: 'Rua da Aurora',
  numero: '123',
  complemento: 'Apto 45',
  bairro: 'Boa Vista',
  cep: '50050-000',
})

const stats = ref({
  ingressos: 12,
  pontos: 750,
  eventos: 8,
})

const preferencias = reactive({
  emailPromocoes: true,
  emailEventos: true,
  whatsapp: false,
  sms: false,
})

function simularUpload() {
  alert('Funcionalidade de upload de foto em desenvolvimento')
}

function salvarDados() {
  editandoDados.value = false
  alert('Dados atualizados com sucesso!')
}

function salvarEndereco() {
  editandoEndereco.value = false
  alert('Endereço atualizado com sucesso!')
}

function alterarSenha() {
  if (!senhaAtual.value) {
    alert('Digite sua senha atual')
    return
  }
  if (novaSenha.value.length < 6) {
    alert('A nova senha deve ter no mínimo 6 caracteres')
    return
  }
  if (novaSenha.value !== confirmarSenha.value) {
    alert('As senhas não coincidem')
    return
  }

  alert('Senha alterada com sucesso!')
  senhaAtual.value = ''
  novaSenha.value = ''
  confirmarSenha.value = ''
}

function salvarPreferencias() {
  alert('Preferências salvas com sucesso!')
}
</script>

<style scoped>
.page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: #0a0e17;
}

.main-perfil {
  flex: 1;
  padding: 40px 20px;
}

.perfil-container {
  max-width: 1200px;
  margin: 0 auto;
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 28px;
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

.perfil-grid {
  display: grid;
  grid-template-columns: 360px 1fr;
  gap: 24px;
}

.perfil-coluna {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.perfil-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 24px;
}

.foto-card {
  text-align: center;
}

.foto-area {
  margin-bottom: 16px;
}

.avatar-circle {
  width: 120px;
  height: 120px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto 12px;
}

.avatar-iniciais {
  font-size: 3rem;
  font-weight: 800;
  color: #0a0e17;
}

.btn-editar-foto {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 6px 12px;
  border-radius: 8px;
  color: #c9a84c;
  font-size: 0.7rem;
  font-weight: 600;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  margin-top: 8px;
}

.info-area h2 {
  font-size: 1.3rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 4px;
}

.membro-badge {
  display: inline-block;
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
  border: 1px solid rgba(201, 168, 76, 0.3);
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 600;
  margin-bottom: 16px;
}

.info-stats {
  display: flex;
  justify-content: center;
  gap: 24px;
}

.stat {
  text-align: center;
}

.stat-value {
  display: block;
  font-size: 1.2rem;
  font-weight: 800;
  color: #c9a84c;
}

.stat-label {
  font-size: 0.7rem;
  color: #6b7280;
}

.pontos-destaque {
  text-align: center;
  padding: 16px;
  background: rgba(201, 168, 76, 0.05);
  border-radius: 16px;
  margin-bottom: 20px;
}

.pontos-valor {
  display: block;
  font-size: 2.5rem;
  font-weight: 800;
  color: #c9a84c;
}

.pontos-label {
  font-size: 0.8rem;
  color: #8e9aaf;
}

.progresso-proximo-nivel {
  margin-bottom: 20px;
}

.nivel-atual {
  display: flex;
  justify-content: space-between;
  font-size: 0.8rem;
  color: #b0b8c5;
  margin-bottom: 8px;
}

.progress-bar {
  height: 6px;
  background: #1a2233;
  border-radius: 10px;
  overflow: hidden;
  margin-bottom: 8px;
}

.progress-fill {
  height: 100%;
  background: linear-gradient(90deg, #c9a84c, #d4af37);
  border-radius: 10px;
  transition: width 0.3s ease;
}

.proximo-nivel {
  font-size: 0.7rem;
  color: #6b7280;
}

.pontos-info {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.info-box {
  display: flex;
  gap: 12px;
  padding: 12px;
  background: rgba(201, 168, 76, 0.05);
  border-radius: 12px;
  border-left: 3px solid #c9a84c;
}

.info-box svg {
  flex-shrink: 0;
  color: #c9a84c;
}

.info-text {
  flex: 1;
}

.info-text strong {
  display: block;
  font-size: 0.85rem;
  color: #c9a84c;
  margin-bottom: 4px;
}

.info-text p {
  font-size: 0.75rem;
  color: #b0b8c5;
  margin: 0;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 12px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.card-title {
  font-size: 0.9rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
  letter-spacing: 1px;
  display: flex;
  align-items: center;
  gap: 8px;
  margin: 0 0 5px 0;
}

.btn-editar {
  background: transparent;
  border: none;
  color: #8e9aaf;
  font-size: 0.7rem;
  font-weight: 600;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 4px;
  transition: color 0.2s;
}

.btn-editar:hover {
  color: #c9a84c;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.form-field {
  display: flex;
  flex-direction: column;
  gap: 6px;
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
  padding: 10px 14px;
  color: white;
  font-size: 0.85rem;
  outline: none;
  transition: all 0.3s ease;
}

.input-field:focus {
  border-color: #c9a84c;
  box-shadow: 0 0 0 3px rgba(201, 168, 76, 0.1);
}

.input-field:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.form-actions {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.btn-salvar {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 10px 24px;
  border-radius: 10px;
  color: #0a0e17;
  font-weight: 700;
  font-size: 0.85rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s ease;
}

.btn-salvar:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.3);
}

.btn-senha {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 12px 20px;
  border-radius: 10px;
  color: #c9a84c;
  font-weight: 600;
  font-size: 0.85rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s ease;
  margin-top: 20px;
  width: 100%;
  justify-content: center;
}

.btn-senha:hover {
  background: rgba(201, 168, 76, 0.1);
  border-color: #c9a84c;
}

.preferencias-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 20px;
}

.preferencia-item {
  display: flex;
  align-items: center;
  gap: 12px;
  cursor: pointer;
  color: #e0e0e0;
  font-size: 0.9rem;
}

.preferencia-item input {
  width: 18px;
  height: 18px;
  cursor: pointer;
  accent-color: #c9a84c;
}

.btn-salvar-preferencias {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 10px 20px;
  border-radius: 10px;
  color: #c9a84c;
  font-weight: 600;
  font-size: 0.85rem;
  cursor: pointer;
  width: 100%;
  transition: all 0.3s ease;
}

.btn-salvar-preferencias:hover {
  background: rgba(201, 168, 76, 0.1);
  border-color: #c9a84c;
}

@media (max-width: 768px) {
  .perfil-grid {
    grid-template-columns: 1fr;
  }

  .form-grid {
    grid-template-columns: 1fr;
  }

  .form-field.full-width {
    grid-column: span 1;
  }

  .main-perfil {
    padding: 24px 16px;
  }
}
</style>
