import { Injectable } from '@angular/core';
import { JwtResponse } from 'src/app/model';
import { environment as e } from '../../../environments/environment';

type rest = { 
user: {
    user: string,
    signin: string,
    refreshtoken: string,
    logout: string,
};
people: {
    people: string,
    listall: string,
}};

type redirect = {
    DASHBOARD: string,
    LOGIN: string,
};
@Injectable({
    providedIn: 'root'
})
export class ConstService {
    jwtResponse: JwtResponse = new JwtResponse;

    rest:rest= { 
        user: {
            user:e.rota+ "user",
            signin:e.rota+ "user/auth/signin",
            refreshtoken: e.rota+"user/auth/refreshtoken",
            logout: e.rota+"user/auth/logout",
        },
        people: {
            people: e.rota+"people",
            listall: e.rota+"people/",
        },
    } ; 
    redirect:redirect= {
        DASHBOARD: '/dashboard',
        LOGIN: '/login',
    };
    constructor() { 
    }


    
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
