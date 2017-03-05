
package moviez.mnf.com.movie.DataSet.TV.itemDetail;

import java.util.HashMap;
import java.util.Map;

public class CreatedBy {

    private Integer id;
    private String name;
    private Object profilePath;
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
     *     The profilePath
     */
    public Object getProfilePath() {
        return profilePath;
    }

    /**
     * 
     * @param profilePath
     *     The profile_path
     */
    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
