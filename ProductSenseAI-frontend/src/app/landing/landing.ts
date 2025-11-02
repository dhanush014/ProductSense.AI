import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Navbar } from '../navbar/navbar';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, Navbar],
  templateUrl: './landing.html',
  styleUrl: './landing.css'
})
export class Landing implements OnInit{
  // @ViewChild('typingSubtitle', { static: false }) typingSubtitle!: ElementRef;

  subtitle = 'Your AI-powered Product Management interview coach.';

  landingForm!: FormGroup;

  submitted = false;
  wittyGreeting = '';
  loading = false;
timerMessage = '';

  mcqQuestions: any[] = [];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private api: ApiService // Inject your service here
  ) {}
  
  ngAfterViewInit() {
    this.loopTyping();
  }

  ngOnInit(): void {
    this.landingForm = this.fb.group({
      name: ['', Validators.required],
      role: ['', Validators.required]
    });
  }

  get f() {
    return this.landingForm.controls;
  }
  loopTyping() {
    const el = document.getElementById('typingSubtitle');
    const text = this.subtitle;
    let i = 0;
    const typeSpeed = 100;   // ms per character (change for slower/faster)
    const holdDelay = 2100; // ms full sentence stays shown
  
    function typeChar() {
      if (!el) return;
      if (i <= text.length) {
        el.textContent = text.slice(0, i);
        i++;
        setTimeout(typeChar, typeSpeed);
      } else {
        setTimeout(() => {
          i = 0;
          el.textContent = '';
          setTimeout(typeChar, 550); // short pause before starting to type again
        }, holdDelay);
      }
    }
    typeChar();
  }
  
  onSubmit(): void {
    this.submitted = true;

    if (this.landingForm.invalid) {
      return;
    }
    this.loading = true;
    this.timerMessage = 'Using a free version of Render so waking up the backend, give us a couple of minutes...';
    setTimeout(() => {
      this.timerMessage = '';
      this.loading = false;
    }, 20000);
  
    const { name, role } = this.landingForm.value;
    console.log('Starting interview for', name, role);


    this.api.startInterview(name, role).subscribe({
      next: resp => {
        localStorage.setItem('sessionId', resp.sessionId);
        localStorage.setItem('wittyGreeting', resp.wittyGreeting);
        localStorage.setItem('userName', name);
        localStorage.setItem('choice', role);
        this.router.navigate(['/section1']).then(() => {
          // Clear loading and message only after navigation completes
          this.loading = false;
          this.timerMessage = '';});
      },
     error: () => {
      alert('Failed to start interview. Please try again.');
      // this.loading = false;
      // this.timerMessage = '';
    }
  });
  }
}
