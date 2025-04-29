import React from "react";
import { Navigate } from "react-router-dom";
import { useUser } from "./UserContext";

const PrivateRoute = ({ children, role }) => {
  const { user } = useUser();

  if (!user) {
    return <Navigate to="/login" replace />;
  }

  if (role && user.role !== role) {
    return <Navigate to="/" replace />;
  }

  return children;
};

export default PrivateRoute;