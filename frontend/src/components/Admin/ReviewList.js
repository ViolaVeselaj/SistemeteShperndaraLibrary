import React, { useEffect, useState } from "react";
import axios from '../utils/axiosInstance';
import { Section, SectionTitle, ReviewItem } from "./AdminStyles";

const ReviewList = () => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const token = localStorage.getItem("token");

        const res = await axios.get("/reviews", {
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

  useEffect(() => {
  const fetchReviews = async () => {
    try {
      const res = await axios.get("/reviews");
      console.log("REVIEW COUNT:", res.data.length);
      console.log("ALL REVIEWS:", res.data);
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
        </ReviewItem>
      ))}
    </Section>
  );
};

export default ReviewList;
