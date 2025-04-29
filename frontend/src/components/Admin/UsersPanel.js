import React from "react";
import { Section, SectionTitle } from "./AdminStyles";

const UsersPanel = ({ users, onBlock, onDelete }) => (
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
              <button onClick={() => onBlock(user.id)} style={{ background: "#ff0099", border: "none", padding: "6px 10px", marginRight: 10, borderRadius: 6, cursor: "pointer", color: "white" }}>
                Blloko
              </button>
              <button onClick={() => onDelete(user.id)} style={{ background: "#ff3f3f", border: "none", padding: "6px 10px", borderRadius: 6, cursor: "pointer", color: "white" }}>
                Fshij
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  </Section>
);

export default UsersPanel;