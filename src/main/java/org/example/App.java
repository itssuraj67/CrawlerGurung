package org.example;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.io.File;

public class App
{

    public static void main( String[] args ) throws Exception {
        File crawlStorage = new File("src/test/resources/crawler4j");
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());

        int numCrawlers = Integer.parseInt(CommonUtils.getProperty("numberOfCrawler"));

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer= new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed(CommonUtils.getProperty("baseUrl"));

        CrawlController.WebCrawlerFactory<HtmlCrawler> factory = HtmlCrawler::new;

        controller.start(factory, numCrawlers);

    }
}
