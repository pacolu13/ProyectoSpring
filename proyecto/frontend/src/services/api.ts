// services/api.ts
const request = async (url: string, options: RequestInit = {}) => {
  const token = localStorage.getItem("token");

  const response = await fetch(url, {
    ...options,
    headers: {
      "Content-Type": "application/json",
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
      ...options.headers,
    },
  });

  if (!response.ok) throw new Error(response.statusText);
  return response.json();
};

const api = {
  get: (url: string) => request(url, { method: "GET" }),
  post: (url: string, body: unknown) => request(url, { method: "POST", body: JSON.stringify(body) }),
  put: (url: string, body: unknown) => request(url, { method: "PUT", body: JSON.stringify(body) }),
  delete: (url: string) => request(url, { method: "DELETE" }),
};

export default api;