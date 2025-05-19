// src/components/Books/BookCard.jsx
import React from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom"; // 🔁 IMPORTO navigate

const Card = styled.div`
  background-color: #2e2e2e;
  color: white;
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  margin: 1rem;
  max-width: 300px;
  cursor: pointer;
  transition: 0.3s;

  &:hover {
    background-color: #383838;
  }
`;

const Info = styled.div`
  margin-bottom: 1rem;
`;

const Title = styled.h3`
  font-size: 1.2rem;
  margin: 0;
`;

const Extra = styled.p`
  font-size: 0.95rem;
  color: #ccc;
  margin: 0.2rem 0 0;
`;

const Button = styled.button`
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  margin-top: 0.5rem;
  border-radius: 8px;
  cursor: pointer;
  transition: 0.3s ease;

  &:hover {
    background-color: #45a049;
  }
`;
const BookCard = ({ title, extra }) => {
  const handleFavouriteClick = () => {
    alert(`"${title}" u shtua në favourites!`);
  };

  return (
    <Card>
      <Info>
        <Title>{title}</Title>
        <Extra>{extra}</Extra>
      </Info>
      <Button onClick={handleFavouriteClick}>+ Favourites</Button>
         </Card>
  );
};

export default BookCard;
