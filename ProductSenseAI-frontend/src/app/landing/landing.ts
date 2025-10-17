import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './landing.html',
  styleUrl: './landing.css'
})
export class Landing implements OnInit{
  landingForm!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.landingForm = this.fb.group({
      name: ['', Validators.required],
      role: ['', Validators.required]
    });
  }

  get f() {
    return this.landingForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.landingForm.invalid) {
      return;
    }

    // Process form values or store in a service as needed
    const { name, role } = this.landingForm.value;
    console.log('Starting interview for', name, role);

    // Navigate to next route
    this.router.navigate(['/section1']);
  }
}
