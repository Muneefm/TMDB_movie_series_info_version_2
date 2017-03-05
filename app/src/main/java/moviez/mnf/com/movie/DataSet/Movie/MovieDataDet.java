
package moviez.mnf.com.movie.DataSet.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MovieDataDet {

    private Boolean adult;
    private String backdrop_path;
    private BelongsToCollection belongsToCollection;
    private String budget;
    private List<Genre> genres = new ArrayList<Genre>();
    private String homepage;
    private Integer id;
    private String imdbId;
    private String original_language;
    private String originalTitle;
    private String overview;
    private Double popularity;
    private String poster_path;
    private List<ProductionCompany> productionCompanies = new ArrayList<ProductionCompany>();
    private List<ProductionCountry> productionCountries = new ArrayList<ProductionCountry>();
    private String release_date;
    private String revenue;
    private Integer runtime;
    private List<SpokenLanguage> spokenLanguages = new ArrayList<SpokenLanguage>();
    private String status;
    private String tagline;
    private String title;
    private Boolean video;
    private Double vote_average;
    private Integer voteCount;
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
     *     The backdrop_path
     */
    public String getBackdropPath() {
        return backdrop_path;
    }

    /**
     * 
     * @param backdrop_path
     *     The backdrop_path
     */
    public void setBackdropPath(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    /**
     * 
     * @return
     *     The belongsToCollection
     */
    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    /**
     * 
     * @param belongsToCollection
     *     The belongs_to_collection
     */
    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    /**
     * 
     * @return
     *     The budget
     */
    public String getBudget() {
        return budget;
    }

    /**
     * 
     * @param budget
     *     The budget
     */
    public void setBudget(String budget) {
        this.budget = budget;
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
     *     The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * 
     * @param originalTitle
     *     The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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
     *     The productionCompanies
     */
    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    /**
     * 
     * @param productionCompanies
     *     The production_companies
     */
    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    /**
     * 
     * @return
     *     The productionCountries
     */
    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    /**
     * 
     * @param productionCountries
     *     The production_countries
     */
    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    /**
     * 
     * @return
     *     The release_date
     */
    public String getReleaseDate() {
        return release_date;
    }

    /**
     * 
     * @param release_date
     *     The release_date
     */
    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    /**
     * 
     * @return
     *     The revenue
     */
    public String getRevenue() {
        return revenue;
    }

    /**
     * 
     * @param revenue
     *     The revenue
     */
    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The spokenLanguages
     */
    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    /**
     * 
     * @param spokenLanguages
     *     The spoken_languages
     */
    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
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
     *     The tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * 
     * @param tagline
     *     The tagline
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The video
     */
    public Boolean getVideo() {
        return video;
    }

    /**
     * 
     * @param video
     *     The video
     */
    public void setVideo(Boolean video) {
        this.video = video;
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
