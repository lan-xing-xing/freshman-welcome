package org.graduation.freshmanwelcome.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.graduation.freshmanwelcome.DTO.DeductDTO;
import org.graduation.freshmanwelcome.DTO.GreenInfoDTO;
import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.common.Financial;
import org.graduation.freshmanwelcome.entity.FinancialAid;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;
import org.graduation.freshmanwelcome.mapper.FinancialAidMapper;
import org.graduation.freshmanwelcome.mapper.StudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinancialAidServiceImpl implements FinancialAidService {

    @Autowired
    private FinancialAidMapper financialAidMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object getFinancialAidSatus(Long studentId) {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("student_id",studentId);
        List<FinancialAid> financialAids = financialAidMapper.selectByMap(columnMap);
        if (financialAids.size() > 0){
            return financialAids.get(0).getAuditStatus();
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_FINANCIALAID);
        }
    }

    @Override
    public Object queryMajorFinancialAid(Integer collegeId, Integer majorId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FinancialAid> financialAidList = financialAidMapper.queryMajorFinancialAid(collegeId,majorId);
        PageInfo<FinancialAid> page = new PageInfo<>(financialAidList);
        return new ResultDTO(20000,page);
    }

    @Override
    public Object checkLoanFail(Long studentId) {
        if (financialAidMapper.checkLoanFail(studentId) > 0){
            String path = Financial.url + "updateStudentDeduce";
            DeductDTO deductDTO = new DeductDTO(studentId,0.0);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(path,deductDTO,String.class);
            if (responseEntity.getBody().contains("20000")){
                return ResultDTO.okOf();
            }else{
                return ResultDTO.errorOf(CustomizeErrorCode.UPDATE_EXCEPTION);
            }
        }else{
            return ResultDTO.errorOf(CustomizeErrorCode.UPDATE_EXCEPTION);
        }
    }

    @Override
    public Object checkLoanSuccess(DeductDTO deductDTO) {
        //修改贷款状态
        if (financialAidMapper.checkLoanSuccess(deductDTO) > 0){
            //修改财务学生减免金额
            String path = Financial.url + "updateStudentDeduce";
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(path,deductDTO,String.class);
            if (responseEntity.getBody().contains("20000")){
                return ResultDTO.okOf();
            }else{
                return ResultDTO.errorOf(CustomizeErrorCode.UPDATE_EXCEPTION);
            }
        }else{
            return ResultDTO.errorOf(CustomizeErrorCode.UPDATE_EXCEPTION);
        }
    }

    @Override
    public Object queryCheckInfo(Long studentId) throws Exception {
        String path = financialAidMapper.queryInfoPath(studentId);
        byte[] imageContent = fileToByte(new File(path));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    public static byte[] fileToByte(File img) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
    }

    @Override
    public Object postGreenInfo(GreenInfoDTO greenInfoDTO) {//学生提交绿色通道信息
        if (studentMapper.selectById(greenInfoDTO.getStudentId()) != null){
            Map<String,Object> columnMap = new HashMap<>();
            columnMap.put("student_id",greenInfoDTO.getStudentId());
            List<FinancialAid> financialAids = financialAidMapper.selectByMap(columnMap);
            //获取提交来的图片
            MultipartFile file = greenInfoDTO.getFile();
            if (financialAids.size() > 0){
                FinancialAid financialAid = financialAids.get(0);
                //该学生已经提交绿色通道信息，修改该条信息
                //图片处理
                if(file != null){
                    String fileName = file.getOriginalFilename();
                    int size = (int) file.getSize();
                    System.out.println(fileName + "-->" + size);
                    String path = "D:/FinancialImages" ;
                    File dest = new File(path + "/" + fileName);
                    if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                        dest.getParentFile().mkdir();
                    }
                    try {
                        file.transferTo(dest); //保存文件
                        financialAid.setDeduction(greenInfoDTO.getDeduction());
                        financialAid.setDocumentPath(path + "/" + fileName);
                        if (financialAidMapper.updateByStudentId(financialAid)){
                            //修改成功
                            return ResultDTO.okOf();
                        }else {
                            //修改失败
                            return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                        }
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                    }
                }else {
                    financialAid.setDeduction(greenInfoDTO.getDeduction());
                    if (financialAidMapper.updateByStudentId(financialAid)){
                        //修改成功
                        return ResultDTO.okOf();
                    }else {
                        //修改失败
                        return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                    }
                }
            }else {
                //该学生未提交绿色通道信息，新建一条信息
                if (file == null){
                    return ResultDTO.errorOf(CustomizeErrorCode.EMPTY_PICTURE);
                }else {
                    FinancialAid financialAid = new FinancialAid();
                    //图片处理
                    String fileName = file.getOriginalFilename();
                    int size = (int) file.getSize();
                    System.out.println(fileName + "-->" + size);
                    String path = "files" ;
                    File dest = new File(path + "/" + fileName);
                    if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                        dest.getParentFile().mkdir();
                    }
                    try {
                        file.transferTo(dest); //保存文件
                        BeanUtils.copyProperties(greenInfoDTO,financialAid);
                        financialAid.setDocumentPath(path + "/" + fileName);
                        if (financialAidMapper.insert(financialAid) > 0){
                            //插入成功
                            return ResultDTO.okOf();
                        }else {
                            //插入失败
                            return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                        }
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                    }
                }
            }
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
        }
    }
}
