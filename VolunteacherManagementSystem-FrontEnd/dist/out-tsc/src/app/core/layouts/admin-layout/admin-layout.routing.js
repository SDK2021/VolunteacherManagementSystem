//import { DashboardComponent } from 'src/app/admin/pages/core/dashboard/dashboard.component';
import { ProfileComponent } from '../../../shared/pages/profile/profile/profile.component';
import { TimeLineComponent } from 'src/app/shared/pages/time-line/time-line.component';
import { AttendanceComponent } from 'src/app/kids/pages/attendance/attendance/attendance.component';
import { KidsListComponent } from 'src/app/kids/pages/kids-list/kids-list.component';
import { KidsAttendanceComponent } from 'src/app/kids/pages/kids-attendance/kids-attendance.component';
import { KidsReportComponent } from 'src/app/kids/pages/kids-report/kids-report.component';
import { GroupListComponent } from 'src/app/admin/pages/core/groups/shared-components/group-list/group-list.component';
import { SessionReportingComponent } from 'src/app/user/pages/session-reporting/session-reporting.component';
import { DashboardComponent } from 'src/app/admin/pages/core/dashboard/dashboard.component';
import { VolunteachersListComponent } from 'src/app/admin/pages/volunteachers/volunteachers-list/volunteachers-list.component';
import { VillagesComponent } from 'src/app/admin/pages/core/villages/villages.component';
import { ActivitiesComponent } from 'src/app/admin/pages/events/activities/activities.component';
import { RequirementsComponent } from 'src/app/admin/pages/core/school/requirements/requirements.component';
import { SchoolsComponent } from 'src/app/admin/pages/core/school/schools/schools.component';
import { ProjectComponent } from 'src/app/admin/pages/projects/project/project.component';
export var AdminLayoutRoutes = [
    { path: 'dashboard', component: DashboardComponent },
    { path: 'volunteachers/profile', component: ProfileComponent },
    { path: 'posts', component: TimeLineComponent },
    { path: 'attendance', component: AttendanceComponent },
    { path: 'kids-attendance', component: KidsAttendanceComponent },
    { path: 'kids', component: KidsListComponent },
    { path: 'kids-report', component: KidsReportComponent },
    { path: 'groups', component: GroupListComponent },
    { path: 'session-report', component: SessionReportingComponent },
    { path: 'volunteachers', component: VolunteachersListComponent },
    { path: 'villages', component: VillagesComponent },
    { path: 'activities', component: ActivitiesComponent },
    { path: 'requirements', component: RequirementsComponent },
    { path: 'schools', component: SchoolsComponent },
    { path: 'projects', component: ProjectComponent },
];
//# sourceMappingURL=admin-layout.routing.js.map