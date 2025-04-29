import React from "react";
import styled from "styled-components";

const FooterContainer = styled.footer`
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #1e1f3b;
  padding: 1.5rem 2rem;
  color: white;
  position: relative;
  z-index: 10;
  border-top: 1px solid #444;
  flex-wrap: wrap;

  @media (max-width: 768px) {
    flex-direction: column;
    text-align: center;
    gap: 1rem;
  }
`;

const LogoSection = styled.div`
  width: 20%;
  display: flex;
  align-items: center;

  img {
    width: 48px;
    height: 48px;
    margin-right: 0.5rem;
  }

  span {
    font-weight: bold;
    font-size: 1.2rem;
    color: #ffb6ec;
  }

  @media (max-width: 768px) {
    width: 100%;
    justify-content: center;
  }
`;

const ContentSection = styled.div`
  width: 80%;
  display: flex;
  justify-content: flex-end;

  @media (max-width: 768px) {
    width: 100%;
    justify-content: center;
  }
`;

const FooterLinks = styled.div`
  display: flex;
  gap: 2rem;

  a {
    color: #ccc;
    text-decoration: none;
    font-size: 0.95rem;

    &:hover {
      color: #fff;
    }
  }

  @media (max-width: 768px) {
    flex-direction: column;
    align-items: center;
    gap: 0.5rem;
  }
`;

const Footer = () => {
  return (
    <FooterContainer>
      <LogoSection>
        <img src="/logo192.png" alt="Logo" />
        <span>Library</span>
      </LogoSection>
      <ContentSection>
        <FooterLinks>
          <a href="#">Rreth Nesh</a>
          <a href="#">Kontakt</a>
          <a href="#">Politika e Privatësisë</a>
        </FooterLinks>
      </ContentSection>
    </FooterContainer>
  );
};

export default Footer;
