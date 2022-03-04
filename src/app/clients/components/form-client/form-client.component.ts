import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Client } from 'src/app/core/models/client';

@Component({
  selector: 'app-form-client',
  templateUrl: './form-client.component.html',
  styleUrls: ['./form-client.component.scss'],
})
export class FormClientComponent implements OnInit {
  @Input() public init!: Client;
  @Output() public submitted: EventEmitter<Client>;
  public states: boolean[];
  public form!: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.submitted = new EventEmitter<Client>();
    this.states = [true,false];
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      id: [this.init.id],
      nom: [this.init.nom],
      prenom: [this.init.prenom],
      entreprise: [this.init.entreprise],
      email: [this.init.email],
      telephone: [this.init.telephone],
      mobile: [this.init.mobile],
      actif: [this.init.actif],
      notes: [this.init.notes],
    });
  }

  public onSubmit() {
    this.submitted.emit(this.form.value); // creer un evenement submitted et initialise le flux
  }
}
