package main.jenny.PO.Tester;
import org.apache.ibatis.annotations.Param;

/**
 * Created by JennyHui on 2015/4/28
 */
public interface TesterDao {
    public Tester findTesterByName(String name);
}
