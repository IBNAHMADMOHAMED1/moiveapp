import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import {IMovie, IMovieResponse} from 'src/app/interfaces/interfaces';
import { Status } from 'src/app/interfaces/enums';
import {MovieService} from '../../../services/movie.service';
import {MatRadioChange} from '@angular/material/radio';


@Component({
  selector: 'app-data-table-admin',
  templateUrl: './data-table-admin.component.html',
  styleUrls: ['./data-table-admin.component.scss']
})
export class DataTableAdminComponent implements OnInit {
    displayedColumns = ['tconst', 'titleType', 'primaryTitle', 'originalTitle', 'isAdult', 'startYear', 'endYear', 'runtimeMinutes', 'genres' ];
    dataSource: MatTableDataSource<IMovie>;
    moiveData: IMovie[];
    movieResponse: IMovieResponse;
    showFilter = false;
    showForm = false;
    totalItems = 100;
    pageSize = 5;
    pageSizeOptions = [2, 5, 10, 25, 100];

    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;
    primaryTitle: any;
    originalTitle: any;
    startYear: any;
    filterType: any;
    sortOrder: any;

    constructor(private movieService: MovieService) {
    }

    ngOnInit() {
        this.getMoives(0, 10);
    }
    /**
     * Set the paginator and sort after the view init since this component will
     * be able to query its view for the initialized paginator and sort.
     */
    ngAfterViewInit() {
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    applySearch(filterValue: string) {
        filterValue = filterValue.trim(); // Remove whitespace
        filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
        this.dataSource.filter = filterValue;
    }



    refreshTable() {
        this.showForm = false;
        this.pageSize = 5;
        this.getMoives(0, 10);
    }


    getMoives(pageNumber: number, size: number, primaryTitle?: string, originalTitle?: string, startYear?: string, sortBystartYear?: string) {
        this.movieService.getAllMovies(pageNumber, size, primaryTitle, originalTitle, startYear, sortBystartYear).subscribe(movies => {
            console.log("this is move",movies);
            this.movieResponse = movies;
            this.moiveData = this.movieResponse.content;
            // console.log(this.offersData);
            this.dataSource = new MatTableDataSource(this.moiveData);
            console.log(this.dataSource);
            this.dataSource.paginator = this.paginator;
        });
    }

    handlePageEvent(event) {
        console.log(event);
        this.getMoives(event.pageIndex, event.pageSize);
        event.pageIndex = 0;
        event.pageSize = 5;
    }

    filterTypeChange($event: MatRadioChange) {
        this.filterType = $event.value;
        console.log(this.filterType);
    }

    filterData() {
        console.log(this.primaryTitle, this.originalTitle, this.startYear, this.filterType);
        this.getMoives(this.paginator.pageIndex, this.paginator.pageSize, this.primaryTitle, this.originalTitle, this.startYear, this.filterType);
        this.showFilter = !this.showFilter;
    }
    sortData(value: any) {
        console.log(value);
        this.getMoives(this.paginator.pageIndex, this.paginator.pageSize, undefined, undefined, undefined, value);
    }
}

