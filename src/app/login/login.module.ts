import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { PageForgotPasswordComponent } from './pages/page-forgot-password/page-forgot-password.component';
import { PageResetPasswordComponent } from './pages/page-reset-password/page-reset-password.component';
import { PageSignInComponent } from './pages/page-sign-in/page-sign-in.component';
import { PageSignUpComponent } from './pages/page-sign-up/page-sign-up.component';


@NgModule({
  declarations: [
    PageForgotPasswordComponent,
    PageResetPasswordComponent,
    PageSignInComponent,
    PageSignUpComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule
  ],
  exports: [
    PageForgotPasswordComponent,
    PageResetPasswordComponent,
    PageSignInComponent,
    PageSignUpComponent
  ]
})
export class LoginModule { }
