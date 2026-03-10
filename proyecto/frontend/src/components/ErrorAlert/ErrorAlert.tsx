import { Alert } from "@mui/material";

interface ErrorAlertProps {
  message: string;
}

export const ErrorAlert = ({ message }: ErrorAlertProps) => (
  <Alert
    severity="error"
    sx={{
      mb: "16px",
      backgroundColor: "rgba(215,90,90,0.07)",
      border: "1px solid rgba(215,90,90,0.14)",
      color: "rgba(215,90,90,0.9)",
      borderRadius: "2px",
      fontSize: "12px",
      fontFamily: "Georgia, serif",
      "& .MuiAlert-icon": { color: "rgba(215,90,90,0.7)" },
    }}
  >
    {message}
  </Alert>
);