
package moviez.mnf.com.movie.DataSet.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataMain {

    private Dates dates;
    private Integer page;
    private List<Result> results = new ArrayList<Result>();
    private Integer totalPages;
    private Integer total_results;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The dates
     */
    public Dates getDates() {
        return dates;
    }

    /**
     * 
     * @param dates
     *     The dates
     */
    public void setDates(Dates dates) {
        this.dates = dates;
    }

    /**
     * 
     * @return
     *     The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * 
     * @param totalPages
     *     The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 
     * @return
     *     The total_results
     */
    public Integer getTotalResults() {
        return total_results;
    }

    /**
     * 
     * @param total_results
     *     The total_results
     */
    public void setTotalResults(Integer total_results) {
        this.total_results = total_results;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
