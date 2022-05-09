export interface JwtResponse {
    token: string;
    type: string;   
    roles: string[];
    language: string; 
    expiration: Date;  
}