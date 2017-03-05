
package moviez.mnf.com.movie.DataSet.CastCrew;

import java.util.HashMap;
import java.util.Map;

public class Crew {

    private String creditId;
    private String department;
    private Integer id;
    private String job;
    private String name;
    private Object profilePath;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 
     * @param department
     *     The department
     */
    public void setDepartment(String department) {
        this.department = department;
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
     *     The job
     */
    public String getJob() {
        return job;
    }

    /**
     * 
     * @param job
     *     The job
     */
    public void setJob(String job) {
        this.job = job;
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
