import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from 'src/app/core/models/client';
import { ClientsService } from '../../services/clients.service';

@Component({
  selector: 'app-page-add-client',
  templateUrl: './page-add-client.component.html',
  styleUrls: ['./page-add-client.component.scss'],
})
export class PageAddClientComponent implements OnInit {
  public client: Client;
  constructor(private clientService: ClientsService, private router: Router) {
    this.client = new Client();
  }

  ngOnInit(): void {}

  public action(item: Client) {
    this.clientService
      .add(item)
      .subscribe(() => this.router.navigate(['clients']));
  }
}
