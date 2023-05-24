package com.example.employee.service.Impl;
import com.example.employee.Util.MessageTemplate;
import com.example.employee.Util.ValidateUtil;
import com.example.employee.model.ImageFile;
import com.example.employee.repository.ImageFileRepository;
import com.example.employee.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {

  private final MessageTemplate messageTemplate;

  @Value("${spring.image.banner}")
  private String bannerImageDir;

  @Value("${spring.image.profile}")
  private String profileImageDir;

  @Autowired
  ImageFileRepository imageFileRepository;

  @Override
  public String uploadFile(MultipartFile file, String fileType) throws IOException {
       String uploadDir = fileType.equals(ValidateUtil.FILE_STYLE.BANNER) ? bannerImageDir : profileImageDir;
    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> upload dir" + uploadDir);
    if (!file.isEmpty()) {
      try {
        File fileDir = new File(uploadDir);
        if (!fileDir.exists()) {
          fileDir.mkdirs();
          log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> make dir" + fileDir);
        }
        String filePath = uploadDir + System.currentTimeMillis();
        File dest = new File(filePath);
        //file.transferTo(dest);

        OutputStream os = new FileOutputStream(dest);

        float quality = 0.1f; // Change this as needed

        BufferedImage image = ImageIO.read(file.getInputStream());

        // get all image writers for JPG format
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        Iterator<ImageWriter> writers= ImageIO.getImageWritersByFormatName("png");
        if(extension.equals("png")){
          writers = ImageIO.getImageWritersByFormatName("png");
        }
        else if(extension.equals("jpg")){
          writers = ImageIO.getImageWritersByFormatName("jpg");
        }
        else if(extension.equals("jpeg")){
          writers = ImageIO.getImageWritersByFormatName("jpeg");
        }
        if (!writers.hasNext())
          throw new IllegalStateException("No writers found");

        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        // set compression quality
        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);

        writer.write(null, new IIOImage(image, null, null), param);
        //
        ImageFile img = new ImageFile();
        img.setFilePath(filePath);
        img.setFileType(fileType.equals(ValidateUtil.FILE_STYLE.BANNER) ? ValidateUtil.FILE_STYLE.BANNER : ValidateUtil.FILE_STYLE.PROFILE);
        imageFileRepository.save(img);
        String imageUrl = getImageUrl(filePath);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> img url" + imageUrl);
        return imageUrl;
      } catch (Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
      }
    } else {
      throw new IllegalArgumentException(ValidateUtil.MESSAGE_CODE.FILE_NOT_EXIST);
    }
    return null;
  }

  /**
   * * 3 BYTE đầu của một file GIF sẽ tương ứng 3 ký tự G - I - F nên chỉ cần check 3 ký tự đầu tương ứng thì sẽ là
   * * file GIF
   *
   * @param file
   * @return
   * @throws IOException
   */
  private Boolean isGif(MultipartFile file) throws IOException {
    InputStream inputStream = file.getInputStream();
    byte[] bytes = IOUtils.toByteArray(inputStream);
    if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') return true;
    return false;
  }

  private String getImageUrl(String filePath) {
    return filePath.substring(15);
  }
}
