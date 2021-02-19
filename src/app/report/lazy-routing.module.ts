import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { ReportComponent } from './report.component';

const routes: Routes = [

  { path: '', component: ReportComponent },
  { path: '/lazy', component: HomeComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class LazyRoutingModule { }
