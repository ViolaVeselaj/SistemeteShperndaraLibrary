import React from "react";
import { Section, SectionTitle } from "./AdminStyles";

const Promotions = ({ list }) => (
  <Section>
    <SectionTitle>Fushata Promocionale</SectionTitle>
    <ul>
      {list.map((promo, idx) => (
        <li key={idx}>{promo}</li>
      ))}
    </ul>
  </Section>
);

export default Promotions;