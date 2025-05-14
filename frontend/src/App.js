import React from "react";
import AppRoutes from "./components/routes/Routes";
import { AuthProvider } from "./context/AuthContext";

function App (){
  return (
    <AuthProvider>
      <AppRoutes />
    </AuthProvider>
  );
};

export default App;
