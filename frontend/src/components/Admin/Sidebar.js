import React from "react";
import { SidebarWrapper, AdminOption } from "./AdminStyles";

const Sidebar = () => (
  <SidebarWrapper>
    <h2>Admin Panel</h2>
    <AdminOption>Shto Libër</AdminOption>
    <AdminOption>Regjistro Përdorues</AdminOption>
    <AdminOption>Shiko Reviews</AdminOption>
    <AdminOption>Statistika</AdminOption>
    <AdminOption>Kalendar Kthimesh</AdminOption>
    <AdminOption>Fushata Promocionale</AdminOption>
    <AdminOption>Monitorim i Aktivitetit</AdminOption>
  </SidebarWrapper>
);

export default Sidebar;
