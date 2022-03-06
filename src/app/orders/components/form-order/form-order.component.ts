import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { StateOrder } from 'src/app/core/enums/state-order';
import { TypeOrder } from 'src/app/core/enums/type-order';
import { Order } from 'src/app/core/models/order';

@Component({
  selector: 'app-form-order',
  templateUrl: './form-order.component.html',
  styleUrls: ['./form-order.component.scss'],
})
export class FormOrderComponent implements OnInit {
  @Input() public init!: Order;
  @Output() public submitted: EventEmitter<Order>;
  public states: string[];
  public types: string[];
  public form!: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.submitted = new EventEmitter<Order>();
    this.states = Object.values(StateOrder);
    this.types = Object.values(TypeOrder);
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      label: [this.init.label],
      tjmht: [this.init.tjmht],
      dureejours: [this.init.dureejours],
      tva: [this.init.tva],
      statut: [this.init.statut],
      typecommande: [this.init.typecommande],
      notes: [this.init.notes],
      idclient: [this.init.idclient],
      id: [this.init.id],
    });
    console.log(this.form.value);
    console.log(this.init);
  }

  ngOnDestroy() {
    console.log(this.form.value);
  }

  public onSubmit() {
    this.submitted.emit(this.form.value); // creer un evenement submitted et initialise le flux
  }
}
