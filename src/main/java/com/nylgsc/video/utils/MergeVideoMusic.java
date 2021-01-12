package com.nylgsc.video.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeVideoMusic {

    public static final String ffmpegEXE = "D:\\ffmpeg\\bin\\ffmpeg.exe";



    public static void convertor(String videoInputPath,String musicInputPath,String seconds, String videoOutputPath){
//		ffmpeg -i input.mp4 -y output.avi
        try {
            List<String> command = new ArrayList<>();
            command.add(ffmpegEXE);
            command.add("-i");
            command.add(videoInputPath);
            command.add("-i");
            command.add(musicInputPath);
            command.add("-c:v");
            command.add("aac");
            command.add("-strict");
            command.add("experimental");
            command.add("-map");
            command.add("0:v:0");
            command.add("-map");
            command.add("1:a:0");
            command.add(videoOutputPath);
            System.out.println(command.toString());

            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void test(String videoInputPath,String musicInputPath,String seconds, String videoOutputPath){
        Process process = null;
        try {
            String command =ffmpegEXE + " -i " + videoInputPath + " -i " + musicInputPath + " -c:v copy -c:a aac -strict experimental " +
                    " -map 0:v:0 -map 1:a:0 "
                    + " -y " + videoOutputPath;
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            // 使用这种方式会在瞬间大量消耗CPU和内存等系统资源，所以这里我们需要对流进行处理
            InputStream errorStream = process.getErrorStream();
            InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
            BufferedReader br = new BufferedReader(inputStreamReader);

            String line = "";
            while ((line = br.readLine()) != null) {
            }
            if (br != null) {
                br.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (errorStream != null) {
                errorStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //去除音轨，不然背景音乐无法加上
    public static void removeBgm(String videoInputPath,String videoOutputPath){
        try {
            List<String> command = new ArrayList<>();
            command.add(ffmpegEXE);
            command.add("-i");
            command.add(videoInputPath);
            command.add("-c:v");
            command.add("copy");
            command.add("-an");
            command.add(videoOutputPath);
            System.out.println(command.toString());
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String input = "D:/video/1325631114622054402/video/wx4774d162a4a83c03o6zAJswJ-YBlHnLlaKMa2BgssIUkXS2KRDQhtIvY24d2618b304614f3dee99a252c9fd02b.mp4";
        String music = "D:/video/music/hello.mp3";
        String second = "70";
        String outPut = "D:/video/1325631114622054402/video/11111.mp4";
        String outPut2 = "D:/video/1325631114622054402/video/320b963b82f1456da53461f2ce25e5f0.mp4";
//        MergeVideoMusic.removeBgm(input,outPut);

        MergeVideoMusic.convertor(input,music,second,outPut);

//        List<String> command = new ArrayList<>();
//        command.add(ffmpegEXE);
//        command.add("-i");
//        command.add("D:\\room.mp4");
//        command.add("-c:v");
//        command.add("copy");
//        command.add("-an");
//        command.add("D:\\no_audio.mp4");
//        System.out.println(command.toString());
//
////        List<String> command = new ArrayList<>();
//        command.add(ffmpegEXE);
//        command.add("-i");
//        command.add("D:/no_audio.mp4");
//        command.add("-i");
//        command.add("D:/hello.mp3");
//        command.add("-c");
//        command.add("copy");
//        command.add("-t");
//        command.add("70");
//        command.add("D:/afterMerge.mp4");
//        System.out.println(command.toString());
//
//        ProcessBuilder builder = new ProcessBuilder(command);
//        try {
//            Process process = builder.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
