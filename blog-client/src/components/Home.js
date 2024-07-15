import React, { useState, useEffect } from "react";
import { useAuth } from '../hooks/useAuth';
import PostList from './PostList';
import CreatePost from './CreatePost';
import createPostService from '../api/postService';
import blogImage from "../images/welcomepic.png";

function Home() {
  const { authenticated, username, getAuthToken } = useAuth();
  const [posts, setPosts] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const postService = createPostService(getAuthToken);

  const fetchPosts = async () => {
    setIsLoading(true);
    try {
      const fetchedPosts = await postService.getAllPosts();
      setPosts(fetchedPosts);
    } catch (error) {
      console.error('Failed to fetch posts:', error);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    if (authenticated) {
      fetchPosts();
    }
  }, [authenticated]);

  const handlePostCreated = async (newPost) => {
    try {
      await postService.createPost(newPost);
      await fetchPosts();
    } catch (error) {
      console.error('Failed to create post:', error);
    }
  };

  const handlePostDeleted = async (postId) => {
    try {
      await postService.deletePost(postId);
      await fetchPosts();
    } catch (error) {
      console.error('Failed to delete post:', error);
    }
  };

  const handleCommentAdded = async () => {
    await fetchPosts(); // Reload posts to reflect new comment count, if applicable
  };

  return (
    <div className="container mt-5">
      {!authenticated ? (
        <div className="row justify-content-center">
          <div className="col-md-8">
            <div className="card">
              <div className="card-body text-center">
                <img
                  src={blogImage}
                  className="img-fluid rounded"
                  alt="Welcome to our blog"
                  style={{ maxWidth: '100%', height: 'auto', maxHeight: '300px' }}
                />
              </div>
            </div>
          </div>
        </div>
      ) : (
        <div>
          <CreatePost onPostCreated={handlePostCreated} />
          {isLoading ? (
            <p>Loading posts...</p>
          ) : (
            <PostList 
              posts={posts} 
              onPostDeleted={handlePostDeleted}
              onCommentAdded={handleCommentAdded}
            />
          )}
        </div>
      )}
    </div>
  );
}

export default Home;