import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AdminLayoutComponent } from './core/layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './core/layouts/auth-layout/auth-layout.component';
import { UserLayoutComponent } from './core/layouts/user-layout/user-layout/user-layout.component';
import { ErrorComponent } from './core/components/error/error.component';
import { InternalServerErrorComponent } from './core/components/internal-server-error/internal-server-error.component';
import { RoleCanActivateGuard } from './core/guards/role-can-activate.guard';

const routes: Routes =[
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  }, {
    path: 'admin',
    component: AdminLayoutComponent,
    children: [
      {
        path: '',
        loadChildren: './core/layouts/admin-layout/admin-layout.module#AdminLayoutModule'
      }
    ]
  }, {
    path: '',
    component: AuthLayoutComponent,
    children: [
      {
        path: '',
        loadChildren: 'src/app/core/layouts/auth-layout/auth-layout.module#AuthLayoutModule'
      }
    ]
  }, {
      path: 'user',
      component: UserLayoutComponent,
      canActivate:[RoleCanActivateGuard],
      children: [
        {
          path: '',
          loadChildren: './core/layouts/user-layout/user-layout.module#UserLayoutModule'
        }
      ]
    },
    {
      path: 'error-page',
      component:ErrorComponent
    }, {
        path: 'internal-server-error',
        component:InternalServerErrorComponent
      }, {
    path: '**',
    redirectTo: 'error'
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
      useHash: false
    })
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
