
import React, { useState } from "react";
import Navbar from "../common/Navbar";
import { Wrapper, Main } from "./AdminStyles";
import Sidebar from "./Sidebar";
import BookSection from "./BookSection";
import StatsSection from "./StatsSection";
import ReturnCalendar from "./ReturnCalendar";
import Promotions from "./Promotions";
import SuspiciousUsers from "./SuspiciousUsers";
import UsersPanel from "./UsersPanel";
import Notifications from "./Notifications";
import LoanRequestsPanel from "./LoanRequestsPanel";
import EventList from "./EventList"; // ose rruga relative që ke ti
import ReviewList from "./ReviewList";






const AdminDashboard = () => {
  
  return (
    <>
      <Navbar titles={["Paneli Adminit"]} />
      <Wrapper>
        <Sidebar />
        <Main>
          <ReviewList/>
          <EventList />
        </Main>
      </Wrapper>
    </>
  );
};

export default AdminDashboard;