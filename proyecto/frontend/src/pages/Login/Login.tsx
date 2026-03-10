import { useState, useEffect } from "react";
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
import { Visibility, VisibilityOff, Language } from "@mui/icons-material";
import type { TokenResponseDTO, FormState } from "../../interfaces/index";
import { apiClient } from "../../services/apiClient";
import { Globe, ErrorAlert } from "../../components/index";
import { inputSx } from "../../styles";
import "../Login/Login";

export const Login = () => {
  const [form, setForm] = useState<FormState>({ username: "", password: "" });
  const [loading, setLoading] = useState<boolean>(false);
  const [showPass, setShowPass] = useState<boolean>(false);
  const [error, setError] = useState<string>("");
  const [mounted, setMounted] = useState<boolean>(false);

  useEffect(() => {
    const t = setTimeout(() => setMounted(true), 80);
    return () => clearTimeout(t);
  }, []);

  const handleChange =
    (field: keyof FormState) =>
      (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm((f) => ({ ...f, [field]: e.target.value }));
      };

  const handleSubmit = async (): Promise<void> => {
    if (!form.username || !form.password) {
      setError("Completá usuario y contraseña.");
      return;
    }
    setError("");
    setLoading(true);

    const { data, error } = await apiClient.post<TokenResponseDTO>("/auth/login", {
      username: form.username,
      password: form.password,
    });

    if (error) {
      setError(error);
    } else {
      apiClient.setAuthToken(data!.token);
      // navigate("/dashboard");
    }
    setLoading(false);

  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>): void => {
    if (e.key === "Enter") handleSubmit();
  };

  return (
    <Box className="login-root" alignContent={"center"} justifyContent={"center"}>
      {/* Globe background */}
      <Box className="globe-wrapper">
        <Globe />
      </Box>

      {/* Vignette */}
      <Box className="vignette" />

      {/* Card */}
      <Box className={`login-card ${mounted ? "login-card--visible" : ""}`}>

        {/* Brand */}
        <Box className="login-brand">
          <Box className="login-brand__logo">
            <Language sx={{ fontSize: 18, color: "rgba(190,190,190,0.65)" }} />
            <Typography className="login-brand__name">SPHERA STORE</Typography>
          </Box>
          <Typography variant="h5" className="login-title">
            Iniciá sesión
          </Typography>
          <Typography className="login-subtitle">
            Accedé a tu cuenta para explorar
          </Typography>
        </Box>

        {/* Username */}
        <TextField
          fullWidth
          label="Usuario"
          placeholder="tu_usuario"
          value={form.username}
          onChange={handleChange("username")}
          onKeyDown={handleKeyDown}
          variant="outlined"
          size="small"
          sx={{ ...inputSx, mb: "16px" }}
        />

        {/* Password */}
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
          sx={{ ...inputSx, mb: "10px" }}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton
                  onClick={() => setShowPass((s) => !s)}
                  edge="end"
                  size="small"
                  sx={{ color: "rgba(130,130,130,0.55)" }}
                >
                  {showPass ? (
                    <VisibilityOff sx={{ fontSize: 16 }} />
                  ) : (
                    <Visibility sx={{ fontSize: 16 }} />
                  )}
                </IconButton>
              </InputAdornment>
            ),
          }}
        />

        {/* Forgot password */}
        <Box sx={{ display: "flex", justifyContent: "flex-end", mb: "22px" }}>
          <Link href="#" className="login-forgot">
            ¿Olvidaste tu contraseña?
          </Link>
        </Box>

        {/* Error */}
        {error && (
          <ErrorAlert message={error}/>
        )}

        {/* Submit */}
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

        {/* Register */}
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
