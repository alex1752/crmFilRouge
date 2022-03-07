import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { PageAddUserComponent } from './pages/page-add-user/page-add-user.component';
import { UsersRoutingModule } from './users-routing.module';
import { FormUserComponent } from './components/form-user/form-user.component';

@NgModule({
  declarations: [PageAddUserComponent, FormUserComponent],
  imports: [CommonModule, UsersRoutingModule, SharedModule],
  exports: [PageAddUserComponent],
})
export class UsersModule {}
