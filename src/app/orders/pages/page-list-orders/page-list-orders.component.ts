import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { StateOrder } from 'src/app/core/enums/state-order';
import { TypeOrder } from 'src/app/core/enums/type-order';
import { Order } from 'src/app/core/models/order';
import { OrdersService } from '../../services/orders.service';

@Component({
  selector: 'app-page-list-orders',
  templateUrl: './page-list-orders.component.html',
  styleUrls: ['./page-list-orders.component.scss'],
})
export class PageListOrdersComponent implements OnInit {
  public headers: string[];
  public collection$!: Observable<Order[]>;
  public states!: string[];
  public types!: string[];
  constructor(private ordersService: OrdersService) {
    this.headers = [
      'Actions',
      '#',
      'Label',
      'nb Jours',
      'tjmht',
      'Total HT',
      'Total TTC',
      'Statut',
      'Type de Commande',
      'Notes',
      '# Client',
    ];
    this.collection$ = ordersService.collection$;
    this.states = Object.values(StateOrder);
    this.types = Object.values(TypeOrder);
  }

  ngOnInit(): void {}

  public changeState(item: Order, event: any): void {
    const state = event.target.value;
    this.ordersService
      .changeState(item, state)
      .subscribe((data) => (item = data));
  }

  public changeType(item: Order, event: any): void {
    const type = event.target.value;
    this.ordersService
      .changeType(item, type)
      .subscribe((data) => (item = data));
  }
}
