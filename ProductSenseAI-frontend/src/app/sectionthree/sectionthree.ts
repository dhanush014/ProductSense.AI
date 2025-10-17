import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sectionthree',
  imports: [CommonModule, FormsModule],
  templateUrl: './sectionthree.html',
  styleUrl: './sectionthree.css'
})
export class Sectionthree implements OnInit{
  timer = signal(300); // 5 minutes
  question = 'You are the PM for a new Image Processing & Storage Platform and need to estimate annual storage costs.';
  hints = [
    '10 million DAUs',
    '5% upload a photo daily → 500,000 uploads/day',
    'Each photo: 4 MB original + 1 MB + 0.5 MB optimized versions → 5.5 MB total per photo',
    'Cloud storage: $0.02 per GB per month'
  ];
  estimate: number | null = null;
  reasoning: string = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.startTimer();
  }
  
  startTimer(): void {
    const interval = setInterval(() => {
      const current = this.timer();
      if (current > 0) {
        this.timer.set(current - 1);
      } else {
        clearInterval(interval);
        // Optionally disable inputs or auto-submit
      
        this.onSubmit();
      }
    }, 1000);
  }

  onSubmit(): void {
    console.log('Estimate submitted:', this.estimate);
    console.log('Reasoning:', this.reasoning);
    this.router.navigate(['/loading']); // update to your results or next page
  }
}
