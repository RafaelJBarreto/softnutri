import { JwtResponse } from 'src/app/model';
import { ModuleUser } from 'src/app/model/module/module';

import { Injectable, OnChanges } from '@angular/core';

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
professional: {
    professional: string,
    listall: string,
    professionalaction:string,
    save:string,
    get:string,
    delete:string,
    nutritionist: string,
};
food:{
    save:string,
    listall:string,
    get:string,
    delete:string,
};
bunch:{ 
    save:string,
    listall:string,
    get:string,
    delete:string,
};
foodbunch:{ 
    save:string,
    listall:string,
    get:string,
    delete:string,
    table:string,
};
calendar:{ 
    calendar:string,
    save:string,
    listall:string,
    get:string,
    calendaraction:string,
    cancel:string,
    professional: string
};
intermission:{ 
    save:string,
    get:string,
};
permission:{ 
    get:string,
    save:string,
};
table: {
    table: string,
    listall: string,
    save:string,
    get:string,
    tableaction:string,
    delete:string
};
snack: {
    snack: string,
    listall: string,
    save:string,
    get:string,
    snackaction:string,
    delete:string
};

};

type redirect = {
    HOME: string,
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
        professional: {
            professional: "/professional",
            listall: e.rota + "professional/",
            professionalaction: "/professionalaction",
            save: e.rota + "professional/save",
            get: e.rota + "professional/get/",
            delete: e.rota + "professional/delete/",
            nutritionist: e.rota + "professional/nutritionist"

        },
        food: {
            save: e.rota + "food/save",
            listall: e.rota + "food/",
            get: e.rota + "food/get/",
            delete: e.rota + "food/delete/",

        },
        bunch:{
            save: e.rota + "bunch/save",
            listall: e.rota + "bunch/", 
            get: e.rota + "bunch/get/",
            delete: e.rota + "bunch/delete/",
        },
        foodbunch:{
            save: e.rota + "foodBunch/save",
            listall: e.rota + "foodBunch/",
            table: e.rota + "foodBunch/table/",
            get: e.rota + "foodBunch/get/",
            delete: e.rota + "foodBunch/delete/",
        },
        calendar:{
            calendar: "/calendar",
            save: e.rota + "calendar/save",
            listall: e.rota + "calendar/",
            get: e.rota + "calendar/get/",
            calendaraction: "/calendaraction",
            cancel: e.rota + "calendar/cancel/",
            professional: e.rota + "calendar/professional"
        },
        intermission:{
            save: e.rota + "intermission/save",
            get: e.rota + "intermission/get",
        },
        permission:{
            get: e.rota + "permission/find/",
            save: e.rota + "permission/save",
        },
        table: {
            table: "/table",
            save: e.rota + "table/save",
            listall: e.rota + "table/",
            get: e.rota + "table/get/",
            tableaction: "/tableaction",
            delete: e.rota + "table/delete/",
        },
        snack: {
            snack: "/snack",
            save: e.rota + "snack/save",
            listall: e.rota + "snack/",
            get: e.rota + "snack/get/",
            snackaction: "/snackaction",
            delete: e.rota + "snack/delete/",
        }
    }; 
    redirect:redirect= {
        HOME: '/home',
        FOOD: '/food',
        LOGIN: '/login',
    };

    constructor() {
        var valid = localStorage.getItem('safe');
        if(valid !=null){
            this.jwtResponse=JSON.parse(atob(valid)); 
        }
    } 

    ngOnChanges(): void {
        localStorage.setItem('safe',btoa(JSON.stringify(this.jwtResponse)));
    }  

    setEmail(email: string) {
        this.ngOnChanges();
        this.jwtResponse.email = email;
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
