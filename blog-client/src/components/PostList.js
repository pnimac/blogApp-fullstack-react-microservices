import React, { useState } from 'react';
import { useAuth } from '../hooks/useAuth';
import AddComment from './AddComment';
import ViewComments from './ViewComments';

const PostList = ({ posts, onPostDeleted, onCommentAdded }) => {
  const { username } = useAuth();
  const [activeCommentPost, setActiveCommentPost] = useState(null);
  const [viewingComments, setViewingComments] = useState(null);

  const handleDelete = async (postId) => {
    try {
      await onPostDeleted(postId);
    } catch (error) {
      console.error('Failed to delete post:', error);
    }
  };

  return (
    <div>
      <h2>Recent Posts</h2>
      {posts.map(post => (
        <div key={post.id} className="card mb-3">
          <div className="card-body">
            <h5 className="card-title">
              <i className="bi bi-file-text me-2 text-primary"></i>
              {post.title}
            </h5>
            <p className="card-text">{post.content}</p>
            <p className="card-text">
              <small className="text-muted">Posted by {post.username} on {new Date(post.createdAt).toLocaleDateString()}</small>
            </p>
            {username === post.username && (
              <button onClick={() => handleDelete(post.id)} className="btn btn-danger me-2">Delete</button>
            )}
            <button onClick={() => setActiveCommentPost(post.id)} className="btn btn-primary me-2">Add Comment</button>
            <button onClick={() => setViewingComments(post.id)} className="btn btn-secondary">View Comments</button>
            {activeCommentPost === post.id && (
              <AddComment postId={post.id} onCommentAdded={onCommentAdded} onClose={() => setActiveCommentPost(null)} />
            )}
            {viewingComments === post.id && (
              <ViewComments postId={post.id} onClose={() => setViewingComments(null)} />
            )}
          </div>
        </div>
      ))}
    </div>
  );
};

export default PostList;