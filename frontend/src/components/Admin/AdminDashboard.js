
import React, { useState } from "react";
import Navbar from "../common/Navbar";
import { Wrapper, Main } from "./AdminStyles";
import Sidebar from "./Sidebar";
import BookSection from "./BookSection";
import StatsSection from "./StatsSection";
import ReturnCalendar from "./ReturnCalendar";
import Promotions from "./Promotions";
import SuspiciousUsers from "./SuspiciousUsers";
import UsersPanel from "./UsersPanel";
import Notifications from "./Notifications";
import LoanRequestsPanel from "./LoanRequestsPanel";
import EventList from "./EventList"; // ose rruga relative që ke ti







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
        <Sidebar />
        <Main>
          <BookSection title="Librat më të Huazuar" books={topBooks} />
          <BookSection title="Librat që mungojnë" books={missingBooks} />
          <BookSection title="Reviews e fundit" books={reviews} />
          <StatsSection />
          <ReturnCalendar data={returnCalendar} />
          <Promotions list={promotions} />
          <EventList />
          <SuspiciousUsers users={suspiciousUsers} />
          <UsersPanel users={users} onBlock={handleBlock} onDelete={handleDelete} />
          <LoanRequestsPanel />
          <Notifications />
        </Main>
      </Wrapper>
    </>
  );
};

export default AdminDashboard;