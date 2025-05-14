import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import { ToastContainer, toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import axios from "../utils/axiosInstance"; // përdor axiosInstance me token automatik

const AddAuthorForm = () => {
  const formik = useFormik({
    initialValues: {
      firstName: "",
      lastName: "",
      biography: "",
      birthDate: "",
      nationality: "",
    },
    validationSchema: Yup.object({
      firstName: Yup.string().required("Required"),
      lastName: Yup.string().required("Required"),
      biography: Yup.string(),
      birthDate: Yup.date().nullable(),
      nationality: Yup.string().required("Required"),
    }),
    onSubmit: async (values, { resetForm }) => {
      try {
        await axios.post("/authors/create", values);
        toast.success("Author created successfully!");
        resetForm();
      } catch (error) {
        console.error("Error creating author:", error);
        toast.error("Failed to create author.");
      }
    },
  });

  return (
    <div style={styles.page}>
      <ToastContainer />
      <form onSubmit={formik.handleSubmit} style={styles.form}>
        <h2 style={{ textAlign: "center", color: "black" }}>Add New Author</h2>

        <label style={styles.label}>First Name</label>
        <input
          name="firstName"
          style={styles.input}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          value={formik.values.firstName}
        />
        {formik.touched.firstName && formik.errors.firstName && (
          <div style={styles.error}>{formik.errors.firstName}</div>
        )}

        <label style={styles.label}>Last Name</label>
        <input
          name="lastName"
          style={styles.input}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          value={formik.values.lastName}
        />
        {formik.touched.lastName && formik.errors.lastName && (
          <div style={styles.error}>{formik.errors.lastName}</div>
        )}

        <label style={styles.label}>Biography</label>
        <textarea
          name="biography"
          style={{ ...styles.input, height: "80px" }}
          onChange={formik.handleChange}
          value={formik.values.biography}
        />

        <label style={styles.label}>Birth Date</label>
        <input
          type="date"
          name="birthDate"
          style={styles.input}
          onChange={formik.handleChange}
          value={formik.values.birthDate}
        />

        <label style={styles.label}>Nationality</label>
        <input
          name="nationality"
          style={styles.input}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          value={formik.values.nationality}
        />
        {formik.touched.nationality && formik.errors.nationality && (
          <div style={styles.error}>{formik.errors.nationality}</div>
        )}

        <button type="submit" style={styles.button}>
          Save Author
        </button>
      </form>
    </div>
  );
};

const styles = {
  page: {
    minHeight: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "#f4f6f8",
    padding: "20px",
  },
  form: {
    background: "#ffffff",
    padding: "30px",
    borderRadius: "8px",
    boxShadow: "0 0 15px rgba(0,0,0,0.1)",
    width: "100%",
    maxWidth: "500px",
    display: "flex",
    flexDirection: "column",
  },
  label: {
    marginBottom: "5px",
    fontWeight: "bold",
    color: "black",
  },
  input: {
    padding: "10px",
    marginBottom: "15px",
    borderRadius: "4px",
    border: "1px solid #ccc",
    fontSize: "16px",
  },
  error: {
    color: "red",
    marginBottom: "10px",
    fontSize: "14px",
  },
  button: {
    padding: "12px",
    backgroundColor: "#1e3a8a",
    color: "white",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
    fontSize: "16px",
  },
};

export default AddAuthorForm;
