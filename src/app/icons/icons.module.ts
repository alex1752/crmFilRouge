import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { IconCloseComponent } from './components/icon-close/icon-close.component';
import { IconDeleteComponent } from './components/icon-delete/icon-delete.component';
import { IconEditComponent } from './components/icon-edit/icon-edit.component';
import { IconNavComponent } from './components/icon-nav/icon-nav.component';
import { IconInfoComponent } from './components/icon-info/icon-info.component';

@NgModule({
  declarations: [
    IconCloseComponent,
    IconNavComponent,
    IconEditComponent,
    IconDeleteComponent,
    IconInfoComponent,
  ],
  imports: [CommonModule, FontAwesomeModule],
  exports: [
    IconCloseComponent,
    IconNavComponent,
    IconEditComponent,
    IconDeleteComponent,
    IconInfoComponent,
  ],
})
export class IconsModule {}
