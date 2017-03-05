
package moviez.mnf.com.movie.DataSet.CastDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CrewDetailsData {

    private Boolean adult;
    private List<Object> alsoKnownAs = new ArrayList<Object>();
    private String biography;
    private String birthday;
    private String deathday;
    private String homepage;
    private Integer id;
    private String imdbId;
    private String name;
    private String place_of_birth;
    private Double popularity;
    private String profile_path;
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
     *     The alsoKnownAs
     */
    public List<Object> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    /**
     * 
     * @param alsoKnownAs
     *     The also_known_as
     */
    public void setAlsoKnownAs(List<Object> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    /**
     * 
     * @return
     *     The biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * 
     * @param biography
     *     The biography
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * 
     * @return
     *     The birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 
     * @param birthday
     *     The birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 
     * @return
     *     The deathday
     */
    public String getDeathday() {
        return deathday;
    }

    /**
     * 
     * @param deathday
     *     The deathday
     */
    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    /**
     * 
     * @return
     *     The homepage
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * 
     * @param homepage
     *     The homepage
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
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
     *     The imdbId
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * 
     * @param imdbId
     *     The imdb_id
     */
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
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
     *     The place_of_birth
     */
    public String getPlaceOfBirth() {
        return place_of_birth;
    }

    /**
     * 
     * @param place_of_birth
     *     The place_of_birth
     */
    public void setPlaceOfBirth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
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
     *     The profile_path
     */
    public String getProfilePath() {
        return profile_path;
    }

    /**
     * 
     * @param profile_path
     *     The profile_path
     */
    public void setProfilePath(String profile_path) {
        this.profile_path = profile_path;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
