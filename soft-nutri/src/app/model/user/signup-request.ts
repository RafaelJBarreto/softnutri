export interface SignupRequest {
    username: string;
    email: string;
    role?: string[];
    password: string;
    language: string;
}