
package moviez.mnf.com.movie.DataSet.CastCrew;

import java.util.HashMap;
import java.util.Map;

public class Cast {

    private Integer castId;
    private String character;
    private String creditId;
    private Integer id;
    private String name;
    private Integer order;
    private Object profile_path;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The castId
     */
    public Integer getCastId() {
        return castId;
    }

    /**
     * 
     * @param castId
     *     The cast_id
     */
    public void setCastId(Integer castId) {
        this.castId = castId;
    }

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
     *     The order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 
     * @param order
     *     The order
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 
     * @return
     *     The profile_path
     */
    public Object getProfilePath() {
        return profile_path;
    }

    /**
     * 
     * @param profile_path
     *     The profile_path
     */
    public void setProfilePath(Object profile_path) {
        this.profile_path = profile_path;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
