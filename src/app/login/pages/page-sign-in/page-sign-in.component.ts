import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-page-sign-in',
  templateUrl: './page-sign-in.component.html',
  styleUrls: ['./page-sign-in.component.scss'],
})
export class PageSignInComponent implements OnInit {
  public form!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      login: [''],
      motDePasse: [''],
    });
  }

  public onSubmit() {
    this.authService
      .login(
        this.form.controls['login'].value,
        this.form.controls['motDePasse'].value
      )
      .subscribe((data) => {
        this.router.navigate(['clients']),
          this.authService.setAuthToken(data['token'], data['login']);
      });
  }
}
