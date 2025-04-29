import React from "react";
import { Section, SectionTitle } from "./AdminStyles";

const Notifications = () => (
  <Section>
    <SectionTitle>Njoftime Automatik</SectionTitle>
    <ul>
      <li>📅 Afat kthimi për <strong>"Atomic Habits"</strong> është nesër – do dërgohet email.</li>
      <li>📢 Promocioni <strong>"Pranvera Lexuese"</strong> fillon sot – do njoftohen të gjithë përdoruesit.</li>
      <li>📚 Libri <strong>"Kafka on the Shore"</strong> është kthyer – njoftim për ata që e kishin në pritje.</li>
    </ul>
  </Section>
);

export default Notifications;