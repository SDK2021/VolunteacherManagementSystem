import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { KidsAttendanceComponent } from 'src/app/kids/pages/kids-attendance/kids-attendance.component';
import { SessionsComponent } from 'src/app/admin/pages/sessions/shared-components/sessions/sessions.component';

const routes: Routes = [
    { path: 'sessions-list',     component: SessionsComponent},
    { path: 'sessions-list/:id/kids-attendance',         component: KidsAttendanceComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AttendanceRoutingModule { }
