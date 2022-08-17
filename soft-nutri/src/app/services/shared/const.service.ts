import { Injectable, OnChanges} from '@angular/core';
import { JwtResponse } from 'src/app/model';
import { ModuleUser } from 'src/app/model/module/module';
import { environment as e } from '../../../environments/environment';

type rest = { 
user: {
    user: string,
    signin: string,
    refreshtoken: string,
    logout: string,
};
patient: {
    patient: string,
    listall: string,
    patientaction:string,
    save:string,
    get:string,
    delete:string,
};
food:{
    save:string,
    listall:string,
};
foodgroup:{ 
    save:string,
    listall:string,
    association:string,
}
};

type redirect = {
    DASHBOARD: string,
    FOOD:string,
    LOGIN: string,
};
@Injectable({
    providedIn: 'root'
})
export class ConstService implements OnChanges{
   jwtResponse: JwtResponse = new JwtResponse;

    rest:rest= {
        user: {
            user: e.rota + "user",
            signin: e.rota + "user/auth/signin",
            refreshtoken: e.rota + "user/auth/refreshtoken",
            logout: e.rota + "user/auth/logout",
        },
        patient: {
            patient: "/patient",
            listall: e.rota + "patient/",
            patientaction: "/patientaction",
            save: e.rota + "patient/save",
            get: e.rota + "patient/get/",
            delete: e.rota + "patient/delete/",

        },
        food: {
            save: e.rota + "food/save",
            listall: e.rota + "food/",

        },
        foodgroup:{
            save: e.rota + "foodGroup/save",
            listall: e.rota + "foodGroup/", 
            association: e.rota + "foodGroup/association/", 
        }
    }; 
    redirect:redirect= {
        DASHBOARD: '/dashboard',
        FOOD: '/food',
        LOGIN: '/login',
    };
    constructor() {
        var valid = localStorage.getItem('safe');
        if(valid !=null)
        this.jwtResponse=JSON.parse(atob(valid)); 
    } 

    ngOnChanges(): void {
        localStorage.setItem('safe',btoa(JSON.stringify(this.jwtResponse)));
    }  

    setTypeVar(type: string) {
        this.ngOnChanges();
        this.jwtResponse.type = type;
    }
    setTokenVar(token: string) { 
        this.ngOnChanges();
        this.jwtResponse._token = token;
    }
    getTokenVar(): string { 
        return this.jwtResponse._token;
    }
    setRefreshTokenVar(refreshToken: string) {
        this.ngOnChanges();
        this.jwtResponse.refreshToken = refreshToken;
    }
    getRefreshTokenVar(): string {
        return this.jwtResponse.refreshToken;
    }
    getTypeVar(): string {
        return this.jwtResponse.type;
    }
    setLanguageVar(language: string) {
        this.ngOnChanges();
        this.jwtResponse.language = language;
    }
    getLanguageVar(): string {
        return this.jwtResponse.language;
    }
    setRolesVar(roles: string[]) {
        this.ngOnChanges();
        this.jwtResponse.roles = roles;
    }
    getRolesVar(): string[] {
        return this.jwtResponse.roles;
    }
    setExpirationVar(expiration: Date) {
        this.ngOnChanges();
        this.jwtResponse.expiration = expiration;
    }
    getExpirationVar(): Date {
        return this.jwtResponse.expiration;
    }

    setModuleList(modules: ModuleUser[]){
        this.jwtResponse.modules = modules;
        
    }

    getModuleList(): ModuleUser[]{
        return this.jwtResponse.modules;
    }
}
