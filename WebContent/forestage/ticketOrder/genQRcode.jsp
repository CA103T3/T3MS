<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%!/*
	genQRCode 傳入的參數
	Img_Width 圖片寬度
	Img_Height 圖片高度
	Text_In QRCode 圖片要顯示的文字內容
	File_Path 圖片存檔路徑與檔名
	*/
	public String genQRCode(int Img_Width, int Img_Height, String Text_In, String File_Path) {
		try {
			byte[] TempByte = Text_In.getBytes("Utf-8");//將要製圖的內容轉成 byte 矩陣
			java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(Img_Width, Img_Height,
					java.awt.image.BufferedImage.TYPE_INT_RGB);// 設定圖檔寬度予高度
					
			//所下載元件之參數設定
			com.swetake.util.Qrcode TempQRCode = new com.swetake.util.Qrcode();
			TempQRCode.setQrcodeErrorCorrect('M');//容錯率L M Q H 
			TempQRCode.setQrcodeEncodeMode('B');//字元模式,N A 或其它的A是英文,N是數字,其它是8 byte
			TempQRCode.setQrcodeVersion(7);//可使用的字串長短跟所設定的QrcodeVersion有關,越大可設定的字越多, 0-40,0是自動
			
			//建立一個 java 的 2D 畫布，並設定畫布的背景色、大小與前景色
			java.awt.Graphics2D g2D = (java.awt.Graphics2D) bi.getGraphics();
			g2D.setBackground(java.awt.Color.WHITE);//背景是白色
			g2D.clearRect(0, 0, Img_Width, Img_Height);
			g2D.setColor(java.awt.Color.BLACK);//用黑色來畫 QRCode
			
			//在畫布中畫入 QRCode 圖
			if (TempByte.length > 0 && TempByte.length < 120) {
				boolean[][] TempBoolean = TempQRCode.calQrcode(TempByte);//QRCode 圖只有黑白兩色，呼叫元件建立 QRCode 圖點布林矩陣,false不塗色,True塗入黑色
				for (int XINDEX = 0; XINDEX < TempBoolean.length; XINDEX++) {
					for (int YINDEX = 0; YINDEX < TempBoolean.length; YINDEX++) {
						if (TempBoolean[YINDEX][XINDEX]) {//元件有成功建構圖點者
							g2D.fillRect(YINDEX * 3 + 2, XINDEX * 3 + 2, 3, 3);//就在畫布中塗一個方形點
						}
					}
				}
			} else {
				return "內容長度太長";
			}
			g2D.dispose();
			// bi.flush();
			//存檔
			java.io.File TempFile = new java.io.File(File_Path);
			javax.imageio.ImageIO.write(bi, "jpg", TempFile);
		} catch (Exception e) {
			return "" + e;//.printStackTrace();
		}
		return "";
	}%>
%>