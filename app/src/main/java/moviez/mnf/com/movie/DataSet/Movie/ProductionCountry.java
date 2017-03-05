
package moviez.mnf.com.movie.DataSet.Movie;

import java.util.HashMap;
import java.util.Map;

public class ProductionCountry {

    private String iso31661;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The iso31661
     */
    public String getIso31661() {
        return iso31661;
    }

    /**
     * 
     * @param iso31661
     *     The iso_3166_1
     */
    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
