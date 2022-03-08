import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../login/guards/auth.guard';
import { PageAddOrderComponent } from './pages/page-add-order/page-add-order.component';
import { PageEditOrderComponent } from './pages/page-edit-order/page-edit-order.component';
import { PageListOrdersComponent } from './pages/page-list-orders/page-list-orders.component';

const routes: Routes = [
  { path: '', component: PageListOrdersComponent, canActivate: [AuthGuard] },
  {
    path: 'edit/:id',
    component: PageEditOrderComponent,
    canActivate: [AuthGuard],
  },
  { path: 'add', component: PageAddOrderComponent, canActivate: [AuthGuard] },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OrdersRoutingModule {}
