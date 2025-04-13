import React, { useState } from "react";
import styled from "styled-components";

const Layout = () => {
  const [layout, setLayout] = useState("single-column");

  return (
    <PageWrapper>
      <Navbar>
        <LogoSection>LOGO</LogoSection>
        <NavItems>
          <label>Select Layout:</label>
          <select value={layout} onChange={(e) => setLayout(e.target.value)}>
            <option value="single-column">Single Column</option>
            <option value="split-screen">Split Screen</option>
            <option value="zigzag">Zigzag</option>
            <option value="modular-grid">Modular Grid</option>
            <option value="asymmetrical">Asymmetrical</option>
          </select>
        </NavItems>
      </Navbar>

      <ContentArea>
        <h1>{layout.replace(/-/g, ' ').toUpperCase()} Layout Selected</h1>
        {/* This is where different layouts can be conditionally rendered later */}
      </ContentArea>

      <Footer>
        {/* Content to be added in next steps */}
      </Footer>
    </PageWrapper>
  );
};

export default Layout;

const PageWrapper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
`;

const Navbar = styled.nav`
  display: flex;
  background-color: #001f3f;
  color: white;
  padding: 1rem;
  justify-content: space-between;
  align-items: center;
`;

const LogoSection = styled.div`
  width: 20%;
  text-align: center;
  font-weight: bold;
  font-size: 1.2rem;
`;

const NavItems = styled.div`
  width: 80%;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 1rem;

  select {
    padding: 0.4rem;
    font-size: 1rem;
  }
`;

const ContentArea = styled.main`
  flex: 1;
  padding: 2rem;
  background: #f0f0f5;
  color: #333;
`;

const Footer = styled.footer`
  background-color: #001f3f;
  color: white;
  padding: 1rem;
  text-align: center;
`;
