// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.


export const environment = {
  production: false,
  hmr: false,

  api: {
    rota: "http://localhost:8080/",
    user: {
      user: "user",
      autenticar: "usuario/auth/signin",
      refreshtoken: "user/auth/refreshtoken",
      logout: "user/auth/logout"
    },
    people:{
      people:"people",
      listarTodos:"people/"
    }
  },
  redirect: {
    DASHBOARD: '/dashboard',
    TYPOGRAPHY: '/typography',
    TABLES: '/tables',
    NOTIFICATION: '/notification',
    UI_ELEMENTS_ICONS: '/ui/icons',
    UI_ELEMENTS_CHARTS: '/ui/charts',
    UI_ELEMENTS_MAP: '/ui/map',
    LOGIN: '/login'
  }


};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
