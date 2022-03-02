import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { ClientsRoutingModule } from './clients-routing.module';
import { PageAddClientComponent } from './pages/page-add-client/page-add-client.component';
import { PageEditClientComponent } from './pages/page-edit-client/page-edit-client.component';
import { PageListClientsComponent } from './pages/page-list-clients/page-list-clients.component';
import { PageDisplayClientComponent } from './pages/page-display-client/page-display-client.component';

@NgModule({
  declarations: [
    PageAddClientComponent,
    PageEditClientComponent,
    PageListClientsComponent,
    PageDisplayClientComponent,
  ],
  imports: [CommonModule, ClientsRoutingModule, SharedModule],
  exports: [
    PageAddClientComponent,
    PageEditClientComponent,
    PageListClientsComponent,
    PageDisplayClientComponent,
  ],
})
export class ClientsModule {}
