import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-page-sign-out',
  templateUrl: './page-sign-out.component.html',
  styleUrls: ['./page-sign-out.component.scss'],
})
export class PageSignOutComponent implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit(): void {}

  public logout() {
    this.authService.logout();
  }
}
