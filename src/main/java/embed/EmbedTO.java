package embed;

import java.util.List;

/**
 * Created by ipseeta on 6/9/17.
 */
public class EmbedTO {
    private String title;
    private String description;
    private String ogimage;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private List<String> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOgimage() {
        return ogimage;
    }

    public void setOgimage(String ogimage) {
        this.ogimage = ogimage;
    }

}
