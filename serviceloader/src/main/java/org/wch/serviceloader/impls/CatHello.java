package org.wch.serviceloader.impls;

import org.wch.serviceloader.IHello;

import java.util.logging.Logger;

/**
 * @author weichunhe
 *         Created on 2016/11/28.
 * @version 1.0
 */
public class CatHello implements IHello {
    /**
     * 打招呼
     */
    @Override
    public void hello() {
        Logger.getGlobal().info("miao miao miao");
    }
}
