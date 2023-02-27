package es.codeurjc.mca.events.image;

import java.util.UUID;
import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service("storageService")
@Profile("production")
public class S3ImageService implements ImageService {

    public static S3Client s3Client;

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    @Value("${amazon.s3.region}")
    private String region;

    @PostConstruct
    public void init() {
        s3Client = S3Client.builder()
                .region(Region.of(region))
                .build();
        if (s3Client == null) {
            s3Client.createBucket(
                CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build()
            );
        }
    }

    @Override
    public String createImage(MultipartFile multiPartFile) {
       String fileName = "image_" + UUID.randomUUID() + "_" +multiPartFile.getOriginalFilename();
       File file = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        try {
            multiPartFile.transferTo(file);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't save image locally", ex);
        }
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .acl(ObjectCannedACL.PUBLIC_READ)
            .key(fileName)
            .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
        return fileName;
    }

    @Override
    public void deleteImage(String image) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(image)
            .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

}
