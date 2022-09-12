import { Time } from "@angular/common";
import { User } from "../user/user";

export class Calendar {
    idCalendar!: number;
    professional: User = new User;
    patient: User = new User;
    dateOfDay!: Date;
    hourOfDay!: Time;
    note!: String;
    completed!: boolean;
}