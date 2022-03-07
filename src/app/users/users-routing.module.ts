import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageAddUserComponent } from './pages/page-add-user/page-add-user.component';


const routes: Routes = [
  { path: 'add', component: PageAddUserComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
