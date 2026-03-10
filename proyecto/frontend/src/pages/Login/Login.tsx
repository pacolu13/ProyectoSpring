import { useState, useEffect } from "react";
import {
  Box,
  TextField,
  Button,
  Typography,
  InputAdornment,
  IconButton,
  Alert,
  Link,
  CircularProgress,
} from "@mui/material";
import { Visibility, VisibilityOff, Language } from "@mui/icons-material";
import { Globe } from "../../components/index";
import "../Login/Login";

interface FormState {
  username: string;
  password: string;
}

interface TokenResponseDTO {
  token: string;
  refreshToken?: string;
}

const inputSx = {
  "& .MuiOutlinedInput-root": {
    color: "rgba(228,228,228,0.95)",
    fontFamily: "Georgia, serif",
    fontSize: "14px",
    backgroundColor: "rgba(24,24,24,0.85)",
    borderRadius: "2px",
    "& fieldset": {
      borderColor: "rgba(255,255,255,0.06)",
      borderWidth: "1px",
    },
    "&:hover fieldset": {
      borderColor: "rgba(180,180,180,0.2)",
    },
    "&.Mui-focused fieldset": {
      borderColor: "rgba(195,195,195,0.28)",
      borderWidth: "1px",
    },
    "&.Mui-focused": {
      backgroundColor: "rgba(38,38,38,0.95)",
    },
  },
  "& .MuiInputLabel-root": {
    color: "rgba(150,150,150,0.65)",
    fontFamily: "Georgia, serif",
    fontSize: "12px",
    letterSpacing: "0.1em",
    textTransform: "uppercase",
  },
  "& .MuiInputLabel-root.Mui-focused": {
    color: "rgba(185,185,185,0.75)",
  },
};

export const LoginPage = () => {
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

    try {
      const res = await fetch("/api/v1/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          username: form.username,
          password: form.password,
        }),
      });

      if (!res.ok) throw new Error("Credenciales incorrectas.");

      const data: TokenResponseDTO = await res.json();
      console.log("Token recibido:", data.token);
      // localStorage.setItem("token", data.token);
      // navigate("/dashboard");
    } catch (err: unknown) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError("Error inesperado.");
      }
    } finally {
      setLoading(false);
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>): void => {
    if (e.key === "Enter") handleSubmit();
  };

  return (
    <Box className="login-root">
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
            {error}
          </Alert>
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
