import { Box, Typography } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { useState } from "react";
import { Button, InputField, useToast } from "../../components";
import type { TokenResponseDTO, RegisterDTO } from "../../interfaces";
import { usePost } from "../../hooks/usePost";
import { useAuth } from "../../context/AuthContext";
import { userRoutes } from "../../api/routes";
import './Register.css';

export const Register = () => {
    const { login } = useAuth();
    const showToast = useToast();
    const navigate = useNavigate();
    const [isSeller, setIsSeller] = useState(false);
    const { post } = usePost<TokenResponseDTO>(userRoutes.register);

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

        console.log(registerData);

        try {
            const result = await post(registerData);
            if (!result) {
                showToast("error", "Error al registrarse.");
                return;
            }
            login(result.access_token, { email: data.email, roles: roles });
            navigate("/");
        } catch (error) {
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

                <div className="seller-toggle">
                    <p className="seller-toggle__label">¿Querés registrarte como vendedor?</p>

                    <div className="seller-toggle__options">
                        <label className={`seller-toggle__option ${isSeller === true ? "seller-toggle__option--active" : ""}`}>
                            <input
                                type="radio"
                                name="seller"
                                value="yes"
                                checked={isSeller === true}
                                onChange={() => setIsSeller(true)}
                            />
                            <span className="seller-toggle__check" />
                            Sí
                        </label>

                        <label className={`seller-toggle__option ${isSeller === false ? "seller-toggle__option--active" : ""}`}>
                            <input
                                type="radio"
                                name="seller"
                                value="no"
                                checked={isSeller === false}
                                onChange={() => setIsSeller(false)}
                            />
                            <span className="seller-toggle__check" />
                            No
                        </label>
                    </div>
                </div>

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