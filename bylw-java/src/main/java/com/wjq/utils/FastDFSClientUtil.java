package com.wjq.utils;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * fastFdfs工具类
 *
 * @author wjq
 * @date 2021/12/12 18:17
 **/

@Component
public class FastDFSClientUtil {

    @Value("${fdfs.reqHost}")
    private String reqHost;

    @Value("${fdfs.reqPort}")
    private String reqPort;

    @Autowired
    private FastFileStorageClient storageClient;

    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                                                       FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

//   public void delFile(String filePath) {
//	   storageClient.deleteFile(filePath);
//   }
//
//   public InputStream download(String groupName, String path ) {
//	   InputStream ins =  storageClient.downloadFile(groupName, path, new DownloadCallback<InputStream>(){
//		@Override
//		public InputStream recv(InputStream ins) throws IOException {
//			// 将此ins返回给上面的ins
//			return ins;
//		}}) ;
//	   return ins ;
//   }

    /**
     * 封装文件完整URL地址
     *
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
        return "http://" + reqHost + ":" + reqPort + "/" + storePath.getFullPath();
    }
}