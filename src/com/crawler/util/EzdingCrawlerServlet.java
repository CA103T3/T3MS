package com.crawler.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

/**
 * Servlet implementation class EzdingCrawlerServlet
 */
@WebServlet("/EzdingCrawlerServlet")
public class EzdingCrawlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EzdingCrawlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetUrl = "https://www.ezding.com.tw/movieInfoIndex";
        WebDriver driver = new FirefoxDriver();
        String realPath = getServletContext().getRealPath("/");
        
        driver.get(targetUrl);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        EzdingCrawler.waitLoadingElement(wait, "div[class='post']");
        /*
        WebElement ele = driver.findElement(By.tagName("body"));
        String html = ele.getAttribute("outerHTML");
        //System.out.println(html);

        Document doc = Jsoup.parse(html);

        //pages
        Elements numIndexes = doc.select("div[class*='circle numIndex']");
        int pages = numIndexes.size();
        */
        /*
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < pages; i++) {
            Thread t = new Thread(new EzdingCrawler(i, false));
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
        */
        /*
        //Thread t = new Thread(new EzdingCrawler(1));
//        Thread t = new Thread(new EzdingCrawler(0));
        EzdingCrawler crawler = new EzdingCrawler(4, false);
        crawler.setServletContextRealPath(realPath);
        Thread t = new Thread(crawler); //for showing movie
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
        /*
        for (int i = 0; i < pages; i++) {
            EzdingCrawler app = new EzdingCrawler(i, false);
            app.run();
        }
        */
        
        //for comming movie
        driver.get(targetUrl);
        EzdingCrawler.waitLoadingElement(wait, "div[class='post']");
        WebElement comingDiv = driver.findElement(By.cssSelector("div.btn2"));
        comingDiv.click(); //click coming movie
        EzdingCrawler.waitLoadingElement(wait, "div[class='post']");
        WebElement eleForComing = driver.findElement(By.tagName("body"));
        String htmlForComing = eleForComing.getAttribute("outerHTML");
        Document docForComing = Jsoup.parse(htmlForComing);
        //pages
        //Elements numIndexesForComing = docForComing.select("div[class*='circle numIndex']");
        List<WebElement> numIndexesForComing = eleForComing.findElements(By.cssSelector("div[class*='circle numIndex']"));
        int pagesForComing = numIndexesForComing.size();
        
        
        List<Thread> threadsForComing = new ArrayList<Thread>();
        for (int i = 0; i < pagesForComing; i++) {
            Thread tForComing = new Thread(new EzdingCrawler(i, true));
            threadsForComing.add(tForComing);
            tForComing.start();

            //can not use t.join() here, it will create next thread until this thread ends
//            try {
//                tForComing.join();
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }

        //join all thread
        for(Thread t : threadsForComing) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        /*
        Thread tForComing = new Thread(new EzdingCrawler(0, true)); //for coming movie
        tForComing.start();
        try {
            tForComing.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

        /*
        for (int i = 0; i < pagesForComing; i++) {
            EzdingCrawler app = new EzdingCrawler(i, true);
            app.run();
        }
        */

        System.out.println("main thread close");

        driver.close();
	}

}
