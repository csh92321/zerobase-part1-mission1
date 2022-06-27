package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dto.HistoryDTO;
import dto.wifiDTO;

public class wifi {

	public static int deleteId;

	public static void apiDownload() {
		String result = "";

		try {

			for (int i = 1; i < 15000; i += 1000) {

				int z = i + 999;

				if (i > 14000) {
					z = 14493;
				}

				URL url = new URL(
						"http://openapi.seoul.go.kr:8088/55516a66516373683434445162756a/json/TbPublicWifiInfo/" + i
								+ "/" + z);

				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

				result = bf.readLine();

				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
				JSONObject tbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
				JSONArray row = (JSONArray) tbPublicWifiInfo.get("row");

				for (int j = 0; j <= z - i; j++) {
					JSONObject X_SWIFI_MGR_NO = (JSONObject) row.get(j);
					JSONObject X_SWIFI_WRDOFC = (JSONObject) row.get(j);
					JSONObject X_SWIFI_MAIN_NM = (JSONObject) row.get(j);
					JSONObject X_SWIFI_ADRES1 = (JSONObject) row.get(j);
					JSONObject X_SWIFI_ADRES2 = (JSONObject) row.get(j);
					JSONObject X_SWIFI_INSTL_FLOOR = (JSONObject) row.get(j);
					JSONObject X_SWIFI_INSTL_TY = (JSONObject) row.get(j);
					JSONObject X_SWIFI_INSTL_MBY = (JSONObject) row.get(j);
					JSONObject X_SWIFI_SVC_SE = (JSONObject) row.get(j);
					JSONObject X_SWIFI_CMCWR = (JSONObject) row.get(j);
					JSONObject X_SWIFI_CNSTC_YEAR = (JSONObject) row.get(j);
					JSONObject X_SWIFI_INOUT_DOOR = (JSONObject) row.get(j);
					JSONObject X_SWIFI_REMARS3 = (JSONObject) row.get(j);
					JSONObject LAT = (JSONObject) row.get(j);
					JSONObject LNT = (JSONObject) row.get(j);
					JSONObject WORK_DTTM = (JSONObject) row.get(j);

					wifiDTO dto = new wifiDTO();

					String year = (String) X_SWIFI_CNSTC_YEAR.get("X_SWIFI_CNSTC_YEAR");
					if (year.equals("")) {
						year = "0";
					}

					dto.setMgrNo((String) X_SWIFI_MGR_NO.get("X_SWIFI_MGR_NO"));
					dto.setWrdofc((String) X_SWIFI_WRDOFC.get("X_SWIFI_WRDOFC"));
					dto.setMainNm((String) X_SWIFI_MAIN_NM.get("X_SWIFI_MAIN_NM"));
					dto.setAdres1((String) X_SWIFI_ADRES1.get("X_SWIFI_ADRES1"));
					dto.setAdres2((String) X_SWIFI_ADRES2.get("X_SWIFI_ADRES2"));
					dto.setInstlFloor((String) X_SWIFI_INSTL_FLOOR.get("X_SWIFI_INSTL_FLOOR"));
					dto.setInstlTy((String) X_SWIFI_INSTL_TY.get("X_SWIFI_INSTL_TY"));
					dto.setInstlMby((String) X_SWIFI_INSTL_MBY.get("X_SWIFI_INSTL_MBY"));
					dto.setSvcSe((String) X_SWIFI_SVC_SE.get("X_SWIFI_SVC_SE"));
					dto.setCmcwr((String) X_SWIFI_CMCWR.get("X_SWIFI_CMCWR"));
					dto.setCnstcYear(Integer.parseInt(year));
					dto.setInoutDoor((String) X_SWIFI_INOUT_DOOR.get("X_SWIFI_INOUT_DOOR"));
					dto.setRemars3((String) X_SWIFI_REMARS3.get("X_SWIFI_REMARS3"));
					dto.setLat(Double.parseDouble((String) LAT.get("LAT")));
					dto.setLnt(Double.parseDouble((String) LNT.get("LNT")));
					dto.setWorkDttm((String) WORK_DTTM.get("WORK_DTTM"));

					wifiInsert(dto);
				}

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void wifiInsert(wifiDTO dto) {

		Connection con = null;
		PreparedStatement preStmt = null;

		String dbUrl = "jdbc:sqlite:G:\\제로베이스\\workspace\\zerobase-part1-mission1\\wifi_list.db";

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(dbUrl);

			String sql = "insert into wifi_list(X_SWIFI_MGR_NO,X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

			preStmt = con.prepareStatement(sql);
			preStmt.setString(1, dto.getMgrNo());
			preStmt.setString(2, dto.getWrdofc());
			preStmt.setString(3, dto.getMainNm());
			preStmt.setString(4, dto.getAdres1());
			preStmt.setString(5, dto.getAdres2());
			preStmt.setString(6, dto.getInstlFloor());
			preStmt.setString(7, dto.getInstlTy());
			preStmt.setString(8, dto.getInstlMby());
			preStmt.setString(9, dto.getSvcSe());
			preStmt.setString(10, dto.getCmcwr());
			preStmt.setInt(11, dto.getCnstcYear());
			preStmt.setString(12, dto.getInoutDoor());
			preStmt.setString(13, dto.getRemars3());
			preStmt.setDouble(14, dto.getLat());
			preStmt.setDouble(15, dto.getLnt());
			preStmt.setString(16, dto.getWorkDttm());

			int affected = preStmt.executeUpdate();

			if (affected > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static List<wifiDTO> wifiSelect(double lnt, double lat) {

		List<wifiDTO> wifiList = new ArrayList<wifiDTO>();

		String dbUrl = "jdbc:sqlite:G:\\제로베이스\\workspace\\zerobase-part1-mission1\\wifi_list.db";

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			System.out.println(e);
		}

		Connection con = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(dbUrl);
			System.out.println("SQLite DB connected");

			String sqlLat = "SELECT * FROM wifi_list  WHERE LAT<>" + lat + " ORDER BY ABS(LAT-" + lat
					+ ") ASC LIMIT 10;";
			String sqlLnt = "SELECT * FROM wifi_list  WHERE LNT<>" + lnt + " ORDER BY ABS(LNT-" + lnt
					+ ") ASC LIMIT 10;";
			
			System.out.println("lat : "+ lat);
			System.out.println("lnt : "+ lnt);

			preStmt = con.prepareStatement(sqlLat);

			rs = preStmt.executeQuery();

			while (rs.next()) {
				wifiDTO dto = new wifiDTO();

				double latLoad = Double.parseDouble(rs.getString("LAT"));
				double lntLoad = Double.parseDouble(rs.getString("LNT"));

				double distance = Math
						.sqrt(Math.pow(Math.abs(lat - latLoad), 2) + Math.pow(Math.abs(lnt - lntLoad), 2));
				distance = Math.round(distance * 10000);
				distance = distance / 10000;

				dto.setDistance(distance);
				dto.setMgrNo(rs.getString("X_SWIFI_MGR_NO"));
				dto.setWrdofc(rs.getString("X_SWIFI_WRDOFC"));
				dto.setMainNm(rs.getString("X_SWIFI_MAIN_NM"));
				dto.setAdres1(rs.getString("X_SWIFI_ADRES1"));
				dto.setAdres2(rs.getString("X_SWIFI_ADRES2"));
				dto.setInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
				dto.setInstlTy(rs.getString("X_SWIFI_INSTL_TY"));
				dto.setInstlMby(rs.getString("X_SWIFI_INSTL_MBY"));
				dto.setSvcSe(rs.getString("X_SWIFI_SVC_SE"));
				dto.setCmcwr(rs.getString("X_SWIFI_CMCWR"));
				dto.setCnstcYear(Integer.parseInt(rs.getString("X_SWIFI_CNSTC_YEAR")));
				dto.setInoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
				dto.setRemars3(rs.getString("X_SWIFI_REMARS3"));
				dto.setLat(latLoad);
				dto.setLnt(lntLoad);
				dto.setWorkDttm(rs.getString("WORK_DTTM"));

				wifiList.add(dto);
			}

			preStmt = con.prepareStatement(sqlLnt);

			rs = preStmt.executeQuery();

			while (rs.next()) {
				wifiDTO dto = new wifiDTO();

				double latLoad = Double.parseDouble(rs.getString("LAT"));
				double lntLoad = Double.parseDouble(rs.getString("LNT"));

				double distance = Math
						.sqrt(Math.pow(Math.abs(lat - latLoad), 2) + Math.pow(Math.abs(lnt - lntLoad), 2));
				distance = Math.round(distance * 10000);
				distance = distance / 10000;

				dto.setDistance(distance);
				dto.setMgrNo(rs.getString("X_SWIFI_MGR_NO"));
				dto.setWrdofc(rs.getString("X_SWIFI_WRDOFC"));
				dto.setMainNm(rs.getString("X_SWIFI_MAIN_NM"));
				dto.setAdres1(rs.getString("X_SWIFI_ADRES1"));
				dto.setAdres2(rs.getString("X_SWIFI_ADRES2"));
				dto.setInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
				dto.setInstlTy(rs.getString("X_SWIFI_INSTL_TY"));
				dto.setInstlMby(rs.getString("X_SWIFI_INSTL_MBY"));
				dto.setSvcSe(rs.getString("X_SWIFI_SVC_SE"));
				dto.setCmcwr(rs.getString("X_SWIFI_CMCWR"));
				dto.setCnstcYear(Integer.parseInt(rs.getString("X_SWIFI_CNSTC_YEAR")));
				dto.setInoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
				dto.setRemars3(rs.getString("X_SWIFI_REMARS3"));
				dto.setLat(latLoad);
				dto.setLnt(lntLoad);
				dto.setWorkDttm(rs.getString("WORK_DTTM"));

				wifiList.add(dto);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		historyInsert(lat, lnt);
		return wifiList;
	}

	public static void historyInsert(double lat, double lnt) {

		Connection con = null;
		PreparedStatement preStmt = null;

		String dbUrl = "jdbc:sqlite:G:\\제로베이스\\workspace\\zerobase-part1-mission1\\wifi_list.db";

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(dbUrl);

			String sql = "insert into history(LAT,LNT,CHECK_DTTM) values(?,?,?);";
			LocalDateTime LDN = LocalDateTime.now();

			preStmt = con.prepareStatement(sql);
			preStmt.setDouble(1, lat);
			preStmt.setDouble(2, lnt);
			preStmt.setString(3, LDN.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

			int affected = preStmt.executeUpdate();

			if (affected > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}


		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void historyDelete(int id) {

		System.out.println(id);

		Connection con = null;
		PreparedStatement preStmt = null;

		String dbUrl = "jdbc:sqlite:G:\\제로베이스\\workspace\\zerobase-part1-mission1\\wifi_list.db";

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(dbUrl);

			String sql = "Delete from history where id = ? ;";

			preStmt = con.prepareStatement(sql);
			preStmt.setInt(1, id);

			int affected = preStmt.executeUpdate();

			if (affected > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static List<HistoryDTO> historySelect() {

		List<HistoryDTO> historyList = new ArrayList<HistoryDTO>();

		String dbUrl = "jdbc:sqlite:G:\\제로베이스\\workspace\\zerobase-part1-mission1\\wifi_list.db";

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			System.out.println(e);
		}

		Connection con = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(dbUrl);
			System.out.println("SQLite DB connected");

			String sql = "SELECT * FROM history;";

			preStmt = con.prepareStatement(sql);

			rs = preStmt.executeQuery();

			while (rs.next()) {

				HistoryDTO dto = new HistoryDTO();

				dto.setId(rs.getInt("ID"));
				dto.setLat(rs.getDouble("LAT"));
				dto.setLnt(rs.getDouble("LNT"));
				dto.setCheckDttm(rs.getString("CHECK_DTTM"));

				historyList.add(dto);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return historyList;
	}
}
