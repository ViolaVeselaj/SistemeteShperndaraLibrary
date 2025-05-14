import React from "react";
import HomePage from "./components/User/userHomePage";
import AdminPage from "./components/Admin/AdminDashboard";
import AuthorForm from "./components/Admin/AddAuthorForm";
import RegisterUserForm from "./components/Admin/RegisterUserForm";
import Login from "./components/auth/Login";
import SignUp from "./components/auth/SignUp";
import AppRoutes from "./components/routes/Routes";

const App = () => {
  return <AppRoutes />;
};

export default App;
