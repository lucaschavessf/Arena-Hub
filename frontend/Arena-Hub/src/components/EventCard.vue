<template>
  <article 
    class="event-card"
    :class="{ 'event-card--soldout': event.soldOut }"
    @click="handleCardClick"
    @keydown.enter="handleCardClick"
    @keydown.space.prevent="handleCardClick"
    role="article"
    tabindex="0"
    :aria-label="`Evento: ${event.title}, ${event.date} em ${event.venue}`"
  >
    <div class="card-image">
      <!-- Placeholder enquanto carrega -->
      <div class="card-image__placeholder" v-if="!imageLoaded"></div>
      
      <img 
        :src="event.image" 
        :alt="`Imagem do evento ${event.title}`"
        loading="lazy"
        @load="imageLoaded = true"
        @error="handleImageError"
        :style="{ opacity: imageLoaded ? 1 : 0 }"
      />
      
      <div class="card-overlay"></div>
      
      <!-- Badges múltiplos -->
      <div class="card-badges">
        <span 
          class="card-badge" 
          :class="event.category.toLowerCase()"
          role="status"
        >
          {{ event.category }}
        </span>
        
        <span 
          v-if="event.soldOut" 
          class="card-badge card-badge--soldout"
          role="status"
        >
          Esgotado
        </span>
        
        <span 
          v-if="isNew" 
          class="card-badge card-badge--new"
          role="status"
        >
          Novo
        </span>
      </div>
      
      <!-- Botão de favorito -->
      <button 
        class="card-favorite"
        :class="{ 'card-favorite--active': isFavorite }"
        @click.stop="toggleFavorite"
        :aria-label="isFavorite ? 'Remover dos favoritos' : 'Adicionar aos favoritos'"
        :aria-pressed="isFavorite"
      >
        <svg width="16" height="16" viewBox="0 0 24 24" :fill="isFavorite ? 'currentColor' : 'none'" stroke="currentColor" stroke-width="2.5">
          <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/>
        </svg>
      </button>
    </div>

    <div class="card-body">
      <h3 class="card-title">{{ event.title }}</h3>
      
      <div class="card-meta">
        <div class="meta-item">
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" aria-hidden="true">
            <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
          </svg>
          <span>{{ formatDate(event.date) }}</span>
        </div>
        
        <div class="meta-item">
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" aria-hidden="true">
            <circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/>
          </svg>
          <span>{{ event.venue }}</span>
        </div>

        <!-- Preço (opcional) -->
        <div class="meta-item meta-item--price" v-if="event.price">
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" aria-hidden="true">
            <circle cx="12" cy="12" r="10"/><path d="M15 9.5c0 .8-.7 1.5-1.5 1.5h-3c-.8 0-1.5-.7-1.5-1.5v-3c0-.8.7-1.5 1.5-1.5h3c.8 0 1.5.7 1.5 1.5z"/><path d="M9 15.5c0 .8.7 1.5 1.5 1.5h3c.8 0 1.5-.7 1.5-1.5v-3c0-.8-.7-1.5-1.5-1.5h-3c-.8 0-1.5.7-1.5 1.5z"/>
          </svg>
          <span>A partir de R$ {{ formatPrice(event.price) }}</span>
        </div>
      </div>

      <!-- Botão de ação -->
      <button 
        class="btn-primary card-btn" 
        @click.stop="handleBuyClick"
        :disabled="event.soldOut"
        :aria-label="event.soldOut ? 'Ingressos esgotados' : 'Comprar ingressos'"
      >
        <template v-if="event.soldOut">
          ESGOTADO
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" aria-hidden="true">
            <circle cx="12" cy="12" r="10"/><path d="M15 9l-6 6M9 9l6 6"/>
          </svg>
        </template>
        <template v-else>
          INGRESSOS
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" aria-hidden="true">
            <path d="M5 12h14M12 5l7 7-7 7"/>
          </svg>
        </template>
      </button>
    </div>
  </article>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

