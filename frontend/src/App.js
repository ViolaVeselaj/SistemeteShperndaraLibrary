import React from "react";
import HomePage from "./components/User/userHomePage";
import AdminPage from "./components/Admin/AdminDashboard";
import AuthorForm from "./components/Admin/AddAuthorForm";
import RegisterUserForm from "./components/Admin/RegisterUserForm";
import Login from "./components/auth/Login";
import SignUp from "./components/auth/SignUp";
import AppRoutes from "./components/routes/Routes";
import { AuthProvider } from "./context/AuthContext";
import BookDetails from "./components/common/BookDetails";
import UserHomePage from "./components/User/userHomePage";

function App (){
  return (
    <AuthProvider>
      <AppRoutes />
    </AuthProvider>
  );
};

export default App;
