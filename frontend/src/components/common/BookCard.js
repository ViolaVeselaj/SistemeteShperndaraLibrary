import React from "react";
import styled from "styled-components";

const Card = styled.div`
  background: rgba(255, 255, 255, 0.1);
  margin: 0.5rem 0;
  padding: 1rem 1.5rem;
  border-radius: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  transition: 0.3s;

  &:hover {
    background: rgba(255, 255, 255, 0.2);
  }
`;

const Info = styled.div`
  display: flex;
  flex-direction: column;
`;

const Title = styled.h4`
  margin: 0;
`;

const Extra = styled.p`
  margin: 0;
  font-size: 0.9rem;
  color: #eee;
`;

const Button = styled.button`
  background: #ff0099;
  color: white;
  padding: 6px 14px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: bold;
  transition: 0.2s;

  &:hover {
    background: #ff3fa3;
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
