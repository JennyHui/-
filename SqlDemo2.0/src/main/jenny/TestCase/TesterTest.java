package main.jenny.TestCase;

import main.jenny.PO.Tester.TesterDao;
import main.jenny.PO.Tester.TesterDaoImpl;
import org.testng.annotations.Test;

/**
 * Created by JennyHui on 2015/5/7
 */
public class TesterTest {

    TesterDao testerDao = new TesterDaoImpl();

    // ���Բ�ѯ�û���Ϣ����
    @Test
    public void testFindTesterByName() {
        System.out.println(testerDao.findTesterByName("JennyHui").getSex());
    }
}
