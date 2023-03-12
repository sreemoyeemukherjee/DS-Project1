package ds.project1task2;

// Emoji POJO class
public class Emoji {
    private String name;
    private String code;
    private String emoji;
    private String unicode;
    private String image;
    public Emoji(String name, String code, String emoji, String unicode, String image) {
        this.name = name;
        this.code = code;
        this.emoji = emoji;
        this.unicode = unicode;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getUnicode() {
        return unicode;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
