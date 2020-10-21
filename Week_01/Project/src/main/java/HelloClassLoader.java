import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class HelloClassLoader extends ClassLoader {
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = reFormat(readFile());

        if (b == null) {
            throw new ClassNotFoundException();
        }

        return defineClass(name, b, 0, b.length);
    }

    /**
     * 从文件读出字节数组
     *
     * @return
     */
    private byte[] readFile() {
        File file = new File("D:\\Java训练营\\作业\\JAVA-000\\Week_01\\Project\\Hello.xlass");

        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 字节格式转换
     *
     * @param origin
     * @return
     */
    private byte[] reFormat(byte[] origin) {
        for (int i = 0; i < origin.length; ++i) {
            origin[i] = (byte) (255 - origin[i]);
        }

        return origin;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        HelloClassLoader loader = new HelloClassLoader();
        Class helloClazz = loader.findClass("Hello");

        Object helloObj = helloClazz.newInstance();
        Method helloMethod = helloClazz.getMethod("hello");

        System.out.println(helloMethod.invoke(helloObj));
    }
}