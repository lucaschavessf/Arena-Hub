<template>
  <div class="page">
    <AppNavbar />
    
    <main class="main-shop">
      <div class="shop-container">
        <!-- Header com pontos do usuário -->
        <div class="shop-header">
          <div class="points-card">
            <div class="points-icon">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"/>
                <path d="M12 6v6l4 2"/>
              </svg>
            </div>
            <div class="points-info">
              <span class="points-label">Seus Pontos</span>
              <span class="points-value">{{ userPoints }} pts</span>
            </div>
          </div>
          <h1 class="shop-title">Loja ArenaHub</h1>
          <p class="shop-subtitle">Troque seus pontos por benefícios exclusivos</p>
        </div>

        <!-- Categorias -->
        <div class="categories-tabs">
          <button 
            v-for="cat in categorias" 
            :key="cat.id"
            class="category-tab"
            :class="{ active: categoriaAtiva === cat.id }"
            @click="categoriaAtiva = cat.id"
          >
            {{ cat.nome }}
          </button>
        </div>

        <!-- Produtos -->
        <div class="products-grid">
          <div v-for="produto in produtosFiltrados" :key="produto.id" class="product-card">
            <div class="product-image">
              <img :src="produto.imagem" :alt="produto.nome" />
            </div>
            <h3 class="product-title">{{ produto.nome }}</h3>
            <p class="product-description">{{ produto.descricao }}</p>
            <div class="product-footer">
              <div class="product-price">
                <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="12" r="10"/>
                  <path d="M12 6v6l4 2"/>
                </svg>
                <span>{{ produto.pontos }} pontos</span>
              </div>
              <button 
                class="btn-redeem" 
                :disabled="userPoints < produto.pontos"
                @click="redeemProduct(produto)"
              >
                {{ userPoints >= produto.pontos ? 'Resgatar' : 'Pontos insuficientes' }}
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
import { ref, computed, onMounted } from 'vue'
import { useUserStore } from '@/stores/userStore'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const userStore = useUserStore()
const categoriaAtiva = ref('todos')

const userPoints = computed(() => userStore.userPoints)

const categorias = [
  { id: 'todos', nome: 'Todos' },
  { id: 'comida', nome: 'Alimentação' },
  { id: 'bebida', nome: 'Bebidas' },
  { id: 'cupom', nome: 'Cupons' }
]

const produtos = ref([
  // Alimentação
  {
    id: 1,
    nome: 'Combo Hot Dog + Refri',
    descricao: 'Hot dog especial com refrigerante 350ml',
    pontos: 150,
    tipo: 'comida',
    imagem: 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=300&h=200&fit=crop'
  },
  {
    id: 2,
    nome: 'Pizza Individual',
    descricao: 'Pizza sabor queijo ou calabresa',
    pontos: 250,
    tipo: 'comida',
    imagem: 'https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=300&h=200&fit=crop'
  },
  {
    id: 3,
    nome: 'Porção de Batata Frita',
    descricao: 'Batata frita com molho especial',
    pontos: 100,
    tipo: 'comida',
    imagem: 'https://images.unsplash.com/photo-1630384060421-cf20c0e064e3?w=300&h=200&fit=crop'
  },
  {
    id: 4,
    nome: 'Sanduíche Artesanal',
    descricao: 'Sanduíche com hambúrguer artesanal',
    pontos: 200,
    tipo: 'comida',
    imagem: 'https://images.unsplash.com/photo-1550547660-d9450f859349?w=300&h=200&fit=crop'
  },
  // Bebidas
  {
    id: 5,
    nome: 'Refrigerante 350ml',
    descricao: 'Coca-Cola, Guaraná ou Sprite',
    pontos: 50,
    tipo: 'bebida',
    imagem: 'https://images.unsplash.com/photo-1554866585-cd94860890b7?w=300&h=200&fit=crop'
  },
  {
    id: 6,
    nome: 'Água Mineral',
    descricao: 'Água sem gás 500ml',
    pontos: 30,
    tipo: 'bebida',
    imagem: 'https://images.unsplash.com/photo-1548839140-29a749e1cf4d?w=300&h=200&fit=crop'
  },
  {
    id: 7,
    nome: 'Suco Natural',
    descricao: 'Laranja, limão ou uva',
    pontos: 80,
    tipo: 'bebida',
    imagem: 'https://images.unsplash.com/photo-1600271886742-f049cd451bba?w=300&h=200&fit=crop'
  },
  {
    id: 8,
    nome: 'Cerveja Long Neck',
    descricao: 'Cerveja artesanal 355ml',
    pontos: 120,
    tipo: 'bebida',
    imagem: 'https://images.unsplash.com/photo-1614765405730-09cae1cc7d4f?w=300&h=200&fit=crop'
  },
  // Cupons
  {
    id: 9,
    nome: 'Cupom 10% Off',
    descricao: '10% de desconto em qualquer evento',
    pontos: 300,
    tipo: 'cupom',
    imagem: 'https://images.unsplash.com/photo-1606220945770-b5b6c2c55bf1?w=300&h=200&fit=crop'
  },
  {
    id: 10,
    nome: 'Cupom 20% Off',
    descricao: '20% de desconto em eventos selecionados',
    pontos: 500,
    tipo: 'cupom',
    imagem: 'https://images.unsplash.com/photo-1606220945770-b5b6c2c55bf1?w=300&h=200&fit=crop'
  },
  {
    id: 11,
    nome: 'Ingresso Meia Entrada',
    descricao: 'Direito a meia entrada em um evento',
    pontos: 400,
    tipo: 'cupom',
    imagem: 'https://images.unsplash.com/photo-1501286353178-1ec881214838?w=300&h=200&fit=crop'
  },
  {
    id: 12,
    nome: 'Upgrade de Setor',
    descricao: 'Upgrade para setor premium',
    pontos: 800,
    tipo: 'cupom',
    imagem: 'https://images.unsplash.com/photo-1540039155733-5bb30b53aa14?w=300&h=200&fit=crop'
  }
])

