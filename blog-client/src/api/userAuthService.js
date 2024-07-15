import { userAuthAPI } from '../api';

const createUserAuthService = (getAuthToken) => ({
  login: async (username, password) => {
    const response = await userAuthAPI.post('/auth/login', { username, password });
    return response.data;
  },

  logout: async () => {
    await userAuthAPI.post('/auth/logout', null, {
      headers: { Authorization: `Bearer ${getAuthToken()}` }
    });
  },

  signup: async (username, email, password) => {
    const response = await userAuthAPI.post('/auth/signup', { username, email, password });
    return response.data;
  },

  getCurrentUser: async () => {
    const response = await userAuthAPI.get('/auth/user', {
      headers: { Authorization: `Bearer ${getAuthToken()}` }
    });
    return response.data;
  }
});

export default createUserAuthService;