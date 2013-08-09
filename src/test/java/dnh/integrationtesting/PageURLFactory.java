package dnh.integrationtesting;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * author: Danesh Harjani
 */

// Returns the URL for a requested page depending on the current environment (defined in cucumber.xml)
@Component
public class PageURLFactory {

    @Resource
    Map<Enum<Pages>,String> pageMap;

    public Map<Enum<Pages>, String> getPageMap() {
        return pageMap;
    }

    public void setPageMap(Map<Enum<Pages>, String> pageMap) {
        this.pageMap = pageMap;
    }

    public String getPageURL(String page){
        return pageMap.get(Pages.valueOf(page));
    }
}
