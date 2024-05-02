package com.solution.demo.framework.functional.modules.ihm;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;


public class HomePageModule {
    /**
     * Go to special Page frm its Name (TPG Home Page)
     * @param pageName
     */
    public static void goToPage(String pageName) {
        Reporter.getInstance().newModule();
        ItemAction.click(new Item("//a[normalize-space()='" + pageName + "']", pageName));
        Reporter.getInstance().closeModule();
    }
}
