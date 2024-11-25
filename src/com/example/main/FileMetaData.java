package com.example.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileMetaData {
  private String fileName;
  private Path filePath;
  private long fileSize;
  private long lastModifiedTime;
  private long lastAccessTime;

  FileMetaData(String fileName, Path filePath, long fileSize, long lastModifiedTime, long lastAccessTime) {
    this.fileName = fileName;
    this.filePath = filePath;
    this.fileSize = fileSize;
    this.lastModifiedTime = lastModifiedTime;
    this.lastAccessTime = lastAccessTime;
  }

  String getFileInfo() {
    return "File Name: " + fileName + "\n" +
           "File Path: " + filePath + "\n" +
           "File Size: " + fileSize + " bytes\n" +
           "Last Modified Time: " + lastModifiedTime + "\n" +
           "Last Access Time: " + lastAccessTime;
  }

  static FileMetaData from(Path path) throws IOException {
    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
    return new FileMetaData(
      path.getFileName().toString(),
      path,
      attr.size(),
      attr.lastModifiedTime().toMillis(),
      attr.lastAccessTime().toMillis()
    );
  }
}
