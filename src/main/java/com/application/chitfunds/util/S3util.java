/*
 * package com.application.chitfunds.util;
 * 
 * import java.io.IOException; import java.io.InputStream;
 * 
 * import software.amazon.awssdk.awscore.exception.AwsServiceException; import
 * software.amazon.awssdk.core.exception.SdkClientException; import
 * software.amazon.awssdk.core.sync.RequestBody; import
 * software.amazon.awssdk.core.waiters.WaiterResponse; import
 * software.amazon.awssdk.services.s3.S3Client; import
 * software.amazon.awssdk.services.s3.model.HeadObjectRequest; import
 * software.amazon.awssdk.services.s3.model.HeadObjectResponse; import
 * software.amazon.awssdk.services.s3.model.PutObjectRequest; import
 * software.amazon.awssdk.services.s3.model.S3Exception; import
 * software.amazon.awssdk.services.s3.waiters.S3Waiter;
 * 
 * public class S3util {
 * 
 * private static final String BUCKET = "arn:aws:s3:::enstrokedemo";
 * 
 * public static boolean uploadFile(String fileName, InputStream inputStream)
 * throws S3Exception, AwsServiceException, SdkClientException, IOException {
 * Boolean res = false;
 * 
 * S3Client client = S3Client.builder().build();
 * 
 * PutObjectRequest request =
 * PutObjectRequest.builder().bucket(BUCKET).key(fileName).build();
 * 
 * client.putObject(request, RequestBody.fromInputStream(inputStream,
 * inputStream.available()));
 * 
 * S3Waiter waiter = client.waiter(); HeadObjectRequest waitRequest =
 * HeadObjectRequest.builder().bucket(BUCKET).key(fileName).build();
 * 
 * WaiterResponse<HeadObjectResponse> waitResponse =
 * waiter.waitUntilObjectExists(waitRequest);
 * 
 * waitResponse.matched().response().ifPresent(x -> { // run custom code that
 * should be executed after the upload file exists
 * System.out.println("file uploaded successFully");
 * 
 * });
 * 
 * return res; } }
 */