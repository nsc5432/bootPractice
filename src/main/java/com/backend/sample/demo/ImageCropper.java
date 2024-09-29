package com.backend.sample.demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCropper {

    public static void main(String[] args) throws IOException {
        // 이미지 파일 불러오기
        File inputFile = new File("/img/KakaoTalk_20240929_205628721.jpg");
        BufferedImage image = ImageIO.read(inputFile);

        // 원본 이미지의 너비와 높이
        int width = image.getWidth();
        int height = image.getHeight();

        // 각 부분의 너비와 높이 계산
        int partWidth = width / 2;
        int partHeight = height / 2;
        int heightOverlap = 30;

        // 이미지 4등분으로 자르기
        BufferedImage topLeft = image.getSubimage(0, 0, partWidth, partHeight + heightOverlap);
        BufferedImage topRight = image.getSubimage(partWidth, 0, partWidth, partHeight + heightOverlap);
        BufferedImage bottomLeft =
            image.getSubimage(0, partHeight - heightOverlap, partWidth, partHeight + heightOverlap);
        BufferedImage bottomRight =
            image.getSubimage(partWidth, partHeight - heightOverlap, partWidth, partHeight + heightOverlap);

        // 잘라낸 이미지 저장
        ImageIO.write(topLeft, "jpg", new File("topLeft.jpg"));
        ImageIO.write(topRight, "jpg", new File("topRight.jpg"));
        ImageIO.write(bottomLeft, "jpg", new File("bottomLeft.jpg"));
        ImageIO.write(bottomRight, "jpg", new File("bottomRight.jpg"));

        System.out.println("이미지 4등분으로 성공적으로 자름!");
    }
}
