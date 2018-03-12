package generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wenxiangzhou214164
 * @date 2017/10/23
 */
public class RepoServiceImplGenerator {

    private static String baseDir = "D:/workspace/idea/media-content-recom-engine/tag-recom-dao-api/src/main/java/com/sohu/content/tag/recom/dao";//todo 单斜杠形式
    private static String modelName = "TagOnlineTags";//todo without prefix or suffix

    private static String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    private static String serviceContent(String packageCode) {
        return packageCode + ".service;\n\n/**\n" +
                " * @author wenxiangzhou214164\n" +
                " * @date "+ date + "\n" +
                " */\n" +
                "public interface " + modelName + "Service {\n}";
    }

    private static String repoContent(String packageCode, String importModelCode) {
        return packageCode + ".dao;\n\n" + importModelCode + "import org.springframework.data.repository.CrudRepository;\n" +
                "\n" +
                "/**\n" +
                " * @author wenxiangzhou214164\n" +
                " * @date " + date + "\n" +
                " */\n" +
                "public interface " + modelName + "Repository extends CrudRepository<" + modelName + ", Integer> {\n" +
                "}";
    }

    private static String implContent(String packageCode, String importRepoCode, String importServiceCode) {
        return packageCode + ".service.impl;\n\n" + importServiceCode + importRepoCode +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "/**\n" +
                " * @author wenxiangzhou214164\n" +
                " * @date "+ date + "\n" +
                " */\n" +
                "@RestController\n" +
                "public class " + modelName + "ServiceImpl implements " + modelName + "Service {\n" +
                "\n" +
                "    private " + modelName + "Repository repository;\n" +
                "    @Autowired\n" +
                "    public " + modelName + "ServiceImpl(" + modelName + "Repository repository) {\n" +
                "        this.repository = repository;\n" +
                "    }\n" +
                "\n" +
                "}";
    }

    public static void main(String[] args) throws IOException {
        String serviceDir = baseDir + "/service/";
        String daoDir = baseDir.replaceAll("api", "server") + "/dao/";
        String implDir = baseDir.replaceAll("api", "server") + "/service/impl/";
        String packageCode = "package " + baseDir.split("java/")[1].replaceAll("/", ".");
        String importModelCode = "import " + baseDir.split("java/")[1].replaceAll("/", ".") + ".model." + modelName + ";\n";
        String importServiceCode = "import " + baseDir.split("java/")[1].replaceAll("/", ".") + ".service." + modelName + "Service;\n";
        String importRepoCode = "import " + baseDir.split("java/")[1].replaceAll("/", ".") + ".dao." + modelName + "Repository;\n";
        writeFile(serviceDir + modelName + "Service.java", serviceContent(packageCode));
        writeFile(daoDir + modelName + "Repository.java", repoContent(packageCode, importModelCode));
        writeFile(implDir + modelName + "ServiceImpl.java", implContent(packageCode, importRepoCode, importServiceCode));
    }

    private static File createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            mkDirRecursion(file.getParentFile());
        }
        file.createNewFile();
        return file;
    }

    private static void mkDirRecursion(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        }
        else {
            mkDirRecursion(file.getParentFile());
            file.mkdir();
        }
    }

    private static void writeFile(String filePath, String content) throws IOException {
        File file = createFile(filePath);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
        fw.close();
    }

}
