package com.cinema.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.model.CinemaService;
import com.cinema.model.CinemaVO;

/**
 * Servlet implementation class CinemaServiceTest
 */
@WebServlet("/CinemaServiceTest")
public class CinemaServiceTest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CinemaService cSvc;
    PrintWriter out;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CinemaServiceTest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        out = response.getWriter();
        out.append("Served at: ").append(request.getContextPath()).append("\n");
        cSvc = new CinemaService();
        String cinema_no = testAddCinema();
        testGetOneCinema(cinema_no);
        cinema_no = testAddCinema();
        testGetOneCinema(cinema_no);
        testUpdateCinema(cinema_no);
        testGetOneCinema(cinema_no);
        cinema_no = testAddCinema();
        testGetAll();
        testDeleteTheater(cinema_no);
        testGetAll();
        String cinema_name = "台北京站威秀影城";
        testGetOneCinemaByCinemaName(cinema_name);
    }

    public String testAddCinema() {
        String cinema_name = "台北京站威秀影城";
        String cinema_engname = "Vie Show Cinemas Taipei Qsquare";
        String cinema_address = "台北市大同區市民大道一段209號5樓";
        String cinema_tel = "(02) 2552-5511";
        String introduction = "台北信義威秀影城內有時髦前衛、聲光炫麗的裝潢，為全國第一座專門為放映電影而量身打造的建築設計，提供了由現成建築物改裝的傳統電影院無法擁有的舒適視聽空間。\r\n" + 
                "\r\n" + 
                "世界上最棒的影廳之一的4DX影廳，威秀影城2013年獨家引進，打造『台北信義威秀影城』全台首座4DX影廳，帶給您世界級的最新影音感官全體驗效果。\r\n" + 
                "為滿足觀眾視聽至高無上的享受，台北信義威秀影城應用最新科技，為未來影城之設計規劃建立新規範，也為台灣的電影院史上樹立新的里程碑。出自名室內設計師之手的17間影廳院，皆具備超大型廣角弧形銀幕及球場座位排列的座椅，提供威秀影城之觀眾寬敞舒適、無障礙的空間，為全台唯一經過THX世界標準聲場環境認證的標準影廳，提供北台灣民眾世界級的影音享受。\r\n" + 
                "\r\n" + 
                "\r\n" + 
                "※本影城為方便消費者，亦設有無障礙空間。\r\n" + 
                "●輪椅席(共46個席次)：第1、3-14廳-各3席，第2、15-18廳-各2席\r\n" + 
                "●無障礙電梯：一樓服務台旁\r\n" + 
                "●無障礙坡道：二樓第12廳前\r\n" + 
                "●無障礙停車位：地下停車場二樓\r\n" + 
                "●無障礙廁所\r\n" + 
                "\r\n" + 
                "\r\n" + 
                "本據點共17座影廳3,176席座位、46席無障礙座位，內含：\r\n" + 
                "1座4DX影廳 140個座位";
        String traffic = "－捷運：市政府站3號出口、捷運象山站 1號出口、台北101/世貿站4號出口 \r\n" + 
                "－公車︰可搭乘 284, 202, 266, 277, 611, 612, 621, 20 (松壽路口下)\r\n" + 
                "－商場停車優惠：週一至週四憑台北信義威秀影城當日電影票根即可享當日汽車停車優惠一小時(每小時60元)。（注意事項：國定假日不適用｜每車最多折扺一小時｜於出口票亭時提供當日有效票根一張做為折扣憑證並回收｜免費電影票恕無法折抵停車費｜威秀影城商場保有最終解釋權。）" ;
        String photo_title = "台北京站威秀影城-圖片";
        String photo_path = "/T3MS/img/xxxx.jpg";
        String photo_small = "/T3MS/img/xxxx-s.jpg";
        Integer active = 1;
        Integer state = 1;

        String cinema_no = cSvc.addCinema(cinema_name, cinema_engname, cinema_address, cinema_tel, introduction, traffic, photo_title, photo_path, photo_small, active, state);

        out.println("Add cinema_no : " + cinema_no);
        return cinema_no;
    }

    public void testGetOneCinema(String cinema_no) {
        CinemaVO cinemaVO = cSvc.getOneCinema(cinema_no);
        out.println("Cinema_no : " + cinemaVO.getCinema_no());
        out.println("Cinema_name : " + cinemaVO.getCinema_name());
        out.println("Cinema_engname : " + cinemaVO.getCinema_engname());
        out.println("Cinema_address : " + cinemaVO.getCinema_address());
        out.println("Cinema_tel : " + cinemaVO.getCinema_tel());
        out.println("Introduction : " + cinemaVO.getIntroduction());
        out.println("Traffic : " + cinemaVO.getTraffic());
        out.println("Photo_title : " + cinemaVO.getPhoto_title());
        out.println("Photo_path : " + cinemaVO.getPhoto_path());
        out.println("Photo_small : " + cinemaVO.getPhoto_small());
        out.println("Active : " + cinemaVO.getActive());
        out.println("State : " + cinemaVO.getState());
    }

    public void testGetOneCinemaByCinemaName(String cinema_name) {
        CinemaVO cinemaVO = cSvc.getOneCinemaByCinemaName(cinema_name);
        out.println("testGetOneCinemaByCinemaName : ");
        out.println("Cinema_no : " + cinemaVO.getCinema_no());
        out.println("Cinema_name : " + cinemaVO.getCinema_name());
        out.println("Cinema_engname : " + cinemaVO.getCinema_engname());
        out.println("Cinema_address : " + cinemaVO.getCinema_address());
        out.println("Cinema_tel : " + cinemaVO.getCinema_tel());
        out.println("Introduction : " + cinemaVO.getIntroduction());
        out.println("Traffic : " + cinemaVO.getTraffic());
        out.println("Photo_title : " + cinemaVO.getPhoto_title());
        out.println("Photo_path : " + cinemaVO.getPhoto_path());
        out.println("Photo_small : " + cinemaVO.getPhoto_small());
        out.println("Active : " + cinemaVO.getActive());
        out.println("State : " + cinemaVO.getState());
    }

    public void testUpdateCinema(String cinema_no) {
        String cinema_name = "板橋大遠百威秀影城";
        String cinema_engname = "Vie Show Cinemas Banciao Mega City";
        String cinema_address = "新北市板橋區新站路28號10樓";
        String cinema_tel = "(02) 7738-6608";
        String introduction = "板橋大遠百威秀影城是威秀影城於新北市的第一個據點。座落於新板特區板橋大遠百複合式商場內的10樓，為全數位化影城，全面採用最先進的Christie數位二代機放映系統，並且皆有數位IMAX影廳配置，影城音響系統採用全球最先進QSC四迴路音響系統，是台灣影院中極其頂尖的音響設備。\r\n" + 
                "\r\n" + 
                "\r\n" + 
                "※本影城為方便消費者，亦設有無障礙空間。\r\n" + 
                "●輪椅席(共29個席次) 第1-5廳和8-9廳-各3席，第6廳-5席，第7廳-4席，第10廳-2席\r\n" + 
                "●無障礙電梯：大遠百7號電梯，搭至十二樓\r\n" + 
                "●無障礙廁所：第3廳旁\r\n" + 
                "●無障礙停車場：地下停車場 地下二樓和地下三樓\r\n" + 
                "\r\n" + 
                "\r\n" + 
                "本據點共9座影廳2,059席座位、29席無障礙座位，內含：\r\n" + 
                "1座 IMAX影廳 404個座位（5個無障礙座位）";
        String traffic = "－捷運/高鐵/台鐵：板橋站3 A 出口，往台鐵1F東2門方向右轉步行約3~5分鐘，或由台鐵B1F東門方向經地下連通道即可到達\r\n" + 
                "－公車︰可搭乘至「板橋火車站」站下車，往台鐵東門方向右轉步行約3~5分鐘即可到達 \r\n" + 
                "－客運：可搭乘至「板橋客運站」下車，出口左轉步行約 3~5 分鐘即可到達" ;
        String photo_title = "板橋大遠百威秀影城-圖片";
        String photo_path = "/T3MS/img/yyyy.jpg";
        String photo_small = "/T3MS/img/yyyy-s.jpg";
        Integer active = 0;
        Integer state = 0;

        cSvc.updateCinema(cinema_no, cinema_name, cinema_engname, cinema_address, cinema_tel, introduction, traffic, photo_title, photo_path, photo_small, active, state);
        out.println("testUpdateCinema cinema_no : " + cinema_no);
    }

    public void testGetAll() {
        List<CinemaVO> list = cSvc.getAll();
        out.println(list.size());
        for(CinemaVO vo : list) {
            out.println(vo.getCinema_no());
        }
    }

    public void testDeleteTheater(String cinema_no) {
        cSvc.deleteCinema(cinema_no);
        out.println("delete cinema_no : " + cinema_no);
    }
}
