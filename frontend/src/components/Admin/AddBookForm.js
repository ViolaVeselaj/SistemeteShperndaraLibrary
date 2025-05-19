import React, { useState, useEffect } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import styled from 'styled-components';
import axios from '../utils/axiosInstance';
import 'react-toastify/dist/ReactToastify.css';

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  background-color: #1e3a8a;
`;

const FormContainer = styled.div`
  background-color: #ffffff;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
  width: 350px;
`;

const InputContainer = styled.div`
  margin-bottom: 1.2rem;
  display: flex;
  flex-direction: column;

  input, select {
    padding: 0.8rem;
    border-radius: 6px;
    border: 1px solid #ccc;
    background-color: #f3f4f6;
    outline: none;
    transition: border-color 0.3s;

    &:focus {
      border-color: #60a5fa;
    }
  }
`;

const Button = styled.button`
  cursor: pointer;
  width: 100%;
  padding: 0.9rem;
  border-radius: 6px;
  border: none;
  color: #ffffff;
  font-size: 16px;
  font-weight: 600;
  background-color: #dc2626;
  margin-top: 1rem;
  transition: background-color 0.3s;

  &:hover {
    background-color: #b91c1c;
  }
`;

const AddBookForm = () => {
  const [data, setData] = useState({
    title: '',
    publishedYear: '',
    availableCopies: '',
    imageUrl: '',   
    author: { id: '' }
  });

  const [authors, setAuthors] = useState([]);
  const [newAuthor, setNewAuthor] = useState('');

    useEffect(() => {
      const token = localStorage.getItem('token');

      axios.get('/authors', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
        .then((res) => {
          if (res.status === 200 && Array.isArray(res.data)) {
            setAuthors(res.data);
          } else {
            toast.error("Unexpected response structure");
          }
        })
        .catch((err) => {
          toast.error("Failed to fetch authors");
          console.error(err);
        });
    }, []);


  const handleChange = (e) => {
    const { name, value } = e.target;
    setData({ ...data, [name]: value });
  };

  const handleAuthorChange = (e) => {
    setData({ ...data, author: { id: parseInt(e.target.value, 10) } });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!data.title || !data.publishedYear || !data.availableCopies || !data.author.id) {
      toast.error("Please fill in all fields");
      return;
    }

    const token = localStorage.getItem('token'); // kontrollo që ekziston

    try {
      await axios.post('/books', {
        title: data.title,
        publishedYear: Number(data.publishedYear),
        availableCopies: Number(data.availableCopies),
        imageUrl: data.imageUrl, 
        author: { id: data.author.id }
      }, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });

      toast.success("Book added successfully!");
      setData({
        title: '',
        publishedYear: '',
        availableCopies: '',
        imageUrl: '', 
        author: { id: '' }
      });
    } catch (err) {
      console.error(err);
      toast.error("Error adding book");
    }
  };

  const handleCreateAuthor = async () => {
    if (!newAuthor.trim()) {
      toast.error("Please enter author name");
      return;
    }

    try {
      const response = await axios.post('/authors/create', {
        firstName: newAuthor,
        lastName: ""
      });

      setAuthors([...authors, response.data]);
      setNewAuthor('');
      toast.success("Author created successfully!");
    } catch (err) {
      console.error(err);
      toast.error("Error creating author");
    }
  };

  return (
    <Container>
      <FormContainer>
        <ToastContainer />
        <h2 style={{ textAlign: 'center', marginBottom: '1.5rem' }}>Add Book</h2>
        <form onSubmit={handleSubmit}>
          <InputContainer>
            <input
              type="text"
              name="title"
              placeholder="Book Title"
              value={data.title}
              onChange={handleChange}
            />
          </InputContainer>

          <InputContainer>
            <input
              type="number"
              name="publishedYear"
              placeholder="Published Year"
              value={data.publishedYear}
              onChange={handleChange}
            />
          </InputContainer>

          <InputContainer>
            <input
              type="number"
              name="availableCopies"
              placeholder="Available Copies"
              value={data.availableCopies}
              onChange={handleChange}
            />
          </InputContainer>

          <InputContainer>
            <select name="author" value={data.author.id} onChange={handleAuthorChange}>
              <option value="">Select Author</option>
              {authors.map((author) => (
                <option key={author.id} value={author.id}>
                  {author.firstName} {author.lastName}
                </option>
              ))}
            </select>
          </InputContainer>

          <InputContainer>
            <input
              type="text"
              placeholder="New Author Name"
              value={newAuthor}
              onChange={(e) => setNewAuthor(e.target.value)}
            />
            <Button type="button" onClick={handleCreateAuthor}>Create Author</Button>
          </InputContainer>

          <InputContainer>
            <input
              type="text"
              name="imageUrl"
              placeholder="Image URL"
              value={data.imageUrl}
              onChange={handleChange}
            />
          </InputContainer>

          <Button type="submit">Add Book</Button>
        </form>
      </FormContainer>
    </Container>
  );
};

export default AddBookForm;
