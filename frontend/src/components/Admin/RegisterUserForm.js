import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import { ToastContainer, toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import axios from "../utils/axiosInstance";

const RegisterUserForm = () => {
  const formik = useFormik({
    initialValues: {
      name: "",
      email: "",
      password: "",
      role: "USER",         // default value
      tenantId: 1           // ose merreni nga sesioni nëse ke multi-tenancy dinamik
    },
    validationSchema: Yup.object({
      name: Yup.string().required("Name is required"),
      email: Yup.string().email("Invalid email").required("Email is required"),
      password: Yup.string().min(6, "Minimum 6 characters").required("Password is required"),
      role: Yup.string().oneOf(["ADMIN", "LIBRARIAN", "USER"]).required("Role is required"),
      tenantId: Yup.number().required("Tenant is required")
    }),
    onSubmit: async (values, { resetForm }) => {
      try {
        await axios.post("/users/add", values);
        toast.success("User registered successfully!");
        resetForm();
      } catch (error) {
        console.error(error);
        toast.error("Failed to register user");
      }
    }
  });

  return (
    <div style={styles.page}>
      <ToastContainer />
      <form onSubmit={formik.handleSubmit} style={styles.form}>
        <h2 style={{ textAlign: "center", color: "black" }}>Register New User</h2>

        <label style={styles.label}>Name</label>
        <input name="name" style={styles.input} onChange={formik.handleChange} value={formik.values.name} />
        {formik.touched.name && formik.errors.name && <div style={styles.error}>{formik.errors.name}</div>}

        <label style={styles.label}>Email</label>
        <input name="email" style={styles.input} onChange={formik.handleChange} value={formik.values.email} />
        {formik.touched.email && formik.errors.email && <div style={styles.error}>{formik.errors.email}</div>}

        <label style={styles.label}>Password</label>
        <input type="password" name="password" style={styles.input} onChange={formik.handleChange} value={formik.values.password} />
        {formik.touched.password && formik.errors.password && <div style={styles.error}>{formik.errors.password}</div>}

        <label style={styles.label}>Role</label>
        <select name="role" style={styles.input} onChange={formik.handleChange} value={formik.values.role}>
          <option value="USER">USER</option>
          <option value="LIBRARIAN">LIBRARIAN</option>
          <option value="ADMIN">ADMIN</option>
        </select>

        <label style={styles.label}>Tenant ID</label>
        <input type="number" name="tenantId" style={styles.input} onChange={formik.handleChange} value={formik.values.tenantId} />
        {formik.touched.tenantId && formik.errors.tenantId && <div style={styles.error}>{formik.errors.tenantId}</div>}

        <button type="submit" style={styles.button}>Register</button>
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

export default RegisterUserForm;
