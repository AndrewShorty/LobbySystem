package org.spigotmc.lobbysystem.api;

import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FileSystem {
    public static final void copyFileToDirectory(final File file, final File to) throws IOException {
        if (!to.exists()) {
            to.mkdirs();
        }
        final File n = new File(to.getAbsolutePath() + "/" + file.getName());
        Files.copy(file.toPath(), n.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static final void copyFilesInDirectory(final File from, final File to) throws IOException {
        if (!to.exists()) {
            to.mkdirs();
        }
        for (final File file : from.listFiles()) {
            if (file.isDirectory()) {
                copyFilesInDirectory(file, new File(to.getAbsolutePath() + "/" + file.getName()));
            }
            else {
                final File n = new File(to.getAbsolutePath() + "/" + file.getName());
                Files.copy(file.toPath(), n.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public static final void insertData(final String from, final String to) {
        final InputStream localInputStream = FileSystem.class.getClassLoader().getResourceAsStream(from);
        try {
            Files.copy(localInputStream, Paths.get(to, new String[0]), new CopyOption[0]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rewriteFileUtils(final File file, final String host) throws Exception {
        file.setReadable(true);
        final FileInputStream in = new FileInputStream(file);
        final List<String> liste = new CopyOnWriteArrayList<String>();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        boolean value = false;
        String input;
        while ((input = reader.readLine()) != null) {
            if (value) {
                liste.add("  host: " + host + "\n");
                value = false;
            }
            else if (input.startsWith("  query_enabled")) {
                liste.add(input + "\n");
                value = true;
            }
            else {
                liste.add(input + "\n");
            }
        }
        file.delete();
        file.createNewFile();
        file.setReadable(true);
        final FileOutputStream out = new FileOutputStream(file);
        final PrintWriter w = new PrintWriter(out);
        for (final String wert : liste) {
            w.write(wert);
            w.flush();
        }
        reader.close();
        w.close();
    }
}