import { Component } from '@angular/core';
import { ServiceService } from '../../services/service.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  public message : string = '';

  constructor(public service:ServiceService){ }

  ngOnInit() {
    this.hello();
  }

  hello() {
    this.service.hello().subscribe({
      next : (response) =>{
        console.log(response);
        this.message = response.message;
      },
      error: (error) => {
        console.error('Error occurred:', error);
      }
  })
  }
}
