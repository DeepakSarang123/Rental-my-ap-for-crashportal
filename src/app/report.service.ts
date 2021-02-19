import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Settings } from "./settings/settings";
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { DataService } from './data.service';
import { SettingshttpService } from './settings/settingshttp.service';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  dataRow: any;
  constructor(
    public http: HttpClient,
    public dataService: DataService,
    public settingshttpService: SettingshttpService
  ) { }

  getCrashReport(): Observable<any> {
    return this.dataService.callRestful('GET', this.settingshttpService.getCrashReportAPI(),
      {
        params: {
          uUIDName: this.dataRow.uUIDName,
          macId: this.dataRow.macId,
          dateOfPost: this.dataRow.dateOfPost,
          imageName: this.dataRow.imageName,
          versionId: this.dataRow.versionId
        },
        headerData:

        {
          'Content-Type': 'text'
        }
      }
    )
  }
}
