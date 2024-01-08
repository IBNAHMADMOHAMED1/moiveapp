import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IMovieResponse} from '../interfaces/interfaces';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class MovieService {
    private GET_ALL_MOVIES_URL = 'http://localhost:9090/api/movies';

    constructor(private http: HttpClient) {}
    getAllMovies(pageNumber: number, size: number, primaryTitle?: string, originalTitle?: string, startYear?: string, sortBystartYear?: string):
        Observable<IMovieResponse> {
        let url = `${this.GET_ALL_MOVIES_URL}?page=${pageNumber}&size=${size}
        ${(primaryTitle !== null && primaryTitle !== undefined) ? '&primaryTitle=' + primaryTitle : ''}
        ${(originalTitle !== null && originalTitle !== undefined) ? '&originalTitle=' + originalTitle : ''}
        ${(startYear !== null && startYear !== undefined) ? '&startYear=' + startYear : ''}
        ${(sortBystartYear !== null && sortBystartYear !== undefined) ? '&sortBystartYear=' + sortBystartYear : ''}
        `;
        url = url.replace(/\s/g, '');
        console.log(url);
        return this.http.get<IMovieResponse>(url);

    }
}
