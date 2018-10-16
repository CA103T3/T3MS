package com.boxoffice.model;

import java.sql.Date;

import com.movie.model.MovieVO;

public class BoxOfficeVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1240329133845125022L;
    private String ranking_no;
    private String movie_no;
    private String moviename;
    private Date statistics;
    private Integer rank;
    private Integer loc;
    private MovieVO movieVO;

    public MovieVO getMovieVO() {
        return movieVO;
    }
    public void setMovieVO(MovieVO movieVO) {
        this.movieVO = movieVO;
    }
    public String getRanking_no() {
        return ranking_no;
    }
    public void setRanking_no(String ranking_no) {
        this.ranking_no = ranking_no;
    }
    public String getMovie_no() {
        return movie_no;
    }
    public void setMovie_no(String movie_no) {
        this.movie_no = movie_no;
    }
    public String getMoviename() {
        return moviename;
    }
    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }
    public Date getStatistics() {
        return statistics;
    }
    public void setStatistics(Date statistics) {
        this.statistics = statistics;
    }
    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public Integer getLoc() {
        return loc;
    }
    public void setLoc(Integer loc) {
        this.loc = loc;
    }
}
