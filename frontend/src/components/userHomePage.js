import React, { useState, useEffect, useMemo } from "react";
import styled from "styled-components";
import Navbar from "./Navbar";
import Footer from "./Footer";
import SearchBar from "../components/SearchBar";


const LayoutWrapper = styled.div`
  padding: 100px 2rem 2rem 2rem;
  min-height: 100vh;
  background: linear-gradient(to right, #2b2c49, #5d317b);
  color: white;
`;

const LayoutSelector = styled.select`
  margin: 1rem 0;
  padding: 0.8rem 1.2rem;
  border-radius: 8px;
  border: none;
  font-size: 1rem;
  background-color: #3023ae;
  color: white;
`;

const BookGrid = styled.div`
  display: ${({ layout }) => (layout === "single" ? "flex" : "grid")};
  flex-direction: column;
  gap: 2rem;
  margin-top: 2rem;

  grid-template-columns: ${({ layout }) =>
    layout === "grid" ? "repeat(5, 1fr)" : "1fr"};
`;


const BookCard = styled.div`
  background: #fff;
  color: #222;
  border-radius: 10px;
  padding: 1rem;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.2);
  text-align: center;

  img {
    width: 100%;
    height: 250px;
    object-fit: cover;
    border-radius: 6px;
    margin-bottom: 1rem;
  }

  h3 {
    font-size: 1.1rem;
    margin: 0.5rem 0 0.2rem;
  }

  p {
    font-size: 0.95rem;
    color: #555;
  }
`;


const CarouselWrapper = styled.div`
  display: flex;
  overflow-x: auto;
  scroll-behavior: smooth;
  gap: 1.5rem;
  margin-top: 2rem;
  padding-bottom: 1rem;

  &::-webkit-scrollbar {
    height: 10px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #888;
    border-radius: 10px;
  }
`;

const CarouselCard = styled(BookCard)`
  flex: 0 0 300px;
`;

const HorizontalScrollSection = styled.div`
  margin-top: 3rem;

  h2 {
    color: #ffb6ec;
    margin-bottom: 1rem;
  }
`;

const HorizontalScrollContainer = styled.div`
  display: flex;
  overflow-x: auto;
  gap: 1.2rem;
  padding-bottom: 1rem;
  scroll-behavior: smooth;

  &::-webkit-scrollbar {
    height: 8px;
  }

  &::-webkit-scrollbar-thumb {
    background-color: #555;
    border-radius: 6px;
  }
`;

const HorizontalBookCard = styled(BookCard)`
  flex: 0 0 200px;
`;

const UserHomePage = () => {
  const [layout, setLayout] = useState("grid");

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const mockBooks = useMemo(
    () =>
      Array.from({ length: 15 }, (_, i) => ({
        id: i,
        title: `Libri ${i + 1}`,
        author: `Autori ${i + 1}`,
        image:
          "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=60",
      })),
    []
  );

  const genres = ["Roman", "Frymëzim", "Shkencë", "Fantazi", "Histori"];

  const genreBooks = useMemo(() => {
    if (layout !== "horizontal") return [];
    return genres.map((genre, index) =>
      Array.from({ length: 10 }, (_, i) => ({
        id: `${index}-${i}`,
        title: `${genre} - Libri ${i + 1}`,
        author: `Autori ${i + 1}`,
        genre,
        image:
          "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=60",
      }))
    );
  }, [layout]);


  return (
    <>
      <Navbar titles={["Ballina", "Rekomandime", "Biblioteka Ime"]} />
      
      <SearchBar books={mockBooks} />

      <LayoutWrapper>
        <LayoutSelector
          value={layout}
          onChange={(e) => setLayout(e.target.value)}
        >
          <option value="grid">Grid Layout</option>
          <option value="single">Single Column</option>
          <option value="carousel">Carousel</option>
          <option value="horizontal">Sipas Zhanrit</option>
        </LayoutSelector>

        {layout === "grid" && (
          <BookGrid layout="grid">
            {mockBooks.map((book) => (
              <BookCard key={book.id}>
                <img src={book.image} alt={book.title} />
                <h3>{book.title}</h3>
                <p>{book.author}</p>
              </BookCard>
            ))}
          </BookGrid>
        )}


        {layout === "single" && (
          <BookGrid layout="single">
            {mockBooks.map((book) => (
              <BookCard key={book.id}>
                <img src={book.image} alt={book.title} />
                <div className="info">
                  <h3>{book.title}</h3>
                  <p className="author">{book.author}</p>
                  <p className="description">
                    Ky është një përshkrim i librit që do të vijë nga databaza. Ai mund të përmbajë përmbajtje, mesazhe ose informacione për autorin.
                  </p>
                </div>
              </BookCard>
            ))}
          </BookGrid>
        )}


        {layout === "carousel" && (
          <CarouselWrapper>
            {mockBooks.map((book) => (
              <CarouselCard key={book.id}>
                <img src={book.image} alt={book.title} />
                <h3>{book.title}</h3>
                <p>{book.author}</p>
              </CarouselCard>
            ))}
          </CarouselWrapper>
        )}

        {layout === "horizontal" && (
          <>
            {genreBooks.map((books, idx) => (
              <HorizontalScrollSection key={idx}>
                <h2>{genres[idx]}</h2>
                <HorizontalScrollContainer>
                  {books.map((book) => (
                    <HorizontalBookCard key={book.id}>
                      <img src={book.image} alt={book.title} />
                      <h3>{book.title}</h3>
                      <p>{book.author}</p>
                    </HorizontalBookCard>
                  ))}
                </HorizontalScrollContainer>
              </HorizontalScrollSection>
            ))}
          </>
        )}
      </LayoutWrapper>
      <Footer />
    </>
  );
};

export default UserHomePage;
