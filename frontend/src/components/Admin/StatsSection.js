import React from "react";
import { Section, SectionTitle, Stats, StatBox } from "./AdminStyles";

const StatsSection = () => (
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
);

export default StatsSection;