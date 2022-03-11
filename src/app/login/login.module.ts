import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { LoginRoutingModule } from './login-routing.module';
import { PageForgotPasswordComponent } from './pages/page-forgot-password/page-forgot-password.component';
import { PageResetPasswordComponent } from './pages/page-reset-password/page-reset-password.component';
import { PageSignInComponent } from './pages/page-sign-in/page-sign-in.component';
import { PageSignUpComponent } from './pages/page-sign-up/page-sign-up.component';
import { PageSignOutComponent } from './pages/page-sign-out/page-sign-out.component';

@NgModule({
  declarations: [
    PageForgotPasswordComponent,
    PageResetPasswordComponent,
    PageSignInComponent,
    PageSignUpComponent,
    PageSignOutComponent,
  ],
  imports: [CommonModule, LoginRoutingModule, SharedModule],
  exports: [
    PageForgotPasswordComponent,
    PageResetPasswordComponent,
    PageSignInComponent,
    PageSignUpComponent,
    PageSignOutComponent,
  ],
})
export class LoginModule {}
