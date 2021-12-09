import java.util.HashMap;

public class main {
    public static void main(String[] args) throws InterruptedException {

        //네이버 api 로부터 블로그 게시물 링크 긁어오기
        String[] results = new String[200];
        int i = 0;

        for(int k = 0; k<30; k++) {
            String resp = getData(k);
            String[] result = makeData(resp);
            for (String datum : result) {
                results[i] = datum;
                i++;
            }

            for (String s : result) {
                System.out.println(s);
            }
        }

        //키자드 로그인
        KeyzardLogin logInBot = new KeyzardLogin();
        logInBot.activateLogin();
        Thread.sleep(2000);
        //링크 입력하기
        logInBot.inputLink(results);

    }

    private static String getData(int pageNumber) {
        String resp;

        String url = "http://blog.naver.com/PostTitleListAsync.naver?";
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("blogId", "626ksb");
        param.put("currentPage", Integer.toString(pageNumber));
        param.put("countPerPage", "5");

        resp = HttpConnectionUtil.postRequest(url, param);

        return resp;
    }

    public static String[] makeData(String param) {
        String[] result = new String[5];
        String data = param.substring(param.indexOf("tagQueryString") + 3, param.length());
        data = data.substring(data.indexOf(":")+3, data.indexOf("}")-1);
        result = data.split("&");
        for(int i = 0; i<result.length; i++) {
            result[i] = result[i].replace("logNo=", "https://blog.naver.com/626ksb/");
        }

        return result;
    }

}
