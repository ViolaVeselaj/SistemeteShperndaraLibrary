import React, { useState } from "react";
import styled from "styled-components";
import Navbar from "../common/Navbar";
import BookCard from "../common/BookCard";

const Wrapper = styled.div`
  display: flex;
  width: 100%;
`;

const Sidebar = styled.div`
  position: fixed;
  top: 64px;
  left: 0;
  width: 22%;
  height: calc(100vh - 64px);
  background: #15162e;
  padding: 2rem 1rem;
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
  color: #fff;
`;

const AdminOption = styled.button`
  background-color: #3023ae;
  color: white;
  padding: 10px 16px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: bold;

  &:hover {
    background-color: #4632dc;
  }
`;

const Main = styled.div`
  margin-left: 22%;
  margin-top: 64px;
  padding: 2rem;
  width: 100%;
  min-height: calc(100vh - 64px);
  background: linear-gradient(to right, #1e1f3b, #3023ae);
  color: white;
`;

const Section = styled.div`
  background: rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 1.5rem;
  margin-bottom: 2rem;
`;

const SectionTitle = styled.h2`
  margin-bottom: 1rem;
  font-size: 1.5rem;
  color: #ffb6ec;
`;

const Stats = styled.div`
  display: flex;
  gap: 2rem;
  flex-wrap: wrap;
`;

const StatBox = styled.div`
  background: #ffffff11;
  border: 1px solid #ffffff22;
  padding: 1rem 1.5rem;
  border-radius: 10px;
  flex: 1;
  min-width: 250px;
  color: #eee;
`;

