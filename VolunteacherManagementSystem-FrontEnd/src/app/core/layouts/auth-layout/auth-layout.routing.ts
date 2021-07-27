import { Routes } from '@angular/router';
import { EventParticipationComponent } from 'src/app/home/pages/event-participation/event-participation.component';
import { HomeComponent } from 'src/app/home/pages/home/home.component';

import { LoginComponent } from 'src/app/home/pages/login/login.component';
import { RegisterComponent } from 'src/app/home/pages/register/register.component';
import { VolunteacherFormComponent } from 'src/app/home/pages/volunteacher-form/volunteacher-form.component';
import { ChangePasswordComponent } from 'src/app/shared/pages/profile/change-password/change-password.component';

import { ForgotPasswordComponent } from '../../components/forgot-password/forgot-password.component';
import { SendOtpComponent } from '../../components/send-otp/send-otp.component';

export const AuthLayoutRoutes: Routes = [
    { path: 'login',          component: LoginComponent },
    { path: 'register',       component: RegisterComponent },
    { path: '',           component: HomeComponent },
    { path: 'change-password', component: ChangePasswordComponent},
    { path: 'event/:id/event-registration',           component: EventParticipationComponent},
    { path: 'forgot-password',           component:ForgotPasswordComponent},
    { path: 'forgot-password/send-otp',           component:SendOtpComponent},
    { path: 'volunteacher-registration',           component:VolunteacherFormComponent},
   

];
