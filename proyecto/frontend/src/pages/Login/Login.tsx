import { useNavigate } from "react-router-dom";
import { Box, Button, Typography, Link, CircularProgress } from "@mui/material";
import { useAuth } from "../../context/AuthContext";
import { usePost } from "../../hooks/usePost";
import { useForm } from "react-hook-form";
import { Globe, InputField, useToast } from "../../components/index";
import type { TokenResponseDTO, LoginDTO } from "../../interfaces/index";
import "../Login/Login.css";

export const Login = () => {
  const { login } = useAuth();
  const showToast = useToast();
  const navigate = useNavigate();
  const { post, isLoading } = usePost<TokenResponseDTO>("/api/v1/auth/login");
  const { register, handleSubmit } = useForm<LoginDTO>();

  const onSubmit = async (data: LoginDTO): Promise<void> => {
    const result = await post({ email: data.email, password: data.password });

    if (result) {
      login(result.access_token, { email: data.email, roles: result.roles });
      navigate("/");
    } else {
      showToast("error", "Credenciales incorrectas.");
    }
  };

  return (
    <Box className="login-root">
      <Box className="globe-wrapper">
        <Globe />
      </Box>

      <Box className="login-card">
        <InputField
          label="Email"
          name="email"
          register={register}
          rules={{ required: "El email es obligatorio" }}
          placeholder="tu_email@dominio.com"
        />

        <InputField
          label="Contraseña"
          name="password"
          type="password"
          register={register}
          rules={{ required: "La contraseña es obligatoria" }}
          placeholder="••••••••"
        />

        <Box sx={{ display: "flex", justifyContent: "flex-end", mb: "22px" }}>
          <Link href="#" className="login-forgot">
            ¿Olvidaste tu contraseña?
          </Link>
        </Box>

        <Button
          fullWidth
          onClick={handleSubmit(onSubmit)}
          disabled={isLoading}
          className="login-btn"
          disableElevation
        >
          {isLoading ? (
            <CircularProgress size={16} sx={{ color: "rgba(110,110,110,0.6)" }} />
          ) : (
            "Ingresar"
          )}
        </Button>

        <Typography className="login-register-text">
          ¿No tenés cuenta?{" "}
          <Link href="/register" className="login-register-link">
            Registrate
          </Link>
        </Typography>
      </Box>
    </Box>
  );
};