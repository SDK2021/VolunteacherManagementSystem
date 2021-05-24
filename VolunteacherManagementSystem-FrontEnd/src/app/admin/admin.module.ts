import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComponentsModule } from '../core/components/components.module';
import { SessionsComponent } from './pages/sessions/shared-components/sessions/sessions.component';
import { GroupListComponent } from './pages/core/groups/group-list/group-list.component';
import { ContentComponent } from './pages/core/content/shared-components/content/content.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './pages/core/dashboard/dashboard.component';
import { VolunteachersListComponent } from './pages/volunteachers/volunteachers-list/volunteachers-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { VillagesComponent } from './pages/core/villages/villages.component';
import { TabsComponent } from './components/tabs/tabs.component';
import { SchoolsComponent } from './pages/core/school/schools/schools.component';
import { AreasComponent } from './pages/core/areas/areas.component';
import { ProjectComponent } from './pages/projects/project/project.component';
import { PaymentsComponent } from './pages/donation/payments/payments.component';
import { CreateSessionsComponent } from './pages/sessions/create-sessions/create-sessions.component';
import { SharedModule } from '../shared/shared.module';
import { AllSessionsComponent } from './pages/sessions/all-sessions/all-sessions.component';
import { EventsComponent } from './pages/events/events/events.component';
import { FeedbackComponent } from './pages/feedbacks/feedback/feedback.component';
import { ActivitiesComponent } from './pages/events/activities/activities.component';
import { EventsListComponent } from './pages/events/events-list/events-list.component';
import { UploadContentComponent } from './pages/core/content/upload-content/upload-content.component';
import { ApplicantRequestComponent } from './pages/core/applicant-request/applicant-request.component';
import { EventDetailsComponent } from './pages/events/event-details/event-details.component';
import { ProjectDetailsComponent } from './pages/projects/project-details/project-details.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { ReportsListComponent } from './pages/reports/reports-list/reports-list.component';
import { ReportComponent } from './pages/reports/report/report.component';
import { VillagePipe } from './filters/village.pipe';
import { SessionPipe } from './filters/session.pipe';
import { EventPipe } from './filters/event.pipe';
import { SchoolPipe } from './filters/school.pipe';
import { DonationPipe } from './filters/donation.pipe';
import { KidsPipe } from './filters/kids.pipe';
import { FeedbackPipe } from './filters/feedback.pipe';
import {MatDialogModule} from '@angular/material/dialog';
import { DialogBoxComponent } from './components/dialog-box/dialog-box.component';
import { KidsTableComponent } from './pages/kids/kids-table/kids-table.component';
import { VolunteacherPipe } from './filters/volunteacher.pipe';
import { TimeLineComponent } from './pages/time-line/time-line/time-line.component';
import { KidReportComponent } from './pages/kids/kid-report/kid-report.component';
import { CreatePostComponent } from './pages/time-line/create-post/create-post.component';
import { MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { AttendanceComponent } from './pages/attendance/attendance/attendance.component';
import { KidsReportsListComponent } from './pages/kids/kids-reports-list/kids-reports-list.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { SafePipe } from './pages/core/content/SafePipe.pipe';
import { EditProjectComponent } from './pages/projects/edit-project/edit-project.component';
import { EditEventComponent } from './pages/events/edit-event/edit-event.component';
import { EditSchoolComponent } from './pages/core/school/edit-school/edit-school.component';
import { EditVillageComponent } from './pages/core/edit-village/edit-village.component';
import { EditSessionComponent } from './pages/sessions/edit-session/edit-session.component';
import { AdminKidsListComponent } from './pages/kids/admin-kids-list/admin-kids-list.component';

@NgModule({
  declarations: [
    SessionsComponent,
    GroupListComponent,
    ContentComponent,
    DashboardComponent,
    VolunteachersListComponent,
    VillagesComponent,
    TabsComponent,
    SchoolsComponent,
    AreasComponent,
    ProjectComponent,
    PaymentsComponent,
    CreateSessionsComponent,
    AllSessionsComponent,
    EventsComponent,
    FeedbackComponent,
    ActivitiesComponent,
    EventsListComponent,
    UploadContentComponent,
    ApplicantRequestComponent,
    EventDetailsComponent,
    ProjectDetailsComponent,
    ReportsListComponent,
    ReportComponent,
    VillagePipe,
    SessionPipe,
    EventPipe,
    SchoolPipe,
    DonationPipe,
    KidsPipe,
    FeedbackPipe,
    DialogBoxComponent,
    KidsTableComponent,
    VolunteacherPipe,
    TimeLineComponent,
    KidReportComponent,
    CreatePostComponent,
    AttendanceComponent,
    KidsReportsListComponent,
    SafePipe,
    EditProjectComponent,
    EditEventComponent,
    EditSchoolComponent,
    EditVillageComponent,
    EditSessionComponent,
    AdminKidsListComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule,
    BrowserAnimationsModule,
    BrowserModule,
    RouterModule,
    NgbModule,
    SharedModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    InfiniteScrollModule
  ],
  exports:[
    KidsPipe
  ]
})
export class AdminModule { }
