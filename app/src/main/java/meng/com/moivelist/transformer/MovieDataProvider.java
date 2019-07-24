package meng.com.moivelist.transformer;

import java.util.Map;

public class MovieDataProvider {

    private static class MovieDataProviderHolder {
        static MovieDataProvider instance = new MovieDataProvider();
    }
    private Map<Integer, String> genreMap;

    private MovieDataProvider(){}

    public static MovieDataProvider getInstance() {
        return MovieDataProviderHolder.instance;
    }

    public Map<Integer, String> getGenreMap() {
        return genreMap;
    }

    public void initGenreMap(Map<Integer, String> genreMap) {
        this.genreMap = genreMap;
    }

}
