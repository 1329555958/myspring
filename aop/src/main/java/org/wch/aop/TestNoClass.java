package org.wch.aop;

import org.wch.test.TestNotInPath;

/**
 * @author weichunhe
 *         Created on 2016/9/12.
 * @version 1.0
 *          测试依赖包中存在不在当前路径下的类文件
 */
public class TestNoClass {
    public static void main(String[] args) {
        TestNotInPath notInPath = new TestNotInPath();
        System.out.println(notInPath.nodep());
        notInPath.escape();
    }
}
