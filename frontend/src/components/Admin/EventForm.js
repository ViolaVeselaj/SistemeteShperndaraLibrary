// EventForm.js
import React, { useState } from "react";
import axios from '../utils/axiosInstance';


const styles = {
  container: {
    background: "white",
    padding: "2rem",
    marginTop: "1rem",
    borderRadius: "12px",
    boxShadow: "0 0 10px rgba(0,0,0,0.1)",
    maxWidth: "700px",
    marginLeft: "auto",
    marginRight: "auto",
  },
  heading: {
    marginBottom: "1.5rem",
    color: "#3b3b3b",
  },
  input: {
    display: "block",
    width: "100%",
    padding: "0.7rem 1rem",
    marginBottom: "1rem",
    border: "1px solid #ccc",
    borderRadius: "6px",
    fontSize: "1rem",
  },
  button: {
    padding: "0.8rem 1.5rem",
    backgroundColor: "#3023ae",
    color: "white",
    border: "none",
    borderRadius: "6px",
    cursor: "pointer",
    fontWeight: "bold",
  },
  buttonHover: {
    backgroundColor: "#241b8c",
  },
};

const EventForm = ({ onEventCreated }) => {
    const [formData, setFormData] = useState({
      title: "",
      description: "",
      location: "",
      eventDate: ""
    });
  
    const handleChange = (e) => {
      setFormData({ ...formData, [e.target.name]: e.target.value });
    };
  
    const handleSubmit = async (e) => {
      e.preventDefault();
      const token = localStorage.getItem("token");
      const formattedData = {
        ...formData,
        eventDate: formData.eventDate + ":00Z" // FIX për Instant parsing!
      };
  
      try {
        const res = await axios.post("http://localhost:8080/events", formattedData, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
    
        if (res.status === 200 || res.status === 201) {
          alert("Eventi u shtua me sukses!");
          setFormData({
            title: "",
            description: "",
            location: "",
            eventDate: ""
          });
          onEventCreated();
        } else {
          alert("Gabim gjatë shtimit të eventit");
        }
      } catch (err) {
        console.error("Gabim gjatë postimit të eventit:", err);
        alert("Gabim gjatë shtimit të eventit");
      }
    };
  
    return (
      <form onSubmit={handleSubmit} style={styles.container}>
        <h2 style={styles.heading}>Krijo Event</h2>
        <input
          type="text"
          name="title"
          placeholder="Titulli"
          value={formData.title}
          onChange={handleChange}
          required
          style={styles.input}
        />
        <textarea
          name="description"
          placeholder="Përshkrimi"
          value={formData.description}
          onChange={handleChange}
          style={styles.input}
        />
        <input
          type="text"
          name="location"
          placeholder="Lokacioni"
          value={formData.location}
          onChange={handleChange}
          style={styles.input}
        />
        <input
          type="datetime-local"
          name="eventDate"
          value={formData.eventDate}
          onChange={handleChange}
          required
          style={styles.input}
          
        />
        <button type="submit" style={styles.button}>Shto Event</button>
      </form>
    );
  };
  
export default EventForm;


