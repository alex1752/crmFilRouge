import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from 'src/app/core/models/client';
import { ClientsService } from '../../services/clients.service';

@Component({
  selector: 'app-page-list-clients',
  templateUrl: './page-list-clients.component.html',
  styleUrls: ['./page-list-clients.component.scss'],
})
export class PageListClientsComponent implements OnInit {
  public headers: string[];
  public collection$!: Observable<Client[]>;
  public states!: string[];
  constructor(private clientService: ClientsService) {
    this.headers = [
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
    this.collection$ = clientService.collection$;
  }

  ngOnInit(): void {}
}
