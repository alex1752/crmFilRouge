import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageSignInComponent } from './pages/page-sign-in/page-sign-in.component';

const routes: Routes = [
  { path: 'sign-in', component: PageSignInComponent },
  // { path: 'sign-up', component: PageSignUpComponent },
  // { path: 'reset', component: PageResetPasswordComponent },
  // { path: 'forgot', component: PageForgotPasswordComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LoginRoutingModule {}
