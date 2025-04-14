import React from "react";
import styled from "styled-components";

const SideMenuWrapper = styled.div`
  position: fixed;
  top: 0;
  left: ${({ open }) => (open ? "0" : "-260px")};
  width: 250px;
  height: 100vh;
  background-color: #1e1f3b;
  color: white;
  padding: 1.5rem 1rem;
  transition: left 0.3s ease;
  z-index: 1100;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  box-shadow: 4px 0 12px rgba(0, 0, 0, 0.3);
`;

const CloseButton = styled.button`
  align-self: flex-end;
  background: none;
  border: none;
  font-size: 1.4rem;
  color: white;
  cursor: pointer;

  &:hover {
    color: #ffb6ec;
  }
`;

const MenuTitle = styled.h3`
  margin-top: 0.5rem;
  margin-bottom: 1rem;
  font-size: 1.2rem;
  border-bottom: 1px solid #444;
  padding-bottom: 0.5rem;
`;

const SideMenuItem = styled.button`
  background: #3023ae;
  color: white;
  border: none;
  padding: 0.8rem 1rem;
  border-radius: 6px;
  text-align: left;
  cursor: pointer;
  font-size: 1rem;

  &:hover {
    background: #4632dc;
  }
`;

const SideMenu = ({ open, setOpen }) => {
  return (
    <SideMenuWrapper open={open}>
      <CloseButton onClick={() => setOpen(false)}>←</CloseButton>
      <MenuTitle>Menu</MenuTitle>
      <SideMenuItem>Ballina</SideMenuItem>
      <SideMenuItem>Zhanret</SideMenuItem>
      <SideMenuItem>Ofertat</SideMenuItem>
      <SideMenuItem>Log Out</SideMenuItem>
    </SideMenuWrapper>
  );
};

export default SideMenu
