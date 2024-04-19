import { ProfessionalService } from 'src/app/services/professional/professional.service';

import { Component, Inject, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-professional-delete',
  templateUrl: './professional-delete.component.html',
  styleUrls: ['./professional-delete.component.scss'],
})
export class ProfessionalDeleteComponent {
  public action: any;

  constructor(
    @Optional() public dialogRef: MatDialogRef<ProfessionalDeleteComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private service: ProfessionalService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {}

  onClickDelete(): void {
    this.service
      .delete(this.data.id)
      .then((data) => {
        this.snackBar.open(this.translate.instant(data.message), '', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000,
        });
      })
      .catch((err) => {
        this.snackBar.open(this.translate.instant(err.error.message), 'Error', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000,
        });
      });
    this.dialogRef.close();
  }
}
