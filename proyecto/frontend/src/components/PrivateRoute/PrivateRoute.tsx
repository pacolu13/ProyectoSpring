import { Navigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

type Props = {
  children: React.ReactNode;
  roles?: string[];
};

export const PrivateRoute = ({ children, roles }: Props) => {
  const { token, user } = useAuth();

  if (!token) return <Navigate to="/login" replace />;

  if (roles && !roles.some(role => user?.roles.includes(role))) {
    return <Navigate to="/unauthorized" replace />;
  }
  return children;
};