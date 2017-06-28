package embed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ipseeta on 6/9/17.
 */

public class JBrowserHB {
    public static void main(String[] args) {


        long lStartTime = System.nanoTime();
        JBrowserDriver driver = new JBrowserDriver(Settings.builder().timezone(Timezone.AMERICA_NEWYORK).userAgent(UserAgent.CHROME).build());

       driver.get("https://github.com/Ipseeta");

        By by = new By.ByTagName("meta");
        List<WebElement> inputs = driver.findElements(by);
        EmbedTO embedTO = new EmbedTO();
        ObjectMapper mapper = new ObjectMapper();
        Boolean[] check = new Boolean[3];
        check[0] = check[1] = check[2] = false;
        Map<String,Boolean> hashMap = new HashMap<String,Boolean>();
        for (int i = 0; i < inputs.size(); i++) {
            WebElement webElement = inputs.get(i);
            if (!hashMap.containsKey("title") || !hashMap.containsKey("image") || !hashMap.containsKey("desc")) {
                if ((!hashMap.containsKey("title") && "og:title".equalsIgnoreCase(webElement.getAttribute("property")) || "og:title".equalsIgnoreCase(webElement.getAttribute("name")))) {
                    embedTO.setTitle(webElement.getAttribute("content"));
                    hashMap.put("title", true);
                } else if (!hashMap.containsKey("image") && ("og:image".equalsIgnoreCase(webElement.getAttribute("property")) || "twitter:image".equalsIgnoreCase(webElement.getAttribute("name")))) {
                    embedTO.setOgimage(webElement.getAttribute("content"));
                    hashMap.put("image", true);
                }
                if (!hashMap.containsKey("desc") && ("description".equalsIgnoreCase(webElement.getAttribute("name")) || "og:description".equalsIgnoreCase(webElement.getAttribute("property")))) {
                    embedTO.setDescription(webElement.getAttribute("content"));
                    hashMap.put("desc", true);
                }
            }else if (hashMap.containsKey("title") && hashMap.containsKey("image") && hashMap.containsKey("desc")) break;
        }

        if(!StringUtils.isNotBlank((embedTO.getOgimage()))){
            by = new By.ByTagName("img");
            inputs = driver.findElements(by);
            String imageSrc = "";
            List<String> images = new ArrayList<String>();
            for (int i = 0; i < inputs.size(); i++) {
                WebElement webElement = inputs.get(i);
                imageSrc = webElement.getAttribute("src");
                if(StringUtils.isNotBlank(imageSrc))
                    images.add(imageSrc);
            }
            embedTO.setImages(images);
        }
        if(!StringUtils.isNotBlank((embedTO.getDescription()))){
            by = new By.ByTagName("p");
            embedTO.setDescription(driver.findElements(by).get(0).getText());
        }
        if(!StringUtils.isNotBlank((embedTO.getTitle())))
            embedTO.setTitle(driver.getTitle());

        long lEndTime = System.nanoTime();
        long output = lEndTime - lStartTime;
        System.out.println("Elapsed time in milliseconds: " + output / 1000000);
        String jsonInString = null;
        try {
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(embedTO);
            System.out.println(jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        driver.close();

    }

}
