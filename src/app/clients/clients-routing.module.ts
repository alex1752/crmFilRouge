import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../login/guards/auth.guard';
import { PageAddClientComponent } from './pages/page-add-client/page-add-client.component';
import { PageDisplayClientComponent } from './pages/page-display-client/page-display-client.component';
import { PageEditClientComponent } from './pages/page-edit-client/page-edit-client.component';
import { PageListClientsComponent } from './pages/page-list-clients/page-list-clients.component';

const routes: Routes = [
  { path: '', component: PageListClientsComponent, canActivate: [AuthGuard] },
  {
    path: 'edit/:id',
    component: PageEditClientComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'display/:id',
    component: PageDisplayClientComponent,
    canActivate: [AuthGuard],
  },
  { path: 'add', component: PageAddClientComponent, canActivate: [AuthGuard] },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClientsRoutingModule {}
