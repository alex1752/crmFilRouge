import { Component, OnInit } from '@angular/core';
import { faInfo, IconDefinition } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-icon-info',
  templateUrl: './icon-info.component.html',
  styleUrls: ['./icon-info.component.scss'],
})
export class IconInfoComponent implements OnInit {
  public myIcon: IconDefinition;

  constructor() {
    this.myIcon = faInfo;
  }

  ngOnInit(): void {}
}
