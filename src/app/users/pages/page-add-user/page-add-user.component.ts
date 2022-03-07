import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/core/models/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-page-add-user',
  templateUrl: './page-add-user.component.html',
  styleUrls: ['./page-add-user.component.scss']
})
export class PageAddUserComponent implements OnInit {

  public user: User;
  constructor(private usersService: UserService, private router: Router) {
    this.user = new User();
  }

  ngOnInit(): void {}

  public action(item: User) {
    this.usersService
      .add(item)
      .subscribe(() => this.router.navigate(['clients']));
  }

}
