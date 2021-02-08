package org.example;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Set;
import java.util.regex.Pattern;

public class HtmlCrawler extends WebCrawler {

    private static final Logger logger = LogManager.getLogger(HtmlCrawler.class);


    private final static Pattern EXCLUSIONS
            = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {

        String urlString = url.getURL().toLowerCase();
        return !EXCLUSIONS.matcher(urlString).matches()
                && urlString.startsWith(CommonUtils.getProperty("baseUrl"));
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();

        PropertyConfigurator.configure("src/main/java/org/example/log4j.properties");


        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String title = htmlParseData.getTitle();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            //TODO: do something with the collected data
            logger.info(title);
            logger.info(links.size());
            logger.info(links.toString());
        }
    }
}
