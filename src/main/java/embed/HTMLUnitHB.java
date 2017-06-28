package embed;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;

import java.util.Map;


/**
* Created by ipseeta on 6/8/17.
*/
public class HTMLUnitHB {
    public static void main(String args[]) {
        try {
            //final String UA="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
            String UA ="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
            WebClient webClient = new WebClient();
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            //UA="Mozilla/5.0";
            webClient.addRequestHeader("User-Agent",UA);
            CredentialsProvider credentialsProvider = new DefaultCredentialsProvider();
            AuthScope authScope = new AuthScope("static.licdn.com",443);
            Credentials credentials = new UsernamePasswordCredentials("ipseeta.pkar@gmail.com","!PapaMama20June");
            credentialsProvider.setCredentials(authScope,credentials);
            webClient.setCredentialsProvider(credentialsProvider);
            //webClient.addRequestHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            webClient.addRequestHeader("Accept-Encoding","gzip, deflate, br");
            webClient.addRequestHeader("Accept-Language","en-IN,or-IN;q=0.8,or;q=0.6,en-GB;q=0.4,en-US;q=0.2,en;q=0.2");
            webClient.addRequestHeader("Upgrade-Insecure-Requests","1");
            webClient.addRequestHeader("authority","www.linkedin.com");
            webClient.addRequestHeader("scheme","https");
            webClient.addRequestHeader("Connection","keep-alive");

            HtmlPage page = webClient.getPage("https://github.com/Ipseeta/");
            DomNodeList<DomElement> inputs = page.getElementsByTagName("meta");
            for (int i = 0; i < inputs.size(); i++) {
                DomElement domElement = inputs.get(i);
                Map<String, DomAttr> map = domElement.getAttributesMap();
                if (map.containsKey("property")) {
                    DomAttr titleDom = map.get("property");
                    if ("og:title".equalsIgnoreCase(titleDom.getTextContent())) {
                        System.out.println("Title::: " + map.get("content").getValue());
                    } else if ("og:image".equalsIgnoreCase(titleDom.getTextContent())) {
                        System.out.println("OG:Image::: " + map.get("content").getValue());
                    }
                }
                if (map.containsKey("name")) {
                    DomAttr titleDom = map.get("name");
                    if ("description".equalsIgnoreCase(titleDom.getTextContent())) {
                        System.out.println("Description::: " + map.get("content").getValue());
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
