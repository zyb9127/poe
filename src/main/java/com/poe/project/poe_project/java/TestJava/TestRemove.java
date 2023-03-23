package com.poe.project.poe_project.java.TestJava;

import com.poe.project.poe_project.java.Entry.UserVo;
import org.assertj.core.util.Lists;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author poe.zhang
 * @date 2022年09月19日 11:49
 * @description:测试移除
 */
public class TestRemove {

    public static void main(String[] args) {
        List<UserVo> list= Lists.newArrayList(new UserVo("a","a1","a2"),
                new UserVo("b","b1","b2"),
                new UserVo("c","b1","c1"));

        Map<String, List<UserVo>> nameCollect = list.stream().collect(Collectors.groupingBy(UserVo::getName));
        for (Map.Entry<String, List<UserVo>> name : nameCollect.entrySet()) {
            List<UserVo> list1 = name.getValue();
            Map<String, List<UserVo>> colorCollect = list1.stream().collect(Collectors.groupingBy(UserVo::getColor));
            for (Map.Entry<String, List<UserVo>> colorMap : colorCollect.entrySet()) {
                String color = colorMap.getKey();

                if(!"b1".equals(color)){
                    continue;
                }
                List<UserVo> list2 = colorMap.getValue();
                Iterator<UserVo> iterator = list2.iterator();
                while (iterator.hasNext()) {
                    UserVo next = iterator.next();
                    if(next.getColor().equals("b1")){
                        iterator.remove();
                    }
                }
                System.out.println(list2);
            }
        }
    }
}
