package com.demo.APITEST;

import com.demo.assist.ExcelTest;
import com.demo.assist.FileManage;
import com.demo.assist.PostFormData;

public class ApiTest {

    private static String casePath = FileManage.homePath + FileManage.xx + "case.xlsx";

    public static void main(String[] args) throws Exception {

        runAllCase();

    }

    private static void runAllCase() throws Exception {

        int caseNum = ExcelTest.getRowNum(casePath, "URL");

        String[] runCase;

        for (int i = 2; i < caseNum; i++) {

            runCase = ExcelTest.getRowValue(casePath, "URL", i);

            if (runCase[3].equals("true")) {

                PostFormData.doTest(casePath, runCase[2], true, true);
            }

            System.out.println();
        }
    }

    public static String doLogin(Boolean isRunAll, Boolean isPrint) throws Exception {

        String sheetCaseName = "login";

        return PostFormData.doTest(casePath, sheetCaseName, isRunAll, isPrint);

    }


}
