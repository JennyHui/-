package Public;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2015/5/11
 */
public class ApiEngine {

    /**
     * 根据传入的TID匹配找出需要的api所在行
     * @param tid
     * @return
     */
    public List getApiData(String tid) {

        int DataRow = 0;
        List apiData = null;
        try {
            List tidList;
            tidList = ExcelEngine.getColData(0);
            for (int i = 1; i < tidList.size(); i++) {
                String excelTid = (String) tidList.get(i);
                //System.out.println(excelTid);
                if (excelTid.equals(tid)) {
                    DataRow = i;break;
                    //System.out.println("行数："+i);
                }


            }
            //找出api的行数据
            apiData = ExcelEngine.getRowData(DataRow);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiData;
    }

    public void apiEngine(String tid,String... Parameters) {
        List apiData = getApiData(tid);
        ArrayList key = new ArrayList();
        ArrayList value = new ArrayList();
        //HashMap hashmap = new HashMap();
        int p = 0;
        while(p<Parameters.length){
            //System.out.println("key:"+apiData.get(p+5));
            //System.out.println("value:"+Parameters[p]);
            key.add(apiData.get(p + 5));
            value.add(Parameters[p]);
            p = p+1;
        }
        //System.out.println(key.toString()+' '+value.toString());
        //System.out.println(key.size()+ "and" +value.size());

        StringBuffer allParameters = new StringBuffer();
        int k=0;
        while(k<key.size()) {
            for (int v = 0; v < value.size(); v++) {
                String parametersKey = (String) key.get(k);
                String parametersValue = (String) value.get(v);
                if(v==value.size()-1){
                    allParameters.append(parametersKey + '=' + parametersValue);
                }
                else{
                    allParameters.append(parametersKey + '=' + parametersValue + '&');
                }

                k = k + 1;
            }
        }
        System.out.println(allParameters.toString());


        /**
        Iterator it = hashmap.keySet().iterator();
        while(it.hasNext()) {
            String key = (String)it.next();
            System.out.println("key:" + key);
            System.out.println("value:" + hashmap.get(key));
        }*/
    }


    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
        ExcelEngine.filepath = "F:\\IdeaProject\\excelDemo\\src\\main\\java\\Public\\apiTest.xls";
        //ExcelEngine.inputfile = "test.xls";
        ExcelEngine.sheetname = "api";
        ApiEngine demo = new ApiEngine();
        demo.apiEngine("login", "18659244231", "123456");
        //demo.apiEngine("注册", "18659244231", "040bd08a4290267535cd247b8ba2eca129d9fe9f","迷羊");

    }

}
