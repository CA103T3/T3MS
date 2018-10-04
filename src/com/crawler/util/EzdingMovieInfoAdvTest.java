package com.crawler.util;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EzdingMovieInfoAdvTest {
    
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.ezding.com.tw/movieInfoIndex");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        EzdingMovieInfoAdv.waitLoadingElement(wait, "div[class='post']");
        
        WebElement ele = driver.findElement(By.tagName("body"));
        String html = ele.getAttribute("outerHTML");
        //System.out.println(html);
        
        Document doc = Jsoup.parse(html);
        
        //pages
        Elements numIndexes = doc.select("div[class*='circle numIndex']");
        int pages = numIndexes.size();
        List<Thread> threads = new ArrayList<Thread>();
        
        for (int i = 0; i < pages; i++) {
            Thread t = new Thread(new EzdingMovieInfoAdv(i));
            threads.add(t);
            t.start();
            
            //can not use t.join() here, it will create next thread until this thread ends
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
        
        //join all thread
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        /*
        //Thread t = new Thread(new EzdingMovieInfoAdv(1));
        Thread t = new Thread(new EzdingMovieInfoAdv(1));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
        System.out.println("main thread close");
        
        driver.close();
    }
    
}
