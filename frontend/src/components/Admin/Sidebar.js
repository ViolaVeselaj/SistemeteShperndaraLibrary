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
      <AdminOption onClick={() => navigate("/admin/events/add")}>Shto Event</AdminOption> 
      <AdminOption onClick={() => navigate("/admin/events/list")}>Lista Eventeve</AdminOption> 
    </SidebarWrapper>
  );
};

export default Sidebar;
