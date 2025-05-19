import { createContext, useState, useEffect } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(null);
    const [isLoading, setIsLoading] = useState(true); // ✅
  
    useEffect(() => {
      const storedUser = localStorage.getItem("user");
      const storedToken = localStorage.getItem("token");
  
      if (storedUser && storedToken) {
        setUser(JSON.parse(storedUser));
        setToken(storedToken);
      }
  
      setIsLoading(false); // ❗ vendoset pasi të përfundojë kontrolli
    }, []);
  
    const login = (userData, jwtToken) => {
  setUser(userData);

    const cleanedToken = jwtToken.startsWith("Bearer ")
        ? jwtToken.replace("Bearer ", "")
        : jwtToken;

      setToken(cleanedToken);
      localStorage.setItem("user", JSON.stringify(userData));
      localStorage.setItem("token", cleanedToken);
    };
  
    const logout = () => {
      setUser(null);
      setToken(null);
      localStorage.removeItem("user");
      localStorage.removeItem("token");
    };
  
    return (
      <AuthContext.Provider value={{ user, token, login, logout, isLoading }}>
        {children}
      </AuthContext.Provider>
    );
  };
  