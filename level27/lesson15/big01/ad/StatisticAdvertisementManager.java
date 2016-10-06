package com.javarush.test.level27.lesson15.big01.ad;


import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage adStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance()
    {
        return ourInstance;
    }

    private StatisticAdvertisementManager()
    {
    }

    public List<Advertisement> getActiveVideos() {
        List<Advertisement> list = adStorage.list();
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement ad: list) {
            if (ad.getHits() > 0) {
                result.add(ad);
            }
        }
        return result;
    }

    public List<Advertisement> getArchivedVideos() {
        List<Advertisement> list = adStorage.list();
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement ad: list) {
            if (ad.getHits() == 0) {
                result.add(ad);
            }
        }
        return result;
    }
}
