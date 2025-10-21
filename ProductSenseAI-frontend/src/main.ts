import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { provideHttpClient } from '@angular/common/http';
import { App } from './app/app';

const providers = [
  provideHttpClient(),  // provide HttpClient globally
  ...(appConfig.providers ?? [])
];

bootstrapApplication(App, { ...appConfig, providers})
  .catch((err) => console.error(err));
