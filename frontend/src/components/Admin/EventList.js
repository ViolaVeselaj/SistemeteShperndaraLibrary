// src/components/admin/EventList.js
import React, { useEffect, useState } from "react";
import axios from '../utils/axiosInstance';
import { Section, SectionTitle, EventItem, EventListWrapper } from "./AdminStyles";
import styled from "styled-components";



  
  

const EventList = () => {
  const [events, setEvents] = useState([]);

  

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const token = localStorage.getItem("token");
        console.log("TOKEN:", token); // kontroll në console
  
        const res = await axios.get("http://localhost:8080/events", {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
  
        console.log("Eventet:", res.data);
        setEvents(res.data);
      } catch (error) {
        console.error("Gabim gjatë marrjes së eventeve:", error);
      }
    };
  
    fetchEvents();
  }, []);
  

  return (
    <Section>
      <SectionTitle>Lista e Eventeve</SectionTitle>
      {events.map((event, index) => (
        <EventItem key={index}>
          <h3>{event.title}</h3>
          <p>{event.description}</p>
          <p><strong>Lokacioni:</strong> {event.location}</p>
          <p><strong>Data:</strong> {new Date(event.eventDate).toLocaleString()}</p>
        </EventItem>
      ))}
    </Section>
  );
};

export default EventList;