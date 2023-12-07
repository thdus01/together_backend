package together.study;

import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import together.study.model.vms1365APIVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class selectAPI1365 {
    public List<vms1365APIVO> read1365selectApi(int pageNum, String keyword) throws IOException {
        List<vms1365APIVO> list = new ArrayList<>();
        int MaxCount = 0;
        try {
                String urlAddress = "http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrSearchWordList?numOfRows=10&pageNo="+pageNum+"SchCateGu=all&keyword="+ keyword;// + "&Type=json");

                JSONObject xmlJSONObj = XML.toJSONObject(Jsoup.connect(urlAddress).get().toString());
                Document doc = Jsoup.parse(Jsoup.connect(urlAddress).get().toString());
                Elements rows = doc.select("item");

                for (Element row : rows) {
                    vms1365APIVO vo = new vms1365APIVO();
                    vo.setListApiType("1365");
                    vo.setActTypeName(row.select("srvcClCode").get(0).text());
                    vo.setAreaName(row.select("nanmmbyNm").get(0).text());
                    vo.setStatus((Integer.parseInt((row.select("progrmSttusSe").get(0).text()))));
                    vo.setSeq(row.select("progrmRegistNo").get(0).text());
                    vo.setTitle(row.select("progrmSj").get(0).text());
                    vo.setCentName(row.select("nanmmbyNm").get(0).text());
                    vo.setActPlace(row.select("actPlace").get(0).text());
                    vo.setTeenager(row.select("yngbgsPosblAt").get(0).text());
                    list.add(vo);
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
