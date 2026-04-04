<template>
  <div class="page">
    <AppNavbar />

    <main class="main-content">
      <section class="notfound-section">
        <div class="container">
          <div class="notfound-content">
            
            <h1 class="code">404</h1>

            <h2 class="title">
              Página não encontrada
            </h2>

            <p class="subtitle">
              Parece que você se perdeu. Essa página não existe ou foi removida.
            </p>

            <div class="actions">
              <router-link to="/" class="btn-primary">
                Voltar para Home
              </router-link>

              <button class="btn-secondary" @click="goBack">
                Voltar
              </button>
            </div>

          </div>
        </div>
      </section>
    </main>

    <AppFooter />
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()

const goBack = () => {
  router.back()
}
</script>

<style scoped>
.page { background: #0a0e17; color: white; min-height: 100vh; }

.container { 
  max-width: 1200px; 
  margin: 0 auto; 
  padding: 0 24px; 
  width: 100%;
}

/* 404 */
.notfound-section {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 80vh;
  text-align: center;
}

.notfound-content {
  max-width: 600px;
}

.code {
  font-size: 8rem;
  font-weight: 900;
  color: #c9a84c;
  margin-bottom: 20px;
}

.title {
  font-size: 2rem;
  font-weight: 800;
  margin-bottom: 16px;
}

.subtitle {
  color: #9ca3af;
  margin-bottom: 32px;
}

.actions {
  display: flex;
  gap: 16px;
  justify-content: center;
  flex-wrap: wrap;
}

.btn-primary {
  background: #c9a84c;
  color: #0a0e17;
  padding: 12px 24px;
  border-radius: 10px;
  font-weight: 600;
  text-decoration: none;
}

.btn-secondary {
  background: transparent;
  border: 1px solid rgba(255,255,255,0.2);
  color: white;
  padding: 12px 24px;
  border-radius: 10px;
  cursor: pointer;
}
</style>