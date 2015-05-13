package main.jenny.PO.Tester;

import main.jenny.PO.Tester.Tester;
import main.jenny.PO.Tester.TesterDao;
import main.jenny.until.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by JennyHui on 2015/5/7
 */
public class TesterDaoImpl implements TesterDao {

    private final String findsql = "main.jenny.mapping.TesterMapper.getTester";

    @Override
    public Tester findTesterByName(String name){
        // �ӻỰ�����еõ��Ự
        SqlSession sqlSession = MyBatisUtil.getSqlSession("DB2",true);
        // ͨ��sqlSession�������ݿ�
        // ��һ��������user.xml�����statement��id
        // �ڶ����������������
        Tester tester = sqlSession.selectOne(findsql,name);
        //System.out.println(user);
        // �ͷ���Դ
        sqlSession.close();
        return tester;
    }
}