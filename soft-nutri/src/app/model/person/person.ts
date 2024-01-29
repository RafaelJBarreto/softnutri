import { Phone } from '../phone/phone';
import { Gender } from '../shared/gender';

export class Person {
    idPerson!: number;
    name!: string;
    email!: string;
    cpf!: string;
    birthDate!: Date;
    address!:string;
    gender!: Gender;
    phones!: Array<Phone>;

}