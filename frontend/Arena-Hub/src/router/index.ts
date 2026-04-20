import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'
import LandingPage from '@/views/LandingPage.vue'

// Definimos o tipo das rotas para o TS ajudar no preenchimento
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
  component: () => import('../views/Login.vue'),
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
    path: '/criar-evento', 
    name: 'SolicitarEvento',
    component: () => import('@/views/FormsEventos.vue') 
  },
  { 
    path: '/meus-ingressos', 
    name: 'MeusIngressos',
    component: () => import('@/views/MeusIngressos.vue') 
  },
  { 
    path: '/admin', 
    name: 'AdminDashboard',
    component: () => import('@/views/AdminDashboard.vue') 
  },
  { 
    path: '/admin/agenda', 
    name: 'AdminAgenda',
    component: () => import('@/views/Agenda.vue') 
  },
  { 
    path: '/admin/solicitacoes', 
    name: 'AdminSolicitacoes',
    component: () => import('@/views/Solicitacoes.vue') 
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