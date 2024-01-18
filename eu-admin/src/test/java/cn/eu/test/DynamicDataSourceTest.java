package cn.eu.test;

import cn.eu.system.domain.SysUser;
import cn.eu.system.service.ISysUserService;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhaoeryu
 * @since 2023/10/25
 */
@SpringBootTest
public class DynamicDataSourceTest {

    @Autowired
    ISysUserService sysUserService;

    @Test
    public void testDs(){
        //默认master查询
        SysUser master = sysUserService.getById(1);

        //切换数据源，在slave查询
        DynamicDataSourceContextHolder.push("slave");
        SysUser slave = sysUserService.getById(1);

        // 切换回master
        DynamicDataSourceContextHolder.push("master");
        SysUser master2 = sysUserService.getById(1);

        System.out.println(master);
        System.out.println(slave);
        System.out.println(master2);
    }

}
