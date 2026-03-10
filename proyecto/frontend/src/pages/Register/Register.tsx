import type { Box, Typography, TextField, Button } from "@mui/material";
import type { Link } from "react-router-dom";

export const Register = () => {
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
                />
                <TextField
                    fullWidth
                    label="Correo electrónico"
                    variant="outlined"
                    className="register-input"
                />
                <TextField
                    fullWidth
                    label="Contraseña"
                    variant="outlined"
                    type="password"
                    className="register-input"
                />
                <Button fullWidth className="register-btn" disableElevation>
                    Registrarse
                </Button>
                <Typography className="register-login-text">
                    ¿Ya tenés cuenta?{" "}
                    <Link href="#" className="register-login-link">
                        Ingresá
                    </Link>
                </Typography>
            </Box>
        </Box>
    );
};