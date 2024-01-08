//    private String tconst;
//     private String titleType;
//     private String primaryTitle;
//     private String originalTitle;
//     private boolean isAdult;
//     private String startYear;
//     private String endYear;
//     private String runtimeMinutes;
//     private String genres;


export interface IMovie {
    tconst: string;
    titleType: string;
    primaryTitle: string;
    originalTitle: string;
    isAdult: boolean;
    startYear: string;
    endYear: string;
    runtimeMinutes: string;
    genres: string;
}

// IMovieResponse
// {"content":[{"tconst":"tt0343112","titleType":"short","primaryTitle":"Traffic Crossing Leeds Bridge","originalTitle":"Traffic Crossing Leeds Bridge","startYear":"1888","endYear":"\\N","runtimeMinutes":"1","genres":"Documentary,Short","adult":false},{"tconst":"tt0392728","titleType":"short","primaryTitle":"Roundhay Garden Scene","originalTitle":"Roundhay Garden Scene","startYear":"1888","endYear":"\\N","runtimeMinutes":"1","genres":"Documentary,Short","adult":false},{"tconst":"tt0466876","titleType":"short","primaryTitle":"Leisurely Pedestrians, Open Topped Buses and Hansom Cabs with Trotting Horses","originalTitle":"Leisurely Pedestrians, Open Topped Buses and Hansom Cabs with Trotting Horses","startYear":"1889","endYear":"\\N","runtimeMinutes":"1","genres":"Documentary,Short","adult":false},{"tconst":"tt0361921","titleType":"short","primaryTitle":"Monkeyshines, No. 1","originalTitle":"Monkeyshines, No. 1","startYear":"1890","endYear":"\\N","runtimeMinutes":"1","genres":"Documentary,Short","adult":false},{"tconst":"tt0416047","titleType":"short","primaryTitle":"Monkeyshines, No. 3","originalTitle":"Monkeyshines, No. 3","startYear":"1890","endYear":"\\N","runtimeMinutes":"1","genres":"Short","adult":false},{"tconst":"tt0416046","titleType":"short","primaryTitle":"Monkeyshines, No. 2","originalTitle":"Monkeyshines, No. 2","startYear":"1890","endYear":"\\N","runtimeMinutes":"1","genres":"Short","adult":false},{"tconst":"tt0241393","titleType":"short","primaryTitle":"Duncan and Another, Blacksmith Shop","originalTitle":"Duncan and Another, Blacksmith Shop","startYear":"1891","endYear":"\\N","runtimeMinutes":"\\N","genres":"Short","adult":false},{"tconst":"tt0241735","titleType":"short","primaryTitle":"Monkey and Another, Boxing","originalTitle":"Monkey and Another, Boxing","startYear":"1891","endYear":"\\N","runtimeMinutes":"\\N","genres":"Documentary,Short","adult":false},{"tconst":"tt0241394","titleType":"short","primaryTitle":"Duncan or Devonald with Muslin Cloud","originalTitle":"Duncan or Devonald with Muslin Cloud","startYear":"1891","endYear":"\\N","runtimeMinutes":"\\N","genres":"Short","adult":false},{"tconst":"tt0241715","titleType":"short","primaryTitle":"Men Boxing","originalTitle":"Men Boxing","startYear":"1891","endYear":"\\N","runtimeMinutes":"1","genres":"Action,Documentary,Short","adult":false}],"pageable":{"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":10,"offset":0,"paged":true,"unpaged":false},"totalPages":84900,"totalElements":849000,"last":false,"first":true,"size":10,"number":0,"sort":{"sorted":true,"unsorted":false,"empty":false},"numberOfElements":10,"empty":false}

export interface IMovieResponse {
    content: IMovie[];
    totalElements: number;
    totalPages: number;
    last: boolean;
    size: number;
    number: number;
    sort: string;
    first: boolean;
    numberOfElements: number;
}
