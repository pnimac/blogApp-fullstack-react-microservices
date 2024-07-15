import React, { useState } from 'react';
import { useAuth } from '../hooks/useAuth';
import createCommentService from '../api/commentService';

const AddComment = ({ postId, onCommentAdded, onClose }) => {
  const [commentText, setCommentText] = useState('');
  const { username, getAuthToken } = useAuth();

  const commentService = createCommentService(getAuthToken);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const postIdNumber = Number(postId); // Explicitly cast postId to a number
      await commentService.addComment({ content: commentText, postId: postIdNumber, username });
      onCommentAdded();
      setCommentText('');
      onClose();
    } catch (error) {
      console.error('Failed to add comment:', error);
    }
  };

  return (
    <div className="mt-3">
      <h6>Add a Comment</h6>
      <form onSubmit={handleSubmit}>
        <textarea
          className="form-control mb-2"
          value={commentText}
          onChange={(e) => setCommentText(e.target.value)}
          required
        />
        <button type="submit" className="btn btn-success me-2">Save Comment</button>
        <button type="button" className="btn btn-secondary" onClick={onClose}>Cancel</button>
      </form>
    </div>
  );
};

export default AddComment;
