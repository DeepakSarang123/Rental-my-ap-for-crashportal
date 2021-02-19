import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportComponent } from './report.component';
import { LazyRoutingModule } from './lazy-routing.module';
import { MaterialModule } from '../materialModule';


@NgModule({
  declarations: [ReportComponent],
  imports: [
    CommonModule,
    LazyRoutingModule,
    MaterialModule,
  ]
})
export class LazyModule { }
