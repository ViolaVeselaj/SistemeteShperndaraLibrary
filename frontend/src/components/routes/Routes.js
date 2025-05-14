import React from "react";
import { Routes, Route } from "react-router-dom";
import HomePage from "../common/HomePage";
import Login from "../auth/Login";
import SignUp from "../auth/SignUp";
import UserHomePage from "../User/userHomePage";
import PrivateRoute from "./PrivateRoute";
import AdminPage from "../Admin/AdminDashboard";
import AddBookForm from "../Admin/AddBookForm";
import BookDetails from "../common/BookDetails";
import AddAuthorForm from "../Admin/AddAuthorForm";
import RegisterUserForm from "../Admin/RegisterUserForm";


const AppRoutes = () => {
  return (
    <Routes>
      {/* Public routes */}
      <Route path="/" element={<HomePage />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route
        path="/admin/add-book"
        element={
          <PrivateRoute role="ADMIN">
            <AddBookForm />
          </PrivateRoute>
        }
      />

      <Route
        path="/admin/add-author"
        element={
          <PrivateRoute role="ADMIN">
            <AddAuthorForm />
          </PrivateRoute>
        }
      />

      <Route
        path="/admin/register-user"
        element={
          <PrivateRoute role="ADMIN">
            <RegisterUserForm />
          </PrivateRoute>
        }
      />


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
