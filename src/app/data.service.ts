import { throwError as observableThrowError, Observable, Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { Injector } from '@angular/core'
import { Router, NavigationStart } from "@angular/router";
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, map, refCount, publishLast, takeLast, take } from 'rxjs/operators';
import { of } from 'rxjs';

declare var $: any;


// const API_KEY = makeStateKey('api');
@Injectable(
  {
    providedIn: 'root'
  }
)
export class DataService {
  api: any;
  public dataServiceCart: Subject<any> = new Subject<any>();

  constructor(public injector: Injector, private _router: Router, private _http: HttpClient) {

  }

  callRestful(type: string, url: string, options?: { params?: {}, body?: {}, headerData?: {} }) {
    let params;
    let body;

    if (options != undefined && options['params'] != undefined)
      params = options['params'];
    if (options != undefined && options['body'] != undefined)
      body = options['body'];

    let headers = {
      'Content-Type': 'application/json',
      //'Content-Type': 'application/x-www-form-urlencoded',
      // 'Access-Control-Allow-Origin':'http://localhost:4200',
      // 'Access-Control-Allow-Credentials':true,
      'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE',
      'access-control-allow-origin': '*'
    };

    if (options && options.headerData && Object.keys(options.headerData).length) {
      if (options.headerData['Content-Type']) {
        headers['Content-Type'] = options.headerData['Content-Type'];
      }
      if (options.headerData['Access-Control-Allow-Methods']) {
        headers['Access-Control-Allow-Methods'] = options.headerData['Access-Control-Allow-Methods'];
      }

    }

    let start_time = new Date().getTime();

    switch (type) {
      case 'GET':
        let getOptions = {};
        if (headers["Content-Type"] && headers["Content-Type"].indexOf("text") > -1) {
          getOptions = { params: params, headers: headers, responseType: "text" };
        } else {
          getOptions = { params, headers };
        }
        return this._http.get(url, getOptions).pipe(map(res => {
          let request_time = new Date().getTime() - start_time;
          return res;
        }), catchError(err => this.handleError(err)));
      case 'POST':
        return this._http.post(url, body, { headers, withCredentials: true }).pipe(map(res => {
          let request_time = new Date().getTime() - start_time;
          return res;
        }), catchError(err => this.handleError(err)));
      case 'PUT':
        return this._http.put(url, body, { headers, withCredentials: true }).pipe(map(res => res), catchError(err => this.handleError(err)));
      case 'DELETE':
        return this._http.delete(url, { headers, withCredentials: true }).pipe(map(res => res), catchError(err => this.handleError(err)));
      default:
        return null;
    }
  }
  private handleError(error: HttpErrorResponse | any) {

    if (error.status == 403) {
      alert("something went wrong, please try to refresh the page");
    }
    else if (error.status == 401) {

    } else {
      if (error.type == "error") {
      }
    }

    if (error) {
      if (error.error && error.error.errorMessages) {
      }
      else {
      }
    }

    return observableThrowError(error);;
  }

  showMessage(cssClass, msg) {
    //console.log("show message cssClass " + cssClass + " msg is " + msg);
    var x = document.getElementById("alert-box");
    var classType = "";
    x.innerHTML = msg;
    if (cssClass == "error") classType = "show-error";
    if (cssClass == "success") classType = "show-sucess";
    x.className = classType;
    setTimeout(function () {
      x.className = x.className.replace(classType, "");
    }, 5000);

  }

  public getCookie(name: string) {
    //TODO test split and value
    let ca: Array<string> = document.cookie.split('; ');
    let caLen: number = ca.length;
    let cookieName = name + "=";
    let c: string;

    for (let i: number = 0; i < caLen; i += 1) {
      c = ca[i].replace(/^\s\+/g, "");
      if (c.indexOf(cookieName) == 0) {
        return c.substring(cookieName.length, c.length);
      }
    }
    return "";
  }

  public deleteCookie(name) {
    this.setCookie(name, "", -1);
  }

  public setCookie(name: string, value: string, expireDays: number) {
    let d: Date = new Date();
    d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
    let expires: string = "expires=" + d.toUTCString();
    document.cookie = name + "=" + value + "; " + expires + ";";
  }

  public timestampToData(ts) {
    let d = new Date(ts);
    let dd: any = d.getDate();
    let mm: any = (d.getMonth() + 1);
    let yy = d.getFullYear();

    if (dd < 10) {
      dd = '0' + dd;
    }
    if (mm < 10) {
      mm = '0' + mm;
    }
    return dd + '-' + mm + '-' + yy;
  }

  public getTimeFromTimestamp(ts, isNoTime?: boolean) {
  }

}