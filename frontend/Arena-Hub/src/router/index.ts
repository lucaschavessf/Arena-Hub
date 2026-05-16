import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'
import LandingPage from '@/views/LandingPage.vue'
import { useUserStore } from '@/stores/userStore'

const routes: Array<RouteRecordRaw> = [
  { 
    path: '/', 
    name: 'Home',
    component: LandingPage 
  },
  { 
    path: '/cadastro', 
    name: 'Cadastro',
    component: () => import('@/views/Cadastro.vue') 
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
  },
  {
    path: '/esqueceu-senha',
    name: 'EsqueceuSenha',
    component: () => import('@/views/EsqueceuSenha.vue'),
  },
  {
    path: '/recuperar-senha',
    name: 'RecuperarSenha',
    component: () => import('@/views/RecuperarSenha.vue'),
  },
  { 
    path: '/evento/:id', 
    name: 'EventoDetalhes',
    component: () => import('@/views/InfoEvento.vue'),
    props: true 
  },
  { 
    path: '/comprar/:id', 
    name: 'CompraIngresso',
    component: () => import('@/views/CompraIngresso.vue'),
    props: true
  },
  { 
    path: '/produtor', 
    name: 'ProdutorInfo',
    component: () => import('@/views/ProdutorInfo.vue'),
    meta: { requiresAuth: true }
  },
  { 
    path: '/produtor/cadastro', 
    name: 'ProdutorCadastro',
    component: () => import('@/views/ProdutorCadastro.vue'),
  },
  { 
    path: '/produtor/dashboard', 
    name: 'ProdutorDashboard',
    component: () => import('@/views/ProdutorDashboard.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/produtor/evento/:id/ingressos',
    name: 'ProdutorEventoIngressos',
    component: () => import('@/views/ProdutorEventoIngressos.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/meus-ingressos',
    name: 'MeusIngressos',
    component: () => import('@/views/MeusIngressos.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/perfil',
    name: 'Perfil',
    component: () => import('@/views/Perfil.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/admin',
    name: 'AdminDashboard',
    component: () => import('@/views/AdminDashboard.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/admin/agenda',
    name: 'AdminAgenda',
    component: () => import('@/views/AdminAgenda.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/admin/produtores',
    name: 'AdminProdutores',
    component: () => import('@/views/AdminProdutores.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/admin/solicitacoes',
    name: 'AdminSolicitacoes',
    component: () => import('@/views/AdminSolicitacoes.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/admin/precos',
    name: 'AdminPrecos',
    component: () => import('@/views/AdminPrecos.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/admin/perfil',
    name: 'AdminPerfil',
    component: () => import('@/views/AdminPerfil.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/admin/eventos',
    name: 'AdminEventosAprovados',
    component: () => import('@/views/AdminEventosAprovados.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/admin/evento/:id',
    name: 'AdminEventosDetalhes',
    component: () => import('@/views/AdminEventoDetalhado.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/admin/criar-conta',
    name: 'AdminCriarConta',
    component: () => import('@/views/AdminCriarConta.vue'),
  },
  {
    path: '/pagamento',
    name: 'Pagamento',
    component: () => import('@/views/Pagamento.vue'),
    meta: { requiresAuth: true }
  },
  {
  path: '/:pathMatch(.*)*',
  component: () => import('@/views/NotFound.vue'),
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0, behavior: 'smooth' }
  },
})

router.beforeEach((to) => {
  if (to.meta.requiresAuth) {
    const userStore = useUserStore()
    if (!userStore.isLoggedIn) {
      return { name: 'Login' }
    }
  }
})

export default router