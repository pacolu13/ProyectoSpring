export const authService = {
    saveToken: (token: string) => localStorage.setItem("token", token),
    getToken: () => localStorage.getItem("token"),
    removeToken: () => localStorage.removeItem("token"),
    isAuthenticated: () => !!localStorage.getItem("token"),
};