import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UploadImgComponent } from './upload-img/upload-img.component';
import { ImageCropperModule } from 'ngx-image-cropper';
import  {  PdfViewerModule  }  from  'ng2-pdf-viewer';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AdminSidebarComponent } from './admin-sidebar/admin-sidebar.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { SendOtpComponent } from './send-otp/send-otp.component';
import { AngularFireModule } from '@angular/fire';
import { environment } from 'src/environments/environment';
import { AngularFireDatabaseModule } from '@angular/fire/database';
import { AngularFireStorageModule } from '@angular/fire/storage';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ErrorComponent } from './error/error.component';
import { InternalServerErrorComponent } from './internal-server-error/internal-server-error.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    NgbModule,
    ImageCropperModule,
    PdfViewerModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireDatabaseModule,
    AngularFireStorageModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressBarModule,
    MatProgressSpinnerModule
  ],
  declarations: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    UploadImgComponent,
    PageNotFoundComponent,
    AdminSidebarComponent,
    ForgotPasswordComponent,
    SendOtpComponent,
    ErrorComponent,
    InternalServerErrorComponent
  ],
  exports: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    UploadImgComponent,
    AdminSidebarComponent
  ]
})
export class ComponentsModule { }
