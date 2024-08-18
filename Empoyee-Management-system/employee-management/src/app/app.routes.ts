import { Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { LoginComponent } from './components/login/login.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { ApiDashboardComponent } from './components/api-dashboard/api-dashboard.component';
import { EmployeeListComponent } from './components/api-dashboard/employee-list/employee-list.component';
import { EditemployeeComponent } from './components/api-dashboard/editemployee/editemployee.component';
import { AddemployeeComponent } from './components/api-dashboard/addemployee/addemployee.component';

export const routes: Routes = [

   {
    path : '',
    redirectTo : 'layout',
     pathMatch : 'full'
   },

   {
    path:'layout',
    component : LayoutComponent
   },
   {
    path:'login',
    component : LoginComponent
   },
   {
    path:'register',
    component : UserRegisterComponent
   },
   {
    path:'dashboard',
    component:ApiDashboardComponent
   },{

      path : 'add-employee',
      component:AddemployeeComponent
   },
   {

    path:'edit-employee',
    component:EditemployeeComponent
   },
   {
    path:'all-employeeList',
    component:EmployeeListComponent
   }

];
