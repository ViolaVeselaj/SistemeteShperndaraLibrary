import React, { useState, useEffect, useMemo } from "react";
import styled from "styled-components";
import axios from '../utils/axiosInstance';
import Navbar from "../common/Navbar";
import Footer from "../common/Footer";
import { useNavigate } from "react-router-dom";

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

function UserHomePage() {
  const navigate = useNavigate();
  const handleBookClick = (bookId) => {
    navigate(`/books/${bookId}`);
  };

  const [layout, setLayout] = useState("grid");
  const [query, setQuery] = useState("");
  const [books, setBooks] = useState([]);
  const [showReviewForm, setShowReviewForm] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null);
  const [reviewData, setReviewData] = useState({ rating: 5, comment: "" });

  const handleOpenReviewForm = (book) => {
    setSelectedBook(book);
    setShowReviewForm(true);
  };

  const handleSubmitReview = async () => {
    try {
      await axios.post("/reviews", {
        bookId: selectedBook.id,
        rating: reviewData.rating,
        comment: reviewData.comment,
      });
      alert("Review u dërgua me sukses!");
      setShowReviewForm(false);
      setReviewData({ rating: 5, comment: "" });
    } catch (error) {
      console.error("Gabim gjatë dërguar review-it:", error);
      alert("Dërgimi i review-it dështoi.");
    }
  };

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await axios.get("http://localhost:8080/books");
        setBooks(response.data);
      } catch (error) {
        console.error("Gabim gjatë marrjes së librave:", error);
      }
    };

    fetchBooks();
    window.scrollTo(0, 0);
  }, []);

  const filteredBooks = useMemo(() => {
    return books.filter((book) =>
      book.title.toLowerCase().includes(query.toLowerCase()) ||
      (book.author1 && book.author1.toLowerCase().includes(query.toLowerCase()))
    );
  }, [books, query]);

  const genres = ["Roman", "Frymëzim", "Shkencë", "Fantazi", "Histori"];

  const genreBooks = useMemo(() => {
    if (layout !== "horizontal") return [];
    return genres.map((genre, index) =>
      books
        .filter((book) =>
          book.title.toLowerCase().includes(genre.toLowerCase())
        )
        .slice(0, 10)
        .map((book, i) => ({
          ...book,
          id: `${index}-${i}`,
        }))
    );
  }, [layout, books]);

  return (
    <>
      <Navbar
        titles={["Ballina", "Zhanret", "Rekomandime", "Biblioteka Ime"]}
        books={books}
        query={query}
        setQuery={setQuery}
      />

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
            {filteredBooks.map((book) => (
              <BookCard key={book.id}>
                <img
                  src="https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=60"
                  alt={book.title}
                />
                <h3>{book.title}</h3>
                <p>{book.author1}</p>
                <button onClick={() => handleOpenReviewForm(book)}>Shto Review</button>
              </BookCard>
            ))}
          </BookGrid>
        )}

        {layout === "single" && (
          <BookGrid layout="single">
            {filteredBooks.map((book) => (
              <BookCard key={book.id}>
                <img
                  src="https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=60"
                  alt={book.title}
                />
                <div className="info">
                  <h3>{book.title}</h3>
                  <p className="author">{book.author1}</p>
                  <p className="description">
                    Përshkrimi do të vijë nga databaza në versionin e ardhshëm.
                  </p>
                  <button onClick={() => handleOpenReviewForm(book)}>Shto Review</button>
                </div>
              </BookCard>
            ))}
          </BookGrid>
        )}

        {layout === "carousel" && (
          <CarouselWrapper>
            {filteredBooks.map((book) => (
              <CarouselCard key={book.id}>
                <img
                  src="https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=60"
                  alt={book.title}
                />
                <h3>{book.title}</h3>
                <p>{book.author1}</p>
                <button onClick={() => handleOpenReviewForm(book)}>Shto Review</button>
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
                      <img
                        src="https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=60"
                        alt={book.title}
                      />
                      <h3>{book.title}</h3>
                      <p>{book.author1}</p>
                      <button onClick={() => handleOpenReviewForm(book)}>Shto Review</button>
                    </HorizontalBookCard>
                  ))}
                </HorizontalScrollContainer>
              </HorizontalScrollSection>
            ))}
          </>
        )}

        {showReviewForm && (
          <div style={{ background: "#222", padding: "2rem", borderRadius: "10px", color: "white" }}>
            <h3>Jep vlerësimin për: {selectedBook.title}</h3>
            <label>Rating (1-5):</label>
            <input
              type="number"
              min="1"
              max="5"
              value={reviewData.rating}
              onChange={(e) => setReviewData({ ...reviewData, rating: e.target.value })}
            /><br />
            <label>Komenti:</label>
            <textarea
              rows="4"
              cols="40"
              value={reviewData.comment}
              onChange={(e) => setReviewData({ ...reviewData, comment: e.target.value })}
            ></textarea><br />
            <button onClick={handleSubmitReview}>Dërgo Review</button>
            <button onClick={() => setShowReviewForm(false)}>Anulo</button>
          </div>
        )}
      </LayoutWrapper>

      <Footer />
    </>
  );
}

export default UserHomePage;