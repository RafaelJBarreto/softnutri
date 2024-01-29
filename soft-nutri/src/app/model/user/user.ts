import { Person } from '../person/person';
import { UserType } from '../shared/userType';

export class User extends Person {
    userType!: UserType;
    password!: string;
    language!:string;
    crn!: string;
    annuity!: boolean;
}