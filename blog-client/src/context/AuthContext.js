import React, { createContext, useState, useEffect } from 'react';
import createUserAuthService from '../api/userAuthService';

export const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [authenticated, setAuthenticated] = useState(false);
  const [username, setUsername] = useState('');
  const [loading, setLoading] = useState(true);

  const getAuthToken = () => localStorage.getItem('jwtToken');
  const userAuthService = createUserAuthService(getAuthToken);

  useEffect(() => {
    const checkAuthStatus = async () => {
      const token = getAuthToken();
      if (token) {
        try {
          const user = await userAuthService.getCurrentUser();
          setAuthenticated(true);
          setUsername(user.username);
        } catch (error) {
          console.error('Authentication check failed', error);
          localStorage.removeItem('jwtToken');
        }
      }
      setLoading(false);
    };
    checkAuthStatus();
  }, []);

  const login = async (username, password) => {
    try {
      const data = await userAuthService.login(username, password);
      localStorage.setItem('jwtToken', data.token);
      setAuthenticated(true);
      setUsername(data.username);
      return data;
    } catch (error) {
      console.error('Login failed:', error);
      throw new Error(error.response?.data?.message || 'Login failed');
    }
  };

  const logout = async () => {
    try {
      await userAuthService.logout();
    } catch (error) {
      console.error('Logout failed:', error);
    } finally {
      localStorage.removeItem('jwtToken');
      setAuthenticated(false);
      setUsername('');
    }
  };

  const signup = async (username, email, password) => {
    try {
      const data = await userAuthService.signup(username, email, password);
      return data;
    } catch (error) {
      console.error('Signup failed:', error);
      throw new Error(error.response?.data?.message || 'Signup failed');
    }
  };

  const value = {
    authenticated,
    username,
    loading,
    login,
    logout,
    signup,
    getAuthToken
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;