const AdminDashboard = () => {
  const [users, setUsers] = useState([
    { id: 1, name: "Arta Gashi", email: "arta@example.com", role: "USER" },
    { id: 2, name: "Blerim Hoxha", email: "blerim@example.com", role: "LIBRARIAN" },
    { id: 3, name: "Admin Test", email: "admin@example.com", role: "ADMIN" }
  ]);

  const topBooks = [
    { title: "Kafka on the Shore", extra: "50 herë huazuar" },
    { title: "1984", extra: "43 herë huazuar" }
  ];

  const missingBooks = [
    { title: "Libri i Humbur 1", extra: "Status: Mungon" },
    { title: "Libri i Humbur 2", extra: "Status: Mungon" }
  ];

  const reviews = [
    { title: "Atomic Habits", extra: "5 yje - 'Libër i shkëlqyer!'" },
    { title: "Deep Work", extra: "4 yje - 'Shumë praktik!'" }
  ];

  const returnCalendar = [
    { title: "Kafka on the Shore", date: "25 Prill 2025" },
    { title: "Atomic Habits", date: "28 Prill 2025" },
    { title: "The Power of Now", date: "30 Prill 2025" }
  ];

  const promotions = [
    "Pranvera Lexuese – 30% zbritje për romanet klasike (1-30 Prill)",
    "Muaji i Shkencës – Pako me 3 libra për çmimin e 2 (Maj 2025)"
  ];

  const suspiciousUsers = [
    { email: "blerta@gmail.com", note: "Ka vonuar 5 libra rresht" },
    { email: "eni.xh@hotmail.com", note: "Ka tentuar 10 herë të huazojë pa limit" },
    { email: "hidden_user@anon.com", note: "Rritje e papritur e aktivitetit brenda 1 ore" }
  ];

  const handleDelete = (id) => {
    const confirm = window.confirm("A je i sigurt që dëshiron të fshish këtë përdorues?");
    if (confirm) {
      setUsers(prev => prev.filter(user => user.id !== id));
    }
  };

  const handleBlock = (id) => {
    setUsers(prev =>
      prev.map(user =>
        user.id === id ? { ...user, role: "BLOCKED" } : user
      )
    );
  };

  return (
    <>
      <Navbar titles={["Paneli Adminit"]} />
      <Wrapper>
        <Sidebar>
          <h2>Admin Panel</h2>
          <AdminOption>Shto Libër</AdminOption>
          <AdminOption>Regjistro Përdorues</AdminOption>
          <AdminOption>Shiko Reviews</AdminOption>
          <AdminOption>Statistika</AdminOption>
          <AdminOption>Kalendar Kthimesh</AdminOption>
          <AdminOption>Fushata Promocionale</AdminOption>
          <AdminOption>Monitorim i Aktivitetit</AdminOption>
        </Sidebar>

        <Main>
          <Section>
            <SectionTitle>Librat më të Huazuar</SectionTitle>
            {topBooks.map((book, i) => (
              <BookCard key={i} title={book.title} extra={book.extra} />
            ))}
          </Section>

          <Section>
            <SectionTitle>Librat që mungojnë</SectionTitle>
            {missingBooks.map((book, i) => (
              <BookCard key={i} title={book.title} extra={book.extra} />
            ))}
          </Section>

          <Section>
            <SectionTitle>Reviews e fundit</SectionTitle>
            {reviews.map((review, i) => (
              <BookCard key={i} title={review.title} extra={review.extra} />
            ))}
          </Section>

          <Section>
            <SectionTitle>Statistika të Përgjithshme</SectionTitle>
            <Stats>
              <StatBox>
                <h4>Total Librash</h4>
                <p>520</p>
              </StatBox>
              <StatBox>
                <h4>Përdorues Aktiv</h4>
                <p>126</p>
              </StatBox>
              <StatBox>
                <h4>Huazime të Përgjithshme</h4>
                <p>943</p>
              </StatBox>
            </Stats>
          </Section>

          <Section>
            <SectionTitle>Kalendar – Afatet e Kthimit</SectionTitle>
            <ul>
              {returnCalendar.map((item, idx) => (
                <li key={idx}>
                  {item.title} – <strong>{item.date}</strong>
                </li>
              ))}
            </ul>
          </Section>

          <Section>
            <SectionTitle>Fushata Promocionale</SectionTitle>
            <ul>
              {promotions.map((promo, idx) => (
                <li key={idx}>{promo}</li>
              ))}
            </ul>
          </Section>

          <Section>
            <SectionTitle>Përdorues me Aktivitet të Dyshimtë</SectionTitle>
            <ul>
              {suspiciousUsers.map((user, idx) => (
                <li key={idx}>
                  <strong>{user.email}</strong> – {user.note}
                </li>
              ))}
            </ul>
          </Section>

          <Section>
            <SectionTitle>Paneli i Përdoruesve</SectionTitle>
            <table style={{ width: "100%", textAlign: "left", borderSpacing: "0 10px" }}>
              <thead>
                <tr style={{ color: "#ffb6ec" }}>
                  <th>Emër</th>
                  <th>Email</th>
                  <th>Roli</th>
                  <th>Veprime</th>
                </tr>
              </thead>
              <tbody>
                {users.map((user) => (
                  <tr key={user.id} style={{ background: "#ffffff11", color: "#eee" }}>
                    <td>{user.name}</td>
                    <td>{user.email}</td>
                    <td>
                      <select defaultValue={user.role} style={{ background: "#3023ae", color: "white", borderRadius: 4 }}>
                        <option value="USER">USER</option>
                        <option value="LIBRARIAN">LIBRARIAN</option>
                        <option value="ADMIN">ADMIN</option>
                        <option value="BLOCKED">BLOCKED</option>
                      </select>
                    </td>
                    <td>
                      <button onClick={() => handleBlock(user.id)} style={{ background: "#ff0099", border: "none", padding: "6px 10px", marginRight: 10, borderRadius: 6, cursor: "pointer", color: "white" }}>
                        Blloko
                      </button>
                      <button onClick={() => handleDelete(user.id)} style={{ background: "#ff3f3f", border: "none", padding: "6px 10px", borderRadius: 6, cursor: "pointer", color: "white" }}>
                        Fshij
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </Section>

          <Section>
            <SectionTitle>Njoftime Automatik</SectionTitle>
            <ul>
              <li>📅 Afat kthimi për <strong>"Atomic Habits"</strong> është nesër – do dërgohet email.</li>
              <li>📢 Promocioni <strong>"Pranvera Lexuese"</strong> fillon sot – do njoftohen të gjithë përdoruesit.</li>
              <li>📚 Libri <strong>"Kafka on the Shore"</strong> është kthyer – njoftim për ata që e kishin në pritje.</li>
            </ul>
          </Section>
        </Main>
      </Wrapper>
    </>
  );
};

export default AdminDashboard;
