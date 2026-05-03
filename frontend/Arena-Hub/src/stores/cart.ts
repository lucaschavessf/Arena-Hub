import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export interface Ingresso {
  id: string
  nome: string
  preco: number
  setor: string
  disponivel: number
}

export interface Evento {
  id: string
  nome: string
  data: string
  local: string
  ingressos: Ingresso[]
}

export const useCartStore = defineStore('cart', () => {
  const eventoSelecionado = ref<Evento | null>(null)
  const ingressos = ref<Record<string | number, number>>({})

  const totalItens = computed<number>(() => {
    return Object.values(ingressos.value).reduce((acc, qty) => acc + qty, 0)
  })

  const valorTotal = computed<number>(() => {
    if (!eventoSelecionado.value) return 0
    
    let total = 0
    for (const [idx, quantidade] of Object.entries(ingressos.value)) {
      const ingresso = eventoSelecionado.value.ingressos[Number(idx)]
      if (ingresso && quantidade > 0) {
        total += ingresso.preco * quantidade
      }
    }
    return total
  })

  const itensCarrinho = computed(() => {
    if (!eventoSelecionado.value) return []
    
    return Object.entries(ingressos.value)
      .filter(([idx, quantidade]) => {
        const ingresso = eventoSelecionado.value!.ingressos[Number(idx)]
        return quantidade > 0 && !!ingresso
      })
      .map(([idx, quantidade]) => {
        const ingresso = eventoSelecionado.value!.ingressos[Number(idx)]!
        return {
          ...ingresso,
          quantidade,
          subtotal: ingresso.preco * quantidade,
          idx: Number(idx)
        }
      })
  })

  function setQuantidade(idx: string | number, qty: number): void {
    if (qty <= 0) {
      const novasQtys = { ...ingressos.value }
      delete novasQtys[idx]
      ingressos.value = novasQtys
    } else {
      ingressos.value[idx] = qty
    }
  }

  function limparCarrinho(): void {
    ingressos.value = {}
    eventoSelecionado.value = null
  }

  function adicionarAoCarrinho(idx: string | number, quantidade: number = 1): void {
    const qtyAtual = ingressos.value[idx] || 0
    setQuantidade(idx, qtyAtual + quantidade)
  }

  function removerDoCarrinho(idx: string | number): void {
    setQuantidade(idx, 0)
  }

  function setEvento(evento: Evento): void {
    eventoSelecionado.value = evento
    ingressos.value = {}
  }

  return { 
    eventoSelecionado, 
    ingressos, 
    totalItens,
    valorTotal,
    itensCarrinho,
    setQuantidade, 
    limparCarrinho,
    adicionarAoCarrinho,
    removerDoCarrinho,
    setEvento
  }
})