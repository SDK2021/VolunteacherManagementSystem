import { DonateComponent } from 'src/app/home/pages/donate/donate.component';
import { EventParticipationComponent } from 'src/app/home/pages/event-participation/event-participation.component';
import { HomeComponent } from 'src/app/home/pages/home/home.component';
import { LoginComponent } from 'src/app/home/pages/login/login.component';
import { RegisterComponent } from 'src/app/home/pages/register/register.component';
import { ChangePasswordComponent } from 'src/app/shared/pages/profile/change-password/change-password.component';
import { PageNotFoundComponent } from '../../components/page-not-found/page-not-found.component';
export var AuthLayoutRoutes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'home', component: HomeComponent },
    { path: 'donate', component: DonateComponent },
    { path: 'change-password', component: ChangePasswordComponent },
    { path: 'event/:id/event-registration', component: EventParticipationComponent },
    { path: 'error', component: PageNotFoundComponent }
];
//# sourceMappingURL=auth-layout.routing.js.map