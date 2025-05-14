import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from '../utils/axiosInstance';
import styled from "styled-components";

const Container = styled.div`
  display: flex;
  margin-top: 80px;
  padding: 2rem;
  min-height: calc(100vh - 80px);
  background: linear-gradient(to right, #2b2c49, #5d317b);
  color: white;
  font-family: sans-serif;
`;

const LeftSection = styled.div`
  width: 30%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-right: 2rem;

  img {
    width: 100%;
    max-width: 250px;
    border-radius: 8px;
    margin-bottom: 1.5rem;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.4);
  }

  h2 {
    text-align: center;
    color: #ffb6ec;
  }
`;

const RightSection = styled.div`
  flex: 1;
  background: rgba(255, 255, 255, 0.08);
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
`;

const Description = styled.p`
  font-size: 1rem;
  line-height: 1.6;
  margin-bottom: 2rem;
`;

const ButtonGroup = styled.div`
  display: flex;
  gap: 1rem;

  button {
    padding: 10px 18px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-weight: bold;
    font-size: 0.95rem;
    transition: 0.3s;
  }

  .borrow {
    background: #0099ff;
    color: white;

    &:hover {
      background: #007acc;
    }
  }

  .buy {
    background: #00c853;
    color: white;

    &:hover {
      background: #009624;
    }
  }

  .favorite {
    background: #ff4081;
    color: white;

    &:hover {
      background: #e91e63;
    }
  }
`;

const BookDetails = () => {
  const { id } = useParams(); // merr id nga URL
  const [book, setBook] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios.get(`http://localhost:8080/books/${id}`)
      .then((res) => {
        setBook(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Gabim gjatë marrjes së librit:", err);
        setLoading(false);
      });
  }, [id]);

  if (loading) return <p style={{ color: "white", padding: "2rem" }}>Duke u ngarkuar...</p>;
  if (!book) return <p style={{ color: "white", padding: "2rem" }}>Libri nuk u gjet.</p>;

  return (
    <Container>
      <LeftSection>
        <img src={book.imageUrl || "/cover-placeholder.png"} alt={book.title} />
        <h2>{book.title}</h2>
      </LeftSection>

      <RightSection>
        <Description>
          <strong>Autori:</strong> {book.author?.firstName} {book.author?.lastName}<br />
          <strong>Viti i botimit:</strong> {book.publishedYear}<br />
          <strong>Gjendje:</strong> {book.availableCopies} kopje<br /><br />
          <em>{book.description || "Ky libër nuk ka një përshkrim të detajuar."}</em>
        </Description>

        <ButtonGroup>
          <button className="borrow">Huazo</button>
          <button className="buy">Bli</button>
          <button className="favorite">Shto në Favourite</button>
        </ButtonGroup>
      </RightSection>
    </Container>
  );
};

export default BookDetails;
