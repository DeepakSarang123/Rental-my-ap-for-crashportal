import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { ReportService } from '../report.service';


@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss'],

})
export class ReportComponent implements OnInit {
  constructor(
    public router: Router,
    public reportService: ReportService) { }

  dataRow: any;
  fileUrl;
  fileContent: any;
  isLoadingData: Boolean = false;
  
  
  
  

  ngOnInit() {
    this.dataRow = this.reportService.dataRow;
    this.reportService.getCrashReport().subscribe(res => {
      this.isLoadingData = true;
      this.fileContent = res;
      
    })
  }
  /**
   * @param {}
   * @desc Saves the reponse in a text file and downloads that file
   */
  download() {
    if (this.fileContent) {
      //get formatted date
      const months = [
        'Jan',
        'Feb',
        'Mar',
        'Apr',
        'Ma',
        'Jun',
        'Jul',
        'Aug',
        'Sept',
        'Oct',
        'Nov',
        'Dec',
      ];
      const date = new Date();
      const format_date = `${months[date.getMonth()]
        }_${date.getDate()}_${date.getFullYear()}`;

      // download the text file
      const blob = new Blob([this.fileContent], { type: 'text/plain' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.setAttribute('hidden', '');
      a.setAttribute('href', url);
      a.setAttribute('download', `${this.dataRow.uUIDName}_crash_report.txt`);
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
    }
  }
  goBack() {
    this.router.navigateByUrl('/home');
  }

}




