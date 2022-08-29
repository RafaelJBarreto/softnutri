import { Component, OnInit, Inject, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { PersonService } from 'src/app/services';
import { ProfessionalService } from 'src/app/services/professional/professional.service';

@Component({
  selector: 'app-professional-delete',
  templateUrl: './professional-delete.component.html',
  styleUrls: ['./professional-delete.component.scss']
})
export class ProfessionalDeleteComponent{

  public action: any;

  constructor(
    @Optional() public dialogRef: MatDialogRef<ProfessionalDeleteComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private service: ProfessionalService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {
  }

  onClickDelete(): void {
    this.service.delete(this.data.id).subscribe({
      next: data => {
        this.snackBar.open(this.translate.instant(data.message), '', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['success']
        });
      },
      error: err => {
        this.snackBar.open(this.translate.instant('PROFESSIONAL.ERROR_DELETE_PROFESSIONAL'), 'Error', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['error']
        });
      }
    });
    this.dialogRef.close();
  }

}
