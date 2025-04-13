// src/components/Navbar.js
import React from "react";
import styled from "styled-components";

const NavbarContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  height: 64px;
  width: 100%;
  background-color: #1e1f3b;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 2rem;
  z-index: 1000;
  box-shadow: 0 4px 6px rgba(0,0,0,0.3);
`;

const NavLogo = styled.div`
  display: flex;
  align-items: center;
  font-size: 1.2rem;
  font-weight: bold;
  color: white;

  img {
    width: 32px;
    height: 32px;
    margin-right: 10px;
  }
`;

const NavLinks = styled.div`
  display: flex;
  flex: 1;
  justify-content: center;
  gap: 3rem;

  span {
    color: #ffb6ec;
    font-weight: 600;
    font-size: 1.05rem;
    letter-spacing: 0.5px;
    cursor: default;

    &:hover {
      color: #ffffff;
    }
  }
`;

const NavButton = styled.button`
  background-color: #ff0099;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 600;
  font-size: 0.95rem;

  &:hover {
    background-color: #ff3fa3;
  }
`;

const Navbar = ({ titles = [] }) => {
  return (
    <NavbarContainer>
      <NavLogo>
        <img src="/logo192.png" alt="Logo" />
        Library
      </NavLogo>

      <NavLinks>
        {titles.map((title, index) => (
          <span key={index}>{title}</span>
        ))}
      </NavLinks>

      <NavButton>Log Out</NavButton>
    </NavbarContainer>
  );
};

export default Navbar;
