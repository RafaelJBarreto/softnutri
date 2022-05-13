import { Injectable } from '@angular/core';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
    providedIn: 'root'
})
export class PaginatorI18n {

    constructor(private translate: TranslateService) { }

    getPaginatorIntl(): MatPaginatorIntl {
        const paginatorIntl = new MatPaginatorIntl();

        paginatorIntl.itemsPerPageLabel = this.translate.instant('GLOBAL_WORD.ITEMS_PER_PAGE_LABEL');
        paginatorIntl.nextPageLabel = this.translate.instant('GLOBAL_WORD.NEXT_PAGE_LABEL');
        paginatorIntl.previousPageLabel = this.translate.instant('GLOBAL_WORD.PREVIOUS_PAGE_LABEL');
        paginatorIntl.firstPageLabel = this.translate.instant('GLOBAL_WORD.FIRST_PAGE_LABEL');
        paginatorIntl.lastPageLabel = this.translate.instant('GLOBAL_WORD.LAST_PAGE_LABEL');
        paginatorIntl.getRangeLabel = this.getRangeLabel.bind(this);
        
        return paginatorIntl;
    }

    private getRangeLabel(page: number, pageSize: number, length: number): string {
        if (length === 0 || pageSize === 0) {
            return this.translate.instant('GLOBAL_WORD.RANGE_PAGE_LABEL_1', { length });
        }
        length = Math.max(length, 0);
        const startIndex = page * pageSize;
        const endIndex = startIndex < length ? Math.min(startIndex + pageSize, length) : startIndex + pageSize;
        return this.translate.instant('GLOBAL_WORD.RANGE_PAGE_LABEL_2', { startIndex: startIndex + 1, endIndex, length });
    }
}