interface Event {
  id: number | string
  title: string
  image: string
  category: string
  date: string | Date
  venue: string
  price?: number
  soldOut?: boolean
  createdAt?: string | Date
}

const props = defineProps<{ 
  event: Event
  fallbackImage?: string 
}>()

const emit = defineEmits<{
  favorite: [eventId: number | string]
  click: [eventId: number | string]
  buy: [eventId: number | string]
}>()

const router = useRouter()
const imageLoaded = ref(false)
const imageError = ref(false)
const isFavorite = ref(false)

// Verifica se o evento é novo (últimos 7 dias)
const isNew = computed(() => {
  if (!props.event.createdAt) return false
  const created = parseDate(props.event.createdAt)
  if (!created) return false
  const now = new Date()
  const diffDays = (now.getTime() - created.getTime()) / (1000 * 60 * 60 * 24)
  return diffDays <= 7
})

// Função para parsear data em vários formatos
const parseDate = (dateInput: string | Date): Date | null => {
  if (dateInput instanceof Date) return dateInput
  
  if (typeof dateInput === 'string') {
    // Formato ISO: 2024-12-25 ou 2024-12-25T10:30:00
    if (dateInput.includes('-') && !dateInput.includes('/')) {
      return new Date(dateInput)
    }
    
    // Formato brasileiro: DD/MM/YYYY
    if (dateInput.includes('/')) {
      const parts = dateInput.split('/')
      if (parts.length === 3) {
        const day = parseInt(parts[0], 10)
        const month = parseInt(parts[1], 10) - 1
        const year = parseInt(parts[2], 10)
        return new Date(year, month, day)
      }
    }
    
    // Tentar parse automático
    const parsed = new Date(dateInput)
    if (!isNaN(parsed.getTime())) {
      return parsed
    }
  }
  
  return null
}

const handleImageError = () => {
  imageError.value = true
}

const formatDate = (date: string | Date): string => {
  const parsed = parseDate(date)
  
  if (!parsed) {
    // Se não conseguir parsear, retorna a string original
    return typeof date === 'string' ? date : 'Data indisponível'
  }
  
  // Verificar se a data é válida
  if (isNaN(parsed.getTime())) {
    return typeof date === 'string' ? date : 'Data indisponível'
  }
  
  // Formatar para português
  const diasSemana = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb']
  const meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']
  
  const diaSemana = diasSemana[parsed.getDay()]
  const dia = parsed.getDate().toString().padStart(2, '0')
  const mes = meses[parsed.getMonth()]
  const ano = parsed.getFullYear()
  
  return `${diaSemana}, ${dia} ${mes} ${ano}`
}

const formatPrice = (price: number): string => {
  return price.toLocaleString('pt-BR', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  })
}

const handleCardClick = () => {
  emit('click', props.event.id)
  router.push(`/evento/${props.event.id}`)
}

const handleBuyClick = () => {
  if (!props.event.soldOut) {
    emit('buy', props.event.id)
    router.push(`/comprar/${props.event.id}`)
  }
}

const toggleFavorite = () => {
  isFavorite.value = !isFavorite.value
  emit('favorite', props.event.id)
}
</script>

