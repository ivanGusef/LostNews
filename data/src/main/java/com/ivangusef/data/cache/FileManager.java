package com.ivangusef.data.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Helper class to do operations on regular files/directories.
 */
@Singleton
public class FileManager {

    private static final String TAG = FileManager.class.getSimpleName();

    @Inject
    public FileManager() {
    }

    /**
     * Writes a file to Disk. This is an I/O operation and this method executes in the main thread, so it is recommended to perform this operation
     * using another thread.
     *
     * @param file The file to write to Disk.
     */
    public void writeToFile(@NonNull final File file, @NonNull final String fileContent) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(fileContent);
        } catch (IOException e) {
            Log.w(TAG, e.getMessage(), e);
        } finally {
            closeStream(writer);
        }
    }

    /**
     * Reads a content from a file. This is an I/O operation and this method executes in the main thread, so it is recommended to perform the
     * operation using another thread.
     *
     * @param file The file to read from.
     * @return A string with the content of the file.
     */
    @NonNull
    public String readFromFile(@NonNull final File file) {
        final StringBuilder fileContentBuilder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                fileContentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            Log.w(TAG, e.getMessage(), e);
        } finally {
            closeStream(reader);
        }

        return fileContentBuilder.toString();
    }

    /**
     * Returns a boolean indicating whether this file can be found on the underlying file system.
     *
     * @param file The file to check existence.
     * @return true if this file exists, false otherwise.
     */
    public boolean exists(File file) {
        return file.exists();
    }

    /**
     * Warning: Deletes the content of a directory. This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform the operation using another thread.
     *
     * @param directory The directory which its content will be deleted.
     */
    public void clearDirectory(@NonNull final File directory) {
        if (!directory.exists()) {
            return;
        }

        for (File file : directory.listFiles()) {
            if (!file.delete()) {
                Log.w(TAG, "File has not been deleted: " + file.getPath());
            }
        }
    }

    /**
     * Write a value to a preferences file.
     *
     * @param context            {@link android.content.Context} to retrieve android preferences.
     * @param preferenceFileName A file name reprensenting where data will be written to.
     * @param key                A string for the key that will be used to retrieve the value in the future.
     * @param value              A long representing the value to be inserted.
     */
    public void writeToPreferences(@NonNull final Context context, @NonNull final String preferenceFileName, @NonNull final String key,
                                   final long value) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * Get a value from a preferences file.
     *
     * @param context            {@link android.content.Context} to retrieve android preferences.
     * @param preferenceFileName A file name representing where data will be get from.
     * @param key                A key that will be used to retrieve the value from the preference file.
     * @return A long representing the value retrieved from the preferences file.
     */
    public long getFromPreferences(@NonNull final Context context, @NonNull final String preferenceFileName, @NonNull final String key) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }

    private void closeStream(@Nullable final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.v(TAG, "Exception during closing stream: " + e.getMessage(), e);
            }
        }
    }
}
