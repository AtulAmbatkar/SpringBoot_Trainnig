import { Routes } from '@angular/router';
import { LoginComponent } from './componets/login/login.component';
import { DashboardComponent } from './componets/dashboard/dashboard.component';
import { LayoutComponent } from './componets/layout/layout.component';

export const routes: Routes = [

    {
        path:'',redirectTo:'login',pathMatch:'full'
    },
    {
        path:'login',
        component:LoginComponent
    },
    {
        path:'',
        component: LayoutComponent,
        children: [
            {
            path: 'dashboard',
            component : DashboardComponent

            }
        ]
    }
];
