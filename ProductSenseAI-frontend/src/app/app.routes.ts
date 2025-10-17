import { Routes } from '@angular/router';
import { Landing } from './landing/landing';
import { Sectionone } from './sectionone/sectionone';
import { Sectiontwo } from './sectiontwo/sectiontwo';
import { Sectionthree } from './sectionthree/sectionthree';
import { Resultsloading } from './resultsloading/resultsloading';
import { Results } from './results/results';

export const routes: Routes = [
    { path: '', component: Landing },
    { path: 'section1', component: Sectionone },
    { path: 'section2', component: Sectiontwo },
    { path: 'section3', component: Sectionthree },
    { path: 'loading', component: Resultsloading },
    { path: 'results', component: Results }

];
