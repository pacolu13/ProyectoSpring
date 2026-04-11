import { Box, Typography } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { Button, InputField, useToast } from "../../components";
import { useState } from "react";
import type { TokenResponseDTO, RegisterDTO } from "../../interfaces";
import { usePost } from "../../hooks/usePost";
import { useAuth } from "../../context/AuthContext";
import './Register.css';

export const Register = () => {
    const { login } = useAuth();
    const showToast = useToast();
    const navigate = useNavigate();
    const [isSeller, setIsSeller] = useState(false);
    const { post } = usePost<TokenResponseDTO>("/api/v1/auth/register");

    const { register, handleSubmit } = useForm<RegisterDTO>();

    const onSubmit = async (data: RegisterDTO): Promise<void> => {
        const roles = ["CLIENT"];
        if (isSeller) roles.push("SELLER");

        const registerData: RegisterDTO = {
            username: data.username,
            email: data.email,
            password: data.password,
            roles: roles,
        };

        const result = await post(registerData);
        if (result) {
            login(result.access_token, { email: data.email, roles: roles });
            navigate("/"); 
        } else {
            showToast("error", "Error al registrarse.");
        }
    };

    return (
        <Box className="register-container">
            <Box className="register-form">
                <Typography variant="h4" className="register-title">
                    Registrate
                </Typography>

                <InputField
                    label="Nombre de usuario"
                    name="username"
                    register={register}
                    rules={{ required: "El nombre de usuario es obligatorio" }}
                    placeholder="Nombre de usuario"
                />
                <InputField
                    label="Correo electrónico"
                    name="email"
                    register={register}
                    rules={{ required: "El correo electrónico es obligatorio" }}
                    placeholder="Correo electrónico"
                />
                <InputField
                    label="Contraseña"
                    name="password"
                    type="password"
                    register={register}
                    rules={{ required: "La contraseña es obligatoria" }}
                    placeholder="Contraseña"
                />

                <Box className="register-radio-group">
                    <Typography variant="body1" className="register-radio-label">
                        ¿Querés registrarte como vendedor?
                    </Typography>

                    <Box className="register-radio-options">
                        <label className="register-radio-option">
                            <input
                                type="radio"
                                name="seller"
                                value="yes"
                                checked={isSeller === true}
                                onChange={() => setIsSeller(true)}
                            />
                            Sí
                        </label>
                        <label className="register-radio-option">
                            <input
                                type="radio"
                                name="seller"
                                value="no"
                                checked={isSeller === false}
                                onChange={() => setIsSeller(false)}
                            />
                            No
                        </label>
                    </Box>
                </Box>

                <Button label="REGISTRARSE" parentMethod={handleSubmit(onSubmit)} />

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