import { commentAPI } from '../api';

const createCommentService = (getAuthToken) => ({
  getComments: async (postId) => {
    const response = await commentAPI.get(`/comment/getComments/${postId}`, {
      headers: { Authorization: `Bearer ${getAuthToken()}` }
    });
    return response.data;
  },
  addComment: async (commentData) => {
    const response = await commentAPI.post(`/comment/addComment`, commentData, {
      headers: { Authorization: `Bearer ${getAuthToken()}` }
    });
    return response.data;
  },
  deleteComment: async (commentId) => {
    await commentAPI.delete(`/comment/deleteComment/${commentId}`, {
      headers: { Authorization: `Bearer ${getAuthToken()}` }
    });
  }
});

export default createCommentService;