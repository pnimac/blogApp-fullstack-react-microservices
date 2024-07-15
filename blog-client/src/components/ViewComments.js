import React, { useState, useEffect } from 'react';
import { useAuth } from '../hooks/useAuth';
import createCommentService from '../api/commentService';

const ViewComments = ({ postId, onClose }) => {
  const [comments, setComments] = useState([]);
  const { username, getAuthToken } = useAuth();

  useEffect(() => {
    fetchComments();
  }, [postId]);

  const commentService = createCommentService(getAuthToken);

  const fetchComments = async () => {
    try {
      const fetchedComments = await commentService.getComments(postId);
      setComments(fetchedComments);
    } catch (error) {
      console.error('Failed to fetch comments:', error);
    }
  };

  const handleDeleteComment = async (commentId) => {
    try {
      await commentService.deleteComment(commentId);
      fetchComments(); // Reload comments after deletion
    } catch (error) {
      console.error('Failed to delete comment:', error);
    }
  };

  return (
    <div className="mt-3">
      <h6>Comments</h6>
      {comments.map(comment => (
        <div key={comment.id} className="card mb-2">
          <div className="card-body">
            <p className="card-text">{comment.content}</p>
            <p className="card-text"><small className="text-muted">Comment by {comment.username} on {new Date(comment.createdAt).toLocaleDateString()}</small></p>
            {username === comment.username && (
              <button onClick={() => handleDeleteComment(comment.id)} className="btn btn-danger btn-sm">Delete</button>
            )}
          </div>
        </div>
      ))}
      <button className="btn btn-secondary mt-2" onClick={onClose}>Close Comments</button>
    </div>
  );
};

export default ViewComments;