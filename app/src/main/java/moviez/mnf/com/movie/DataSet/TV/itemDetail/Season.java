
package moviez.mnf.com.movie.DataSet.TV.itemDetail;

import java.util.HashMap;
import java.util.Map;

public class Season {

    private String air_date;
    private Integer episode_count;
    private Integer id;
    private String poster_path;
    private Integer season_number;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The air_date
     */
    public String getAirDate() {
        return air_date;
    }

    /**
     * 
     * @param air_date
     *     The air_date
     */
    public void setAirDate(String air_date) {
        this.air_date = air_date;
    }

    /**
     * 
     * @return
     *     The episode_count
     */
    public Integer getEpisodeCount() {
        return episode_count;
    }

    /**
     * 
     * @param episode_count
     *     The episode_count
     */
    public void setEpisodeCount(Integer episode_count) {
        this.episode_count = episode_count;
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
     *     The season_number
     */
    public Integer getSeasonNumber() {
        return season_number;
    }

    /**
     * 
     * @param season_number
     *     The season_number
     */
    public void setSeasonNumber(Integer season_number) {
        this.season_number = season_number;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
