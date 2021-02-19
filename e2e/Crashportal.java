age com.dt.crash.portal.adapter.web.in;
import com.dt.crash.portal.core.bean.CrashDumpModel;
import com.dt.crash.portal.adapter.constants.CrashDumpModelDTO;
import com.dt.crash.portal.core.port.in.ICrashDumpUseCase;
import com.dt.crash.portal.adapter.constants.S3ServiceConstants;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.dt.core.platform.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Slf4j
@RestController
@CrossOrigin
public class CrashPortalController {
    @Autowired
    private ICrashDumpUseCase iCrashDumpUseCase;
    //@Autowired
    //private CrashDumpService crashDumpService;

    public CrashPortalController() {
    }

    /**
     * This is Post Rest controller method which uploads the crashdump file to S3 bucket
     *
     * RequestParam  file  crashdump file in MultipartFile format
     * return string    Returns file uploaded message
     */
    @PostMapping("/v1/crashUpload")
    @ResponseBody
    public ResponseEntity<String> crashFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("PostMapping(/crashUpload)");

        validateCrashFile(file);
        iCrashDumpUseCase.miniDumpUpload(file);
        return ResponseEntity.status(HttpStatus.OK) .body("Crashdump file uploaded successfully to S3");
    }

    /**
     * This is Post Rest controller method which uploads the symbol file to S3 bucket
     *
     * param  filename  Filename of symbol file to upload
     * return string    Returns file uploaded message
     */
    @PostMapping("/v1/symFilesUpload")
    @ResponseBody
    public ResponseEntity<String> symFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("PostMapping(/symFilesUpload)");

        validateSymbolFile(file);
        iCrashDumpUseCase.miniDumpUpload(file);
        return ResponseEntity.status(HttpStatus.OK) .body("Symbol file uploaded successfully to S3");
    }

    /**
     * This is Delete Rest controller method which deletes all the crash dumps for the given
     * device from S3 bucket
     *
     * param  String macId  mac id of the the device
     * return string    number of files deleted
     */
    @DeleteMapping("/v1/crashDump")
    public ResponseEntity<String> deleteCrashDumps(@QueryParam("macId") String macId) throws IOException {
        log.info("DeleteMapping(/crashDump)");

        validateMacId(macId);
        int numberOfFilesDeleted = iCrashDumpUseCase.deleteCrashDumps(macId);
        return ResponseEntity.status(HttpStatus.OK).body(numberOfFilesDeleted + " file deleted successfully");
    }

    /**
     * This is Get Rest controller method which lists all crash dump files from the s3 bucket
     *
     * param  None
     * return List    Returns list of the crash dumps stored in S3 bucket
     */
    @GetMapping(value={"/v1/crashReports")
    public List<CrashDumpModelDTO> getCrashDumpFileNames(@RequestParam(value="uUIDName",required=false) String uUIDName,
    @RequestParam(value="MacId",required=false) String MacId,@RequestParam(value="DateOfPost",required=false) String DateOfPost) {

        log.info("GetMapping(/crashReports)");
        List <CrashDumpModel>  crashDumpModelList = iCrashDumpUseCase.getAllCrashDumpInformation();
        List <CrashDumpModelDTO>  crashDumpModelDTOList = new ArrayList<>();
        
        if(!uUIDName.isEmpty()||!MacId.isEmpty()||!DateOfPost.isEmpty()){

            if(uUIDName.isEmpty()&&MacId.isEmpty()&&DateOfPost.isEmpty()){

                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    
                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((uUIDName==UUID) && (MacId==MACID)
                    && (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);

                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }else if((uUIDName==UUID) || (MacId==MACID) || (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);

                    /*    BeanUtils.copyProperties(crashDumpModelList, crashDumpModelDTOList);  You can try this instead of 
                           line 38 to 42 */
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!uUIDName.isEmpty()&&!MacId.isEmpty()) {
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    
                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((uUIDName==UUID) && (MacId==MACID)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }else if((uUIDName==UUID) || (MacId==MACID)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!MacId.isEmpty()&&!DateOfPost.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((MacId==MACID) && (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }else if((MacId==MACID) || (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!uUIDName.isEmpty()&&!DateOfPost.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((uUIDName==UUID) && (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }else if((uUIDName==UUID) || (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!uUIDName.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((uUIDName==UUID)
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!MacId.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((MacId==MACID)
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!DateOfPost.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((DateOfPost==DATEOFPOST)
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }
        }else{
            List <CrashDumpModelDTO>  crashDumpModelDTOList = new ArrayList<>();
            for(CrashDumpModel crashDumpModel:crashDumpModelList){
                CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();
                crashDumpModelDTO.setDateOfPost(crashDumpModel.getDateOfPost());
                crashDumpModelDTO.setImageName(crashDumpModel.getImageName());
                crashDumpModelDTO.setMacId(crashDumpModel.getMacId());
                crashDumpModelDTO.setuUIDName(crashDumpModel.getuUIDName());
                crashDumpModelDTO.setVersionId(crashDumpModel.getVersionId());
    
                crashDumpModelDTOList.add(crashDumpModelDTO);
            }
            return crashDumpModelDTOList;
        }


    /**
     * This is Get Rest controller method which generates crash report for the
     * supplied crash dump file.
     *
     * @RequestParam  uUIDName Details of the crash dump file
     * RequestParam     macId Mac id of the cpe device
     * RequestParam     dateOfPost  date of crash dump generated
     * RequestParam     imageName   name of the imgae
     * RequestParam     software version
     * @return String    Returns crash report
     */

    @Produces("text/plain")
    @GetMapping("/v1/crashReport")
    public ResponseEntity<String> getCrashReportByUUID(@RequestParam String uUIDName,
                                       @RequestParam String macId,
                                       @RequestParam String dateOfPost,
                                       @RequestParam String imageName,
                                       @RequestParam String versionId) throws IOException, InterruptedException {

        log.info("GetMapping(/crashReport)");
        log.info("version id = {}, imageName = {} ", versionId, imageName);
        validateUUID(uUIDName);
        validateMacId(macId);
        validateDate(dateOfPost);
        validateImageNameAndVersion(versionId, imageName);

        CrashDumpModel crashDumpModel = new CrashDumpModel(uUIDName, macId, dateOfPost, imageName, versionId);
        String crashReport = iCrashDumpUseCase.getCrashReport(crashDumpModel);
        return ResponseEntity.status(HttpStatus.OK).body(crashReport);
    }

    private void validateImageNameAndVersion(String versionId, String imageName){
        if(isEmptyString(versionId) || isEmptyString(imageName)){
            throw new BadRequestException("invalid versionId/imageName");
        }
    }

    /**
     * This function validates the crash file to upload and if invalid it throws exception.
     *
     * String  multipartFile crashdump file to upload to s3 bucket
     * @return void
     */
    private void validateCrashFile(MultipartFile multipartFile ){
        String fileName = multipartFile.getOriginalFilename();
        String[] tokens = fileName.split("[_]");

        if((multipartFile.getSize() < 100)){
            throw new BadRequestException("File is mandatory");
        }else if ((isEmptyString(fileName)) || (tokens.length != 6)){
            throw new BadRequestException("Invalid file name");
        } else if (!(fileName.endsWith(S3ServiceConstants.CRASHFILE_EXTENSION))) {
            log.error("invalid file extention");
            throw new BadRequestException("Invalid file type");
        }

        String uuid = tokens[0];
        String macID = tokens[1];
        String dateOfPost = tokens[2];

        validateUUID(uuid);
        validateMacId(macID);
        validateDateWithPrefix(dateOfPost);
    }

    /**
     * This function validates the format of UUID of the device.
     *
     * String  uuid of the device
     * @return boolean    Returns true if proper, else return false
     */
    private void validateUUID(String uuid){
        log.info("uuid = {} ", uuid);

        if((isEmptyString(uuid))
                || (uuid.length() == 0)
                || !(uuid.matches("^[0-9A-Fa-f]*$"))) {
            throw new BadRequestException("invalid UUID");
        }
    }

    /**
     * This function validates the mac address of the device.
     *
     * String  macID of the device
     * @return boolean    Returns true if proper, else return false
     */
    private void validateMacId(String macID){
        log.info("mac id passed = {}", macID);
        String[]  mac;

        if((isEmptyString(macID)) || (macID.length() != 15)) {
            throw new BadRequestException("Invalid mac id");
        }

        String macPrefix = macID.substring(0,3);

        if(!(macPrefix.equals("mac"))){
            throw new BadRequestException("Invalid mac id");
        }

        macID = macID.substring(3);
        mac = macID.split("(?<=\\G..)");
        macID = mac[0] + ":" + mac[1] + ":" + mac[2] + ":" + mac[3] + ":" + mac[4] + ":" + mac[5];

        log.info("mac id passed after split = {}", macID);
        String regex
                = "^([0-9A-Fa-f]{2}[:-])"
                + "{5}([0-9A-Fa-f]{2})|"
                + "([0-9a-fA-F]{4}\\."
                + "[0-9a-fA-F]{4}\\."
                + "[0-9a-fA-F]{4})$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(macID);

        if (!m.matches()) {
            throw new BadRequestException("Invalid mac id: " + macID);
        }
    }

    /**
     * This function validates the date format.
     *
     * String  dateInput
     * @return boolean    Returns true if proper, else return false
     */
    private void validateDate(String dateInput){
        log.info("date passed: {}", dateInput);

        if((isEmptyString(dateInput)) || (dateInput.length() != 19)){
            throw new BadRequestException("Invalid date");
        }

        String[] tokens = dateInput.split("[-]");
        log.info("date tokens= {}", tokens.length);
        if((tokens.length != 6) || (tokens[0].length() != 4)){
            throw new BadRequestException("Invalid date");
        }else{
            for (int i = 1; i < 6; i++) {
                if (tokens[i].length() != 2) {
                    throw new BadRequestException("Invalid date");
                }
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        dateFormat.setLenient(false);

        try {
            Date javaDate = dateFormat.parse(dateInput);
        }
        /* Date format is invalid */
        catch (ParseException exp){
            log.error(dateInput+" is Invalid Date format, {} ", exp.getMessage(), exp);
            throw new BadRequestException("Invalid date");
        }
    }

    private void validateDateWithPrefix(String dateInput){
        String str = dateInput.substring(0,3);
        boolean isValidDate = true;

       if((isEmptyString(dateInput))
               || (dateInput.length() != 22)
               || (!(str.equals("dat")))) {
            throw new BadRequestException("Invalid date");
        }
        validateDate(dateInput.substring(3));
    }

    /**
     * This function validates the symbol file.
     *
     * String  fileName Name of the symbol file
     * @return boolean    Returns true if proper, else return false
     */
    private void validateSymbolFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String[] tokens = fileName.split("[_]");

         if((multipartFile.getSize() < 100)){
            log.error("file is empty");
            throw new BadRequestException("File is mandatory");
        } else if ((isEmptyString(fileName)) || ((tokens.length != 4))){
            log.error("invalid image name ");
            throw new BadRequestException("invalid image name");
        } else if (!(fileName.endsWith(S3ServiceConstants.SYMBOLFILE_EXTENSION))){
            log.error("invalid file extention");
            throw new BadRequestException("Invalid file type");
        } else if(tokens[3].length() <= ((S3ServiceConstants.SYMBOLFILE_EXTENSION).length())){
            log.error("invalid version");
            throw new BadRequestException("Invalid version");
        }
    }

    private boolean isEmptyString(String str){
        if(str == null || str.trim().isEmpty() || str.contains(",")){
            return true;
        }
        return false;
    }

}
