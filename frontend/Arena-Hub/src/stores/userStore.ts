import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export interface User {
  id: string
  name: string
  email: string
  points: number
  isLoggedIn: boolean
}

export const useUserStore = defineStore('user', () => {
  const user = ref<User>({
    id: '',
    name: '',
    email: '',
    points: 0,
    isLoggedIn: false
  })

  const isLoggedIn = computed(() => user.value.isLoggedIn)
  const userPoints = computed(() => user.value.points)

  function login(userData: Partial<User>) {
    user.value = {
      id: userData.id || '1',
      name: userData.name || 'João Silva',
      email: userData.email || 'joao@email.com',
      points: userData.points || 1250,
      isLoggedIn: true
    }
  }

  function logout() {
    user.value = {
      id: '',
      name: '',
      email: '',
      points: 0,
      isLoggedIn: false
    }
  }

  function addPoints(amount: number) {
    user.value.points += amount
  }

  function redeemPoints(amount: number) {
    if (user.value.points >= amount) {
      user.value.points -= amount
      return true
    }
    return false
  }

  return {
    user,
    isLoggedIn,
    userPoints,
    login,
    logout,
    addPoints,
    redeemPoints
  }
})