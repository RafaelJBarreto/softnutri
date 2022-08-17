import { Person } from "../person/person";
import { UserType } from "../shared/userType";

export class User extends Person {
    userType!: UserType;
}