import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Client } from 'src/app/core/models/client';
import { ClientsService } from '../../services/clients.service';

@Component({
  selector: 'app-page-edit-client',
  templateUrl: './page-edit-client.component.html',
  styleUrls: ['./page-edit-client.component.scss'],
})
export class PageEditClientComponent implements OnInit {
  public id!: number;
  public client$!: Observable<Client[]>;

  constructor(
    private clientsService: ClientsService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.client$ = this.clientsService.getItemById(this.id);
  }

  ngOnInit(): void {}

  public action(item: Client) {
    this.clientsService
      .update(item)
      .subscribe(() => this.router.navigate(['clients']));
  }
}
