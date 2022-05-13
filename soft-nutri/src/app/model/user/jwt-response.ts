import { Injectable } from "@angular/core";
import { ModuleUser } from "../module/module";

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
    public modules!: ModuleUser[];
}