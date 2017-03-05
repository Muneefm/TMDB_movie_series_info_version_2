
package moviez.mnf.com.movie.DataSet.CastCrew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CastCrewData {

    private Integer id;
    private List<Cast> cast = new ArrayList<Cast>();
    private List<Crew> crew = new ArrayList<Crew>();
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
     *     The cast
     */
    public List<Cast> getCast() {
        return cast;
    }

    /**
     * 
     * @param cast
     *     The cast
     */
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    /**
     * 
     * @return
     *     The crew
     */
    public List<Crew> getCrew() {
        return crew;
    }

    /**
     * 
     * @param crew
     *     The crew
     */
    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
