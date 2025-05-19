// src/components/admin/AdminStyles.js
import styled from "styled-components";

export const Wrapper = styled.div`
  display: flex;
  width: 100%;
`;

export const SidebarWrapper = styled.div`
  position: fixed;
  top: 64px;
  left: 0;
  width: 22%;
  height: calc(100vh - 64px);
  background: #15162e;
  padding: 2rem 1rem;
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
  color: #fff;
`;

export const AdminOption = styled.button`
  background-color: #3023ae;
  color: white;
  padding: 10px 16px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: bold;

  &:hover {
    background-color: #4632dc;
  }
`;

export const Main = styled.div`
  margin-left: 22%;
  margin-top: 64px;
  padding: 2rem;
  width: 100%;
  min-height: calc(100vh - 64px);
  background: linear-gradient(to right, #1e1f3b, #3023ae);
  color: white;
`;

export const Section = styled.div`
  background: rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 1.5rem;
  margin-bottom: 2rem;
`;

export const SectionTitle = styled.h2`
  margin-bottom: 1rem;
  font-size: 1.5rem;
  color: #ffb6ec;
`;

export const Stats = styled.div`
  display: flex;
  gap: 2rem;
  flex-wrap: wrap;
`;

export const StatBox = styled.div`
  background: #ffffff11;
  border: 1px solid #ffffff22;
  padding: 1rem 1.5rem;
  border-radius: 10px;
  flex: 1;
  min-width: 250px;
  color: #eee;
`;

export const EventItem = styled.div`
  background: rgba(255, 255, 255, 0.08);
  padding: 1.2rem;
  border-radius: 10px;
  margin-bottom: 1rem;
  border-left: 5px solid #8a63d2;
  color: #fff;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
`;

export const EventListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;

