import React from "react";
import styled from "styled-components";
import BookCard from "../common/BookCard";
import Navbar from "../common/Navbar";

// LAYOUT
const Wrapper = styled.div`
  display: flex;
  width: 100%;
`;

// SIDEBAR FIKS
const Sidebar = styled.div`
  position: fixed;
  top: 64px;
  left: 0;
  width: 25%;
  height: calc(100vh - 64px);
  background: #1e1f3b;
  padding: 2rem 1.5rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  color: white;
`;

// PROFILI & BUTTONAT
const ProfileInfo = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;

  p {
    margin: 0;
    line-height: 1.6;
  }

  strong {
    color: #ffb6ec;
  }
`;

const SidebarButtons = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;

  button {
    padding: 10px 16px;
    background-color: #ff0099;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-weight: bold;

    &:hover {
      background-color: #ff3fa3;
    }
  }
`;

// MAIN CONTENT
const MainContent = styled.div`
  margin-left: 25%;
  margin-top: 64px;
  height: calc(100vh - 64px);
  overflow-y: auto;
  padding: 2rem 3rem;
  flex: 1;
  background: linear-gradient(to right, #2b2c49, #5d317b);
`;

const Section = styled.div`
  background: rgba(255, 255, 255, 0.06);
  padding: 1.5rem;
  border-radius: 5px;
  margin: 2rem auto;
  width: 90%;
  max-width: 800px;
`;

const TitleRow = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Title = styled.h2`
  font-size: 1.4rem;
  margin: 0;
`;

const SeeAllButton = styled.button`
  background: transparent;
  border: 1px solid #ff0099;
  color: #ff0099;
  font-size: 0.85rem;
  padding: 6px 12px;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 500;
  transition: 0.3s;

  &:hover {
    background: #ff0099;
    color: white;
  }
`;

// KOMPONENTI KRYESOR
const UserProfile = () => {
  const mockUser = {
    name: "Arta Gashi",
    email: "arta@example.com",
    role: "USER"
  };

  const mockLoans = [
    { title: "Kafka on the Shore", status: "Borrowed" },
    { title: "Atomic Habits", status: "Returned" }
  ];

  const mockPurchases = [
    { title: "Deep Work", author: "Cal Newport" },
    { title: "Rich Dad Poor Dad", author: "Robert Kiyosaki" }
  ];

  const mockFavorites = [
    { title: "The Alchemist", author: "Paulo Coelho" },
    { title: "1984", author: "George Orwell" }
  ];

  return (
    <>
    <Navbar titles={["Huazuar", "Blerë", "Preferuar"]} />
      <Wrapper>
        <Sidebar>
          <ProfileInfo>
            <h2>Profili</h2>
            <p><strong>Emri:</strong> {mockUser.name}</p>
            <p><strong>Email:</strong> {mockUser.email}</p>
            <p><strong>Roli:</strong> {mockUser.role}</p>
          </ProfileInfo>

          <SidebarButtons>
            <button>Subscriptions</button>
            <button>Log Out</button>
          </SidebarButtons>
        </Sidebar>

        <MainContent>
          <Section>
            <TitleRow>
              <Title>Librat e Huazuar</Title>
              <SeeAllButton>See all</SeeAllButton>
            </TitleRow>
            {mockLoans.map((book, idx) => (
              <BookCard key={idx} title={book.title} extra={`Statusi: ${book.status}`} />
            ))}
          </Section>

          <Section>
            <TitleRow>
              <Title>Librat e Blerë</Title>
              <SeeAllButton>See all</SeeAllButton>
            </TitleRow>
            {mockPurchases.map((book, idx) => (
              <BookCard key={idx} title={book.title} extra={`Autori: ${book.author}`} />
            ))}
          </Section>

          <Section>
            <TitleRow>
              <Title>Librat e Preferuar</Title>
              <SeeAllButton>See all</SeeAllButton>
            </TitleRow>
            {mockFavorites.map((book, idx) => (
              <BookCard key={idx} title={book.title} extra={`Autori: ${book.author}`} />
            ))}
          </Section>
        </MainContent>
      </Wrapper>
    </>
  );
};

export default UserProfile;
