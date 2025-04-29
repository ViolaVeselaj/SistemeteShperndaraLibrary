import React from "react";
import { Section, SectionTitle } from "./AdminStyles";

const ReturnCalendar = ({ data }) => (
  <Section>
    <SectionTitle>Kalendar – Afatet e Kthimit</SectionTitle>
    <ul>
      {data.map((item, idx) => (
        <li key={idx}>
          {item.title} – <strong>{item.date}</strong>
        </li>
      ))}
    </ul>
  </Section>
);

export default ReturnCalendar;
