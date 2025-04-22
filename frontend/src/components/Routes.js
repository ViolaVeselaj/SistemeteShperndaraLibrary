import React from "react";
import { Routes, Route } from "react-router-dom";
import HomePage from "./HomePage";
import Login from "./Login";
import SignUp from "./SignUp";
import UserHomePage from "./userHomePage";
import PrivateRoute from "./PrivateRoute";
import AdminPage from "./AdminPage";

const AppRoutes = () => {
  return (
    <Routes>
      {/* Public routes */}
      <Route path="/" element={<HomePage />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />

      {/* Protected route for ADMIN */}
      <Route
        path="/admin"
        element={
          <PrivateRoute role="ADMIN">
            <AdminPage />
          </PrivateRoute>
        }
      />

      {/* Protected route for USER */}
      <Route
        path="/userHomePage"
        element={
          <PrivateRoute role="USER">
            <UserHomePage />
          </PrivateRoute>
        }
      />
    </Routes>
  );
};

export default AppRoutes;
