package com.example.tdd.board.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.tdd.board.exception.ImageUploadException;
import com.example.tdd.board.web.dto.common.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName; // 버킷명

    private String changedImageName(String originName) {
        String today = LocalDateTime.now().toString();
        String random = UUID.randomUUID().toString();
        return today + random;
    }

    private String uploadImageToS3(MultipartFile image) {
        String originName = image.getOriginalFilename();
        String ext = originName.substring(originName.lastIndexOf("."));
        String changedName = this.changedImageName(originName);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/" + ext);

        try {
            PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(
                    bucketName, changedName, image.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new ImageUploadException("Image Upload Failure");
        }

        return amazonS3.getUrl(bucketName, changedName).toString();
    }

    public Image uploadImage(MultipartFile image) {
        String originName = image.getOriginalFilename();
        String storedImageUrlPath = this.uploadImageToS3(image);

        Image newImage = Image.builder()
                .originName(originName)
                .storedImageUrlPath(storedImageUrlPath)
                .build();
        return newImage;
    }
}
