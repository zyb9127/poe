package com.poe.project.poe_project.java.TestJava;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author poe
 * @description: TODO
 * @date 2021/9/22 3:21 下午
 */
public class TestMap {

    public static void main(String[] args) {
        List<Group> groupList=new ArrayList<>();
        groupList.add(new Group("1","0"));
        groupList.add(new Group("11","0"));
        groupList.add(new Group("2","11"));
        groupList.add(new Group("444","0"));
        groupList.add(new Group("4443333","444"));
        groupList.add(new Group("AA","poe"));
        groupList.add(new Group("aaasss","tuop"));
        groupList.add(new Group("asd","qwe"));
        groupList.add(new Group("asdgf","modelTest"));
        groupList.add(new Group("BB","poe"));
        groupList.add(new Group("beck","0"));
        groupList.add(new Group("business-group","businessDefaultModel"));
        groupList.add(new Group("businessDefaultModel","0"));
        groupList.add(new Group("code","0"));
        groupList.add(new Group("cs","0"));
        groupList.add(new Group("cs1","cs"));
        groupList.add(new Group("dfgsaas","0"));
        groupList.add(new Group("ethanModel","0"));
        groupList.add(new Group("host","platformDefaultModel"));
        groupList.add(new Group("infrastructureDefaultModel","0"));
        groupList.add(new Group("location","infrastructureDefaultModel"));
        groupList.add(new Group("modelTest","0"));
        groupList.add(new Group("network-device","infrastructureDefaultModel"));
        groupList.add(new Group("new","0"));
        groupList.add(new Group("npm_resource","platformDefaultModel"));
        groupList.add(new Group("physical-server","infrastructureDefaultModel"));
        groupList.add(new Group("platformDefaultModel","0"));
        groupList.add(new Group("poe","0"));
        groupList.add(new Group("safetyDevice","infrastructureDefaultModel"));
        groupList.add(new Group("service-group","businessDefaultModel"));
        groupList.add(new Group("service-node","platformDefaultModel"));
        groupList.add(new Group("storage-device","infrastructureDefaultModel"));
        groupList.add(new Group("testbeck2","beck"));
        groupList.add(new Group("qwe","0"));
        groupList.add(new Group("aaa1","0"));
        groupList.add(new Group("aaa2","aaa1"));
        groupList.add(new Group("aaa3","aaa2"));
        groupList.add(new Group("aaa4","aaa3"));
        groupList.add(new Group("aaa5","aaa4"));
        groupList.add(new Group("testtest","0"));
        groupList.add(new Group("testtest2","testtest"));
        groupList.add(new Group("testtest3","testtest2"));
        groupList.add(new Group("testtest4","testtest3"));
        groupList.add(new Group("testtest5","testtest2"));
        groupList.add(new Group("tuop","0"));
        groupList.add(new Group("tuopu2","tuop"));
        groupList.add(new Group("tuoputuzilei","tuopu2"));


        Map<String, String> collect = groupList.stream().collect(Collectors.toMap(Group::getGroupId, Group::getParentId));
        Set<String> groudIdSet=new HashSet<>();
        groudIdSet.add("aaa5");

        Set<String> topParentId=new HashSet<>();
        Set<String> parentIdSet=new HashSet<>(groudIdSet);
        long start = System.currentTimeMillis();
        Set<String> id = getParentId(parentIdSet,groudIdSet, collect,topParentId);
        long end = System.currentTimeMillis();
        System.out.println(parentIdSet);

    }

    public static Set<String> getParentId(Set<String> parentIdSet,Set<String> groudIdSet, Map<String, String> all,Set<String> topParentId){
        groudIdSet.forEach(
                groud->{
                    if(all.containsKey(groud)){
                        //获取上一级
                        String parent = all.get(groud);
                        if(!"0".equals(groud)){
                            parentIdSet.add(parent);
                            getParentId(parentIdSet,Collections.singleton(parent),all,topParentId);
                        }else{
                            topParentId.add(groud);
                        }
                    }
                }
        );
        return topParentId;
    }

}


@Data
class Group{
    private String groupId;
    private String parentId;

    public Group(String groupId, String parentId) {
        this.groupId = groupId;
        this.parentId = parentId;
    }
}