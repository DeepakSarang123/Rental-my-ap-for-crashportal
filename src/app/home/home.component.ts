import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource, MatTable } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SettingshttpService } from '../settings/settingshttp.service'
import { Subscription } from 'rxjs';
import { ReportService } from '../report.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements AfterViewInit {
  subscription: Subscription;
  displayedColumns: string[] = [
    'uUIDName',
    'macId',
    'dateOfPost',
    'imageName',
    'versionId',
  ];
  dataSource: any;
  list: any;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  page: any;

  constructor(
    public http: HttpClient,
    public settingshttpService: SettingshttpService,
    public reportService: ReportService,
  ) { }

  /**
   * Method to handle any additional initialization tasks.
   * Assign the data to the data source for the table to render.
   */
  ngAfterViewInit() {
    this.settingshttpService.getCrashData().subscribe(res => {
      this.list = res;
      this.dataSource = new MatTableDataSource(this.list);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      // const list = document.getElementsByClassName('mat-paginator-range-label');
      // list[0].innerHTML = 'Page: ' + this.page.toString();
    });

  }
  /**
   * Apply filter to the table.
   * @param {string} filterValue
   * @returns string
   */

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }


  public getRecord(row: any) {
    this.reportService.dataRow = row;
  }
}
