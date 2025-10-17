import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-results',
  imports: [CommonModule],
  templateUrl: './results.html',
  styleUrl: './results.css'
})
export class Results {
  score: number = 87;
  feedback: string = 'Excellent root cause analysis and clear estimation strategy. Keep iterating for precision and clarity!';

  constructor(private router: Router) {}

  returnHome() {
    this.router.navigate(['/']);
  }
}
