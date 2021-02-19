import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Settings } from "./settings";
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { DataService } from '../data.service';


@Injectable({
  providedIn: 'root'
})
export class SettingshttpService {

  constructor(private http: HttpClient, public dataService: DataService) {
  }
  public settings: Settings;
  dataRow: any;

  init(): Promise<any> {

    return new Promise(
      (resolve) => {
        this.http.get('../assets/settings.json')
          .toPromise()
          .then(response => {
            this.settings = <Settings>response;
            // this.settingsService.settings = <Settings>response;
            resolve();
          }
          )
      }
    );
  }
  getKeycloakUrl() {
    return this.settings.keycloakURL;

  }
  getRealm() {
    return this.settings.realm;
  }
  getClientId() {
    return this.settings.clientId;
  }
  getCrashPortalUrl() {
    return this.settings.crashPortalURL;
  }
  getCrashReportAPI() {
    return this.settings.crashReportAPI;
  }
  getCrashData(): Observable<any> {
    // return this.http.get(this.getCrashPortalUrl()).pipe(map(res => res)
    // );
    return this.dataService.callRestful('GET', this.getCrashPortalUrl());

  }
  // getCrashReport(): Observable<any> {
  //   return this.http.get(this.getCrashReportAPI(), {
  //     params: {
  //       uUIDName: this.dataRow.uUIDName,
  //       macId: this.dataRow.macId,
  //       dateOfPost: this.dataRow.dateOfPost,
  //       imageName: this.dataRow.imageName,
  //       versionId: this.dataRow.versionId
  //     },
  //     responseType: 'text',
  //     observe: 'response'
  //   })
  // }

}
