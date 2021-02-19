import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
const localUrl = 'assets/CrashPortalDumpGetApi.json';


@Injectable({
  providedIn: 'root'
})

export class DataService {
  dataRow: any;
  lists: any;
  constructor(private http: HttpClient) { }

}

