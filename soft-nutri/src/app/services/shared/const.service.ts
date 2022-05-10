import { Injectable } from '@angular/core';
import { JwtResponse } from 'src/app/model';

@Injectable({
    providedIn: 'root'
})
export class ConstService {
    jwtResponse: JwtResponse = new JwtResponse;
    constructor() { }

    setTypeVar(type: string) {
        this.jwtResponse.type = type;
    }
    setTokenVar(token: string) {
        this.jwtResponse._token = token;
    }
    getTokenVar(): string {
        return this.jwtResponse._token;
    }
    setRefreshTokenVar(refreshToken: string) {
        this.jwtResponse.refreshToken = refreshToken;
    }
    getRefreshTokenVar(): string {
        return this.jwtResponse.refreshToken;
    }
    getTypeVar(): string {
        return this.jwtResponse.type;
    }
    setLanguageVar(language: string) {
        this.jwtResponse.language = language;
    }
    getLanguageVar(): string {
        return this.jwtResponse.language;
    }
    setRolesVar(roles: string[]) {
        this.jwtResponse.roles = roles;
    }
    getRolesVar(): string[] {
        return this.jwtResponse.roles;
    }
    setExpirationVar(expiration: Date) {
        this.jwtResponse.expiration = expiration;
    }
    getExpirationVar(): Date {
        return this.jwtResponse.expiration;
    }
}
