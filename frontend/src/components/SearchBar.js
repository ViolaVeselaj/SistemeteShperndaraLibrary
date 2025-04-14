// src/components/SearchBar.js
import React, { useState } from 'react';
import styled from 'styled-components';
import { FiSearch } from 'react-icons/fi';

const SearchWrapper = styled.div`
  display: flex;
  justify-content: center;
  padding: 2rem 1rem;
  margin-top: 70px;
`;

const SearchInputWrapper = styled.div`
  position: relative;
  width: 100%;
  max-width: 500px;
`;

const SearchInput = styled.input`
  width: 100%;
  padding: 12px 48px 12px 20px;
  border: none;
  border-radius: 50px;
  background-color: #f1f1f1;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.1);
  font-size: 1rem;

  &:focus {
    outline: none;
    background-color: #fff;
  }
`;

const SearchIcon = styled(FiSearch)`
  position: absolute;
  right: 16px;
  top: 50%;
  transform: translateY(-50%);
  color: #666;
  pointer-events: none;
`;

const Results = styled.ul`
  margin-top: 1rem;
  list-style: none;
  padding: 0;
  max-width: 500px;
  margin-left: auto;
  margin-right: auto;
  color: #000;
  background: white;
  border-radius: 10px;
  box-shadow: 0px 2px 6px rgba(0,0,0,0.15);
`;

const SearchBar = ({ books }) => {
  const [query, setQuery] = useState('');

  const filteredBooks = books.filter(book =>
    book.title.toLowerCase().includes(query.toLowerCase()) ||
    book.author.toLowerCase().includes(query.toLowerCase())
  );

  return (
    <>
      <SearchWrapper>
        <SearchInputWrapper>
          <SearchInput
            type="text"
            placeholder="Kërko..."
            value={query}
            onChange={e => setQuery(e.target.value)}
          />
          <SearchIcon size={20} />
        </SearchInputWrapper>
      </SearchWrapper>

      {query && (
        <Results>
          {filteredBooks.length === 0 ? (
            <li style={{ padding: '1rem', textAlign: 'center' }}>Asnjë rezultat</li>
          ) : (
            filteredBooks.map(book => (
              <li key={book.id} style={{ padding: '0.8rem 1rem', borderBottom: '1px solid #eee' }}>
                <strong>{book.title}</strong> — {book.author}
              </li>
            ))
          )}
        </Results>
      )}
    </>
  );
};

export default SearchBar;
