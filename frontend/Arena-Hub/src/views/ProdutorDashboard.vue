<template>
  <div class="page">
    <AppNavbar />

    <main class="main-produtor">
      <div class="produtor-container">
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
          <span class="breadcrumb-current">Painel do Produtor</span>
        </div>

        <header class="produtor-header">
          <div class="header-info">
            <h1>Painel do Produtor</h1>
            <p>Gerencie seus eventos e solicitações</p>
          </div>
          <button class="btn-novo-evento" @click="abrirModalNovoEvento">
            <svg
              width="18"
              height="18"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <line x1="12" y1="5" x2="12" y2="19" />
              <line x1="5" y1="12" x2="19" y2="12" />
            </svg>
            Novo Evento
          </button>
        </header>

        <section class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon pendentes">
              <svg
                width="22"
                height="22"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <circle cx="12" cy="12" r="10" />
                <path d="M12 6v6l4 2" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Solicitações</span>
              <div class="stat-value">{{ stats.pendentes }}</div>
              <span class="stat-sub">Aguardando análise</span>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon aprovados">
              <svg
                width="22"
                height="22"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <polyline points="20 6 9 17 4 12" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Eventos Aprovados</span>
              <div class="stat-value">{{ stats.aprovados }}</div>
              <span class="stat-sub">Eventos confirmados</span>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon realizados">
              <svg
                width="22"
                height="22"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <rect x="3" y="4" width="18" height="18" rx="2" ry="2" />
                <line x1="16" y1="2" x2="16" y2="6" />
                <line x1="8" y1="2" x2="8" y2="6" />
                <line x1="3" y1="10" x2="21" y2="10" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Realizados</span>
              <div class="stat-value">{{ stats.realizados }}</div>
              <span class="stat-sub">Eventos concluídos</span>
            </div>
          </div>

          <div class="stat-card">
            <div class="stat-icon publico">
              <svg
                width="22"
                height="22"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z" />
                <circle cx="12" cy="12" r="3" />
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Público Total</span>
              <div class="stat-value">{{ stats.publicoTotal }}</div>
              <span class="stat-sub">Espectadores</span>
            </div>
          </div>
        </section>

        <div class="tabs-container">
          <button
            class="tab-btn"
            :class="{ active: tabAtivo === 'solicitacoes' }"
            @click="tabAtivo = 'solicitacoes'"
          >
            <svg
              width="16"
              height="16"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
            >
              <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z" />
              <polyline points="14 2 14 8 20 8" />
            </svg>
            Minhas Solicitações
            <span class="tab-count">{{ solicitacoes.length }}</span>
          </button>
          <button
            class="tab-btn"
            :class="{ active: tabAtivo === 'eventos' }"
            @click="tabAtivo = 'eventos'"
          >
            <svg
              width="16"
              height="16"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
            >
              <rect x="3" y="4" width="18" height="18" rx="2" ry="2" />
              <line x1="16" y1="2" x2="16" y2="6" />
              <line x1="8" y1="2" x2="8" y2="6" />
              <line x1="3" y1="10" x2="21" y2="10" />
            </svg>
            Meus Eventos
            <span class="tab-count">{{ eventos.length }}</span>
          </button>
          <button
            class="tab-btn"
            :class="{ active: tabAtivo === 'perfil' }"
            @click="tabAtivo = 'perfil'"
          >
            <svg
              width="16"
              height="16"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
            >
              <circle cx="12" cy="8" r="4" />
              <path d="M4 20c0-4 3.6-7 8-7s8 3 8 7" />
            </svg>
            Meu Perfil
          </button>
        </div>

        <div class="tab-content">
          <div v-if="tabAtivo === 'solicitacoes'" class="solicitacoes-list">
            <div v-for="solicitacao in solicitacoes" :key="solicitacao.id" class="solicitacao-card">
              <div class="solicitacao-status" :class="`status-${solicitacao.status}`">
                {{ getStatusLabel(solicitacao.status) }}
              </div>

              <div class="solicitacao-header">
                <div class="solicitacao-icon">
                  <img
                    v-if="solicitacao.imagem"
                    :src="solicitacao.imagem"
                    :alt="solicitacao.nomeEvento"
                    class="solicitacao-img"
                  />
                  <svg
                    v-else
                    width="32"
                    height="32"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="1.5"
                  >
                    <path d="M20 12V8H4v16h16V20M12 12v8M8 12v4M16 12v4" />
                  </svg>
                </div>
                <div class="solicitacao-info">
                  <h3>{{ solicitacao.nomeEvento }}</h3>
                  <p>Enviado em {{ formatarData(solicitacao.dataEnvio) }}</p>
                </div>
              </div>

              <div class="solicitacao-detalhes">
                <div class="detalhe-item">
                  <svg
                    width="14"
                    height="14"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <circle cx="12" cy="12" r="10" />
                    <polyline points="12 6 12 12 16 14" />
                  </svg>
                  <span>{{ formatarData(solicitacao.dataEvento) }}</span>
                </div>
                <div class="detalhe-item">
                  <svg
                    width="14"
                    height="14"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <circle cx="12" cy="12" r="10" />
                    <path d="M12 6v6l4 2" />
                  </svg>
                  <span>{{ solicitacao.publicoEstimado }} pessoas</span>
                </div>
                <div class="detalhe-item">
                  <svg
                    width="14"
                    height="14"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <rect x="3" y="3" width="18" height="18" rx="2" />
                  </svg>
                  <span>{{ solicitacao.espacos.join(', ') }}</span>
                </div>
              </div>

              <div class="solicitacao-footer">
                <button class="btn-ver" @click="verSolicitacao(solicitacao)">Ver detalhes</button>
                <div
                  v-if="solicitacao.status === 'reprovado' && solicitacao.motivo"
                  class="feedback-motivo"
                >
                  <svg
                    width="14"
                    height="14"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <circle cx="12" cy="12" r="10" />
                    <line x1="12" y1="8" x2="12" y2="12" />
                    <line x1="12" y1="16" x2="12.01" y2="16" />
                  </svg>
                  <span>Motivo: {{ solicitacao.motivo }}</span>
                </div>
              </div>
            </div>

            <div v-if="solicitacoes.length === 0" class="empty-state">
              <svg
                width="64"
                height="64"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="1.5"
              >
                <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z" />
                <polyline points="14 2 14 8 20 8" />
              </svg>
              <h3>Nenhuma solicitação</h3>
              <p>Clique em "Novo Evento" para começar</p>
            </div>
          </div>

          <div v-if="tabAtivo === 'eventos'" class="eventos-list">
            <div v-for="evento in eventos" :key="evento.id" class="evento-card">
              <div class="evento-status" :class="`status-${evento.status}`">
                {{ getEventoStatusLabel(evento.status) }}
              </div>

              <div class="evento-header">
                <div class="evento-imagem">
                  <img
                    :src="
                      evento.imagem ||
                      'https://images.unsplash.com/photo-1540039155733-5bb30b53aa14?w=100&h=100&fit=crop'
                    "
                    :alt="evento.nome"
                  />
                </div>
                <div class="evento-info">
                  <h3>{{ evento.nome }}</h3>
                  <div class="evento-meta">
                    <span>{{ formatarData(evento.data) }}</span>
                    <span>{{ evento.local }}</span>
                  </div>
                </div>
              </div>

              <div class="evento-stats">
                <div class="stat-item">
                  <span class="stat-label">Ingressos Vendidos</span>
                  <span class="stat-value">{{ evento.ingressosVendidos }}</span>
                </div>
                <div class="stat-item">
                  <span class="stat-label">Ocupação</span>
                  <span class="stat-value">{{ evento.ocupacao }}%</span>
                </div>
                <div class="stat-item">
                  <span class="stat-label">Receita</span>
                  <span class="stat-value">{{ formatCurrency(evento.receita) }}</span>
                </div>
              </div>

              <div class="evento-progress">
                <div class="progress-bar">
                  <div class="progress-fill" :style="{ width: evento.ocupacao + '%' }"></div>
                </div>
              </div>

              <div class="evento-actions">
                <button class="btn-dashboard" @click="verDashboardEvento(evento)">
                  <svg
                    width="16"
                    height="16"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <circle cx="12" cy="12" r="3" />
                    <path
                      d="M22 12c-2.667 4.667-6 7-10 7s-7.333-2.333-10-7c2.667-4.667 6-7 10-7s7.333 2.333 10 7z"
                    />
                  </svg>
                  Dashboard
                </button>
                <button class="btn-ingressos" @click="gerenciarIngressos(evento)">
                  <svg
                    width="16"
                    height="16"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <path
                      d="M20 12V8H6a2 2 0 0 1-2-2c0-1.5 1.5-2 2-2h12M4 17v-2a2 2 0 0 1 2-2h14v-2"
                    />
                    <path d="M16 22h2a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2h-2" />
                  </svg>
                  Ingressos
                </button>
              </div>
            </div>

            <div v-if="eventos.length === 0" class="empty-state">
              <svg
                width="64"
                height="64"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="1.5"
              >
                <rect x="3" y="4" width="18" height="18" rx="2" ry="2" />
                <line x1="16" y1="2" x2="16" y2="6" />
                <line x1="8" y1="2" x2="8" y2="6" />
                <line x1="3" y1="10" x2="21" y2="10" />
              </svg>
              <h3>Nenhum evento aprovado</h3>
              <p>Seus eventos aparecerão aqui após a aprovação</p>
            </div>
          </div>

          <div v-if="tabAtivo === 'perfil'" class="perfil-content">
            <div class="perfil-card">
              <div class="perfil-header">
                <div class="avatar-large">
                  <span class="avatar-iniciais">{{ produtor.nome.charAt(0) }}</span>
                </div>
                <div class="perfil-titles">
                  <h2>{{ produtor.nome }}</h2>
                  <p>{{ produtor.email }}</p>
                  <span class="produtor-badge">Produtor de Eventos</span>
                </div>
              </div>

              <div class="perfil-form">
                <div class="form-row">
                  <div class="form-field">
                    <label>Nome da Empresa</label>
                    <input type="text" v-model="produtor.empresa" class="input-field" />
                  </div>
                  <div class="form-field">
                    <label>CNPJ/CPF</label>
                    <input type="text" v-model="produtor.documento" class="input-field" />
                  </div>
                </div>
                <div class="form-row">
                  <div class="form-field">
                    <label>Telefone</label>
                    <input type="tel" v-model="produtor.telefone" class="input-field" />
                  </div>
                  <div class="form-field">
                    <label>Website</label>
                    <input type="url" v-model="produtor.website" class="input-field" />
                  </div>
                </div>
                <div class="form-field">
                  <label>Biografia</label>
                  <textarea
                    v-model="produtor.bio"
                    rows="4"
                    class="input-field"
                    placeholder="Conte um pouco sobre sua trajetória..."
                  ></textarea>
                </div>
                <button class="btn-salvar-perfil" @click="salvarPerfil">Salvar alterações</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <AppFooter />

    <div v-if="modalNovoEvento" class="modal-overlay" @click.self="fecharModalNovoEvento">
      <div class="modal-container modal-large">
        <div class="modal-header">
          <h3>Solicitar Novo Evento</h3>
          <button class="modal-fechar" @click="fecharModalNovoEvento">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <line x1="18" y1="6" x2="6" y2="18" />
              <line x1="6" y1="6" x2="18" y2="18" />
            </svg>
          </button>
        </div>
        <div class="modal-body">
          <div class="form-row">
            <div class="form-field">
              <label>Nome do Evento *</label>
              <input type="text" v-model="novoEvento.nome" class="input-field" />
            </div>
            <div class="form-field">
              <label>Categoria *</label>
              <select v-model="novoEvento.categoria" class="input-field">
                <option value="">Selecione...</option>
                <option value="Show">Show</option>
                <option value="Esportes">Esportes</option>
                <option value="Corporativo">Corporativo</option>
                <option value="Teatro">Teatro</option>
                <option value="Comédia">Comédia</option>
              </select>
            </div>
          </div>

          <div class="form-field">
            <label>Descrição *</label>
            <textarea
              v-model="novoEvento.descricao"
              rows="4"
              class="input-field"
              placeholder="Descreva detalhadamente o evento..."
            ></textarea>
          </div>

          <div class="form-field">
            <label>Imagem do Evento</label>
            <div
              class="image-upload-area"
              @click="uploadImagem.click()"
              v-if="!novoEvento.imagemPreview"
            >
              <svg
                width="48"
                height="48"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="1.5"
              >
                <rect x="2" y="3" width="20" height="18" rx="2" ry="2" />
                <circle cx="9" cy="9" r="2" />
                <path d="M21 15l-5-4-3 3-4-4-5 5" />
              </svg>
              <span>Clique para adicionar imagem</span>
              <small>Formatos: JPG, PNG (max 5MB)</small>
            </div>
            <div class="image-preview-area" v-else>
              <img :src="novoEvento.imagemPreview" alt="Preview do evento" />
              <button type="button" class="btn-remove-image" @click.stop="removerImagem">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2.5"
                >
                  <line x1="18" y1="6" x2="6" y2="18" />
                  <line x1="6" y1="6" x2="18" y2="18" />
                </svg>
                Remover
              </button>
            </div>
            <input
              type="file"
              ref="uploadImagem"
              style="display: none"
              accept="image/jpeg,image/png,image/jpg"
              @change="handleImagemUpload"
            />
          </div>

          <div class="form-row">
            <div class="form-field">
              <label>Data do Evento *</label>
              <input type="date" v-model="novoEvento.data" class="input-field" />
            </div>
            <div class="form-field">
              <label>Horário *</label>
              <input type="time" v-model="novoEvento.horario" class="input-field" />
            </div>
          </div>

          <div class="form-row">
            <div class="form-field">
              <label>Horário de Término</label>
              <input type="time" v-model="novoEvento.horarioTermino" class="input-field" />
            </div>
            <div class="form-field">
              <label>Público Estimado *</label>
              <input
                type="number"
                v-model="novoEvento.publicoEstimado"
                class="input-field"
                placeholder="Número de pessoas"
              />
            </div>
          </div>

          <div class="form-field">
            <label>Espaços Solicitados *</label>
            <div class="espacos-grid-select">
              <div
                v-for="espaco in espacosDisponiveis"
                :key="espaco.id"
                class="espaco-select-card"
                :class="{ selected: novoEvento.espacos.some((e) => e.id === espaco.id) }"
                @click="toggleEspaco(espaco)"
              >
                <div class="espaco-select-header">
                  <span class="espaco-icon">{{ espaco.icon }}</span>
                  <div class="espaco-info">
                    <h4>{{ espaco.nome }}</h4>
                    <small>{{ espaco.descricao }}</small>
                  </div>
                </div>
                <div class="espaco-pricing">
                  <span class="price-badge" v-if="espaco.tipoCobranca === 'evento'">
                    R$ {{ formatCurrencyNumber(espaco.precoEvento) }} / evento
                  </span>
                  <span class="price-badge" v-else-if="espaco.tipoCobranca === 'hora'">
                    R$ {{ formatCurrencyNumber(espaco.precoHora) }} / hora
                  </span>
                  <span class="price-badge" v-else>
                    R$ {{ formatCurrencyNumber(espaco.precoDiaria) }} / diária
                  </span>
                  <span class="capacity-badge"
                    >🎯 {{ espaco.capacidade.toLocaleString() }} pessoas</span
                  >
                </div>
                <button
                  type="button"
                  class="btn-detalhes-espaco"
                  @click.stop="verDetalhesEspaco(espaco)"
                >
                  <svg
                    width="16"
                    height="16"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <circle cx="12" cy="12" r="3" />
                    <path
                      d="M22 12c-2.667 4.667-6 7-10 7s-7.333-2.333-10-7c2.667-4.667 6-7 10-7s7.333 2.333 10 7z"
                    />
                  </svg>
                </button>
              </div>
            </div>
          </div>

          <div v-if="novoEvento.espacos.length > 0" class="espacos-resumo">
            <h4>📋 Resumo dos Espaços Selecionados</h4>
            <div class="resumo-list">
              <div v-for="espaco in novoEvento.espacos" :key="espaco.id" class="resumo-item">
                <div class="resumo-info">
                  <span class="resumo-icon">{{ espaco.icon }}</span>
                  <div>
                    <strong>{{ espaco.nome }}</strong>
                    <small>{{ espaco.descricao }}</small>
                  </div>
                </div>
                <div class="resumo-valores">
                  <div class="resumo-preco">
                    <span>{{ formatarTipoCobranca(espaco.tipoCobranca) }}</span>
                    <strong>R$ {{ formatCurrencyNumber(obterPrecoEspaco(espaco)) }}</strong>
                  </div>
                  <button
                    type="button"
                    class="btn-remover-espaco"
                    @click="removerEspaco(espaco.id)"
                  >
                    <svg
                      width="14"
                      height="14"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2.5"
                    >
                      <line x1="18" y1="6" x2="6" y2="18" />
                      <line x1="6" y1="6" x2="18" y2="18" />
                    </svg>
                  </button>
                </div>
              </div>
            </div>
            <div class="resumo-total">
              <span>💰 Valor Total Estimado:</span>
              <strong>R$ {{ formatCurrencyNumber(calcularTotalEspacos) }}</strong>
            </div>
          </div>

          <div class="form-row">
            <div class="form-field">
              <label>Classificação Indicativa</label>
              <select v-model="novoEvento.classificacao" class="input-field">
                <option value="Livre">Livre</option>
                <option value="10">10 anos</option>
                <option value="12">12 anos</option>
                <option value="14">14 anos</option>
                <option value="16">16 anos</option>
                <option value="18">18 anos</option>
              </select>
            </div>
            <div class="form-field">
              <label>Menores Acompanhados</label>
              <select v-model="novoEvento.menoresAcompanhados" class="input-field">
                <option :value="true">Sim</option>
                <option :value="false">Não</option>
              </select>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn-modal-cancelar" @click="fecharModalNovoEvento">Cancelar</button>
          <button class="btn-modal-confirmar" @click="enviarSolicitacao">Enviar Solicitação</button>
        </div>
      </div>
    </div>

    <div v-if="modalEspacoDetalhes" class="modal-overlay" @click.self="fecharModalEspacoDetalhes">
      <div class="modal-container">
        <div class="modal-header">
          <h3>{{ espacoSelecionado?.nome }}</h3>
          <button class="modal-fechar" @click="fecharModalEspacoDetalhes">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <line x1="18" y1="6" x2="6" y2="18" />
              <line x1="6" y1="6" x2="18" y2="18" />
            </svg>
          </button>
        </div>
        <div class="modal-body">
          <div class="espaco-detalhes">
            <div class="detalhe-header">
              <span class="espaco-icon-large">{{ espacoSelecionado?.icon }}</span>
              <div>
                <h4>{{ espacoSelecionado?.nome }}</h4>
                <p class="descricao">{{ espacoSelecionado?.descricao }}</p>
              </div>
            </div>

            <div class="info-detalhe">
              <div class="info-row">
                <span>🎯 Capacidade:</span>
                <strong>{{ espacoSelecionado?.capacidade.toLocaleString() }} pessoas</strong>
              </div>
              <div class="info-row">
                <span>📋 Tipo de Cobrança:</span>
                <strong class="tipo-badge">{{
                  formatarTipoCobranca(espacoSelecionado?.tipoCobranca)
                }}</strong>
              </div>
            </div>

            <div class="precos-detalhe">
              <h4>💰 Valores</h4>
              <div v-if="espacoSelecionado?.tipoCobranca === 'evento'" class="preco-item">
                <span>Preço por Evento:</span>
                <strong>R$ {{ formatCurrencyNumber(espacoSelecionado?.precoEvento) }}</strong>
              </div>
              <div v-else-if="espacoSelecionado?.tipoCobranca === 'hora'" class="preco-item">
                <span>Preço por Hora:</span>
                <strong>R$ {{ formatCurrencyNumber(espacoSelecionado?.precoHora) }}</strong>
              </div>
              <div v-else class="preco-item">
                <span>Preço por Diária:</span>
                <strong>R$ {{ formatCurrencyNumber(espacoSelecionado?.precoDiaria) }}</strong>
              </div>
            </div>

            <div class="incluido-detalhe">
              <h4>✅ O que está incluído</h4>
              <ul>
                <li>🧹 Limpeza e conservação</li>
                <li>💡 Iluminação básica</li>
                <li>🛡️ Segurança no local</li>
                <li>🚛 Acesso para carga e descarga</li>
                <li>🚻 Banheiros exclusivos</li>
                <li>🔌 Pontos de energia</li>
              </ul>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button
            class="btn-modal-confirmar"
            @click="selecionarEspaco(); fecharModalEspacoDetalhes()"
            >
            Selecionar este espaço
            </button>
        </div>
      </div>
    </div>

    <div v-if="modalSolicitacao" class="modal-overlay" @click.self="fecharModalSolicitacao">
      <div class="modal-container">
        <div class="modal-header">
          <h3>Detalhes da Solicitação</h3>
          <button class="modal-fechar" @click="fecharModalSolicitacao">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2.5"
            >
              <line x1="18" y1="6" x2="6" y2="18" />
              <line x1="6" y1="6" x2="18" y2="18" />
            </svg>
          </button>
        </div>
        <div class="modal-body">
          <div class="detalhe-status" :class="`status-${solicitacaoSelecionada?.status}`">
            Status: {{ getStatusLabel(solicitacaoSelecionada?.status) }}
          </div>

          <div class="info-section">
            <h4>Informações do Evento</h4>
            <div class="info-grid">
              <div><strong>Nome:</strong> {{ solicitacaoSelecionada?.nomeEvento }}</div>
              <div><strong>Categoria:</strong> {{ solicitacaoSelecionada?.categoria }}</div>
              <div>
                <strong>Data:</strong> {{ formatarData(solicitacaoSelecionada?.dataEvento) }}
              </div>
              <div><strong>Horário:</strong> {{ solicitacaoSelecionada?.horario }}</div>
              <div>
                <strong>Público:</strong> {{ solicitacaoSelecionada?.publicoEstimado }} pessoas
              </div>
              <div><strong>Espaços:</strong> {{ solicitacaoSelecionada?.espacos?.join(', ') }}</div>
            </div>
          </div>

          <div class="info-section">
            <h4>Descrição</h4>
            <p>{{ solicitacaoSelecionada?.descricao }}</p>
          </div>

          <div
            v-if="solicitacaoSelecionada?.status === 'reprovado' && solicitacaoSelecionada?.motivo"
            class="info-section feedback"
          >
            <h4>Feedback do Administrador</h4>
            <div class="feedback-box">
              <svg
                width="20"
                height="20"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
              >
                <circle cx="12" cy="12" r="10" />
                <path d="M12 8v4M12 16h.01" />
              </svg>
              <span>{{ solicitacaoSelecionada?.motivo }}</span>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn-modal-fechar" @click="fecharModalSolicitacao">Fechar</button>
          <button
            v-if="solicitacaoSelecionada?.status === 'reprovado'"
            class="btn-modal-reenviar"
            @click="reenviarSolicitacao"
          >
            Reenviar com correções
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()
const tabAtivo = ref('solicitacoes')
const modalNovoEvento = ref(false)
const modalSolicitacao = ref(false)
const solicitacaoSelecionada = ref<any>(null)
const uploadImagem = ref<HTMLInputElement>()
const modalEspacoDetalhes = ref(false)
const espacoSelecionado = ref<any>(null)

