import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { User } from 'src/app/core/models/user';

@Component({
  selector: 'app-form-user',
  templateUrl: './form-user.component.html',
  styleUrls: ['./form-user.component.scss'],
})
export class FormUserComponent implements OnInit {
  @Input() public init!: User;
  @Output() public submitted: EventEmitter<User>;
  public form!: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.submitted = new EventEmitter<User>();
  }
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      login: [this.init.login],
      email: [this.init.email],
      motDePasse: [this.init.motDePasse],
    });
  }

  public onSubmit() {
    this.submitted.emit(this.form.value); // creer un evenement submitted et initialise le flux
  }
}
