import React, { useEffect, useState, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";

const LoanRequestsPanel = () => {
  const { token } = useContext(AuthContext);
  const [loans, setLoans] = useState([]);

  useEffect(() => {
    const fetchLoans = async () => {
      try {
        const res = await fetch("http://localhost:8080/loans/pending", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        const data = await res.json();
        setLoans(data);
      } catch (error) {
        console.error("Gabim gjatë marrjes së kërkesave:", error);
      }
    };

    fetchLoans();
  }, [token]);

  const handleAccept = async (id) => {
    try {
      await fetch(`http://localhost:8080/loans/${id}/approve`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setLoans((prev) => prev.filter((loan) => loan.id !== id));
    } catch (error) {
      console.error("Gabim gjatë aprovimit:", error);
    }
  };

  const handleDeny = async (id) => {
    try {
      await fetch(`http://localhost:8080/loans/${id}/reject`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setLoans((prev) => prev.filter((loan) => loan.id !== id));
    } catch (error) {
      console.error("Gabim gjatë refuzimit:", error);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2 style={{ marginBottom: "16px" }}>📚 Kërkesat për Huazim</h2>
      <table style={{ width: "100%", borderCollapse: "collapse" }}>
        <thead>
          <tr style={{ backgroundColor: "#f2f2f2" }}>
            <th style={thStyle}>Përdoruesi</th>
            <th style={thStyle}>Libri</th>
            <th style={thStyle}>Data</th>
            <th style={thStyle}>Veprim</th>
          </tr>
        </thead>
        <tbody>
          {loans.map((loan) => (
            <tr key={loan.id}>
              <td style={tdStyle}>{loan.user?.name || "—"}</td>
              <td style={tdStyle}>{loan.book?.title || "—"}</td>
              <td style={tdStyle}>
                {loan.borrowDate
                  ? new Date(loan.borrowDate).toLocaleDateString()
                  : "—"}
              </td>
              <td style={tdStyle}>
                <button style={acceptBtn} onClick={() => handleAccept(loan.id)}>
                  Accept
                </button>
                <button style={denyBtn} onClick={() => handleDeny(loan.id)}>
                  Deny
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {loans.length === 0 && <p style={{ marginTop: "16px" }}>Nuk ka kërkesa për momentin.</p>}
    </div>
  );
};

// Style helpers
const thStyle = {
  padding: "10px",
  borderBottom: "2px solid #ddd",
  textAlign: "left",
};

const tdStyle = {
  padding: "10px",
  borderBottom: "1px solid #eee",
};

const acceptBtn = {
  marginRight: "8px",
  padding: "6px 12px",
  backgroundColor: "#4CAF50",
  color: "white",
  border: "none",
  borderRadius: "4px",
  cursor: "pointer",
};

const denyBtn = {
  padding: "6px 12px",
  backgroundColor: "#f44336",
  color: "white",
  border: "none",
  borderRadius: "4px",
  cursor: "pointer",
};

export default LoanRequestsPanel;
