import { Routes } from '@angular/router';


import { ProfileComponent } from '../../../shared/pages/profile/profile/profile.component';
import { TimeLineComponent } from 'src/app/admin/pages/time-line/time-line/time-line.component';
import { KidsListComponent } from 'src/app/admin/pages/kids/kids-list/kids-list.component'

import { GroupListComponent } from 'src/app/admin/pages/core/groups/group-list/group-list.component';
import { DashboardComponent } from 'src/app/admin/pages/core/dashboard/dashboard.component';
import { VolunteachersListComponent } from 'src/app/admin/pages/volunteachers/volunteachers-list/volunteachers-list.component';
import { VillagesComponent } from 'src/app/admin/pages/core/villages/villages.component';
import { SchoolsComponent } from 'src/app/admin/pages/core/school/schools/schools.component';
import { ProjectComponent } from 'src/app/admin/pages/projects/project/project.component';
import { PaymentsComponent } from 'src/app/admin/pages/donation/payments/payments.component';
import { CreateSessionsComponent } from 'src/app/admin/pages/sessions/create-sessions/create-sessions.component';
import { AllSessionsComponent } from 'src/app/admin/pages/sessions/all-sessions/all-sessions.component';
import { EventsComponent } from 'src/app/admin/pages/events/events/events.component';
import { FeedbackComponent } from 'src/app/admin/pages/feedbacks/feedback/feedback.component';
import { ActivitiesComponent } from 'src/app/admin/pages/events/activities/activities.component';
import { EventsListComponent } from 'src/app/admin/pages/events/events-list/events-list.component';
import { AreasComponent } from 'src/app/admin/pages/core/areas/areas.component';
import { ContentComponent } from 'src/app/admin/pages/core/content/shared-components/content/content.component';
import { UploadContentComponent } from 'src/app/admin/pages/core/content/upload-content/upload-content.component';
import { ApplicantRequestComponent } from 'src/app/admin/pages/core/applicant-request/applicant-request.component';
import { EventDetailsComponent } from 'src/app/admin/pages/events/event-details/event-details.component';
import { ProjectDetailsComponent } from 'src/app/admin/pages/projects/project-details/project-details.component';
import { ReportsListComponent } from 'src/app/admin/pages/reports/reports-list/reports-list.component';
import { ReportComponent } from 'src/app/admin/pages/reports/report/report.component';
import { KidsTableComponent } from 'src/app/admin/pages/kids/kids-table/kids-table.component';
import { CreatePostComponent } from 'src/app/admin/pages/time-line/create-post/create-post.component';
import { KidReportComponent } from 'src/app/admin/pages/kids/kid-report/kid-report.component';
import { AttendanceComponent } from 'src/app/admin/pages/attendance/attendance/attendance.component';
import { KidsReportsListComponent } from 'src/app/admin/pages/kids/kids-reports-list/kids-reports-list.component';
import { EditProjectComponent } from 'src/app/admin/pages/projects/edit-project/edit-project.component';
import { EditEventComponent } from 'src/app/admin/pages/events/edit-event/edit-event.component';
import { EditSchoolComponent } from 'src/app/admin/pages/core/school/edit-school/edit-school.component';
import { EditVillageComponent } from 'src/app/admin/pages/core/edit-village/edit-village.component';
import { EditSessionComponent } from 'src/app/admin/pages/sessions/edit-session/edit-session.component';
import { AdminKidsListComponent } from 'src/app/admin/pages/kids/admin-kids-list/admin-kids-list.component';


export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',       component: DashboardComponent },
    // { path: 'volunteachers/:id/profile',         component: ProfileComponent },
    { path: 'volunteachers/:id/profile',   component: ProfileComponent, 
    children: [
          {
            path: '',
            loadChildren: 'src/app/shared/pages/profile/profile.module#ProfileModule'
          }
        ] 
    },
    { path: 'post',      component: TimeLineComponent},
    { path: 'volunteachers', component: VolunteachersListComponent},
    { path: 'villages', component: VillagesComponent},
    { path: 'villages/:id/edit', component: EditVillageComponent},
    { path: 'schools', component: SchoolsComponent},
    { path: 'schools/:id/edit', component: EditSchoolComponent},
    { path: 'projects', component: ProjectComponent},
    { path: 'donation', component: PaymentsComponent},
    { path: 'kids-list', component: KidsTableComponent},
    { path: 'sessions/:id/attendance', component: AttendanceComponent},
    { path: 'sessions/:id/edit', component: EditSessionComponent},
    { path: 'sessions', component: CreateSessionsComponent},
    { path: 'sessions/all', component: AllSessionsComponent},
    { path: 'events', component: EventsComponent},
    { path: 'sessions/:id/feedback', component: FeedbackComponent},
    { path: 'activities', component: ActivitiesComponent},
    { path: 'events/all', component: EventsListComponent},
    { path: 'villages/:id/areas', component: AreasComponent},
    { path: 'content', component: UploadContentComponent},
    { path: 'groups', component: GroupListComponent},
    { path: 'requests', component: ApplicantRequestComponent},
    { path: 'events/:id/event-details', component: EventDetailsComponent},
    { path: 'events/:id/edit', component: EditEventComponent},
    { path: 'projects/:id/project-details', component: ProjectDetailsComponent},
    { path: 'projects/:id/edit', component: EditProjectComponent},
    { path: 'reports-list', component: ReportsListComponent},
    { path: 'report', component:ReportComponent},
    { path: 'kids', component:AdminKidsListComponent},
    { path: 'kids/:id/reports/:rid/kids-report', component: KidReportComponent},
    { path: 'kids/:id/reports', component: KidsReportsListComponent},
    { path: 'post/create-post', component: CreatePostComponent},
    { path: 'profile',   component: ProfileComponent, 
    children: [
          {
            path: '',
            loadChildren: 'src/app/shared/pages/profile/profile.module#ProfileModule'
          }
        ] 
    },

];
