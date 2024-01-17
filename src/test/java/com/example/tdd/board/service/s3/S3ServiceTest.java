package com.example.tdd.board.service.s3;

import com.example.tdd.board.web.dto.common.Image;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
class S3ServiceTest {

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    @Autowired
    private S3Service s3Service;

    @Test
    @DisplayName("amazon S3 이미지 업로드를 테스트한다.")
    public void s3ImageUpload() {
        // given
        String name = "image.jpeg";
        String originName = "image.jpeg";
        String contentType = "image/jpeg";
        MockMultipartFile uploadFile = new MockMultipartFile(name, originName, contentType, new byte[1]);

        // when
        Image image = s3Service.uploadImage(uploadFile);

        // then
        Assertions.assertThat(image.getOriginName()).isEqualTo(originName);
    }
}