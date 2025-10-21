import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Navbar } from '../navbar/navbar';
@Component({
  selector: 'app-results',
  imports: [CommonModule,Navbar],
  templateUrl: './results.html',
  styleUrl: './results.css'
})
export class Results {
  score: number = 0;
  feedback: string = '';
  suggestion: string = '';
  closing: string = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    // 🟢 NEW: Load final score from localStorage
    const finalScore = localStorage.getItem('finalScore');
    if (finalScore) {
      const data = JSON.parse(finalScore);
      this.score = data.score;
      this.feedback = data.feedback;
      this.suggestion = data.suggestion;
      this.closing = data.closing;
    }
  }
  returnHome() {
    // 🟢 NEW: Clear localStorage on return home
    localStorage.clear();
    this.router.navigate(['/']);
  }
}