const produtor = ref({
  nome: 'João Produtor',
  email: 'joao@produtora.com',
  empresa: 'Recife Eventos LTDA',
  documento: '12.345.678/0001-90',
  telefone: '(81) 99999-8888',
  website: 'www.recifeeventos.com.br',
  bio: 'Produtor de eventos com mais de 10 anos de experiência no mercado pernambucano.',
})

const stats = ref({
  pendentes: 2,
  aprovados: 3,
  realizados: 5,
  publicoTotal: '45.2k',
})

const solicitacoes = ref([
  {
    id: 1,
    nomeEvento: 'Festival de Jazz PE',
    categoria: 'Show',
    dataEvento: '2026-09-15',
    dataEnvio: '2026-04-01',
    horario: '20:00',
    publicoEstimado: 5000,
    espacos: ['Campo', 'Anel Inferior'],
    descricao: 'Festival de jazz com artistas nacionais e internacionais...',
    imagem: null,
    status: 'pendente',
    motivo: null,
  },
  {
    id: 2,
    nomeEvento: 'Copa Nordeste de Vôlei',
    categoria: 'Esportes',
    dataEvento: '2026-08-20',
    dataEnvio: '2026-03-15',
    horario: '14:00',
    publicoEstimado: 3000,
    espacos: ['Campo', 'Camarote'],
    descricao: 'Competição regional de vôlei...',
    imagem: null,
    status: 'aprovado',
    motivo: null,
  },
  {
    id: 3,
    nomeEvento: 'Show de Comédia',
    categoria: 'Comédia',
    dataEvento: '2026-06-10',
    dataEnvio: '2026-02-10',
    horario: '21:00',
    publicoEstimado: 1500,
    espacos: ['Teatro'],
    descricao: 'Show de stand-up comedy...',
    imagem: null,
    status: 'reprovado',
    motivo: 'Data indisponível para o período solicitado. Sugerimos reagendar para julho.',
  },
])