const produtosFiltrados = computed(() => {
  if (categoriaAtiva.value === 'todos') {
    return produtos.value
  }
  return produtos.value.filter(p => p.tipo === categoriaAtiva.value)
})

function redeemProduct(produto: any) {
  if (userStore.redeemPoints(produto.pontos)) {
    alert(`${produto.nome} resgatado com sucesso! Verifique seu e-mail.`)
  } else {
    alert('Pontos insuficientes para este resgate')
  }
}

onMounted(() => {
  if (!userStore.isLoggedIn) {
    userStore.login({ name: 'João Silva', points: 1250 })
  }
})
</script>

<style scoped>
.page { 
  min-height: 100vh; 
  display: flex; 
  flex-direction: column; 
  background: #0a0e17;
}

.main-shop {
  flex: 1;
  padding: 60px 20px;
}

.shop-container {
  max-width: 1200px;
  margin: 0 auto;
}

.shop-header {
  text-align: center;
  margin-bottom: 48px;
}

.points-card {
  display: inline-flex;
  align-items: center;
  gap: 12px;
  background: linear-gradient(135deg, #c9a84c, #d4af37);
  padding: 12px 24px;
  border-radius: 40px;
  margin-bottom: 24px;
  color: #0a0e17;
}

.points-icon svg {
  color: #0a0e17;
}

.points-info {
  display: flex;
  flex-direction: column;
}

.points-label {
  font-size: 0.7rem;
  font-weight: 600;
  text-transform: uppercase;
}

.points-value {
  font-size: 1.5rem;
  font-weight: 800;
  line-height: 1;
}

.shop-title {
  font-size: 2.5rem;
  font-family: 'Bebas Neue', sans-serif;
  color: #fff;
  margin-bottom: 8px;
}

.shop-subtitle {
  color: #8e9aaf;
  font-size: 1rem;
}

.categories-tabs {
  display: flex;
  justify-content: center;
  gap: 12px;
  margin-bottom: 40px;
  flex-wrap: wrap;
}

.category-tab {
  background: #1a2233;
  border: 1px solid rgba(255, 255, 255, 0.05);
  padding: 10px 24px;
  border-radius: 30px;
  color: #8e9aaf;
  cursor: pointer;
  transition: all 0.3s ease;
  font-weight: 600;
}

.category-tab:hover {
  border-color: rgba(201, 168, 76, 0.3);
  color: #c9a84c;
}

.category-tab.active {
  background: #c9a84c;
  color: #0a0e17;
  border-color: #c9a84c;
}

.products-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 24px;
}

.product-card {
  background: linear-gradient(135deg, #121826 0%, #0f131e 100%);
  border: 1px solid rgba(255, 255, 255, 0.05);
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.3s ease;
}

.product-card:hover {
  transform: translateY(-4px);
  border-color: rgba(201, 168, 76, 0.2);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
}

.product-image {
  width: 100%;
  height: 180px;
  overflow: hidden;
  position: relative;
}

.product-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}

.product-card:hover .product-image img {
  transform: scale(1.05);
}

.product-title {
  font-size: 1.1rem;
  font-weight: 700;
  color: #fff;
  margin: 16px 16px 8px;
}

.product-description {
  font-size: 0.85rem;
  color: #8e9aaf;
  margin: 0 16px 20px;
  line-height: 1.4;
}

.product-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 16px 20px;
}

.product-price {
  display: flex;
  align-items: center;
  gap: 6px;
  color: #c9a84c;
  font-weight: 700;
  font-size: 0.9rem;
}

.btn-redeem {
  background: #c9a84c;
  color: #0a0e17;
  border: none;
  padding: 8px 16px;
  border-radius: 8px;
  font-weight: 700;
  font-size: 0.8rem;
  cursor: pointer;
  transition: all 0.3s ease;
}

.btn-redeem:hover:not(:disabled) {
  background: #d4af37;
  transform: translateY(-2px);
}

.btn-redeem:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

@media (max-width: 768px) {
  .shop-title {
    font-size: 1.8rem;
  }
  
  .products-grid {
    grid-template-columns: 1fr;
  }
  
  .product-image {
    height: 160px;
  }
}
</style>