package com.fastjrun.thirdparty.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fastjrun.thirdparty.helper.HttpHelper;

import net.sf.json.JSONObject;




public class BaiduLBSUtil {

    protected final Log log = LogFactory.getLog(this.getClass());

    private final static String USER_AGENT = "Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)";
    
    private String baiduKey = ""; // zgl 密钥
    private String baiduSk = ""; //
    
//    private String baiduSk="94608E7E4B6Ba80633d5a44ef3956c4b";   //用户的权限签名

    private String createTableUrl = "http://api.map.baidu.com/geodata/v3/geotable/create";

    private String listTableUrl = "http://api.map.baidu.com/geodata/v3/geotable/list";

    private String baiduApiUrl = "http://api.map.baidu.com/geoconv/v1/";
    
    //查询指定id表（detail geotable）接口
    private String detailTableUrl = "http://api.map.baidu.com/geodata/v3/geotable/detail";
    
    //修改表（update geotable）接口
    private String updateTableUrl = "http://api.map.baidu.com/geodata/v3/geotable/update";
    
    //删除表（delete geotable）接口
    private String deleteTableUrl = "http://api.map.baidu.com/geodata/v3/geotable/delete";
    
    //查询列接口
    private String columnListUrl = "http://api.map.baidu.com/geodata/v3/column/list";   
    
    //创建列接口
    private String createColumnUrl = "http://api.map.baidu.com/geodata/v3/column/create"; 
    
    //查询指定id列（detail column）详情接口
    private String detailColumnUrl = "http://api.map.baidu.com/geodata/v3/column/detail"; 
    
    //修改指定条件列（column）接口
    private String updateColumnUrl = "http://api.map.baidu.com/geodata/v3/column/update"; 
    
    //删除指定条件列（column）接口
    private String deleteColumnUrl = "http://api.map.baidu.com/geodata/v3/column/delete"; 
    
    //创建数据（create poi）接口
    private String createPoiUrl = "http://api.map.baidu.com/geodata/v3/poi/create";
    
    //查询指定条件的数据（poi）列表接口
    private String listPoiUrl = "http://api.map.baidu.com/geodata/v3/poi/list";
    
    //查询指定id的数据（detail poi）详情接口
    private String detailPoiUrl = "http://api.map.baidu.com/geodata/v3/poi/detail";
    
    //修改数据（update poi）接口
    private String updatePoiUrl = "http://api.map.baidu.com/geodata/v3/poi/update";
    
    // 删除单个数据（delete poi）接口（支持批量）
    private String deletePoiUrl = "http://api.map.baidu.com/geodata/v3/poi/delete";
    
    //批量上传数据（post pois csv file）接口
    private String uploadPoiUrl = "http://api.map.baidu.com/geodata/v3/poi/upload";    
  
//  private String baiduKey = "45ea3d1dbeb76aa8f530192424ffb5b8";   //zgl 密钥
  
//  private String baiduSk = "5d63ab9df6bf1f5074f05513f589bfe1";  //
	
  //poi周边搜索URL
    private String nearbyUrl = "http://api.map.baidu.com/geosearch/v3/nearby"; // GET请求
  
  //poi本地检索
    private String localUrl = "http://api.map.baidu.com/geosearch/v3/local";   // GET请求
  
  //poi矩形检索
    private String boundUrl = "http://api.map.baidu.com/geosearch/v3/bound";   // GET请求
  
  //poi详情检索
    private String detailUrl = "http://api.map.baidu.com/geosearch/v3/detail/";  // GET请求    
    
//    private int geotable_id = 127982;  //t_shop
    private int geotable_id = 128649;  //t_shop
    private double longitude = 116.324196;
	private double latitude = 39.989358;
    /*
     * 查询表（list geotable）接口
     */
    public JSONObject listTables(String tableName){
    	String sn="";
    	Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ak", baiduKey);
        try {
			String paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/geotable/list?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.listTableUrl);
        urlSB.append("?ak=").append(this.baiduKey);
        urlSB.append("&sn=").append(sn);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject tables = JSONObject.fromObject(response);
        return tables;
    }
    