const eventos = ref([
  {
    id: 1,
    nome: 'Rock Nacional 2026',
    data: '2026-08-15',
    local: 'Arena Pernambuco',
    imagem: 'https://images.unsplash.com/photo-1540039155733-5bb30b53aa14?w=100&h=100&fit=crop',
    ingressosVendidos: 18500,
    ocupacao: 74,
    receita: 425000,
    status: 'aprovado',
  },
  {
    id: 2,
    nome: 'Recife Tech Summit',
    data: '2026-07-20',
    local: 'Arena Pernambuco',
    imagem: 'https://images.unsplash.com/photo-1540575467063-178a50c2df87?w=100&h=100&fit=crop',
    ingressosVendidos: 3200,
    ocupacao: 64,
    receita: 128000,
    status: 'aprovado',
  },
])

const novoEvento = ref({
  nome: '',
  categoria: '',
  descricao: '',
  data: '',
  horario: '',
  horarioTermino: '',
  publicoEstimado: '',
  espacos: [] as any[],
  classificacao: 'Livre',
  menoresAcompanhados: true,
  imagem: null as File | null,
  imagemPreview: ''
})

const espacosDisponiveis = ref([
  {
    id: 1,
    nome: 'Campo',
    descricao: 'Área principal para shows e eventos de grande porte',
    capacidade: 25000,
    tipoCobranca: 'evento',
    precoEvento: 50000,
    precoHora: 0,
    precoDiaria: 0,
    icon: '🏟️',
  },
  {
    id: 2,
    nome: 'Anel Inferior',
    descricao: 'Arquibancada coberta com excelente visibilidade',
    capacidade: 8000,
    tipoCobranca: 'evento',
    precoEvento: 30000,
    precoHora: 0,
    precoDiaria: 0,
    icon: '🪑',
  },
  {
    id: 3,
    nome: 'Anel Superior',
    descricao: 'Arquibancada superior com visão panorâmica',
    capacidade: 5000,
    tipoCobranca: 'evento',
    precoEvento: 20000,
    precoHora: 0,
    precoDiaria: 0,
    icon: '👆',
  },
  {
    id: 4,
    nome: 'Camarote',
    descricao: 'Espaço exclusivo com serviço diferenciado',
    capacidade: 500,
    tipoCobranca: 'evento',
    precoEvento: 15000,
    precoHora: 0,
    precoDiaria: 0,
    icon: '✨',
  },
  {
    id: 5,
    nome: 'Estacionamento',
    descricao: 'Área de estacionamento com 2000 vagas',
    capacidade: 2000,
    tipoCobranca: 'evento',
    precoEvento: 10000,
    precoHora: 0,
    precoDiaria: 0,
    icon: '🅿️',
  },
  {
    id: 6,
    nome: 'Sala de Imprensa',
    descricao: 'Espaço para coletivas e entrevistas',
    capacidade: 100,
    tipoCobranca: 'hora',
    precoEvento: 0,
    precoHora: 500,
    precoDiaria: 3000,
    icon: '📸',
  },
  {
    id: 7,
    nome: 'Sala VIP',
    descricao: 'Área premium para convidados especiais',
    capacidade: 200,
    tipoCobranca: 'evento',
    precoEvento: 8000,
    precoHora: 0,
    precoDiaria: 0,
    icon: '👑',
  },
  {
    id: 8,
    nome: 'Área de Alimentação',
    descricao: 'Espaço para food trucks e praça de alimentação',
    capacidade: 1000,
    tipoCobranca: 'diaria',
    precoEvento: 0,
    precoHora: 0,
    precoDiaria: 5000,
    icon: '🍔',
  },
])

