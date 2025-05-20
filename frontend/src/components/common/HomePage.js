import React, { useEffect, useState } from "react";
import styled, { keyframes, createGlobalStyle } from "styled-components";
//import Navbar from "../common/Navbar";
import Login from "../auth/Login";
import SignUp from "../auth/SignUp";

const GlobalStyles = createGlobalStyle`
  html {
    scroll-behavior: smooth;
  }
`;

const fadeIn = keyframes`
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
`;

const slideIn = keyframes`
  from {
    opacity: 0;
    transform: translateX(-50px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
`;

const Wrapper = styled.div`
  margin-top: 64px;
  min-height: 100vh;
  background: linear-gradient(to right, #2b2c49, #5d317b);
  color: white;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 3rem 2rem;
  text-align: center;
  animation: ${fadeIn} 1.2s ease-out;
`;

const Title = styled.h1`
  font-size: 3.5rem;
  background: linear-gradient(103deg, #00128f, #fff, #000528);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: animate 5s linear infinite;
  background-size: 80%;
  margin-bottom: 1rem;
`;

const Description = styled.p`
  font-size: 1.5rem;
  max-width: 700px;
  margin: 1.5rem 0;
  color: #ffb6ec;
`;

const Image = styled.img`
  max-width: 520px;
  width: 100%;
  margin-bottom: 2rem;
  border-radius: 20px;
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.4);
  animation: ${fadeIn} 1.4s ease-out;
`;

const Gallery = styled.div`
  display: flex;
  gap: 2rem;
  flex-wrap: wrap;
  justify-content: center;
  margin: 3rem 0;
  animation: ${slideIn} 1.4s ease-out;
`;

const GalleryImage = styled.img`
  width: 260px;
  height: 170px;
  object-fit: cover;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.3);
  transition: 0.3s;

  &:hover {
    transform: scale(1.08);
  }
`;

const ButtonGroup = styled.div`
  display: flex;
  gap: 1.5rem;
  margin-top: 2rem;
  animation: ${fadeIn} 1.6s ease-out;
`;

const StyledButton = styled.button`
  padding: 1rem 2rem;
  font-size: 1.15rem;
  font-weight: bold;
  background-color: #ff0099;
  color: white;
  border-radius: 10px;
  border: none;
  cursor: pointer;
  transition: 0.3s;

  &:hover {
    background-color: #ff3fa3;
    transform: scale(1.05);
  }
`;

const Tagline = styled.p`
  font-style: italic;
  font-size: 1.1rem;
  margin-top: 0.5rem;
  color: #ccc;
  animation: ${fadeIn} 2s ease-out;
`;

const GradientLine = styled.div`
  height: 4px;
  width: 200px;
  background: linear-gradient(90deg, #ff0099, #3023AE);
  margin: 1.5rem 0;
  border-radius: 2px;
  animation: ${fadeIn} 1.5s ease-out;
`;

const FunFact = styled.div`
  margin-top: 3rem;
  font-size: 1.2rem;
  color: #eee;
  background: rgba(255,255,255,0.08);
  padding: 1.5rem 2rem;
  border-radius: 12px;
  max-width: 700px;
  animation: ${slideIn} 1.8s ease-out;
`;

const HomePage = () => {
  const [showLogin, setShowLogin] = useState(false);
  const [showSignup, setShowSignup] = useState(false);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <GlobalStyles />
      {/* <Navbar titles={[]} /> */}
      {showLogin && <Login />}
      {showSignup && <SignUp />}
      {!showLogin && !showSignup && (
        <Wrapper>
          <Image src="https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=900&q=60" alt="Library Books" />
          <Title>MIRESEVINI NE BIBLIOTEKEN ONLINE!</Title>
          <GradientLine />
          <Description>
            Zbuloni mijëra libra, krijoni koleksione personale, dhe eksploroni tituj që ju inspirojnë. Bëhuni pjesë e komunitetit tonë të lexuesve.
          </Description>
          <Tagline>Leximi nuk është vetëm pasion, por mënyrë jetese! </Tagline>
          <ButtonGroup>
            <StyledButton onClick={() => setShowLogin(true)}>Log In</StyledButton>
            <StyledButton onClick={() => setShowSignup(true)}>Sign Up</StyledButton>
          </ButtonGroup>

          <Gallery>
            <GalleryImage src="https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=60" alt="Open Book on Table" />
            <GalleryImage src="https://images.unsplash.com/photo-1519681393784-d120267933ba?auto=format&fit=crop&w=800&q=60" alt="Girl Reading Book" />
            <GalleryImage src="https://images.unsplash.com/photo-1532012197267-da84d127e765?auto=format&fit=crop&w=800&q=60" alt="Book Stack in Library" />
          </Gallery>

          <FunFact>
            A e dini? Leximi për vetëm 6 minuta mund të ulë nivelin e stresit deri në 68% — më shumë se të dëgjosh muzikë apo të pish çaj! Provoje sot me një libër të mirë. 
          </FunFact>
        </Wrapper>
      )}
    </>
  );
};

export default HomePage;

const GlobalStyle = styled.div`
  @keyframes animate {
    0% {
      background-position: -500%;
    }
    100% {
      background-position: 500%;
    }
  }
`;