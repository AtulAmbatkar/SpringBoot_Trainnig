import { Routes } from '@angular/router';
import { LoginComponent } from './componets/login/login.component';
import { DashboardComponent } from './componets/dashboard/dashboard.component';
import { RegisterComponent } from './componets/register/register.component';

export const routes: Routes = [

    {
        path:'',redirectTo:'login',pathMatch:'full'
    },
    {
        path:'login',
        component:LoginComponent
    },
    {
        path:'register',
        component:RegisterComponent
    },
    {
       path:'dashboard',
       component : DashboardComponent
    }
];
