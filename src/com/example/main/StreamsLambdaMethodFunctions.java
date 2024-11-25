package com.example.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.example.main.FileMetaData;

public class StreamsLambdaMethodFunctions {
  public static void main(String[] args) {
    Path rootDir = Paths.get("com/example/files");

    String fileExtension = ".txt";

    try {
      List<FileMetaData> metaDataList = Files.walk(rootDir)
          .filter(Files::isRegularFile)
          .filter(path -> path.toString().endsWith(fileExtension))
          .map(path -> {
            try {
              return FileMetaData.from(path);
            } catch (IOException e) {
              e.printStackTrace();
              return null;
            }
          })
          .filter(fileMetaData -> fileMetaData != null)
          .collect(Collectors.toList());

      metaDataList.forEach(fileMetaData -> {
        System.out.println(fileMetaData.getFileInfo());
      });

      // Output:
      // File Name: loremipsum.txt
      // File Path: com/example/files/loremipsum.txt
      // File Size: 2270 bytes
      // Last Modified Time: 1732554083512
      // Last Access Time: 1732554112467
      // File Name: cupcakeipsum.txt
      // File Path: com/example/files/cupcakeipsum.txt
      // File Size: 4161 bytes
      // Last Modified Time: 1732554601333
      // Last Access Time: 1732554605241

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}