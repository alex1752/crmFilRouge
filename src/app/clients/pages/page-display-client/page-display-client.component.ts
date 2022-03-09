import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Client } from 'src/app/core/models/client';
import { Order } from 'src/app/core/models/order';
import { OrdersService } from 'src/app/orders/services/orders.service';
import { ClientsService } from '../../services/clients.service';

@Component({
  selector: 'app-page-display-client',
  templateUrl: './page-display-client.component.html',
  styleUrls: ['./page-display-client.component.scss'],
})
export class PageDisplayClientComponent implements OnInit {
  public headersClient: string[];
  public headersOrder: string[];
  public collectionClient!: Client[];
  public collectionOrder!: Order[];
  public idClient: number;

  constructor(
    private clientsService: ClientsService,
    private ordersService: OrdersService,
    private activatedRoute: ActivatedRoute
  ) {
    this.idClient = Number(this.activatedRoute.snapshot.paramMap.get('id'));

    this.clientsService
      .getItemById(this.idClient)
      .subscribe((data) => (this.collectionClient = data));

    this.ordersService.collection$.subscribe(
      (data) =>
        (this.collectionOrder = data.filter(
          (order) => order.idclient === this.idClient
        ))
    );

    this.headersClient = [
      '#',
      'Nom',
      'Prenom',
      'Entreprise',
      'Email',
      'Telephone',
      'Mobile',
      'Actif',
      'Notes',
    ];

    this.headersOrder = [
      '#',
      'Label',
      'nb Jours',
      'tjmht',
      'Total HT',
      'Total TTC',
      'Statut',
      'Type de Commande',
      'Notes',
    ];
  }

  ngOnInit(): void {}
}
