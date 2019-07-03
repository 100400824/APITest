package com.demo.assist;

import com.demo.APITEST.ApiTest;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.util.ArrayList;
import java.util.List;

public class PostFormData {

    public static String doTest(String casePath, String sheetCaseName, Boolean isAll, Boolean isPrint) throws Exception {

        String entity = "";

        String sheetURLName = "URL";

        String[] hostURL = ExcelTest.getColumnValue(casePath, sheetURLName, 2);

        String url = hostURL[1] + "/" + sheetCaseName;

        int pNum = ExcelTest.getColumnNum(casePath, sheetCaseName) - 3;

        int caseNum = 1;

        if (isAll) {

            caseNum = ExcelTest.getRowNum(casePath, sheetCaseName) - 1;
        }

        String[] pName = ExcelTest.getRowValue(casePath, sheetCaseName, 0);

        String[] pValue;

        String caseName = pName[0];

        if (isPrint) {

            System.out.println(caseName + ":");
        }

        for (int i = 1; i <= caseNum; i++) {

            pValue = ExcelTest.getRowValue(casePath, sheetCaseName, i);

            entity = PostFormData.doPost(url, pNum, pName, pValue, isPrint);
        }

        return entity;
    }

    private static String doPost(String url, int pNum, String[] pName, String[] pValue, Boolean isPrint) throws Exception {

        int index = Integer.parseInt(pValue[0]);

        String caseInfo = pValue[1];

        String verify = pValue[pNum + 2];

        CloseableHttpClient httpClient = HttpClients.createDefault();

        CookieStore cookieStore = new BasicCookieStore();

        HttpClientContext context = HttpClientContext.create();

        context.setCookieStore(cookieStore);

        CloseableHttpResponse response;

        String entity;

        HttpPost httpPost;

        httpPost = new HttpPost(url);

        List<NameValuePair> p = new ArrayList<NameValuePair>();

        for (int i = 1; i <= pNum; i++) {

            if ((pValue[i + 1]).equals("tokenright")) {

                String[] arr = ApiTest.doLogin(false,false).split("=");

                pValue[i + 1] = arr[1];
            }

            p.add(new BasicNameValuePair(pName[i + 1], pValue[i + 1]));

        }

        UrlEncodedFormEntity uf = new UrlEncodedFormEntity(p, "utf-8");

        httpPost.setEntity(uf);

        response = httpClient.execute(httpPost);

        entity = EntityUtils.toString(response.getEntity(), "UTF-8");

        if (entity.contains(verify)) {

            if (isPrint) {

                System.out.println(index + "、" + caseInfo + "：测试通过");
            }

        } else {
            System.out.println(index + "、" + caseInfo + "测试未通过,请检查。。。");

            System.out.println("实际返回值：" + entity);

            System.out.println("期望返回值：" + verify);

        }

        return entity;
    }
}
