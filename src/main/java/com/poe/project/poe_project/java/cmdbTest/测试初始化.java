package com.poe.project.poe_project.java.cmdbTest;

import com.poe.project.poe_project.java.Entry.ModelGroupVo;
import com.poe.project.poe_project.java.Entry.PersonVo;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author poe.zhang
 * @date 2023年03月13日 17:07
 * @description:初始化分组
 */
public class 测试初始化 {
    private static final Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);

    public static final List<ModelGroupVo> GROUPLIST =new ArrayList<>();
    static{
        GROUPLIST.add(new ModelGroupVo("businessDefaultModel","业务类","110","business-group"));
        GROUPLIST.add(new ModelGroupVo("0","业务资源","110","businessDefaultModel"));
        GROUPLIST.add(new ModelGroupVo("platformDefaultModel","主机","110","host"));
        GROUPLIST.add(new ModelGroupVo("0","基础设施","110","infrastructureDefaultModel"));
        GROUPLIST.add(new ModelGroupVo("infrastructureDefaultModel","地理位置","110","location"));
        GROUPLIST.add(new ModelGroupVo("infrastructureDefaultModel","网络设备","110","network-device"));
        GROUPLIST.add(new ModelGroupVo("platformDefaultModel","NPM资源","110","npm_resource"));
        GROUPLIST.add(new ModelGroupVo("0","其他","110","other"));
        GROUPLIST.add(new ModelGroupVo("0","p1","110","p1"));
        GROUPLIST.add(new ModelGroupVo("p1","p2","110","p3"));
        GROUPLIST.add(new ModelGroupVo("infrastructureDefaultModel","物理服务器","110","physical-server"));
        GROUPLIST.add(new ModelGroupVo("0","平台资源","110","platformDefaultModel"));
        GROUPLIST.add(new ModelGroupVo("infrastructureDefaultModel","安全设备","110","safetyDevice"));
        GROUPLIST.add(new ModelGroupVo("businessDefaultModel","服务类","110","service-group"));
        GROUPLIST.add(new ModelGroupVo("platformDefaultModel","服务节点","110","service-node"));
        GROUPLIST.add(new ModelGroupVo("infrastructureDefaultModel","存储设备","110","storage-devic"));
    }
    public static void main(String[] args) {
        Map<String, List<ModelGroupVo>> accountMap = GROUPLIST.stream().filter(g->!g.getGroupId().equals("other")).collect(Collectors.groupingBy(ModelGroupVo::getAccountId));
        for (Map.Entry<String, List<ModelGroupVo>> accountMapEntry : accountMap.entrySet()) {
            List<ModelGroupVo> mapByAccountId = accountMapEntry.getValue();
            //获取所有的模型分组，忽略其它
            Map<String, List<ModelGroupVo>> sortMap = mapByAccountId.stream().collect(Collectors.groupingBy(ModelGroupVo::getParentId));
            for (Map.Entry<String, List<ModelGroupVo>> parentMapEntry : sortMap.entrySet()) {
                List<ModelGroupVo> listVos = parentMapEntry.getValue().stream()
                        .sorted((o1, o2) -> CHINA_COMPARE.compare(o1.getGroupName(), o2.getGroupName()))
                        .sorted((o1, o2) -> CHINA_COMPARE.compare(o1.getGroupName().substring(0, 1), o2.getGroupName().substring(0, 1)))
                        .collect(Collectors.toList());
                //设置编号
                AtomicInteger size = new AtomicInteger(1);
                listVos.forEach(s->s.setSort(size.getAndIncrement()));
                listVos.forEach(System.out::println);
                System.out.println("######");
            }
        }


    }
}
