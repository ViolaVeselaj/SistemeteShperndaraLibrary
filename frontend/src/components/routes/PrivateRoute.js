import React, { useContext } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";

const PrivateRoute = ({ children, role }) => {
  const { user, isLoading } = useContext(AuthContext);

  if (isLoading) return <div>Loading...</div>; // ⏳ pret të lexojë nga localStorage

  if (!user) return <Navigate to="/login" />;
  if (role && user.role !== role) return <Navigate to="/unauthorized" />;

  return children;
};

export default PrivateRoute;