function formatarData(data: string) {
  if (!data) return ''
  return new Date(data).toLocaleDateString('pt-BR')
}

function formatCurrency(value: number) {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(value)
}

function getStatusLabel(status: string) {
  const map: Record<string, string> = {
    pendente: 'Pendente',
    aprovado: 'Aprovado',
    reprovado: 'Reprovado',
  }
  return map[status] || status
}

function getEventoStatusLabel(status: string) {
  const map: Record<string, string> = {
    aprovado: 'Agendado',
    realizado: 'Realizado',
    cancelado: 'Cancelado',
  }
  return map[status] || status
}

function handleImagemUpload(event: Event) {
  const input = event.target as HTMLInputElement
  if (input.files && input.files[0]) {
    const file = input.files[0]
    novoEvento.value.imagem = file

    const reader = new FileReader()
    reader.onload = (e) => {
      novoEvento.value.imagemPreview = e.target?.result as string
    }
    reader.readAsDataURL(file)
  }
}

function removerImagem() {
  novoEvento.value.imagem = null
  novoEvento.value.imagemPreview = ''
  if (uploadImagem.value) {
    uploadImagem.value.value = ''
  }
}

function abrirModalNovoEvento() {
  novoEvento.value = {
    nome: '',
    categoria: '',
    descricao: '',
    data: '',
    horario: '',
    horarioTermino: '',
    publicoEstimado: '',
    espacos: [],
    classificacao: 'Livre',
    menoresAcompanhados: true,
    imagem: null,
    imagemPreview: '',
  }
  modalNovoEvento.value = true
}

