package com.crawler.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EzdingCrawler implements Runnable {
//    private String dir = "WebContent/resources/crawler/movieinfo";
    private String dir = "/resources/crawler/movieinfo"; //for Java EE Environment
    //private String output = "TheatersInfo.txt";
    private String output = "TheatersInfo";
    private String outputMovie = "Movie";
    private int page;
    private boolean coming;
    private String targetUrl = "https://www.ezding.com.tw/movieInfoIndex";
    private String servletContextRealPath;
    private Set<String> cinemaSet;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public EzdingCrawler(int page, boolean coming) {
        super();
        this.page = page;
        this.coming = coming;
    }

    @Override
    public void run() {
        WebDriver driver = new FirefoxDriver();
        List<HashMap> movieInfoList = new ArrayList<HashMap>();
        List<HashMap> movieSessionList = new ArrayList<HashMap>();
        cinemaSet = new LinkedHashSet<String>();
        driver.get(targetUrl);
//        String pageSource = driver.getPageSource();
//        System.out.println(pageSource);

        WebDriverWait wait = new WebDriverWait(driver, 5);
//      WebDriverWait wait = new WebDriverWait(driver, 10);
        waitLoadingElement(wait, "div[class='post']");

        clickComingMovie(driver, wait);

        WebElement ele = driver.findElement(By.tagName("body"));
        String html = ele.getAttribute("outerHTML");
        //System.out.println(html);

        Document doc = Jsoup.parse(html);

        clickPageNumber(driver, wait);

        List<WebElement> postEle = driver.findElements(By.cssSelector("div[class='post']"));
        System.out.println("postEle.size(): " + postEle.size());
        int postSize = postEle.size();

        for(int k = 0; k < postSize; k++) {
            System.out.println("k: " + k);
            List<WebElement> postDiv = driver.findElements(By.cssSelector("div[class='post']"));
            System.out.println("postDiv.size(): " + postDiv.size());

            String bgImg = postDiv.get(k).getAttribute("style");
            System.out.println("bgImg: " + bgImg); //bgImg: background-image: url("../../static/common/poster.png");
            //skip poster.png(no data inside)
            if(bgImg.matches(".*poster.png.*")) {
                System.out.printf("poster.png - no data inside, skip index %d %n", k);
                continue;
            }

            postDiv.get(k).click();

            waitLoadingElement(wait, "div[class='sessionBox']");
            /*
            if(!waitLoadingElement(wait, "div[class='sessionBox']")) {
                System.out.println("TimeoutException after clicking movie post");
                
                //back to https://www.ezding.com.tw/movieInfoIndex
                driver.get(targetUrl);
                //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='post']")));
                waitLoadingElement(wait, "div[class='post']");
                clickPageNumber(driver, wait);
                continue;
            }
            */

            WebElement eleTime = driver.findElement(By.tagName("body"));
            String htmlTime = eleTime.getAttribute("outerHTML");
            //System.out.println(htmlTime);

            Document docTime = Jsoup.parse(htmlTime);

            HashMap<String, String> movieInfo = new HashMap<String, String>();

            getPosterMovieInfo(docTime, movieInfo);

            mkdirDlImg(movieInfo.get("movieTitle"), movieInfo.get("imgFileName"), movieInfo.get("posterImgUrl"));

            //video
            //ezding : <div class="videobox"><div class="video"><iframe width="1980" height="400" src="https://www.youtube.com/embed/hAXu4z0uhKM" frameborder="0" allowfullscreen=""></iframe></div></div>
            //youtube Embed Video : <iframe width="560" height="315" src="https://www.youtube.com/embed/hAXu4z0uhKM" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
            Element iframe = docTime.selectFirst(".videobox iframe");
            String videoUrl = iframe.attr("src");
            movieInfo.put("videoUrl", videoUrl);
            System.out.printf("videoUrl: %s%n", videoUrl);

            WebElement introDiv = driver.findElement(By.cssSelector("div.movie-intro-title-wrapper > div:nth-child(2)"));
            introDiv.click();  //show movie introduction

            waitLoadingElement(wait, ".staffbox");
            List<WebElement> staffbox = driver.findElements(By.cssSelector(".staffbox"));
            System.out.println("staffbox.size : " + staffbox.size());
            WebElement director = staffbox.get(0).findElement(By.cssSelector(".staff"));
            System.out.println("director : " + director.getText());
            movieInfo.put("director", director.getText());
            List<WebElement> staffContent = driver.findElements(By.cssSelector("div.staffContent > span"));
            String staff = "";
            for(WebElement we : staffContent) {
                staff += we.getText();
                System.out.println("staff : " + we.getText());
            }
            System.out.println("staff all : " + staff);
            movieInfo.put("staff", staff);

            WebElement intro = driver.findElement(By.cssSelector("div.movie-intro-content"));
            System.out.println("intro : " + intro.getText());
            movieInfo.put("intro", intro.getText());

            movieInfoList.add(movieInfo);

            WebElement movieTime = driver.findElement(By.cssSelector("div.movie-intro-title-wrapper > div:nth-child(1)"));
            movieTime.click();  //show movie time

            waitLoadingElement(wait, ".location");

            //must click .location then display <div class="locationList">
            WebElement loc = driver.findElement(By.cssSelector(".location"));
            loc.click(); //display <div class="locationList">

            List<WebElement> locationList= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".locationList > .list")));
            System.out.println("locationList size:" + locationList.size());
            for(WebElement locDiv : locationList) {
                System.out.println(locDiv.getText());
            }

            threadSleep(300);

            int locationListSize = locationList.size();
            loc.click(); //click again, close <div class="locationList">

            for(int j = 0; j < locationListSize; j++) {

                //must click .location then display <div class="locationList">
                WebElement newloc = driver.findElement(By.cssSelector(".location"));
                newloc.click(); //display <div class="locationList">
                
                List<WebElement> newLocationList= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".locationList > .list")));
                
                String locationArea = newLocationList.get(j).getText();
                newLocationList.get(j).click();

                if(!waitLoadingElement(wait, "div[class='sessionBox']")) {
                    //System.out.printf("after clicking locationList: %s %s %n", movieInfo.get("movieTitle"), newLocationList.get(j).getText());
//                    Exception in thread "main" org.openqa.selenium.StaleElementReferenceException: The element reference of <div class="list"> is stale; 
//                    either the element is no longer attached to the DOM, it is not in the current frame context, or the document has been refreshed
                    System.out.printf("after clicking locationList: %s %s %n", movieInfo.get("movieTitle"), locationArea);
                    continue;
                }

                List<WebElement> dateboxList = driver.findElements(By.cssSelector(".datebox"));

                for(int i = 0; i < dateboxList.size(); i++) {
                    dateboxList.get(i).click();
                    getSingleDaySessions(driver, movieSessionList, movieInfo);
                }

            }

            //driver.navigate().back(); // click() does not work after driver.navigate().back()
            driver.get(targetUrl);
            waitLoadingElement(wait, "div[class='post']");
            clickComingMovie(driver, wait);
            clickPageNumber(driver, wait);
        }

        StringBuilder sb = getDataSb(movieSessionList);
        outputFile(output, sb.toString());
        //printAll(movieSessionList);

        StringBuilder sbMovie = getDataSb(movieInfoList);
        outputFile(outputMovie, sbMovie.toString());

        handleMovie(movieInfoList);
        handleMovieSessionList(movieSessionList);
        handleTheater();

        threadSleep(3000);
        driver.close();
    }

    // Java SE application can not run JNDI of model
    // javax.naming.NoInitialContextException: Need to specify class name in environment or system property, or as an applet parameter, or in an application resource file:  java.naming.factory.initial
    public void handleMovie(List<HashMap> movieInfoList) {
        System.out.println("handleMovie");
        MovieHandler mhdr = new MovieHandler(movieInfoList);
        mhdr.setServletContextRealPath(servletContextRealPath);
        List<String> list = mhdr.importDB(); //return list of movie_no
        mhdr.exportSer(list);
    }

    public void handleMovieSessionList(List<HashMap> movieSessionList) {
        handleCinema(movieSessionList);
        //handleTheater(movieSessionList);
    }

    public void handleCinema(List<HashMap> movieSessionList) {
        System.out.println("handleCinema");
        CinemaHandler chdr = new CinemaHandler(movieSessionList);
        chdr.setServletContextRealPath(servletContextRealPath);
        List<String> list = chdr.importDB(); //return list of cinema_no
        chdr.exportSer(list);
    }

    // public void handleTheater(List<HashMap> movieSessionList) {
    public void handleTheater() {
        System.out.println("handleTheater");
        TheaterHandler thdr = new TheaterHandler(cinemaSet);
        thdr.setServletContextRealPath(servletContextRealPath);
        List<String> list = thdr.importDB(); //return list of theater_no
        thdr.exportSer(list);
    }

    public void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clickPageNumber(WebDriver driver, WebDriverWait wait) {
        List<WebElement> numIndex = driver.findElements(By.cssSelector("div[class*='circle numIndex']"));
        numIndex.get(page).click();
        waitLoadingElement(wait, "div[class='post']");
        threadSleep(300);
        System.out.println("page: " + page);
    }

    public void clickComingMovie(WebDriver driver, WebDriverWait wait) {
        if(coming) {
            WebElement comingDiv = driver.findElement(By.cssSelector("div.btn2"));
            comingDiv.click();
            waitLoadingElement(wait, "div[class='post']");
        }
    }

    //maybe not have WebElement, handle org.openqa.selenium.TimeoutException
    public static boolean waitLoadingElement(WebDriverWait wait, String selector) {
        //waiting for loading <div class="sessionBox">
        try {
            List<WebElement> WebElements= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(selector)));
        //System.out.println(sessionBoxes.size());
        } catch(TimeoutException e) {
            System.out.printf("org.openqa.selenium.TimeoutException %s %n", selector);
            return false;
        } catch(StaleElementReferenceException se) {
            System.out.printf("org.openqa.selenium.StaleElementReferenceException %s %n", selector);
            return false;
        }
        return true;
    }

    public void getPosterMovieInfo(Document docTime, HashMap<String, String> movieInfo) {
        //about movie info
        Element poster = docTime.selectFirst(".poster");
        String posterStyle = poster.attr("style");
        String posterImgUrl = posterStyle.substring(posterStyle.indexOf("http"), posterStyle.indexOf("\");"));
        movieInfo.put("posterImgUrl", posterImgUrl);
        System.out.printf("posterImgUrl: %s%n", posterImgUrl);
        String imgFileName = posterImgUrl.substring(posterImgUrl.lastIndexOf("/") + 1);
        movieInfo.put("imgFileName", imgFileName);
        System.out.printf("imgFileName: %s%n", imgFileName);
        String grade = poster.selectFirst(".grade").text();
        movieInfo.put("grade", grade);
        System.out.printf("grade: %s%n", grade);
        String movieTitle = poster.selectFirst(".movie-title").text();
        movieInfo.put("movieTitle", movieTitle);
        System.out.printf("movieTitle: %s%n", movieTitle);
        String movieTitleEn = poster.selectFirst("[class='movie-title eu']").text();
        movieInfo.put("movieTitleEn", movieTitleEn);
        System.out.printf("movieTitleEn: %s%n", movieTitleEn);

        System.out.println(poster.selectFirst(".time span").text());
        String releaseDateSpan = poster.selectFirst(".time span").text();
        String releaseDate = releaseDateSpan.substring(releaseDateSpan.indexOf("：") + 1);
        movieInfo.put("releaseDate", releaseDate);
        System.out.printf("releaseDate: %s%n", releaseDate);

        String length = poster.selectFirst(".length").text();
        System.out.printf("length: %s%n", length);
        String lengthMin = length.substring(length.indexOf("：") + 1, length.length() - 2);
        movieInfo.put("lengthMin", lengthMin);
        System.out.printf("lengthMin: %s%n", lengthMin);
        System.out.printf("lengthMin.length(): %d%n", lengthMin.length());

        String imdb = poster.selectFirst(".imdb .score").text();
        movieInfo.put("imdb", imdb);
        System.out.printf("imdb: %s%n", imdb);
        String tomato = poster.selectFirst(".tomato .score").text();
        movieInfo.put("tomato", tomato);
        System.out.printf("tomato: %s%n", tomato);
    }

    public void getSingleDaySessions(WebDriver driver, List<HashMap> movieSessionList, HashMap<String, String> movieInfo) {
        //refresh WebElement
        WebElement newEleTime = driver.findElement(By.tagName("body"));
        String newHtmlTime = newEleTime.getAttribute("outerHTML");
        //System.out.println(htmlTime);
        
        Document newDocTime = Jsoup.parse(newHtmlTime);

        //datebox checked
        Elements dateboxChecked = newDocTime.select("div[class='datebox checked']");
        System.out.println("dateboxChecked : " + dateboxChecked.size());
        System.out.println("dateboxChecked : " + dateboxChecked.get(0).text());
        System.out.println("dateboxChecked : " + dateboxChecked.get(0).html());
        String monthStr = newDocTime.selectFirst("div[class='datebox checked']").select(".month").text();
        String month = monthStr.substring(0, monthStr.length() - 1);
        String date = newDocTime.selectFirst("div[class='datebox checked']").select(".date").text();
        String sessionDate = String.format("%02d", Integer.parseInt(month)) + "/" + String.format("%02d", Integer.parseInt(date));
        System.out.println("sessionDate: " + sessionDate);

        getCinemabox(newDocTime, movieInfo, sessionDate, movieSessionList);
    }

    public StringBuilder getDataSb(List<HashMap> sessions) {
        StringBuilder sb = new StringBuilder();
        for(HashMap<String, String> r : sessions) {
            for(Map.Entry<String, String> e : r.entrySet()) {
                String output = String.format("%s\t", e.getValue());
                sb.append(output);
            }
            sb.append("\n");
        }
        return sb;
    }

    public void outputFile(String output, String str) {
        //File out = new File(output);
        //File out = new File(output + page + ".txt");

        //create dir
        //File targetDir = new File(dir);
        File targetDir = new File(servletContextRealPath + dir);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        //target file
        String comingStr = "";
        if(coming) {
            comingStr = "_coming_";
        }
        File out = new File(targetDir, output + comingStr + page + ".txt");

        FileWriter fw;
        try {
            fw = new FileWriter(out);
            fw.write(str);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printAll(List<HashMap> sessions) {
        //StringBuilder sb = new StringBuilder();
        for(HashMap<String, String> r : sessions) {
            for(Map.Entry<String, String> e : r.entrySet()) {
//                System.out.printf("%s %s\t", e.getKey(), e.getValue());
                System.out.printf("%s\t", e.getValue());
                String output = String.format("%s\t", e.getValue());
                //sb.append(output);
            }
            //sb.append("\n");
            System.out.println();
        }
    }    

    public void getCinemabox(Document docTime, HashMap<String, String> movieInfo, String sessionDate, List<HashMap> movieSessionList) {
        Elements cinemaboxes = docTime.select(".cinemabox");
        System.out.println("cinemaboxes.size() : " + cinemaboxes.size());
        for(Element cinemabox : cinemaboxes) {
            String cinemaName = cinemabox.selectFirst(".cinemaName").text();
            System.out.println(cinemaName);
            cinemaSet.add(cinemaName);
            String version = cinemabox.selectFirst(".version").text();
            System.out.println(version);
            Elements times = cinemabox.select(".session .time");
            System.out.println("times.size() : " + times.size());
            for(Element time : times) {
                System.out.printf("%s\t", time.text());
                //clone
                HashMap<String, String> mapRow = cloneHashMap(movieInfo);
                mapRow.put("cinemaName", cinemaName);
                mapRow.put("version", version);
                mapRow.put("sessionDate", sessionDate);
                mapRow.put("time", time.text());
                movieSessionList.add(mapRow);
            }
            System.out.println();
        }
    }

    public HashMap<String, String> cloneHashMap(HashMap<String, String> origin) {
        HashMap<String, String> clone = new HashMap<>();
        for(Map.Entry<String, String> entry : origin.entrySet()) {
            if("intro".equals(entry.getKey())) {
                continue;
            }
            clone.put(entry.getKey(), entry.getValue());
        }
        return clone;
    }

    public void mkdirDlImg(String movieTitle, String imgFileName, String posterImgUrl) {
        //create dir of movieinfo
        //File sDir = new File(dir);
        File sDir = new File(servletContextRealPath, dir);
        if (!sDir.exists()) {
            sDir.mkdirs();
        }
        System.out.println("sDir: " + sDir);
        //create dir of movieTitle
        //File movieDir = new File(dir, movieTitle);
        File movieDir = new File(sDir, movieTitle);
        if (!movieDir.exists()) {
            movieDir.mkdirs();
        }
        //get image
        File file = new File(movieDir, imgFileName);
        try {
            URL myURL = new URL(posterImgUrl);
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            InputStream is = conn.getInputStream();
            FileOutputStream fos = new FileOutputStream(file);

            System.out.println("image: " + imgFileName + " kick-off!");
            // 自訂緩衝區
            int len = 0;
            byte[] b = new byte[4096];
            while ((len = is.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.flush();
            fos.close();
            is.close();
            System.out.println(imgFileName + " Done!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
