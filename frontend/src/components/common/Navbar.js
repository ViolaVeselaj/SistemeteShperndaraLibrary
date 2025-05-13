// 🔄 Navbar me dropdown për profil, log out dhe kërkim funksional për libra (i lidhur me HomePage)
import React, { useState } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const NavbarWrapper = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: linear-gradient(to right, #3023ae, #6c3b95);
  padding: 1rem 2rem;
  color: white;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
`;

const NavLinks = styled.div`
  display: flex;
  gap: 1.5rem;

  a {
    color: white;
    text-decoration: none;
    font-weight: 500;
    &:hover {
      color: #ffb6ec;
    }
  }
`;

const SearchWrapper = styled.div`
  position: relative;
  display: flex;
  align-items: center;
`;

const SearchInput = styled.input`
  padding: 0.6rem 1rem;
  border-radius: 6px;
  border: none;
  font-size: 1rem;
  width: 250px;
`;

const Results = styled.ul`
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  background: white;
  color: black;
  border-radius: 10px;
  box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.15);
  margin-top: 0.5rem;
  list-style: none;
  padding: 0;
  z-index: 9999;
`;

const ResultItem = styled.li`
  padding: 0.8rem 1rem;
  border-bottom: 1px solid #eee;
  cursor: pointer;
  &:hover {
    background-color: #f2f2f2;
  }
`;

const ProfileWrapper = styled.div`
  position: relative;
`;

const ProfileButton = styled.button`
  background-color: #ff69b4;
  border: none;
  border-radius: 20px;
  padding: 0.6rem 1.2rem;
  color: white;
  font-weight: bold;
  cursor: pointer;

  &:hover {
    background-color: #ff85c1;
  }
`;

const Dropdown = styled.div`
  position: absolute;
  top: 110%;
  right: 0;
  background-color: white;
  color: black;
  border-radius: 8px;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.2);
  width: 160px;
  z-index: 9999;

  a, button {
    display: block;
    width: 100%;
    padding: 0.8rem 1rem;
    background: none;
    border: none;
    text-align: left;
    font-size: 1rem;
    cursor: pointer;

    &:hover {
      background-color: #f1f1f1;
    }
  }
`;

const Navbar = ({ titles = [], books = [], query, setQuery }) => {
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const navigate = useNavigate();

  const filteredBooks = (books || []).filter(book =>
    book.title.toLowerCase().includes(query.toLowerCase()) ||
    book.author.toLowerCase().includes(query.toLowerCase())
  );

  return (
    <NavbarWrapper>
      <NavLinks>
  {titles.map((title, index) => (
    <button
      key={index}
      onClick={() => navigate(`/${title.toLowerCase().replace(/ /g, '-')}`)}
      style={{
        background: "none",
        border: "none",
        color: "white",
        fontWeight: "500",
        cursor: "pointer",
        fontSize: "1rem"
      }}
    >
      {title}
    </button>
  ))}
</NavLinks>


      <SearchWrapper>
        <SearchInput
          type="text"
          placeholder="Kërko..."
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
        {query && (
          <Results>
            {filteredBooks.length === 0 ? (
              <ResultItem>Asnjë rezultat</ResultItem>
            ) : (
              filteredBooks.map(book => (
                <ResultItem key={book.id}>
                  <strong>{book.title}</strong> — {book.author}
                </ResultItem>
              ))
            )}
          </Results>
        )}
      </SearchWrapper>

      <ProfileWrapper>
        <ProfileButton onClick={() => setDropdownOpen(!dropdownOpen)}>
          More ▾
        </ProfileButton>

        {dropdownOpen && (
          <Dropdown>
            <button onClick={() => navigate("/profile")}>Profili</button>
            <button onClick={() => alert("Duke u shkyçur...")}>Log Out</button>
          </Dropdown>
        )}
      </ProfileWrapper>
    </NavbarWrapper>
  );
};

export default Navbar;