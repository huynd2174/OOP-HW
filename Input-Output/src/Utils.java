import java.io.*;
import java.nio.file.*;

public class Utils {

    /**
     * Phương thức đọc nội dung từ một tệp .txt trên ổ cứng.
     *
     * @param path tep
     * @return noi dung
     * @throws IOException ngoai le
     */
    public static String readContentFromFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        return content.toString();
    }

    /**
     * Phương thức ghi nội dung vào một tệp trên ổ cứng (ghi đè nội dung cũ).
     *
     * @param path    tep
     * @param content noi dung
     */
    public static void writeContentToFile(String path, String content) {
        Path filePath = Paths.get(path);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức bổ sung nội dung mới vào cuối tệp hiện tại.
     *
     * @param path    tep hien tai
     * @param content noi dung
     */
    public static void appendContentToFile(String path, String content) {
        Path filePath = Paths.get(path);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức tìm kiếm một tệp trong một thư mục.
     *
     * @param folderPath thu muc can tim
     * @param fileName   ten tep
     * @return tep can tim
     * @throws IOException ngoai le
     */
    public static Path findFileByName(String folderPath, String fileName) throws IOException {
        Path folder = Paths.get(folderPath);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folder, fileName)) {
            for (Path file : directoryStream) {
                if (Files.isRegularFile(file)) {
                    return file;
                }
            }
        }
        return null;
    }
}
