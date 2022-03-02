import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
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
  constructor(private orderService: OrdersService) {
    this.headers = [
      '#',
      'Label',
      'tjmht',
      'nb Jours',
      'TVA',
      'Statut',
      'Type de Commande',
      'Notes',
      '# Client',
    ];
    this.collection$ = orderService.collection$;
  }

  ngOnInit(): void {}
}
