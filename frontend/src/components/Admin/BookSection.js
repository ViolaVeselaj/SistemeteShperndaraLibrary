import React from "react";
import BookCard from "../common/BookCard";
import { Section, SectionTitle } from "./AdminStyles";

const BookSection = ({ title, books }) => (
  <Section>
    <SectionTitle>{title}</SectionTitle>
    {books.map((book, i) => (
      <BookCard key={i} title={book.title} extra={book.extra} />
    ))}
  </Section>
);

export default BookSection;