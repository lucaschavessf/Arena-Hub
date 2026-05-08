import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export interface User {
  id: string
  name: string
  email: string
  points: number
  isLoggedIn: boolean
}

const STORAGE_KEY = 'arena_hub_auth'

export const useUserStore = defineStore('user', () => {
  const token = ref<string>('')
  const user = ref<User>({
    id: '',
    name: '',
    email: '',
    points: 0,
    isLoggedIn: false
  })

  const isLoggedIn = computed(() => user.value.isLoggedIn)
  const userPoints = computed(() => user.value.points)

  const stored = localStorage.getItem(STORAGE_KEY)
  if (stored) {
    try {
      const data = JSON.parse(stored)
      if (data.token) {
        token.value = data.token
        user.value = { ...user.value, ...data.user, isLoggedIn: true }
      }
    } catch {
      localStorage.removeItem(STORAGE_KEY)
    }
  }

  function login(userData: { name: string; token: string; id?: string; email?: string }) {
    token.value = userData.token
    user.value = {
      id: userData.id || '',
      name: userData.name,
      email: userData.email || '',
      points: 0,
      isLoggedIn: true
    }
    localStorage.setItem(STORAGE_KEY, JSON.stringify({ token: token.value, user: user.value }))
  }

  function logout() {
    token.value = ''
    user.value = {
      id: '',
      name: '',
      email: '',
      points: 0,
      isLoggedIn: false
    }
    localStorage.removeItem(STORAGE_KEY)
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
    token,
    isLoggedIn,
    userPoints,
    login,
    logout,
    addPoints,
    redeemPoints
  }
})
