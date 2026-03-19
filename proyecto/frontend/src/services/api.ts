// services/api.ts
const api = async (url: string, options: RequestInit = {}) => {
  const token = localStorage.getItem("token");
  
  const response = await fetch(url, {
    ...options,
    headers: {
      "Content-Type": "application/json",
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
      ...options.headers,
    },
  });

  console.log(response)
  
  if (!response.ok) throw new Error(response.statusText);
  return response.json();
};

export default api;