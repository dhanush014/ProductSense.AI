import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-resultsloading',
  imports: [CommonModule],
  templateUrl: './resultsloading.html',
  styleUrl: './resultsloading.css'
})
export class Resultsloading {

  constructor(private router: Router) {}
  ngOnInit(): void {
    setTimeout(() => this.router.navigate(['/results']), 2000);
  }
}