    /**
     * 创建表（create geotable）接口
     * @param tableName
     * @return
     */
    public JSONObject createTable(String tableName){
        Map<String, String> paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ak", this.baiduKey);
        paramsMap.put("geotype", "1");
        paramsMap.put("is_published", "1");
        paramsMap.put("name", tableName);
        String sn="";
        String paramsStr = "";
		try {
//			snTmp = this.toQueryString(paramsMap);
//	        log.info(snTmp);
//			String snMD5Before = URLEncoder.encode("/geodata/v3/geotable/create?"+snTmp+this.baiduSk, "UTF-8");
//	        log.info(snMD5Before);
//	        sn=this.MD5(snMD5Before);
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/geotable/create?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
	        paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	       
		paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr, createTableUrl,true);
        log.info(response);
        JSONObject tables = JSONObject.fromObject(response);
        return tables;
    }
    
    /**
     *  查询指定id表（detail geotable）接口
     *  @param id: 指定geotable的id,int32
     */
    public JSONObject detailTable(int id){
    	String sn = "";
    	String paramsStr = "";
    	Map paramsMap = new LinkedHashMap<String, String>();
    	paramsMap.put("id", id+"");
    	paramsMap.put("ak", baiduKey);
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/geotable/detail?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
			paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.detailTableUrl);
        urlSB.append("?").append(paramsStr).append("&sn=").append(sn);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject table = JSONObject.fromObject(response);
        return table;
    }
    /**
     * 修改表（update geotable）接口
     * POST请求
     */
    public JSONObject updateTable(Map paramsMap){
    	String paramsStr = "";
    	String sn = "";
        try {
        	paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/geotable/update?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
	        paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr,this.updateTableUrl,true);
        log.info(response);
        JSONObject table = JSONObject.fromObject(response);
        return table;
    }
    
    
    /**
     *  删除表（delete geotable）接口
     *  注：当geotable里面没有有效数据时，才能删除geotable
     *  post请求
     */
    public JSONObject deleteTable(int id){
    	String sn = "";
    	String paramsStr = "";
    	Map paramsMap = new LinkedHashMap<String, String>();
    	paramsMap.put("ak", baiduKey);
    	paramsMap.put("id", id+"");
    	
        try {
        	paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/geotable/delete?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
	        paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr,this.deleteTableUrl,true);
        log.info(response);
        JSONObject table = JSONObject.fromObject(response);
        return table;
    }
    
    /**
     * 查询列接口
     * @param geotable_id:geotable的主键
     * get请求
     */
    public JSONObject columnList(int geotable_id){
    	String sn = "";
    	String paramsStr ="";
    	Map paramsMap = new LinkedHashMap<String, String>();
    	paramsMap.put("geotable_id", geotable_id+"");
    	paramsMap.put("ak", baiduKey);
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/column/list?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
			paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.columnListUrl);
        urlSB.append("?").append(paramsStr).append("&sn=").append(sn);
        log.info(urlSB);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject columns = JSONObject.fromObject(response);
        return columns;
    }
    /**
     * 创建列接口
     * 参数名:
     * name,
     * key,
     * type,
     * max_length
     * default_value
     * is_sortfilter_field
     * is_search_field
     * Is_index_field
     * is_unique_field
     * geotable_id
     * ak
     * sn
     * post请求
     */
    public JSONObject createColumn(Map paramsMap){
    	String sn = "";
    	String paramsStr ="";
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/column/create?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
			paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr,this.createColumnUrl,true);
        log.info(response);
        JSONObject column = JSONObject.fromObject(response);
        return column;
    }
  /**
   *  查询指定id列（detail column）详情接口
   *  get请求
   */
    public JSONObject detailColumn(int id){
    	String sn = "";
    	String paramsStr = "";
    	Map paramsMap = new LinkedHashMap<String, String>();
    	paramsMap.put("id", id+"");
    	paramsMap.put("geotable_id", geotable_id);
    	paramsMap.put("ak", baiduKey);
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/column/detail?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
			paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.detailColumnUrl);
        urlSB.append("?").append(paramsStr).append("&sn=").append(sn);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject column = JSONObject.fromObject(response);
        return column;
    }
    
    /*
     * 修改指定条件列（column）接口
     * post请求
     * updateColumn
     */
    public JSONObject updateColumn(Map paramsMap){
    	String paramsStr = "";
    	String sn = "";
        try {
        	paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/column/update?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
	        paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr,this.updateColumnUrl,true);
        log.info(response);
        JSONObject table = JSONObject.fromObject(response);
        return table;
    }
    
    /**
     *  删除指定条件列（column）接口
     *  post请求
     */
    public JSONObject deleteColumn(int id){
    	String sn = "";
    	String paramsStr = "";
    	Map paramsMap = new LinkedHashMap<String, String>();
    	paramsMap.put("ak", baiduKey);
    	paramsMap.put("geotable_id", geotable_id);
    	paramsMap.put("id", id+"");
    	
        try {
        	paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/column/delete?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
	        paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr,this.deleteColumnUrl,true);
        log.info(response);
        JSONObject column = JSONObject.fromObject(response);
        return column;
    }
    
    /**
     * 创建数据（create poi）接口
     * post请求
     */
    public JSONObject createPoi(Map paramsMap){
    	String sn = "";
    	String paramsStr ="";
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/poi/create?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
			paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr,this.createPoiUrl,true);
        log.info(response);
        JSONObject column = JSONObject.fromObject(response);
        return column;
    }
    
    /**
     * 查询指定条件的数据（poi）列表接口
     * get请求
     */
    public JSONObject poiList(int geotable_id){
    	String sn = "";
    	String paramsStr ="";
    	Map paramsMap = new LinkedHashMap<String, String>();
    	paramsMap.put("geotable_id", geotable_id+"");
    	paramsMap.put("ak", baiduKey);
    	paramsMap.put("page_index", 0);
    	paramsMap.put("page_size", 10);
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/poi/list?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
			paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.listPoiUrl);
        urlSB.append("?").append(paramsStr).append("&sn=").append(sn);
        log.info(urlSB);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject columns = JSONObject.fromObject(response);
        return columns;
    }
    
    /**
     * 查询指定id的数据（poi）详情接口
     * get请求
     */
    public JSONObject detailPoi(Map paramsMap){
    	String sn = "";
    	String paramsStr ="";
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/poi/detail?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
			paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.detailPoiUrl);
        urlSB.append("?").append(paramsStr).append("&sn=").append(sn);
        log.info(urlSB);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject columns = JSONObject.fromObject(response);
        return columns;
    }
    
    /**
     * 修改数据（poi）接口
     * post请求
     */
    public JSONObject updatePoi(Map paramsMap){
    	String paramsStr = "";
    	String sn = "";
        try {
        	paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/poi/update?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
	        paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr,this.updatePoiUrl,true);
        log.info(response);
        JSONObject table = JSONObject.fromObject(response);
        return table;
    }
    
    /**
     * 删除数据（poi）接口
     */
    public JSONObject deletePoi(int id){
    	String sn = "";
    	String paramsStr = "";
    	Map paramsMap = new LinkedHashMap<String, String>();
    	paramsMap.put("ak", baiduKey);
    	paramsMap.put("geotable_id", geotable_id+"");
    	paramsMap.put("id",id);
    	
        try {
        	paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/poi/delete?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
	        paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr,this.deletePoiUrl,true);
        log.info(response);
        JSONObject table = JSONObject.fromObject(response);
        return table;
    }
    
    /**
     * 删除数据（poi）接口
     */
    public JSONObject deletePois(String ids){
    	String sn = "";
    	String paramsStr = "";
    	Map paramsMap = new LinkedHashMap<String, String>();
    	paramsMap.put("ak", baiduKey);
    	paramsMap.put("geotable_id", "128293");
    	paramsMap.put("ids",ids);
    	
        try {
        	paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/poi/delete?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
	        paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        paramsStr += "&sn="+sn;
        String response = HttpHelper.request(paramsStr,this.deletePoiUrl,true);
        log.info(response);
        JSONObject table = JSONObject.fromObject(response);
        return table;
    }
    
    /**
     * 批量上传数据（post pois csv file）接口
     * post请求
     */
    public JSONObject uploadPoi(Map paramsMap){
    	String sn = "";
    	String paramsStr ="";
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geodata/v3/poi/upload?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
			paramsMap.put("sn", sn);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        paramsStr += "&timestamp="+new Date().getTime();
        paramsStr += "&sn="+sn;
        log.info(paramsStr);
        String response = HttpHelper.request(paramsStr,this.uploadPoiUrl,true);
        log.info(response);
        JSONObject result = JSONObject.fromObject(response);
        return result;
    }
    
    /**
     * poi周边搜索
     */
    public JSONObject nearBySearch(){
    	String sn="";
    	Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ak", baiduKey);
        paramsMap.put("geotable_id", 127982);
        paramsMap.put("q", "物美海淀店");
        paramsMap.put("location", "116.324196,39.989358");
        paramsMap.put("radius", 5000);
      //  paramsMap.put("tags", "超市");   //空格分隔的多字符串
        paramsMap.put("page_index", 0);
        paramsMap.put("page_size", 10);
        String paramsStr = "";
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geosearch/v3/nearby?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.nearbyUrl);
        urlSB.append("?").append(paramsStr);
        urlSB.append("&sn=").append(sn);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject tables = JSONObject.fromObject(response);
        return tables;
    }
    
    /**
     * poi本地检索
     */
    public JSONObject localSearch(){
    	String sn="";
    	Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ak", baiduKey);
        paramsMap.put("geotable_id", 127982);
        paramsMap.put("q", "");
        paramsMap.put("region", "海淀区");
     //   paramsMap.put("tags", "超市");   //空格分隔的多字符串
        paramsMap.put("sortby", "distance:1");  //1升序，-1降序
//        paramsMap.put("page_index", 0);
//        paramsMap.put("page_size", 10);
        
        String paramsStr = "";
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geosearch/v3/local?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.localUrl);
        urlSB.append("?").append(paramsStr);
        urlSB.append("&sn=").append(sn);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject tables = JSONObject.fromObject(response);
        return tables;
    }
    
    /**
     * poi矩形检索
     */
    public JSONObject boundSearch(){
    	String sn="";
    	Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ak", baiduKey);
        paramsMap.put("geotable_id", 127982);
        paramsMap.put("q", "");
        paramsMap.put("bounds", "116.2321,38.56623;116.4321,38.96623");//左下角和右上角的经纬度坐标点。2个点用;号分隔
       // paramsMap.put("tags", "超市");   //空格分隔的多字符串
        paramsMap.put("sortby", "distance:1");  //1升序，-1降序
        paramsMap.put("page_index", 0);
        paramsMap.put("page_size", 10);
        
        String paramsStr = "";
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geosearch/v3/bound?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.boundUrl);
        urlSB.append("?").append(paramsStr);
        urlSB.append("&sn=").append(sn);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject tables = JSONObject.fromObject(response);
        return tables;
    }
    
    /**
     * poi详情检索
     * get请求    
     */
    public JSONObject detailSearch(int uid){
    	String sn="";
    	String paramsStr = "";
    	Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ak", baiduKey);
        paramsMap.put("coord_type", 1);
        paramsMap.put("geotable_id", 127982);
        try {
			paramsStr = toQueryString(paramsMap);
			String wholeStr = new String("/geosearch/v3/detail?" + paramsStr + baiduSk);
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
			sn = MD5(tempStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer urlSB = new StringBuffer(this.detailUrl);
        urlSB.append("1521905605");
        urlSB.append("?").append(paramsStr);
        urlSB.append("&sn=").append(sn);
        log.info(urlSB);
        Map<String, String> map=new HashMap<String, String>();
        String response = HttpHelper.sendGet(urlSB.toString(),map);
        log.info(response);
        JSONObject tables = JSONObject.fromObject(response);
        return tables;
    }
    
    
    
 // 对Map内所有value作utf8编码，拼接返回结果
    public String toQueryString(Map<?, ?> data)
                    throws UnsupportedEncodingException {
            StringBuffer queryString = new StringBuffer();
            for (Entry<?, ?> pair : data.entrySet()) {
                    queryString.append(pair.getKey() + "=");
                    queryString.append(URLEncoder.encode(String.valueOf(pair.getValue()),
                                    "UTF-8") + "&");
            }
            if (queryString.length() > 0) {
                    queryString.deleteCharAt(queryString.length() - 1);
            }
            return queryString.toString();
    }

    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    public String MD5(String md5) {
            try {
                    java.security.MessageDigest md = java.security.MessageDigest
                                    .getInstance("MD5");
                    byte[] array = md.digest(md5.getBytes());
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < array.length; ++i) {
                            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                                            .substring(1, 3));
                    }
                    return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {
            }
            return null;
    }    
}

