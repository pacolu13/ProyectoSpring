// Login.tsx
import { useState } from "react";
import {
  Box,
  TextField,
  Button,
  Typography,
  InputAdornment,
  IconButton,
  Link,
  CircularProgress,
} from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import type { TokenResponseDTO, FormState } from "../../interfaces/index";
import { apiClient } from "../../services/apiClient";
import { useAuth } from "../../context/AuthContext";
import { Globe, ErrorAlert } from "../../components/index";
import "../Login/Login.css";

export const Login = () => {
  const { login } = useAuth();
  const [form, setForm] = useState<FormState>({ email: "", password: "" });
  const [loading, setLoading] = useState<boolean>(false);
  const [showPass, setShowPass] = useState<boolean>(false);
  const [error, setError] = useState<string>("");
  const inputSx = { mb: "24px" };

  const handleChange =
    (field: keyof FormState) =>
      (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm((f) => ({ ...f, [field]: e.target.value }));
      };

  const handleSubmit = async (): Promise<void> => {
    if (!form.email || !form.password) {
      setError("Completá usuario y contraseña.");
      return;
    }
    setError("");
    setLoading(true);

    const { data, error } = await apiClient.post<TokenResponseDTO>("/auth/login", {
      email: form.email,
      password: form.password,
    });

    if (error) {
      setError(error);
    } else {
      apiClient.setAuthToken(data!.access_token);
      login(data!.access_token);
    }
    setLoading(false);
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>): void => {
    if (e.key === "Enter") handleSubmit();
  };

  return (
    <Box className="login-root">
      <Box className="globe-wrapper">
        <Globe />
      </Box>

      <Box className="login-card">
        <TextField
          fullWidth
          label="Usuario"
          placeholder="tu_usuario"
          value={form.email}
          onChange={handleChange("email")}
          onKeyDown={handleKeyDown}
          variant="outlined"
          size="small"
          sx={inputSx}
        />

        <TextField
          fullWidth
          label="Contraseña"
          placeholder="••••••••"
          type={showPass ? "text" : "password"}
          value={form.password}
          onChange={handleChange("password")}
          onKeyDown={handleKeyDown}
          variant="outlined"
          size="small"
          sx={inputSx}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton onClick={() => setShowPass((s) => !s)} edge="end" size="small">
                  {showPass ? <VisibilityOff sx={{ fontSize: 26 }} /> : <Visibility sx={{ fontSize: 26 }} />}
                </IconButton>
              </InputAdornment>
            ),
          }}
        />

        <Box sx={{ display: "flex", justifyContent: "flex-end", mb: "22px" }}>
          <Link href="#" className="login-forgot">
            ¿Olvidaste tu contraseña?
          </Link>
        </Box>

        {error && <ErrorAlert message={error} />}

        <Button
          fullWidth
          onClick={handleSubmit}
          disabled={loading}
          className="login-btn"
          disableElevation
        >
          {loading ? (
            <CircularProgress size={16} sx={{ color: "rgba(110,110,110,0.6)" }} />
          ) : (
            "Ingresar"
          )}
        </Button>

        <Typography className="login-register-text">
          ¿No tenés cuenta?{" "}
          <Link href="#" className="login-register-link">
            Registrate
          </Link>
        </Typography>
      </Box>
    </Box>
  );
};