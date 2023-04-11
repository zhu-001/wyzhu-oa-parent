package com.wyzhu.auth.utils;

import com.wyzhu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2023/4/12 0012
 * Author:wyzhu
 * Description:
 */
public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> sysMenus) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu,sysMenus));
            }
        }
        return trees;
    }

    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenus) {
        sysMenu.setChildren(new ArrayList<SysMenu>());

        for (SysMenu it : sysMenus) {
            if(sysMenu.getId().longValue() == it.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,sysMenus));
            }
        }
        return sysMenu;
    }

}
