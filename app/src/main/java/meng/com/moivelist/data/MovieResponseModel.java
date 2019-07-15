package meng.com.moivelist.data;

import java.util.List;

/**
 * Created by mengzhou on 7/14/19.
 */

public class MovieResponseModel {
    int page;
    int total_results;
    int total_pages;
    List<MovieServerModel> results;
}
