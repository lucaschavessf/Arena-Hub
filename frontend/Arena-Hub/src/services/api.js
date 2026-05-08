import axios from 'axios';

const STORAGE_KEY = 'arena_hub_auth';

const api = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
});

api.interceptors.request.use((config) => {
    config.headers['Content-Type'] = 'application/json';
    const stored = localStorage.getItem(STORAGE_KEY);
    if (stored) {
        try {
            const { token } = JSON.parse(stored);
            if (token) config.headers['Authorization'] = `Bearer ${token}`;
        } catch {
            // ignore malformed storage
        }
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

export default api;
