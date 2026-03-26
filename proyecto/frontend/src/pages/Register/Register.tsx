import { Box, Typography, TextField } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "../../components";
import { useState } from "react";
import type { TokenResponseDTO } from "../../interfaces";
import type { RegisterDTO } from "../../interfaces/AuthDTO";
import { usePost } from "../../hooks/usePost";
import { useAuth } from "../../context/AuthContext";
import './Register.css'


export const Register = () => {
    const { login } = useAuth();
    const navigate = useNavigate();
    const { post, isLoading } = usePost<TokenResponseDTO>("/api/v1/auth/register");
    const [form, setForm] = useState<RegisterDTO>({ username: "", email: "", password: "" });
    const [error, setError] = useState<string>("");


    const handleChange =
        (field: keyof RegisterDTO) =>
            (e: React.ChangeEvent<HTMLInputElement>) => {
                setForm((f) => ({ ...f, [field]: e.target.value }));
            };

    const handleSubmit = async (): Promise<void> => {
        if (!form.username || !form.email || !form.password) {
            setError("Completá usuario y contraseña.");
            return;
        }
        setError("");

        const data = await post({ username: form.username, email: form.email, password: form.password });

        if (data) {
            login(data.access_token, { email: form.email });
            navigate("/");
        } else {
            setError("Error al registrarse.");
        }
    };

    return (
        <Box className="register-container">
            <Box className="register-form">
                <Typography variant="h4" className="register-title">
                    Registrate
                </Typography>
                <TextField
                    fullWidth
                    label="Nombre de usuario"
                    variant="outlined"
                    className="register-input"
                    onChange={handleChange("username")}

                />
                <TextField
                    fullWidth
                    label="Correo electrónico"
                    variant="outlined"
                    className="register-input"
                    onChange={handleChange("email")}

                />
                <TextField
                    fullWidth
                    label="Contraseña"
                    variant="outlined"
                    type="password"
                    className="register-input"
                    onChange={handleChange("password")}

                />
                <Button label="REGISTRARSE" parentMethod={() => handleSubmit()}></Button>
                <Typography className="register-login-text">
                    ¿Ya tenés cuenta?{" "}
                    <Link to="/login" className="register-login-link">
                        Ingresá
                    </Link>
                </Typography>
            </Box>
        </Box>
    );
};