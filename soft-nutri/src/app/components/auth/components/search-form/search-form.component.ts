import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss']
})
export class SearchFormComponent implements OnInit {
  @Output() sendSignForm = new EventEmitter<void>();
  public formSearch!: UntypedFormGroup;

  public ngOnInit(): void {
    this.formSearch = new UntypedFormGroup({
      emailCad: new UntypedFormControl('', [Validators.required, Validators.email]),
      passwordCad: new UntypedFormControl('', [Validators.required])
    });
  }

  public sign(): void {
    if (this.formSearch.valid) {
      this.sendSignForm.emit();
    }
  }

}