<style scoped>
.event-card {
  background: var(--navy-mid, #131a2a);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 16px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  position: relative;
  outline: none;
}

.event-card:focus-visible {
  outline: 3px solid var(--gold, #c9a84c);
  outline-offset: 2px;
  transform: translateY(-4px);
}

@media (hover: hover) {
  .event-card:hover {
    transform: translateY(-8px);
    border-color: rgba(201, 168, 76, 0.3);
    box-shadow: 0 20px 40px rgba(0, 0, 0, 0.4);
  }
}

@media (hover: none) {
  .event-card:active {
    transform: scale(0.98);
  }
}

.event-card--soldout {
  opacity: 0.85;
}

/* Imagem */
.card-image {
  position: relative;
  aspect-ratio: 16 / 10;
  overflow: hidden;
  background: var(--navy-dark, #0a0e1a);
}

.card-image__placeholder {
  position: absolute;
  inset: 0;
  background: linear-gradient(90deg, 
    rgba(255,255,255,0.03) 25%, 
    rgba(255,255,255,0.08) 50%, 
    rgba(255,255,255,0.03) 75%
  );
  background-size: 200% 100%;
  animation: shimmer 1.5s infinite;
}

@keyframes shimmer {
  0% { background-position: 200% 0; }
  100% { background-position: -200% 0; }
}

.card-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.6s ease, opacity 0.3s ease;
  opacity: 0;
}

@media (hover: hover) {
  .event-card:hover .card-image img {
    transform: scale(1.1);
  }
}

.card-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(to top, 
    var(--navy-mid, #131a2a) 0%, 
    transparent 60%
  );
  opacity: 0.8;
}

/* Badges */
.card-badges {
  position: absolute;
  top: 12px;
  right: 12px;
  display: flex;
  flex-direction: column;
  gap: 6px;
  z-index: 2;
}

.card-badge {
  font-size: 0.65rem;
  font-weight: 800;
  padding: 4px 10px;
  border-radius: 6px;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  background: var(--gold, #c9a84c);
  color: var(--navy, #0a0e1a);
  backdrop-filter: blur(4px);
  align-self: flex-end;
}

.card-badge.esportes { 
  background: #4cc98e; 
  color: #0a0e17; 
}

.card-badge.show { 
  background: var(--gold, #c9a84c); 
  color: #0a0e17; 
}

.card-badge--soldout {
  background: #ef4444;
  color: white;
}

.card-badge--new {
  background: #3b82f6;
  color: white;
}

/* Botão de favorito */
.card-favorite {
  position: absolute;
  top: 12px;
  left: 12px;
  width: 32px;
  height: 32px;
  border-radius: 8px;
  background: rgba(0, 0, 0, 0.4);
  backdrop-filter: blur(4px);
  border: 1px solid rgba(255, 255, 255, 0.1);
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  cursor: pointer;
  transition: all 0.2s;
  z-index: 2;
}

.card-favorite:hover {
  background: rgba(239, 68, 68, 0.2);
  border-color: #ef4444;
}

.card-favorite--active {
  color: #ef4444;
  background: rgba(239, 68, 68, 0.15);
  border-color: #ef4444;
}

.card-favorite:focus-visible {
  outline: 2px solid white;
  outline-offset: 2px;
}

/* Corpo do card */
.card-body {
  padding: 20px;
}

.card-title {
  font-family: var(--font-display, serif);
  font-size: 1.2rem;
  color: #fff;
  margin-bottom: 12px;
  letter-spacing: 0.03em;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  line-height: 1.4;
}

.card-meta {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-bottom: 20px;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 0.8rem;
  color: var(--muted, #8a8f9e);
}

.meta-item svg {
  color: var(--gold, #c9a84c);
  opacity: 0.8;
  flex-shrink: 0;
}

.meta-item--price {
  color: var(--gold, #c9a84c);
  font-weight: 600;
}

/* Botão */
.card-btn {
  width: 100%;
  justify-content: center;
  gap: 10px;
  font-size: 0.75rem;
  padding: 12px;
  border-radius: 10px;
  transition: all 0.2s;
}

.card-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
  background: var(--muted, #8a8f9e);
}

.card-btn:not(:disabled):hover {
  background: var(--gold-light, #d4b35c);
}

.card-btn:focus-visible {
  outline: 2px solid var(--gold, #c9a84c);
  outline-offset: 2px;
}

@media (hover: hover) {
  .event-card:hover .card-btn:not(:disabled) {
    background: var(--gold-light, #d4b35c);
  }
}

:root {
  --navy-dark: #0a0e1a;
  --navy-mid: #131a2a;
  --gold: #c9a84c;
  --gold-light: #d4b35c;
  --muted: #8a8f9e;
}
</style>