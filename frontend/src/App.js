import React from "react";
import HomePage from "./components/User/userHomePage";
import AdminPage from "./components/Admin/AdminDashboard";
import Login from "./components/auth/Login";
import SignUp from "./components/auth/SignUp";
import AppRoutes from "./components/routes/Routes";
import BookDetails from "./components/common/BookDetails";

const App = () => {
  return <AppRoutes />;
};

export default App;
