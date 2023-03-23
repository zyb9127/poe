package com.poe.project.poe_project.java.constant;

import java.util.ArrayList;
import java.util.List;

public class ModelConstant {
    /**
     * 模型根路径
     */
    public static final String BASE_MODEL = BaseConstant.BASE_URI + "/model";
    /**
     * 模型列表路径
     */
    public static final String MODEL_LIST = "/list";
    /**
     * 添加模型路径
     */
    public static final String MODEL_ADD = "/add";
    /**
     * 模型预删除路径
     */
    public static final String MODEL_PREDELETE = "/predelete";
    /**
     * 模型删除路径
     */
    public static final String MODEL_DELETE = "/delete";
    /**
     * 修改模型
     */
    public static final String MODEL_MODIFY = "/modify";
    /**
     * 模型详情
     */
    public static final String MODEL_DETAIL = "/detail";

    /**
     * 编辑模型
     */
    public static final String MODEL_MODIFY_INFORMATION = "/modifyInformation";

    public static final String MODEL_ATTRIBUTE_ID_AVAILABLE_CHECK = "/attrid/check";
    public static final String MODEL_ATTR_CHECK_CACHE_PREFIX = "model:attr:check:";
    public static final int MODEL_ATTR_CHECK_EXPIRE_SECONDS = 60;
    public static final String MODEL_KEY_ATTRIBUTE_CHECK = "/key/attribute/check";

    /**
     *模型属性
     */
    public static final String MODEL_ID = "modelId";
    public static final String GROUD_ID = "groupId";
    public static final String ATTRID = "attrID";
    public static final String TYPE = "type";

    /**
     * 内置模版不可删除属性集合
     */
    public static final List<String> TEMPLATE_PROPERTIES=new ArrayList<String>();

    static {
        TEMPLATE_PROPERTIES.add("asset_number"); //资产编码
        //资产状态
        TEMPLATE_PROPERTIES.add("asset_status");
        //所属机构
        TEMPLATE_PROPERTIES.add("owner_organization");
        //使用机构
        TEMPLATE_PROPERTIES.add("user_organization");
        //资产使用人
        TEMPLATE_PROPERTIES.add("asset_user");
    }


    /**
     * 模型属性-资产编码控件
     */
    public static final String ITAMKEY = "itamKey";//编码字段
    public static final String SPACECHAR = "spaceChar";//间隔符
    public static final String ORDER = "order";//编码字段序号
    public static final String ROLE_CONTENT = "roleContent";//编码规则内容
    public static final String SERIAL_NUMBER = "serialNumber";//流水号位数


    /**
     * 模型属性-计算属性控件
     */
    public static final String RULE_CONTENT = "ruleContent";//编码规则内容
    public static final String RULE_ID = "ruleId";//编码规则内容
    public static final String RULE_VALUE = "ruleValue";//编码规则内容
    public static final String OPERATOR = "operator";//运算符
    public static final String ISATTR = "isAttr";//是否是属性


    /**
     * 模型属性-数值范围控件
     */
    public static final String START_NUMBER = "startNumber";//开始数字
    public static final String END_NUMBER = "endNumber";//结束数字
    public static final String MIN_NUMBER = "minNumber";//可设最小值
    public static final String MAX_NUMBER = "maxNumber";//可设最大值
    public static final String U_GROUPID = "uGroupId";

    /**
     * 模型属性-U位范围控件
     */
    public static final String UNIT_TOTAL = "unitTotal";

    /**
     * 请求来源
     */
    public static final String SOURCE_MODEL = "MODEL";
    public static final String SOURCE_CI = "CI";

    public static final String MODEL_ATTRBUTE = "KA";

    public static final String MODEL_CONTENT_TREE = "/content_tree";

}
