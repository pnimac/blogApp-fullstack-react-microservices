import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

export const useLogout = () => {
  const navigate = useNavigate();
  const { logout: contextLogout } = useAuth();

  const handleLogout = async () => {
    try {
      await contextLogout(); // Use the logout function from AuthContext
      navigate('/login');
    } catch (error) {
      console.error("Logout failed", error);
      // Handle error (e.g., show an error message to the user)
    }
  };

  return handleLogout;
};