package com.yupi.springbootinit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @ClassName：Test
 * @Author: fmy
 * @Date: 2024/12/6 17:51
 * @Description:
 */
@SpringBootTest
public class FetchPicture {
    @Test
    void testFetchPicture() throws IOException {
        Document doc = Jsoup.connect("https://www.bing.com/images/search?q=oj&FORM=HDRSC2").get();
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {

        }
    }
}
