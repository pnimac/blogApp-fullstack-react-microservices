import { postAPI } from '../api';

const createPostService = (getAuthToken) => ({
  getAllPosts: async () => {
    const response = await postAPI.get('/post/getAllPosts', {
      headers: { Authorization: `Bearer ${getAuthToken()}` }
    });
    return response.data;
  },

  createPost: async (postData) => {
    const response = await postAPI.post('/post/createPost', postData, {
      headers: { Authorization: `Bearer ${getAuthToken()}` }
    });
    return response.data;
  },

  deletePost: async (postId) => {
    await postAPI.delete(`/post/deletePost/${postId}`, {
      headers: { Authorization: `Bearer ${getAuthToken()}` }
    });
  }
});

export default createPostService;