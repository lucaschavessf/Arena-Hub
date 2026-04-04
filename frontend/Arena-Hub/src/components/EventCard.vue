<template>
  <div class="event-card" @click="$router.push(`/evento/${event.id}`)">
    <div class="card-image">
      <img :src="event.image" :alt="event.title" loading="lazy" />
      <div class="card-overlay"></div>
      <span class="card-badge" :class="event.category.toLowerCase()">
        {{ event.category }}
      </span>
    </div>

    <div class="card-body">
      <h3 class="card-title">{{ event.title }}</h3>
      
      <div class="card-meta">
        <div class="meta-item">
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>
          </svg>
          <span>{{ event.date }}</span>
        </div>
        
        <div class="meta-item">
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
            <circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/>
          </svg>
          <span>{{ event.venue }}</span>
        </div>
      </div>

      <button class="btn-primary card-btn" @click.stop="$router.push(`/comprar/${event.id}`)">
        INGRESSOS
        <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
          <path d="M5 12h14M12 5l7 7-7 7"/>
        </svg>
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
// Adicionei tipagem TS simples para consistência
interface Event {
  id: number;
  title: string;
  image: string;
  category: string;
  date: string;
  venue: string;
}

defineProps<{ event: Event }>()
</script>

<style scoped>
.event-card {
  background: var(--navy-mid);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 16px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  position: relative;
}

.event-card:hover {
  transform: translateY(-8px);
  border-color: rgba(201, 168, 76, 0.3);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.4);
}

/* Efeito na Imagem */
.card-image {
  position: relative;
  aspect-ratio: 16/10;
  overflow: hidden;
}

.card-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.6s ease;
}

.card-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(to top, var(--navy-mid), transparent);
  opacity: 0.6;
}

.event-card:hover .card-image img {
  transform: scale(1.1);
}

/* Badge Estilizada */
.card-badge {
  position: absolute;
  top: 12px;
  right: 12px;
  font-size: 0.65rem;
  font-weight: 800;
  padding: 4px 10px;
  border-radius: 6px;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  z-index: 2;
  background: var(--gold);
  color: var(--navy);
}

/* Cores por Categoria (Ajustadas para o Dark Mode) */
.card-badge.esportes { background: #4cc98e; color: #0a0e17; }
.card-badge.show { background: var(--gold); color: #0a0e17; }

/* Corpo do Card */
.card-body {
  padding: 20px;
}

.card-title {
  font-family: var(--font-display);
  font-size: 1.2rem;
  color: #fff;
  margin-bottom: 12px;
  letter-spacing: 0.03em;
  /* Limita a duas linhas */
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
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
  color: var(--muted);
}

.meta-item svg {
  color: var(--gold);
  opacity: 0.8;
}

/* Botão Interno */
.card-btn {
  width: 100%;
  justify-content: center;
  gap: 10px;
  font-size: 0.75rem;
  padding: 12px;
  border-radius: 10px;
}

.event-card:hover .card-btn {
  background: var(--gold-light);
}
</style>