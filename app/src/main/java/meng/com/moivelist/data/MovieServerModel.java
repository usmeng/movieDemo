package meng.com.moivelist.data;

import java.util.List;

/**
 * Created by mengzhou on 7/14/19.
 */

public class MovieServerModel {
    public int vote_count;
    public long id;
    public boolean video;
    public double vote_average;
    public String title;
    public double popularity;
    public String poster_path;
    public String original_language;
    public String original_title;
    public List<Integer>  genre_ids;
    public String backdrop_path;
    public boolean adult;
    public String overview;
    public String release_date;
}