function fecharModalNovoEvento() {
  modalNovoEvento.value = false
}

function enviarSolicitacao() {
  if (!novoEvento.value.nome || !novoEvento.value.categoria || !novoEvento.value.data) {
    alert('Preencha os campos obrigatórios')
    return
  }

  if (novoEvento.value.espacos.length === 0) {
    alert('Selecione pelo menos um espaço')
    return
  }

  let imagemUrl = ''
  if (novoEvento.value.imagemPreview) {
    imagemUrl = novoEvento.value.imagemPreview
  }

  solicitacoes.value.unshift({
    id: Date.now(),
    nomeEvento: novoEvento.value.nome,
    categoria: novoEvento.value.categoria,
    dataEvento: novoEvento.value.data,
    dataEnvio: new Date().toISOString().split('T')[0],
    horario: novoEvento.value.horario,
    horarioTermino: novoEvento.value.horarioTermino,
    publicoEstimado: Number(novoEvento.value.publicoEstimado),
    espacos: novoEvento.value.espacos.map((e) => e.nome),
    descricao: novoEvento.value.descricao,
    classificacao: novoEvento.value.classificacao,
    menoresAcompanhados: novoEvento.value.menoresAcompanhados,
    valorTotalEspacos: calcularTotalEspacos.value,
    imagem: imagemUrl,
    status: 'pendente',
    motivo: null,
  })

  fecharModalNovoEvento()
  alert(
    `Solicitação enviada com sucesso!\n\nValor total estimado: R$ ${formatCurrencyNumber(calcularTotalEspacos.value)}\n\nAguarde a análise do administrador.`,
  )
}

function verSolicitacao(solicitacao: any) {
  solicitacaoSelecionada.value = solicitacao
  modalSolicitacao.value = true
}

function fecharModalSolicitacao() {
  modalSolicitacao.value = false
  solicitacaoSelecionada.value = null
}

function reenviarSolicitacao() {
  fecharModalSolicitacao()
  abrirModalNovoEvento()
}

function verDashboardEvento(evento: any) {
  router.push(`/produtor/evento/${evento.id}`)
}

function gerenciarIngressos(evento: any) {
  router.push(`/produtor/evento/${evento.id}/ingressos`)
}

