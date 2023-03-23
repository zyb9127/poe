package com.poe.project.poe_project.java.SM4;

/**
 * @author: 徐一杰
 * @date: 2022/10/11
 */
class SM4_Context {
    public int mode;

    public int[] sk;

    public boolean isPadding;

    public SM4_Context() {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new int[32];
    }
}
