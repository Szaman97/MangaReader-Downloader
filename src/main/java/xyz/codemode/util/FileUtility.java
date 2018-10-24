package xyz.codemode.util;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileUtility {

    /**
     * Saves text file
     * @param path path of a file to save
     * @param content content of file to save
     * @param charsetName encoding of a file
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException when encoding is incorrect
     */
    public static void saveTextFile(String path, String content, String charsetName) throws FileNotFoundException, UnsupportedEncodingException {
        saveTextFile(new File(path),content,charsetName);
    }

    /**
     * Saves text file
     * @param file file to save
     * @param content content of file to save
     * @param charsetName encoding of a file
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException when encoding is incorrect
     */
    public static void saveTextFile(File file, String content, String charsetName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter output = new PrintWriter(file, charsetName);
        output.print(content);
        output.close();
    }

    /**
     * Saves text file
     * @param path path of a file to save
     * @param lines List to save; every position in list is write to separate line in file
     * @param charsetName encoding of a file
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException when encoding is incorrect
     */
    public static void saveTextFile(String path, List<?> lines, String charsetName) throws FileNotFoundException, UnsupportedEncodingException {
        saveTextFile(new File(path),lines,charsetName);
    }

    /**
     * Saves text file
     * @param file file to save
     * @param lines List to save; every position in list is write to separate line in file
     * @param charsetName encoding of a file
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException when encoding is incorrect
     */
    public static void saveTextFile(File file, List<?> lines, String charsetName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter output = new PrintWriter(file, charsetName);
        Iterator<?> iter = lines.iterator();
        while(iter.hasNext()) {
            output.println(iter.next());
        }
        output.close();
    }

    /**
     * Saves text file with UTF-8 encoding
     * @param path path of a file to save
     * @param content content of file to save
     * @throws FileNotFoundException
     */
    public static void saveTextFile(String path, String content) throws FileNotFoundException {
        try {
            saveTextFile(new File(path), content, "UTF-8");
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    /**
     * Saves text file with UTF-8 encoding
     * @param file file to save
     * @param content content of file to save
     * @throws FileNotFoundException
     */
    public static void saveTextFile(File file, String content) throws FileNotFoundException {
        try {
            saveTextFile(file, content, "UTF-8");
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    /**
     * Saves text file with UTF-8 encoding
     * @param path path of a file to save
     * @param lines List to save; every position in list is write to separate line in file
     * @throws FileNotFoundException
     */
    public static void saveTextFile(String path, List<?> lines) throws FileNotFoundException {
        try {
            saveTextFile(new File(path), lines, "UTF-8");
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    /**
     * Saves text file with UTF-8 encoding
     * @param file file to save
     * @param lines List to save; every position in list is write to separate line in file
     * @throws FileNotFoundException
     */
    public static void saveTextFile(File file, List<?> lines) throws FileNotFoundException {
        try {
            saveTextFile(file, lines, "UTF-8");
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }


    /**
     * Loads file as a String
     * @param path path of a file to load
     * @param charsetName encoding of a file
     * @return String with entire file content
     * @throws IOException
     */
    public static String loadAsString(String path, String charsetName) throws IOException {
        return loadAsString(new File(path), charsetName);
    }

    /**
     * Loads file as a String
     * @param file file to load
     * @param charsetName encoding of a file
     * @return String with entire file content
     * @throws IOException
     */
    public static String loadAsString(File file, String charsetName) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));

        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while((line = input.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        } finally {
            input.close();
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Loads file as a String with UTF-8 encoding
     * @param path path of a file to load
     * @return String with entire file content
     * @throws IOException
     */
    public static String loadAsString(String path) throws IOException {
        return loadAsString(new File(path));
    }

    /**
     * Loads file as a String with UTF-8 encoding
     * @param file file to load
     * @return String with entire file content
     * @throws IOException
     */
    public static String loadAsString(File file) throws IOException {
        return loadAsString(file,"UTF-8");
    }


    /**
     * Loads file as a List of Strings
     * @param path path of a file to load
     * @param charsetName encoding of a file
     * @return List of Strings with entire file content; every position in list is equal to separate line in file (without '\n' character)
     * @throws IOException
     */
    public static List<String> loadAsList(String path, String charsetName) throws IOException {
        return loadAsList(new File(path), charsetName);
    }

    /**
     * Loads file as a List of Strings
     * @param file file to load
     * @param charsetName encoding of a file
     * @return List of Strings with entire file content; every position in list is equal to separate line in file (without '\n' character)
     * @throws IOException
     */
    public static List<String> loadAsList(File file, String charsetName) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
        List<String> list = new LinkedList<>();
        String line;
        try {
            while((line = input.readLine()) != null) {
                list.add(line);
            }
        } finally {
            input.close();
        }

        return list;
    }

    /**
     * Loads file as a List of Strings with UTF-8 encoding
     * @param path path of a file to load
     * @return List of Strings with entire file content; every position in list is equal to separate line in file (without '\n' character)
     * @throws IOException
     */
    public static List<String> loadAsList(String path) throws IOException {
        return loadAsList(new File(path));
    }

    /**
     * Loads file as a List of Strings with UTF-8 encoding
     * @param file file to load
     * @return List of Strings with entire file content; every position in list is equal to separate line in file (without '\n' character)
     * @throws IOException
     */
    public static List<String> loadAsList(File file) throws IOException {
        return loadAsList(file,"UTF-8");
    }

    /**
     * Loads stream as a List of Strings
     * @param is InputStream
     * @param encoding encoding of a stream
     * @return List of Strings with entire stream content; every position in list is equal to stream part splitted by '\n' character
     */
    public static List<String> loadAsList(InputStream is, String encoding) {
        Scanner scanner = new Scanner(is, encoding);
        List<String> list = new LinkedList<>();

        while(scanner.hasNext()) {
            list.add(scanner.nextLine());
        }

        scanner.close();
        return list;
    }

    /**
     * Loads stream as a List of Strings with UTF-8 encoding
     * @param is InputStream
     * @return List of Strings with entire stream content; every position in list is equal to stream part splitted by '\n' character
     */
    public static List<String> loadAsList(InputStream is) {
        return loadAsList(is, "UTF-8");
    }
}
