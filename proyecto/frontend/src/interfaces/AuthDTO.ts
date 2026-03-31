export interface RegisterDTO {
    username: string,
    password: string,
    email: string,
    roles: string[]
}

export interface LoginDTO {
    email: string,
    password: string
}