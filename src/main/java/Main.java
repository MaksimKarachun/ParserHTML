import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception{

        File directory = new File("image/");
        if (!directory.exists())
            directory.mkdir();

        //args[o] = https://lenta.ru/
        Document doc = Jsoup.connect(args[0]).get();
        Elements elements = doc.select("img.g-picture");

        Pattern p = Pattern.compile("src=\\\"[^\\\"]+\\\"");
        Matcher m;

        for (Element element : elements){
            m = p.matcher(element.toString());
            if (m.find())
                loadImageToThePath(m.group(), directory);

            }
        }


        public static void loadImageToThePath(String path, File dir) throws Exception{
            try {
                BufferedImage image;
                URL url = new URL(path.substring(path.indexOf("\"") + 1, path.lastIndexOf("\"")));
                image = ImageIO.read(url);
                if (image != null){
                    ImageIO.write(image, "png",
                            new File(dir.getAbsolutePath() + "/"
                                    + path.substring(path.lastIndexOf("/") + 1, path.length() - 5)+ ".png"));
                }
            }
            catch (FileNotFoundException e) {
                e.getMessage();
            }
        }

    }
