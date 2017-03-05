
package moviez.mnf.com.movie.DataSet.first;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private Boolean adult;
    private Object backdropPath;
    private Integer id;
    private String originalTitle;
    private String release_date;
    private Object poster_path;
    private Double popularity;
    private String title;
    private Boolean video;
    private Float vote_average;
    private Integer vote_count;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     * 
     * @param adult
     *     The adult
     */
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    /**
     * 
     * @return
     *     The backdropPath
     */
    public Object getBackdropPath() {
        return backdropPath;
    }

    /**
     * 
     * @param backdropPath
     *     The backdrop_path
     */
    public void setBackdropPath(Object backdropPath) {
        this.backdropPath = backdropPath;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * 
     * @param originalTitle
     *     The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * 
     * @return
     *     The release_date
     */
    public String getReleaseDate() {
        return release_date;
    }

    /**
     * 
     * @param release_date
     *     The release_date
     */
    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    /**
     * 
     * @return
     *     The poster_path
     */
    public Object getPosterPath() {
        return poster_path;
    }

    /**
     * 
     * @param poster_path
     *     The poster_path
     */
    public void setPosterPath(Object poster_path) {
        this.poster_path = poster_path;
    }

    /**
     * 
     * @return
     *     The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     * 
     * @param popularity
     *     The popularity
     */
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The video
     */
    public Boolean getVideo() {
        return video;
    }

    /**
     * 
     * @param video
     *     The video
     */
    public void setVideo(Boolean video) {
        this.video = video;
    }

    /**
     * 
     * @return
     *     The vote_average
     */
    public Float getVoteAverage() {
        return vote_average;
    }

    /**
     * 
     * @param vote_average
     *     The vote_average
     */
    public void setVoteAverage(Float vote_average) {
        this.vote_average = vote_average;
    }

    /**
     * 
     * @return
     *     The vote_count
     */
    public Integer getVoteCount() {
        return vote_count;
    }

    /**
     * 
     * @param vote_count
     *     The vote_count
     */
    public void setVoteCount(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
