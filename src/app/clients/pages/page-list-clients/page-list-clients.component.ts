import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  public collection!: Client[];
  public states!: boolean[];

  constructor(private clientsService: ClientsService, private router: Router) {
    this.clientsService.collection$.subscribe(
      (data) => (this.collection = data)
    );
    this.states = [true, false];
    this.headers = [
      'Actions',
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
  }

  ngOnInit(): void {}

  public changeState(item: Client, event: any): void {
    const state = event.target.value;
    this.clientsService.changeState(item, state).subscribe((data) => {
      item = data;
    });
  }

  public goToEdit(id: number) {
    this.router.navigate(['clients', 'edit', id]);
  }

  public delete(item: Client) {
    this.clientsService
      .delete(item)
      .subscribe((data) =>
        this.clientsService.collection$.subscribe(
          (data2) => (this.collection = data2)
        )
      );
  }
}
