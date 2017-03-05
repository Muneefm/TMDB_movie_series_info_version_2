
package moviez.mnf.com.movie.DataSet.Posters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoviePosters {

    private Integer id;
    private List<Backdrop> backdrops = new ArrayList<Backdrop>();
    private List<Poster> posters = new ArrayList<Poster>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The backdrops
     */
    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    /**
     * 
     * @param backdrops
     *     The backdrops
     */
    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

    /**
     * 
     * @return
     *     The posters
     */
    public List<Poster> getPosters() {
        return posters;
    }

    /**
     * 
     * @param posters
     *     The posters
     */
    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
