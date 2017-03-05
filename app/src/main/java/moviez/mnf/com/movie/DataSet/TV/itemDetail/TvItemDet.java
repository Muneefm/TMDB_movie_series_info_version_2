
package moviez.mnf.com.movie.DataSet.TV.itemDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TvItemDet {

    private String poster_path;
    private List<CreatedBy> createdBy = new ArrayList<CreatedBy>();
    private List<Integer> episodeRunTime = new ArrayList<Integer>();
    private String first_air_date;
    private List<Genre> genres = new ArrayList<Genre>();
    private String homepage;
    private Integer id;
    private Boolean inProduction;
    private List<String> languages = new ArrayList<String>();
    private String last_air_date;
    private String name;
    private List<Network> networks = new ArrayList<Network>();
    private Integer number_of_episodes;
    private Integer number_of_seasons;
    private List<String> originCountry = new ArrayList<String>();
    private String original_language;
    private String originalName;
    private String overview;
    private Double popularity;
    private String backdrop_path;
    private List<Object> productionCompanies = new ArrayList<Object>();
    private List<Season> seasons = new ArrayList<Season>();
    private String status;
    private String type;
    private Double vote_average;
    private Integer voteCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The poster_path
     */
    public String getBackdropPath() {
        return poster_path;
    }

    /**
     * 
     * @param poster_path
     *     The backdrop_path
     */
    public void setBackdropPath(String poster_path) {
        this.poster_path = poster_path;
    }

    /**
     * 
     * @return
     *     The createdBy
     */
    public List<CreatedBy> getCreatedBy() {
        return createdBy;
    }

    /**
     * 
     * @param createdBy
     *     The created_by
     */
    public void setCreatedBy(List<CreatedBy> createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 
     * @return
     *     The episodeRunTime
     */
    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    /**
     * 
     * @param episodeRunTime
     *     The episode_run_time
     */
    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    /**
     * 
     * @return
     *     The first_air_date
     */
    public String getFirstAirDate() {
        return first_air_date;
    }

    /**
     * 
     * @param first_air_date
     *     The first_air_date
     */
    public void setFirstAirDate(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    /**
     * 
     * @return
     *     The genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * 
     * @param genres
     *     The genres
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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
     *     The inProduction
     */
    public Boolean getInProduction() {
        return inProduction;
    }

    /**
     * 
     * @param inProduction
     *     The in_production
     */
    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    /**
     * 
     * @return
     *     The languages
     */
    public List<String> getLanguages() {
        return languages;
    }

    /**
     * 
     * @param languages
     *     The languages
     */
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    /**
     * 
     * @return
     *     The last_air_date
     */
    public String getLastAirDate() {
        return last_air_date;
    }

    /**
     * 
     * @param last_air_date
     *     The last_air_date
     */
    public void setLastAirDate(String last_air_date) {
        this.last_air_date = last_air_date;
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
     *     The networks
     */
    public List<Network> getNetworks() {
        return networks;
    }

    /**
     * 
     * @param networks
     *     The networks
     */
    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    /**
     * 
     * @return
     *     The number_of_episodes
     */
    public Integer getNumberOfEpisodes() {
        return number_of_episodes;
    }

    /**
     * 
     * @param number_of_episodes
     *     The number_of_episodes
     */
    public void setNumberOfEpisodes(Integer number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    /**
     * 
     * @return
     *     The number_of_seasons
     */
    public Integer getNumberOfSeasons() {
        return number_of_seasons;
    }

    /**
     * 
     * @param number_of_seasons
     *     The number_of_seasons
     */
    public void setNumberOfSeasons(Integer number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
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
     *     The original_language
     */
    public String getOriginalLanguage() {
        return original_language;
    }

    /**
     * 
     * @param original_language
     *     The original_language
     */
    public void setOriginalLanguage(String original_language) {
        this.original_language = original_language;
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
     *     The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * 
     * @param overview
     *     The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
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
     *     The backdrop_path
     */
    public String getPosterPath() {
        return backdrop_path;
    }

    /**
     * 
     * @param backdrop_path
     *     The poster_path
     */
    public void setPosterPath(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    /**
     * 
     * @return
     *     The productionCompanies
     */
    public List<Object> getProductionCompanies() {
        return productionCompanies;
    }

    /**
     * 
     * @param productionCompanies
     *     The production_companies
     */
    public void setProductionCompanies(List<Object> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    /**
     * 
     * @return
     *     The seasons
     */
    public List<Season> getSeasons() {
        return seasons;
    }

    /**
     * 
     * @param seasons
     *     The seasons
     */
    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The vote_average
     */
    public Double getVoteAverage() {
        return vote_average;
    }

    /**
     * 
     * @param vote_average
     *     The vote_average
     */
    public void setVoteAverage(Double vote_average) {
        this.vote_average = vote_average;
    }

    /**
     * 
     * @return
     *     The voteCount
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     * 
     * @param voteCount
     *     The vote_count
     */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