function toggleEspaco(espaco: any) {
  const index = novoEvento.value.espacos.findIndex((e) => e.id === espaco.id)
  if (index === -1) {
    novoEvento.value.espacos.push({
      ...espaco,
      quantidade: 1,
      horas: espaco.tipoCobranca === 'hora' ? 4 : null,
      diarias: espaco.tipoCobranca === 'diaria' ? 1 : null,
    })
  } else {
    novoEvento.value.espacos.splice(index, 1)
  }
}

function removerEspaco(espacoId: number) {
  novoEvento.value.espacos = novoEvento.value.espacos.filter((e) => e.id !== espacoId)
}

function obterPrecoEspaco(espaco: any): number {
  if (espaco.tipoCobranca === 'evento') return espaco.precoEvento
  if (espaco.tipoCobranca === 'hora') return espaco.precoHora * (espaco.horas || 4)
  return espaco.precoDiaria * (espaco.diarias || 1)
}

function formatarTipoCobranca(tipo: string): string {
  const map: Record<string, string> = {
    evento: 'Por Evento',
    hora: 'Por Hora',
    diaria: 'Por Diária',
  }
  return map[tipo] || tipo
}

function formatCurrencyNumber(value: number): string {
  return value.toLocaleString('pt-BR', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  })
}

function verDetalhesEspaco(espaco: any) {
  espacoSelecionado.value = espaco
  modalEspacoDetalhes.value = true
}

function fecharModalEspacoDetalhes() {
  modalEspacoDetalhes.value = false
  espacoSelecionado.value = null
}

function selecionarEspaco() {
  if (
    espacoSelecionado.value &&
    !novoEvento.value.espacos.some((e) => e.id === espacoSelecionado.value.id)
  ) {
    novoEvento.value.espacos.push({
      ...espacoSelecionado.value,
      quantidade: 1,
      horas: espacoSelecionado.value.tipoCobranca === 'hora' ? 4 : null,
      diarias: espacoSelecionado.value.tipoCobranca === 'diaria' ? 1 : null,
    })
  }
}

const calcularTotalEspacos = computed(() => {
  return novoEvento.value.espacos.reduce((total, espaco) => {
    let preco = 0
    if (espaco.tipoCobranca === 'evento') preco = espaco.precoEvento
    else if (espaco.tipoCobranca === 'hora') preco = espaco.precoHora * (espaco.horas || 4)
    else preco = espaco.precoDiaria * (espaco.diarias || 1)
    return total + preco
  }, 0)
})

function salvarPerfil() {
  alert('Perfil atualizado com sucesso!')
}

onMounted(() => {
  window.scrollTo(0, 0)
})
</script>

<style scoped>
.page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: #0a0e17;
}

.main-produtor {
  flex: 1;
  padding: 40px 20px;
}

.produtor-container {
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

.produtor-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 32px;
  flex-wrap: wrap;
  gap: 16px;
}

.header-info h1 {
  font-family: 'Bebas Neue', sans-serif;
  font-size: 2rem;
  letter-spacing: 1px;
  background: linear-gradient(135deg, #fff 0%, #e0e0e0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin-bottom: 4px;
}

.header-info p {
  color: #8e9aaf;
  font-size: 0.85rem;
}

.btn-novo-evento {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  color: #0a0e17;
  border: none;
  padding: 12px 24px;
  border-radius: 12px;
  font-weight: 700;
  font-size: 0.85rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s;
}

.btn-novo-evento:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(201, 168, 76, 0.3);
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  margin-bottom: 32px;
}

.stat-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  padding: 20px;
  border-radius: 16px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  display: flex;
  align-items: center;
  gap: 14px;
  transition: all 0.3s;
}

.stat-card:hover {
  transform: translateY(-3px);
  border-color: rgba(201, 168, 76, 0.15);
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.stat-icon.pendentes {
  background: rgba(245, 158, 11, 0.15);
}
.stat-icon.pendentes svg {
  color: #f59e0b;
}
.stat-icon.aprovados {
  background: rgba(16, 185, 129, 0.15);
}
.stat-icon.aprovados svg {
  color: #10b981;
}
.stat-icon.realizados {
  background: rgba(59, 130, 246, 0.15);
}
.stat-icon.realizados svg {
  color: #3b82f6;
}
.stat-icon.publico {
  background: rgba(201, 168, 76, 0.15);
}
.stat-icon.publico svg {
  color: #c9a84c;
}

.stat-info {
  flex: 1;
}

.stat-label {
  color: #8e9aaf;
  font-size: 0.7rem;
  text-transform: uppercase;
  letter-spacing: 0.8px;
  display: block;
  margin-bottom: 4px;
}

.stat-value {
  font-size: 1.6rem;
  font-weight: 800;
  line-height: 1.2;
}

.stat-sub {
  font-size: 0.65rem;
  color: #6b7280;
  display: block;
}

.tabs-container {
  display: flex;
  gap: 8px;
  margin-bottom: 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  padding-bottom: 12px;
}

.tab-btn {
  background: transparent;
  border: none;
  padding: 10px 20px;
  color: #8e9aaf;
  font-size: 0.85rem;
  font-weight: 600;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  border-radius: 10px;
  transition: all 0.3s;
}

.tab-btn:hover {
  color: #c9a84c;
  background: rgba(201, 168, 76, 0.05);
}

.tab-btn.active {
  color: #c9a84c;
  background: rgba(201, 168, 76, 0.1);
  border-bottom: 2px solid #c9a84c;
}

.tab-count {
  background: rgba(255, 255, 255, 0.1);
  padding: 2px 6px;
  border-radius: 10px;
  font-size: 0.7rem;
}

.tab-btn.active .tab-count {
  background: rgba(201, 168, 76, 0.2);
}

.tab-content {
  min-height: 400px;
}

.solicitacoes-list,
.eventos-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.solicitacao-card,
.evento-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 16px;
  padding: 20px;
  position: relative;
  transition: all 0.3s;
}

.solicitacao-card:hover,
.evento-card:hover {
  transform: translateY(-2px);
  border-color: rgba(201, 168, 76, 0.15);
}

.solicitacao-status,
.evento-status {
  position: absolute;
  top: 20px;
  right: 20px;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 700;
}

.status-pendente {
  background: rgba(245, 158, 11, 0.15);
  color: #f59e0b;
  border: 1px solid rgba(245, 158, 11, 0.3);
}
.status-aprovado {
  background: rgba(16, 185, 129, 0.15);
  color: #10b981;
  border: 1px solid rgba(16, 185, 129, 0.3);
}
.status-reprovado {
  background: rgba(239, 68, 68, 0.15);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}

