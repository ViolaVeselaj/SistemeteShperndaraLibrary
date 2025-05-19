import React from "react";
import { SidebarWrapper, AdminOption } from "./AdminStyles";
import { useNavigate } from "react-router-dom";

const Sidebar = () => {
  const navigate = useNavigate();

  return (
    <SidebarWrapper>
      <h2>Admin Panel</h2>
      <AdminOption onClick={() => navigate("/admin/add-book")}>Shto Libër</AdminOption>
      <AdminOption onClick={() => navigate("/admin/add-author")}>Shto Autor</AdminOption>
    
      <AdminOption onClick={() => navigate("/admin/register-user")}>Regjistro Përdorues</AdminOption>
      <AdminOption onClick={() => navigate("/admin/requests")}>Kërkesat për Huazim</AdminOption> {/* Buton i ri */}
    </SidebarWrapper>
  );
};

export default Sidebar;
