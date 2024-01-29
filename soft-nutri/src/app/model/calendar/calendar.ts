import { User } from "../user/user";

export class Calendar {
    idCalendar!: any;
    professional: User = new User;
    patient: User = new User;
    dateOfDay!: Date;
    hourOfDayAux!: Date;
    hourOfDay!: Date;
    note!: String;
    completed!: boolean;
    cancel!: boolean;
}