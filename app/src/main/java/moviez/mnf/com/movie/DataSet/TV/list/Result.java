
package moviez.mnf.com.movie.DataSet.TV.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Result {

    private String backdropPath;
    private Integer id;
    private String originalName;
    private String firstAirDate;
    private List<String> originCountry = new ArrayList<String>();
    private String poster_path;
    private Double popularity;
    private String name;
    private Float vote_average;
    private Integer vote_count;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The backdropPath
     */
    public String getBackdropPath() {
        return backdropPath;
    }

    /**
     * 
     * @param backdropPath
     *     The backdrop_path
     */
    public void setBackdropPath(String backdropPath) {
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
     *     The originalName
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * 
     * @param originalName
     *     The original_name
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * 
     * @return
     *     The firstAirDate
     */
    public String getFirstAirDate() {
        return firstAirDate;
    }

    /**
     * 
     * @param firstAirDate
     *     The first_air_date
     */
    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    /**
     * 
     * @return
     *     The originCountry
     */
    public List<String> getOriginCountry() {
        return originCountry;
    }

    /**
     * 
     * @param originCountry
     *     The origin_country
     */
    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    /**
     * 
     * @return
     *     The poster_path
     */
    public String getPosterPath() {
        return poster_path;
    }

    /**
     * 
     * @param poster_path
     *     The poster_path
     */
    public void setPosterPath(String poster_path) {
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
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
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