.solicitacao-header,
.evento-header {
  display: flex;
  gap: 16px;
  margin-bottom: 16px;
}

.solicitacao-icon,
.evento-imagem {
  width: 56px;
  height: 56px;
  border-radius: 12px;
  background: rgba(201, 168, 76, 0.1);
  display: flex;
  align-items: center;
  justify-content: center;
  color: #c9a84c;
  overflow: hidden;
}

.solicitacao-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 12px;
}

.evento-imagen img {
  width: 100%;
  height: 100%;
  border-radius: 12px;
  object-fit: cover;
}

.solicitacao-info h3,
.evento-info h3 {
  font-size: 1rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 4px;
}

.solicitacao-info p,
.evento-info .evento-meta {
  font-size: 0.75rem;
  color: #8e9aaf;
}

.solicitacao-detalhes,
.evento-stats {
  display: flex;
  gap: 20px;
  margin-bottom: 16px;
  padding: 12px 0;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.detalhe-item,
.stat-item {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 0.8rem;
  color: #b0b8c5;
}

.solicitacao-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.btn-ver {
  background: rgba(201, 168, 76, 0.15);
  border: none;
  padding: 8px 16px;
  border-radius: 8px;
  color: #c9a84c;
  font-weight: 600;
  font-size: 0.8rem;
  cursor: pointer;
  transition: all 0.3s;
}

.btn-ver:hover {
  background: rgba(201, 168, 76, 0.25);
}

.feedback-motivo {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 0.7rem;
  color: #ef4444;
  background: rgba(239, 68, 68, 0.1);
  padding: 6px 12px;
  border-radius: 8px;
}

.evento-progress {
  margin-bottom: 16px;
}

.progress-bar {
  height: 6px;
  background: #1a2233;
  border-radius: 10px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background: linear-gradient(90deg, #c9a84c, #d4af37);
  border-radius: 10px;
}

.evento-actions {
  display: flex;
  gap: 12px;
}

.btn-dashboard,
.btn-ingressos {
  padding: 8px 16px;
  border-radius: 8px;
  font-weight: 600;
  font-size: 0.8rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: all 0.3s;
}

.btn-dashboard {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  color: #0a0e17;
}

.btn-dashboard:hover {
  transform: translateY(-2px);
}

.btn-ingressos {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  color: #c9a84c;
}

.btn-ingressos:hover {
  background: rgba(201, 168, 76, 0.1);
}

.perfil-content {
  max-width: 800px;
  margin: 0 auto;
}

.perfil-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  padding: 32px;
}

.perfil-header {
  display: flex;
  align-items: center;
  gap: 24px;
  margin-bottom: 32px;
  padding-bottom: 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.avatar-large {
  width: 100px;
  height: 100px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.avatar-iniciais {
  font-size: 2.5rem;
  font-weight: 800;
  color: #0a0e17;
}

.perfil-titles h2 {
  font-size: 1.3rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 4px;
}

.perfil-titles p {
  color: #8e9aaf;
  font-size: 0.85rem;
  margin-bottom: 8px;
}

.produtor-badge {
  display: inline-block;
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
  border: 1px solid rgba(201, 168, 76, 0.3);
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.7rem;
  font-weight: 600;
}

.perfil-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
}

.form-field {
  display: flex;
  flex-direction: column;
  gap: 6px;
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

.btn-salvar-perfil {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 12px 24px;
  border-radius: 10px;
  color: #0a0e17;
  font-weight: 700;
  cursor: pointer;
  transition: all 0.3s;
  align-self: flex-start;
}

.btn-salvar-perfil:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(201, 168, 76, 0.3);
}

.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.8);
  backdrop-filter: blur(8px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-container {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(201, 168, 76, 0.15);
  border-radius: 24px;
  width: 90%;
  max-width: 600px;
  max-height: 85vh;
  overflow-y: auto;
}

.modal-large {
  max-width: 800px;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.modal-header h3 {
  font-size: 1.2rem;
  font-weight: 700;
  color: #c9a84c;
}

.modal-fechar {
  background: transparent;
  border: none;
  color: #8e9aaf;
  cursor: pointer;
  transition: color 0.2s;
}

.modal-fechar:hover {
  color: #ef4444;
}

.modal-body {
  padding: 24px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  padding: 20px 24px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.btn-modal-cancelar,
.btn-modal-fechar {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 10px 20px;
  border-radius: 10px;
  color: #8e9aaf;
  font-weight: 600;
  cursor: pointer;
}

.btn-modal-confirmar {
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  border: none;
  padding: 10px 20px;
  border-radius: 10px;
  color: #0a0e17;
  font-weight: 700;
  cursor: pointer;
}

.btn-modal-reenviar {
  background: rgba(59, 130, 246, 0.15);
  border: 1px solid rgba(59, 130, 246, 0.3);
  padding: 10px 20px;
  border-radius: 10px;
  color: #3b82f6;
  font-weight: 600;
  cursor: pointer;
}

.image-upload-area {
  background: #1a2233;
  border: 2px dashed rgba(201, 168, 76, 0.3);
  border-radius: 16px;
  padding: 32px;
  text-align: center;
  cursor: pointer;
  transition: all 0.3s;
}

.image-upload-area:hover {
  border-color: #c9a84c;
  background: rgba(201, 168, 76, 0.05);
}

.image-upload-area svg {
  color: #c9a84c;
  margin-bottom: 12px;
}

.image-upload-area span {
  display: block;
  color: #e0e0e0;
  font-size: 0.9rem;
  margin-bottom: 8px;
}

.image-upload-area small {
  color: #6b7280;
  font-size: 0.7rem;
}

.image-preview-area {
  position: relative;
  border-radius: 16px;
  overflow: hidden;
  background: #1a2233;
}

.image-preview-area img {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.btn-remove-image {
  position: absolute;
  top: 12px;
  right: 12px;
  background: rgba(0, 0, 0, 0.7);
  border: none;
  padding: 8px 12px;
  border-radius: 8px;
  color: #ef4444;
  font-weight: 600;
  font-size: 0.7rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: all 0.3s;
}

.btn-remove-image:hover {
  background: #ef4444;
  color: white;
}

.espacos-checkbox {
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

.detalhe-status {
  padding: 10px;
  border-radius: 10px;
  text-align: center;
  font-weight: 700;
}

.info-section {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.info-section h4 {
  font-size: 0.85rem;
  font-weight: 700;
  color: #c9a84c;
  text-transform: uppercase;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.info-grid div {
  font-size: 0.85rem;
  color: #b0b8c5;
}

.info-grid strong {
  color: #e0e0e0;
}

.feedback-box {
  display: flex;
  align-items: center;
  gap: 10px;
  background: rgba(239, 68, 68, 0.1);
  padding: 12px;
  border-radius: 12px;
  color: #ef4444;
  font-size: 0.85rem;
}

.empty-state {
  text-align: center;
  padding: 60px 20px;
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border-radius: 20px;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.empty-state svg {
  color: #c9a84c;
  opacity: 0.4;
  margin-bottom: 16px;
}

.empty-state h3 {
  font-size: 1.1rem;
  font-weight: 600;
  color: #e0e0e0;
  margin-bottom: 8px;
}

.empty-state p {
  color: #6b7280;
}

.espacos-grid-select {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
  margin-top: 8px;
  max-height: 400px;
  overflow-y: auto;
  padding: 4px;
}

.espaco-select-card {
  background: #1a2233;
  border: 2px solid rgba(255, 255, 255, 0.06);
  border-radius: 16px;
  padding: 16px;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
}

.espaco-select-card:hover {
  border-color: rgba(201, 168, 76, 0.3);
  background: rgba(201, 168, 76, 0.05);
}

.espaco-select-card.selected {
  border-color: #c9a84c;
  background: rgba(201, 168, 76, 0.1);
  box-shadow: 0 0 0 2px rgba(201, 168, 76, 0.2);
}

.espaco-select-header {
  display: flex;
  gap: 12px;
  margin-bottom: 12px;
}

.espaco-icon {
  font-size: 28px;
}

.espaco-info h4 {
  font-size: 1rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 4px;
}

.espaco-info small {
  font-size: 0.7rem;
  color: #8e9aaf;
  display: block;
}

.espaco-pricing {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 8px;
  margin-top: 8px;
  padding-top: 8px;
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.price-badge {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
  padding: 4px 8px;
  border-radius: 8px;
  font-size: 0.7rem;
  font-weight: 600;
}

.capacity-badge {
  background: rgba(255, 255, 255, 0.05);
  color: #8e9aaf;
  padding: 4px 8px;
  border-radius: 8px;
  font-size: 0.7rem;
}

.btn-detalhes-espaco {
  position: absolute;
  top: 12px;
  right: 12px;
  background: rgba(255, 255, 255, 0.05);
  border: none;
  border-radius: 8px;
  padding: 6px;
  cursor: pointer;
  color: #8e9aaf;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-detalhes-espaco:hover {
  background: #c9a84c;
  color: #0a0e17;
}

.espacos-resumo {
  background: rgba(201, 168, 76, 0.05);
  border-radius: 16px;
  padding: 16px;
  margin-top: 16px;
}

.espacos-resumo h4 {
  font-size: 0.85rem;
  font-weight: 700;
  color: #c9a84c;
  margin-bottom: 12px;
}

.resumo-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 16px;
  max-height: 250px;
  overflow-y: auto;
}

.resumo-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #1a2233;
  border-radius: 12px;
  padding: 12px;
}

.resumo-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.resumo-icon {
  font-size: 24px;
}

.resumo-info strong {
  display: block;
  font-size: 0.85rem;
  color: #fff;
}

.resumo-info small {
  font-size: 0.7rem;
  color: #8e9aaf;
}

.resumo-valores {
  display: flex;
  align-items: center;
  gap: 12px;
}

.resumo-preco {
  text-align: right;
}

.resumo-preco span {
  display: block;
  font-size: 0.65rem;
  color: #6b7280;
}

.resumo-preco strong {
  font-size: 0.9rem;
  color: #c9a84c;
}

.btn-remover-espaco {
  background: rgba(239, 68, 68, 0.15);
  border: none;
  border-radius: 8px;
  padding: 6px;
  cursor: pointer;
  color: #ef4444;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-remover-espaco:hover {
  background: #ef4444;
  color: white;
}

.resumo-total {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 12px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  font-size: 0.9rem;
}

.resumo-total strong {
  font-size: 1.2rem;
  color: #c9a84c;
}

.espaco-detalhes {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.detalhe-header {
  display: flex;
  gap: 16px;
  align-items: center;
}

.espaco-icon-large {
  font-size: 48px;
  background: rgba(201, 168, 76, 0.1);
  width: 80px;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 20px;
}

.detalhe-header h4 {
  font-size: 1.2rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 4px;
}

.detalhe-header .descricao {
  color: #8e9aaf;
  font-size: 0.85rem;
}

.info-detalhe {
  background: #1a2233;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.info-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.info-row span {
  color: #8e9aaf;
  font-size: 0.85rem;
}

.tipo-badge {
  background: rgba(201, 168, 76, 0.15);
  color: #c9a84c;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.75rem;
}

.precos-detalhe {
  background: #1a2233;
  border-radius: 12px;
  padding: 16px;
}

.precos-detalhe h4 {
  font-size: 0.85rem;
  font-weight: 700;
  color: #c9a84c;
  margin-bottom: 12px;
}

.preco-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.preco-item:last-child {
  border-bottom: none;
}

.preco-item strong {
  color: #c9a84c;
  font-size: 1rem;
}

.incluido-detalhe {
  background: #1a2233;
  border-radius: 12px;
  padding: 16px;
}

.incluido-detalhe h4 {
  font-size: 0.85rem;
  font-weight: 700;
  color: #c9a84c;
  margin-bottom: 12px;
}

.incluido-detalhe ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.incluido-detalhe li {
  padding: 6px 0;
  color: #b0b8c5;
  font-size: 0.85rem;
}

@media (max-width: 700px) {
  .espacos-grid-select {
    grid-template-columns: 1fr;
  }

  .resumo-item {
    flex-direction: column;
    gap: 12px;
    align-items: flex-start;
  }

  .resumo-valores {
    width: 100%;
    justify-content: space-between;
  }
}

@media (max-width: 900px) {
  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .form-row {
    grid-template-columns: 1fr;
  }

  .info-grid {
    grid-template-columns: 1fr;
  }

  .perfil-header {
    flex-direction: column;
    text-align: center;
  }
}

@media (max-width: 600px) {
  .stats-grid {
    grid-template-columns: 1fr;
  }

  .solicitacao-detalhes,
  .evento-stats {
    flex-direction: column;
    gap: 8px;
  }

  .solicitacao-footer {
    flex-direction: column;
    gap: 12px;
    align-items: flex-start;
  }

  .evento-actions {
    flex-direction: column;
  }

  .btn-dashboard,
  .btn-ingressos {
    justify-content: center;
  }

  .tabs-container {
    overflow-x: auto;
  }

  .tab-btn {
    white-space: nowrap;
  }
}
</style>
