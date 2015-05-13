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
        // 从会话工厂中得到会话
        SqlSession sqlSession = MyBatisUtil.getSqlSession("DB2",true);
        // 通过sqlSession操作数据库
        // 第一个参数：user.xml定义的statement的id
        // 第二个参数：输入参数
        Tester tester = sqlSession.selectOne(findsql,name);
        //System.out.println(user);
        // 释放资源
        sqlSession.close();
        return tester;
    }
}