import { Paper } from './paper';

export class Permission {
    idModule!: number;
    description!: string;
    checked!: boolean;
    paper!: Array<Paper>;
}