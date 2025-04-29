import React from "react";
import { Section, SectionTitle } from "./AdminStyles";

const SuspiciousUsers = ({ users }) => (
  <Section>
    <SectionTitle>Përdorues me Aktivitet të Dyshimtë</SectionTitle>
    <ul>
      {users.map((user, idx) => (
        <li key={idx}>
          <strong>{user.email}</strong> – {user.note}
        </li>
      ))}
    </ul>
  </Section>
);

export default SuspiciousUsers;