package together.study;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import together.study.Service.UserService;
import together.study.Service.UserServiceImpl;
import together.study.mapper.UserMapper;
import together.study.model.vms1365APIVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class vmsAPI {
    public List<vms1365APIVO> readVMSApi() throws IOException {
        String key = "2fTk6t8QCKyKFWliruTah0dyNyxcsuVasxuTHQo4ZnUEoFIGqw4gNbOypm1BW4PuCYI6pLqwHRwn9h14XwY9lA%3D%3D";
        List<vms1365APIVO> list = new ArrayList<>();
        try {
                String urlAddress = "http://apis.data.go.kr/B460014/vmsdataview/getVollcolectionList?serviceKey=" + key + "&numOfRows=10&pageNo=1&strDate=2023-05-01&endDate=2023-12-31&areaCode=0115&TermType=1&status=2";// + "&Type=json");

                org.json.JSONObject xmlJSONObj = XML.toJSONObject(Jsoup.connect(urlAddress).get().toString());
                Document doc = Jsoup.parse(Jsoup.connect(urlAddress).get().toString());
                Elements rows = doc.select("item");
                for (Element row : rows) {
                    vms1365APIVO vo = new vms1365APIVO();
                    vo.setListApiType("VMS");
                    vo.setActTypeName(row.select("actTypeName").get(0).text());
                    vo.setAreaName(row.select("areaName").get(0).text());
                    vo.setStatus((Integer.parseInt((row.select("status").get(0).text()))));
                    vo.setSeq(row.select("seq").get(0).text());
                    vo.setTitle(row.select("title").get(0).text());
                    vo.setCentName(row.select("centName").get(0).text());
                    vo.setActPlace(row.select("place").get(0).text());
                    vo.setTeenager(row.select("teenager").get(0).text());
                    list.add(vo);
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
