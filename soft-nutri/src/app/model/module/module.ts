export class ModuleUser {
    idModule: number;
    name: String;
    pathBase: String;
    icon: String;
    orders: number;

    constructor(idModule: number, name: String, pathBase: String, icon: String, orders: number) {
        this.idModule = idModule;
        this.name = name;
        this.pathBase = pathBase;
        this.icon = icon;
        this.orders = orders;
    }
}