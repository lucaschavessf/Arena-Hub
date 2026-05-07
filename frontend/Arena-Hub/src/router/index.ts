import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'
import LandingPage from '@/views/LandingPage.vue'

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
    component: () => import('@/views/ProdutorInfo.vue') 
  },
  { 
    path: '/produtor/cadastro', 
    name: 'ProdutorCadastro',
    component: () => import('@/views/ProdutorCadastro.vue') 
  },
  { 
    path: '/produtor/dashboard', 
    name: 'ProdutorDashboard',
    component: () => import('@/views/ProdutorDashboard.vue') 
  },
  { 
    path: '/meus-ingressos', 
    name: 'MeusIngressos',
    component: () => import('@/views/MeusIngressos.vue') 
  },
  { 
    path: '/perfil', 
    name: 'Perfil',
    component: () => import('@/views/Perfil.vue') 
  },
  { 
    path: '/admin', 
    name: 'AdminDashboard',
    component: () => import('@/views/AdminDashboard.vue') 
  },
  { 
    path: '/admin/agenda', 
    name: 'AdminAgenda',
    component: () => import('@/views/AdminAgenda.vue') 
  },
  { 
    path: '/admin/solicitacoes', 
    name: 'AdminSolicitacoes',
    component: () => import('@/views/AdminSolicitacoes.vue') 
  },
  { 
    path: '/admin/precos', 
    name: 'AdminPrecos',
    component: () => import('@/views/AdminPrecos.vue') 
  },
  { 
    path: '/admin/perfil', 
    name: 'AdminPerfil',
    component: () => import('@/views/AdminPerfil.vue') 
  },
  { 
    path: '/admin/eventos', 
    name: 'AdminEventosAprovados',
    component: () => import('@/views/AdminEventosAprovados.vue') 
  },
  { 
    path: '/admin/evento/:id', 
    name: 'AdminEventosDetalhes',
    component: () => import('@/views/AdminEventoDetalhado.vue') 
  },
  { 
    path: '/pagamento', 
    name: 'Pagamento',
    component: () => import('@/views/Pagamento.vue') 
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

export default router