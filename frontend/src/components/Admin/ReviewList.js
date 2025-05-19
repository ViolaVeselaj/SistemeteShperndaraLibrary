import React, { useEffect, useState } from "react";
import axios from '../utils/axiosInstance';
import { Section, SectionTitle, ReviewItem } from "./AdminStyles";

const ReviewList = () => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const token = localStorage.getItem("token");

        const res = await axios.get("/api/reviews", {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });

        setReviews(res.data);
      } catch (error) {
        console.error("Gabim gjatë marrjes së reviews:", error);
      }
    };

    fetchReviews();
  }, []);

  return (
    <Section>
      <SectionTitle>Lista e Reviews</SectionTitle>
      {reviews.map((review, index) => (
        <ReviewItem key={index}>
          <p><strong>Komenti:</strong> {review.comment}</p>
          <p><strong>Rating:</strong> {review.rating}</p>
          <p><strong>Data:</strong> {new Date(review.created_at).toLocaleString()}</p>
        </ReviewItem>
      ))}
    </Section>
  );
};

export default ReviewList;
    