import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { PostsComponent } from './posts/posts.component';
import { ProjectsComponent } from './projects/projects.component';


const routes: Routes = [
  { path: 'change-password',     component: ChangePasswordComponent},
  { path: 'posts',               component: PostsComponent},
  { path: 'edit-profile',        component: EditProfileComponent},
  { path: 'projects',            component: ProjectsComponent},
 
 ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
