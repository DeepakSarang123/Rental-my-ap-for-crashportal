import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { initializer } from '../app/utils/app.init';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


/*
************************
USER COMPONENTS
************************
*/
import { HeaderComponent } from '../app/header/header.component';
import { FooterComponent } from '../app/footer/footer.component';
import { HomeComponent } from '../app/home/home.component';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { SettingshttpService } from './settings/settingshttp.service';
import { MaterialModule } from '../app/materialModule';
import { ReportComponent } from './report/report.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    //ReportComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    KeycloakAngularModule
  ],
  providers: [{
    provide: APP_INITIALIZER,
    useFactory: initializer,
    deps: [KeycloakService, SettingshttpService],
    multi: true
  }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
