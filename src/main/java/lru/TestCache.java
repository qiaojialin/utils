package lru;

public class TestCache {

    public static void main(String[] args) {
        try {
            int testCount = 10;
            int cacheSize = 5;
            LRUCache<Integer, Integer> cache = new LRUCache<Integer, Integer>(cacheSize) {
                @Override
                public void beforeRemove(Integer object) {
                    return;
                }

                @Override
                public Integer loadObjectByKey(Integer key) {
                    return key * 10;
                }
            };

            for (int i = 1; i < testCount; i++) {
                System.out.println("cache.get(" + i + ")=" + cache.get(i));
                System.out.println("cache.get("+(i-1)+")=" + cache.get(i-1));
                System.out.println(cache.getCache());
                System.out.println();
            }
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }
}
