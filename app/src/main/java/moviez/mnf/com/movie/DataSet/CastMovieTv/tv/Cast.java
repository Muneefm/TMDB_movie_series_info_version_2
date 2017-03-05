
package moviez.mnf.com.movie.DataSet.CastMovieTv.tv;

import java.util.HashMap;
import java.util.Map;

public class Cast {

    private String character;
    private String creditId;
    private Integer episodeCount;
    private String firstAirDate;
    private Integer id;
    private String name;
    private String originalName;
    private String poster_path;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The character
     */
    public String getCharacter() {
        return character;
    }

    /**
     * 
     * @param character
     *     The character
     */
    public void setCharacter(String character) {
        this.character = character;
    }

    /**
     * 
     * @return
     *     The creditId
     */
    public String getCreditId() {
        return creditId;
    }

    /**
     * 
     * @param creditId
     *     The credit_id
     */
    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    /**
     * 
     * @return
     *     The episodeCount
     */
    public Integer getEpisodeCount() {
        return episodeCount;
    }

    /**
     * 
     * @param episodeCount
     *     The episode_count
     */
    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
