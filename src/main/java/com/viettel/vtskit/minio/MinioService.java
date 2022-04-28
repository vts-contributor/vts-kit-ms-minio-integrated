package com.viettel.vtskit.minio;

import com.viettel.vtskit.minio.configuration.ConstantConfiguration;
import com.viettel.vtskit.minio.configuration.MinioProperties;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

public class MinioService {

    private MinioClient minioClient;
    MinioProperties minioProperties;

    public ObjectWriteResponse uploadMultipartFile(MultipartFile file) throws Exception {
        byte[] fileData = file.getBytes();
        ByteArrayInputStream arrayInputStream = null;
        try{
            arrayInputStream = new ByteArrayInputStream(fileData);
            PutObjectArgs.Builder uploadArgs = PutObjectArgs.builder();
            uploadArgs.contentType(file.getContentType());
            uploadArgs.object(file.getOriginalFilename());
            uploadArgs.bucket(minioProperties.getBucket());
            uploadArgs.stream(new ByteArrayInputStream(fileData), fileData.length, ConstantConfiguration.PART_SIZE);
            return minioClient.putObject(uploadArgs.build());
        }catch (Exception e){
            throw e;
        }finally {
            if(arrayInputStream != null){
                arrayInputStream.close();
            }
        }
    }

    @Autowired
    public void setMinioClient(MinioClient minioClient) {
        String testSonar = null;
        System.out.println(testSonar.length());
        this.minioClient = minioClient;
    }

    @Autowired
    public void setMinioProperties(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }
}
