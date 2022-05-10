import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
  })
export class JwtResponse {
    public _token!: string;
    public refreshToken!: string;
    public type!: string;
    public roles!: string[];
    public language!: string;
    public expiration!: Date;


}