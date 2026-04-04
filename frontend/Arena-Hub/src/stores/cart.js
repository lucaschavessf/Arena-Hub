import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useCartStore = defineStore('cart', () => {
  // Estado
  const eventoSelecionado = ref(null)
  const ingressos = ref({}) // { index_do_ingresso: quantidade }

  // Getters (Computados)
  const totalItens = computed(() => {
    return Object.values(ingressos.value).reduce((acc, qty) => acc + qty, 0)
  })

  // Ações
  function setQuantidade(idx, qty) {
    if (qty <= 0) {
      // Usamos uma cópia para o Vue detectar a mudança de exclusão
      const novasQtys = { ...ingressos.value }
      delete novasQtys[idx]
      ingressos.value = novasQtys
    } else {
      ingressos.value[idx] = qty
    }
  }

  function limparCarrinho() {
    ingressos.value = {}
    eventoSelecionado.value = null
  }

  return { 
    eventoSelecionado, 
    ingressos, 
    totalItens, 
    setQuantidade, 
    limparCarrinho 
  }
})